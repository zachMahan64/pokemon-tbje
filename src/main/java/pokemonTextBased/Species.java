package pokemonTextBased;

import java.util.*;

//evolution for eevee unfinished
public class Species {
    private final String name;
    private final String type1;
    private final String type2;
    private final int evolutionLevel;
    private final int baseHp;
    private final int baseAttack;
    private final int baseDefense;
    private final int baseSpAtk;
    private final int baseSpDef;
    private final int baseSpeed;
    private final List<Move> moves;

    //Types (species & pokemon uses strings, not this enum)
    public enum Type {
        BUG("Bug"),
        DARK("Dark"),
        DRAGON("Dragon"),
        ELECTRIC("Electric"),
        FAIRY("Fairy"),
        FIGHTING("Fighting"),
        FIRE("Fire"),
        FLYING("Flying"),
        GHOST("Ghost"),
        GRASS("Grass"),
        GROUND("Ground"),
        ICE("Ice"),
        NORMAL("Normal"),
        POISON("Poison"),
        PSYCHIC("Psychic"),
        ROCK("Rock"),
        STEEL("Steel"),
        WATER("Water");

        final String typeStr;

        Type(String typeStr) {
            this.typeStr = typeStr;
        }
        public String getStr() {
            return this.typeStr;
        }
        @Override
        public String toString() {
            return typeStr;
        }
    }

    //Constructor
    public Species(String name, String type1, String type2, int evolutionLevel, int baseHp, int baseAttack, int baseDefense, int baseSpAtk, int baseSpDef, int baseSpeed, List<Move> moves) {
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.evolutionLevel = evolutionLevel;
        this.baseHp = baseHp;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.baseSpAtk = baseSpAtk;
        this.baseSpDef = baseSpDef;
        this.baseSpeed = baseSpeed;
        this.moves = moves;
    }

    //pokedex
    public static void enterPokedexMenu(Scanner sc1){
        System.out.println("You flipped open the Pokedex.");
        while(true){
            Graphics.printPokedexImage();
            String choicePokedexMenu = sc1.nextLine();
            if(choicePokedexMenu.equalsIgnoreCase("I")) {
                while(true){
                    System.out.println("\nEnter the name or # of any Pokemon to view its info or [C] to cancel.");
                    if (sc1.hasNextInt()){
                        int requestedDexNum = sc1.nextInt();
                        if (requestedDexNum > 0 && requestedDexNum < speciesListedInPokedexOrder.size()+1){
                            Graphics.printSpecies(requestedDexNum);
                            System.out.println("#" + requestedDexNum + " " + getSpecies(getNameFromDexNum(requestedDexNum)));
                            sc1.nextLine();
                        }
                        else {
                            System.out.println("Pokemon not found.");
                            sc1.nextLine();
                        }
                    }
                    else if(sc1.hasNextLine()) {
                        String requestedPokemonName = sc1.nextLine();
                        if (requestedPokemonName.equalsIgnoreCase("C")) {
                            break;
                        }
                        if (!getSpecies(requestedPokemonName).getName().equalsIgnoreCase("Missingno")) {
                            Graphics.printSpecies(requestedPokemonName);
                            System.out.println("#" + getDexNumFromName(requestedPokemonName) + " " + getSpecies(requestedPokemonName));
                        }
                        else {
                            System.out.println("Pokemon not found.");
                        }
                    }
                }
            }
            else if (choicePokedexMenu.equalsIgnoreCase("L")) {
                printPokedexList();
                while (true) {
                    String askIfWantReturnToPokedexMainMenu = sc1.nextLine();
                    if (askIfWantReturnToPokedexMainMenu.equalsIgnoreCase("B")){
                        break;
                    }
                    else {
                        System.out.println("Invalid input. Please try again.");
                    }

                }
            }
            else if(choicePokedexMenu.equalsIgnoreCase("C")){
                Graphics.printPokedexClosed();
                System.out.println("You shut the Pokedex.");
                break;
            }
            else{
                System.out.println("Invalid input. Please try again.");
                sc1.nextLine();
            }
        }
    }
    public static void printPokedexList() {
        final String RED = "\u001B[31m";
        final String GREEN = "\u001B[32m";
        final String RESET = "\u001B[0m";

        Graphics.printTopOfPokedexList();
        System.out.print("⣿   ");

        for (int i = 0; i < speciesListedInPokedexOrder.size(); i++) {
            String name = speciesListedInPokedexOrder.get(i);
            String COLOR = (User.pokemonRegisteredInPokedex.contains(name)) ? GREEN : RED;

            String rawEntry = String.format("%d. %s", i + 1, name);

            String paddedEntry = String.format("%-20s", rawEntry);

            System.out.print(COLOR + paddedEntry + RESET);

            if ((i + 1) % 6 == 3) {
                System.out.print("⡇ ⢸ ");
            }
            if ((i + 1) % 6 == 0) {
                System.out.print(" ⣿");
                System.out.println();
                System.out.print("⣿   ");
            }
        }


        System.out.println();
        Graphics.printBottomOfPokedexList();
    }

