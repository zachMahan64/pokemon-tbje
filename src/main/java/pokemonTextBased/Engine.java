package pokemonTextBased;

import java.util.*;
import java.util.concurrent.*;
public class Engine {
    public int version = 0;
    public int iteration = 0;
    public boolean battleDialogsAreEnabled;
    public int BASE_PT_FOR_DAMAGE;
    public int MAXIMUM_DAMAGE_RATIO_TO_CONSIDER;
    public int PT_PUNISHMENT_FOR_OPPONENT_HAVING_KO_MOVE;
    public int BASE_PT_FOR_OTHER_EFFECT;
    public int BASE_PT_FOR_PURE_STATUS;
    public int PT_GOOD_SET_UP_MOVE;
    public int BASE_PT_PRIORITY;
    public int PRIORITY_PER_STAGE_BONUS;
    public int LAST_DITCH_PRIORITY_BONUS;
    public int PRIORITY_GUARANTEED_KO;
    public int BASE_SPEED_ADVANTAGE_BONUS;
    public int LIKELY_KO_BONUS;
    public int FAKE_OUT_BONUS;
    public int TRICK_ROOM_BONUS;
    public int BASE_SWITCH_MOVE_BONUS;
    public int SWITCH_MOVE_BONUS_WHEN_OPP_HAS_A_KO_MOVE;
    public int SLEEP_BONUS;
    public int OPP_CANNOT_MOVE_BONUS_FOR_SET_UP_MOVES;

    public int PT_PUNISHMENT_FOR_INACCURACY;
    public int PT_PUNISHMENT_FOR_WAIT;
    public int PT_PUNISHMENT_FOR_RECOIL;

    public int SWITCH_THRESHOLD_PERCENT;
    public int NON_ACTIVE_MATCH_UP_WEIGHT_PERCENT;

    public int OWN_WAIT_PENALTY;
    public int OPP_WAIT_PENALTY;
    public int OPP_HAS_GUARANTEED_KO_PENALTY;
    public Engine(EnginePackage enginePackage) {
        this(enginePackage, false);
    }
    public Engine(EnginePackage enginePackage, boolean battleDialogsAreEnabled) {
        this.battleDialogsAreEnabled = battleDialogsAreEnabled;
        BASE_PT_FOR_DAMAGE = enginePackage.get(EnginePackage.BASE_PT_FOR_DAMAGE);
        MAXIMUM_DAMAGE_RATIO_TO_CONSIDER = enginePackage.get(EnginePackage.MAXIMUM_DAMAGE_RATIO_TO_CONSIDER);
        PT_PUNISHMENT_FOR_OPPONENT_HAVING_KO_MOVE = enginePackage.get(EnginePackage.PT_PUNISHMENT_FOR_OPPONENT_HAVING_KO_MOVE);
        BASE_PT_FOR_OTHER_EFFECT = enginePackage.get(EnginePackage.BASE_PT_FOR_OTHER_EFFECT);
        BASE_PT_FOR_PURE_STATUS = enginePackage.get(EnginePackage.BASE_PT_FOR_PURE_STATUS);
        PT_GOOD_SET_UP_MOVE = enginePackage.get(EnginePackage.PT_GOOD_SET_UP_MOVE);
        BASE_PT_PRIORITY = enginePackage.get(EnginePackage.BASE_PT_PRIORITY);
        PRIORITY_PER_STAGE_BONUS = enginePackage.get(EnginePackage.PRIORITY_PER_STAGE_BONUS);
        LAST_DITCH_PRIORITY_BONUS = enginePackage.get(EnginePackage.LAST_DITCH_PRIORITY_BONUS);
        PRIORITY_GUARANTEED_KO = enginePackage.get(EnginePackage.PRIORITY_GUARANTEED_KO);
        BASE_SPEED_ADVANTAGE_BONUS = enginePackage.get(EnginePackage.BASE_SPEED_ADVANTAGE_BONUS);
        LIKELY_KO_BONUS = enginePackage.get(EnginePackage.LIKELY_KO_BONUS);
        FAKE_OUT_BONUS = enginePackage.get(EnginePackage.FAKE_OUT_BONUS);
        TRICK_ROOM_BONUS = enginePackage.get(EnginePackage.TRICK_ROOM_BONUS);
        BASE_SWITCH_MOVE_BONUS = enginePackage.get(EnginePackage.BASE_SWITCH_MOVE_BONUS);
        SWITCH_MOVE_BONUS_WHEN_OPP_HAS_A_KO_MOVE = enginePackage.get(EnginePackage.SWITCH_MOVE_BONUS_WHEN_OPP_HAS_A_KO_MOVE);
        SLEEP_BONUS = enginePackage.get(EnginePackage.SLEEP_BONUS);
        OPP_CANNOT_MOVE_BONUS_FOR_SET_UP_MOVES = enginePackage.get(EnginePackage.OPP_CANNOT_MOVE_BONUS_FOR_SET_UP_MOVES);

        PT_PUNISHMENT_FOR_INACCURACY = enginePackage.get(EnginePackage.PT_PUNISHMENT_FOR_INACCURACY);
        PT_PUNISHMENT_FOR_WAIT = enginePackage.get(EnginePackage.PT_PUNISHMENT_FOR_WAIT);
        PT_PUNISHMENT_FOR_RECOIL = enginePackage.get(EnginePackage.PT_PUNISHMENT_FOR_RECOIL);

        SWITCH_THRESHOLD_PERCENT = enginePackage.get(EnginePackage.SWITCH_THRESHOLD_PERCENT);
        NON_ACTIVE_MATCH_UP_WEIGHT_PERCENT = enginePackage.get(EnginePackage.NON_ACTIVE_MATCH_UP_WEIGHT_PERCENT);

        OWN_WAIT_PENALTY = enginePackage.get(EnginePackage.OWN_WAIT_PENALTY);
        OPP_WAIT_PENALTY = enginePackage.get(EnginePackage.OPP_WAIT_PENALTY);
        OPP_HAS_GUARANTEED_KO_PENALTY = enginePackage.get(EnginePackage.OPP_HAS_GUARANTEED_KO_PENALTY);
    }

