package fr.cgcorp.cardgame;

public class CardDefinition {
	protected String name;
	protected int points;
	protected int cost;
	protected String description;

	public CardDefinition(String name, int points, int cost,
			String description) {
		super();
		this.name = name;
		this.points = points;
		this.cost = cost;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "CardDefinition [name=" + name + ", points=" + points
				+ ", cost=" + cost + ", description=" + description + "]";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String mName) {
		this.name = mName;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int mPoints) {
		this.points = mPoints;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int mCost) {
		this.cost = mCost;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String mDescription) {
		this.description = mDescription;
	}
}
