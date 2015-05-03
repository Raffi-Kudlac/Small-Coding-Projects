/**
 * Purpose:
 * 		Each card instance is a card in the game.
 * 		Each card has a name and a type.
 * 
 * 		Cards can only be the same as another. No card is greater then another.
 * @author Raffi
 *
 */
public class Card implements Comparable<Card> {
	
	String name; // The name of the card
	String type; // The type of the card
	public Card(String nme, String type)
	{
		name = nme;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	
	public String getType()
	{
		return type;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString()
	{
		return name;
	}
	
	@Override
	public int compareTo(Card o) {
		// TODO Auto-generated method stub
		if(this.name.equalsIgnoreCase( o.getName()))		
			return 0;
		else
			return -1;
	}
	
	@Override
	public boolean equals(Object obj) {
	    
		if(obj == null)
			return false;
		
		else if(!obj.getClass().isInstance(this))
			return false;
		
		Card c = (Card) obj;
		
		if(c.getName().equalsIgnoreCase(this.name))
			return true;
		else
			return false;
	}
}
