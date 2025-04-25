package pokemonTextBased;

import java.util.*;

public class User {
    //general
    public static String username = "Cyan";

    public enum Gender {
        BOY("boy"),
        GIRL("girl");
        public final String genderString;

        Gender(String genderString) {
            this.genderString = genderString;
        }
    }
    public static Gender gender = Gender.BOY;
    public static int textSpeed = 2000;
    public enum Difficulty{
        EASY("EASY"),
        NORMAL("NORMAL"),
        CHALLENGE("CHALLENGE"),
        PROFESSIONAL("PROFESSIONAL");
        public final String str;
        Difficulty(String str) {
            this.str = str;
        }
        public String getStr(){
            return this.str;
        }
    }
    public static Difficulty difficultyMode = Difficulty.CHALLENGE;
    public enum Hints {
        NO_HINTS("NO_HINTS", "NO HINTS"),
        SHOW_EFFECTIVENESS("SHOW_EFFECTIVENESS", "SHOW EFFECTIVENESS"),
        SHOW_ENGINE_CHOICES("SHOW_ENGINE_CHOICES", "SHOW ENGINE ANALYSIS");
        public final String str;
        public final String description;
        Hints (String str, String description) {
            this.str = str;
            this.description = description;
        }
        public String getStr(){
            return this.str;
        }
        public String getDescription(){
            return this.description;
        }
    }
    public static Hints hintMode = Hints.SHOW_ENGINE_CHOICES;
    //progression
    public static int reputation = 0;
    public static int recordColosseumTrainersBeaten = 0;
    //logs
    public static ArrayList<String> tradeHistory = new ArrayList<>();
    //trainers
    public static Map<Trainer.Title, Boolean> badgesEarned = new TreeMap<>();
    static {
        badgesEarned.put(Trainer.Title.PEWTER_GYM_LEADER, false);
        badgesEarned.put(Trainer.Title.CERULEAN_GYM_LEADER, false);
        badgesEarned.put(Trainer.Title.VERMILLION_GYM_LEADER, false);
        badgesEarned.put(Trainer.Title.CELADON_GYM_LEADER, false);
        badgesEarned.put(Trainer.Title.CINNABAR_GYM_LEADER, false);
        badgesEarned.put(Trainer.Title.FUCHSIA_GYM_LEADER, false);
        badgesEarned.put(Trainer.Title.SAFFRON_GYM_LEADER, false);
        badgesEarned.put(Trainer.Title.VIRIDIAN_GYM_LEADER, false);
        badgesEarned.put(Trainer.Title.ROCKETOPOLIS_GYM_LEADER, false);
        badgesEarned.put(Trainer.Title.VAUGHAN_DISTRICT_GYM_LEADER, false);
    }
    public static Map<Trainer.Title, Boolean> majorTrainersBeaten = new TreeMap<>();
    static {
        majorTrainersBeaten.put(Trainer.Title.ELITE_FOUR_LORELEI, false);
        majorTrainersBeaten.put(Trainer.Title.ELITE_FOUR_BRUNO, false);
        majorTrainersBeaten.put(Trainer.Title.ELITE_FOUR_AGATHA, false);
        majorTrainersBeaten.put(Trainer.Title.ELITE_FOUR_LANCE, false);
        majorTrainersBeaten.put(Trainer.Title.CHAMPION, false);
    }
    public static Map<Integer, Integer> trainersBeatenOnARoute = new TreeMap<>();
    static {
        for (int i = 1; i <= 25; i++) {
            trainersBeatenOnARoute.put(i, 0);
        }
    }
    //locations
    public static Map<Integer, Boolean> routesReached = new TreeMap<>();
    static {
        for (int i = 1; i <= 25; i++) {
            routesReached.put(i, i == 7 || i == 16 || i == 1);
        }
    }
    public static Map<Location.Area, Boolean> areasReached = new TreeMap<>();
    static {
        //cities
        areasReached.put(Location.Area.PALLET_TOWN, true); //starts here
        areasReached.put(Location.Area.VIRIDIAN_CITY, false);
        areasReached.put(Location.Area.PEWTER_CITY, false);
        areasReached.put(Location.Area.CERULEAN_CITY, false);
        areasReached.put(Location.Area.VERMILION_CITY, false);
        areasReached.put(Location.Area.LAVENDER_TOWN, false);
        areasReached.put(Location.Area.SAFFRON_CITY, false);
        areasReached.put(Location.Area.FUCHSIA_CITY, false);
        areasReached.put(Location.Area.CINNABAR_ISLAND, false);
        areasReached.put(Location.Area.ROCKETOPOLIS, true); //unlocked from the get-go
        areasReached.put(Location.Area.VAUGHAN_DISTRICT, true); // ^
        areasReached.put(Location.Area.CITY_CENTER, true); // ^
        //Other POIs
        areasReached.put(Location.Area.VIRIDIAN_FOREST, false);
        areasReached.put(Location.Area.MT_MOON, false);
        areasReached.put(Location.Area.ROCK_TUNNEL, false);
        areasReached.put(Location.Area.POKEMON_TOWER, false);
        areasReached.put(Location.Area.POKEMON_MANSION, false);
        areasReached.put(Location.Area.DIGLETTS_CAVE, false);
        areasReached.put(Location.Area.SAFARI_ZONE, false);
        areasReached.put(Location.Area.POWER_PLANT, false);
        areasReached.put(Location.Area.SEAFOAM_ISLANDS, false);
        areasReached.put(Location.Area.VICTORY_ROAD, false);
        areasReached.put(Location.Area.INDIGO_PLATEAU, false);
        areasReached.put(Location.Area.CERULEAN_CAVE, false);
    }
    //POKEDEX PROGRESS
    public static ArrayList<String> pokemonRegisteredInPokedex = new ArrayList<>();

