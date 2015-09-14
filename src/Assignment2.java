import java.text.DecimalFormat;
import java.util.*;


public class Assignment2
{

   private static final String CHERRIES = "cherries";
   private static final String BAR = "BAR";
   private static final String SPACE = "space";
   private static final String SEVEN = "7";
   
   private static Scanner keyboard = new Scanner(System.in);
   
   public static void main(String[] args)
   {
      
      TripleString pullString = null;
      int bet, multiplier, winnings, totalWinnings = 0;
      
      do
      {
         
         bet = getBet();
         if(bet == 0)
            break;
         
         pullString = pull();
         multiplier = getPayMultiplier(pullString);
         winnings = bet * multiplier;
         totalWinnings += winnings;
         TripleString.saveWinnings(winnings);
         display(pullString, winnings);
         
      } while (bet != 0);
      
      System.out.println("\nThanks for playing at the Casino!");
      System.out.println("Your individual winnings were:");
      System.out.println(TripleString.displayWinnings());
      System.out.println("Your total winnings were: $" + totalWinnings);    
      keyboard.close();
            
   }
   
   public static int getBet()
   {
      
      final int MIN_BET = 0;
      final int MAX_BET = 100;
      
      int bet = -1;
      
      while( !(bet >= MIN_BET && bet <= MAX_BET) )
      {
         
         System.out.println("How much would you like to bet (1 - 100) or "
               + "0 to quit?");
         bet = keyboard.nextInt();
         keyboard.nextLine();

      }
            
      return bet;
      
   }
   
   public static TripleString pull()
   {
      
      TripleString tripleString = new TripleString();
      
      tripleString.setString1(randString());
      tripleString.setString2(randString());
      tripleString.setString3(randString());
            
      return tripleString;
      
   }
   
   public static void display (TripleString thePull, int winnings )
   {
      
      System.out.println("whirrrrrr .... and your pull is ...");
      System.out.println(thePull.toString());
      
      if(winnings == 0)
         System.out.println("sorry, you lose.");
      else
         System.out.println("congratulations, you win:" + winnings);
      
      System.out.println("");
      
   }
   
   private static int getPayMultiplier(TripleString thePull)
   {
      
      String string1, string2, string3;
      
      string1 = thePull.getString1();
      string2 = thePull.getString2();
      string3 = thePull.getString3();
      
      if( string1.equals(CHERRIES) && !string2.equals(CHERRIES) )
         return 5;
      else if( string1.equals(CHERRIES) && 
            string2.equals(CHERRIES) && !string3.equals(CHERRIES) )
         return 15;
      else if( string1.equals(CHERRIES) && 
            string2.equals(CHERRIES) && string3.equals(CHERRIES) )
         return 30;
      else if( string1.equals(BAR) && 
            string2.equals(BAR) && string3.equals(BAR) )
         return 50;
      else if( string1.equals(SEVEN) && 
            string2.equals(SEVEN) && string3.equals(SEVEN) )
         return 100;
      else
         return 0;
      
   }
   
   private static String randString()
   {
      
      Random randomGenerator = new Random();
      double randomDb = randomGenerator.nextDouble() * 100;
            
      if( randomDb > 50.0 && randomDb <= 75.0 )
         return CHERRIES;
      else if( randomDb > 75.0 && randomDb <= 87.5)
         return SPACE;
      else if( randomDb > 87.5)
         return SEVEN;
      else
         return BAR;
     
   }

}


/*
 * TripleString class*
 */
class TripleString
{
   
   public static final int MAX_LEN = 20;
   public static final int MAX_PULLS = 40;
   
   private static int[] pullWinnings =new int[MAX_PULLS];
   private static int numPulls = 0;
         
   private String string1;
   private String string2;
   private String string3;
   
   public TripleString()
   {
      
      this.string1 = "";
      this.string2 = "";
      this.string3 = "";
      
   }
   
   private boolean validString( String str )
   {
      
      if(str == null || str.length() > MAX_LEN)
         return false;
      
      return true;
      
   }
   
   public static void saveWinnings(int winnings)
   {
      
      if(numPulls < MAX_PULLS)
      {
         pullWinnings[numPulls] = winnings;
         numPulls++;
      }
      
   }
   
   public static String displayWinnings()
   {
      
      String total = "";
      
      for(int i = 0; i < numPulls; i++)
      {
         total = (i == 0) ? total + pullWinnings[i] : total + 
               " " + pullWinnings[i];
      }
         
      return total;
      
   }
   
   public String toString() 
   {
      return string1 + " | " + string2 + " | " + string3;
   }
   
   /*getter functions*/
   public String getString1()
   {
       return string1;
   }
   
   public String getString2()
   {
       return string2;
   }
   
   public String getString3()
   {
       return string3;
   }
   
   /*setter functions*/
   public boolean setString1(String str)
   {
      
      if(validString( str ))
      {
         string1 = str;
         return true;
      }
      
      return false;
      
   }
   
   public boolean setString2(String str)
   {
      
      if(validString( str ))
      {
         string2 = str;
         return true;
      }
      
      return false;
      
   }
   
   public boolean setString3(String str)
   {
      
      if(validString( str ))
      {
         string3 = str;
         return true;
      }
      
      return false;
      
   }

}