    public Engine getRandomlyTweakedEngine(EnginePackage enginePackage) {
        Engine tweakedEngine = new Engine(enginePackage.tweakRandomly());
        tweakedEngine.iteration++;
        return tweakedEngine;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        simulateAllMatchUpsMultithreaded(10);
    }

    //Individual battle sim instance
    public static boolean simulateBattle(Arena arena) throws InterruptedException {
        //only lead with an advantage in harder difficulties, otherwise let starting pokemon remain random
        if(arena.playerEngine.battleDialogsAreEnabled) {
            Graphics.printPokemon(arena.fp[0].getName(), false, arena.fp[0].isShiny());
            System.out.println(arena.trainer.name + " sent out " + arena.fp[0].getName() + "!");
            Thread.sleep(User.textSpeed);
            Graphics.printPokemon(arena.p[0].getName(), false, arena.p[0].isShiny());
            System.out.println("You sent out " + arena.p[0].getName() + "!");
            Thread.sleep(User.textSpeed);
        }

        boolean playerHasWon = false;
        boolean foeHasWon = false;
        boolean playerSwitched = false;
        Move playerMove = null;
        Move foeMove = null;
        int playerSlot = 0;
        int foeSwitchSlot = 0;
        do {
            //graphics
            if(arena.playerEngine.battleDialogsAreEnabled) Encounter.displayBattleStatus(arena, true);

            // foe decisions
            foeMove = Fight.findBestMove(arena, arena.fp[0], arena.p[0]);
            foeSwitchSlot = Fight.findBestPokemonSlotToHaveOutBasedOnSwitchThreshold(arena, true);

            //plauer decisions
            playerSlot = Fight.findBestPokemonSlotToHaveOutBasedOnSwitchThreshold(arena, false);
            playerSwitched = Fight.decideToSwitchPokemonAndDoItIfWanted(arena, false, playerSlot);
            playerMove = Fight.findBestMove(arena, arena.p[0], arena.fp[0]);
            if(playerSwitched) playerMove = null;

            //skip player turn
            if (arena.p[0].isSkipNextTurn()) {
                if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(arena.p[0].getName() + " is waiting this turn.\n");
                Thread.sleep(User.textSpeed);
            }

            //check if foe wanted to switch and switch if so desired
            boolean foeSwitched = Fight.decideToSwitchPokemonAndDoItIfWanted(arena, true, foeSwitchSlot);
            if(foeSwitched) foeMove = null;

            //start of turn stuff
            Encounter.handleStatusConditionsAtBeginningOfTurn(arena);

            // Handle turns (normal switches and move order based on speed)
            Encounter.processMoveOrder(arena, playerMove, foeMove, playerSwitched);
            Encounter.handleEndOfTurnInteractions(arena);

            // Check if the player's Pokémon has fainted
            if (arena.p[0].getCurrentHp() == 0) {
                if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(arena.p[0].getName() + " fainted!\n");
                Thread.sleep(User.textSpeed);

                // check if ANY living Pokémon remain
                boolean hasLivingPokemon = Arrays.stream(arena.p)
                        .anyMatch(p -> p != null && p.getCurrentHp() > 0);

                if (!hasLivingPokemon) {
                    foeHasWon = true;
                } else {
                    // Force switch to next available Pokémon (even if not optimal)
                    boolean switched = Fight.decideToSwitchPokemonAndDoItIfWanted(arena, false);
                    if (!switched) foeHasWon = true; // Only if truly no Pokémon left
                }
            }

            // Check if the foe's Pokémon has fainted
            if (arena.fp[0].getCurrentHp() == 0) {
                if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(arena.fp[0].getName() + " fainted!\n");

                // check if ANY living Pokémon remain
                boolean hasLivingPokemon = Arrays.stream(arena.fp)
                        .anyMatch(p -> p != null && p.getCurrentHp() > 0);

                if (!hasLivingPokemon) {
                    playerHasWon = true;
                } else {
                    // Force switch to next available Pokémon (even if not optimal)
                    boolean switched = Fight.decideToSwitchPokemonAndDoItIfWanted(arena, true);
                    if (!switched) playerHasWon = true; // Only if truly no Pokémon left
                }
            }

            // Handle end-of-turn interactions
            Encounter.handleTurnChangeInteractions(arena);
            playerSwitched = false;
            arena.incrementTurns();
        } while (!playerHasWon && !foeHasWon && arena.p[0].getCurrentHp() > 0);

        return playerHasWon;
    }

