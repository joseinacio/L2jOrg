package handlers.itemhandlers;

import org.l2j.gameserver.handler.IItemHandler;
import org.l2j.gameserver.model.actor.Playable;
import org.l2j.gameserver.model.item.instance.Item;
import org.l2j.gameserver.network.SystemMessageId;
import org.l2j.gameserver.network.serverpackets.ShowXMasSeal;

import static org.l2j.gameserver.util.GameUtils.isPlayer;

/**
 * @author devScarlet, mrTJO
 */
public class SpecialXMas implements IItemHandler
{
	@Override
	public boolean useItem(Playable playable, Item item, boolean forceUse)
	{
		if (!isPlayer(playable))
		{
			playable.sendPacket(SystemMessageId.YOUR_PET_CANNOT_CARRY_THIS_ITEM);
			return false;
		}
		
		playable.sendPacket(new ShowXMasSeal(item.getId()));
		return true;
	}
}
