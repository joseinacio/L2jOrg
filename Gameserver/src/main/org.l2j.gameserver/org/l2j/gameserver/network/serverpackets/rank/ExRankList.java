package org.l2j.gameserver.network.serverpackets.rank;

import org.l2j.gameserver.data.database.RankManager;
import org.l2j.gameserver.data.database.data.RankData;
import org.l2j.gameserver.network.GameClient;
import org.l2j.gameserver.network.ServerPacketId;
import org.l2j.gameserver.network.serverpackets.ServerPacket;

import java.util.Collections;
import java.util.List;

/**
 * @author JoeAlisson
 */
public class ExRankList extends ServerPacket {

    private final int race;
    private final byte group;
    private final byte scope;

    public ExRankList(byte group, byte scope, int race) {
        this.group = group;
        this.scope = scope;
        this.race = race;
    }

    @Override
    protected void writeImpl(GameClient client)  {
        writeId(ServerPacketId.EX_RANKING_CHAR_RANKERS);
        writeByte(group);
        writeByte(scope);
        writeInt(race);

        List<RankData> rankers = switch (group) {
            case 0 -> RankManager.getInstance().getRankers();
            case 1 -> RankManager.getInstance().getRaceRankers(race);
            default -> Collections.emptyList();
        };

        writeInt(rankers.size());

        for (var ranker : rankers) {
            writeSizedString(ranker.getPlayerName());
            writeSizedString(ranker.getClanName());
            writeInt(ranker.getLevel());
            writeInt(ranker.getClassId());
            writeInt(ranker.getRace());
            writeInt(ranker.getRank());
            writeInt(ranker.getRankSnapshot());
            writeInt(ranker.getRankRaceSnapshot());
        }
    }
}
