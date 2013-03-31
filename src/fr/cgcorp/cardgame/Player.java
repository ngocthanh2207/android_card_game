package fr.cgcorp.cardgame;

public class Player {
	protected String username;
	
	public Player(String username) {
		super();
		this.username = username;
	}

	@Override
	public String toString() {
		return "Player [username=" + username + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
}
