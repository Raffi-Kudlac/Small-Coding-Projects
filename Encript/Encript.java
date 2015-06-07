
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * 
 * @Author: raffi
 * @Date:Jun 2, 2015
 * @Purpose: 
 * 		To have a program that can encript basic text files
 * @algorithm: 
 * 		The program depends upon a key file. Every Character is mapped
 * 		to another character and this mapping is stored in the key file.
 * 		Without it the program can not operate. If there is no key file then
 * 		one is made. If the file is lost the all encripted files will be 
 * 		lost. 
 *
 */
public class Encript {

	char[] ascii; //the array that holds the mapping. The integer index
				//represents the ascii character of that value. 
	
	boolean goodToGo = false; // a boolean value telling the user the status
					// of the encription
				
	
	public Encript()
	{
		ascii = new char[256];
		readEncriptionFile();
	}
	
	public boolean getStatus()
	{
		return goodToGo;
	}
	
	/**
	 * 
	 * @Purpose:
	 *		Reads the key file and stores it in the ascii array.
	 *		This array will be used to encript and decript all files. 
	 */
	public void readEncriptionFile()
	{
		File key = new File("key.txt");
		FileInputStream  input;
		//value to be read from file and the location to store it in the array
		int value = 0, counter = 0;
		try 
		{
			//making connection to key file
			 input = new FileInputStream(key);			 
			 while((value = input.read()) != -1)
			 {
				ascii[counter] = (char)value; 
				counter++;
			 }
			 
			 input.close();
			 goodToGo = true; //reading key worked, can read private file now
		} 
		//first time program is being used. Need to make key file.
		catch (FileNotFoundException e) 
		{	
			writeEncriptionFile();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @Purpose:
	 *		This method is called the first time the program is ran and
	 *		there is no key file. A new key file will be written containing
	 *		the mapping of characters to others. The fist character in the
	 *		file will map to index 0, 2nd character to index 1 and so forth.
	 */
	public void writeEncriptionFile()
	{
		//these variables will be used to shuffle the array
		char temp;
		int randomNumberOne = 0, randomNumberTwo = 0;
		
		//initalizing the ascii array. At first, the array is the ASCII table.
		for(int x = 0; x < 256; x++)
		{
			ascii[x] = (char)x;
		}
		
		//shuffles the ascii array
		for(int z = 0; z < 256*4; z++)
		{
			randomNumberOne = (int)(Math.random()*ascii.length);
			randomNumberTwo = (int)(Math.random()*ascii.length);
			
			temp = ascii[randomNumberOne];
			ascii[randomNumberOne] = ascii[randomNumberTwo];
			ascii[randomNumberTwo] = temp;			
		}
		
		//making key file
		File key = new File("key.txt");		
		FileOutputStream out;
		try {
			key.createNewFile();
			 out = new FileOutputStream(key);
			 for(int x = 0; x < ascii.length; x++)
			 {
				 out.write(ascii[x]);
			 }
			 out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @Purpose:
	 *		To unencript a file encripted by this program
	 * @param name
	 * 		The file name to be unecripted 
	 * @return
	 * 		True if successful. False otherwise
	 */		
	public boolean unEncriptFile(String name)
	{
		File encriptedFile = new File(name);
		//the unencripted file to be created 
		File unencriptedFile = new File(name.replaceFirst("encripted", "decripted"));
		/*possition is just for styling so the encripted file is not one long
		line*/
		int readValue, possition = 2;		
		
		FileInputStream in;
		FileOutputStream out;
		
		try 
		{
			//making file connections
			in = new FileInputStream(encriptedFile);
			unencriptedFile.delete();
			unencriptedFile.createNewFile();
			out = new FileOutputStream(unencriptedFile);
			
			while((readValue = in.read()) != -1)
			{				
				if(possition%21 == 0)
				{
				//do nothing ( on purpose )	
					
				}
				else
				{
				/*a character is read. A linear search must be done to find
				what character the encripted character maps to */
					for(int y = 0; y < ascii.length; y++)
					{
						if(ascii[y] == readValue)
						{
							out.write((char)y);
							break; //match is found, can stop searching
						}
					}	
				}
				possition++;
			}
			in.close();
			out.close();
			return true;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	 * @Purpose:
	 *		To encript a file using the generated key file.
	 * @param name
	 * 		The name of the file to be encripted
	 * @return
	 * 		True if successful. False otherwise.
	 */		
	public boolean encriptFile(String name)
	{
		//making file connections
		File target = new File(name);
		File writingTo = new File(name.substring(0, name.length()-4)+
				"_encripted.txt");
		FileInputStream in;
		FileOutputStream out;
		int readValue; //value read from file
		
		/* Position is used to insert new lines in the encripted file so it
		 * is not all printed on one line. This is then canceled out in the 
		 * unencript method.
		 */
		int possition = 1;
		
		try 
		{
			//file connections
			in = new FileInputStream(target);
			writingTo.delete();
			writingTo.createNewFile();
			out = new FileOutputStream(writingTo);
			
			/*read a character and write the character that the read value is
			  mapped to */
			while((readValue = in.read()) != -1)
			{
				out.write(ascii[readValue]);
				possition++;
				if(possition%20 == 0)
				{
					out.write('\n');
				}
				
			}
			
			in.close();
			out.close();
			return true;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
