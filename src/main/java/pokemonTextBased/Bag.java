package pokemonTextBased;

import java.util.*;
//battle items bag printed twice, notes menu buggy w/ pressing enter to continue
public class Bag {
    //money
    private static long pokedollars = 500;
    private static long BP = 0;
    private static long debt = 0;
    private static int goldBars = 0;
    public static HashMap<String, Integer> stockPortfolio = new HashMap<>();
    //items
    private static final Map<String, Integer> battleItems = new HashMap<>();
    static {
        battleItems.put("Pokeball", 5);
        battleItems.put("Potion", 5);
        battleItems.put("Super Potion", 1);
        battleItems.put("Hyper Potion", 1);
        battleItems.put("Revive", 1);
        battleItems.put("Full Heal", 1); //non-working currently
    }
    private static final Map<String, Integer> specialItems = new HashMap<>();
    static {
        specialItems.put("Rare Candy", 1); // Start with 1 Rare Candy
    }
    //notes
    private static final Map<String, String[]> referenceNotes = new HashMap<>();
    static {
        referenceNotes.put("GYM TIPS", new String[] {
                "~~~~~~~~~~~~~~~~~~~~~~ GYM TIPS ~~~~~~~~~~~~~~~~~~~~~~",
                "~~~~~~~~~~~~~~ A ROOKIE TRAINER'S GUIDE ~~~~~~~~~~~~~~",
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~",
                "1) Gym Leaders specialize in one type. Use that to",
                " your advantage!",
                "2) Make sure you're prepared before battling. That",
                " means having healing items for your Pokemon and",
                " don't forget about bringing a good attitude for",
                " yourself! No one likes a sore winner or loser.",
                "3) Check your party and moves before battling! Don't",
                " be ill-equipped.",
                "4) There are 8 gyms and 8 corresponding badges that you",
                " must collect in order to enter the Pokemon League.",
                "5) There are also other gyms in the region that are",
                " not part of the official League. Beating those gyms is",
                " not required for League entrance.",
                " ",
                " 6) The offical Pokemon League Gym Leaders are:",
                " Brock, Misty, Lt. Surge, Erika, Koga, Sabrina, ",
                " Blaine, and Blue",
                " ",
                " Beat those powerful trainers to earn your gym badges!",
                " ",
                " Good luck on your quest to becoming CHAMPION!",
                "                                     -R. SAKAKI",
                "            Printed by Silph Co., c 1996, revised 1998",
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~",
        });

        referenceNotes.put("POKEMON CARE", new String[] {
                "Pokémon heal at Pokémon Centers for free.",
                "Use Potions in battle when needed.",
                "Status conditions can be cured with Full Heal."
        });

        referenceNotes.put("COLOSSEUM TIPS", new String[] {
                "~~~~~~~~~~~~~~~~~~~ COLOSSEUM TIPS ~~~~~~~~~~~~~~~~~~~",
                "~~~~~~~~~~~~~~ A ROOKIE TRAINER'S GUIDE ~~~~~~~~~~~~~~",
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~",
                "1) Opponents will always have parties that are about",
                " evenly matched with yours!",
                "2) Try to win as many battles in a row as you can!",
                "3) You will earn Battle Points (BP) with every trainer",
                " that you beat. The longer your win streak, the more",
                " BP you earn per win!",
                "4) Come visit the Help Desk to claim prizes with your",
                " BP!",
                "5) Once you achieve a record of 25 trainers beaten,",
                " you may procure one Pokemon from each trainer that ",
                " you defeat in THE COLOSSEUM.",
                "                                     -G. SAKAKI",
                "              Printed by LAUNCH Enterprises Co. 1998",
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~",
        });
    }
    private static Map<String, String[]> notes = new HashMap<>();
    public static boolean addNote(String noteKey, Scanner sc1) {
        if (referenceNotes.containsKey(noteKey) && !notes.containsKey(noteKey)) {
            String[] noteContent = referenceNotes.get(noteKey);
            String[] copy = new String[noteContent.length];
            System.arraycopy(noteContent, 0, copy, 0, noteContent.length);

            notes.put(noteKey, copy);
            System.out.println(noteKey + " has been added to your notes pocket.");
            Sound.playSoundOnce("src/main/music/gotItem.mp3");
            Game.pressEnterToContinue(sc1);
            return true;
        }
        if (notes.containsKey(noteKey)) {
            System.out.println("You already have a copy of " + noteKey + " in your bag.");
            Game.pressEnterToContinue(sc1);
        }
        return false;
    }
    public static void openNotesPocket(Scanner sc1) throws InterruptedException {
        if (notes.isEmpty()) {
            System.out.println("\nYour notes pocket is empty.");
            System.out.println("You'll collect notes as you journey through the Kanto region.\n");
            Game.pressEnterToContinue(sc1);
            return;
        }

        String choice;
        do {
            // Display header
            Graphics.printBag();
            System.out.println("=========================================");
            System.out.println("                   Notes                 ");
            System.out.println("=========================================");

            // List all available notes with numbers
            int index = 1;
            Map<Integer, String> noteIndexMap = new HashMap<>();
            for (String noteTitle : notes.keySet()) {
                System.out.printf("[%d] %s\n", index, noteTitle);
                noteIndexMap.put(index++, noteTitle);
            }

            System.out.println("\n[B] Back to Bag");
            System.out.println("=========================================");

            choice = sc1.nextLine().trim().toUpperCase();

            if (choice.equals("B")) {
                break;
            }

            try {
                int noteChoice = Integer.parseInt(choice);
                if (noteIndexMap.containsKey(noteChoice)) {
                    String selectedNote = noteIndexMap.get(noteChoice);
                    displayNote(selectedNote, sc1);
                } else {
                    System.out.println("Invalid note number. Please try again.");
                    Game.pressEnterToContinue(sc1);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number or [B] to go back.");
                Game.pressEnterToContinue(sc1);
            }

        } while (!choice.equals("B"));
    }
    private static void displayNote(String noteTitle, Scanner scanner) throws InterruptedException {
        String[] noteContent = notes.get(noteTitle);
        for (String line : noteContent) {
            System.out.println(line);
            Thread.sleep(50); // Small delay
        }
        Game.pressEnterToContinue(scanner);
    }
    //general
    public static void openBagMenu(Scanner sc1) throws InterruptedException{
        String choice = "";
        do {
            Graphics.printBag();
            System.out.println("=========================================");
            System.out.println("                    Bag                  ");
            System.out.println("=========================================");
            System.out.println("[1] Battle Items Pocket");
            System.out.println("[2] Special Items Pocket");
            System.out.println("[3] Notes Pocket");
            System.out.println("[B] Back");
            System.out.println("=========================================");
            choice = sc1.nextLine().trim().toUpperCase();
            switch (choice) {
                case "1": openBattlePocketInMenu(sc1); break;
                case "2": openSpecialItemsPocket(sc1); break;
                case "3": openNotesPocket(sc1); break;
            }
        } while(!choice.equalsIgnoreCase("B"));
    }
    //special items
    public static void openSpecialItemsPocket(Scanner sc1) throws InterruptedException {
        printSpecialItems();

        List<String> availableItems = new ArrayList<>();
        int index = 1;
        for (String item : specialItems.keySet()) {
            if (specialItems.get(item) > 0) {
                System.out.println("[" + index + "] Use " + item + " (" + specialItems.get(item) + ")");
                availableItems.add(item);
                index++;
            }
        }
        System.out.println("[B] Back");
        System.out.println("==========================================");

        String selection;
        do {
            selection = sc1.nextLine().toUpperCase().trim();
            if (selection.equals("B")) {
                return;
            }

            try {
                int selectionIndex = Integer.parseInt(selection) - 1;
                if (selectionIndex >= 0 && selectionIndex < availableItems.size()) {
                    useSpecialItem(availableItems.get(selectionIndex), sc1);
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number or 'B' to go back.");
            }
        } while (true);
    }
    private static void printSpecialItems() {
        Graphics.printBag();
        System.out.println("==========================================");
        System.out.println("               Special Items              ");
        System.out.println("==========================================");
    }
    private static void useSpecialItem(String item, Scanner sc1) throws InterruptedException {
        switch (item) {
            case "Rare Candy":
                useRareCandy(sc1);
                break;
            case "Mystery Egg":
                useMysteryEgg(sc1);
                break;
            case "Fire Stone":
            case "Thunder Stone":
            case "Water Stone":
                useEvoStone(item, sc1);
                break;
            default:
                System.out.println("Can't use that item right now.");
        }
    }
    private static void useMysteryEgg(Scanner sc1) throws InterruptedException{
        String choice = "";
        while (true) {
            Graphics.printMysteryEgg();
            choice = sc1.nextLine().trim().toUpperCase();

            ArrayList<String> possibleSpecies = new ArrayList<>();

            System.out.println("Hatch Mystery Egg? (Y/N) | You have: " + Bag.specialItems.get("Mystery Egg"));
            if (choice.equals("Y")) {
                Sound.playSoundOnce("src/main/music/openEgg.mp3");
                for (String pkm : Species.speciesListedInPokedexOrder) {
                    Species spc = Species.getSpecies(pkm);
                    int evoLvl = spc.getEvolutionLevel();
                    if (evoLvl > 0 && evoLvl < 32) {
                        possibleSpecies.add(pkm);
                    }
                }
                Thread.sleep(2500);
                Random rand = new Random();
                String randomSpeciesStr = possibleSpecies.get(rand.nextInt(possibleSpecies.size()));
                Pokemon randomPkm = new Pokemon(randomSpeciesStr, User.checkLevelCap() / 2);
                Graphics.printPokemon(randomPkm);
                System.out.println("Wow! You hatched a " + randomSpeciesStr + "!");
                Party.addToParty(randomPkm, sc1);
                Bag.specialItems.put("Mystery Egg", Bag.specialItems.get("Mystery Egg") - 1);
            }
            else if (choice.equals("N")) {
                break;
            }
            else {
                System.out.println("Invalid input.");
            }
        }
    }

    private static void useEvoStone(String item, Scanner sc1) throws InterruptedException {
        Pokemon selectedPkm = null;
        while (true) {
            System.out.println("\nSelect a Pokémon to use " + item + " on:");
            for (int i = 0; i < Party.getPartySize(); i++) {
                Pokemon pkm = Party.getPokemon(i);
                System.out.printf("[%d] %s (Lv. %d)%n",
                        i + 1,
                        pkm.getName(),
                        pkm.getLevel());
            }
            System.out.println("[C] Cancel");

            String input = sc1.nextLine().toUpperCase().trim();
            if (input.equals("C")) {
                return;
            }

            try {
                int index = Integer.parseInt(input) - 1;
                if (index >= 0 && index < Party.getPartySize()) {
                    selectedPkm = Party.getPokemon(index);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                Game.pressEnterToContinue(sc1);
            }
            if (selectedPkm.getName().equalsIgnoreCase("Eevee")) {
                Bag.specialItems.put(item, Bag.specialItems.get(item) - 1);
                Pokemon.evolveEevee(selectedPkm, item);
                break;
            }
            else {
                System.out.println("Cannot use " + item + " on " + selectedPkm.getName() + ".");
                Game.pressEnterToContinue(sc1);
            }
        }
    }
    private static void useRareCandy(Scanner sc1) throws InterruptedException {
        int candyCount = specialItems.getOrDefault("Rare Candy", 0);
        if (candyCount <= 0) {
            System.out.println("You don't have any Rare Candies!");
            Game.pressEnterToContinue(sc1);
            return;
        }

        Pokemon selectedPkm = null;
        // Pokémon selection loop
        while (true) {
            System.out.println("\nSelect a Pokémon to use Rare Candies on:");
            for (int i = 0; i < Party.getPartySize(); i++) {
                Pokemon pkm = Party.getPokemon(i);
                System.out.printf("[%d] %s (Lv. %d)%n",
                        i + 1,
                        pkm.getName(),
                        pkm.getLevel());
            }
            System.out.println("[C] Cancel");

            String input = sc1.nextLine().toUpperCase().trim();
            if (input.equals("C")) {
                return;
            }

            try {
                int index = Integer.parseInt(input) - 1;
                if (index >= 0 && index < Party.getPartySize()) {
                    selectedPkm = Party.getPokemon(index);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                Game.pressEnterToContinue(sc1);
            }
        }

        if(selectedPkm == null) {
            System.out.println("No pokemon in that slot.");
            Game.pressEnterToContinue(sc1);
            return;
        }

        // Check level cap
        int levelCap = User.checkLevelCap();
        if (selectedPkm.getLevel() >= levelCap) {
            System.out.printf("%s is already at the current level cap (lv. %d)!%n",
                    selectedPkm.getName(), levelCap);
            Game.pressEnterToContinue(sc1);
            if (Pokemon.checkIfPokemonCanEvolve(selectedPkm)) {
                System.out.println("Used 1 rare candy to evolve " + selectedPkm.getName() + "!");
                Game.pressEnterToContinue(sc1);
                Pokemon.tryEvolvePokemon(selectedPkm);
            }
            return;
        }
        if (selectedPkm.getCurrentHp() <= 0) {
            System.out.printf("%s cannot level up because it is fainted!\n",
                    selectedPkm.getName());
            Game.pressEnterToContinue(sc1);
            return;
        }

        // Calculate maximum usable candies
        int maxPossible = levelCap - selectedPkm.getLevel();
        int maxAvailable = Math.min(candyCount, maxPossible);

        // Candy amount selection loop
        while (true) {
            System.out.printf("\n%s is Lv. %d (Cap: %d)%n",
                    selectedPkm.getName(), selectedPkm.getLevel(), levelCap);
            System.out.printf("You have %d Rare Candies. How many to use? (1-%d, C to cancel)%n",
                    candyCount, maxAvailable);

            String input = sc1.nextLine().toUpperCase().trim();
            if (input.equals("C")) {
                System.out.println("Cancelled using Rare Candy.");
                Game.pressEnterToContinue(sc1);
                return;
            }

            try {
                int amount = Integer.parseInt(input);
                if (amount <= 0) {
                    System.out.println("Please enter a positive number.");
                    Game.pressEnterToContinue(sc1);
                    continue;
                }

                // Auto-adjust to maximum if input exceeds limit
                int actualAmount = Math.min(amount, maxAvailable);
                if (amount > maxAvailable) {
                    System.out.printf("Adjusting to use %d candies (maximum possible).%n", maxAvailable);
                }

                // Final confirmation
                System.out.printf("Use %d Rare Candy on %s (Lv. %d → Lv. %d)? (Y/N)%n",
                        actualAmount,
                        selectedPkm.getName(),
                        selectedPkm.getLevel(),
                        selectedPkm.getLevel() + actualAmount);

                String confirm = sc1.nextLine().toUpperCase().trim();
                if (confirm.equals("Y") || confirm.equals("YES")) {
                    // Apply level ups
                    for (int i = 0; i < actualAmount; i++) {
                        selectedPkm.levelUpPokemon();
                    }

                    // Update inventory
                    specialItems.put("Rare Candy", candyCount - actualAmount);

                    // Feedback
                    Sound.playSoundOnce("src/main/music/levelUp.mp3");
                    System.out.printf("%s grew to Lv. %d!%n",
                            selectedPkm.getName(), selectedPkm.getLevel());
                    System.out.printf("You have %d Rare Candies remaining.%n", candyCount - actualAmount);
                    Game.pressEnterToContinue(sc1);
                    return;
                } else {
                    System.out.println("Cancelled using Rare Candies.");
                    Game.pressEnterToContinue(sc1);
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                Game.pressEnterToContinue(sc1);
            }
        }
    }
    //wild encounter
    public static boolean openBattlePocketWildEncounter(Arena arena, Scanner sc1) throws InterruptedException {
        String bagSelection;
        do {
            printBattleItems();
            List<String> availableItems = new ArrayList<>();

            int index = 1;
            for (String item : battleItems.keySet()) {
                if (battleItems.get(item) > 0) {
                    System.out.println("[" + index + "] Use " + item + " (" + getItemCount(item) + ")");
                    availableItems.add(item);
                    index++;
                }
            }
            System.out.println("[B] Back");
            System.out.println("==========================================");
            bagSelection = sc1.nextLine().toUpperCase().trim();
            if (bagSelection.equals("B")) {
                return false;
            }
            try {
                int selectionIndex = Integer.parseInt(bagSelection) - 1;
                if (selectionIndex >= 0 && selectionIndex < availableItems.size()) {
                    return useBattleItemWildEncounter(arena, availableItems.get(selectionIndex), sc1);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input.");
            }
        } while (true);
    }
    public static void printBattleItems() {
        Graphics.printBag();
        System.out.println("==========================================");
        System.out.println("               Battle Items               ");
        System.out.println("==========================================");
    }
    public static boolean useBattleItemWildEncounter(Arena arena, String item, Scanner sc1) throws InterruptedException {
        if (battleItems.getOrDefault(item, 0) > 0) {
            if (item.equals("Potion") || item.equals("Super Potion") || item.equals("Hyper Potion")) {
                while (true) {
                    System.out.println("Select a Pokémon to use the " + item + " on:");
                    for (int i = 0; i < Party.getPartySize(); i++) {
                        System.out.println("[" + (i + 1) + "] " + Party.getPokemon(i).getName() +
                                " (HP: " + Party.getPokemon(i).getCurrentHp() + "/" + Party.getPokemon(i).getCurrentMaxHp() + ")");
                    }
                    System.out.println("[C] Cancel");

                    String selection = sc1.nextLine().toUpperCase().trim();
                    if (selection.equals("C")) {
                        return openBattlePocketWildEncounter(arena, sc1);
                    }

                    try {
                        int index = Integer.parseInt(selection) - 1;
                        if (index >= 0 && index < Party.getPartySize() && Party.p[index].getCurrentHp() != 0 && Party.p[index].getCurrentHp()/Party.p[index].getCurrentMaxHp() != 1) {
                            Pokemon selectedPkm = Party.getPokemon(index);
                            int healAmount = item.equals("Potion") ? 20 :
                                    item.equals("Super Potion") ? 60 : 120;
                            selectedPkm.setCurrentHp(selectedPkm.getCurrentHp() + healAmount);
                            battleItems.put(item, battleItems.get(item) - 1);
                            System.out.println(selectedPkm.getName() + " was healed by " + healAmount + " HP!\n");
                            Thread.sleep(User.textSpeed);
                            return true;
                        }
                        else if (Party.p[index].getCurrentHp() == 0){
                            System.out.println("Cannot use " + item + " because " + Party.p[index].getName() + " is fainted!\n");
                            Thread.sleep(User.textSpeed);
                        }
                        else if (Party.p[index].getCurrentHp()/Party.p[index].getCurrentMaxHp() == 1){
                            System.out.println("Cannot use " + item + " because " + Party.p[index].getName() + " has full health!\n");
                            Thread.sleep(User.textSpeed);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Input.");
                    }
                }
            } else if (item.equals("Revive")) {
                while (true) {
                    System.out.println("Select a fainted Pokémon to use the " + item + " on:");
                    List<Pokemon> faintedPokemon = new ArrayList<>();
                    for (int i = 0; i < Party.getPartySize(); i++) {
                        Pokemon pkm = Party.getPokemon(i);
                        if (pkm.getCurrentHp() == 0) {
                            faintedPokemon.add(pkm);
                            System.out.println("[" + (faintedPokemon.size()) + "] " + pkm.getName());
                        }
                    }
                    if (faintedPokemon.isEmpty()) {
                        System.out.println("No fainted Pokémon.");
                        return openBattlePocketWildEncounter(arena, sc1);
                    }

                    System.out.println("[C] Cancel");
                    String selection = sc1.nextLine().toUpperCase().trim();
                    if (selection.equals("C")) {
                        return openBattlePocketWildEncounter(arena, sc1);
                    }

                    try {
                        int index = Integer.parseInt(selection) - 1;
                        if (index >= 0 && index < faintedPokemon.size()) {
                            Pokemon selectedPkm = faintedPokemon.get(index);
                            selectedPkm.setCurrentHp(selectedPkm.getCurrentMaxHp() / 2);  // Revives to 50% HP
                            battleItems.put("Revive", battleItems.get("Revive") - 1);
                            System.out.println(selectedPkm.getName() + " was revived with 50% HP!");
                            Thread.sleep((long)(.75*User.textSpeed));
                            return true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Input.");
                    }
                }
            } else if (item.equals("Full Heal")) {
                while (true) {
                    System.out.println("Select a Pokémon to use the " + item + " on:");
                    for (int i = 0; i < Party.getPartySize(); i++) {
                        Pokemon pkm = Party.getPokemon(i);
                        System.out.println("[" + (i + 1) + "] " + pkm.getName() +
                                " (Status: " + pkm.getStatusCondition() + ")");
                    }
                    System.out.println("[C] Cancel");

                    String selection = sc1.nextLine().toUpperCase().trim();
                    if (selection.equals("C")) {
                        return openBattlePocketWildEncounter(arena, sc1);
                    }

                    try {
                        int index = Integer.parseInt(selection) - 1;
                        if (index >= 0 && index < Party.getPartySize()) {
                            Pokemon selectedPkm = Party.getPokemon(index);
                            if (selectedPkm.getStatusCondition().equals("None")) {
                                System.out.println(selectedPkm.getName() + " is already healthy!");
                                Thread.sleep((long)(.75*User.textSpeed));
                                return openBattlePocketWildEncounter(arena, sc1);
                            } else {
                                selectedPkm.setStatusCondition("None");
                                battleItems.put("Full Heal", battleItems.get("Full Heal") - 1);
                                System.out.println(selectedPkm.getName() + " was cured of its status condition!");
                                Thread.sleep((long)(.75*User.textSpeed));
                                return true;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Input.");
                    }
                }
            } else if (item.equals("Pokeball")) {
                battleItems.put(item, battleItems.get(item) - 1);
                throwPokeball(arena, sc1);
                return true;
            }
        } else {
            System.out.println("You have no " + item + " left!");
        }
        return false;
    }
    public static void throwPokeball(Arena arena, Scanner sc1) throws InterruptedException {
        Random rand = new Random();
        int chance = getCatchOdds(arena);
        boolean success = chance < 90;
        Graphics.printFlyingPokeball();
        Sound.playSoundOnce("src/main/music/throwBall.mp3");
        System.out.println("You threw a Pokeball.");
        Thread.sleep((long) (.75 * User.textSpeed));
        Graphics.printLandedPokeball();
        Sound.playSoundOnce("src/main/music/ballShake.mp3");
        System.out.println("The Pokeball landed on the ground.");
        Thread.sleep(User.textSpeed);
        Graphics.printAltLandedPokeball();
        Sound.playSoundOnce("src/main/music/ballShakeAgain.mp3");
        System.out.println("It shook once!");
        Thread.sleep((long) (.75 * User.textSpeed));
        if(rand.nextInt(0,2) == 1) {
            Graphics.printLandedPokeball();
            Sound.playSoundOnce("src/main/music/ballShakeAgain.mp3");
            System.out.println("It shook twice!");
            Thread.sleep((long) (.75 * User.textSpeed));
            if(rand.nextInt(0,2) == 1) {
                Graphics.printAltLandedPokeball();
                Sound.playSoundOnce("src/main/music/ballShakeAgain.mp3");
                System.out.println("It shook for a third time!");
                Thread.sleep((long) (.75 * User.textSpeed));
            }
        }
        catchWildPkm(arena, sc1, success);
    }
    public static int getCatchOdds(Arena arena) {
        Random rand = new Random();
        return rand.nextInt(0, 100) + (int) (50.0 * arena.fp[0].getCurrentHp()/arena.fp[0].getCurrentMaxHp() * (arena.fp[0].getBST()/250.0));
    } //rework to be more inuitive
    //trainer encounter/regular usage
    public static boolean openBattlePocketInMenu(Scanner sc1) throws InterruptedException {
        while (true) {
            printBattleItems();
            List<String> availableItems = new ArrayList<>();
            int index = 1;
            for (String item : battleItems.keySet()) {
                if (battleItems.get(item) > 0) {
                    System.out.println("[" + index + "] Use " + item + " (" + getItemCount(item) + ")");
                    availableItems.add(item);
                    index++;
                }
            }
            System.out.println("[B] Back");
            System.out.println("==========================================");

            String bagSelection = sc1.nextLine().toUpperCase().trim();
            if (bagSelection.equals("B")) {
                return false;
            }
            try {
                int selectionIndex = Integer.parseInt(bagSelection) - 1;
                if (selectionIndex >= 0 && selectionIndex < availableItems.size()) {
                    boolean itemUsed = useItemTrainerEncounter(availableItems.get(selectionIndex), sc1);
                    if (!itemUsed) {
                        continue; // if the player cancels --> reopen the bag
                    }
                    return true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input.");
            }
        }
    }
    public static boolean openBattlePocketTrainerEncounter(Scanner sc1) throws InterruptedException {
        if(User.difficultyMode == User.Difficulty.PROFESSIONAL) {
            System.out.println("You're a professional! No items allowed in trainer battles!");
            Game.pressEnterToContinue(sc1);
            return false;
        }
        return openBattlePocketInMenu(sc1);
    }
    public static boolean useItemTrainerEncounter(String item, Scanner sc1) throws InterruptedException {
        while (true) {
            if (battleItems.getOrDefault(item, 0) > 0) {
                if (item.equals("Potion") || item.equals("Super Potion") || item.equals("Hyper Potion")) {
                    System.out.println("Select a Pokémon to use the " + item + " on:");
                    for (int i = 0; i < Party.getPartySize(); i++) {
                        System.out.println("[" + (i + 1) + "] " + Party.getPokemon(i).getName() +
                                " (HP: " + Party.getPokemon(i).getCurrentHp() + "/" + Party.getPokemon(i).getCurrentMaxHp() + ")");
                    }
                    System.out.println("[C] Cancel");

                    String selection = sc1.nextLine().toUpperCase().trim();
                    if (selection.equals("C")) {
                        return false; // false reopens bag
                    }

                    try {
                        int index = Integer.parseInt(selection) - 1;
                        if (index >= 0 && index < Party.getPartySize() && Party.p[index].getCurrentHp() != 0 && Party.p[index].getCurrentHp()/Party.p[index].getCurrentMaxHp() != 1) {
                            Pokemon selectedPkm = Party.getPokemon(index);
                            int healAmount = item.equals("Potion") ? 20 :
                                    item.equals("Super Potion") ? 60 : 120;
                            selectedPkm.setCurrentHp(selectedPkm.getCurrentHp() + healAmount);
                            battleItems.put(item, battleItems.get(item) - 1);
                            System.out.println(selectedPkm.getName() + " was healed by " + healAmount + " HP!\n");
                            Thread.sleep(User.textSpeed);
                            return true;
                        }
                        else if (Party.p[index].getCurrentHp() == 0){
                            System.out.println("Cannot use " + item + " because " + Party.p[index].getName() + " is fainted!\n");
                            Thread.sleep(User.textSpeed);
                        }
                        else if (Party.p[index].getCurrentHp()/Party.p[index].getCurrentMaxHp() == 1){
                            System.out.println("Cannot use " + item + " because " + Party.p[index].getName() + " has full health!\n");
                            Thread.sleep(User.textSpeed);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Input.");
                    }
                } else if (item.equals("Revive")) {
                    while (true) {
                        System.out.println("Select a fainted Pokémon to use the " + item + " on:");
                        List<Pokemon> faintedPokemon = new ArrayList<>();
                        for (int i = 0; i < Party.getPartySize(); i++) {
                            Pokemon pkm = Party.getPokemon(i);
                            if (pkm.getCurrentHp() == 0) {
                                faintedPokemon.add(pkm);
                                System.out.println("[" + (faintedPokemon.size()) + "] " + pkm.getName());
                            }
                        }
                        if (faintedPokemon.isEmpty()) {
                            System.out.println("No fainted Pokémon.");
                            return false; // No fainted Pokémon, can't use Revive
                        }

                        System.out.println("[C] Cancel");
                        String selection = sc1.nextLine().toUpperCase().trim();
                        if (selection.equals("C")) {
                            return false; // Cancel, reopen bag
                        }

                        try {
                            int index = Integer.parseInt(selection) - 1;
                            if (index >= 0 && index < faintedPokemon.size()) {
                                Pokemon selectedPkm = faintedPokemon.get(index);
                                selectedPkm.setCurrentHp(selectedPkm.getCurrentMaxHp() / 2);  // Revives to 50% HP
                                battleItems.put("Revive", battleItems.get("Revive") - 1);
                                System.out.println(selectedPkm.getName() + " was revived with 50% HP!");
                                Thread.sleep((long)(.75*User.textSpeed));
                                return true;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid Input.");
                        }
                    }
                }
                else if (item.equals("Full Heal")) {
                    while (true) {
                        System.out.println("Select a Pokémon to use the " + item + " on:");
                        for (int i = 0; i < Party.getPartySize(); i++) {
                            Pokemon pkm = Party.getPokemon(i);
                            System.out.println("[" + (i + 1) + "] " + pkm.getName() +
                                    " (Status: " + pkm.getStatusCondition() + ")");
                        }
                        System.out.println("[C] Cancel");

                        String selection = sc1.nextLine().toUpperCase().trim();
                        if (selection.equals("C")) {
                            return openBattlePocketTrainerEncounter(sc1);
                        }

                        try {
                            int index = Integer.parseInt(selection) - 1;
                            if (index >= 0 && index < Party.getPartySize()) {
                                Pokemon selectedPkm = Party.getPokemon(index);
                                if (selectedPkm.getStatusCondition().equals("None")) {
                                    System.out.println(selectedPkm.getName() + " is already healthy!");
                                    Thread.sleep((long)(.75*User.textSpeed));
                                    return openBattlePocketTrainerEncounter(sc1);
                                } else {
                                    selectedPkm.setStatusCondition("None");
                                    battleItems.put("Full Heal", battleItems.get("Full Heal") - 1);
                                    System.out.println(selectedPkm.getName() + " was cured of its status condition!");
                                    Thread.sleep((long)(.75*User.textSpeed));
                                    return true;
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid Input.");
                        }
                    }
                }
                else if (item.equals("Pokeball")) {
                    System.out.println("Can't use that right now.");
                    Thread.sleep(User.textSpeed);
                    return false;
                }
            } else {
                System.out.println("You have no " + item + " left!");
                return false;
            }
        }
    }
    //general
    public static void addItem(String item, int amount) throws InterruptedException {
        if (amount > 0) {
            battleItems.put(item, battleItems.getOrDefault(item, 0) + amount);
            System.out.println(amount + " " + item + "(s) added to your bag.");
            Sound.playSoundOnce("src/main/music/gotItem.mp3");
            Game.pressEnterToContinue();
        } else {
            System.out.println("Invalid amount for " + item + ".");
        }
    }
    public static void adjustPokedollarBalance(long amount) {
        pokedollars += amount;
        System.out.println("Your new balance is " + pokedollars + " Pokedollars.\n");
        if(amount>0) Sound.playSoundOnce("src/main/music/glitter.mp3");
    }
    public static void spendPokedollars(long pokedollarsSpent) {
        if (pokedollars >= pokedollarsSpent) {
            pokedollars -= pokedollarsSpent;
            System.out.println("You spent " + pokedollarsSpent + " Pokedollars.");
        } else {
            System.out.println("Not enough Pokedollars!");
        }
    }
    public static void spendPokedollarsSilent(long pokedollarsSpent) {
        if (pokedollars >= pokedollarsSpent) {
            pokedollars -= pokedollarsSpent;
        } else {
            System.out.println("Not enough Pokedollars!");
        }
    }
    public static void losePokedollars(int pokedollarsLost) {
        pokedollars = Math.max(0, pokedollars - pokedollarsLost);
        System.out.println("You lost " + pokedollarsLost + " Pokedollars.\n");
    }
    public static int getItemCount(String item) {
        if (battleItems.containsKey(item)) {
            return battleItems.get(item);
        } else if (specialItems.containsKey(item)) {
            return specialItems.get(item);
        }
        return 0;
    }
    public static long getPokedollars() {
        return pokedollars;
    }
    public static void addSpecialItem(String item, int amount) throws InterruptedException{
        if (amount > 0) {
            specialItems.put(item, specialItems.getOrDefault(item, 0) + amount);
            System.out.println(amount + " " + item + "(s) added to your bag.\n");
            Sound.playSoundOnce("src/main/music/gotItem.mp3");
            Game.pressEnterToContinue();
        } else {
            System.out.println("Invalid amount for " + item + ".\n");
        }
    }

    //catch logic
    public static void catchWildPkm(Arena arena, Scanner sc1, boolean success) throws InterruptedException {
        if (success) {
            Sound.stopAllSounds();
            Graphics.printStarPokeball();
            System.out.println("You caught the wild " + arena.fp[0].getName() + "!");
            Sound.playSoundOnce("src/main/music/caughtJingle.mp3");
            Thread.sleep(2L *User.textSpeed);
            Sound.playMusicOnLoop("src/main/music/victoryVsWildPkmTheme.mp3");
            Graphics.printHeldPokeball();
            Party.addToParty(arena.fp[0], sc1);
            arena.isCaught = true;
        } else {
            Graphics.printOpenedPokeball();
            Sound.playSoundOnce("src/main/music/catchFail.mp3");
            System.out.println("\nThe Pokémon broke free!\n");
            Thread.sleep((long) (User.textSpeed * .75));
        }
    } // 50/50 pokeball rng currently

    //banking and stocks
    public static int getNumGoldBars() {
        return goldBars;
    }
    public static void addGoldBars(int changeGoldBars) {
        Bag.goldBars += changeGoldBars;
        if(changeGoldBars>0) Sound.playSoundOnce("src/main/music/glitter.mp3");
    }
    public static long getDebt() {
        return debt;
    }
    public static void addDebt(long debt) {
        Bag.debt += debt;
    }
    public static void addStock(String stockName, int amount) {
        stockPortfolio.put(stockName, stockPortfolio.getOrDefault(stockName, 0) + amount);
        if (stockPortfolio.get(stockName) <= 0) {
            stockPortfolio.remove(stockName);
        }
        if(amount>0) Sound.playSoundOnce("src/main/music/glitter.mp3");
    }
    public static int getStockCount(String stockName) {
        return stockPortfolio.getOrDefault(stockName, 0);
    }
    //BP
    public static long getBP() {
        return BP;
    }
    public static void earnBP(int BPGain) {
        BP += BPGain;
        System.out.println("You earned " + BPGain + " BP! (You have: " + BP + " total BP)");
        Sound.playSoundOnce("src/main/music/glitter.mp3");
        Game.pressEnterToContinue();
    }
    public static void spendBP(int BPLoss) {
        BP -= BPLoss;
        System.out.println("You spent " + BPLoss + " BP! (You have: " + BP + " total BP)");
        Game.pressEnterToContinue();
    }
    public static void setBP(long BP) {
        Bag.BP = BP;
    }
}

