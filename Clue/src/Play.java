import java.util.ArrayList;


public class Play implements Elements{
	
	// holds the list of cards that are played in the game
	static ArrayList<Card> elements = new ArrayList<Card>();
	// holds the list of people that are playing in the game
	static ArrayList<Player> participants = new ArrayList<Player>();
	
	//holds a list of players who have one as more then one player could find the answer at the same time
	static ArrayList<Player> playersWhoHaveWon = new ArrayList<Player>();
	static int numberOfPlayers = 5;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Player answer;
		makePlayers();
		makeCards();
		makeMurderer();
		shuffle();
		distributeCards();
		
		//This is so all the players can see all the other players
		Player.playerList = participants;
		Player.printPlayerList();				
		
		
		answer = startTurns();
		
		System.out.println("In " + Player.round.size() + " turns");
		for(Player p: playersWhoHaveWon)
		{
			System.out.println(p.playerName + " discovered that the answer is " + answer.getAnswer().toString() + " after starting " 
					+ p.getNumberOfRumorsMade() + " rumors");
		}
	}
	
	
	/**
	 * Starts the turn process. Won't end until an answer is found.
	 * the process is
	 * 		- make a rumor
	 * 		- ask everyone the rumor
	 * 		- see if an answer is known
	 * @return
	 */
	static Player startTurns()
	{
		boolean found = false;
		int playerCount = 0;
		Card[] rumor = null;
		
		while(found == false)
		{
			rumor = participants.get(playerCount).makeRumor();
			askAllPlayers(rumor, playerCount);
			if(Player.round.size() > 0 )
				anayliseRound();
			isAnswerKnown();
			found = !playersWhoHaveWon.isEmpty();
			if(found)
				break;
			playerCount = (playerCount + 1) % numberOfPlayers;
		}
		
		return participants.get(playerCount);
	}
	
	/**
	 * 
	 * @author: Raffi
	 * @Purpose:
	 *		Builds a list of players who know the answer to the murder
	 */
	public static void isAnswerKnown()
	{
		for(Player p: participants.subList(0, participants.size()-2))
		{
			if(p.isAnswerKnown())
			{
				playersWhoHaveWon.add(p);
			}
		}
	}
	
	/**
	 * 
	 * @author: Raffi
	 * @Purpose:
	 *		Each play anaylises the rounds that have happened and sees if anything new can be determined
	 */
	public static void anayliseRound()
	{
		for(Player p: participants.subList(0, participants.size()-2))
		{
			p.anayliseAllRounds();
		}
	}
	
	/**
	 * Asks all the players the rumor
	 * @param rumor
	 * 		- the rumor of the murder
	 * @param player
	 * 		- the player who is asking
	 */
	static void askAllPlayers(Card[] rumor, int player)
	{
		boolean found = false;
		int playerCount = (player + 1) % numberOfPlayers;
		Card responce;
		while(found == false && player != playerCount)
		{
			responce = participants.get(playerCount).Contains(rumor);
			if( responce != null)
			{
				// a query was complete and added to the query base ( a question
				// was asked and answered)
				Turn t = new Turn(rumor, participants.get(player),
						participants.get(playerCount));
				Player.addTurn(t);
				
				//information was recorded to the person who asked the question
				participants.get(player).answerToRumor(responce,
						participants.get(playerCount));
				found = true;
				break;
			}			
			playerCount = (playerCount + 1) % numberOfPlayers;
		}
		
		if(playerCount == player)
		{
			for(int x = 0; x<3;x++)
			{
				participants.get(player).answerToRumor(rumor[x]);
			}
		}
	}
	
	
	/**
	 * Distributes the cards to the players
	 */
	static void distributeCards()
	{
		int playerCounter = 0;
		int playerSize = 5;
		while (elements.size() > 0)
		{
			participants.get(playerCounter).addCard(elements.remove(0));
			playerCounter = (playerCounter + 1) % playerSize;
		}
	}
	
	
	/**
	 * Makes the players
	 */
	static void makePlayers()
	{
		participants.add(new Player("Ben"));
		participants.add(new Player("Harry"));
		participants.add(new Player("Molly"));
		participants.add(new Player("Sara"));
		participants.add(new Player("Kevin"));
	}
	
	
	/**
	 * Makes the cards
	 */
	static void makeCards()
	{
		
		elements.add(new Card(mustard, person));
		elements.add(new Card(plum, person)); 
		elements.add(new Card(green, person)); 
		elements.add(new Card(peacock, person));
		elements.add(new Card(scarlet, person));
		elements.add(new Card(white, person));
		elements.add(new Card(knife, weapon));
		elements.add(new Card(candleStick, weapon));
		elements.add(new Card(pistol, weapon));
		elements.add(new Card(poison, weapon));
		elements.add(new Card(trophy, weapon));
		elements.add(new Card(rope, weapon));
		elements.add(new Card(bat, weapon));
		elements.add(new Card(ax, weapon));
		elements.add(new Card(dumbbell, weapon));
		elements.add(new Card(spa, room));
		elements.add(new Card(hall, room));
		elements.add(new Card(diningRoom, room));
		elements.add(new Card(kitchen, room));
		elements.add(new Card(patio, room));
		elements.add(new Card(observatory, room));
		elements.add(new Card(theater, room));
		elements.add(new Card(livingRoom, room));
		elements.add(new Card(guestHouse, room));
	}
	
	
	/**
	 * Makes the murderer who is a special player that only holds 3 cards
	 * This method must happen before the cards are distributed
	 */
	static void makeMurderer()
	{
		int person;
		int weapon;
		int room;
		
		person = (int)(Math.random()*6);
		weapon = ((int)(Math.random()*9)) + 6;
		room = ((int)(Math.random()*9)) + 15;
		
		ArrayList<Card> a = new ArrayList<Card>();
		a.add(elements.get(person));
		a.add(elements.get(room));
		a.add(elements.get(weapon));
		participants.add(new Player(murderer, a));
		elements.remove(a.get(0));
		elements.remove(a.get(1));
		elements.remove(a.get(2));
	}

	
	/**
	 * Shuffles the cards before they are dealt to the players
	 */
	static void shuffle()
	{
		Card temp;
		int pos1;
		int pos2;
		for(int x = 0; x < elements.size()*4; x++)
		{
			pos1 = (int) Math.random()*elements.size();
			pos2 = (int) Math.random()*elements.size();
			temp = elements.get(pos1);
			elements.set(pos1, elements.get(pos2));
			elements.set(pos2, temp);
		}
	}
}
