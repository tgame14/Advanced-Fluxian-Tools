package com.tgame.advfluxtools;

import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author tgame14
 * @since 29/04/14
 */
public class Settings
{
	public static final String ID = "advfluxtools";
	public static final String NAME = "Advanced Fluxian Tools";
	public static final String CHANNEL = ID;
	public static final String VERSION = "@VERSION@";
	public static final String AUTHOR = "@AUTHOR@";
    public static final String DOMAIN = "com.tgame.advfluxtools";

	public static final String RESOURCE_LOCATION = ID + ":";

	public static final Logger LOGGER = LogManager.getLogger(ID);
	public static Configuration CONFIGURATION;



}
