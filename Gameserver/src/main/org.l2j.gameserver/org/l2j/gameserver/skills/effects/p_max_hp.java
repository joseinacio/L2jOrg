package org.l2j.gameserver.skills.effects;

import org.l2j.gameserver.model.actor.instances.creature.Abnormal;
import org.l2j.gameserver.stats.Env;
import org.l2j.gameserver.stats.StatModifierType;
import org.l2j.gameserver.stats.Stats;
import org.l2j.gameserver.templates.skill.EffectTemplate;

public final class p_max_hp extends p_abstract_stat_effect
{
	private final boolean _restore;

	public p_max_hp(Abnormal abnormal, Env env, EffectTemplate template)
	{
		super(abnormal, env, template, Stats.MAX_HP);
		_restore = template.getParam().getBool("restore", false);
	}

	@Override
	protected void afterApplyActions()
	{
		if(!_restore || getEffected().isHealBlocked())
			return;

		double power = getValue();
		if(getModifierType() == StatModifierType.PER)
			power = power / 100. * getEffected().getMaxHp();

		if(power > 0)
			getEffected().setCurrentHp(getEffected().getCurrentHp() + power, false);
	}
}