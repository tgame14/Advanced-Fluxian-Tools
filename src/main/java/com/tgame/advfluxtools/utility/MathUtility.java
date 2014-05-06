package com.tgame.advfluxtools.utility;

/**
 * @author tgame14
 * @since 30/04/14
 */
public class MathUtility
{
	public static int minInt(int a, int b)
	{
		return a < b ? a : b;
	}

	public static double hypot(double a, double b)
	{
		return Math.sqrt(a * a + b * b);
	}

	public static double hypot(int a, int b)
	{
		return hypot((double) a, (double) b);
	}
}
