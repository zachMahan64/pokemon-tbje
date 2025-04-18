package pokemonTextBased;

import java.util.List;
import java.util.Scanner;

public class Party {
    public static Pokemon[] p = new Pokemon[6]; //party

    public static void addToParty(Pokemon pokemon, Scanner sc1) throws InterruptedException{
        pokemon.setIsFoe(false);
        for (int i = 0; i < p.length; i++) {
            if (p[i] == null) {
                p[i] = pokemon;
                Graphics.printPokemon(pokemon);
                System.out.println(pokemon.getName() + " was added to your party.\n");
                Game.pressEnterToContinue(sc1);
                return;
            }
        }

        System.out.println("Your party is full! Cannot add " + pokemon.getName() + ".");
        System.out.println("Would you like to add " + pokemon.getName() + " to the party and send another Pokemon to your Box? (Y/N)");
        String choice = sc1.nextLine().trim().toUpperCase();
        if (choice.equals("Y")) {
            sendToBox(sc1);
            addToParty(pokemon, sc1);
        } else {
            System.out.println(pokemon.getName() + " was not added to the party.");
            Box.addToBox(pokemon);
            Game.pressEnterToContinue(sc1);
        }
    }
    public static void enterPartyMenu(Scanner sc1) throws InterruptedException{
        while (true) {
            printParty();
            System.out.println("|             What would you like to do?            |");
            System.out.println("=====================================================");
            System.out.println("| [1] Switch active pokémon                         |");
            System.out.println("| [2] Send pokémon to Box                           |");
            System.out.println("| [3] Retrieve pokémon from Box                     |");
            System.out.println("| [4] View pokémon's details                        |");
            System.out.println("| [B] Back                                          |");
            System.out.println("-----------------------------------------------------");

            String choice = sc1.nextLine().trim().toUpperCase();

            switch (choice) {
                case "1":
                    switchPokemon(null, false, sc1);
                    break;
                case "2":
                    sendToBox(sc1);
                    break;
                case "3":
                    retrieveFromBox(sc1);
                    break;
                case "4":
                    enterPartyMemberDetailsMenu(sc1);
                    break;
                case "B":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option! Please choose a valid action.");
                    Game.pressEnterToContinue(sc1);
            }
        }
    }
    //wrappers
    public static boolean switchPokemon(boolean automaticallyForcedToSwitch, Scanner sc1) throws InterruptedException {
        return switchPokemon(null, automaticallyForcedToSwitch, sc1);
    }
    public static boolean switchPokemon(Arena arena, boolean automaticallyForcedToSwitch, Scanner sc1) throws InterruptedException {
        int slot = getSwitchSlotFromUser(arena, automaticallyForcedToSwitch, sc1);
        return switchPokemonToSlot(slot);
    }

