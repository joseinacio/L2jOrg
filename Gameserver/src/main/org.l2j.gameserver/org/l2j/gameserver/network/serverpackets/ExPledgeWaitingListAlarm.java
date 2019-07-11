package org.l2j.gameserver.network.serverpackets;

import org.l2j.gameserver.network.GameClient;
import org.l2j.gameserver.network.ServerPacketId;

/**
 * @author Sdw
 */
public class ExPledgeWaitingListAlarm extends ServerPacket {
    public static final ExPledgeWaitingListAlarm STATIC_PACKET = new ExPledgeWaitingListAlarm();

    @Override
    public void writeImpl(GameClient client) {
        writeId(ServerPacketId.EX_PLEDGE_WAITING_LIST_ALARM);

    }

}
