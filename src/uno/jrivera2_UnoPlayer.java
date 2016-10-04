
package uno;

import java.util.ArrayList;

public class jrivera2_UnoPlayer implements UnoPlayer {
    //color array in order 0-9 numbers, 10 reverse, 11 skip, 12 draw 2
    int[] red = new int[] {1,2,2,2,2,2,2,2,2,2,2,2,2};
    int[] green = new int[] {1,2,2,2,2,2,2,2,2,2,2,2,2};
    int[] blue = new int[] {1,2,2,2,2,2,2,2,2,2,2,2,2};
    int[] yellow = new int[] {1,2,2,2,2,2,2,2,2,2,2,2,2};
    double[] probRed = new double[13];
    double[] probGreen = new double[13];
    double[] probBlue = new double[13];
    double[] probYellow = new double[13];
    //num wilds left
    int wild = 4;
    int wildD4 = 4;
    double probWild;
    double probWildD4;
    //Cards in deck
    int CID = 108;
    //player probability in format red, green, blue, yellow
    double[] p1 = new double[4];
    double[] p2 = new double[4];
    double[] p3 = new double[4];
    double[] hand = new double[4];


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

        cardsLeft(state.getPlayedCards());
        cardsLeft(hand);
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
    
    public void prob()
    {
        for(int i=0;i<red.length;i++)
        {
            probRed[i]=(double)red[i]/CID;
        }
        for(int i=0;i<green.length;i++)
        {
            probGreen[i]=(double)green[i]/CID;
        }
        for(int i=0;i<blue.length;i++)
        {
            probBlue[i]=(double)blue[i]/CID;
        }
        for(int i=0;i<yellow.length;i++)
        {
            probYellow[i]=(double)yellow[i]/CID;
        }
        probWild = wild/CID;
        probWildD4 = wildD4/CID;
    }
 
}