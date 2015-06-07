import java.util.Scanner;

/**
 * 
 * @Author: raffi
 * @Date:Jun 3, 2015
 * @Purpose:
 *			To test the encription class. This program will continue to ask 
 *			the user if he/she wishes to encript of decript a file until the 
 *			user ends the program.
 */
public class run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Encript safe = new Encript();		
		Scanner commandScan = new Scanner(System.in);
		int command = -1; // the users answer
		
		//loop until zero is entered
		do
		{
			printCommands();
			command = commandScan.nextInt();
			
			if(command == 1)
			{
				encriptProtical(safe);
			}
			else if(command == 2)
			{
				decriptProtical(safe);
			}
			else if(command != 0)
			{
				System.out.println("Enter a valid intpu please");
			}
			
		}while(command != 0);
		System.out.println("Done. Thanks for being safe");
					
	}
	
	/**
	 * 
	 * @Purpose:
	 *			Print the commands the user can enter
	 */
	public static void printCommands()
	{
		System.out.println("Enter a command");
		System.out.println("0 - to terminate program");
		System.out.println("1 - to encript text file");
		System.out.println("2 - to decript text file");	
	}
	
	/**
	 * 
	 * @Purpose:
	 *		The user chose to encript a file	
	 * @param en
	 * 		The encription object
	 */		
	public static void encriptProtical(Encript en)
	{
		Scanner scan = new Scanner(System.in);
		if(en.getStatus())
			System.out.println("The program is ready to encript a text file");		
		else
		{
			System.out.println("Something went wrong. Program terminated");
			System.exit(0);
		}
		
		System.out.println("\nEnter the name of the file you would like to "
				+ "encript");		
		System.out.println("The new file will be named the same but with the"
				+ " word 'encripted at the end");
		
		if(en.encriptFile(scan.nextLine()))
			System.out.println("The encripted file was created");
	}
	
	/**
	 * 
	 * @Purpose:
	 *		The user chose to decript a file	
	 * @param en
	 * 		The encription object
	 */
	public static void decriptProtical(Encript en)
	{
		Scanner scan = new Scanner(System.in);
		if(en.getStatus())
			System.out.println("The program is ready to decript a text file");		
		else
		{
			System.out.println("Something went wrong. Program terminated");
			System.exit(0);
		}
		
		System.out.println("\nEnter the name of the file you would like to "
				+ "decript");		
		System.out.println("The new file will be named the same but with the"
				+ " word 'decripted at the end");
		
		if(en.unEncriptFile(scan.nextLine()))
			System.out.println("The decripted file was created");
	
	}

}
