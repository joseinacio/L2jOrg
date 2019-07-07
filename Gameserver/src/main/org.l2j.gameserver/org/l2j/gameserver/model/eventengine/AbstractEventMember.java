package org.l2j.gameserver.model.eventengine;

import org.l2j.gameserver.model.actor.instance.L2PcInstance;
import org.l2j.gameserver.network.serverpackets.ServerPacket;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @param <T>
 * @author UnAfraid
 */
public abstract class AbstractEventMember<T extends AbstractEvent<?>> {
    private final T _event;
    private final AtomicInteger _score = new AtomicInteger();
    private final L2PcInstance player;

    public AbstractEventMember(L2PcInstance player, T event) {
        this.player = player;
        _event = event;
    }

    public final int getObjectId() {
        return player.getObjectId();
    }

    public L2PcInstance getPlayer() {
        return player;
    }

    public void sendPacket(ServerPacket... packets) {
        final L2PcInstance player = getPlayer();
        if (player != null) {
            for (ServerPacket packet : packets) {
                player.sendPacket(packet);
            }
        }
    }

    public int getClassId() {
        final L2PcInstance player = getPlayer();
        if (player != null) {
            return player.getClassId().getId();
        }
        return 0;
    }

    public int getScore() {
        return _score.get();
    }

    public void setScore(int score) {
        _score.set(score);
    }

    public int incrementScore() {
        return _score.incrementAndGet();
    }

    public int decrementScore() {
        return _score.decrementAndGet();
    }

    public int addScore(int score) {
        return _score.addAndGet(score);
    }

    public final T getEvent() {
        return _event;
    }
}
