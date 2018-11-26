package org.l2j.gameserver.network.l2.c2s;

import org.l2j.gameserver.model.Player;
import org.l2j.gameserver.model.entity.boat.Boat;
import org.l2j.gameserver.utils.Location;

// format: cddddd
public class CannotMoveAnymoreInVehicle extends L2GameClientPacket
{
	private Location _loc = new Location();
	private int _boatid;

	@Override
	protected void readImpl()
	{
		_boatid = readInt();
		_loc.x = readInt();
		_loc.y = readInt();
		_loc.z = readInt();
		_loc.h = readInt();
	}

	@Override
	protected void runImpl()
	{
		Player player = getClient().getActiveChar();
		if(player == null)
			return;

		Boat boat = player.getBoat();
		if(boat != null && boat.getBoatId() == _boatid)
		{
			player.setInBoatPosition(_loc);
			player.setHeading(_loc.h);
			player.broadcastPacket(boat.inStopMovePacket(player));
		}
	}
}