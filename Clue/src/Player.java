import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
/**
 * @author Raffi
 * Purpose:
 * 		This class represents a player in the game
 * 		Each player holds a set of random cards and can see the other players but not see thier hands.
 * 		
 * 		Each player has a checklist that maps to a card, if there is a name mapped to the card then that player
 * 		has that card. 
 */

public class Player implements Elements{
	
	// round holds the list of rounds that has happened in the game.
	// all players can see this
	static ArrayList<Turn> round = new ArrayList<Turn>();
	
	// playerList is the list of players in the game that all players can see
	static ArrayList<Player> playerList = new ArrayList<Player>();
	
	// the checklist mentioned above
	HashMap<String,String> knowledge;
	String playerName;
	int numberOfRumorsMade = 0;
	
	// The players list of what they know was part of the murder. (holds the players answer) 
	ArrayList<Card> answer = new ArrayList<Card>();
	
	// The cards given at the beginning of the game
	ArrayList<Card> cardsGiven;
	
	// These arrays are used to make a rumor. Each rumor must contain a weapon,person and room
	Card[] personElement = new Card[6];
	Card[] roomElement = new Card[9];
	Card[] weaponElement = new Card[9];
	
	
	/**
	 * 
	 * @param nme
	 * 		The name of the player
	 * 
	 *  Purpose:
	 *  	Initialize variables
	 */
	public Player(String nme)
	{
		cardsGiven = new ArrayList<Card>();
		playerName = nme;
		knowledge = new HashMap<String,String>();
		
		knowledge.put(mustard, "");
		knowledge.put(plum, "");
		knowledge.put(green, "");
		knowledge.put(peacock, "");
		knowledge.put(scarlet, "");
		knowledge.put(white, "");
		knowledge.put(knife, "");
		knowledge.put(candleStick, "");
		knowledge.put(pistol, "");
		knowledge.put(poison, "");
		knowledge.put(trophy, "");
		knowledge.put(rope, "");
		knowledge.put(bat, "");
		knowledge.put(ax, "");
		knowledge.put(dumbbell, "");
		knowledge.put(hall, "");
		knowledge.put(diningRoom, "");
		knowledge.put(kitchen, "");
		knowledge.put(patio, "");
		knowledge.put(observatory, "");
		knowledge.put(theater, "");
		knowledge.put(livingRoom, "");
		knowledge.put(spa, "");
		knowledge.put(guestHouse, "");
		
		personElement[0] = new Card(mustard, person);
		personElement[1] = new Card(plum, person); 
		personElement[2] = new Card(green, person); 
		personElement[3] = new Card(peacock, person);
		personElement[4] = new Card(scarlet, person);
		personElement[5] = new Card(white, person);
		
		weaponElement[0] = new Card(knife, weapon);
		weaponElement[1] = new Card(candleStick, weapon);
		weaponElement[2] = new Card(pistol, weapon);
		weaponElement[3] = new Card(poison, weapon);
		weaponElement[4] = new Card(trophy, weapon);
		weaponElement[5] = new Card(rope, weapon);
		weaponElement[6] = new Card(bat, weapon);
		weaponElement[7] = new Card(ax, weapon);
		weaponElement[8] = new Card(dumbbell, weapon);
		
		roomElement[0] = new Card(spa, room);
		roomElement[1] = new Card(hall, room);
		roomElement[2] = new Card(diningRoom, room);
		roomElement[3] = new Card(kitchen, room);
		roomElement[4] = new Card(patio, room);
		roomElement[5] = new Card(observatory, room);
		roomElement[6] = new Card(theater, room);
		roomElement[7] = new Card(livingRoom, room);
		roomElement[8] = new Card(guestHouse, room);
	}
	
	
	/**
	 * Purpose:
	 * 		This method being called represents a players rumor being answered.
	 * @param c
	 * 		The card being answered with
	 * @param p
	 * 		The player answering the rumor
	 * @return
	 * 		Returns true or false depending if the answer to the murder is known
	 */		
	public boolean answerToRumor(Card c, Player p)
	{
		knowledge.put(c.getName(), p.playerName);
		//check if there is only one type left
		oneTypeLeft(c.getType());
		
		//System.out.println(this.playerName + " discovered that " + p.playerName + " holds " + c.getName());
		if(answer.size() == 3)
			return true;
		else
			return false;
	}
	
	
	/**
	 * 
	 * @author: Raffi
	 * @Purpose:
	 *		To see if the player knows where all the cards of that type are but one. (process of elimination) 
	 * @param type
	 * 		the type of card we want to see if it is part of the murder
	 */
	public void oneTypeLeft(String type)
	{
		Card[] temp;
		int heldBySomeone = 0;
		int index = -1;
		String owner = "";
		
		// answer already holds a card of this type
		// no need to continue
		if(answerContains(type)!= null)
			return;
		
		// points temp at the right array
		if(type.equalsIgnoreCase(person))
			temp = personElement;
		else if(type.equalsIgnoreCase(room))
			temp = roomElement;
		else
			temp = weaponElement;
		
		// finds how many cards of that type we know where they are
		for(int x = 0; x < temp.length; x++)
		{
			owner = knowledge.get(temp[x].getName());
			if(!owner.equalsIgnoreCase(""))
			{
				heldBySomeone++;
			}
			else if(owner.equalsIgnoreCase(""))
			{
				index = x;
			}
		}
		
		if(heldBySomeone == temp.length -1 && index != -1)
		{
			knowledge.put(temp[index].getName(), murderer);
			if(!answer.contains(temp[index]))
			{
				answer.add(temp[index]);
				//System.out.println(this.playerName + " learnt that " + temp[index] + " is part of the murder");
			}
		}
	}
	
	
	/**
	 * Purpose:
	 * 		This method is only called when the rumor has made it all around the table.
	 * 		No one could answer the rumor.
	 * @param c
	 * 		The card being answered
	 * @param p
	 * 		The player who asked the rumor
	 * @param madeItAllAround
	 * 		This parameter exists so the method could be overloaded
	 * @return
	 */
 	public boolean answerToRumor(Card c)
	{
		
		if(!cardsGiven.contains(c))
		{
			knowledge.put(c.getName(), murderer);
			if(!answer.contains(c))
				answer.add(c);
		}
		
		if(answer.size() == 3)
			return true;
		else
			return false;
	}
	
 	
	public ArrayList<Card> getAnswer()
	{
		return answer;
	}
	
	
	/**
	 * If the answer is of size 3 then the answer is known
	 * @return
	 */
	public boolean isAnswerKnown()
	{
		return answer.size()==3;
	}
	
	
	/**
	 * Purpose:
	 * 		Checks if this player contains any cards asked in the rumor
	 * @param rumor
	 * 		The rumor going around to all the players
	 * @return
	 * 		True or false depending on if the player holds the card
	 */
	public Card Contains(Card[] rumor)
	{
		for(Card c : rumor)
		{						
			if(cardsGiven.contains(c))
			{
				return c;
			}
		}
		return null;
	}
	
	
	/**
	 * This constructor is only for the murderer. He/She is a special type of player
	 * @param nme
	 * 		The name of the murderer. which is set by the constant in the Elements interface
	 * @param murderer
	 * 		The answer to the murder
	 */
	public Player(String nme, ArrayList<Card> murderer)
	{
		playerName = nme;
		answer = murderer;
	}
	
	
	/**
	 * Purpose: 
	 * 		Adds a card to the players hand. Represented by the cardsGiven list.
	 * @param c
	 * 		The card added
	 */
	public void addCard(Card c)
	{
		cardsGiven.add(c);
		knowledge.put(c.getName(), playerName);
	}
	
	
	/**
	 * Purpose:
	 * 		When it is the players turn, He/She must make a rumor.
	 * 		It is set right now so that no player will make a rumor about a card if it is known where that card is.
	 * @return
	 * 		The rumor that is created by the player.
	 */
	public Card[] makeRumor()
	{
		Random rand = new Random();
		Card[] rumor = new Card[3];
		int index = -1;		
		Card temp = null;
		numberOfRumorsMade++;
		
		/*
		 * The loops below all have the same idea.
		 * Find a card that you know nothing about and put it in the rumor.
		 */
		
		//find a person to put in the rumor
		temp = answerContains(person);		
		while(temp == null)
		{
			index = rand.nextInt(6);			
			temp = personElement[index];
			
			if(!knowledge.get(temp.name).equalsIgnoreCase(""))
			{
				temp = null; 
			}			
		}
		rumor[0] = temp;

		//finds a room to put in the rumor
		temp = answerContains(room);		
		while(temp == null)
		{
			index = rand.nextInt(9);			
			temp = roomElement[index];
			
			if(!knowledge.get(temp.name).equalsIgnoreCase(""))
			{
				temp = null; 
			}
		}
		rumor[1] = temp;								
		
		//finds a weapon to put in the rumor
		temp = answerContains(weapon);		
		while(temp == null)
		{
			index = rand.nextInt(9);			
			temp = weaponElement[index];
			
			if(!knowledge.get(temp.name).equalsIgnoreCase(""))
			{
				temp = null; 
			}
		}				
		
		rumor[2] = temp;		
		return rumor;		
	}
	
	
	public int getNumberOfRumorsMade()
	{
		return numberOfRumorsMade;
	}
	
	
	/**
	 * Purpose:
	 * 		To see if the player has the type of card in his/her answer.
	 * 		The answer holds a person,weapon and room.
	 * @param type
	 * 		The type of card that we want to see if the player has in the answer
	 * @return
	 * 		The card if it matches the type asked for
	 */
 	public Card answerContains(String type)
	{
		for(Card c: answer)
		{
			if(c.type.equalsIgnoreCase(type))
				return c;
		}
		
		return null;
	}
	
 	
	/**
	 * Purpose:
	 * 		To print what each player is holding
	 * 		For testing
	 */
	public String toString()
	{
		String message;
		message = "Name: " + playerName + "\n";
		if(cardsGiven==null)
			return "";
		message += cardsGiven.toString();
		
		return message;
		
	}
	
	
	/**
	 * 
	 * @author: Raffi
	 * @Purpose:
	 *		A player anaylises all turns that have happened and sees if anything can be duduced
	 */
	public void anayliseAllRounds()
	{
		boolean responce = false;
		
		while(!responce) //while responce is false
		{
			for(Turn t: round)
			{
				responce = learnFromOtherPlayersTurns(t);
				if(responce)
				{
					responce = false;
					break;
				}
			}
			responce = true;
		}
	}
	
	
	/**
	 * 
	 * @author: Raffi
	 * @Purpose:
	 *		This method anaylises a single turn and sees if anything can be determined.
	 *		If something new is found then the process must be restarted as new information
	 *		could be unlocked.
	 * @param t
	 * 		The turn to be anaylised
	 * @return
	 * 		true if something new is found. False otherwise
	 */	
	public boolean learnFromOtherPlayersTurns(Turn t)
	{
		Card[] rumor = t.getRumor();
		Player answer = t.getAnsweredBy();
		String currentName = "";
		int indexOfUnkown = 0;
		ArrayList<String> ownersOfCards = new ArrayList<String>();
		int numberOfUnknown = 0;
		
		for(int x = 0; x <= 2; x++)
		{
			currentName = knowledge.get(rumor[x].getName());
			if(!ownersOfCards.contains(currentName))
					ownersOfCards.add(currentName);
			
			if(currentName.equalsIgnoreCase(""))
			{
				numberOfUnknown++;
				indexOfUnkown = x;
			}
		}
		
		// if two of the three cards are known and a different person is involved for every card
		if(numberOfUnknown == 1 && ownersOfCards.size()==3 && !ownersOfCards.contains(answer.playerName))
		{
			knowledge.put(rumor[indexOfUnkown].getName(), answer.playerName);
			oneTypeLeft(rumor[indexOfUnkown].getType());
			return true;
		}
		// if two of the three cards belong to the same person and the unknown card belongs to another person
		else if(numberOfUnknown == 1 && ownersOfCards.size()==2 && !ownersOfCards.contains(answer.playerName))
		{
			knowledge.put(rumor[indexOfUnkown].getName(), answer.playerName);
			oneTypeLeft(rumor[indexOfUnkown].getType());
			return true;
		}
		return false;
	}
	
