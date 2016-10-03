#CPSC 220 - Programming & Problem-solving - Fall 2016

Programming assignment #3

Possible experience: +50XP (or even +60XP)

Due: Monday, Oct. 10th, midnight

##Uno!

Your assignment is to implement a sensible, strategic gaming algorithm that can compete in live game play against your fellow students and (ideally) slaughter them.

(Note that in this assignment, you will not be turning in a "main()" method. Indeed, the ultimate purpose of this assignment is not to create a main() method. Your purpose is rather to create two other methods that my main() method will call as it simulates an Uno game.)

##The game

If you're one of the seventeen people in the U.S. who has never played Uno, you should read the Wikipedia article to get informed. Briefly, Uno is a popular card game in which each player holds a hand of cards, and tries to be the first one to "go out," or play all of their cards. Players are seated in a ring, and in the middle of this ring is an "up card," or a card placed on the table face up. Players take turns in sequence, clockwise around the ring. When it is your turn, you have the opportunity to play one of your cards on this up card (which will then become the new up card) and thus reduce the size of your hand. The card you play, however, must be playable on the up card, according to the following rules:

Most cards have a color -- red, green, blue, or yellow -- and you may play a card if it has the same color as the up card.
The colored cards each have a rank -- either a number from 0-9, or else a special "skip," "reverse," or "draw 2" rank. You may play a card if it has the same rank as the up card, even if it is of a different color.
There are two kinds of "wild" cards: ordinary wilds, and "draw 4" wilds. Either kind can be played on any up card. When you do so, you "call" a new color, specifying what color the next player must play.
Some special cards have an effect after being played, namely:

If a "skip" card is played, the next player in sequence is skipped.
If a "draw two" card is played, the next player in sequence must draw two cards from the deck, and is then skipped.
If a "wild draw four" card is played, the next player in sequence must draw four cards from the deck, and is then skipped. (The player who played the "wild draw four" must then call a color as with a normal wild.)
If a "reverse" card is played, the sequence of players is reversed to counterclockwise (or back to clockwise, after an even number of reverses.)
The object of the game is to run out of cards. When this happens, the player going out is awarded points based on the cards remaining in the opponents' hands. These points are calculated as follows:

For every numbered card held by an opponent, the winner of the round gets points equal to that number. (5 points for a 5, no points for a 0, etc.)
For every "special" colored card (draw two, reverse, and skip), the winner of the round gets 20 points.
For every "wild" card (either normal, or draw four), the winner of the round gets 50 points.
Normally, players continue playing hand after hand until one player reaches 500 points, and is declared the overall winner of the game.

##Your project

I have written an Uno simulation game. It simulates shuffling a deck, dealing hands to players, drawing an initial up card, enforcing all of the rules above, declaring a winner, and calculating scores. The only thing it does not do is actually choose a card to play from a hand (or call a color if a wild is played.)

Every student in the class will be writing their own code to do those two things: play a card from a hand, and call a color in case a wild was played. My program will run the game, and then at the appropriate points, call your method(s) to do those two things. In this way, your program will be able to "play" Uno against your classmates in a tournament. Whoever has the best algorithm for playing a card should win.

You may object to that last statement, claiming that luck is a major factor. This would be true except for one thing: I am not going to pit your Uno program against your fellow students' programs in just one game, but in 50,000 straight games. This will admittedly take a few seconds. But over that many games, any "lucky deals" that any one player might get will even out over time, leaving a superior algorithm with the lead.

###Getting started

Do the following to get your project set up in Netbeans:

