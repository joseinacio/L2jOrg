package org.l2j.gameserver.network.clientpackets;

import org.l2j.gameserver.model.actor.instance.Player;
import org.l2j.gameserver.network.serverpackets.ExShowReceivedPostList;
import org.l2j.gameserver.settings.GeneralSettings;

import static java.util.Objects.isNull;
import static org.l2j.commons.configuration.Configurator.getSettings;

/**
 * @author Migi, DS
 */
public final class RequestReceivedPostList extends ClientPacket {
    @Override
    public void readImpl() {

    }

    @Override
    public void runImpl() {
        final Player activeChar = client.getPlayer();
        if (isNull(activeChar) || !getSettings(GeneralSettings.class).allowMail()) {
            return;
        }

        // if (!activeChar.isInsideZone(ZoneId.PEACE))
        // {
        // activeChar.sendPacket(SystemMessageId.YOU_CANNOT_RECEIVE_OR_SEND_MAIL_WITH_ATTACHED_ITEMS_IN_NON_PEACE_ZONE_REGIONS);
        // return;
        // }

        client.sendPacket(new ExShowReceivedPostList(activeChar.getObjectId()));
    }
}