    //general methods
    public static String getUsername() {
        return username;
    }
    public static void setUsername(String newUsername) {
        username = newUsername;
    }
    public static void setGender(Gender newGender) {
        if (newGender == null) {
            throw new IllegalArgumentException("Gender cannot be null");
        }
        gender = newGender;
    }
    public static Gender getGender(){
        return gender;
    }
    public static String getYouAreKnownAs(){
        String alignment = "";
        String status = "";
        String article = "a ";
        //alignment
        if(reputation >= -10 && reputation <= 10){
            alignment = "";
        }
        else if(reputation >= 11  && reputation < 50){
            alignment = "respectable, ";
        }
        else if(reputation >= 51  && reputation < 100){
            alignment = "likeable, ";
        }
        else if(reputation >= 101  && reputation < 250){
            article = "an ";
            alignment = "admirable, ";
        }
        else if(reputation >= 251  && reputation < 400){
            alignment = "benevolent, ";
        }
        else if(reputation >= 401 ){
            alignment = "sainted,  ";
        }
        else if(reputation>= -50 && reputation <= -11){
            alignment = "hardened, ";
        }
        else if(reputation>= -100 && reputation <= -51){
            alignment = "scummy, ";
        }
        else if(reputation>= -150 && reputation <= -101){
            alignment = "sleazy, ";
        }
        else if(reputation>= -200 && reputation <= -151){
            alignment = "morally corrupt ";
        }
        else if(reputation>= -250 && reputation <= -201){
            alignment = "rotten, ";
        }
        else if(reputation>= -300 && reputation <= -251){
            alignment = "despicable, ";
        }
        else if(reputation <= -301){
            alignment = "downright evil, ";
        }
        //status
        if(checkNumBadges() == 0){
            alignment = "";
            status = "nobody";
        }
        else if(checkNumBadges() <= 2){
            if(alignment.isEmpty()) article = "an ";
            status = "aspiring Pokemon trainer";
        }
        else if(checkNumBadges() == 3){
            status = "mediocre Pokemon trainer";
        }
        else if(checkNumBadges() == 4){
            status = "solid Pokemon trainer";
        }
        else if(checkNumBadges() <= 5){
            status = "talented Pokemon trainer";
        }
        else if(checkNumBadges() <= 7){
            status = "gifted Pokemon trainer";
        }
        else if(checkNumBadges() == 8){
            status = "masterful Pokemon trainer";
        }
        else {
            status = "godlike trainer of Pokemon";
        }
        return article + alignment + status + ".";
    }
    public static void setDifficulty(Difficulty difficulty) {
        difficultyMode = difficulty;
    }
    //progression methods
    public static void unlockEverything() {
        // Unlock all badges
        badgesEarned.replaceAll((t, v) -> true);

        // Unlock all routes
        for (int i = 1; i <= 25; i++) {
            routesReached.put(i, true);
        }

        // Mark all trainers as beaten on every route
        for (int i = 1; i <= 25; i++) {
            trainersBeatenOnARoute.put(i, 10);
        }

        // Unlock all locations
        areasReached.replaceAll((a, v) -> true);

        System.out.println("Everything unlocked! God mode activated.");
    }
    public static int checkLevelCap(){
        switch(checkNumBadges()) {
            case 0 -> {
                return 20;
            }
            case 1 -> {
                return 25;
            }
            case 2 -> {
                return 30;
            }
            case 3 -> {
                return 35;
            }
            case 4 -> {
                return 40;
            }
            case 5 -> {
                return 50;
            }
            case 6 -> {
                return 55;
            }
            case 7 -> {
                return 65;
            }
            case 8 -> {
                return 70;
            }
            case 9 -> {
                return 80;
            }
            case 10 -> {
                return 100;
            }
        }
        return 36;
    }
    public static void increaseReputation(int reputationIncrease){reputation += reputationIncrease;}
    public static void decreaseReputation(int reputationDecrease){reputation -= reputationDecrease;}
    public static boolean hasBadge(Trainer.Title title) {
        return badgesEarned.getOrDefault(title, false);
    }
    public static int checkNumBadges() {
        int count = 0;
        for (boolean earned : badgesEarned.values()) {
            if (earned) {
                count++;
            }
        }
        return count;
    }
    public static void unlockRoute(int routeNumber) {
        if (routesReached.containsKey(routeNumber)) {
            routesReached.put(routeNumber, true);
        }
    }
    public static String getMonth() {
        int badgeNum = checkNumBadges();
        String[] months = {"February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        if(badgeNum >= 0 && badgeNum <= 10) {
            return months[badgeNum];
        }
        return "Date NA";
    }

