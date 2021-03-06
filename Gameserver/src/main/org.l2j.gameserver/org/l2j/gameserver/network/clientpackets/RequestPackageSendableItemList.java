package org.l2j.gameserver.network.clientpackets;

import org.l2j.gameserver.model.actor.instance.Player;
import org.l2j.gameserver.network.serverpackets.PackageSendableList;

/**
 * @author Mobius
 */
public class RequestPackageSendableItemList extends ClientPacket {
    private int _objectId;

    @Override
    public void readImpl() {
        _objectId = readInt();
    }

    @Override
    public void runImpl() {
        final Player activeChar = client.getPlayer();
        if (activeChar == null) {
            return;
        }
        client.sendPacket(new PackageSendableList(1, activeChar, _objectId));
        client.sendPacket(new PackageSendableList(2, activeChar, _objectId));
    }
}
