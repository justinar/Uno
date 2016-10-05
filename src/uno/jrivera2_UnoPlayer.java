
package uno;

import java.util.ArrayList;

public class jrivera2_UnoPlayer implements UnoPlayer {
    //color array in order 0-9 numbers, 10 reverse, 11 skip, 12 draw 2
    Integer[] red = new Integer[] {1,2,2,2,2,2,2,2,2,2,2,2,2};
    Integer[] green = new Integer[] {1,2,2,2,2,2,2,2,2,2,2,2,2};
    Integer[] blue = new Integer[] {1,2,2,2,2,2,2,2,2,2,2,2,2};
    Integer[] yellow = new Integer[] {1,2,2,2,2,2,2,2,2,2,2,2,2};
    //value prob
    Double[] probRed = new Double[13];
    Double[] probGreen = new Double[13];
    Double[] probBlue = new Double[13];
    Double[] probYellow = new Double[13];
    //color prob
    Double probR;
    Double probG;
    Double probB;
    Double probY;    
    //num wilds left
    Integer wild = 4;
    Integer wildD4 = 4;
    //wild prob
    Double probWild;
    Double probWildD4;
    //Cards in deck
    Integer CID = 108;
    //player probability in format red, green, blue, yellow
    Double[] p1 = new Double[4];
    Double[] p2 = new Double[4];
    Double[] p3 = new Double[4];
    Double[] hand = new Double[4];


    /**
     * play - This method is called when it's your turn and you need to
     * choose what card to play.
     *
     * The hand parameter tells you what's in your hand. You can call
     * getColor(), getRank(), and getNumber() on each of the cards it
     * contains to see what it is. The color will be the color of the card,
     * or "Color.NONE" if the card is a wild card. The rank will be
     * "Rank.NUMBER" for all numbered cards, and another value (e.g.,
     * "Rank.SKIP," "Rank.REVERSE," etc.) for special cards. The value of
     * a card's "number" only has meaning if it is a number card. 
     * (Otherwise, it will be -1.)
     *
     * The upCard parameter works the same way, and tells you what the 
     * up card (in the middle of the table) is.
     *
     * The calledColor parameter only has meaning if the up card is a wild,
     * and tells you what color the player who played that wild card called.
     *
     * Finally, the state parameter is a GameState object on which you can 
     * invoke methods if you choose to access certain detailed information
     * about the game (like who is currently ahead, what colors each player
     * has recently called, etc.)
     *
     * You must return a value from this method indicating which card you
     * wish to play. If you return a number 0 or greater, that means you
     * want to play the card at that index. If you return -1, that means
     * that you cannot play any of your cards (none of them are legal plays)
     * in which case you will be forced to draw a card (this will happen
     * automatically for you.)
     */
    public int play(ArrayList<Card> hand, Card upCard, Color calledColor,
        GameState state) {
        // THIS IS WHERE YOUR AMAZING CODE GOES

        cardsLeft(state.getPlayedCards());//find cards not played
        cardsLeft(hand);//find cards not held
        prob();//find probability cards available for play will be played
        diagnostic();//check variable states
        return -1;
    }


    /**
     * callColor - This method will be called when you have just played a
     * wild card, and is your way of specifying which color you want to 
     * change it to.
     *
     * You must return a valid Color value from this method. You must not
     * return the value Color.NONE under any circumstances.
     */
    public Color callColor(ArrayList<Card> hand) {

        // THIS IS WHERE YOUR AMAZING CODE GOES
        return null;
    }
    
    //find cards left in deck
    public void cardsLeft(ArrayList<Card> played)
    {
        for(int i=0;i<played.size();i++)
        {
            switch(played.get(i).getRank())
            {
                case WILD_D4:
                    wildD4 -= 1;
                    break;
                case WILD:
                    wild -= 1;
                    break;
                case DRAW_TWO:
                    adjArray(played.get(i).getColor(),12);
                    break;
                case SKIP:
                    adjArray(played.get(i).getColor(),11);
                    break;
                case REVERSE:
                    adjArray(played.get(i).getColor(),10);
                    break;
                case NUMBER:
                    adjArray(played.get(i).getColor(),played.get(i).getNumber());
                    break;
            }
            CID--;
        }
    }
    
    public void adjArray(Color card, int i)
    {
        switch(card)
        {
            case RED:
                red[i]--;
                break;
            case GREEN:
                green[i]--;
                break;
            case BLUE:
                blue[i]--;
                break;
            case YELLOW:
                yellow[i]--;
                break;
        }
    }
    //find probability of card being played
    public void prob()
    {
        for(int i=0;i<13;i++)
        {
            probRed[i]=(double)red[i]/CID;
            probGreen[i]=(double)green[i]/CID;
            probBlue[i]=(double)blue[i]/CID;
            probYellow[i]=(double)yellow[i]/CID;
        }
        probWild = wild/(double)CID;
        probWildD4 = wildD4/(double)CID;
        probR = addArray(red)/(double)CID;
        probG = addArray(green)/(double)CID;
        probB = addArray(blue)/(double)CID;
        probY = addArray(yellow)/(double)CID;        
    }
    
    public Integer addArray(Integer[] arrayTest)
    {
        Integer val=0;
        for(int i=0;i<arrayTest.length;i++)
        {
            val += arrayTest[i];
        }
        return val;
    }
    //print main variables
    public void diagnostic()
    {
        System.out.printf("%f%n%f%n%f%n%f%n%f%n%f%n%d%n%d%n%d%n",probR,probG,probB,probY,probWild,probWildD4,CID,wild,wildD4);
        for(int i=0;i<red.length;i++)System.out.print(red[i]);
        System.out.println();
        for(int i=0;i<red.length;i++)System.out.print(green[i]);
        System.out.println();
        for(int i=0;i<red.length;i++)System.out.print(blue[i]);
        System.out.println();
        for(int i=0;i<red.length;i++)System.out.print(yellow[i]);
        System.out.println();
        for(int i=0;i<red.length;i++)System.out.print(probRed[i]);
        System.out.println();
        for(int i=0;i<red.length;i++)System.out.print(probGreen[i]);
        System.out.println();
        for(int i=0;i<red.length;i++)System.out.print(probBlue[i]);
        System.out.println();
        for(int i=0;i<red.length;i++)System.out.print(probYellow[i]);
        System.out.println();
    }
 
}