    //change player info menus
    public static int getTextSpeed(Scanner sc1){
        int textSpeed = 0;
        while(true) {
            System.out.println("      Please select a text speed:");
            System.out.println("=======================================");
            System.out.println(" [1] Instant (animations will not play)");
            System.out.println(" [2] Normal");
            System.out.println(" [3] Slower");
            System.out.println(" [4] Slowest");
            try {
                int input = sc1.nextInt();
                switch(input) {
                    case 1:
                        textSpeed = 200; // Instant
                        break;
                    case 2:
                        textSpeed = 2000; // Fast
                        break;
                    case 3:
                        textSpeed = 2500; // Medium (recommended)
                        break;
                    case 4:
                        textSpeed = 3500; // Slow
                        break;
                    default:
                        System.out.println("Invalid input. Please try again.");
                        continue;
                }
                sc1.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc1.nextLine();
            }
        }
        User.textSpeed = textSpeed;
        return textSpeed;
    }
    public static void askUserToSetDifficulty(Scanner sc1) {
        String choice= " ";
        while(true){
            System.out.println("                  SELECT DIFFICULTY");
            System.out.println("===================================================");
            System.out.println("| [1] NORMAL                                      |");
            System.out.println("|  Opponents are smart and rarely make mistakes,  |");
            System.out.println("|  although often do not make optimal decisions.  |");
            System.out.println("|  Recommended for intermediate players.          |");
            System.out.println("|-------------------------------------------------|");
            System.out.println("| [2] CHALLENGE                                   |");
            System.out.println("|  Opponents are smarter, consider more factors,  |");
            System.out.println("|  practically never make mistakes, and typically |");
            System.out.println("|  make optimal decisions. Recommended for        |");
            System.out.println("|  advanced players or those looking for a        |");
            System.out.println("|  challenge!                                     |");
            System.out.println("|-------------------------------------------------|");
            System.out.println("| [3] PROFESSIONAL                                |");
            System.out.println("|  Opponents seldom make mistakes and essentially |");
            System.out.println("|  always make optimal decisions. Use of items    |");
            System.out.println("|  during trainer battles is also prohibited.     |");
            System.out.println("|  Recommended for players with competitive       |");
            System.out.println("|  Pokemon experience.                            |");
            System.out.println("|-------------------------------------------------|");
            System.out.println("| [4] EASY                                        |");
            System.out.println("|  Opponents often blunder and are predictable.   |");
            System.out.println("|  Recommended for new players or those desiring  |");
            System.out.println("|  a more relaxed, adventure-focused playthrough. |");
            System.out.println("|-------------------------------------------------|");
            System.out.println("|      Difficulty can be changed at any time.     |");
            System.out.println("|-------------------------------------------------|");
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("1")){
                setDifficulty(Difficulty.NORMAL);
            }
            if(choice.equals("2")){
                setDifficulty(Difficulty.CHALLENGE);
            }
            if(choice.equals("3")){
                setDifficulty(Difficulty.PROFESSIONAL);
            }
            if(choice.equals("4")){
                setDifficulty(Difficulty.EASY);
            }
            if(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")) {
                    System.out.println("Difficulty has been set to " + User.difficultyMode.getStr() + ".");
                    Game.pressEnterToContinue();
                    break;
            } else {
                System.out.println("Invalid choice. Please select a difficulty.");
                Game.pressEnterToContinue(sc1);
            }
        }
    }
    public static void askUserToSetHints(Scanner sc1) {
        String choice= " ";
        while(true){
            System.out.println("               SELECT BATTLE HINT MODE");
            System.out.println("===================================================");
            System.out.println("| [1] NO HINTS                                    |");
            System.out.println("|-------------------------------------------------|");
            System.out.println("| [2] SHOW MOVE EFFECTIVENESS                     |");
            System.out.println("|-------------------------------------------------|");
            System.out.println("| [3] SHOW LIVE GAME ENGINE ANALYSIS              |");
            System.out.println("---------------------------------------------------");
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("1")){
                hintMode = Hints.NO_HINTS;
            }
            if(choice.equals("2")){
                hintMode = Hints.SHOW_EFFECTIVENESS;
            }
            if(choice.equals("3")){
                hintMode = Hints.SHOW_ENGINE_CHOICES;
            }
            if(choice.equals("1") || choice.equals("2") || choice.equals("3")) {
                System.out.println("HINT MODE has been set to " + User.hintMode.getDescription() + ".");
                Game.pressEnterToContinue();
                break;
            } else {
                System.out.println("Invalid choice. Please select a difficulty.");
                Game.pressEnterToContinue(sc1);
            }
        }
    }
}