    //calculate win %
    public static String calculateWinPercentage(Pokemon[] playerParArr, Pokemon[] foeParArr, int simNum) throws InterruptedException, ExecutionException {
        User.textSpeed = 0;
        Sound.disableSound = true;
        int numWins = 0;
        Engine engine = new Engine(new EnginePackage(EnginePackage.defaultEngineParameterMap), false);
        numWins += simulateMatchUpMultithreaded(simNum, playerParArr, foeParArr, engine, engine, false);
        double numWinsAsPercent = 100.00 * numWins / simNum;
        User.textSpeed = 2000;
        Sound.disableSound = false;
        return "Your win chance is " + numWinsAsPercent + "% (game engine ver" + engine.version + "." + engine.iteration + ")";
    }

    //multithreaded
    public static void simulateAllMatchUpsMultithreaded(int numSimsPerMatchUp) throws InterruptedException, ExecutionException {
//       ALL TRAINERS
//        for (HashMap.Entry<Trainer.Title, Pokemon[]> thisPlayerParty : Trainer.parties.entrySet()) {
//            for (HashMap.Entry<Trainer.Title, Pokemon[]> thisFoeParty : Trainer.parties.entrySet()) {
//                playerWins += simulateMatchUpMultithreaded(numSimsPerMatchUp, thisPlayerParty.getValue(), thisFoeParty.getValue(), testEngine, nullHypEngine, false);
//            }
//        }
        User.textSpeed = 0;
        Sound.disableSound = true;

        boolean stopRunningSims = false;

        EnginePackage nullHypEnginePackage = new EnginePackage(EnginePackage.defaultEngineParameterMap);

        int thisVersion = 0;
        int thisIteration = 0;

        do {
            EnginePackage testEnginePackage = nullHypEnginePackage.tweakRandomly();
            Engine nullHypEngine = new Engine(nullHypEnginePackage, false);
            Engine testEngine = new Engine(testEnginePackage, false);

            nullHypEngine.version = thisVersion;
            nullHypEngine.iteration = thisIteration;

            int totalMatchUps = Trainer.competitiveParties.size() * Trainer.competitiveParties.size();
            int totalBattles = numSimsPerMatchUp * totalMatchUps;
            int battlesCompleted = 0;
            int LOADING_BAR_LENGTH = 30;
            int[] loadingBarIntervals = new int[LOADING_BAR_LENGTH];
            for (int i = 0; i < loadingBarIntervals.length; i++) {
                loadingBarIntervals[i] = (int) ((double) (i + 1) / LOADING_BAR_LENGTH * totalBattles);
            }
            int loadingIndex = 0;

            System.out.println(" Starting Match-up Simulation");
            System.out.println("==============================");
            System.out.println("Running " + totalBattles + " simulations...\n");
            System.out.print("Progress: [");

            long startTime = System.currentTimeMillis();
            int playerWins = 0;

            for (Trainer.Title thisPlayerPartyOwner : Trainer.competitiveParties) {
                for (Trainer.Title thisFoePartyOwner : Trainer.competitiveParties) {
                    playerWins += simulateMatchUpMultithreaded(
                            numSimsPerMatchUp,
                            Trainer.parties.get(thisPlayerPartyOwner),
                            Trainer.parties.get(thisFoePartyOwner),
                            testEngine,
                            nullHypEngine,
                            false);

                    battlesCompleted+= numSimsPerMatchUp;

                    if (loadingIndex < LOADING_BAR_LENGTH && battlesCompleted >= loadingBarIntervals[loadingIndex]) {
                        System.out.print("⣿");
                        loadingIndex++;
                    }
                }
            }

            System.out.println("] Done.");


            long endTime = System.currentTimeMillis();

            // Calculate and display results
            double winProp = (double) playerWins / totalBattles;

            double zScore = (winProp - .5) / Math.sqrt((.5 * .5) / totalBattles);

            double zCritValue = 1.6449;

            boolean altHypEngineIsSignificantlyImproved = zScore > zCritValue;

            double winRateAsPercent = 100.0 * winProp;
            System.out.println("\n Simulation Results");
            System.out.println("=======================");
            //System.out.println("NULL HYP ENGINE HASH: " + System.identityHashCode(nullHypEnginePackage));
            //System.out.println("TEST ENGINE HASH: " + System.identityHashCode(testEnginePackage));
            System.out.println("NULL HYP ENG PACKAGE PARAMS:");
            System.out.println(nullHypEnginePackage);
            System.out.println();
            System.out.println("TEST ENG PACKAGE PARAMS:");
            System.out.println(testEnginePackage);
            System.out.println();
            System.out.printf("Player won %d out of %d battles (%.4f%% win rate)\n",
                    playerWins, totalBattles, winRateAsPercent);
            System.out.printf("z-score = %.4f (crit. value is %.4f)\n", zScore, zCritValue);
            System.out.printf("Completed in %.2f seconds\n\n", (endTime - startTime) / 1000.0);
            System.out.println("Using game engine version " + nullHypEngine.version + '.' + nullHypEngine.iteration);

            if (altHypEngineIsSignificantlyImproved) {
                System.out.println(">>> New engine accepted! Performance significantly better. <<<");
                nullHypEnginePackage = testEnginePackage;
                thisVersion++;
            }
            else thisIteration++;

        } while (!stopRunningSims);

        User.textSpeed = 2000;
        Sound.disableSound = false;
    }
    public static int simulateMatchUpMultithreaded(int numSimsPerMatchUp, Pokemon[] playerParArr, Pokemon[] foeParArr, Engine testEngine, Engine nullHypEngine, boolean doPrints) throws InterruptedException, ExecutionException {

        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        int baseSimsPerThread = numSimsPerMatchUp / numThreads;
        int remainingSims = numSimsPerMatchUp % numThreads;

        long startTime = System.currentTimeMillis();

        //aggregate results
        List<Future<Integer>> futures = new ArrayList<>(numThreads);

        for (int i = 0; i < numThreads; i++) {
             int threadSims = baseSimsPerThread + (i < remainingSims ? 1 : 0);
            if (threadSims <= 0) continue;

            futures.add(executor.submit(() -> {
                int threadWins = 0;

                Pokemon[] thisPlayerParArr = cloneParty(playerParArr, false);
                Pokemon[] thisFoeParArr = cloneParty(foeParArr, true);
                Trainer foeTrainer = new Trainer(Trainer.Title.CHAMPION);

                for (int j = 0; j < threadSims; j++) {
                    Pokemon[] playerTeam = cloneParty(thisPlayerParArr, false);
                    Pokemon[] foeTeam = cloneParty(thisFoeParArr, true);

                    Arena arena = new Arena(playerTeam, foeTeam, foeTrainer, testEngine, nullHypEngine);
                    arena.isSimulation = true;

                    if (simulateBattle(arena)) {
                        threadWins++;
                    }
                }
                return threadWins;
            }));
        }

        // Aggregate results
        int totalWins = 0;
        for (Future<Integer> future : futures) {
            totalWins += future.get();
        }

        executor.shutdown();

        if (doPrints) {
            System.out.println("Player won " + totalWins + "/" + numSimsPerMatchUp + " battles");
            long endTime = System.currentTimeMillis();
            System.out.printf("Completed in %.2f seconds\n\n", (endTime - startTime) / 1000.0);
        }
        return totalWins;
    }
    //helper
    public static Pokemon[] cloneParty(Pokemon[] original, boolean isFoe) {
        Pokemon[] partyClone = new Pokemon[original.length];
        for (int k = 0; k < original.length; k++) {
            if (original[k] != null) {
                partyClone[k] = original[k].clone();
                partyClone[k].setIsFoe(isFoe);
            }
        }
        return partyClone;
    }
}