package com.tgame.advfluxtools.entities;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.tgame.advfluxtools.Settings;
import com.tgame.advfluxtools.items.EnumLaserMode;
import com.tgame.advfluxtools.utility.Vec3Utility;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.common.registry.IThrowableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * @author tgame14
 * @since 30/04/14
 */
public class EntityLaserProjectile extends Entity implements IEntityAdditionalSpawnData, IThrowableEntity
{
	private int lifeCycle; //kill bullets after 30 seconds or more
	protected Entity owner;

	private EnumLaserMode enumMode;


	public EntityLaserProjectile(World par1World, EntityLivingBase shooter, EnumLaserMode mode, int lifeCycle)
	{
		this(par1World, Vec3.createVectorHelper(shooter.posX, shooter.posY, shooter.posZ), shooter.rotationYaw, shooter.rotationPitch, shooter, 1);

		this.setSize(0.5F, 0.3F);
		this.owner = shooter;
		this.enumMode = mode;
		this.lifeCycle = lifeCycle;

	}



	private EntityLaserProjectile(World world, Vec3 origin, float yaw, float pitch, EntityLivingBase shooter, float speed)
	{
		super(world);
		double yawRad = Math.toRadians(yaw);
		double pitchRad = Math.toRadians(pitch);
		this.setLocationAndAngles(origin.xCoord, origin.yCoord, origin.zCoord, (float) yawRad, (float) pitchRad);
		this.setPosition(posX, posY, posZ);

		double startMotX = -Math.sin(yawRad) * Math.cos(pitchRad);
		double startMotY = -Math.sin(pitchRad);
		double startMotZ = Math.cos(yawRad) * Math.cos(pitchRad);

		this.setProjectileHeading(startMotX, startMotY, startMotZ, speed);
		Settings.LOGGER.info("motX " + this.motionX + " motY " + this.motionY + " motZ " + this.motionZ);

	}



	public void setProjectileHeading(double startMotX, double startMotY, double startMotZ, float speed)
	{
		double currSpeed = Math.hypot(Math.hypot(startMotX, startMotY), startMotZ);

		this.motionX = startMotX / (currSpeed * speed);
		this.motionY = startMotY / (currSpeed * speed);
		this.motionZ = startMotZ / (currSpeed * speed);

		this.prevRotationYaw = this.rotationYaw = (float) Math.toDegrees(Math.atan2(startMotX, startMotZ));
		this.prevRotationPitch = this.rotationPitch = (float) Math.toDegrees(Math.atan2(startMotY, Math.hypot(startMotX, startMotZ)));

	}

	@Override
	protected void entityInit()
	{

	}



	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt)
	{
		String ownerName = nbt.getString("owner");
		if (ownerName != null && !ownerName.equals("null"))
		{
			this.owner = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().getPlayerForUsername(ownerName);
		}

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt)
	{
		if (owner != null)
		{
			nbt.setString("owner", owner.getEntityName());
		}
		else
		{
			nbt.setString("owner", "null");
		}

	}

	@Override
	public void writeSpawnData(ByteArrayDataOutput data)
	{
		data.writeUTF(enumMode.toString());
		if (this.owner == null)
			data.writeUTF("null");
		else
			data.writeUTF(this.owner.getEntityName());

	}

	@Override
	public void readSpawnData(ByteArrayDataInput data)
	{
		String enummode = data.readUTF();
		this.enumMode = EnumLaserMode.valueOf(enummode);

		String ownerName = data.readUTF();

		for (Object obj : this.worldObj.loadedEntityList)
		{
			if (((Entity) obj).getEntityName().equals(ownerName))
				this.owner = (Entity) obj;
		}

	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		this.ticksExisted++;

		if (this.ticksExisted > this.lifeCycle)
		{
			this.setDead();
		}

		Vec3 currPos = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
		Vec3 nextPos = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		MovingObjectPosition hit = worldObj.rayTraceBlocks_do_do(Vec3Utility.cloneVec3(currPos), Vec3Utility.cloneVec3(nextPos), false, true);

		if (hit != null)
		{
			nextPos = Vec3Utility.cloneVec3(hit.hitVec);

			if (!isDead)
			{
				enumMode.onImpact(worldObj, this);
			}
		}
		this.setPosition(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
	}


	@Override
	public Entity getThrower()
	{
		return this.owner;
	}

	@Override
	public void setThrower(Entity entity)
	{
		this.owner = entity;
	}
}
