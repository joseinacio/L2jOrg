package org.l2j.gameserver.model.actor.instance;

import org.l2j.gameserver.ai.BoatAI;
import org.l2j.gameserver.enums.InstanceType;
import org.l2j.gameserver.model.Location;
import org.l2j.gameserver.model.actor.Vehicle;
import org.l2j.gameserver.model.actor.templates.CreatureTemplate;
import org.l2j.gameserver.network.serverpackets.VehicleDeparture;
import org.l2j.gameserver.network.serverpackets.VehicleInfo;
import org.l2j.gameserver.network.serverpackets.VehicleStarted;


/**
 * @author Maktakien, DS
 */
public class Boat extends Vehicle {

    public Boat(CreatureTemplate template) {
        super(template);
        setInstanceType(InstanceType.L2BoatInstance);
        setAI(new BoatAI(this));
    }

    @Override
    public boolean isBoat() {
        return true;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public boolean moveToNextRoutePoint() {
        final boolean result = super.moveToNextRoutePoint();
        if (result) {
            broadcastPacket(new VehicleDeparture(this));
        }

        return result;
    }

    @Override
    public void oustPlayer(Player player) {
        super.oustPlayer(player);

        final Location loc = getOustLoc();
        if (player.isOnline()) {
            player.teleToLocation(loc.getX(), loc.getY(), loc.getZ());
        } else {
            player.setXYZInvisible(loc.getX(), loc.getY(), loc.getZ()); // disconnects handling
        }
    }

    @Override
    public void stopMove(Location loc) {
        super.stopMove(loc);

        broadcastPacket(new VehicleStarted(this, 0));
        broadcastPacket(new VehicleInfo(this));
    }

    @Override
    public void sendInfo(Player activeChar) {
        activeChar.sendPacket(new VehicleInfo(this));
    }
}
