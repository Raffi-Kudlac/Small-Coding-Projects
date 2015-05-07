import java.text.DecimalFormat;
import java.util.ArrayList;
/**
 * 
 * @Author: Raffi Kudlac
 * @Date: May 5, 2015
 * @Purpose:
 *		To race prime number algorithms against each other
 */

public class prime {

	static DecimalFormat df = new DecimalFormat("#,##0.00");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// index 0 holds size of array
		// index 1 holds number of iterations
		// index 2 holds time in millicesonds to complete
		// index 3 hold number of times the algorithm is to be executed. Set to 1
		double primeData[][] = new double[5][4];
		
		long amount = 70000000;	
		
		primeData[0][3] = 1;		
		method1(amount,0,primeData);
		toString(primeData,0);
				
		primeData[1][3] = 1; 	
		method2(amount,1,primeData);
		toString(primeData,1);
		
//		primeData[2][3] =1;
//		method3(amount,2,primeData);
//		toString(primeData,2);
		
		primeData[3][3] = 1;
		method4(amount,3,primeData);
		toString(primeData,3);
		
		
		primeData[4][3] =1;
		method5(amount,4,primeData);
		toString(primeData,4);
		
		compareItterations(primeData,3,4);		
	}
	
	/**
	 * 
	 * @Purpose:
	 *		The first algorithm. Stores all the known primes below the number
	 *		and sees if the number is divisible by any of the numbers in the 
	 *		list. If not then the number is prime.
	 * @param amount: 
	 * 			find all prime numbers up until this number
	 * @param index:
	 * 			The position this algorithm is in the 2D array
	 * @param pd
	 * 			The information that will be recorded. See main method
	 * 			for details
	 */
	public static void method1(long amount, int index, double[][] pd)
	{
		long startTime = System.currentTimeMillis();		
		long endTime = 0;
		pd[index][1] = 0;
		
		for(int it = 0; it < pd[index][3]; it++)
		{
		
			ArrayList<Integer> list = new ArrayList<Integer>(6000000);
			boolean pass = false;			
			long sqrt = 0;
			long current;
			
			list.add(2);		
			
			for(int x = 3; x<= amount; x +=2)
			{
				pd[index][1]++;
				pass = true;
				sqrt = (long) Math.sqrt(x);
				
				
				for(int k = 1; k < list.size(); k++)
				{
					pd[index][1]++;
					current = list.get(k);
					if (x % current == 0 ) 
					{
						pass = false;
						break;
					}	
					else if(sqrt < current)
					{
						pass = true;
						break;
					}
				}
				
				if(pass)
				{				
					list.add(x);
				}
				
			}
			pd[index][0] = list.size();
		}		
		endTime = System.currentTimeMillis();
		pd[index][2] = (endTime - startTime)/1000.0;
	}
	
	
	/**
	 * 
	 * @Purpose:
	 *		The second algorithm. All primes are in the form of 6k +- 1. This
	 *		algorithm finds all the primes up until the amount param using 
	 *		this formula.
	 *
	 * @param amount:
	 * 		The number to stop finding primes at
	 * @param index
	 * 		The position this algorithm is in the 2D array
	 * @param pd
	 * 		The information that will be recorded. See main method for details
	 */			
	public static void method2(long amount, int index, double[][] pd)
	{
		// all primes are in the form 6k +- 1 
		
		long startTime = System.currentTimeMillis();		
		long endTime = 0;
		pd[index][1] = 0;
		for(int it =0; it <pd[index][3]; it++)
		{
			ArrayList<Integer> list = new ArrayList<Integer>(6000000);
			boolean pass = true;
			int value = 0;			
			long sqrtValue = 0;
			long current;
			
			list.add(2);
			list.add(3);
			
			for(int x = 1; x <= amount/6; x++)
			{				
				value = 6*x - 1;
				pass = true;
				sqrtValue = (long) Math.sqrt(value);
				
				for(int k = 2; k < list.size(); k++)
				{
					pd[index][1]++;
					current = list.get(k);
					if (value % current == 0)
					{
					//	System.out.println("failed on" + k );
						pass = false;
						break;
					}
					else if(current > sqrtValue)
					{
						pass = true;
						break;
					}
						
				}						
				
				if(pass)
				{				
					list.add(value);				
				}
				
				
				value = 6*x + 1;
				pass = true;
				sqrtValue = (long) Math.sqrt(value); 
						
				for(int h = 2; h< list.size(); h++)
				{
					pd[index][1]++;
					current = list.get(h);
					if (value % current == 0)
					{					
						pass = false;
						break;
					}
					else if(current > sqrtValue)
					{
						pass = true;
						break;
					}
				}
				
				if(pass)
				{				
					list.add(value);				
				}
			}
			pd[index][0] = list.size();
		}
		
		endTime = System.currentTimeMillis();
		pd[index][2] = (endTime - startTime)/1000.0;
		
	}
	
	
	/**
	 * 
	 * @Purpose:
	 *		The third algorithm. Makes a list of all the numbers and then wipes
	 *		out all multiples of 2,3 etc... the remaining numbers are prime 
	 * @param amount:
	 * 			Find all the primes up until this number
	 * @param index
	 * 			The position this algorithm is in the 2D array
	 * @param pd
	 * 			The information that will be recorded. See main method
	 * 			for details
	 */
	public static void method3(long amount, int index, double[][] pd)
	{
		long startTime = System.currentTimeMillis();		
		long endTime = 0;
		
		pd[index][1] = 0;
		
		for(int it = 0; it < pd[index][3]; it++)
		{
			ArrayList<Integer> all = new ArrayList<Integer>(6000000);			
			
			int pos = 0; //always points to the next prime				
			
			for(int x = 2; x<=amount; x++)
			{				
				all.add(x);	
				pd[index][1]++;
			}			
			
			while(pos < all.size())
			{
				long jump = all.get(pos);
				
				for(long x = pos+jump; x< all.size();x+=jump)
				{
					pd[index][1]++;
					if(x>all.size())
						break;
					all.set((int)x, 0);
				}				
				
				do
				{
					pd[index][1]++;
					pos++;
				}while( pos < all.size() && all.get(pos)==0);

			}
			
			long counter = 0;
			for(int j = 0; j < all.size();j++)
			{
				if(all.get(j) != 0)
					counter++;
			}
			
			pd[index][0] = counter;
		}
		
		endTime = System.currentTimeMillis();
		pd[index][2] = (endTime - startTime)/1000.0;
	}
	
	
	/**
	 * 
	 * @Purpose:
	 * 		The fourth algorithm. Uses the Sieve_of_Sundaram algorithm.
	 * 		can be viewed here. http://en.wikipedia.org/wiki/Sieve_of_Sundaram
	 *		
	 * @param amount:
	 * 			Find all the primes up until this number
	 * @param index
	 * 			The position this algorithm is in the 2D array
	 * @param pd
	 * 			The information that will be recorded. See main method
	 * 			for details
	 */
	public static void method4(long amount, int index, double[][] pd)
	{
		long startTime = System.currentTimeMillis();		
		long endTime = 0;
		pd[index][1] = 0;		
		amount = (amount/2) -1;
		
		for(int it = 0; it < pd[index][3]; it++)
		{
			ArrayList<Integer> list = new ArrayList<Integer>(6000000);
			long value = 0;			
			
			//fills list with numbers
			for(int x = 1;x <= amount; x++)
			{
				pd[index][1]++;
				list.add(x);
			}
			
			for(int i = 1; i < list.size(); i++)
			{
				for(int j = i;j < list.size(); j++)
				{	
										
					pd[index][1]++;
					value = method4Formula(i,j);						
					if( value > amount)
					{						
						break;
					}
					else
					{				
						list.set((int)(value-1), 0);						
					}											
				}				
			}
			
			long counter = 0;
			for(int w =0; w< list.size(); w++)
			{				
				pd[index][1]++;
				if(list.get(w) != 0)
				{
					counter++;
					list.set(w, list.get(w)*2 + 1);					
				}								
			}			
			
			list.add(2);
			pd[index][0] = counter+1;				
		}
		
		endTime = System.currentTimeMillis();
		pd[index][2] = (endTime - startTime)/1000.0;		
	}
	
	/**
	 * 
	 * @Purpose:
	 *		The 5th algorithm. The sieve of atkins.
	 *		the algorithm can be seen here.
	 *		http://en.wikipedia.org/wiki/Sieve_of_Atkin
	 * @param amount:
	 * 			Find all the primes up until this number
	 * @param index
	 * 			The position this algorithm is in the 2D array
	 * @param pd
	 * 			The information that will be recorded. See main method
	 * 			for details
	 */
	public static void method5(long amount, int index, double[][] pd)
	{
		
		long startTime = System.currentTimeMillis();		
		long endTime = 0;
		pd[index][1] = 0;
		
		//will hold the prime numbers
		ArrayList<Integer> primes = new ArrayList<Integer>();
		//each index maps to a real number, if true then the index is prime
		ArrayList<Boolean> sieve = new ArrayList<Boolean>();
		
		//initalize sieve list to all false
		for(int x = 0; x < amount; x++ )
		{
			pd[index][1]++;
			sieve.add(false);
		}
		
		primes.add(2);
		primes.add(3);		
		
		int limit = (int) Math.ceil(Math.sqrt(amount));
		
		long n = 0;
		
		for(int x = 1; x <= limit; x++)
		{
			for(int y = 1; y <= limit; y++)
			{
				pd[index][1]++;
				
				n = (4*x*x) + (y*y);
				if(n <= amount && (n % 12 == 1 || n % 12 == 5))
				{
					sieve.set((int)n, true);
				}
				
				n = (3*x*x) + (y*y);
				if(n <= amount && (n % 12 == 7 ))
				{
					sieve.set((int) n, true);
				}
				
				n = (3*x*x) - (y*y);
				if((n <= amount) && (x > y) && (n % 12 == 11))
				{
					sieve.set((int) n , true);
				}				
			}			
		}
		
		//finds a prime and loops through all multiples turning them false
		//can be refactored into above loop
		for(int r = 5; r <= limit; r++)
		{
			if(sieve.get(r))
			{
				for(int k = r*r; k < amount; k += r)
				{
					pd[index][1]++;
					sieve.set(k, false);
				}
			}
		}
		
		//adds prime to prime list
		// can also be refactored into first loop
		for(int h = 5; h < amount; h++)
		{
			if(sieve.get(h))
			{
				pd[index][1]++;
				primes.add(h);				
			}
		}
		
		pd[index][0] = primes.size();		
		endTime = System.currentTimeMillis();
		pd[index][2] = (endTime - startTime)/1000.0;		
	}
	
	
	/**
	 * 
	 * @Purpose:
	 *		The formula used by the 4th algorithm
	 * @param i
	 * 		A number somewhere in the range of 1 and the amount  
	 * @param j
	 * 		A number somewhere in the range of 1 and i
	 * @return
	 * 		The value calculated by the formula
	 */
	public static long method4Formula(long i, long j)
	{
		return (i + j + 2*i*j);
	}
	
	
	/**
	 * 
	 * @Purpose:
	 *		To compare data collected from two different algorithms
	 * @param pd
	 * 		The data structure where the data is stored
	 * @param index1
	 * 			The index of one of the algorithms
	 * @param index2
	 * 			The index of another algorithms
	 */
	public static void compareItterations(double[][] pd, int index1, int index2)
	{
		int diff = (int) Math.abs((pd[index1][1] - pd[index2][1]));
		if(pd[index1][1] > pd[index2][1])
		{
			System.out.println("Method " + (index1+1) + " had " + df.format(diff)
					+ " more itterations than method " + (index2+1));
		}
		else if( pd[index1][1] < pd[index2][1])
		{
			System.out.println("Method " + (index2+1) + " had " + df.format(diff)
					+ " more itterations than method " + (index1+1));
		}
		else
			System.out.println("Both Methods had the same number of itterations");
		
		System.out.println();
	}
	
	/**
	 * 
	 * @Purpose:
	 * 		Prints all the data of one of the algorithms
	 *		
	 * @param pd
	 * 		The data structure that holds the data
	 * @param index
	 * 		The index of an algorithms
	 */
	public static void toString(double[][] pd, int index)
	{		
		System.out.println("Method " + (index+1));
		System.out.println( "\tTook " + pd[index][2]
				+ " seconds to complete");
		System.out.println("\tFinished with " + df.format(pd[index][0]) + " primes found");
		System.out.println("\tLooped " + pd[index][3] + " time(s)");
		System.out.println("\tMade " + df.format((long)pd[index][1]) + " itterations \n");
	}
}
