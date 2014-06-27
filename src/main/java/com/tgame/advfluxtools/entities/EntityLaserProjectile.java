package com.tgame.advfluxtools.entities;

import com.tgame.advfluxtools.items.EnumLaserMode;
import com.tgame.mods.libs.utility.MathUtility;
import com.tgame.mods.libs.utility.Vec3Utility;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.common.registry.IThrowableEntity;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * @author tgame14
 * @since 30/04/14
 */
public class EntityLaserProjectile extends Entity implements IEntityAdditionalSpawnData, IThrowableEntity
{
    protected Entity owner;
    private int lifeCycle; //kill bullets after 30 seconds or more
    private EnumLaserMode enumMode;
    private int blocksHit;

    public EntityLaserProjectile (World par1World, EntityLivingBase shooter, EnumLaserMode mode, int lifeCycle, float speed)
    {
        this(par1World, Vec3.createVectorHelper(shooter.posX, shooter.posY, shooter.posZ), shooter.rotationYaw, shooter.rotationPitch, shooter, speed);

        this.setSize(0.5F, 0.3F);
        this.owner = shooter;
        this.enumMode = mode;
        this.lifeCycle = lifeCycle;
        this.blocksHit = 0;

    }

    private EntityLaserProjectile (World world, Vec3 origin, float yaw, float pitch, EntityLivingBase shooter, float speed)
    {
        super(world);
        //Settings.LOGGER.info("Spawned on: " + (world.isRemote ? "client" : "server"));
        double yawRad = Math.toRadians(yaw);
        double pitchRad = Math.toRadians(pitch);
        this.setLocationAndAngles(origin.xCoord, origin.yCoord, origin.zCoord, (float) yawRad, (float) pitchRad);
        this.setPosition(posX, posY, posZ);

        double startMotX = -Math.sin(yawRad) * Math.cos(pitchRad);
        double startMotY = -Math.sin(pitchRad);
        double startMotZ = Math.cos(yawRad) * Math.cos(pitchRad);

        this.setProjectileHeading(startMotX, startMotY, startMotZ, speed);

    }

    public int getBlocksHit ()
    {
        return blocksHit;
    }

    public void setBlocksHit (int blocksHit)
    {
        this.blocksHit = blocksHit;
    }

    //TODO: enforce a new Math.hypot as the java one is too precise and too slow
    public void setProjectileHeading (double startMotX, double startMotY, double startMotZ, float speed)
    {
        double currSpeed = MathUtility.hypot(MathUtility.hypot(startMotX, startMotY), startMotZ);

        this.motionX = startMotX / (currSpeed * speed);
        this.motionY = startMotY / (currSpeed * speed);
        this.motionZ = startMotZ / (currSpeed * speed);

        this.prevRotationYaw = this.rotationYaw = (float) Math.toDegrees(Math.atan2(startMotX, startMotZ));
        this.prevRotationPitch = this.rotationPitch = (float) Math.toDegrees(Math.atan2(startMotY, MathUtility.hypot(startMotX, startMotZ)));

    }

    @Override
    protected void entityInit ()
    {

    }


    @Override
    protected void readEntityFromNBT (NBTTagCompound nbt)
    {
        String ownerName = nbt.getString("owner");
        if (ownerName != null && !ownerName.equals("null"))
        {
            this.owner = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().func_152612_a(ownerName);
        }

    }

    @Override
    protected void writeEntityToNBT (NBTTagCompound nbt)
    {
        if (owner != null)
        {
            nbt.setString("owner", owner.getCommandSenderName());
        }
        else
        {
            nbt.setString("owner", "null");
        }

    }

    @Override
    public void writeSpawnData (ByteBuf data)
    {
        ByteBufUtils.writeUTF8String(data, enumMode.toString());
        if (this.owner == null)
            ByteBufUtils.writeUTF8String(data, "null");
        else
            ByteBufUtils.writeUTF8String(data, this.owner.getCommandSenderName());

    }

    @Override
    public void readSpawnData (ByteBuf data)
    {
        String enummode = ByteBufUtils.readUTF8String(data);
        this.enumMode = EnumLaserMode.valueOf(enummode);

        String ownerName = ByteBufUtils.readUTF8String(data);

        for (Object obj : this.worldObj.loadedEntityList)
        {
            if (((Entity) obj).getCommandSenderName().equals(ownerName))
                this.owner = (Entity) obj;
        }

    }

    @Override
    public void onUpdate ()
    {
        super.onUpdate();
        this.ticksExisted++;

        if (this.ticksExisted > this.lifeCycle)
        {
            this.setDead();
            return;
        }

        Vec3 currPos = Vec3.createVectorHelper(this.posX, this.posY + 1.62, this.posZ);
        Vec3 nextPos = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY + 1.62, this.posZ + this.motionZ);
        MovingObjectPosition hit = Vec3Utility.rayTrace(worldObj, Vec3Utility.cloneVec3(currPos), Vec3Utility.cloneVec3(nextPos), false);

        if (hit != null)
        {
            nextPos = Vec3Utility.cloneVec3(hit.hitVec);

            if (!isDead)
            {
                if (hit.entityHit != null && hit.entityHit == getThrower())
                {
                    this.setPosition(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
                    return;
                }
                enumMode.onImpact(worldObj, this, hit);
            }
        }
        this.setPosition(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
    }

    @Override
    public Entity getThrower ()
    {
        return this.owner;
    }

    @Override
    public void setThrower (Entity entity)
    {
        this.owner = entity;
    }
}
