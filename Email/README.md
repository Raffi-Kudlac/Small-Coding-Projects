---Personal Email---

This program is writen to be used with other programs.
The idea is that when the other program crashes it will send an email 
to some one informing them of the crash. 

All that is needed is the email destination (email address), the sender (email address) and the password of the sender.
This program will only work with sending emails from gmail accounts.

See Test Email class for an example (or look below) or look at the email class to see how it works. 

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
		