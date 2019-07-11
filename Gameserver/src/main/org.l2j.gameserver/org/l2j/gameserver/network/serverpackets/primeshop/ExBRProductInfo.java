package org.l2j.gameserver.network.serverpackets.primeshop;

import org.l2j.gameserver.model.actor.instance.Player;
import org.l2j.gameserver.model.primeshop.PrimeShopProduct;
import org.l2j.gameserver.model.primeshop.PrimeShopItem;
import org.l2j.gameserver.network.GameClient;
import org.l2j.gameserver.network.ServerPacketId;
import org.l2j.gameserver.network.serverpackets.ServerPacket;

/**
 * @author Gnacik
 */
public class ExBRProductInfo extends ServerPacket {
    private final PrimeShopProduct _item;
    private final int _charPoints;
    private final long _charAdena;

    public ExBRProductInfo(PrimeShopProduct item, Player player) {
        _item = item;
        _charPoints = player.getL2Coins();
        _charAdena = player.getAdena();
    }

    @Override
    public void writeImpl(GameClient client) {
        writeId(ServerPacketId.EX_BR_PRODUCT_INFO);

        writeInt(_item.getId());
        writeInt(_item.getPrice());
        writeInt(_item.getItems().size());
        for (PrimeShopItem item : _item.getItems()) {
            writeInt(item.getId());
            writeInt((int) item.getCount());
            writeInt(item.getWeight());
            writeInt(item.isTradable());
        }
        writeLong(_charAdena);
        writeLong(_charPoints);
        writeLong(0x00); // Hero coins
    }

}
