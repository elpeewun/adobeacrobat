import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class chatbot
{
	Map<String,String>nouns=new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
	Map<String,String>welcome=new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
	Map<String,String>farewell=new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);

	boolean exit=false;
		
	public chatbot()
	{
		nouns.put("money", "Where??  finance ");
		nouns.put("finance", "Many firms provide loan options    invest : bank  : shares  :  loan  ");
		nouns.put("invest", "Yes,Offcourse.Basically there are many options to invest.Regional Or Investment Banks In which section would you like to invest?");
		nouns.put("bank", "Yes,Offcourse.Basically there are many options to invest.Regional Or Investment Banks In which section would you like to invest?");
		nouns.put("shares", "Which company Shares? R or I");
		nouns.put("loan", "Which loan?? Housing ,Personal,Educational.I recommend to visit SBI banks for this");
		nouns.put("investment","Well there are many such as UBS,Barclays,Deutsche bank,HSBC,Wells Fargo");
		nouns.put("regional", "There are many SBI,IDBI,KotaK Mahindra \n SBI offers 10% etc");
			
		welcome.put("hii","Welcome! How can I help you??  money?"  );
		welcome.put("hey","hey,how I can help you?money?");
		welcome.put("hello","hello,How can I help you??");
		welcome.put("thanks", "Welcome");
		
		farewell.put("bye","bye");
		farewell.put("bbye","bbye");
	}
	
	public static void main(String[] args)
	{
		System.out.println("hey lets start");
		Scanner s=new Scanner(System.in);
		chatbot c=new  chatbot();
		
		while(true)
		{
			String input=s.nextLine();	
			String output=c.giveans(input);
			System.out.println(output);
			if(c.exit)
			{
				break;
			}
		}
		s.close();
	}
	public String giveans(String input)
	{
		//keywords=new ArrayList<String>();	
		String tokens[]=input.split("\\s");
		for(int i=0;i<tokens.length;i++)
		{
			if(welcome.containsKey(tokens[i].toLowerCase()))
			{
				return welcome.get(tokens[i]);		
			}
			else if(farewell.containsKey(tokens[i].toLowerCase()))
			{
				return farewell.get(tokens[i]);
			}
			else if(nouns.containsKey(tokens[i].toLowerCase()))
			{
				return nouns.get(tokens[i]);
			}
		}
		return ("I am sorry. I don't get this.");		
	}
}
