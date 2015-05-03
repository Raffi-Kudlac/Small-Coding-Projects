import java.util.Scanner;


public class TestEmail {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Email em = new Email("going to","comming from","password of comming from");
		em.setSubject("The Program Broke");
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter a number to divide by");
		
		int numb = scan.nextInt();
		
		double answer = 0;
		
		try{
			answer = 5/numb;
		}
		catch(ArithmeticException e)
		{
			em.setMsgBody(e.toString(), false, 0);
			em.send();
			System.out.println("The email sent");
		}
		
		System.out.println("The answer is: " + answer);
		scan.close();
		
	}

}
