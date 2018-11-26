package org.l2j.gameserver.network.l2.s2c;

import gnu.trove.iterator.TIntIntIterator;
import gnu.trove.map.TIntIntMap;
import gnu.trove.map.hash.TIntIntHashMap;

import org.l2j.gameserver.model.Player;
import org.l2j.gameserver.model.quest.QuestState;

public class GMViewQuestInfoPacket extends L2GameServerPacket
{
	private final String _characterName;
	private final TIntIntMap _quests = new TIntIntHashMap();

	public GMViewQuestInfoPacket(Player targetCharacter)
	{
		_characterName = targetCharacter.getName();
		for(QuestState quest : targetCharacter.getAllQuestsStates())
			if(quest.getQuest().isVisible(targetCharacter) && quest.isStarted())
				_quests.put(quest.getQuest().getId(), quest.getCondsMask());
	}

	@Override
	protected final void writeImpl()
	{
		writeString(_characterName);
		writeShort(_quests.size());
		for(TIntIntIterator iterator = _quests.iterator(); iterator.hasNext();)
		{
			iterator.advance();

			writeInt(iterator.key());
			writeInt(iterator.value());
		}
		writeShort(0); //количество элементов типа: ddQd , как-то связано с предметами
	}
}