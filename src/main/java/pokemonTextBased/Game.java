package pokemonTextBased;// Zach Mahan, started 20250228, for fun Pokemon Game

//BUGS: fix flinching ghosts (doesn't fail) (resolved?), engine choosing fake-out when it fails?

//general ideas:
// -Add savability when game is farther along (JSON)!

//STORY & Progression
// -Add Silph Portable TV (in Bag), new news reels after each gym?
// -Finish Map coloring --> unlocking of locations & progression + "unlock all areas mode"*
// -Finish Gym Leader NPC dialogue

//LOCATIONS
// -Add more battle/encounter zones (e.g. mt moon)
// -Add Rocket Safari Zone w/ special mons --> rock/bait mechanic

//BATTLES
// -Add better Trick Room judgement
// -Add full-fxning Tailwind, Light Screen, Reflect

//SPECIFIC
// -Add Move sounds (one per move type?)
// -Add full pokedex functionality (track catches)
// -Add different pokeballs (enums, adjust images and catch rates accordingly, add to pokemarts)
// -Make the Box not dogwater (pages, like 30 pkm per page)

//OTHER FEATURES


import java.util.*;
import java.util.concurrent.ExecutionException;

public class Game {

    public static boolean playStatus = true;
    public static boolean testStatus = true;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        new javafx.embed.swing.JFXPanel();
        Scanner sc1 = new Scanner(System.in);
        Thread.sleep(100);
        Graphics.printClearLines(50);
        bootIntoVer(testStatus, sc1);
        enterDummyMainMenu(sc1);
        User.getTextSpeed(sc1);
        User.askUserToSetDifficulty(sc1);
        User.askUserToSetHints(sc1);
        playOpening(sc1);
        while (playStatus) {
            enterMainMenu(sc1);
        }
        sc1.close();
    }

    //cutscenes
    public static void playOpening(Scanner sc1) throws InterruptedException {
        Graphics.printSmallTitleImage();
        Graphics.printLoadingBar(60);
        Sound.stopAllSounds();
        Sound.playMusicOnLoop("src/main/music/labTheme.mp3");
        int openingWaitTime = User.textSpeed;
        Graphics.printProfessorOak();
        System.out.println("Welcome to the world of Pokemon! I'm Professor Oak.");
        Thread.sleep(openingWaitTime);
        System.out.println("I have invented a marvelous new technology: the Pokedex!");
        Thread.sleep(openingWaitTime);
        System.out.println("It lets us track and identify every known Pokemon! Isn't that amazing?");
        Thread.sleep(openingWaitTime);
        System.out.println("And, since you're embarking on your own Pokemon journey, I want to give one to you.");
        Thread.sleep(openingWaitTime);
        System.out.println("\nObtained the Pokedex from Professor Oak!\n");
        Thread.sleep(openingWaitTime);
        System.out.println("Oh, and, uh, remind me, what's your name?");
        User.setUsername(askForUsername(sc1));
        System.out.println("Right, of course, " + User.getUsername() + "!");
        Thread.sleep(openingWaitTime);
        System.out.println("Also, are you a boy or girl?");
        String choiceGender = "";
        String choiceToConfirmGender = "";
        do {
            System.out.println("[1] Boy");
            System.out.println("[2] Girl");
            choiceGender = sc1.nextLine().trim();
            switch (choiceGender) {
                case "1" -> User.gender = User.Gender.BOY;
                case "2" -> User.gender = User.Gender.GIRL;
                default -> {
                    System.out.println("Invalid input. Enter 1 or 2.");
                    continue;
                }
            }
            Graphics.printPlayer(false);
            System.out.println("So, you're a " + User.gender.genderString + "? (Y/N)");
            choiceToConfirmGender = sc1.nextLine().trim().toUpperCase();
            if (!choiceToConfirmGender.equalsIgnoreCase("Y")) {
                System.out.println("Are you a boy or girl?");
            }
        } while (!choiceToConfirmGender.equalsIgnoreCase("Y"));
        Pokemon starter = null;
        do {
            Graphics.printThreeStarterPokeballs();
            Thread.sleep((int) (.5 * openingWaitTime));
            System.out.println("Now, which starter pokemon would you like?");
            int starterNum = getUndecidedStarterSelection(sc1);
            switch (starterNum) {
                case 1:
                    Graphics.printSpecies("Bulbasaur");
                    Pokemon confirmedBulbasaur = confirmStarterInOpening(sc1, starterNum);
                    if (confirmedBulbasaur != null) {
                        starter = confirmedBulbasaur;
                    }
                    break;
                case 2:
                    Graphics.printSpecies("Charmander");
                    Pokemon confirmedCharmander = confirmStarterInOpening(sc1, starterNum);
                    if (confirmedCharmander != null) {
                        starter = confirmedCharmander;
                    }
                    break;
                case 3:
                    Graphics.printSpecies("Squirtle");
                    Pokemon confirmedSquirtle = confirmStarterInOpening(sc1, starterNum);
                    if (confirmedSquirtle != null) {
                        starter = confirmedSquirtle;
                    }
                    break;
                default:
                    System.out.println("Invalid choice! You must choose [1], [2], or [3].");
                    return;
            }
        } while (starter == null);
        Party.addToParty(starter, sc1);
        System.out.println("Ah! I think it likes you. Why don't you go take " + starter.getName() + " home?");
        Thread.sleep((long) (1.5 * openingWaitTime));
        pressEnterToContinue(sc1);
        Sound.stopMusic("src/main/music/labTheme.mp3");
        Graphics.printLoadingBar(60);
        Sound.playMusicOnLoop("src/main/music/palletTownTheme.mp3");
        System.out.println();
        Graphics.printHomeImage();
        Thread.sleep(openingWaitTime);
        Graphics.printPlayerMom();
        System.out.println("Oh, hi, " + User.getUsername() + "! Is that your " + starter.getName() + "?");
        Thread.sleep(openingWaitTime);
        System.out.println("It's so cute!");
        Thread.sleep((long) (.75 * openingWaitTime));
        System.out.println("Now, since you're going to be on your own, take this.\n");
        Thread.sleep(openingWaitTime);
        System.out.println("Received Bag from Mom!\n");
        Thread.sleep((long) (.75 * openingWaitTime));
        System.out.println("Received Map from Mom!\n");
        Thread.sleep((long) (.75 * openingWaitTime));
        System.out.println("That should help you on your Pokemon journey, but be careful out there. Avoid Team Rocket as much as you can.\n");
        Thread.sleep(openingWaitTime);
        System.out.println("And stay out of Rocketopolis!\n");
        Thread.sleep(openingWaitTime);
        pressEnterToContinue(sc1);
        Sound.stopAllSounds();
        Graphics.printLoadingBar(120);
    }
    public static String askForUsername(Scanner sc1) {
        System.out.println("Enter your name: ");
        String username;
        while (true) {
            username = sc1.nextLine().trim();
            if (!username.isEmpty()) {
                return username;
            }
            System.out.print("Invalid input. Please enter a valid name: ");
        }
    }
    public static int getUndecidedStarterSelection(Scanner sc1) {
        int starter;
        while (true) {
            if (sc1.hasNextInt()) {
                starter = sc1.nextInt();
                sc1.nextLine();  // Consume the leftover newline character
                if (starter == 1 || starter == 2 || starter == 3) {
                    return starter;
                }
            } else {
                sc1.next();  // Skip non-integer input
            }
            System.out.println("Invalid choice. Please enter 1, 2, or 3.");
        }
    }
    public static Pokemon confirmStarterInOpening(Scanner sc1, int undecidedStarterSelection) {
        while (true) {
            if (undecidedStarterSelection == 1) {
                System.out.println("Are you sure you want to select Bulbasaur? (Y/N)");
                String choice1 = sc1.nextLine();
                if (choice1.equalsIgnoreCase("Y")) {
                    return new Pokemon(Species.getSpecies("Bulbasaur"), 5, false);
                } else if (choice1.equalsIgnoreCase("N")) {
                    return null;
                } else {
                    System.out.println("Invalid Input. Enter [Y] or [N]");
                }
            } else if (undecidedStarterSelection == 2) {
                System.out.println("Are you sure you want to select Charmander? (Y/N)");
                String choice2 = sc1.nextLine();
                if (choice2.equalsIgnoreCase("Y")) {
                    return new Pokemon(Species.getSpecies("Charmander"), 5, false);
                } else if (choice2.equalsIgnoreCase("N")) {
                    return null;
                } else {
                    System.out.println("Invalid Input. Enter [Y] or [N]");
                }
            } else if (undecidedStarterSelection == 3) {
                System.out.println("Are you sure you want to select Squirtle? (Y/N)");
                String choice3 = sc1.nextLine();
                if (choice3.equalsIgnoreCase("Y")) {
                    return new Pokemon(Species.getSpecies("Squirtle"), 5, false);
                } else if (choice3.equalsIgnoreCase("N")) {
                    return null;
                } else {
                    System.out.println("Invalid Input. Enter [Y] or [N]");
                }
            } else {
                System.out.println("Error, method was given an input other than 1, 2, or 3.");
                return null;
            }
        }
    }

    //menu logic
    public static void enterMainMenu(Scanner sc1) throws InterruptedException, ExecutionException {
        Sound.stopAllSounds();
        while (true) {
            Sound.playMusicOnLoop("src/main/music/manWhoSoldTheWorld.mp3");
            for(int i = 0; i < 10; i++) {
                System.out.println("=".repeat(137));
            }
            Graphics.printBigTitleImage();
            System.out.println("=".repeat(137));
            System.out.println("----------------------------------");
            System.out.println("| [ENTER] to start | [E] to exit |");
            System.out.println("----------------------------------");
            String choice = sc1.nextLine().trim().toUpperCase();

            if (choice.isBlank()) {
                Sound.click();
                Sound.stopAllSounds();
                enterPlayMenu(sc1);
                break;
            } else if (choice.equals("E")) {
                askToConfirmExit(sc1);
                Sound.stopAllSounds();
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }
    public static void enterDummyMainMenu(Scanner sc1) throws InterruptedException, ExecutionException {
        Sound.stopAllSounds();
        while (true) {
            Sound.playMusicOnLoop("src/main/music/manWhoSoldTheWorld.mp3");
            for(int i = 0; i < 10; i++) {
                System.out.println("=".repeat(137));
            }
            Graphics.printBigTitleImage();
            System.out.println("=".repeat(137));
            System.out.println("----------------------------------");
            System.out.println("| [ENTER] to start | [E] to exit |");
            System.out.println("----------------------------------");
            String choice = sc1.nextLine().trim().toUpperCase();

            if (choice.isBlank()) {
                Sound.click();
                break;
            } else if (choice.equals("E")) {
                askToConfirmExit(sc1);
                Sound.stopAllSounds();
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }
    public static void enterPlayMenu(Scanner sc1) throws InterruptedException, ExecutionException {
        label:
        while (true) {
            Sound.playMusicOnLoop("src/main/music/titleMusic.mp3");
            Graphics.printPlayer(true);
            System.out.println("==========================================================================");
            System.out.println("| [M] Open Map | [V] View Badges | [O] Options | [R] Return to Main Menu |");
            System.out.println("--------------------------------------------------------------------------");
            String choicePlayMenu = sc1.nextLine().trim().toUpperCase();
            switch (choicePlayMenu) {
                case "M":
                    Location.openMap(sc1);
                    break;
                case "V":
                    Location.viewBadges(sc1);
                    break;
                case "O":
                    Location.openOptionsMenu(sc1);
                    break;
                case "R":
                    break label;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }
    }

    public static boolean askToPlayBattleRushMode(Scanner sc1) throws InterruptedException, ExecutionException {
        sc1.nextLine();
        String choice;
        boolean enteredBattleRushMode = false;
        do {
            System.out.println("Do you want to play the Battle Rush game mode? (Y/N) - Currently this mode is an onslaught of random wild encounters, mainly for battle testing purposes.");
            choice = sc1.nextLine().trim();
            if (choice.equalsIgnoreCase("Y")) {
                enteredBattleRushMode = true;
                givePlayerPkmForBattleRushMode(sc1);
                Location.goOnRouteWithInfinite149Enc(sc1);
                Party.clearParty();
                Box.clearBox();
            }
        } while (!choice.equalsIgnoreCase("N"));
        System.out.println("\nYou quit Battle Rush!");
        Thread.sleep((long) (.75 * User.textSpeed));
        return enteredBattleRushMode;
    }
    public static void givePlayerPkmForBattleRushMode(Scanner sc1) throws InterruptedException {
        System.out.println("Giving you 3 random Pokemon...\n");
        Random randSpecies = new Random();
        Random randLevel1 = new Random();
        Random randLevel2 = new Random();
        Random randLevel3 = new Random();
        int level1 = randLevel1.nextInt(5, 16);
        int level2 = randLevel2.nextInt(5, 16);
        int level3 = randLevel3.nextInt(5, 16);
        Pokemon randomPkm1 = new Pokemon(Species.getSpecies(Species.getNameFromDexNum(randSpecies.nextInt(1, 152))), level1, false);
        Pokemon randomPkm2 = new Pokemon(Species.getSpecies(Species.getNameFromDexNum(randSpecies.nextInt(1, 152))), level2, false);
        Pokemon randomPkm3 = new Pokemon(Species.getSpecies(Species.getNameFromDexNum(randSpecies.nextInt(1, 152))), level3, false);
        Thread.sleep((long) (User.textSpeed * .5));
        Party.addToParty(randomPkm1, sc1);
        Thread.sleep((long) (User.textSpeed * .5));
        Party.addToParty(randomPkm2, sc1);
        Thread.sleep((long) (User.textSpeed * .5));
        Party.addToParty(randomPkm3, sc1);
        Thread.sleep((long) (User.textSpeed * .5));
    }

    public static boolean askToPlay(Scanner sc1) {
        while (true) {
            System.out.println("Play? (Y/N)");
            String input = sc1.nextLine();
            if (input.equalsIgnoreCase("Y")) {
                System.out.println("\nBooting up...\n");
                Graphics.printSmallTitleImage();
                return true;
            } else if (input.equalsIgnoreCase("N")) {
                Graphics.sayGoodBye();
                System.out.println("Exiting game...");
                System.exit(0);
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }
    public static void askToConfirmExit(Scanner sc1) {
        while (true) {
            System.out.println("Are you sure you want to exit? (Y/N)");
            String input = sc1.nextLine();
            if (input.equalsIgnoreCase("N")) {
                System.out.println("\nBooting up...\n");
                break;
            } else if (input.equalsIgnoreCase("Y")) {
                Graphics.sayGoodBye();
                System.out.println("Exiting game...");
                System.exit(0);
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }
    public static void pressEnterToContinue(Scanner sc1) {
        System.out.println("[ENTER] to continue");
        sc1.nextLine();
        Sound.click();
    }
    public static void pressEnterToContinue() {
        Scanner sc1 = new Scanner(System.in);
        System.out.println("[ENTER] to continue");
        sc1.nextLine();
        Sound.click();
    }

    //tools
    public static void bootIntoVer(boolean testStatus, Scanner sc1) throws InterruptedException, ExecutionException {
        Sound.playMusicOnLoop("src/main/music/titleMusic.mp3");
        System.out.println("ALPHA 4/2025");
        if(!testStatus) {
            System.out.println("BOOTED IN VER. REG");
        }
        if (testStatus) {
            System.out.println("BOOTED IN VER. TEST");
            Sound.playMusicOnLoop("src/main/music/titleMusic.mp3");
            Graphics.printThreeStarterPokeballs();
            Party.addToParty(new Pokemon(Species.getSpecies("scizor"), 20, true), sc1);
            Party.addToParty(new Pokemon(Species.getSpecies("Tyranitar"), 20, Pokemon.getShinyOdds()), sc1);
            Party.addToParty(new Pokemon(Species.getSpecies("mew"), 20, Pokemon.getShinyOdds()), sc1);
            Party.addToParty(new Pokemon(Species.getSpecies("excadrill"), 20, Pokemon.getShinyOdds()), sc1);
//            Party.addToParty(new Pokemon(Species.getSpecies("Togekiss"), 20, Pokemon.getShinyOdds()), sc1);
//            Party.addToParty(new Pokemon(Species.getSpecies("dragapult"), 20, Pokemon.getShinyOdds()), sc1);
            //User.askUserToSetHints(sc1);
            //User.askUserToSetDifficulty(sc1);
            Trainer templateTrainer = new Trainer(Trainer.Title.VAUGHAN_DISTRICT_GYM_LEADER);
            //Party.p = Engine.cloneParty(templateTrainer.getParty(), false);
            User.decreaseReputation(0);
            Bag.earnBP(500);
            //Bag.addSpecialItem("Rare Candy", 200);
            //User.unlockEverything();
            //Encounter.enterWildPkmBattle(new Pokemon("Incineroar", 20, true), sc1);
            //Encounter.enterTrainerBattle(templateTrainer, sc1);
            //Encounter.enterTrainerBattle(Trainer.buildBattleLeagueTrainer(), sc1);
            enterMainMenu(sc1);
        }
    }
}
