package org.l2j.gameserver.network.l2.s2c;

import org.l2j.gameserver.skills.TimeStamp;

public class ExUseSharedGroupItem extends L2GameServerPacket
{
	private int _itemId, _grpId, _remainedTime, _totalTime;

	public ExUseSharedGroupItem(int grpId, TimeStamp timeStamp)
	{
		_grpId = grpId;
		_itemId = timeStamp.getId();
		_remainedTime = (int) (timeStamp.getReuseCurrent() / 1000);
		_totalTime = (int) (timeStamp.getReuseBasic() / 1000);
	}

	@Override
	protected final void writeImpl()
	{
		writeInt(_itemId);
		writeInt(_grpId);
		writeInt(_remainedTime);
		writeInt(_totalTime);
	}
}