package fr.cgcorp.cardgame;

import java.util.HashMap;

public class CardSet extends HashMap<String, CardDefinition> {
	
	protected static CardSet mSet = new CardSet();
	
	protected CardSet() {
		super();
		
		put("baba", new CardDefinition("baba", 1000, 5, "fezfezg"));
		put("bobo", new CardDefinition("bobo", 1100, 6, "eztze"));
		put("bibi", new CardDefinition("bibi", 900, 4, "kulyub"));
		put("bubu", new CardDefinition("bubu", 1200, 7, "wsdza"));
	}
	
	public static CardDefinition getCardDefinition(String name) {
		return mSet.get(name);
	}
	
}
