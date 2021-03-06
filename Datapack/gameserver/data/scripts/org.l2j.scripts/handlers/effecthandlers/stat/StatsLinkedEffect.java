package handlers.effecthandlers.stat;

import org.l2j.gameserver.engine.skill.api.Skill;
import org.l2j.gameserver.engine.skill.api.SkillEffectFactory;
import org.l2j.gameserver.model.StatsSet;
import org.l2j.gameserver.model.actor.Creature;
import org.l2j.gameserver.model.effects.AbstractEffect;
import org.l2j.gameserver.model.stats.Stat;

/**
 * @author JoeAlisson
 */
public class StatsLinkedEffect extends AbstractEffect {

    public final double power;
    private final Stat stat;
    private final Stat baseStat;

    public StatsLinkedEffect(StatsSet data) {
        power = data.getDouble("power", 0);
        stat = data.getEnum("stat", Stat.class);
        baseStat = data.getEnum("base-stat", Stat.class);
    }

    @Override
    public void pump(Creature effected, Skill skill) {
        var base = effected.getStats().getValue(baseStat);
        effected.getStats().mergeAdd(stat, base * power / 100);
    }

    public static class Factory implements SkillEffectFactory {

        @Override
        public AbstractEffect create(StatsSet data) {
            return new StatsLinkedEffect(data);
        }

        @Override
        public String effectName() {
            return "stats-linked";
        }
    }
}
