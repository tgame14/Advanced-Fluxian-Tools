package com.tgame.advfluxtools.utility;

import net.minecraft.util.Vec3;

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
}
