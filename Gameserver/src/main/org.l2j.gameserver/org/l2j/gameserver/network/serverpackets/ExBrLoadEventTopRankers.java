package org.l2j.gameserver.network.serverpackets;

import org.l2j.gameserver.network.GameClient;
import org.l2j.gameserver.network.ServerExPacketId;

/**
 * Halloween rank list server packet.
 */
public class ExBrLoadEventTopRankers extends ServerPacket {
    private final int _eventId;
    private final int _day;
    private final int _count;
    private final int _bestScore;
    private final int _myScore;

    public ExBrLoadEventTopRankers(int eventId, int day, int count, int bestScore, int myScore) {
        _eventId = eventId;
        _day = day;
        _count = count;
        _bestScore = bestScore;
        _myScore = myScore;
    }

    @Override
    public void writeImpl(GameClient client) {
        writeId(ServerExPacketId.EX_BR_LOAD_EVENT_TOP_RANKERS_ACK);

        writeInt(_eventId);
        writeInt(_day);
        writeInt(_count);
        writeInt(_bestScore);
        writeInt(_myScore);
    }

}
