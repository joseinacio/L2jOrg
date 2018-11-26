package org.l2j.gameserver.network.l2.s2c;

public final class ExShowStatPage extends L2GameServerPacket
{
	private final int _page;

	public ExShowStatPage(int page)
	{
		_page = page;
	}

	@Override
	protected void writeImpl()
	{
		writeInt(_page);
	}
}