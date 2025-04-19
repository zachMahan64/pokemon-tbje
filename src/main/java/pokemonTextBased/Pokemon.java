package pokemonTextBased;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    private String name, type1, type2;
    private int level;
    private int baseHp, baseAttack, baseDefense, baseSpAtk, baseSpDef, baseSpeed;
    private int currentHp, currentMaxHp, currentAttack, currentDefense, currentSpAtk, currentSpDef, currentSpeed;
    private List<Move> moves;  // List of moves currently available to this Pokemon
    private final boolean shiny;
    private String statusCondition; // None, Sleep, Paralysis, Poison, Burn
    int attackStage, defenseStage, spAtkStage, spDefStage, speedStage;
    private boolean isFlinched = false;
    private boolean skipNextTurn = false;
    private int wakeUpTurn;
    private int rechargeTurn;
    private int turnSentOut;
    private boolean isFoe;
    private int timesSwitchedInBattle = 0;
    // Constructors
    public Pokemon(Species species, int level, boolean shiny) {
        this.name = species.getName(); this.level = level; this.type1 = species.getType1(); this.type2 = species.getType2();
        this.baseHp = species.getBaseHp(); this.baseAttack = species.getBaseAttack(); this.baseDefense = species.getBaseDefense();
        this.baseSpAtk = species.getBaseSpAtk(); this.baseSpDef = species.getBaseSpDef(); this.baseSpeed = species.getBaseSpeed();

        this.currentHp = (int) (10 + baseHp * (level/50.0)); this.currentMaxHp = (int) (10 + baseHp * (level/50.0)); this.currentAttack = (int) (5 + baseAttack * (level/50.0));
        this.currentDefense = (int) (5 + baseDefense * (level/50.0)); this.currentSpAtk = (int) (5 + baseSpAtk * (level/50.0)); this.currentSpDef = (int) (5 + baseSpDef * (level/50.0));
        this.currentSpeed = (int) (5 + baseSpeed * (level/50.0));

        this.moves = species.getMoves();

        this.shiny = shiny;
        this.turnSentOut = 0;
        this.wakeUpTurn = 0;
        this.statusCondition = "None";
        this.isFoe = true;
        resetStages();
    }
    public Pokemon(Species species, int level) {
        this(species, level, Pokemon.getShinyOdds());
    }
    public Pokemon(String speciesName, int level, boolean shiny) {
        this(Species.getSpecies(speciesName), level, shiny);
    }
    public Pokemon(String speciesName, int level) {
        this(Species.getSpecies(speciesName), level, Pokemon.getShinyOdds());
    }
    public static void healPokemon(Pokemon pokemon) {
        pokemon.currentHp = (int) (10.0 + pokemon.baseHp * (pokemon.level / 50.0));
        pokemon.currentMaxHp = (int) (10.0 + pokemon.baseHp * (pokemon.level / 50.0));
        pokemon.currentAttack = (int) (5.0 + pokemon.baseAttack * (pokemon.level / 50.0));
        pokemon.currentDefense = (int) (5.0 + pokemon.baseDefense * (pokemon.level / 50.0));
        pokemon.currentSpAtk = (int) (5.0 + pokemon.baseSpAtk * (pokemon.level / 50.0));
        pokemon.currentSpDef = (int) (5.0 + pokemon.baseSpDef * (pokemon.level / 50.0));
        pokemon.currentSpeed = (int) (5.0 + pokemon.baseSpeed * (pokemon.level / 50.0));
        // Reset PP for each move
        for (Move move : pokemon.moves) {
            Move.resetMovePp(move);
        }
        pokemon.statusCondition = "None";
        pokemon.setFlinched(false);
        pokemon.setSkipNextTurn(false);
        pokemon.resetStages();
    }
    public static void resetPokemonsNonHpStats(Pokemon pokemon) {
        pokemon.currentMaxHp = (int) (10.0 + pokemon.baseHp * (pokemon.level / 50.0));
        pokemon.currentAttack = (int) (5.0 + pokemon.baseAttack * (pokemon.level / 50.0));
        pokemon.currentDefense = (int) (5.0 + pokemon.baseDefense * (pokemon.level / 50.0));
        pokemon.currentSpAtk = (int) (5.0 + pokemon.baseSpAtk * (pokemon.level / 50.0));
        pokemon.currentSpDef = (int) (5.0 + pokemon.baseSpDef * (pokemon.level / 50.0));
        pokemon.currentSpeed = (int) (5.0 + pokemon.baseSpeed * (pokemon.level / 50.0));
        pokemon.statusCondition = "None";
        pokemon.setFlinched(false);
        pokemon.setSkipNextTurn(false);
        pokemon.setTurnSentOut(0);
        pokemon.resetStages();
        pokemon.setTimesSwitchedInBattle(0);
    }
    public Pokemon clone() {
        Pokemon cloned = new Pokemon(Species.getSpecies(this.name), this.level, this.shiny);

        cloned.currentHp = this.currentHp;
        cloned.currentMaxHp = this.currentMaxHp;
        cloned.currentAttack = this.currentAttack;
        cloned.currentDefense = this.currentDefense;
        cloned.currentSpAtk = this.currentSpAtk;
        cloned.currentSpDef = this.currentSpDef;
        cloned.currentSpeed = this.currentSpeed;

        cloned.moves = new ArrayList<>();
        for (Move move : this.moves) {
            cloned.moves.add(new Move(move)); // Deep copy each move
        }

        cloned.statusCondition = this.statusCondition;
        cloned.attackStage = this.attackStage;
        cloned.defenseStage = this.defenseStage;
        cloned.spAtkStage = this.spAtkStage;
        cloned.spDefStage = this.spDefStage;
        cloned.speedStage = this.speedStage;
        cloned.isFlinched = this.isFlinched;
        cloned.skipNextTurn = this.skipNextTurn;
        cloned.wakeUpTurn = this.wakeUpTurn;
        cloned.rechargeTurn = this.rechargeTurn;
        cloned.turnSentOut = this.turnSentOut;
        cloned.isFoe = this.isFoe;

        return cloned;
    }

    // Getter for moves
    public List<Move> getMoves() {
        return this.moves;
    }
    // Method to get a move by name (for user input)
    public Move getMove(String moveName) {
        for (Move move : moves) {
            if (move.getName().equalsIgnoreCase(moveName)) {
                return move;  // Return the move if names match
            }
        }
        return null;  // Return null if no matching move found
    }


    // Getters for base stats
    public String getName() {
        return name;
    }
    public String getType1() {
        return type1;
    }
    public String getType2() {
        return type2;
    }
    public String getTyping() {
        if(type2.equals("None")) return type1;
        else return type1 + "/" + type2;
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
    public int getBaseSpDef() {
        return baseSpDef;
    }
    public int getBaseSpAtk() {
        return baseSpAtk;
    }
    public int getBaseSpeed() {
        return baseSpeed;
    }
    public int getBST() {
        return (baseHp + baseAttack + baseDefense + baseSpAtk + baseSpDef + baseSpeed);
    }
    // Getters for current stats
    public int getLevel() {
        return level;
    }
    public int getCurrentHp() {
        return currentHp;
    }
    public int getCurrentMaxHp() { return currentMaxHp; }
    public int getCurrentAttack() {
        return currentAttack;
    }
    public int getCurrentDefense() {
        return currentDefense;
    }
    public int getCurrentSpAtk() {
        return currentSpAtk;
    }
    public int getCurrentSpDef() {
        return currentSpDef;
    }
    public int getCurrentSpeed() {
        return currentSpeed;
    }
    public String getStatusCondition() {
        return statusCondition;
    }


    // Setters for current stats
    public void setCurrentHp(int newHp) {
        this.currentHp = Math.max(0, Math.min(newHp, currentMaxHp)); // Ensure HP doesn't go below 0 or exceed baseHp, make this use hp calc formula
    }
    public void setCurrentAttack(int newAttack) {
        this.currentAttack = newAttack;
    }
    public void setCurrentDefense(int newDefense) {
        this.currentDefense = newDefense;
    }
    public void setCurrentSpAtk(int newSpAtk) {
        this.currentSpAtk = newSpAtk;
    }
    public void setCurrentSpDef(int newSpDef) {
        this.currentSpDef = newSpDef;
    }
    public void setCurrentSpeed(int newSpeed) {
        this.currentSpeed = newSpeed;
    }
    public void setLevel(int newLevel) {
        this.level = newLevel;
    }
    public void setStatusCondition(String statusCondition) {this.statusCondition = statusCondition; }
    //shiny logic
    public static boolean getShinyOdds() {
        return Math.random() * 256.0 <= 1;
    }
    public boolean isShiny() {
        return shiny;
    }
    // stuff for stat changes
    public void resetStages() {
        attackStage = 0; defenseStage = 0; spAtkStage = 0; spDefStage = 0; speedStage = 0;
    }
    public int clampStage(int stage) {
        return Math.max(-6, Math.min(6, stage));
    }
    public static final double[] STAGE_MULTIPLIERS = {0.25, 0.28, 0.33, 0.40, 0.50, 0.66, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0};
    public double getMultiplier(int stage) {
        return STAGE_MULTIPLIERS[stage + 6];
    }
    public void changeStatStage(String stat, int change) {
        switch (stat.toLowerCase()) {
            case "attack":
                attackStage = clampStage(attackStage + change);
                break;
            case "defense":
                defenseStage = clampStage(defenseStage + change);
                break;
            case "spatk":
                spAtkStage = clampStage(spAtkStage + change);
                break;
            case "spdef":
                spDefStage = clampStage(spDefStage + change);
                break;
            case "speed":
                speedStage = clampStage(speedStage + change);
                break;
        }
    }
    public double getStatMultiplier(String stat) {
        return switch (stat.toLowerCase()) {
            case "attack" -> getMultiplier(attackStage);
            case "defense" -> getMultiplier(defenseStage);
            case "spatk" -> getMultiplier(spAtkStage);
            case "spdef" -> getMultiplier(spDefStage);
            case "speed" -> getMultiplier(speedStage);
            default -> 1.0;
        };
    }
    public boolean isFlinched() {
        return isFlinched;
    }
    public void setFlinched(boolean flinched) {
        isFlinched = flinched;
    }
    public boolean isSkipNextTurn() {
        return skipNextTurn;
    }
    public void setSkipNextTurn(boolean skipNextTurn) {
        this.skipNextTurn = skipNextTurn;
    }
    public int getWakeUpTurn() {
        return wakeUpTurn;
    }
    public void setWakeUpTurnFor3TurnsInTheFuture(Arena arena) {
        this.wakeUpTurn = arena.turnNum + 3;
    }
    public void setWakeUpTurnToTurnNum(Arena arena) {
        this.wakeUpTurn = arena.turnNum;
    }
    public int getRechargeTurn() {
        return rechargeTurn;
    }
    public void setRechargeTurn(Arena arena) {
        this.rechargeTurn = arena.turnNum + 1;
    }
    public int getTurnSentOut() {
        return turnSentOut;
    }
    public void setTurnSentOut(int turnSentOut) {
        this.turnSentOut = turnSentOut;
    }
    //evolution stuff
    public static boolean checkIfPokemonCanEvolve(Pokemon pokemon) {
        Species species = Species.getSpecies(pokemon.getName());
        int evolutionLevel = species.getEvolutionLevel();
        return evolutionLevel > 0 && pokemon.getLevel() >= evolutionLevel;
    }
    public static void evolvePokemon(Pokemon pokemon, Species newSpecies) {
        int currentHpRatio = pokemon.getCurrentHp()/ pokemon.getCurrentMaxHp();
        pokemon.name = newSpecies.getName();
        pokemon.type1 = newSpecies.getType1();
        pokemon.type2 = newSpecies.getType2();

        pokemon.baseHp = newSpecies.getBaseHp();
        pokemon.baseAttack = newSpecies.getBaseAttack();
        pokemon.baseDefense = newSpecies.getBaseDefense();
        pokemon.baseSpAtk = newSpecies.getBaseSpAtk();
        pokemon.baseSpDef = newSpecies.getBaseSpDef();
        pokemon.baseSpeed = newSpecies.getBaseSpeed();

        pokemon.currentMaxHp = (int) (10 + pokemon.baseHp * (pokemon.getLevel() / 50.0));
        pokemon.currentHp = Math.min(pokemon.getCurrentMaxHp()*currentHpRatio, pokemon.currentMaxHp); // Keep current HP proportional
        pokemon.currentAttack = (int) (5 + pokemon.baseAttack * (pokemon.getLevel() / 50.0));
        pokemon.currentDefense = (int) (5 + pokemon.baseDefense * (pokemon.getLevel() / 50.0));
        pokemon.currentSpAtk = (int) (5 + pokemon.baseSpAtk * (pokemon.getLevel() / 50.0));
        pokemon.currentSpDef = (int) (5 + pokemon.baseSpDef * (pokemon.getLevel() / 50.0));
        pokemon.currentSpeed = (int) (5 + pokemon.baseSpeed * (pokemon.getLevel() / 50.0));

        pokemon.moves = newSpecies.getMoves(); // Update move list based on the new species
    }
    public static void tryEvolvePokemon(Pokemon pokemon) throws InterruptedException{
        String originalName = pokemon.getName();
        Species newSpecies = null;
        switch (originalName) {
            case "Bulbasaur": newSpecies = Species.getSpecies("Ivysaur"); break;
            case "Ivysaur": newSpecies = Species.getSpecies("Venusaur"); break;
            case "Charmander": newSpecies = Species.getSpecies("Charmeleon"); break;
            case "Charmeleon": newSpecies = Species.getSpecies("Charizard"); break;
            case "Squirtle": newSpecies = Species.getSpecies("Wartortle"); break;
            case "Wartortle": newSpecies = Species.getSpecies("Blastoise"); break;
            case "Caterpie": newSpecies = Species.getSpecies("Metapod"); break;
            case "Metapod": newSpecies = Species.getSpecies("Butterfree"); break;
            case "Weedle": newSpecies = Species.getSpecies("Kakuna"); break;
            case "Kakuna": newSpecies = Species.getSpecies("Beedrill"); break;
            case "Pidgey": newSpecies = Species.getSpecies("Pidgeotto"); break;
            case "Pidgeotto": newSpecies = Species.getSpecies("Pidgeot"); break;
            case "Rattata": newSpecies = Species.getSpecies("Raticate"); break;
            case "Spearow": newSpecies = Species.getSpecies("Fearow"); break;
            case "Ekans": newSpecies = Species.getSpecies("Arbok"); break;
            case "Pikachu": newSpecies = Species.getSpecies("Raichu"); break;
            case "Sandshrew": newSpecies = Species.getSpecies("Sandslash"); break;
            case "NidoranF": newSpecies = Species.getSpecies("Nidorina"); break;
            case "Nidorina": newSpecies = Species.getSpecies("Nidoqueen"); break;
            case "NidoranM": newSpecies = Species.getSpecies("Nidorino"); break;
            case "Nidorino": newSpecies = Species.getSpecies("Nidoking"); break;
            case "Clefairy": newSpecies = Species.getSpecies("Clefable"); break;
            case "Vulpix": newSpecies = Species.getSpecies("Ninetales"); break;
            case "Jigglypuff": newSpecies = Species.getSpecies("Wigglytuff"); break;
            case "Zubat": newSpecies = Species.getSpecies("Golbat"); break;
            case "Oddish": newSpecies = Species.getSpecies("Gloom"); break;
            case "Gloom": newSpecies = Species.getSpecies("Vileplume"); break;
            case "Paras": newSpecies = Species.getSpecies("Parasect"); break;
            case "Venonat": newSpecies = Species.getSpecies("Venomoth"); break;
            case "Diglett": newSpecies = Species.getSpecies("Dugtrio"); break;
            case "Meowth": newSpecies = Species.getSpecies("Persian"); break;
            case "Psyduck": newSpecies = Species.getSpecies("Golduck"); break;
            case "Mankey": newSpecies = Species.getSpecies("Primeape"); break;
            case "Growlithe": newSpecies = Species.getSpecies("Arcanine"); break;
            case "Poliwag": newSpecies = Species.getSpecies("Poliwhirl"); break;
            case "Poliwhirl": newSpecies = Species.getSpecies("Poliwrath"); break;
            case "Abra": newSpecies = Species.getSpecies("Kadabra"); break;
            case "Kadabra": newSpecies = Species.getSpecies("Alakazam"); break;
            case "Machop": newSpecies = Species.getSpecies("Machoke"); break;
            case "Machoke": newSpecies = Species.getSpecies("Machamp"); break;
            case "Bellsprout": newSpecies = Species.getSpecies("Weepinbell"); break;
            case "Weepinbell": newSpecies = Species.getSpecies("Victreebel"); break;
            case "Tentacool": newSpecies = Species.getSpecies("Tentacruel"); break;
            case "Geodude": newSpecies = Species.getSpecies("Graveler"); break;
            case "Graveler": newSpecies = Species.getSpecies("Golem"); break;
            case "Ponyta": newSpecies = Species.getSpecies("Rapidash"); break;
            case "Slowpoke": newSpecies = Species.getSpecies("Slowbro"); break;
            case "Magnemite": newSpecies = Species.getSpecies("Magneton"); break;
            case "Doduo": newSpecies = Species.getSpecies("Dodrio"); break;
            case "Seel": newSpecies = Species.getSpecies("Dewgong"); break;
            case "Grimer": newSpecies = Species.getSpecies("Muk"); break;
            case "Shellder": newSpecies = Species.getSpecies("Cloyster"); break;
            case "Gastly": newSpecies = Species.getSpecies("Haunter"); break;
            case "Haunter": newSpecies = Species.getSpecies("Gengar"); break;
            case "Drowzee": newSpecies = Species.getSpecies("Hypno"); break;
            case "Krabby": newSpecies = Species.getSpecies("Kingler"); break;
            case "Voltorb": newSpecies = Species.getSpecies("Electrode"); break;
            case "Exeggcute": newSpecies = Species.getSpecies("Exeggutor"); break;
            case "Cubone": newSpecies = Species.getSpecies("Marowak"); break;
            case "Koffing": newSpecies = Species.getSpecies("Weezing"); break;
            case "Rhyhorn": newSpecies = Species.getSpecies("Rhydon"); break;
            case "Horsea": newSpecies = Species.getSpecies("Seadra"); break;
            case "Goldeen": newSpecies = Species.getSpecies("Seaking"); break;
            case "Staryu": newSpecies = Species.getSpecies("Starmie"); break;
            case "Magikarp": newSpecies = Species.getSpecies("Gyarados"); break;
            case "Eevee": return; // Requires specific evolution conditions
            case "Omanyte": newSpecies = Species.getSpecies("Omastar"); break;
            case "Kabuto": newSpecies = Species.getSpecies("Kabutops"); break;
            case "Dratini": newSpecies = Species.getSpecies("Dragonair"); break;
            case "Dragonair": newSpecies = Species.getSpecies("Dragonite"); break;
            case "Chikorita": newSpecies = Species.getSpecies("Bayleef"); break;
            case "Bayleef": newSpecies = Species.getSpecies("Meganium"); break;
            case "Cyndaquil": newSpecies = Species.getSpecies("Quilava"); break;
            case "Quilava": newSpecies = Species.getSpecies("Typhlosion"); break;
            case "Totodile": newSpecies = Species.getSpecies("Croconaw"); break;
            case "Croconaw": newSpecies = Species.getSpecies("Feraligatr"); break;
            case "Togepi": newSpecies = Species.getSpecies("Togetic"); break;
            case "Scyther": newSpecies = Species.getSpecies("Scizor"); break;
            case "Togetic": newSpecies = Species.getSpecies("Togekiss"); break;
            case "Swinub": newSpecies = Species.getSpecies("Piloswine"); break;
            case "Piloswine": newSpecies = Species.getSpecies("Mamoswine"); break;
            case "Houndour": newSpecies = Species.getSpecies("Houndoom"); break;
            case "Larvitar": newSpecies = Species.getSpecies("Pupitar"); break;
            case "Pupitar": newSpecies = Species.getSpecies("Tyranitar"); break;
            case "Treecko": newSpecies = Species.getSpecies("Grovyle"); break;
            case "Grovyle": newSpecies = Species.getSpecies("Sceptile"); break;
            case "Torchic": newSpecies = Species.getSpecies("Combusken"); break;
            case "Combusken": newSpecies = Species.getSpecies("Blaziken"); break;
            case "Mudkip": newSpecies = Species.getSpecies("Marshtomp"); break;
            case "Marshtomp": newSpecies = Species.getSpecies("Swampert"); break;
            case "Ralts": newSpecies = Species.getSpecies("Kirlia"); break;
            case "Kirlia": newSpecies = Species.getSpecies("Gardevoir"); break;
            case "Bagon": newSpecies = Species.getSpecies("Shelgon"); break;
            case "Shelgon": newSpecies = Species.getSpecies("Salamence"); break;
            case "Beldum": newSpecies = Species.getSpecies("Metang"); break;
            case "Metang": newSpecies = Species.getSpecies("Metagross"); break;
            case "Gible": newSpecies = Species.getSpecies("Gabite"); break;
            case "Gabite": newSpecies = Species.getSpecies("Garchomp"); break;
            case "Rhydon": newSpecies = Species.getSpecies("Rhyperior"); break;
            case "Riolu": newSpecies = Species.getSpecies("Lucario"); break;
            case "Drilbur": newSpecies = Species.getSpecies("Excadrill"); break;
            case "Zorua": newSpecies = Species.getSpecies("Zoroark"); break;
            case "Foongus": newSpecies = Species.getSpecies("Amoonguss"); break;
            case "Litten": newSpecies = Species.getSpecies("Torracat"); break;
            case "Torracat": newSpecies = Species.getSpecies("Incineroar"); break;
            case "Rookidee": newSpecies = Species.getSpecies("Corvisquire"); break;
            case "Corvisquire": newSpecies = Species.getSpecies("Corviknight"); break;
            case "Dreepy": newSpecies = Species.getSpecies("Drakloak"); break;
            case "Drakloak": newSpecies = Species.getSpecies("Dragapult"); break;
            default: return; // No evolution
        }
        if (checkIfPokemonCanEvolve(pokemon) && newSpecies != null) {
            evolvePokemon(pokemon, newSpecies);
            Graphics.printPokemon(pokemon);
            System.out.println("Congratulations! Your " + originalName + " evolved into " + newSpecies.getName() + "!\n");
            Sound.playSoundOnce("main/music/levelUp.wav");
            Game.pressEnterToContinue();
        }
    }
    public static void evolveEevee(Pokemon pokemon, String stone) throws InterruptedException {
        if (!pokemon.getName().equals("Eevee")) {
            return; // Only Eevee can evolve using stones
        }

        Species newSpecies = null;

        switch (stone) {
            case "Fire Stone":
                newSpecies = Species.getSpecies("Flareon");
                break;
            case "Water Stone":
                newSpecies = Species.getSpecies("Vaporeon");
                break;
            case "Thunder Stone":
                newSpecies = Species.getSpecies("Jolteon");
                break;
            default:
                return; // Invalid stone, no evolution occurs
        }

        if (newSpecies != null) {
            String originalName = pokemon.getName();
            evolvePokemon(pokemon, newSpecies);
            Graphics.printPokemon(pokemon);
            System.out.println("Congratulations! Your " + originalName + " evolved into " + newSpecies.getName() + "!\n");
            Sound.playSoundOnce("main/music/levelUp.wav");
            Game.pressEnterToContinue();
        }
    }
    public void levelUpPokemon() throws InterruptedException{
        if(this.getCurrentHp() != 0 && this.getLevel() < User.checkLevelCap() && this.level < 100) {
            this.level++;
            tryEvolvePokemon(this);
        }
    }
    public void levelUpPokemonTwoThirdsChance() throws InterruptedException {
        if (Math.random() <= .67) {
            this.levelUpPokemon();
        }
    }
    //for ditto
    public static void takeOnOpponentStats(Pokemon ditto, Pokemon opponent) {
        ditto.currentMaxHp = opponent.currentMaxHp;
        ditto.currentHp = opponent.currentHp;
        ditto.currentAttack = opponent.currentAttack;
        ditto.currentDefense = opponent.currentDefense;
        ditto.currentSpAtk = opponent.currentSpAtk;
        ditto.currentSpDef = opponent.currentSpDef;
        ditto.currentSpeed = opponent.currentSpeed;

        ditto.attackStage = opponent.attackStage;
        ditto.defenseStage = opponent.defenseStage;
        ditto.spAtkStage = opponent.spAtkStage;
        ditto.spDefStage = opponent.spDefStage;
        ditto.speedStage = opponent.speedStage;

        ditto.statusCondition = opponent.statusCondition;

        ditto.moves = opponent.getMoves();
    }

    @Override
    public String toString() {
        String star = "";
        if(isShiny()){
            star = " ✨";
        }
        if (!type2.equals("None")) {
            return name + star + " lv. " + level + " | " + type1 + "/" + type2 + " | HP: " + currentHp + "/" + currentMaxHp;
        } else {
            return name + star+ " lv. " + level + " | " + type1 + " | HP: " + currentHp + "/" + currentMaxHp;
        }
    }
    public boolean isFoe() {
        return isFoe;
    }
    public void setIsFoe(boolean isFoe) {
        this.isFoe = isFoe;
    }

    public int getTimesSwitchedInBattle() {
        return timesSwitchedInBattle;
    }

    public void setTimesSwitchedInBattle(int timesSwitchedInBattle) {
        this.timesSwitchedInBattle = timesSwitchedInBattle;
    }
    public void incTimesSwitchedInBattle() {
        this.timesSwitchedInBattle++;
    }
}
