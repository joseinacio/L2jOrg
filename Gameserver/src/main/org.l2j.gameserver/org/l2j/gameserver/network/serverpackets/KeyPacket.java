package org.l2j.gameserver.network.serverpackets;

import org.l2j.gameserver.network.GameClient;
import org.l2j.gameserver.network.ServerPacketId;
import org.l2j.gameserver.settings.ServerSettings;

import static org.l2j.commons.configuration.Configurator.getSettings;
import static org.l2j.gameserver.ServerType.CLASSIC;

public final class KeyPacket extends ServerPacket {
    private final byte[] _key;
    private final int _result;

    public KeyPacket(byte[] key, int result) {
        _key = key;
        _result = result;
    }

    @Override
    public void writeImpl(GameClient client) {
        writeId(ServerPacketId.VERSION_CHECK);

        writeByte((byte) _result); // 0 - wrong protocol, 1 - protocol ok
        for (int i = 0; i < 8; i++) {
            writeByte(_key[i]); // key
        }
        var serverSettings = getSettings(ServerSettings.class);
        writeInt(0x01); // ciphen enabled
        writeInt(serverSettings.serverId());
        writeByte((byte) 0x00); // merged server
        writeInt(0x00); // obfuscation key
        writeByte((byte) ((serverSettings.type() & CLASSIC.getMask()) != 0 ? 0x01 : 0x00)); // isClassic
        writeByte((byte) 0x00); // queued ?
    }

}
