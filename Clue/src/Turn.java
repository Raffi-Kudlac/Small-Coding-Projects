
/**
 * Purpose:
 * 		Each turn instance holds what happened in a single turn of the game.
 * 		Who started the rumor, what the rumor was and who answered it.
 * @author Raffi
 *
 */

public class Turn {
	//Holds the elements that were asked in the question
	//0-Person, 1- room, 2- weapon
	Card[] elements = new Card[3];
	
	Player askedBy;
	Player answeredBy;
	
	public Turn(Card[] query, Player askedBy, Player cardGivenBy)
	{
		elements = query;
		this.askedBy = askedBy;
		answeredBy = cardGivenBy;
		
	}
	
	public Card getElement(int index)
	{
		return elements[index];
	}
	public Player getAskedBy() {
		return askedBy;
	}

	public void setAskedBy(Player askedBy) {
		this.askedBy = askedBy;
	}
	public Card[] getRumor()
	{
		return elements;
	}
	public Player getAnsweredBy() {
		return answeredBy;
	}

	public void setAnsweredBy(Player answeredBy) 
	{
		this.answeredBy = answeredBy;
	}
	
	public String toString()
	{
		String m;
		
		m = askedBy.playerName + " started a rumor that " + elements[0] + " commited murder " +
		" in the " + elements[1] + " with the " + elements[2];
		
		m.concat(" The rumor was answered by " + answeredBy.playerName);
				
		
		return m;
	}

}
