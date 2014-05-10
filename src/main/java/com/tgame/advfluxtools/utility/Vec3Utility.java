package com.tgame.advfluxtools.utility;

import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author tgame14
 * @since 30/04/14
 */
public class Vec3Utility
{

	public static Vec3 cloneVec3(Vec3 vec3)
	{
		return Vec3.createVectorHelper(vec3.xCoord, vec3.yCoord, vec3.zCoord);
	}

	public static MovingObjectPosition rayTrace(World world, Vec3 origin, Vec3 target, boolean collisionFlag)
	{
		MovingObjectPosition pickedBlock = rayTraceBlocks(world, cloneVec3(origin), cloneVec3(target), collisionFlag);
		MovingObjectPosition pickedEntity = rayTraceEntities(world, cloneVec3(origin), cloneVec3(target));

		if (pickedBlock == null)
		{
			return pickedEntity;
		}
		else if (pickedEntity == null)
		{
			return pickedBlock;
		}
		else
		{
			double dBlock = origin.distanceTo(cloneVec3(pickedBlock.hitVec));
			double dEntity = origin.distanceTo(cloneVec3(pickedEntity.hitVec));

			if (dEntity < dBlock)
			{
				return pickedEntity;
			}
			else
			{
				return pickedBlock;
			}
		}
	}

	public static MovingObjectPosition rayTraceBlocks(World world,Vec3 origin, Vec3 target, boolean collisionFlag)
	{
		return world.rayTraceBlocks(origin, target);
	}

	/**
	 * Does an entity raytrace.
	 *
	 * @param world  - The world object.
	 * @param target - The rotation in terms of Vector3. Convert using
	 *               getDeltaPositionFromRotation()
	 * @return The target hit.
	 */
	public static MovingObjectPosition rayTraceEntities(World world,Vec3 origin, Vec3 target)
	{
		MovingObjectPosition pickedEntity = null;
		Vec3 startingPosition = origin;
		Vec3 look = cloneVec3(target);
		double reachDistance = origin.distanceTo(target);
		//Vec3 reachPoint = Vec3.createVectorHelper(startingPosition.xCoord + look.xCoord * reachDistance, startingPosition.yCoord + look.yCoord * reachDistance, startingPosition.zCoord + look.zCoord * reachDistance);

		double checkBorder = 1.1 * reachDistance;
		AxisAlignedBB boxToScan = AxisAlignedBB.getAABBPool().getAABB(-checkBorder, -checkBorder, -checkBorder, checkBorder, checkBorder, checkBorder).offset(origin.xCoord, origin.yCoord, origin.zCoord);

		@SuppressWarnings("unchecked")
		List<Entity> entitiesInBounds = world.getEntitiesWithinAABBExcludingEntity(null, boxToScan);
		double closestEntity = reachDistance;

		if (entitiesInBounds == null || entitiesInBounds.isEmpty())
		{
			return null;
		}
		for (Entity possibleHits : entitiesInBounds)
		{
			if (possibleHits != null && possibleHits.canBeCollidedWith() && possibleHits.boundingBox != null)
			{
				float border = possibleHits.getCollisionBorderSize();
				AxisAlignedBB aabb = possibleHits.boundingBox.expand(border, border, border);
				MovingObjectPosition hitMOP = aabb.calculateIntercept(startingPosition, cloneVec3(target));

				if (hitMOP != null)
				{
					if (aabb.isVecInside(startingPosition))
					{
						if (0.0D < closestEntity || closestEntity == 0.0D)
						{
							pickedEntity = new MovingObjectPosition(possibleHits);
							if (pickedEntity != null)
							{
								pickedEntity.hitVec = hitMOP.hitVec;
								closestEntity = 0.0D;
							}
						}
					}
					else
					{
						double distance = startingPosition.distanceTo(hitMOP.hitVec);

						if (distance < closestEntity || closestEntity == 0.0D)
						{
							pickedEntity = new MovingObjectPosition(possibleHits);
							pickedEntity.hitVec = hitMOP.hitVec;
							closestEntity = distance;
						}
					}
				}
			}
		}
		return pickedEntity;
	}
}
