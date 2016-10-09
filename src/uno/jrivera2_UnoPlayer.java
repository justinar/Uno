
package uno;

import java.util.ArrayList;

public class jrivera2_UnoPlayer implements UnoPlayer {
    //color array in order 0-9 numbers, 10 reverse, 11 skip, 12 draw 2
    Integer[][] numCards = new Integer[][]{ {1,2,2,2,2,2,2,2,2,2,2,2,2},   //0-Red
                                            {1,2,2,2,2,2,2,2,2,2,2,2,2},   //1-Yellow
                                            {1,2,2,2,2,2,2,2,2,2,2,2,2},   //2-Green
                                            {1,2,2,2,2,2,2,2,2,2,2,2,2},}; //3-Blue
    //value prob
    Double[][] probValue = new Double[4][13];
    //color prob
    Double[] probColor = new Double[4];
    //num wilds left
    Integer wild = 4;
    Integer wildD4 = 4;
    //wild prob
    Double probWild;
    Double probWildD4;
    //Cards in deck
    Integer CID = 108;
    //Every player's card color played this game in format
    //[0] left,[1] center,[2] right,[3] self
    ArrayList<Color>[] played = new ArrayList[4];
    //player probability in format
    //[0][x] 1st in line,[1][x] 2nd in line,[2][x] 3rd in line,[3][x] hand
    //[x][0]red, [x][1]yellow, [x][2]green, [x][3]blue
    Double[][] probPlayerColor = new Double[4][4];
    //preference for self
    Color prefColor;
    Integer[] prefNumber = new Integer[10];
    Rank[] prefRank = new Rank[6];


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
    public int play(ArrayList<Card> hand, Card upCard, Color calledColor,GameState state)
    {
        cardsLeft(state.getPlayedCards());//find cards not played
        cardsLeft(hand);//find cards not held
        prob();//find probability cards available for play will be played
        //vvvvvvv remove for final vvvvvvv//
        diagnostic();//check variable states
        //^^^^^^^ remove for final ^^^^^^^//
        pref();//set preferences
        return 5;
    }


    /**
     * callColor - This method will be called when you have just played a
     * wild card, and is your way of specifying which color you want to 
     * change it to.
     *
     * You must return a valid Color value from this method. You must not
     * return the value Color.NONE under any circumstances.
     */
    public Color callColor(ArrayList<Card> hand)
    {
        return prefColor;
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
                    numCards[played.get(i).getColor().ordinal()][12]--;
                    break;
                case SKIP:
                    numCards[played.get(i).getColor().ordinal()][11]--;
                    break;
                case REVERSE:
                    numCards[played.get(i).getColor().ordinal()][10]--;
                    break;
                case NUMBER:
                    numCards[played.get(i).getColor().ordinal()][played.get(i).getNumber()]--;
                    break;
            }
            CID--;
        }
    }
    
    //find probability of card being played
    public void prob()
    {
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<13;j++)
            {
                probValue[i][j]=numCards[i][j]/(double)CID;
            }
        }
        probWild = wild/(double)CID;
        probWildD4 = wildD4/(double)CID;
        for(int i=0;i<4;i++)
        {
            probColor[i] = addArray(numCards[i])/(double)CID;
        }
    }
    //find total value of array
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
        System.out.printf("%f%n%f%n%d%n%d%n%d%n",probWild,probWildD4,CID,wild,wildD4);
        for(int i=0;i<4;i++)System.out.print(probColor[i]);
        System.out.println();
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<13;j++)
            {
                System.out.print(numCards[i][j]);
            }
            System.out.println();
        }
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<13;j++)
            {
                System.out.print(probValue[i][j]);
            }
            System.out.println();
        }
    }
    //set preferences based on hand and players
    public void pref()
    {
        pColor();
        pRank();
        pNumber();
    }
    //set color preferences
    public void pColor()
    {
        prefColor = Color.BLUE;
    }
    //set rank preferences
    public void pRank()
    {
        
    }
    //set number preferences
    public void pNumber()
    {
        
    }
    //find cards played
    public void cardHistory(GameState state)
    {
        ArrayList<Card> history =  state.getPlayedCards();
        //direction of play
        boolean foward = true;
        //player #
        int c = -1;
        Color currentColor = Color.NONE;
        for(int i=history.size();i>0;i--)
        {
            switch(history.get(i).getRank())
            {
                case REVERSE:
                    foward = !foward;
                    currentColor = history.get(i).getColor();
                    break;
                case SKIP:
                    c++;
                    currentColor = history.get(i).getColor();
                    break;
                case WILD:
                    currentColor = history.get(i-1).getColor();
                    break;
                case WILD_D4:
                    currentColor = history.get(i-1).getColor();
                    break;
                case DRAW_TWO:
                    currentColor = history.get(i).getColor();
                    break;
                case NUMBER:
                    currentColor = history.get(i).getColor();
                    break;
            }
            if(foward)
            {
                if(c==4) c=0;
                else c++;
            }
            else if(!foward)
            {
                if(c==0) c=4;
                else c--;
            }
            played[c].add(currentColor);
        }
    }
}