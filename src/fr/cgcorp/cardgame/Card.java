package fr.cgcorp.cardgame;

import java.util.UUID;


public class Card {

	protected CardDefinition definition;
	protected UUID uid;

	public Card(CardDefinition definition) {
		super();
		this.definition = definition;
		this.uid = UUID.randomUUID();
	}

	public CardDefinition getDefinition() {
		return definition;
	}

	public void setDefinition(CardDefinition definition) {
		this.definition = definition;
	}

	public UUID getUid() {
		return uid;
	}
	
	@Override
	public String toString() {
		return "Card [definition=" + definition.getName() + "]";
	}
}
