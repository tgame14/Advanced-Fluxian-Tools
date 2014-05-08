package com.tgame.advfluxtools.utility;

import java.nio.ByteBuffer;

/**
 * TODO: in 1.7 this will be a ByteBuf saved!! MAXIMUM EFFICIENCY
 *
 * @author tgame14
 * @since 07/05/14
 */
public interface IByteDataSave
{
	void writeToStream(ByteBuffer stream);

	void readFromStream(ByteBuffer stream);

}