	//----------Static Methods--------
	/**
	 * Purpose:
	 * 		Adds the turn that just happened to the turn list
	 * @param t
	 */
	public static void addTurn(Turn t)
	{
		round.add(t);
	}
	
	
	/**
	 * Purpose: 
	 * 		Prints out all the turns that has happened
	 */
	public static void printAllTurns()
	{
		for(Turn t: round)
		{
			System.out.println(t.toString());
		}
	}
	
	
	/**
	 * Purpose: 
	 * 		Prints out the last turn that has happened
	 */
	public static void printLastTurn()
	{
		System.out.println(round.get(round.size()-1).toString());
		
	}
	
	
	/**
	 * Purpose:
	 * 		Prints out the list of players and what they hold.
	 * 		This was created for testing mainly.
	 */
	public static void printPlayerList()
	{
		for(Player p: playerList)			
			System.out.println(p.toString());				
	}
	
	
	/**
	 * Purpose:
	 * 		Prints out what each player knows about the murder.
	 * 		This was meant for testing.
	 */
	public static void printWhatPeopleKnow()
	{
		for(Player p: playerList)
		{
			if(!p.getAnswer().isEmpty() && !p.playerName.equalsIgnoreCase(murderer))
			System.out.println(p.playerName + " knows " + p.getAnswer().toString() +
					" is part of the murder ");
		}
	}
}
