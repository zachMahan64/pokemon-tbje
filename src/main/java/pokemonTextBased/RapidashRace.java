package pokemonTextBased;

import java.util.*;

public class RapidashRace {
    public static void simulateRace() throws InterruptedException {
        Scanner sc1 = new Scanner(System.in);
        Random random = new Random();
        String[] names = {"Pewter", "Cerulean", "Vermilion", "Celadon", "Fuchsia", "Cinnabar"};
        int[] possibleOdds = {-300, -200, -100, 100, 200, 300};

        boolean playAgain = true;

        while (playAgain) {
            Rapidash[] rapidashes = new Rapidash[6];
            for (int i = 0; i < 6; i++) {
                rapidashes[i] = new Rapidash(names[i], possibleOdds[random.nextInt(possibleOdds.length)]);
            }

            System.out.println("Welcome to Kanto's National Rapidash Track!");
            System.out.println("Here are today's odds:");
            for (int i = 0; i < rapidashes.length; i++) {
                System.out.println((i + 1) + ". Rapidash " + rapidashes[i].name + " (" + rapidashes[i].odds + ")");
            }

            int choice;
            while (true) {
                System.out.print("Which Rapidash would you like to bet on? (Enter 1-6): ");
                String input = sc1.nextLine().trim();
                try {
                    choice = Integer.parseInt(input) - 1;
                    if (choice >= 0 && choice < 6) break;
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }

            int wager = Casino.getWager(Bag.getPokedollars());

            System.out.println("\nThe race is about to begin!");
            Thread.sleep(1000);
            System.out.println("And they're off!\n");

            int[] progress = new int[6];
            boolean raceOver = false;

            while (!raceOver) {
                Thread.sleep(1000);
                int eventRapidash = random.nextInt(6);
                int eventMove = random.nextInt(3) + 1;
                progress[eventRapidash] += eventMove;
                System.out.println("Rapidash " + rapidashes[eventRapidash].name + " is galloping forward!\n");

                if (random.nextDouble() < 0.4) {
                    int eventType = random.nextInt(3);
                    switch (eventType) {
                        case 0:
                            System.out.println("Oh no! Rapidash " + rapidashes[eventRapidash].name + " stumbles and loses ground!\n");
                            progress[eventRapidash] = Math.max(0, progress[eventRapidash] - 4);
                            break;
                        case 1:
                            System.out.println("Incredible! Rapidash " + rapidashes[eventRapidash].name + " finds a burst of speed!\n");
                            progress[eventRapidash] += 4;
                            break;
                        case 2:
                            System.out.println("A wild Pidgeotto swoops down, distracting Rapidash " + rapidashes[eventRapidash].name + "!\n");
                            progress[eventRapidash] = Math.max(0, progress[eventRapidash] - 2);
                            break;
                    }
                }

                if (Arrays.stream(progress).max().orElse(0) >= 10) {
                    raceOver = true;
                }
            }

            int winnerIndex = 0;
            for (int i = 1; i < progress.length; i++) {
                if (progress[i] > progress[winnerIndex]) {
                    winnerIndex = i;
                }
            }
            Graphics.printGallopingRapidash();
            System.out.println("\nAnd the winner is... Rapidash " + rapidashes[winnerIndex].name + "!\n");

            if (winnerIndex == choice) {
                double payoutMultiplier = rapidashes[winnerIndex].odds < 0
                        ? 100.0 / -rapidashes[winnerIndex].odds
                        : rapidashes[winnerIndex].odds / 100.0;
                int winnings = (int) (wager * payoutMultiplier);
                System.out.println("Congratulations! You won " + winnings + " Pokédollars!\n");
                Bag.adjustPokedollarBalance(winnings);
            } else {
                System.out.println("Better luck next time! You lost your " + wager + " Pokedollar wager.\n");
                Bag.adjustPokedollarBalance(-wager);
            }

            while (true) {
                System.out.print("Would you like to bet on another race? (Y/N): ");
                String replayInput = sc1.nextLine().trim().toUpperCase();
                if (replayInput.equals("Y")) {
                    playAgain = true;
                    break;
                } else if (replayInput.equals("N")) {
                    playAgain = false;
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                }
            }
            System.out.println();
        }
    }

    static class Rapidash {
        String name;
        int odds;

        Rapidash(String name, int odds) {
            this.name = name;
            this.odds = odds;
        }
    }
}