    public static void enterPartyMemberDetailsMenu(Scanner sc1) {
        String choice = "";

        do {
            printParty();
            System.out.println("Enter a Pokémon (1-6) of which to view details, or [C] to cancel:");
            choice = sc1.nextLine().trim();

            if (choice.matches("[1-6]")) {
                int slot = Integer.parseInt(choice) - 1;

                if (p[slot] != null) {
                    viewPartyMemberDetails(slot, sc1);
                } else {
                    System.out.println("No Pokémon in that slot.");
                }
            }

        } while (!choice.equalsIgnoreCase("C"));
    }
    public static void viewPartyMemberDetails(int slot, Scanner sc1) {
        if (p[slot] == null) {
            System.out.println("No Pokémon in this slot.");
            return;
        }

        Pokemon pokemon = p[slot];
        Graphics.printPokemon(pokemon.getName(), false, pokemon.isShiny());
        System.out.println("=".repeat(100));

        String star = pokemon.isShiny() ? " ✨" : "";
        String type2 = pokemon.getType2().equals("None") ? "" : "/" + pokemon.getType2();

        // Print column headers
        System.out.printf("%-20s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n",
                " Name", "Level", "HP", "Attack", "SpAtk", "SpDef", "Speed", "Type");

        System.out.println("-".repeat(100));

        // Print Pokémon stats
        System.out.printf("%-20s %-10d %-10s %-10d %-10d %-10d %-10d %-10s\n",
                pokemon.getName() + star,
                pokemon.getLevel(),
                pokemon.getCurrentHp() + "/" + pokemon.getCurrentMaxHp(),
                pokemon.getCurrentAttack(),
                pokemon.getCurrentSpAtk(),
                pokemon.getCurrentSpDef(),
                pokemon.getCurrentSpeed(),
                pokemon.getType1() + type2);
        System.out.println("=".repeat(100));
        if (Species.getSpecies(p[slot].getName()).getEvolutionLevel() > 0) {
            System.out.println("Evolves at lvl. " + Species.getSpecies(p[slot].getName()).getEvolutionLevel());
            System.out.println("=".repeat(100));
        }
        System.out.println(" Moves:");
        System.out.println("--------");
        List<Move> availableMoves = pokemon.getMoves();
        for (int i = 0; i < availableMoves.size(); i++) {
            Move move = availableMoves.get(i);
            System.out.println((i + 1) + ". " + move.getName() + " | Dmg: " + move.getDamage() + " | " + move.getType() + " | PP: " + move.getCurrentPp() + "/" + move.getTotalPp() + " | " + move.getAccuracy() + "% accurate | Other effect: " + move.getOtherEffect());
        }
        System.out.println("=".repeat(100));
        Game.pressEnterToContinue(sc1);
    }
    public static void printParty(Arena arena) {
        if(User.hintMode== User.Hints.SHOW_ENGINE_CHOICES && arena != null && !checkIfEveryPkmHasFainted()) {
            System.out.println("=====================================================");
            System.out.printf("%-61s|%n", "|" + Fight.makeRecommendedSwitchStr(arena, false));
        }
        System.out.println("=====================================================");
        System.out.println("|                       Party                       |");
        System.out.println("=====================================================");
        for (int i = 0; i < p.length; i++) {
            if (p[i] != null) {
                System.out.printf(" %-14s lv. %-3d | HP %-20s %3d/%-3d%n",
                        (i + 1) + ") " + p[i].getName(),
                        p[i].getLevel(),
                        makeHealthBar(p[i], 10),
                        p[i].getCurrentHp(),
                        p[i].getCurrentMaxHp());
            }
        }
        System.out.println("=====================================================");
    }
    public static void printParty() {
        printParty(null);
    }
    public static String makeHealthBar(Pokemon pokemon, int length) {
        final String RESET = "\u001B[0m";
        final String RED = "\u001B[31m";
        final String GREEN = "\u001B[92m";
        final String YELLOW_GREEN = "\u001B[38;5;154m";
        final String YELLOW = "\u001B[93m";

        double maxHp = pokemon.getCurrentMaxHp();
        double currentHp = pokemon.getCurrentHp();
        double unroundedHpRatio =  (currentHp / maxHp);
        double hp = Math.round(unroundedHpRatio * length);

        String emptyStart = "⣏";
        String emptyEnd = "⣹";
        String full = "⣿";
        String empty = "⣉";

        String color = RESET;
        if (unroundedHpRatio < .2) color = RED;
        else if (unroundedHpRatio < .5) color = YELLOW;
        else if (unroundedHpRatio < .80) color = YELLOW_GREEN;
        else color = GREEN;

        StringBuilder bar = new StringBuilder();
        bar.append("[").append(color);
        for (int i = 0; i < length; i++) {
            if (i < hp) {
                bar.append(full);
            } else if (i == 0) {
                bar.append(emptyStart);
            } else if (i < length - 1) {
                bar.append(empty);
            } else {
                bar.append(emptyEnd);
            }
        }
        bar.append(RESET).append("]");
        return bar.toString();
    }
    public static int getPartySize() {
        int count = 0;
        for (Pokemon pokemon : p) {
            if (pokemon != null) {
                count++;
            }
        }
        return count;
    }
    public static Pokemon getPokemon(int index) {
        if (index >= 0 && index < p.length) {
            return p[index];
        }
        return null;
    }
    public static int getSwitchSlotFromUser(Arena arena, boolean automaticallyForcedToSwitch, Scanner sc1) {
        int slot;
        Pokemon selectedPokemon;

        // If all Pokémon have fainted --> immediately exit
        if (checkIfEveryPkmHasFainted()) return -2;
        if (automaticallyForcedToSwitch || p[0].getCurrentHp() == 0) {
            while (true) {
                slot = askToSwitchPokemonForced(arena, sc1);
                if(slot == -1) return -1;
                selectedPokemon = getPokemon(slot - 1);

                if (selectedPokemon == null || selectedPokemon.getCurrentHp() == 0) {
                    System.out.println("This Pokémon is either fainted or unavailable and cannot be switched.");
                    continue;
                }

                return slot - 1;
            }
        } else {
            while (true) {
                slot = askToSwitchPokemon(arena, sc1);
                if (slot == -1) {
                    System.out.println("Switching canceled.");
                    return -1;
                }

                selectedPokemon = getPokemon(slot - 1);

                if (selectedPokemon == null || selectedPokemon.getCurrentHp() == 0) {
                    System.out.println("This Pokémon is either fainted or unavailable and cannot be switched.");
                    continue;
                }

                return slot - 1;
            }
        }
    }
    public static boolean switchPokemonToSlot(int slot) throws InterruptedException {
        if (slot < 0 || slot >= p.length || p[slot] == null || p[slot].getCurrentHp() == 0) return false;

        swapActivePokemon(slot, p[slot]);
        Graphics.printPokemon(p[0].getName(), false, p[0].isShiny());
        System.out.println("Switched to " + p[0].getName() + "!\n");
        Sound.playSoundOnce("src/main/music/catchFail.wav");
        Thread.sleep(User.textSpeed);
        return true;
    }
    private static void swapActivePokemon(int slot, Pokemon selectedPokemon) {
        p[0].resetStages();
        p[slot] = p[0];
        p[0] = selectedPokemon;
    }
    public static int askToSwitchPokemon(Arena arena, Scanner sc1) {
        while (true) {
            printParty(arena);
            System.out.println("Enter a Pokémon (2-6) to switch to, or [C] to cancel:");

            String input = sc1.nextLine().trim(); // this clears the newline
            if (input.equalsIgnoreCase("C")) {
                return -1; // Return -1 to indicate cancellation!!!
            }
            try {
                int choiceSwitchPokemon = Integer.parseInt(input);
                if (choiceSwitchPokemon >= 2 && choiceSwitchPokemon <= 6) {
                    return choiceSwitchPokemon; // Return valid choice
                }
            } catch (NumberFormatException _) {
            }
            System.out.println("Invalid input! Please enter a number between 2-6 or [C] to cancel.");
            Game.pressEnterToContinue();
        }
    }
    public static int askToSwitchPokemonForced(Arena arena, Scanner sc1) {
        while (true) {
            printParty(arena);
            if (checkIfEveryPkmHasFaintedBesidesActive()) {
                System.out.println(p[0].getName() + " must stay in!");
                Game.pressEnterToContinue(sc1);
                break;
            }
            System.out.println("Enter a Pokémon (2-6) to switch to (you cannot cancel):");

            String input = sc1.nextLine().trim();
            try {
                int choiceSwitchPokemon = Integer.parseInt(input);
                if (choiceSwitchPokemon >= 2 && choiceSwitchPokemon <= 6) {
                    return choiceSwitchPokemon; // Return valid choice
                }
            } catch (NumberFormatException _) {
            }
            System.out.println("Invalid input! Please enter a number between 2-6.");
        }
        return -1;
    }
    //box interactions
    private static void sendToBox(Scanner sc1) throws InterruptedException {
        boolean hasBoxablePokemon = false;
        for (int i = 1; i < 6; i++) {
            if (p[i] != null) {
                hasBoxablePokemon = true;
                break;
            }
        }

        if (!hasBoxablePokemon) {
            System.out.println("\nNo Pokémon available to send to the box!\n");
            Game.pressEnterToContinue(sc1);
            return;
        }

        while (true) {
            try {
                printParty();
                System.out.println("\nChoose a Pokémon to send to the box (2-6) or [0] to cancel:");

                if (!sc1.hasNextInt()) {
                    System.out.println("Please enter a number.");
                    sc1.nextLine();
                    Thread.sleep(User.textSpeed);
                    continue;
                }

                int choice = sc1.nextInt();
                sc1.nextLine();

                // Handle cancellation
                if (choice == 0) {
                    System.out.println("Operation cancelled.");
                    Thread.sleep(User.textSpeed);
                    return;
                }

                // Validate input range
                if (choice < 1 || choice > 6) {
                    System.out.println("Please enter a number between 1-6.");
                    Thread.sleep(User.textSpeed);
                    continue;
                }

                // Handle active Pokémon
                if (choice == 1) {
                    System.out.println("\nYou cannot send your active Pokémon to the box!\n");
                    Thread.sleep(User.textSpeed);
                    continue;
                }

                int slot = choice - 1;

                // Check slot has Pokémon
                if (p[slot] == null) {
                    System.out.println("No Pokémon in this slot.");
                    Thread.sleep(User.textSpeed);
                    continue;
                }

                // Confirmation prompt
                System.out.printf("Send %s to the box? (Y/N) ", p[slot].getName());
                String confirm = sc1.nextLine().trim().toUpperCase();

                if (confirm.equals("Y") || confirm.equals("YES")) {
                    Box.addToBox(p[slot]);
                    System.out.printf("\n%s was sent to the box!\n", p[slot].getName());
                    p[slot] = null;
                    Thread.sleep(User.textSpeed);
                    return;
                } else {
                    System.out.println("Operation cancelled.");
                    Thread.sleep(User.textSpeed);
                    return;
                }

            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
                sc1.nextLine(); // Clear scanner buffer
                Thread.sleep(User.textSpeed);
            }
        }
    }
    public static void retrieveFromBox(Scanner sc1) throws InterruptedException{
        Pokemon retrievedPokemon = Box.getPokemonFromBox(sc1);
        if (retrievedPokemon != null) {
            addToParty(retrievedPokemon, sc1);
        }
    }
    //tools
    public static void levelUpEntirePartyByOne() throws InterruptedException {
        Sound.playSoundOnce("src/main/music/levelUp.wav");
        for (Pokemon pokemon : p) {
            if (pokemon != null) {
                pokemon.levelUpPokemonTwoThirdsChance();
            }
        }
        System.out.println("Your party gained experience!\n");
        Thread.sleep((long) (User.textSpeed * .75));
    }
    public static void clearParty() {
        for (int i = 0; i < p.length; i++) {
            p[i] = null;
        }
    }
    public static boolean checkIfEveryPkmHasFainted() {
        for (Pokemon pokemon : p) {
            if (pokemon != null && pokemon.getCurrentHp() > 0) {
                return false;
            }
        }
        return true;
    }
    public static boolean checkIfEveryPkmHasFaintedBesidesActive() {
        if (p[0].getCurrentHp() == 0) return false;

        for (int i = 1; i < 6; i++) {
            if (p[i] != null && p[i].getCurrentHp() > 0) {
                return false;
            }
        }

        return true;
    }
    public static void healParty() {
        for (Pokemon pokemon : p) {
            if (pokemon != null) {
                Pokemon.healPokemon(pokemon);
            }
        }
    }
    public static void resetPartyAfterBattle() {
        for (Pokemon pokemon : p) {
            if (pokemon != null) {
                Pokemon.resetPokemonsNonHpStats(pokemon);
            }
        }
    }
    public static int getAvgPLvl() {
        int totalLevel = 0;
        int partySize = getPartySize();

        if (partySize == 0) return 0; // Avoid division by zero

        for (Pokemon pokemon : p) {
            if (pokemon != null) {
                totalLevel += pokemon.getLevel();
            }
        }

        return totalLevel / partySize;
    }
}