Create a new project. Call it "Uno." (Please do not call it anything else. Please call it "Uno," capitalized.)
Right-click on the "uno" (lower-case) package that Netbeans created inside your Uno (capitalized) project, and choose "New > Java Class."
Name the new class "Card."
Open up the Card.java file you just created. Delete its entire contents, and replace it with the contents of this file. (You do not need to understand anything in this file, or even read it.)
Right-click on the "uno" (lower-case) package that Netbeans created inside your Uno (capitalized) project, and choose "New > Java Class."
Name this second new class "GameState."
Open up the GameState.java file you just created. Delete its entire contents, and replace it with the contents of this file. (Ditto. Don't need to read or understand.)
Right-click on the "uno" (lower-case) package that Netbeans created inside your Uno (capitalized) project, and choose "New > Java Class."
Name this third new class "UnoPlayer."
Open up the UnoPlayer.java file you just created. Delete its entire contents, and replace it with the contents of this file. (Ditto. Don't need to read or understand.)
Right-click on the "uno" (lower-case) package that Netbeans created inside your Uno (capitalized) project, and choose "New > Java Class."
Name this fourth (and final) new class "jsmith_UnoPlayer," where "jsmith" is your UMW userid. Please do not name the class literally "jsmith_UnoPlayer". Rather, name it (for example) "rjones2_UnoPlayer," if rjones2 is your userid. Please do not name the class anything else. Please do not get clever, or creative, or capitalize differently, or use a hyphen instead of an underscore, or deviate from this naming convention in any way.
Open up the rjones2_UnoPlayer.java (or whatever) file you just created. Delete its entire contents, and replace it with the contents of this file. Note that when you do this, you will immediately have an error, which will be remedied by step 14, below.
In your newly pasted-in copy of this class, change the word "jsmith" to your userid in the "public class" line.
Finally, open up the Uno class that was created by NetBeans by default. You may delete its entire contents, and paste in this simple example of a small test case. This represents one of many scenarios (hands of cards plus chosen "up card") that your code will need to address properly and strategically. As before, replace "jsmith" with your actual UMW userid.
You are now ready to begin your mission of implementing the play() and callColor() methods.

###The UnoPlayer.java file

The UnoPlayer.java file you have just copied contains what is called a "Java interface." This is an advanced topic that you will learn all about in CPSC 240; lucky for you, you don't need to understand anything about it right now except what I'm telling you on this page. What is important about this file is the two lines that begin with "public enum."

I have created these two "enumerated types" to represent the colors and the ranks of the cards in the program. Essentially, what I have done here is add to the basic list of Java data types (int, double, etc.) Now, in addition to having a variable of type "int" or type "double," you can have a variable of type "Color" or "Rank."

The way you specify a value of one of these types is to prefix one of the capitalized words with "Color." or "Rank." For instance, here is some legal code:

    int x = 5;
    double y = 3.14;
    Color myFavoriteColor = Color.BLUE;
    Rank aPowerfulRank = Rank.WILD_D4;
There's really nothing more to it than that. Just be aware that the way you say "green" in the program is "Color.GREEN", and you'll be fine.

###Uno player: methods

Your rjones2_UnoPlayer.java file has detailed comments describing the play() and callColor() methods you are to write code for. Reading these detailed comments is an excellent and praiseworthy idea. Note that the play() method takes four parameters. Here is its method signature:

    public int play(ArrayList<Card> hand, Card upCard, Color calledColor,
        GameState state);
Collectively, these arguments tell your method (1) what cards are in your hand, (2) what card is the "up card," (3) what color was called (this argument only has relevance if the "up card" is a wild), and (4) a way to find out other miscellaneous things about the state of the game, for your use in building a sophisticated strategy.

Your job is to write code that returns the integer of the card you wish to play. In the event that you cannot play any card, you should return -1 from this method. (Note that returning a -1 for a hand in which you can legally play is an error.)

If you wish, you may call methods on the GameState object passed to access detailed information about the state of the game. This object supports the following methods:

int[] getNumCardsInHandsOfUpcomingPlayers() - the array returned by this method will have length equal to the number of players minus 1 (in a normal tournament game, this will be 3.) It tells you in order how many cards the next player to play after you has (at index 0), how many the player across from you has (at index 1), and how many the player who just played has (at index 2.) Note that when I say "the next player to play after you," that presumes, of course, that you do not play a skip or a reverse, in which case the player represented at index 1 or index 2, respectively, will be the next player.
int[] getMostRecentColorCalledByUpcomingPlayers() - this array follows the same format as the previous, except it contains Colors, not ints. It tells you the most recent color each player called when they played a wild. (The value will be "Color.NONE" if that player has not yet played a wild card this round.)
ArrayList<Card> getPlayedCards() - this method returns a list of the cards that have been played, in order, since the last deck remix. (A deck "remix" occurs if/when the draw pile becomes exhausted, and all of the cards in the discard pile are reshuffled and turned face down to become the new draw pile.) Interesting note: just from experimenting with my simulator, deck remixes are pretty uncommon. It seems that a large majority of games complete without ever requiring a deck remix, even when the players have the dumbest possible strategy (just play a random matching card.)
int[] getTotalScoreOfUpcomingPlayers() - finally, this array tells you the total cumulative score for each player (in the grand 50,000-game Uno match), in order of their presumed turns, in the same way that arrays from the first two method calls in this list represented that order.
You can take advantage of the game state object by choosing to call any of these methods on it that you choose. Note that you are also free to ignore any of them if they're not of interest to you in developing your strategy.

The callColor() method is simpler. It takes only one parameter, telling you what's in your hand:

    public Color callColor(ArrayList<Card> hand);
My code will call this method of yours when you have just played a wild and I need to know what color you want to call. It must return one of the four valid Color values (not Color.NONE.)

Finally, note that these two methods do not contain the word static, and they do contain the word public. There are deep reasons for these two facts that you will eventually learn. You do not need to know them now.

##Uno Tournament

Two weeks from Friday, during class, we will hold a bracket-style Uno tournament in which all functioning programs are entered as participants. Drama and excitement will be aplenty as the big screen shows the action. The grand prize winner will receive a valuable prize not available in stores.

##Grading

You have two grading options for this assignment. You can go for +50XP, or you can go for +60XP.

For +50XP...

To receive +50XP for this assignment, it is only necessary that your methods return a correct answer. In other words, your play() method must always return the index of a card that can in fact be played on the up card, and must always return -1 only in the case where no card can in fact be legally played. Your callColor() method must simply always return a valid Color. Beyond that, it doesn't matter how "dumb" your methods are, or how badly they play: they only must play according to the rules.

For +60XP...

To get +60XP, you must go beyond simply legality and attempt to implement an intelligent strategy for playing the game. You must strive to play not only a legal card, but a good card. Think about how you actually play the game of Uno: how do you decide what card to play? What color to call? Whether to switch the color or stick with it? When to part with a wild card? Try to write code that imitates your thought process. (If you're stuck for ideas, see the following section "Strategy ideas.")

Now how will I judge whether you "attempted to implement an intelligent strategy?" Good question. Here's my grading criteria:

If you place in the Final Four of the Uno tournament, your program will automatically and unquestionably judged to be of +60XP quality.
If you do not place in the Final Four, I will look at your well-commented, compellingly documented code to judge whether you attempted a non-trivial, intelligent approach. Your comments should narrate your algorithm completely and transparently. They should shed light on what your code is doing, and why, and explain the theory behind your approach. I will award points solely at my discretion based on how thorough and creative your program looks to me.
For something worse...

Note that your program cannot be entered into the tournament if it does not meet the "for +50XP..." criteria above. This is for the simple reason that it will crash my simulator. Only methods that return correct answers will be eligible for the tournament, and I will test this with my test suite (see below.)

##Vetting bugs

In order to thoroughly test your play() method, I have designed a comprehensive test suite. This program, when run properly, will execute your play() method on 10,000 random hands, ensuring that in each case you return a correct result. It is highly recommended that you perform this operation on your code before you turn it in. This is what I will use to determine whether your code is safe to enter the tournament.

To run it, perform the following steps:

In NetBeans, right-click on your "uno" package (not your "Uno" project) and choose "New > Java Class". Call the new class "TestCaseProcessor", and paste the entire contents of the tester program into the window. Change "jsmith" to your username, and save.
Also in NetBeans, right-click again on your "uno" package and choose "New > Other...". In the left "Categories" pick list, click on "Other" (at the bottom.) Then, in the right "File Types" pick list, click on "Empty File" (near the bottom.) Press "Next". Name the file "testCases.txt", and save. Now paste the entire contents of this file into the window, and save again.
Change the run-time configuration of the program:
In NetBeans, choose "Run > Set Project Configuration ... > Customize ...".
Click on "Run".
Set the "Main Class" to be "uno.TestCaseProcessor".
Set the Arguments to your userid (e.g., rjones2).
Change the "Working Directory" by pressing the "Browse..." button next to it. Navigate into your capitalized "Uno" project, then into "src" and finally "uno". Select "uno" as the working directory.
Run it, clicking the green arrow. You should either get happy messages telling you how many test cases have passed as they run, or else you should get a helpful message about how one of them didn't pass.
Stare hard at the message and then fix your error.
Strategy ideas

So you want to whip up on your fellow programmers. How can you do this? What should be a good strategy for choosing a card from an Uno hand? Here are some ideas:

Maybe since your hand's points go to an opponent if you lose the round, you should try to minimize the number of points you hold, getting rid of wild cards as soon as you can, then the special cards, and then 9's before 8's, 8's before 7's, etc.
On the other hand, it seems dumb to get rid of a wild card when you don't have to, since you can always hold it and play it later. So maybe you want to only play wild cards when you absolutely have to.
Or maybe there's some middle "sweet spot" between ditching wilds too early and holding on to them too long and getting stuck with them.
When you call a color, surely you want to call the color you have the most cards of.
On the other hand, if you have green 0, 2, and 3, and red 8 and Skip, you have a heck of a lot more points represented in your red cards, so maybe it would be better to call red to get rid of those sooner.
Suppose the up card is a red 5. You have both a red 3 and a blue 5. Should you change the color to blue or keep it red? This is similar to the decision about what to call when you play a wild, but with a twist: the up card may be red because one of your opponents wanted it to be red, and so it may be in your best interests to change it.
Interestingly, there are fewer "0" cards than any other 1-9 number. (The standard deck has two "2s" and two "5s" and two "6s" of every color, but only one "0" of each color.) So playing a 0 card means it's less likely that an opponent will be able to change the color before you get a chance to play again. So maybe if you have multiple cards in a color, and one of them is a 0, you want to play that early on, even though it's worth less points, since you will be more likely to be able to get rid of another card in that color.
Maybe all these decisions are affected by how big your overall hand is. If you only have a few cards left, you want to do everything possible to win the round and claim the prize, even if this means taking short-term risks. If you have a ton of cards, on the other hand, you might want to forget about winning the round and simply ditch as many points as possible, figuring to lose as small as possible.
Of course, your fellow students are reading these ideas too, so...maybe you should anticipate their following them and counteract. For instance, if everyone is going to try and ditch high point values first, you could try to keep high point values early in the game, so that it's less likely someone can switch the color on you later on by playing a card of the same rank. Or, if everyone is going to try to switch the color to favor their hand, you might want to deliberately call a different color when you have the choice, depending on circumstances.
Etc. Etc. Etc.
Trash talking and other mean-spirited ways of trying to intimidate your opponents

Heck yeah. Do it.

##Turning it in

To submit this program, send me an email with your jsmith_UnoPlayer.java file attached. (Double-check that it's actually attached!) The email should have the subject line "CPSC 220 program #3 turn-in". If you're going for +60XP, the body of this email should contain a concise summary your program's strategy. (If there is no paragraph describing this in your email, I will assume you are going for +50XP.)

#Good luck!!
