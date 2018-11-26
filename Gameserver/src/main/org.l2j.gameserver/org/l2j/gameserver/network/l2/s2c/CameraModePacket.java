package org.l2j.gameserver.network.l2.s2c;

public class CameraModePacket extends L2GameServerPacket
{
	int _mode;

	/**
	 * Forces client camera mode change
	 * @param mode
	 * 0 - third person cam
	 * 1 - first person cam
	 */
	public CameraModePacket(int mode)
	{
		_mode = mode;
	}

	@Override
	protected final void writeImpl()
	{
		writeInt(_mode);
	}
}