    //tools
    public static String getNameFromDexNum(int dexNum) {
        try {
            return speciesListedInPokedexOrder.get(dexNum - 1);
        }
        catch (IndexOutOfBoundsException e) {
            return "Pokedex Num Not Found.";
        }
    }
    public static int getDexNumFromName(String name) {
        for (int i = 0; i < speciesListedInPokedexOrder.size(); i++) {
            if (speciesListedInPokedexOrder.get(i).equalsIgnoreCase(name)) {
                return i + 1;
            }
        }
        return 0;
    }
    //getters and setters
    public String getName() {
        return name;
    }
    public String getType1() {
        return type1;
    }
    public String getType2(){
        return type2;
    }
    public int getBaseHp() {
        return baseHp;
    }
    public int getBaseAttack() {
        return baseAttack;
    }
    public int getBaseDefense() {
        return baseDefense;
    }
    public int getBaseSpAtk() {
        return baseSpAtk;
    }
    public int getBaseSpDef() {
        return baseSpDef;
    }
    public int getBaseSpeed() {
        return baseSpeed;
    }
    public List<Move> getMoves() {
        return moves;
    }
    public int getEvolutionLevel() {
        return evolutionLevel;
    }
    public int getBST( ){
        return baseHp + baseAttack + baseDefense + baseSpAtk + baseSpDef + baseSpeed;
    }
    public static final List<String> speciesListedInPokedexOrder;
    static {
        speciesListedInPokedexOrder = Arrays.asList(
                "Bulbasaur", "Ivysaur", "Venusaur", "Charmander", "Charmeleon", "Charizard",
                "Squirtle", "Wartortle", "Blastoise", "Caterpie", "Metapod", "Butterfree",
                "Weedle", "Kakuna", "Beedrill", "Pidgey", "Pidgeotto", "Pidgeot",
                "Rattata", "Raticate", "Spearow", "Fearow", "Ekans", "Arbok", "Pikachu", "Raichu",
                "Sandshrew", "Sandslash", "NidoranF", "Nidorina", "Nidoqueen", "NidoranM",
                "Nidorino", "Nidoking", "Clefairy", "Clefable", "Vulpix", "Ninetales",
                "Jigglypuff", "Wigglytuff", "Zubat", "Golbat", "Oddish", "Gloom", "Vileplume",
                "Paras", "Parasect", "Venonat", "Venomoth", "Diglett", "Dugtrio", "Meowth",
                "Persian", "Psyduck", "Golduck", "Mankey", "Primeape", "Growlithe", "Arcanine",
                "Poliwag", "Poliwhirl", "Poliwrath", "Abra", "Kadabra", "Alakazam", "Machop",
                "Machoke", "Machamp", "Bellsprout", "Weepinbell", "Victreebel", "Tentacool",
                "Tentacruel", "Geodude", "Graveler", "Golem", "Ponyta", "Rapidash", "Slowpoke",
                "Slowbro", "Magnemite", "Magneton", "Farfetch'd", "Doduo", "Dodrio", "Seel",
                "Dewgong", "Grimer", "Muk", "Shellder", "Cloyster", "Gastly", "Haunter", "Gengar",
                "Onix", "Drowzee", "Hypno", "Krabby", "Kingler", "Voltorb", "Electrode",
                "Exeggcute", "Exeggutor", "Cubone", "Marowak", "Hitmonlee", "Hitmonchan",
                "Lickitung", "Koffing", "Weezing", "Rhyhorn", "Rhydon", "Chansey", "Tangela",
                "Kangaskhan", "Horsea", "Seadra", "Goldeen", "Seaking", "Staryu", "Starmie",
                "MrMime", "Scyther", "Jynx", "Electabuzz", "Magmar", "Pinsir", "Tauros",
                "Magikarp", "Gyarados", "Lapras", "Ditto", "Eevee", "Vaporeon", "Jolteon",
                "Flareon", "Porygon", "Omanyte", "Omastar", "Kabuto", "Kabutops", "Aerodactyl",
                "Snorlax", "Articuno", "Zapdos", "Moltres", "Dratini", "Dragonair", "Dragonite",
                "Mewtwo", "Mew", "Chikorita", "Bayleef", "Meganium", "Cyndaquil", "Quilava", "Typhlosion",
                "Totodile", "Croconaw", "Feraligatr", "Togepi", "Togetic", "Togekiss", "Espeon", "Umbreon",
                "Leafeon", "Glaceon", "Sylveon", "Scizor", "Wailmer", "Wailord", "Swinub", "Piloswine", "Mamoswine",
                "Houndour", "Houndoom", "Hitmontop", "Raikou", "Entei", "Suicune", "Larvitar", "Pupitar", "Tyranitar",
                "Lugia", "Ho-Oh", "Celebi", "Treecko", "Grovyle", "Sceptile", "Torchic", "Combusken",
                "Blaziken", "Mudkip", "Marshtomp", "Swampert", "Ralts", "Kirlia", "Gardevoir", "Zangoose",
                "Bagon", "Shelgon", "Salamence", "Beldum", "Metang", "Metagross", "Latias", "Latios", "Kyogre",
                "Groudon", "Rayquaza", "Jirachi", "Deoxys", "Gible", "Gabite", "Garchomp", "Rhyperior", "Riolu", "Lucario",
                "Dialga", "Palkia", "Giratina", "Darkrai", "Arceus", "Victini", "Drilbur", "Excadrill",
                "Zorua", "Zoroark", "Foongus", "Amoonguss", "Reshiram", "Zekrom", "Litten", "Torracat",
                "Incineroar", "Rookidee", "Corvisquire", "Corviknight", "Dreepy", "Drakloak", "Dragapult"

        );
    }
    public static final List<String> restrictedSpecies;
    static {
        restrictedSpecies = Arrays.asList(
                "Articuno", "Zapdos", "Moltres", "Mewtwo", "Mew", "Raikou", "Entei", "Suicune",
                "Lugia", "Ho-Oh", "Celebi", "Latias", "Latios", "Kyogre", "Groudon", "Rayquaza",
                "Jirachi", "Deoxys", "Dialga", "Palkia", "Giratina", "Darkrai", "Arceus", "Victini",
                "Reshiram", "Zekrom"
        );
    }
    public static final Map<String, Species> speciesMap = new HashMap<>();
    static {
        speciesMap.put("default", new Species("MissingNo", "Normal", "None", 0, 10, 10, 10, 10, 10, 10,
                Arrays.asList(Move.getMove("Move Not Found"), Move.getMove("Move Not Found"), Move.getMove("Move Not Found"), Move.getMove("Move Not Found"))));
        speciesMap.put("bulbasaur", new Species("Bulbasaur", "Grass", "Poison", 16, 45, 49, 49, 65, 65, 45,
                Arrays.asList(Move.getMove("Tackle"), Move.getMove("Vine Whip"), Move.getMove("Growl"), Move.getMove("Quick Attack"))));
        speciesMap.put("ivysaur", new Species("Ivysaur", "Grass", "Poison", 32, 60, 62, 63, 80, 80, 60,
                Arrays.asList(Move.getMove("Tackle"), Move.getMove("Razor Leaf"), Move.getMove("Growl"), Move.getMove("Acid"))));
        speciesMap.put("venusaur", new Species("Venusaur", "Grass", "Poison", 0, 80, 82, 83, 100, 100, 80,
                Arrays.asList(Move.getMove("Sleep Powder"), Move.getMove("Energy Ball"), Move.getMove("Sunny Day"), Move.getMove("Sludge Bomb"))));
        speciesMap.put("charmander", new Species("Charmander", "Fire", "None", 16, 39, 52, 43, 60, 50, 65,
                Arrays.asList(Move.getMove("Scratch"), Move.getMove("Ember"), Move.getMove("Growl"), Move.getMove("Quick Attack"))));
        speciesMap.put("charmeleon", new Species("Charmeleon", "Fire", "None", 36, 58, 64, 58, 80, 65, 80,
                Arrays.asList(Move.getMove("Scratch"), Move.getMove("Flame Wheel"), Move.getMove("Growl"), Move.getMove("Brick Break"))));
        speciesMap.put("charizard", new Species("Charizard", "Fire", "Flying", 0, 78, 84, 78, 109, 85, 100,
                Arrays.asList(Move.getMove("Air Slash"), Move.getMove("Flamethrower"), Move.getMove("Outrage"), Move.getMove("Solar Beam"))));
        speciesMap.put("squirtle", new Species("Squirtle", "Water", "None", 16, 44, 48, 65, 50, 64, 43,
                Arrays.asList(Move.getMove("Tackle"), Move.getMove("Bubble"), Move.getMove("Tail Whip"), Move.getMove("Quick Attack"))));
        speciesMap.put("wartortle", new Species("Wartortle", "Water", "None", 36, 59, 63, 80, 65, 80, 58,
                Arrays.asList(Move.getMove("Surf"), Move.getMove("Water Gun"), Move.getMove("Rain Dance"), Move.getMove("Ice Beam"))));
        speciesMap.put("blastoise", new Species("Blastoise", "Water", "None", 0, 79, 83, 100, 85, 105, 78,
                Arrays.asList(Move.getMove("Hydro Pump"), Move.getMove("Scald"), Move.getMove("Rain Dance"), Move.getMove("Ice Beam"))));
        speciesMap.put("caterpie", new Species("Caterpie", "Bug", "None", 7, 45, 30, 35, 20, 20, 45,
                Arrays.asList(Move.getMove("Tackle"), Move.getMove("String Shot"))));
        speciesMap.put("metapod", new Species("Metapod", "Bug", "None", 10, 50, 20, 55, 25, 25, 30,
                Arrays.asList(Move.getMove("Harden"))));
        speciesMap.put("butterfree", new Species("Butterfree", "Bug", "Flying", 0, 60, 45, 50, 90, 80, 70,
                Arrays.asList(Move.getMove("Confusion"), Move.getMove("Air Slash"), Move.getMove("Poison Powder"), Move.getMove("Bug Buzz"))));
        speciesMap.put("weedle", new Species("Weedle", "Bug", "Poison", 7, 40, 35, 30, 20, 20, 50,
                Arrays.asList(Move.getMove("Poison Sting"), Move.getMove("String Shot"))));
        speciesMap.put("kakuna", new Species("Kakuna", "Bug", "Poison", 10, 45, 25, 50, 25, 25, 35,
                Arrays.asList(Move.getMove("Harden"))));
        speciesMap.put("beedrill", new Species("Beedrill", "Bug", "Poison", 0, 65, 90, 40, 45, 80, 75,
                Arrays.asList(Move.getMove("Twineedle"), Move.getMove("Fury Cutter"), Move.getMove("Agility"), Move.getMove("Extreme Speed"))));
        speciesMap.put("pidgey", new Species("Pidgey", "Normal", "Flying", 18, 40, 45, 40, 35, 35, 56,
                Arrays.asList(Move.getMove("Tackle"), Move.getMove("Gust"), Move.getMove("Quick Attack"))));
        speciesMap.put("pidgeotto", new Species("Pidgeotto", "Normal", "Flying", 36, 63, 60, 55, 50, 50, 71,
                Arrays.asList(Move.getMove("Gust"), Move.getMove("Quick Attack"), Move.getMove("Drill Peck"))));
        speciesMap.put("pidgeot", new Species("Pidgeot", "Normal", "Flying", 0, 83, 80, 75, 70, 70, 91,
                Arrays.asList(Move.getMove("Wing Attack"), Move.getMove("Extreme Speed"), Move.getMove("Drill Peck"), Move.getMove("Tailwind"))));
        speciesMap.put("rattata", new Species("Rattata", "Normal", "None", 20, 30, 56, 35, 25, 35, 72,
                Arrays.asList(Move.getMove("Tackle"), Move.getMove("Quick Attack"), Move.getMove("Tail Whip"))));
        speciesMap.put("raticate", new Species("Raticate", "Normal", "None", 0, 55, 81, 60, 50, 70, 97,
                Arrays.asList(Move.getMove("Hyper Fang"), Move.getMove("Power-Up Punch"), Move.getMove("Bite"), Move.getMove("Extreme Speed"))));
        speciesMap.put("spearow", new Species("Spearow", "Normal", "Flying", 20, 40, 60, 30, 31, 31, 70,
                Arrays.asList(Move.getMove("Peck"), Move.getMove("Leer"))));
        speciesMap.put("fearow", new Species("Fearow", "Normal", "Flying", 0, 65, 90, 65, 61, 61, 100,
                Arrays.asList(Move.getMove("Drill Peck"), Move.getMove("Leer"), Move.getMove("Agility"))));
        speciesMap.put("ekans", new Species("Ekans", "Poison", "None", 22, 35, 60, 44, 40, 54, 55,
                Arrays.asList(Move.getMove("Tackle"), Move.getMove("Poison Sting"), Move.getMove("Leer"))));
        speciesMap.put("arbok", new Species("Arbok", "Poison", "None", 0, 60, 85, 69, 65, 79, 80,
                Arrays.asList(Move.getMove("Acid"), Move.getMove("Poison Sting"), Move.getMove("Glare"))));
        speciesMap.put("pikachu", new Species("Pikachu", "Electric", "None", 0, 35, 100, 40, 100, 50, 90,
                Arrays.asList(Move.getMove("Thunder Shock"), Move.getMove("Thunder"), Move.getMove("Quick Attack"), Move.getMove("Thunder Wave"))));
        speciesMap.put("raichu", new Species("Raichu", "Electric", "None", 0, 60, 90, 55, 90, 80, 110,
                Arrays.asList(Move.getMove("Thunderbolt"), Move.getMove("Volt Switch"), Move.getMove("Thunder Wave"), Move.getMove("Fake Out"))));
        speciesMap.put("sandshrew", new Species("Sandshrew", "Ground", "None", 22, 50, 75, 85, 20, 30, 40,
                Arrays.asList(Move.getMove("Scratch"), Move.getMove("Defense Curl"))));
        speciesMap.put("sandslash", new Species("Sandslash", "Ground", "None", 0, 75, 100, 110, 45, 55, 65,
                Arrays.asList(Move.getMove("Slash"), Move.getMove("Earthquake"), Move.getMove("Defense Curl"))));
        speciesMap.put("nidoranf", new Species("NidoranF", "Poison", "None", 16, 55, 47, 52, 40, 40, 41,
                Arrays.asList(Move.getMove("Tackle"), Move.getMove("Poison Sting"), Move.getMove("Growl"), Move.getMove("Mud-Slap"))));
        speciesMap.put("nidorina", new Species("Nidorina", "Poison", "None", 32, 70, 62, 67, 55, 55, 56,
                Arrays.asList(Move.getMove("Tackle"), Move.getMove("Poison Jab"), Move.getMove("Growl"), Move.getMove("Mud-Slap"))));
        speciesMap.put("nidoqueen", new Species("Nidoqueen", "Poison", "Ground", 0, 90, 82, 87, 75, 85, 76,
                Arrays.asList(Move.getMove("Body Slam"), Move.getMove("Poison Jab"), Move.getMove("Earthquake"), Move.getMove("Dig"))));
        speciesMap.put("nidoranm", new Species("NidoranM", "Poison", "None", 16, 46, 57, 40, 40, 40, 50,
                Arrays.asList(Move.getMove("Tackle"), Move.getMove("Poison Sting"), Move.getMove("Leer"))));
        speciesMap.put("nidorino", new Species("Nidorino", "Poison", "None", 32, 61, 72, 57, 55, 55, 65,
                Arrays.asList(Move.getMove("Slash"), Move.getMove("Poison Jab"), Move.getMove("Leer"))));
        speciesMap.put("nidoking", new Species("Nidoking", "Poison", "Ground", 0, 81, 102, 77, 85, 75, 85,
                Arrays.asList(Move.getMove("Drill Run"), Move.getMove("Poison Jab"), Move.getMove("Megahorn"), Move.getMove("Horn Drill"))));
        speciesMap.put("clefairy", new Species("Clefairy", "Fairy", "None", 30, 70, 45, 48, 35, 60, 65,
                Arrays.asList(Move.getMove("Play Rough"), Move.getMove("Sing"), Move.getMove("Fairy Wind"))));
        speciesMap.put("clefable", new Species("Clefable", "Fairy", "None", 0, 95, 70, 73, 60, 85, 90,
                Arrays.asList(Move.getMove("Play Rough"), Move.getMove("Sing"), Move.getMove("Dazzling Gleam"), Move.getMove("Moonblast"))));
        speciesMap.put("vulpix", new Species("Vulpix", "Fire", "None", 25, 38, 41, 40, 65, 50, 65,
                Arrays.asList(Move.getMove("Ember"), Move.getMove("Quick Attack"), Move.getMove("Tail Whip"), Move.getMove("Growl"))));
        speciesMap.put("ninetales", new Species("Ninetales", "Fire", "None", 0, 73, 76, 75, 100, 81, 100,
                Arrays.asList(Move.getMove("Flamethrower"), Move.getMove("Earth Power"), Move.getMove("Will-O-Wisp"), Move.getMove("Agility"))));
        speciesMap.put("jigglypuff", new Species("Jigglypuff", "Normal", "Fairy", 30, 115, 45, 20, 45, 25, 20,
                Arrays.asList(Move.getMove("Sing"), Move.getMove("Pound"), Move.getMove("Calm Mind"), Move.getMove("Play Rough"))));
        speciesMap.put("wigglytuff", new Species("Wigglytuff", "Normal", "Fairy", 0, 140, 70, 45, 85, 50, 45,
                Arrays.asList(Move.getMove("Hyper Beam"), Move.getMove("Dazzling Gleam"), Move.getMove("Mystical Fire"), Move.getMove("Calm Mind"))));
        speciesMap.put("zubat", new Species("Zubat", "Poison", "Flying", 22, 40, 45, 35, 55, 30, 40,
                Arrays.asList(Move.getMove("Poison Sting"), Move.getMove("Bite"), Move.getMove("Wing Attack"), Move.getMove("Screech"), Move.getMove("Gust"))));
        speciesMap.put("golbat", new Species("Golbat", "Poison", "Flying", 0, 75, 80, 70, 90, 65, 75,
                Arrays.asList(Move.getMove("Wing Attack"), Move.getMove("Poison Fang"), Move.getMove("Slash"), Move.getMove("Leech Life"))));
        speciesMap.put("oddish", new Species("Oddish", "Grass", "Poison", 22, 45, 50, 55, 30, 75, 65,
                Arrays.asList(Move.getMove("Absorb"), Move.getMove("Poison Powder"), Move.getMove("Tackle"), Move.getMove("Quick Attack"))));
        speciesMap.put("gloom", new Species("Gloom", "Grass", "Poison", 36, 60, 65, 70, 40, 85, 75,
                Arrays.asList(Move.getMove("Acid"), Move.getMove("Sleep Powder"), Move.getMove("Mega Drain"), Move.getMove("Poison Powder"))));
        speciesMap.put("vileplume", new Species("Vileplume", "Grass", "Poison", 0, 75, 80, 85, 50, 100, 90,
                Arrays.asList(Move.getMove("Solar Beam"), Move.getMove("Sludge Bomb"), Move.getMove("Petal Dance"), Move.getMove("Sleep Powder"))));
        speciesMap.put("paras", new Species("Paras", "Bug", "Grass", 24, 35, 70, 55, 25, 45, 55,
                Arrays.asList(Move.getMove("Scratch"), Move.getMove("Leech Life"), Move.getMove("Spore"), Move.getMove("Energy Ball"))));
        speciesMap.put("parasect", new Species("Parasect", "Bug", "Grass", 0, 60, 95, 80, 30, 60, 80,
                Arrays.asList(Move.getMove("Fury Cutter"), Move.getMove("Spore"), Move.getMove("Slash"), Move.getMove("Megahorn"))));
        speciesMap.put("venonat", new Species("Venonat", "Bug", "Poison", 32, 60, 55, 50, 45, 40, 55,
                Arrays.asList(Move.getMove("Tackle"), Move.getMove("Poison Powder"), Move.getMove("Psybeam"), Move.getMove("Quick Attack"))));
        speciesMap.put("venomoth", new Species("Venomoth", "Bug", "Poison", 0, 70, 65, 60, 90, 90, 75,
                Arrays.asList(Move.getMove("Psychic"), Move.getMove("Bug Buzz"), Move.getMove("Sleep Powder"))));
        speciesMap.put("diglett", new Species("Diglett", "Ground", "None", 26, 10, 55, 25, 95, 35, 45,
                Arrays.asList(Move.getMove("Earthquake"), Move.getMove("Pound"), Move.getMove("Growl"), Move.getMove("Dig"))));
        speciesMap.put("dugtrio", new Species("Dugtrio", "Ground", "None", 0, 35, 80, 50, 120, 50, 70,
                Arrays.asList(Move.getMove("Earthquake"), Move.getMove("Slash"), Move.getMove("Dig"), Move.getMove("Rock Slide"))));
        speciesMap.put("meowth", new Species("Meowth", "Normal", "None", 22, 40, 45, 35, 90, 40, 40,
                Arrays.asList(Move.getMove("Scratch"), Move.getMove("Bite"), Move.getMove("Pound"), Move.getMove("Growl"))));
        speciesMap.put("persian", new Species("Persian", "Normal", "None", 0, 65, 70, 60, 115, 65, 65,
                Arrays.asList(Move.getMove("Crunch"), Move.getMove("Extreme Speed"), Move.getMove("Fake Out"), Move.getMove("Swords Dance"))));
        speciesMap.put("psyduck", new Species("Psyduck", "Water", "None", 33, 50, 52, 48, 55, 65, 50,
                Arrays.asList(Move.getMove("Water Gun"), Move.getMove("Confusion"), Move.getMove("Bubble"), Move.getMove("Bubble Beam"))));
        speciesMap.put("golduck", new Species("Golduck", "Water", "None", 0, 80, 82, 78, 85, 95, 80,
                Arrays.asList(Move.getMove("Hydro Pump"), Move.getMove("Psychic"), Move.getMove("Aqua Tail"), Move.getMove("Slash"))));
        speciesMap.put("mankey", new Species("Mankey", "Fighting", "None", 28, 40, 80, 35, 70, 35, 45,
                Arrays.asList(Move.getMove("Karate Chop"), Move.getMove("Low Kick"), Move.getMove("Agility"), Move.getMove("Growl"))));
        speciesMap.put("primeape", new Species("Primeape", "Fighting", "None", 0, 65, 105, 60, 95, 60, 70,
                Arrays.asList(Move.getMove("Crunch"), Move.getMove("Slash"), Move.getMove("Close Combat"), Move.getMove("Mach Punch"))));
        speciesMap.put("growlithe", new Species("Growlithe", "Fire", "None", 30, 55, 70, 45, 60, 70, 50,
                Arrays.asList(Move.getMove("Flame Wheel"), Move.getMove("Bite"), Move.getMove("Growl"), Move.getMove("Leer"))));
        speciesMap.put("arcanine", new Species("Arcanine", "Fire", "None", 0, 90, 110, 80, 95, 80, 95,
                Arrays.asList(Move.getMove("Will-O-Wisp"), Move.getMove("Extreme Speed"), Move.getMove("Crunch"), Move.getMove("Flare Blitz"))));
        speciesMap.put("poliwag", new Species("Poliwag", "Water", "None", 25, 40, 50, 40, 90, 40, 90,
                Arrays.asList(Move.getMove("Water Gun"), Move.getMove("Bubble Beam"), Move.getMove("Hypnosis"))));
        speciesMap.put("poliwhirl", new Species("Poliwhirl", "Water", "None", 40, 65, 65, 65, 90, 50, 90,
                Arrays.asList(Move.getMove("Water Pulse"), Move.getMove("Body Slam"), Move.getMove("Rain Dance"))));
        speciesMap.put("poliwrath", new Species("Poliwrath", "Water", "Fighting", -1, 90, 95, 95, 70, 90, 70,
                Arrays.asList(Move.getMove("Hydro Pump"), Move.getMove("Mach Punch"), Move.getMove("Ice Punch"), Move.getMove("Close Combat"))));
        speciesMap.put("abra", new Species("Abra", "Psychic", "None", 16, 25, 20, 15, 105, 55, 90,
                Arrays.asList(Move.getMove("Agility"), Move.getMove("Psybeam"), Move.getMove("Confusion"))));
        speciesMap.put("kadabra", new Species("Kadabra", "Psychic", "None", 40, 40, 35, 30, 120, 70, 105,
                Arrays.asList(Move.getMove("Psychic"), Move.getMove("Calm Mind"), Move.getMove("Agility"))));
        speciesMap.put("alakazam", new Species("Alakazam", "Psychic", "None", -1, 55, 50, 45, 135, 95, 120,
                Arrays.asList(Move.getMove("Psychic"), Move.getMove("Dark Pulse"), Move.getMove("Calm Mind"), Move.getMove("Agility"))));
        speciesMap.put("machop", new Species("Machop", "Fighting", "None", 28, 70, 80, 50, 35, 35, 35,
                Arrays.asList(Move.getMove("Low Kick"), Move.getMove("Karate Chop"), Move.getMove("Leer"), Move.getMove("Quick Attack"))));
        speciesMap.put("machoke", new Species("Machoke", "Fighting", "None", -1, 80, 100, 70, 45, 45, 55,
                Arrays.asList(Move.getMove("Low Kick"), Move.getMove("Karate Chop"), Move.getMove("Close Combat"), Move.getMove("Swords Dance"))));
        speciesMap.put("machamp", new Species("Machamp", "Fighting", "None", -1, 90, 130, 80, 65, 85, 55,
                Arrays.asList(Move.getMove("Low Kick"), Move.getMove("Karate Chop"), Move.getMove("Close Combat"), Move.getMove("Swords Dance"))));
        speciesMap.put("bellsprout", new Species("Bellsprout", "Grass", "Poison", 21, 50, 75, 35, 70, 30, 40,
                Arrays.asList(Move.getMove("Vine Whip"), Move.getMove("Acid"), Move.getMove("Poison Powder"))));
        speciesMap.put("weepinbell", new Species("Weepinbell", "Grass", "Poison", 36, 65, 90, 50, 85, 45, 55,
                Arrays.asList(Move.getMove("Vine Whip"), Move.getMove("Acid"), Move.getMove("Energy Ball"))));
        speciesMap.put("victreebel", new Species("Victreebel", "Grass", "Poison", -1, 80, 105, 65, 100, 60, 70,
                Arrays.asList(Move.getMove("Solar Beam"), Move.getMove("Acid"), Move.getMove("Bug Buzz"), Move.getMove("Scald"))));
        speciesMap.put("tentacool", new Species("Tentacool", "Water", "Poison", 30, 40, 40, 35, 50, 100, 70,
                Arrays.asList(Move.getMove("Acid"), Move.getMove("Bubble"), Move.getMove("Toxic"), Move.getMove("Water Gun"))));
        speciesMap.put("tentacruel", new Species("Tentacruel", "Water", "Poison", -1, 80, 70, 65, 80, 120, 100,
                Arrays.asList(Move.getMove("Hydro Pump"), Move.getMove("Acid"), Move.getMove("Toxic"), Move.getMove("Scald"))));
        speciesMap.put("geodude", new Species("Geodude", "Rock", "Ground", 25, 40, 80, 100, 30, 30, 20,
                Arrays.asList(Move.getMove("Tackle"), Move.getMove("Rock Throw"), Move.getMove("Defense Curl"), Move.getMove("Explosion"))));
        speciesMap.put("graveler", new Species("Graveler", "Rock", "Ground", 45, 55, 95, 115, 45, 45, 35,
                Arrays.asList(Move.getMove("Tackle"), Move.getMove("Rock Throw"), Move.getMove("Earthquake"), Move.getMove("Explosion"))));
        speciesMap.put("golem", new Species("Golem", "Rock", "Ground", 0, 80, 110, 130, 55, 65, 45,
                Arrays.asList(Move.getMove("Earthquake"), Move.getMove("Rock Slide"), Move.getMove("Explosion"), Move.getMove("Iron Defense"))));
        speciesMap.put("ponyta", new Species("Ponyta", "Fire", "None", 40, 50, 65, 40, 60, 50, 90,
                Arrays.asList(Move.getMove("Tackle"), Move.getMove("Ember"), Move.getMove("Growl"), Move.getMove("Leer"))));
        speciesMap.put("rapidash", new Species("Rapidash", "Fire", "None", 0, 65, 100, 70, 80, 80, 105,
                Arrays.asList(Move.getMove("Flare Blitz"), Move.getMove("Quick Attack"), Move.getMove("Agility"), Move.getMove("Swords Dance"))));
        speciesMap.put("slowpoke", new Species("Slowpoke", "Water", "Psychic", 37, 90, 65, 65, 40, 40, 15,
                Arrays.asList(Move.getMove("Tackle"), Move.getMove("Confusion"), Move.getMove("Bubble"), Move.getMove("Scald"))));
        speciesMap.put("slowbro", new Species("Slowbro", "Water", "Psychic", 0, 95, 75, 110, 65, 80, 30,
                Arrays.asList(Move.getMove("Psychic"), Move.getMove("Surf"), Move.getMove("Bubble Beam"), Move.getMove("Calm Mind"))));
        speciesMap.put("magnemite", new Species("Magnemite", "Electric", "Steel", 30, 25, 35, 70, 95, 55, 45,
                Arrays.asList(Move.getMove("Thunder Shock"), Move.getMove("Agility"), Move.getMove("Tackle"))));
        speciesMap.put("magneton", new Species("Magneton", "Electric", "Steel", 0, 50, 60, 95, 120, 70, 70,
                Arrays.asList(Move.getMove("Thunderbolt"), Move.getMove("Agility"), Move.getMove("Thunder"), Move.getMove("Flash Cannon"))));
        speciesMap.put("farfetch'd", new Species("Farfetch'd", "Normal", "Flying", 0, 52, 65, 55, 58, 50, 60,
                Arrays.asList(Move.getMove("Peck"), Move.getMove("Growl"), Move.getMove("Pound"), Move.getMove("Wing Attack"))));
        speciesMap.put("doduo", new Species("Doduo", "Normal", "Flying", 31, 35, 85, 45, 35, 35, 75,
                Arrays.asList(Move.getMove("Peck"), Move.getMove("Growl"), Move.getMove("Quick Attack"))));
        speciesMap.put("dodrio", new Species("Dodrio", "Normal", "Flying", 0, 60, 110, 70, 60, 60, 100,
                Arrays.asList(Move.getMove("Drill Peck"), Move.getMove("Hyper Beam"), Move.getMove("Quick Attack"), Move.getMove("Agility"))));
        speciesMap.put("seel", new Species("Seel", "Water", "None", 34, 65, 45, 55, 45, 70, 45,
                Arrays.asList(Move.getMove("Headbutt"), Move.getMove("Water Gun"), Move.getMove("Growl"), Move.getMove("Snowy Day"))));
        speciesMap.put("dewgong", new Species("Dewgong", "Water", "Ice", 0, 90, 70, 80, 70, 95, 60,
                Arrays.asList(Move.getMove("Ice Beam"), Move.getMove("Surf"), Move.getMove("Recover"), Move.getMove("Snowy Day"))));
        speciesMap.put("grimer", new Species("Grimer", "Poison", "None", 38, 80, 80, 50, 40, 50, 25,
                Arrays.asList(Move.getMove("Toxic"), Move.getMove("Tackle"), Move.getMove("Harden"), Move.getMove("Pound"))));
        speciesMap.put("muk", new Species("Muk", "Poison", "None", 0, 105, 105, 75, 65, 100, 50,
                Arrays.asList(Move.getMove("Sludge Bomb"), Move.getMove("Toxic"), Move.getMove("Harden"), Move.getMove("Close Combat"))));
        speciesMap.put("shellder", new Species("Shellder", "Water", "None", 22, 30, 65, 45, 25, 40, 40,
                Arrays.asList(Move.getMove("Tackle"), Move.getMove("Water Gun"), Move.getMove("Bubble Beam"), Move.getMove("Rain Dance"))));
        speciesMap.put("cloyster", new Species("Cloyster", "Water", "Ice", 0, 50, 95, 180, 85, 45, 70,
                Arrays.asList(Move.getMove("Hydro Pump"), Move.getMove("Ice Beam"), Move.getMove("Agility"), Move.getMove("Rain Dance"))));
        speciesMap.put("gastly", new Species("Gastly", "Ghost", "Poison", 25, 30, 35, 30, 100, 35, 80,
                Arrays.asList(Move.getMove("Lick"), Move.getMove("Night Shade"), Move.getMove("Hypnosis"), Move.getMove("Calm Mind"))));
        speciesMap.put("haunter", new Species("Haunter", "Ghost", "Poison", 40, 45, 50, 45, 115, 55, 95,
                Arrays.asList(Move.getMove("Shadow Ball"), Move.getMove("Night Shade"), Move.getMove("Hypnosis"), Move.getMove("Calm Mind"))));
        speciesMap.put("gengar", new Species("Gengar", "Ghost", "Poison", 0, 60, 65, 60, 130, 75, 110,
                Arrays.asList(Move.getMove("Shadow Ball"), Move.getMove("Sludge Bomb"), Move.getMove("Hypnosis"), Move.getMove("Nasty Plot"))));
        speciesMap.put("onix", new Species("Onix", "Rock", "Ground", 0, 35, 45, 160, 30, 45, 70,
                Arrays.asList(Move.getMove("Rock Throw"), Move.getMove("Screech"), Move.getMove("Giga Impact"), Move.getMove("Iron Defense"))));
        speciesMap.put("drowzee", new Species("Drowzee", "Psychic", "None", 26, 60, 48, 45, 43, 90, 42,
                Arrays.asList(Move.getMove("Confusion"), Move.getMove("Pound"), Move.getMove("Hypnosis"))));
        speciesMap.put("hypno", new Species("Hypno", "Psychic", "None", 0, 85, 73, 70, 73, 115, 67,
                Arrays.asList(Move.getMove("Confusion"), Move.getMove("Trick Room"), Move.getMove("Hypnosis"), Move.getMove("Psychic"))));
        speciesMap.put("krabby", new Species("Krabby", "Water", "None", 28, 30, 105, 70, 25, 25, 50,
                Arrays.asList(Move.getMove("Slash"), Move.getMove("Bubble"), Move.getMove("Leer"), Move.getMove("Glare"))));
        speciesMap.put("kingler", new Species("Kingler", "Water", "None", 0, 55, 130, 115, 50, 35, 75,
                Arrays.asList(Move.getMove("Crabhammer"), Move.getMove("Swords Dance"), Move.getMove("Waterfall"), Move.getMove("Agility"))));
        speciesMap.put("voltorb", new Species("Voltorb", "Electric", "None", 30, 40, 30, 50, 55, 35, 30,
                Arrays.asList(Move.getMove("Tackle"), Move.getMove("Electro Ball"), Move.getMove("Self-Destruct"), Move.getMove("Quick Attack"))));
        speciesMap.put("electrode", new Species("Electrode", "Electric", "None", 0, 50, 70, 90, 80, 50, 140,
                Arrays.asList(Move.getMove("Thunderbolt"), Move.getMove("Thunder Wave"), Move.getMove("Self-Destruct"), Move.getMove("Nasty Plot"))));
        speciesMap.put("exeggcute", new Species("Exeggcute", "Grass", "Psychic", 30, 60, 40, 80, 60, 45, 40,
                Arrays.asList(Move.getMove("Tackle"), Move.getMove("Absorb"), Move.getMove("Hypnosis"), Move.getMove("Confusion"))));
        speciesMap.put("exeggutor", new Species("Exeggutor", "Grass", "Psychic", 0, 95, 75, 85, 125, 65, 55,
                Arrays.asList(Move.getMove("Solar Beam"), Move.getMove("Psychic"), Move.getMove("Giga Drain"), Move.getMove("Hypnosis"))));
        speciesMap.put("cubone", new Species("Cubone", "Ground", "None", 28, 50, 50, 95, 35, 40, 20,
                Arrays.asList(Move.getMove("Bone Club"), Move.getMove("Growl"), Move.getMove("Leer"), Move.getMove("Scratch"))));
        speciesMap.put("marowak", new Species("Marowak", "Ground", "None", 0, 60, 80, 110, 45, 50, 30,
                Arrays.asList(Move.getMove("Bone Club"), Move.getMove("Earthquake"), Move.getMove("Slash"), Move.getMove("Swords Dance"))));
        speciesMap.put("hitmonlee", new Species("Hitmonlee", "Fighting", "None", 0, 50, 120, 70, 87, 35, 100,
                Arrays.asList(Move.getMove("Low Kick"), Move.getMove("Ice Punch"), Move.getMove("Thunder Punch"), Move.getMove("Swords Dance"))));
        speciesMap.put("hitmonchan", new Species("Hitmonchan", "Fighting", "None", 0, 50, 105, 79, 76, 35, 50,
                Arrays.asList(Move.getMove("Fire Punch"), Move.getMove("Ice Punch"), Move.getMove("Thunder Punch"), Move.getMove("Close Combat"))));
        speciesMap.put("lickitung", new Species("Lickitung", "Normal", "None", 0, 90, 55, 75, 30, 60, 40,
                Arrays.asList(Move.getMove("Lick"), Move.getMove("Tackle"), Move.getMove("Growl"))));
        speciesMap.put("koffing", new Species("Koffing", "Poison", "None", 35, 40, 65, 95, 35, 45, 60,
                Arrays.asList(Move.getMove("Toxic"), Move.getMove("Tackle"), Move.getMove("Agility"))));
        speciesMap.put("weezing", new Species("Weezing", "Poison", "None", 0, 65, 90, 120, 60, 50, 70,
                Arrays.asList(Move.getMove("Sludge Bomb"), Move.getMove("Giga Impact"), Move.getMove("Explosion"), Move.getMove("Poison Jab"))));
        speciesMap.put("rhyhorn", new Species("Rhyhorn", "Rock", "Ground", 42, 80, 85, 95, 25, 30, 30,
                Arrays.asList(Move.getMove("Mud-Slap"), Move.getMove("Horn Attack"), Move.getMove("Slash"), Move.getMove("Defense Curl"))));
        speciesMap.put("rhydon", new Species("Rhydon", "Rock", "Ground", 42, 105, 130, 120, 40, 50, 60,
                Arrays.asList(Move.getMove("Drill Run"), Move.getMove("Rock Slide"), Move.getMove("Horn Drill"), Move.getMove("Swords Dance"))));
        speciesMap.put("chansey", new Species("Chansey", "Normal", "None", 0, 250, 5, 5, 50, 35, 105,
                Arrays.asList(Move.getMove("Recover"), Move.getMove("Pound"), Move.getMove("Sing"))));
        speciesMap.put("tangela", new Species("Tangela", "Grass", "None", 0, 65, 55, 115, 60, 60, 50,
                Arrays.asList(Move.getMove("Vine Whip"), Move.getMove("Tackle"), Move.getMove("Absorb"))));
        speciesMap.put("kangaskhan", new Species("Kangaskhan", "Normal", "None", 0, 105, 95, 80, 90, 50, 45,
                Arrays.asList(Move.getMove("Quick Attack"), Move.getMove("Power-Up Punch"), Move.getMove("Bulk Up"), Move.getMove("Giga Impact"))));
        speciesMap.put("horsea", new Species("Horsea", "Water", "None", 32, 30, 40, 70, 60, 50, 40,
                Arrays.asList(Move.getMove("Bubble"), Move.getMove("Water Gun"), Move.getMove("Leer"), Move.getMove("Powder Snow"))));
        speciesMap.put("seadra", new Species("Seadra", "Water", "None", 0, 55, 65, 95, 85, 45, 50,
                Arrays.asList(Move.getMove("Hydro Pump"), Move.getMove("Ice Beam"), Move.getMove("Leer"), Move.getMove("Rain Dance"))));
        speciesMap.put("goldeen", new Species("Goldeen", "Water", "None", 33, 45, 60, 40, 63, 50, 45,
                Arrays.asList(Move.getMove("Peck"), Move.getMove("Tail Whip"), Move.getMove("Horn Attack"), Move.getMove("Quick Attack"))));
        speciesMap.put("seaking", new Species("Seaking", "Water", "None", 0, 80, 92, 65, 68, 60, 75,
                Arrays.asList(Move.getMove("Horn Drill"), Move.getMove("Waterfall"), Move.getMove("Peck"), Move.getMove("Agility"))));
        speciesMap.put("staryu", new Species("Staryu", "Water", "None", 25, 30, 45, 55, 85, 45, 70,
                Arrays.asList(Move.getMove("Water Gun"), Move.getMove("Rapid Spin"), Move.getMove("Recover"), Move.getMove("Bubble"))));
        speciesMap.put("starmie", new Species("Starmie", "Water", "Psychic", 0, 60, 75, 85, 115, 50, 60,
                Arrays.asList(Move.getMove("Surf"), Move.getMove("Psychic"), Move.getMove("Recover"), Move.getMove("Nasty Plot"))));
        speciesMap.put("mrmime", new Species("MrMime", "Psychic", "Fairy", 0, 40, 45, 65, 90, 50, 60,
                Arrays.asList(Move.getMove("Confusion"), Move.getMove("Psychic"), Move.getMove("Ice Beam"), Move.getMove("Calm Mind"))));
        speciesMap.put("scyther", new Species("Scyther", "Bug", "Flying", 32, 70, 110, 80, 105, 55, 70,
                Arrays.asList(Move.getMove("Brick Break"), Move.getMove("X-Scissor"), Move.getMove("Swords Dance"), Move.getMove("Fury Cutter"))));
        speciesMap.put("jynx", new Species("Jynx", "Ice", "Psychic", 0, 65, 50, 35, 95, 55, 65,
                Arrays.asList(Move.getMove("Ice Beam"), Move.getMove("Psychic"), Move.getMove("Lick"), Move.getMove("Nasty Plot"))));
        speciesMap.put("electabuzz", new Species("Electabuzz", "Electric", "None", 0, 65, 83, 57, 105, 60, 45,
                Arrays.asList(Move.getMove("Thunderbolt"), Move.getMove("Quick Attack"), Move.getMove("Thunder Wave"), Move.getMove("Slash"))));
        speciesMap.put("magmar", new Species("Magmar", "Fire", "None", 0, 65, 95, 57, 93, 50, 55,
                Arrays.asList(Move.getMove("Fire Punch"), Move.getMove("Confusion"), Move.getMove("Ember"), Move.getMove("Pound"))));
        speciesMap.put("pinsir", new Species("Pinsir", "Bug", "None", 0, 65, 125, 75, 85, 40, 55,
                Arrays.asList(Move.getMove("X-Scissor"), Move.getMove("Swords Dance"), Move.getMove("Agility"), Move.getMove("Close Combat"))));
        speciesMap.put("tauros", new Species("Tauros", "Normal", "None", 0, 75, 100, 70, 110, 50, 40,
                Arrays.asList(Move.getMove("Giga Impact"), Move.getMove("Mud-Slap"), Move.getMove("Leer"), Move.getMove("Bulk Up"))));
        speciesMap.put("magikarp", new Species("Magikarp", "Water", "None", 20, 20, 10, 55, 80, 45, 35,
                Arrays.asList(Move.getMove("Splash"))));
        speciesMap.put("gyarados", new Species("Gyarados", "Water", "Flying", 0, 95, 125, 79, 60, 100, 81,
                Arrays.asList(Move.getMove("Fly"), Move.getMove("Waterfall"), Move.getMove("Dragon Dance"), Move.getMove("Rain Dance"))));
        speciesMap.put("lapras", new Species("Lapras", "Water", "Ice", 0, 130, 85, 80, 85, 95, 60,
                Arrays.asList(Move.getMove("Surf"), Move.getMove("Ice Beam"), Move.getMove("Sing"), Move.getMove("Snowy Day"))));
        speciesMap.put("ditto", new Species("Ditto", "Normal", "None", 0, 48, 48, 48, 48, 48, 48,
                Arrays.asList(Move.getMove("Transform"))));
        speciesMap.put("eevee", new Species("Eevee", "Normal", "None", 0, 55, 55, 50, 45, 65, 55,
                Arrays.asList(Move.getMove("Tackle"), Move.getMove("Growl"), Move.getMove("Quick Attack"), Move.getMove("Charm"))));
        speciesMap.put("vaporeon", new Species("Vaporeon", "Water", "None", 0, 130, 65, 60, 65, 50, 55,
                Arrays.asList(Move.getMove("Hydro Pump"), Move.getMove("Scald"), Move.getMove("Ice Beam"), Move.getMove("Nasty Plot"))));
        speciesMap.put("jolteon", new Species("Jolteon", "Electric", "None", 0, 65, 65, 60, 110, 50, 130,
                Arrays.asList(Move.getMove("Thunderbolt"), Move.getMove("Agility"), Move.getMove("Thunder"), Move.getMove("Thunder Wave"))));
        speciesMap.put("flareon", new Species("Flareon", "Fire", "None", 0, 65, 130, 60, 65, 110, 65,
                Arrays.asList(Move.getMove("Flare Blitz"), Move.getMove("Quick Attack"), Move.getMove("Fire Spin"), Move.getMove("Bulk Up"))));
        speciesMap.put("porygon", new Species("Porygon", "Normal", "None", 0, 65, 60, 70, 40, 50, 45,
                Arrays.asList(Move.getMove("Psybeam"), Move.getMove("Tackle"), Move.getMove("Recover"))));
        speciesMap.put("omanyte", new Species("Omanyte", "Rock", "Water", 40, 35, 40, 100, 35, 50, 50,
                Arrays.asList(Move.getMove("Bite"), Move.getMove("Tackle"), Move.getMove("Rock Throw"))));
        speciesMap.put("omastar", new Species("Omastar", "Rock", "Water", 0, 70, 60, 125, 55, 60, 70,
                Arrays.asList(Move.getMove("Hydro Pump"), Move.getMove("Crunch"), Move.getMove("Rock Slide"), Move.getMove("Iron Defense"))));
        speciesMap.put("kabuto", new Species("Kabuto", "Rock", "Water", 40, 30, 80, 90, 40, 55, 60,
                Arrays.asList(Move.getMove("Absorb"), Move.getMove("Scratch"), Move.getMove("Harden"))));
        speciesMap.put("kabutops", new Species("Kabutops", "Rock", "Water", 0, 60, 115, 105, 80, 75, 90,
                Arrays.asList(Move.getMove("Waterfall"), Move.getMove("Slash"), Move.getMove("Leer"), Move.getMove("Bulk Up"))));
        speciesMap.put("aerodactyl", new Species("Aerodactyl", "Rock", "Flying", 0, 80, 105, 65, 130, 60, 70,
                Arrays.asList(Move.getMove("Crunch"), Move.getMove("Wing Attack"), Move.getMove("Agility"), Move.getMove("Rock Slide"))));
        speciesMap.put("snorlax", new Species("Snorlax", "Normal", "None", 0, 160, 110, 65, 65, 110, 30,
                Arrays.asList(Move.getMove("Headbutt"), Move.getMove("High Horsepower"), Move.getMove("Belly Drum"), Move.getMove("Rest"))));
        speciesMap.put("articuno", new Species("Articuno", "Ice", "Flying", 0, 90, 85, 100, 125, 125, 85,
                Arrays.asList(Move.getMove("Ice Beam"), Move.getMove("Air Slash"), Move.getMove("Agility"), Move.getMove("Scald"))));
        speciesMap.put("zapdos", new Species("Zapdos", "Electric", "Flying", 0, 90, 90, 85, 100, 75, 60,
                Arrays.asList(Move.getMove("Thunder"), Move.getMove("Drill Peck"), Move.getMove("Agility"), Move.getMove("Thunderbolt"))));
        speciesMap.put("moltres", new Species("Moltres", "Fire", "Flying", 0, 90, 100, 90, 120, 85, 90,
                Arrays.asList(Move.getMove("Fire Blast"), Move.getMove("Air Slash"), Move.getMove("Agility"), Move.getMove("Flamethrower"))));
        speciesMap.put("dratini", new Species("Dratini", "Dragon", "None", 30, 41, 64, 45, 50, 50, 50,
                Arrays.asList(Move.getMove("Tackle"), Move.getMove("Leer"))));
        speciesMap.put("dragonair", new Species("Dragonair", "Dragon", "None", 55, 61, 84, 65, 70, 70, 70,
                Arrays.asList(Move.getMove("Dragon Claw"), Move.getMove("Thunder Wave"), Move.getMove("Dragon Dance"), Move.getMove("Surf"))));
        speciesMap.put("dragonite", new Species("Dragonite", "Dragon", "Flying", 0, 91, 134, 95, 100, 100, 80,
                Arrays.asList(Move.getMove("Dragon Claw"), Move.getMove("Fly"), Move.getMove("Thunder Punch"), Move.getMove("Dragon Dance"))));
        speciesMap.put("mewtwo", new Species("Mewtwo", "Psychic", "None", 0, 106, 110, 90, 154, 90, 130,
                Arrays.asList(Move.getMove("Psychic"), Move.getMove("Recover"), Move.getMove("Hypnosis"), Move.getMove("Shadow Ball"))));
        speciesMap.put("mew", new Species("Mew", "Psychic", "None", 0, 100, 100, 100, 100, 100, 100,
                Arrays.asList(Move.getMove("Psychic"), Move.getMove("Ice Beam"), Move.getMove("Thunderbolt"), Move.getMove("Calm Mind"))));
        //MORE POKEMON!
        // Johto Starters
        speciesMap.put("chikorita", new Species("Chikorita", "Grass", "None", 16, 45, 49, 65, 49, 65, 45,
                Arrays.asList(Move.getMove("Tackle"), Move.getMove("Razor Leaf"), Move.getMove("Poison Powder"), Move.getMove("Absorb"))));
        speciesMap.put("bayleef", new Species("Bayleef", "Grass", "None", 32, 60, 62, 80, 63, 80, 60,
                Arrays.asList(Move.getMove("Razor Leaf"), Move.getMove("Body Slam"), Move.getMove("Poison Powder"), Move.getMove("Agility"))));
        speciesMap.put("meganium", new Species("Meganium", "Grass", "None", 0, 80, 82, 100, 83, 100, 80,
                Arrays.asList(Move.getMove("Solar Beam"), Move.getMove("Earthquake"), Move.getMove("Giga Drain"), Move.getMove("Nasty Plot"))));

        speciesMap.put("cyndaquil", new Species("Cyndaquil", "Fire", "None", 14, 39, 52, 43, 60, 50, 65,
                Arrays.asList(Move.getMove("Tackle"), Move.getMove("Ember"), Move.getMove("Quick Attack"), Move.getMove("Growl"))));
        speciesMap.put("quilava", new Species("Quilava", "Fire", "None", 36, 58, 64, 58, 80, 65, 80,
                Arrays.asList(Move.getMove("Flame Wheel"), Move.getMove("Swift"), Move.getMove("Quick Attack"), Move.getMove("Defense Curl"))));
        speciesMap.put("typhlosion", new Species("Typhlosion", "Fire", "None", 0, 78, 84, 78, 109, 85, 100,
                Arrays.asList(Move.getMove("Flamethrower"), Move.getMove("Earthquake"), Move.getMove("Eruption"), Move.getMove("Nasty Plot"))));

        speciesMap.put("totodile", new Species("Totodile", "Water", "None", 18, 50, 65, 64, 44, 48, 43,
                Arrays.asList(Move.getMove("Scratch"), Move.getMove("Water Gun"), Move.getMove("Bite"), Move.getMove("Quick Attack"), Move.getMove("Leer"))));
        speciesMap.put("croconaw", new Species("Croconaw", "Water", "None", 30, 65, 80, 80, 59, 63, 58,
                Arrays.asList(Move.getMove("Slash"), Move.getMove("Ice Fang"), Move.getMove("Crunch"), Move.getMove("Scary Face"))));
        speciesMap.put("feraligatr", new Species("Feraligatr", "Water", "None", 0, 85, 105, 100, 79, 83, 78,
                Arrays.asList(Move.getMove("Hydro Pump"), Move.getMove("Crunch"), Move.getMove("Ice Punch"), Move.getMove("Dragon Dance"))));
        // Togepi Line
        speciesMap.put("togepi", new Species("Togepi", "Fairy", "None", 18, 35, 20, 65, 40, 65, 20,
                Arrays.asList(Move.getMove("Charm"), Move.getMove("Pound"), Move.getMove("Rest"))));
        speciesMap.put("togetic", new Species("Togetic", "Fairy", "Flying", 36, 55, 40, 85, 80, 105, 40,
                Arrays.asList(Move.getMove("Fairy Wind"), Move.getMove("Ancient Power"), Move.getMove("Charm"), Move.getMove("Agility"))));
        speciesMap.put("togekiss", new Species("Togekiss", "Fairy", "Flying", 0, 85, 50, 95, 120, 115, 80,
                Arrays.asList(Move.getMove("Air Slash"), Move.getMove("Moonblast"), Move.getMove("Aura Sphere"), Move.getMove("Recover"))));
        // Eeveelutions
        speciesMap.put("espeon", new Species("Espeon", "Psychic", "None", 0, 65, 65, 60, 130, 95, 110,
                Arrays.asList(Move.getMove("Psychic"), Move.getMove("Calm Mind"), Move.getMove("Shadow Ball"), Move.getMove("Dazzling Gleam"))));
        speciesMap.put("umbreon", new Species("Umbreon", "Dark", "None", 0, 95, 65, 110, 60, 130, 65,
                Arrays.asList(Move.getMove("Foul Play"), Move.getMove("Moonlight"), Move.getMove("Iron Tail"), Move.getMove("Recover"))));
        speciesMap.put("leafeon", new Species("Leafeon", "Grass", "None", 0, 65, 110, 130, 60, 65, 95,
                Arrays.asList(Move.getMove("Leaf Blade"), Move.getMove("Swords Dance"), Move.getMove("X-Scissor"), Move.getMove("Giga Drain"))));
        speciesMap.put("glaceon", new Species("Glaceon", "Ice", "None", 0, 65, 60, 110, 130, 95, 65,
                Arrays.asList(Move.getMove("Ice Beam"), Move.getMove("Snowy Day"), Move.getMove("Shadow Ball"), Move.getMove("Scald"))));
        speciesMap.put("sylveon", new Species("Sylveon", "Fairy", "None", 0, 95, 65, 65, 110, 130, 60,
                Arrays.asList(Move.getMove("Moonblast"), Move.getMove("Mystical Fire"), Move.getMove("Charm"), Move.getMove("Calm Mind"))));
        // Other Johto Pokémon
        speciesMap.put("scizor", new Species("Scizor", "Bug", "Steel", 0, 70, 130, 100, 55, 80, 65,
                Arrays.asList(Move.getMove("Bullet Punch"), Move.getMove("X-Scissor"), Move.getMove("Iron Head"), Move.getMove("Swords Dance"))));

        speciesMap.put("swinub", new Species("Swinub", "Ice", "Ground", 33, 50, 50, 40, 30, 30, 50,
                Arrays.asList(Move.getMove("Powder Snow"), Move.getMove("Mud-Slap"), Move.getMove("Ice Shard"), Move.getMove("Tackle"))));
        speciesMap.put("piloswine", new Species("Piloswine", "Ice", "Ground", 0, 100, 100, 80, 60, 60, 50,
                Arrays.asList(Move.getMove("Ice Fang"), Move.getMove("Earthquake"), Move.getMove("Ancient Power"), Move.getMove("Blizzard"))));
        speciesMap.put("mamoswine", new Species("Mamoswine", "Ice", "Ground", 0, 110, 130, 80, 70, 60, 80,
                Arrays.asList(Move.getMove("Rock Slide"), Move.getMove("Earthquake"), Move.getMove("Icicle Crash"), Move.getMove("Swords Dance"))));

        speciesMap.put("houndour", new Species("Houndour", "Dark", "Fire", 24, 45, 60, 30, 80, 50, 65,
                Arrays.asList(Move.getMove("Ember"), Move.getMove("Bite"), Move.getMove("Quick Attack"), Move.getMove("Growl"))));
        speciesMap.put("houndoom", new Species("Houndoom", "Dark", "Fire", 0, 75, 90, 50, 110, 80, 95,
                Arrays.asList(Move.getMove("Flamethrower"), Move.getMove("Snarl"), Move.getMove("Dark Pulse"), Move.getMove("Agility"))));

        speciesMap.put("hitmontop", new Species("Hitmontop", "Fighting", "None", 0, 50, 95, 95, 35, 110, 70,
                Arrays.asList(Move.getMove("Low Kick"), Move.getMove("Fake Out"), Move.getMove("Close Combat"), Move.getMove("Bulk Up"))));
        // Legendary Beasts
        speciesMap.put("raikou", new Species("Raikou", "Electric", "None", 0, 90, 85, 75, 115, 100, 115,
                Arrays.asList(Move.getMove("Thunderbolt"), Move.getMove("Extreme Speed"), Move.getMove("Calm Mind"), Move.getMove("Agility"))));
        speciesMap.put("entei", new Species("Entei", "Fire", "None", 0, 115, 115, 85, 90, 75, 100,
                Arrays.asList(Move.getMove("Fire Blast"), Move.getMove("Extreme Speed"), Move.getMove("Swords Dance"), Move.getMove("Iron Head"))));
        speciesMap.put("suicune", new Species("Suicune", "Water", "None", 0, 100, 75, 115, 90, 115, 85,
                Arrays.asList(Move.getMove("Hydro Pump"), Move.getMove("Ice Beam"), Move.getMove("Calm Mind"), Move.getMove("Recover"))));
        // Larvitar Line
        speciesMap.put("larvitar", new Species("Larvitar", "Rock", "Ground", 30, 50, 64, 50, 45, 50, 41,
                Arrays.asList(Move.getMove("Bite"), Move.getMove("Rock Slide"), Move.getMove("Screech"), Move.getMove("Sandstorm"))));
        speciesMap.put("pupitar", new Species("Pupitar", "Rock", "Ground", 55, 70, 84, 70, 65, 70, 51,
                Arrays.asList(Move.getMove("Rock Throw"), Move.getMove("Dark Pulse"), Move.getMove("Earthquake"), Move.getMove("Scary Face"))));
        speciesMap.put("tyranitar", new Species("Tyranitar", "Rock", "Dark", 0, 100, 134, 110, 95, 100, 61,
                Arrays.asList(Move.getMove("Rock Slide"), Move.getMove("Crunch"), Move.getMove("Earthquake"), Move.getMove("Dragon Dance"))));
        // Johto Legendaries
        speciesMap.put("lugia", new Species("Lugia", "Psychic", "Flying", 0, 106, 90, 130, 90, 154, 110,
                Arrays.asList(Move.getMove("Aeroblast"), Move.getMove("Psychic"), Move.getMove("Recover"), Move.getMove("Calm Mind"))));
        speciesMap.put("ho-oh", new Species("Ho-Oh", "Fire", "Flying", 0, 106, 130, 90, 110, 154, 90,
                Arrays.asList(Move.getMove("Sacred Fire"), Move.getMove("Brave Bird"), Move.getMove("Recover"), Move.getMove("Nasty Plot"))));
        speciesMap.put("celebi", new Species("Celebi", "Psychic", "Grass", 0, 100, 100, 100, 100, 100, 100,
                Arrays.asList(Move.getMove("Psychic"), Move.getMove("Leaf Storm"), Move.getMove("Recover"), Move.getMove("Calm Mind"))));
        // Hoenn Starters
        speciesMap.put("treecko", new Species("Treecko", "Grass", "None", 16, 40, 45, 35, 65, 55, 70,
                Arrays.asList(Move.getMove("Pound"), Move.getMove("Quick Attack"), Move.getMove("Absorb"), Move.getMove("Agility"))));
        speciesMap.put("grovyle", new Species("Grovyle", "Grass", "None", 36, 50, 65, 45, 85, 65, 95,
                Arrays.asList(Move.getMove("Leaf Blade"), Move.getMove("Quick Attack"), Move.getMove("Fury Cutter"), Move.getMove("Screech"))));
        speciesMap.put("sceptile", new Species("Sceptile", "Grass", "None", 0, 70, 85, 65, 105, 85, 120,
                Arrays.asList(Move.getMove("Leaf Blade"), Move.getMove("X-Scissor"), Move.getMove("Earthquake"), Move.getMove("Swords Dance"))));

        speciesMap.put("torchic", new Species("Torchic", "Fire", "None", 16, 45, 60, 40, 70, 50, 45,
                Arrays.asList(Move.getMove("Scratch"), Move.getMove("Ember"), Move.getMove("Quick Attack"), Move.getMove("Growl"))));
        speciesMap.put("combusken", new Species("Combusken", "Fire", "Fighting", 36, 60, 85, 60, 85, 60, 55,
                Arrays.asList(Move.getMove("Low Kick"), Move.getMove("Fire Punch"), Move.getMove("Drill Peck"), Move.getMove("Bulk Up"))));
        speciesMap.put("blaziken", new Species("Blaziken", "Fire", "Fighting", 0, 80, 120, 70, 110, 70, 80,
                Arrays.asList(Move.getMove("Blaze Kick"), Move.getMove("Close Combat"), Move.getMove("Brave Bird"), Move.getMove("Bulk Up"))));

        speciesMap.put("mudkip", new Species("Mudkip", "Water", "None", 16, 50, 70, 50, 50, 50, 40,
                Arrays.asList(Move.getMove("Tackle"), Move.getMove("Water Gun"), Move.getMove("Mud-Slap"), Move.getMove("Growl"))));
        speciesMap.put("marshtomp", new Species("Marshtomp", "Water", "Ground", 36, 70, 85, 70, 60, 70, 50,
                Arrays.asList(Move.getMove("Mud-Slap"), Move.getMove("Water Pulse"), Move.getMove("Rock Throw"), Move.getMove("Defense Curl"))));
        speciesMap.put("swampert", new Species("Swampert", "Water", "Ground", 0, 100, 110, 90, 85, 90, 60,
                Arrays.asList(Move.getMove("Earthquake"), Move.getMove("Scald"), Move.getMove("Ice Punch"), Move.getMove("Recover"))));
        speciesMap.put("wailmer", new Species("Wailmer", "Water", "None", 40, 130, 70, 35, 70, 35, 60,
                Arrays.asList(Move.getMove("Water Gun"), Move.getMove("Bubble"), Move.getMove("Rain Dance"), Move.getMove("Rest"))));
        speciesMap.put("wailord", new Species("Wailord", "Water", "None", 0, 170, 90, 45, 90, 45, 60,
                Arrays.asList(Move.getMove("Hydro Pump"), Move.getMove("Ice Beam"), Move.getMove("Rain Dance"), Move.getMove("Rest"))));
        // Ralts Line
        speciesMap.put("ralts", new Species("Ralts", "Psychic", "Fairy", 20, 28, 25, 25, 45, 35, 40,
                Arrays.asList(Move.getMove("Confusion"), Move.getMove("Growl"), Move.getMove("Charm"), Move.getMove("Agility"))));
        speciesMap.put("kirlia", new Species("Kirlia", "Psychic", "Fairy", 30, 38, 35, 35, 65, 55, 50,
                Arrays.asList(Move.getMove("Psychic"), Move.getMove("Dazzling Gleam"), Move.getMove("Calm Mind"), Move.getMove("Magical Leaf"))));
        speciesMap.put("gardevoir", new Species("Gardevoir", "Psychic", "Fairy", 0, 68, 65, 65, 125, 115, 80,
                Arrays.asList(Move.getMove("Moonblast"), Move.getMove("Psychic"), Move.getMove("Calm Mind"), Move.getMove("Shadow Ball"))));

        speciesMap.put("zangoose", new Species("Zangoose", "Normal", "None", 0, 73, 115, 60, 60, 60, 90,
                Arrays.asList(Move.getMove("Slash"), Move.getMove("Quick Attack"), Move.getMove("Swords Dance"), Move.getMove("Close Combat"))));
        // Bagon Line
        speciesMap.put("bagon", new Species("Bagon", "Dragon", "None", 30, 45, 75, 60, 40, 30, 50,
                Arrays.asList(Move.getMove("Headbutt"), Move.getMove("Dragon Breath"), Move.getMove("Growl"), Move.getMove("Ember"))));
        speciesMap.put("shelgon", new Species("Shelgon", "Dragon", "None", 50, 65, 95, 100, 60, 50, 50,
                Arrays.asList(Move.getMove("Dragon Claw"), Move.getMove("Zen Headbutt"), Move.getMove("Protect"), Move.getMove("Fire Fang"))));
        speciesMap.put("salamence", new Species("Salamence", "Dragon", "Flying", 0, 95, 135, 80, 110, 80, 100,
                Arrays.asList(Move.getMove("Dragon Claw"), Move.getMove("Fly"), Move.getMove("Fire Blast"), Move.getMove("Hydro Pump"))));
        // Beldum Line
        speciesMap.put("beldum", new Species("Beldum", "Steel", "Psychic", 20, 40, 55, 80, 35, 60, 30,
                Arrays.asList(Move.getMove("Tackle"), Move.getMove("Iron Defense"), Move.getMove("Zen Headbutt"))));
        speciesMap.put("metang", new Species("Metang", "Steel", "Psychic", 45, 60, 75, 100, 55, 80, 50,
                Arrays.asList(Move.getMove("Metal Claw"), Move.getMove("Zen Headbutt"), Move.getMove("Bullet Punch"), Move.getMove("Agility"))));
        speciesMap.put("metagross", new Species("Metagross", "Steel", "Psychic", 0, 80, 135, 130, 95, 90, 70,
                Arrays.asList(Move.getMove("Meteor Mash"), Move.getMove("Zen Headbutt"), Move.getMove("Earthquake"), Move.getMove("Bullet Punch"))));
        // Hoenn Legendaries
        speciesMap.put("latias", new Species("Latias", "Dragon", "Psychic", 0, 80, 80, 90, 110, 130, 110,
                Arrays.asList(Move.getMove("Draco Meteor"), Move.getMove("Luster Purge"), Move.getMove("Dazzling Gleam"), Move.getMove("Calm Mind"))));
        speciesMap.put("latios", new Species("Latios", "Dragon", "Psychic", 0, 80, 90, 80, 130, 110, 110,
                Arrays.asList(Move.getMove("Dragon Pulse"), Move.getMove("Draco Meteor"), Move.getMove("Psychic"), Move.getMove("Calm Mind"))));
        speciesMap.put("kyogre", new Species("Kyogre", "Water", "None", 0, 100, 100, 90, 150, 140, 90,
                Arrays.asList(Move.getMove("Origin Pulse"), Move.getMove("Thunder"), Move.getMove("Ice Beam"), Move.getMove("Water Spout"))));
        speciesMap.put("groudon", new Species("Groudon", "Ground", "None", 0, 100, 150, 140, 100, 90, 90,
                Arrays.asList(Move.getMove("Earthquake"), Move.getMove("Eruption"), Move.getMove("Solar Beam"), Move.getMove("Bulk Up"))));
        speciesMap.put("rayquaza", new Species("Rayquaza", "Dragon", "Flying", 0, 105, 150, 90, 150, 90, 95,
                Arrays.asList(Move.getMove("Dragon Ascent"), Move.getMove("Dragon Claw"), Move.getMove("Extreme Speed"), Move.getMove("Swords Dance"))));
        speciesMap.put("jirachi", new Species("Jirachi", "Steel", "Psychic", 0, 100, 100, 100, 100, 100, 100,
                Arrays.asList(Move.getMove("Flash Cannon"), Move.getMove("Psychic"), Move.getMove("Rest"), Move.getMove("Calm Mind"))));
        speciesMap.put("deoxys", new Species("Deoxys", "Psychic", "None", 0, 50, 150, 50, 150, 50, 150,
                Arrays.asList(Move.getMove("Psycho Boost"), Move.getMove("Extreme Speed"), Move.getMove("Close Combat"), Move.getMove("Ice Beam"))));
        // Gible Line
        speciesMap.put("gible", new Species("Gible", "Dragon", "Ground", 24, 58, 70, 45, 40, 45, 42,
                Arrays.asList(Move.getMove("Dragon Rage"), Move.getMove("Sand Tomb"), Move.getMove("Quick Attack"), Move.getMove("Defense Curl"))));
        speciesMap.put("gabite", new Species("Gabite", "Dragon", "Ground", 48, 68, 90, 65, 50, 55, 82,
                Arrays.asList(Move.getMove("Dragon Claw"), Move.getMove("Dig"), Move.getMove("Slash"), Move.getMove("Dragon Dance"))));
        speciesMap.put("garchomp", new Species("Garchomp", "Dragon", "Ground", 0, 108, 130, 95, 80, 85, 102,
                Arrays.asList(Move.getMove("Dragon Claw"), Move.getMove("Earthquake"), Move.getMove("Rock Slide"), Move.getMove("Dragon Dance"))));
        speciesMap.put("rhyperior", new Species("Rhyperior", "Ground", "Rock", 0, 115, 140, 130, 55, 55, 40,
                Arrays.asList(Move.getMove("Rock Slide"), Move.getMove("Earthquake"), Move.getMove("Megahorn"), Move.getMove("Surf"))));
        // Riolu Line
        speciesMap.put("riolu", new Species("Riolu", "Fighting", "None", 30, 40, 70, 40, 35, 40, 60,
                Arrays.asList(Move.getMove("Quick Attack"), Move.getMove("Mach Punch"), Move.getMove("Defense Curl"), Move.getMove("Bullet Punch"))));
        speciesMap.put("lucario", new Species("Lucario", "Fighting", "Steel", 0, 70, 110, 70, 115, 70, 90,
                Arrays.asList(Move.getMove("Bullet Punch"), Move.getMove("Close Combat"), Move.getMove("Extreme Speed"), Move.getMove("Swords Dance"))));
        // Sinnoh Legendaries
        speciesMap.put("dialga", new Species("Dialga", "Steel", "Dragon", 0, 100, 120, 120, 150, 100, 90,
                Arrays.asList(Move.getMove("Draco Meteor"), Move.getMove("Flash Cannon"), Move.getMove("Earth Power"), Move.getMove("Bulk Up"))));
        speciesMap.put("palkia", new Species("Palkia", "Water", "Dragon", 0, 90, 120, 100, 150, 120, 100,
                Arrays.asList(Move.getMove("Spacial Rend"), Move.getMove("Hydro Pump"), Move.getMove("Earth Power"), Move.getMove("Trick Room"))));
        speciesMap.put("giratina", new Species("Giratina", "Ghost", "Dragon", 0, 150, 100, 120, 100, 120, 90,
                Arrays.asList(Move.getMove("Shadow Force"), Move.getMove("Dragon Claw"), Move.getMove("Earth Power"), Move.getMove("Calm Mind"))));
        speciesMap.put("darkrai", new Species("Darkrai", "Dark", "None", 0, 70, 90, 90, 135, 90, 125,
                Arrays.asList(Move.getMove("Hypnosis"), Move.getMove("Dark Pulse"), Move.getMove("Nasty Plot"), Move.getMove("Calm Mind"))));
        speciesMap.put("arceus", new Species("Arceus", "Normal", "None", 0, 120, 120, 120, 120, 120, 120,
                Arrays.asList(Move.getMove("Judgment"), Move.getMove("Recover"), Move.getMove("Extreme Speed"), Move.getMove("Calm Mind"))));
        //VICTINI
        speciesMap.put("victini", new Species("Victini", "Psychic", "Fire", 0, 100, 100, 100, 100, 100, 100,
                Arrays.asList(Move.getMove("V-create"), Move.getMove("Zen Headbutt"), Move.getMove("Wild Charge"), Move.getMove("Bulk Up"))));
        // Drilbur Line
        speciesMap.put("drilbur", new Species("Drilbur", "Ground", "None", 31, 60, 85, 40, 30, 45, 68,
                Arrays.asList(Move.getMove("Dig"), Move.getMove("Slash"), Move.getMove("Metal Claw"), Move.getMove("Defense Curl"))));
        speciesMap.put("excadrill", new Species("Excadrill", "Ground", "Steel", 0, 110, 135, 60, 50, 65, 88,
                Arrays.asList(Move.getMove("Earthquake"), Move.getMove("Iron Head"), Move.getMove("Rock Slide"), Move.getMove("Swords Dance"))));
        // Zorua Line
        speciesMap.put("zorua", new Species("Zorua", "Dark", "None", 30, 40, 65, 40, 80, 40, 65,
                Arrays.asList(Move.getMove("Foul Play"), Move.getMove("Quick Attack"), Move.getMove("Nasty Plot"), Move.getMove("Shadow Claw"))));
        speciesMap.put("zoroark", new Species("Zoroark", "Dark", "None", 0, 60, 105, 60, 120, 60, 105,
                Arrays.asList(Move.getMove("Dark Pulse"), Move.getMove("Aura Sphere"), Move.getMove("Flash Cannon"), Move.getMove("Nasty Plot"))));
        // Foongus Line
        speciesMap.put("foongus", new Species("Foongus", "Grass", "Poison", 39, 69, 55, 45, 55, 55, 15,
                Arrays.asList(Move.getMove("Absorb"), Move.getMove("Toxic"), Move.getMove("Spore"), Move.getMove("Mega Drain"))));
        speciesMap.put("amoonguss", new Species("Amoonguss", "Grass", "Poison", 0, 114, 85, 70, 85, 80, 30,
                Arrays.asList(Move.getMove("Spore"), Move.getMove("Giga Drain"), Move.getMove("Sludge Bomb"), Move.getMove("Poison Powder"))));
        // Unova Legendaries
        speciesMap.put("reshiram", new Species("Reshiram", "Dragon", "Fire", 0, 100, 120, 100, 150, 120, 90,
                Arrays.asList(Move.getMove("Fusion Flare"), Move.getMove("Dragon Pulse"), Move.getMove("Earth Power"), Move.getMove("Will-O-Wisp"))));
        speciesMap.put("zekrom", new Species("Zekrom", "Dragon", "Electric", 0, 100, 150, 120, 120, 100, 90,
                Arrays.asList(Move.getMove("Fusion Bolt"), Move.getMove("Dragon Claw"), Move.getMove("Draco Meteor"), Move.getMove("Volt Switch"))));
        // Alola
        speciesMap.put("litten", new Species("Litten", "Fire", "None", 17, 45, 65, 40, 60, 40, 70,
                Arrays.asList(Move.getMove("Ember"), Move.getMove("Scratch"), Move.getMove("Lick"), Move.getMove("Fire Fang"))));
        speciesMap.put("torracat", new Species("Torracat", "Fire", "None", 34, 65, 85, 50, 80, 50, 90,
                Arrays.asList(Move.getMove("Fire Fang"), Move.getMove("Bite"), Move.getMove("Double"), Move.getMove("Flame Charge"))));
        speciesMap.put("incineroar", new Species("Incineroar", "Fire", "Dark", 0, 95, 115, 90, 80, 90, 60,
                Arrays.asList(Move.getMove("Fake Out"), Move.getMove("Flare Blitz"), Move.getMove("Parting Shot"), Move.getMove("Will-O-Wisp"))));
        // Galar Pokémon
        speciesMap.put("rookidee", new Species("Rookidee", "Flying", "None", 18, 38, 47, 35, 33, 35, 57,
                Arrays.asList(Move.getMove("Peck"), Move.getMove("Leer"), Move.getMove("Quick Attack"), Move.getMove("Agility"))));
        speciesMap.put("corvisquire", new Species("Corvisquire", "Flying", "None", 38, 68, 67, 55, 43, 55, 77,
                Arrays.asList(Move.getMove("Drill Peck"), Move.getMove("Iron Defense"), Move.getMove("Wing Attack"), Move.getMove("Scary Face"))));
        speciesMap.put("corviknight", new Species("Corviknight", "Flying", "Steel", 0, 98, 87, 105, 53, 85, 67,
                Arrays.asList(Move.getMove("Brave Bird"), Move.getMove("Iron Head"), Move.getMove("Bulk Up"), Move.getMove("Recover"))));
        // Dreepy Line
        speciesMap.put("dreepy", new Species("Dreepy", "Dragon", "Ghost", 50, 28, 55, 30, 25, 25, 82,
                Arrays.asList(Move.getMove("Quick Attack"), Move.getMove("Bite"), Move.getMove("Dragon Rage"), Move.getMove("Shadow Sneak"))));
        speciesMap.put("drakloak", new Species("Drakloak", "Dragon", "Ghost", 60, 68, 80, 50, 60, 50, 102,
                Arrays.asList(Move.getMove("Dragon Pulse"), Move.getMove("Shadow Ball"), Move.getMove("Shadow Claw"), Move.getMove("Dragon Dance"))));
        speciesMap.put("dragapult", new Species("Dragapult", "Dragon", "Ghost", 0, 88, 120, 75, 100, 75, 142,
                Arrays.asList(Move.getMove("Dragon Darts"), Move.getMove("Shadow Claw"), Move.getMove("Draco Meteor"), Move.getMove("Dragon Dance"))));
    }
    public static final HashMap<String, Integer> baseStatTotals = new HashMap<>();
    static {
        for (String pkmStr : speciesListedInPokedexOrder) {
            baseStatTotals.put(pkmStr, getSpecies(pkmStr).getBST());
        }
    }
    public static Species getSpecies(String speciesName) {
            return speciesMap.getOrDefault(speciesName.toLowerCase(), speciesMap.get("default"));
        }
    @Override
    public String toString() {
        if (name.equalsIgnoreCase("MissingNo")){
            return name;
        } else {
            return name + " | Type: " + type1 + " | HP: " + baseHp + " | Attack: " + baseAttack + " | Defense: " + baseDefense + " | Sp.Atk: " + baseSpAtk + " | Sp.Def: " + baseSpDef + " | Speed: " + baseSpeed + " | BST: " + (baseHp + baseAttack + baseDefense + baseSpAtk + baseSpDef + baseSpeed);
        }
    }
}