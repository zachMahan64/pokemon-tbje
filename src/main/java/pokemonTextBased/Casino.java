package pokemonTextBased;

import java.util.*;

public class Casino {
    private static final Random rand = new Random();
    private static final Scanner sc1 = new Scanner(System.in);

    //blackjack
    public static void startBlackjack() {
        while (true) {
            Graphics.printBlackjackImage();
            long playerPokedollars = Bag.getPokedollars();
            System.out.println("Welcome to the Blackjack table!");

            // wagering
            int wager = getWager(playerPokedollars);
            if (wager <= 0) {
                System.out.println("You don't have enough Pokedollars to play.");
                return; // exit if the player can't afford to wager
            }

            // dealing
            List<String> deck = createDeck();
            Collections.shuffle(deck);
            List<String> playerHand = new ArrayList<>();
            List<String> dealerHand = new ArrayList<>();
            dealCards(playerHand, dealerHand, deck);

            // player's turn
            boolean playerTurn = true;
            while (playerTurn && handValue(playerHand) < 21) {
                System.out.println("Your hand: " + playerHand + " (Total: " + handValue(playerHand) + ")");
                System.out.println("Dealer's showing: " + dealerHand.get(0));
                System.out.println("Do you want to [H]it or [S]tand?");
                String action = sc1.nextLine().toUpperCase().trim();
                if (action.equals("H")) {
                    playerHand.add(deck.remove(deck.size() - 1)); // Draw a card
                } else if (action.equals("S")) {
                    playerTurn = false; // End player's turn
                } else {
                    System.out.println("Invalid choice, please select \"H\" to Hit or \"S\" to Stand.");
                }
            }

            // Dealer's turn/AI
            if (handValue(playerHand) <= 21) {
                while (handValue(dealerHand) < 17) {
                    dealerHand.add(deck.remove(deck.size() - 1)); // Dealer hits if below 17
                }
            }

            // Show hands and determine the winner
            System.out.println("Your hand: " + playerHand + " (Total: " + handValue(playerHand) + ")");
            System.out.println("Dealer's hand: " + dealerHand + " (Total: " + handValue(dealerHand) + ")\n");

            if (handValue(playerHand) > 21) {
                System.out.println("You busted! You lose " + wager + " Pokedollars.");
                Bag.losePokedollars(wager);
            } else if (handValue(dealerHand) > 21) {
                System.out.println("Dealer busted! You win " + wager + " Pokedollars.");
                Bag.adjustPokedollarBalance(wager);
            } else if (handValue(playerHand) > handValue(dealerHand)) {
                System.out.println("You win! You gain " + wager + " Pokedollars.");
                Bag.adjustPokedollarBalance(wager);
            } else if (handValue(playerHand) < handValue(dealerHand)) {
                System.out.println("Dealer wins! You lose " + wager + " Pokedollars.");
                Bag.losePokedollars(wager);
            } else {
                System.out.println("It's a tie! No Pokedollars are wagered.");
            }

            System.out.println("\nDo you want to play again? (Y/N)");
            String playAgain = sc1.nextLine().toUpperCase().trim();
            if (playAgain.equals("N")) {
                System.out.println("\nCome back to the table anytime!");
                break;
            }
        }
    }
    public static int getWager(long playerPokedollars) {
        int wager = -1;
        while (wager < 0 || wager > playerPokedollars) {
            System.out.println("How much would you like to wager?");
            System.out.print("(You have " + playerPokedollars + " Pokedollars): ");
            try {
                wager = Integer.parseInt(sc1.nextLine().trim());
                if (wager < 0 || wager > playerPokedollars) {
                    System.out.println("Invalid wager amount. Please enter a number within your balance.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return wager;
    }
    private static List<String> createDeck() {
        List<String> deck = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(rank + " of " + suit);
            }
        }
        return deck;
    }
    private static void dealCards(List<String> playerHand, List<String> dealerHand, List<String> deck) {
        playerHand.add(deck.remove(deck.size() - 1)); // Deal to player
        dealerHand.add(deck.remove(deck.size() - 1)); // Deal to dealer
        playerHand.add(deck.remove(deck.size() - 1)); // Deal to player
        dealerHand.add(deck.remove(deck.size() - 1)); // Deal to dealer
    }
    private static int handValue(List<String> hand) {
        int value = 0;
        int aceCount = 0;

        for (String card : hand) {
            String rank = card.split(" ")[0];
            if (rank.equals("A")) {
                value += 11;
                aceCount++;
            } else if (rank.equals("K") || rank.equals("Q") || rank.equals("J")) {
                value += 10;
            } else {
                value += Integer.parseInt(rank);
            }
        }

        // Adjust for aces if value is over 21
        while (value > 21 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }

        return value;
    }
    //roulette
    public static void startRoulette() throws InterruptedException {
        Scanner sc1 = new Scanner(System.in);
        Random random = new Random();

        boolean playAgain = false;
        do {
            Graphics.printRouletteWheel();
            System.out.println("Welcome to the Roulette Table!");
            System.out.println("The roulette wheel has numbers from 0 to 36 (with 0 being green, 1-36 being red or black).");
            System.out.println("You can bet on a color (Red/Black), number (0-36), or odd/even.\n");

            System.out.println("What would you like to bet on? (Red/Black/Number/Odd/Even): ");
            String betType = sc1.next().toLowerCase();

            int wager = getWager(Bag.getPokedollars());

            Graphics.printRouletteWheel();
            System.out.println("\nThe roulette wheel is spinning...\n");
            Thread.sleep(2000);

            int resultNum = random.nextInt(37);
            String resultColor = (resultNum == 0) ? "Green" : (resultNum % 2 == 0 ? "Black" : "Red");

            // result
            System.out.println("The ball is spinning... and it lands on " + resultNum + " (" + resultColor + ")!\n");
            Thread.sleep(2000);

            // winnings/losses
            int winnings = 0;
            switch (betType) {
                case "red":
                    if (resultColor.equals("Red")) {
                        winnings = wager * 2; // If bet on red, payout 2x
                        System.out.println("Congratulations! You won " + winnings + " Pokédollars!");
                    } else {
                        System.out.println("Sorry, you lost your wager.");
                        winnings = -wager; // Loss
                    }
                    break;

                case "black":
                    if (resultColor.equals("Black")) {
                        winnings = wager * 2; // If bet on black, payout 2x
                        System.out.println("Congratulations! You won " + winnings + " Pokédollars!");
                    } else {
                        System.out.println("Sorry, you lost your wager.");
                        winnings = -wager; // Loss
                    }
                    break;

                case "even":
                    if (resultNum != 0 && resultNum % 2 == 0) {
                        winnings = wager * 2; // If bet on even, payout 2x
                        System.out.println("Congratulations! You won " + winnings + " Pokédollars!");
                    } else {
                        System.out.println("Sorry, you lost your wager.");
                        winnings = -wager; // Loss
                    }
                    break;

                case "odd":
                    if (resultNum != 0 && resultNum % 2 != 0) {
                        winnings = wager * 2; // If bet on odd, payout 2x
                        System.out.println("Congratulations! You won " + winnings + " Pokédollars!");
                    } else {
                        System.out.println("Sorry, you lost your wager.");
                        winnings = -wager; // Loss
                    }
                    break;

                case "number":
                    System.out.print("Enter your number bet (0-36): ");
                    int numberBet = sc1.nextInt();
                    if (numberBet == resultNum) {
                        winnings = wager * 35; // If bet on number, payout 35x
                        System.out.println("Congratulations! You won " + winnings + " Pokédollars!");
                    } else {
                        System.out.println("Sorry, you lost your wager.");
                        winnings = -wager; // Loss
                    }
                    break;

                default:
                    System.out.println("Hey, take this seriously or we might just take your money! Please choose from Red, Black, Odd, Even, or Number.");
                    Thread.sleep(3000);
                    continue;
            }
            //give out winnings/losses
            Bag.adjustPokedollarBalance(winnings);

            System.out.print("Would you like to play again? (Y/N): ");
            String playAgainChoice = sc1.next().toUpperCase();
            playAgain = playAgainChoice.equals("Y");
            System.out.println();
        } while(playAgain);
    }


    //who's that pokemon
    public static void startWhosThatPokemon(Scanner sc1) throws InterruptedException {
        boolean playAgain = false;
        do {
            System.out.println("Guess the name right to earn a bit of money, but guess the Pokedex number right to double your money!");
            System.out.println("Get ready to play \"Who's that Pokemon?\" ...\n");
            Thread.sleep(3000);
            int wager = getWager(Bag.getPokedollars());
            Bag.spendPokedollarsSilent(wager);
            int pokemonToGuessNum = rand.nextInt(1, 152);
            Graphics.printSpecies(pokemonToGuessNum);
            System.out.println("Who's that Pokemon!?");
            String playerGuessedName = sc1.nextLine().trim();
            if(playerGuessedName.equalsIgnoreCase(Species.getNameFromDexNum(pokemonToGuessNum))){
                System.out.println("That's right! You 1.1 times'd your wager!\n");
                Thread.sleep(2000);
                Bag.adjustPokedollarBalance((int) (wager * 1.1));
            } else {
                System.out.println("Oops, that's wrong! You lost your wager!\n");
                Thread.sleep(2000);
                System.out.println("The right answer was: " + Species.getNameFromDexNum(pokemonToGuessNum));
                Thread.sleep(2000);
            }
            System.out.println("What's that Pokemon's Pokedex number!?");
            String playerGuessedNum = sc1.nextLine().trim();
            if(playerGuessedNum.equalsIgnoreCase(Integer.toString(pokemonToGuessNum))){
                System.out.println("That's right, nice! You got some good money!\n");
                Thread.sleep(2000);
                Bag.adjustPokedollarBalance(wager);
            } else {
                System.out.println("Oops, that's wrong!\n");
                Thread.sleep(2000);
                System.out.println("The right answer was: " + pokemonToGuessNum);
                Thread.sleep(2000);
            }
            System.out.println("Play Again?(Y/N)");
            playAgain = sc1.nextLine().trim().equalsIgnoreCase("Y");
        } while (playAgain);
    }
}
