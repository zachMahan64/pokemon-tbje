package pokemonTextBased;

import java.util.*;
public class Fight {
    public static Move askUserToChooseAMove(Arena arena, Pokemon dealer, Pokemon recipient, Move engineMove, Scanner sc1) {
        Move moveToUse = null;
        boolean choiceToGoBack = false;
        do {
            printMoves(arena, dealer, recipient, engineMove);

            String input = sc1.nextLine().trim().toUpperCase();

            if (input.equals("B")) {
                choiceToGoBack = true;
            } else {
                try {
                    int moveChoice = Integer.parseInt(input);
                    List<Move> availableMoves = arena.p[0].getMoves();

                    if (moveChoice >= 1 && moveChoice <= availableMoves.size()) {
                        moveToUse = availableMoves.get(moveChoice - 1);
                        if (!moveToUse.canUseMove()) {
                            System.out.println("Cannot use " + moveToUse.getName() + " — no PP remaining!");
                            moveToUse = null;
                        }
                    } else {
                        System.out.println("Invalid choice! Please enter a number between 1 and " + availableMoves.size() + ".\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a number (1-4) or [B] to go back.\n");
                    Game.pressEnterToContinue(sc1);
                }
            }
        } while (moveToUse == null && !choiceToGoBack);

        return choiceToGoBack ? null : moveToUse;
    }
    public static Move askUserToChooseAMove(Arena arena, Scanner sc1) {
        Move moveToUse = null;
        boolean choiceToGoBack = false;
        do {
            printMoves(arena);

            String input = sc1.nextLine().trim().toUpperCase();

            if (input.equals("B")) {
                choiceToGoBack = true;
            } else {
                try {
                    int moveChoice = Integer.parseInt(input);
                    List<Move> availableMoves = arena.p[0].getMoves();

                    if (moveChoice >= 1 && moveChoice <= availableMoves.size()) {
                        moveToUse = availableMoves.get(moveChoice - 1);
                        if (!moveToUse.canUseMove()) {
                            System.out.println("Cannot use " + moveToUse.getName() + " — no PP remaining!");
                            moveToUse = null;
                        }
                    } else {
                        System.out.println("Invalid choice! Please enter a number between 1 and " + availableMoves.size() + ".\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a number (1-4) or [B] to go back.\n");
                    Game.pressEnterToContinue(sc1);
                }
            }
        } while (moveToUse == null && !choiceToGoBack);

        return choiceToGoBack ? null : moveToUse;
    }
    public static void printMoves(Arena arena, Pokemon dealer, Pokemon recipient, Move engineMove) {
        final String GREEN = "\u001B[92m";
        final String RED = "\u001B[31m";
        final String RESET = "\u001B[0m";

        // Calculate dynamic column widths
        int maxMoveNameLength = dealer.getMoves().stream()
                .mapToInt(m -> m.getName().length())
                .max()
                .orElse(12); // Default minimum width

        maxMoveNameLength = Math.max(maxMoveNameLength, 12);
        int damageColumnWidth = 3; // Fixed width for damage

        String moveFormat = String.format(
                "[%%d] %%-%ds | Dmg: %%-%ds | %%-8s | PP: %%2d/%%-2d %%s%%n",
                maxMoveNameLength,
                damageColumnWidth
        );

        System.out.println("What will " + arena.p[0].getName() + " do?");

        for (int i = 0; i < dealer.getMoves().size(); i++) {
            Move move = dealer.getMoves().get(i);
            String damage = String.valueOf(move.getDamage());
            String effectiveness = " | ";
            if ((User.hintMode == User.Hints.SHOW_ENGINE_CHOICES || User.hintMode == User.Hints.SHOW_EFFECTIVENESS)
                    && checkIfMoveIsSuperEffective(move, arena.p[0], arena.fp[0])) {
                effectiveness = " | " + GREEN + "Super Effective!" + RESET;
            }
            else if ((User.hintMode == User.Hints.SHOW_ENGINE_CHOICES || User.hintMode == User.Hints.SHOW_EFFECTIVENESS)
                    && checkIfMoveIsNotVeryEffective(move, arena.p[0], arena.fp[0])) {
                effectiveness = " | " + RED + "Not Very Effective..." + RESET;
            }

            System.out.printf(
                    moveFormat,
                    i + 1,
                    move.getName(),
                    damage,
                    move.getType(),
                    move.getCurrentPp(),
                    move.getTotalPp(),
                    Fight.compareMoveChoiceToEngine(arena, dealer, recipient, move, engineMove) + effectiveness
            );
        }
        System.out.println("[B] Back");
    } //used in trainer battles
    public static void printMoves(Arena arena) {
        Pokemon pokemon = arena.p[0];
        final String GREEN = "\u001B[92m";
        final String RESET = "\u001B[0m";
        final String RED = "\u001B[31m";

        // Calculate dynamic column widths
        int maxMoveNameLength = pokemon.getMoves().stream()
                .mapToInt(m -> m.getName().length())
                .max()
                .orElse(12); // Default minimum width

        maxMoveNameLength = Math.max(maxMoveNameLength, 12);
        int damageColumnWidth = 3; // Fixed width for damage

        String moveFormat = String.format(
                "[%%d] %%-%ds | Dmg: %%-%ds | %%-8s | PP: %%2d/%%-2d %%s%%n",
                maxMoveNameLength,
                damageColumnWidth
        );

        System.out.println("What will " + arena.p[0].getName() + " do?");

        for (int i = 0; i < pokemon.getMoves().size(); i++) {
            Move move = pokemon.getMoves().get(i);
            String damage = String.valueOf(move.getDamage());
            String effectiveness = "";
            if ((User.hintMode == User.Hints.SHOW_ENGINE_CHOICES || User.hintMode == User.Hints.SHOW_EFFECTIVENESS)
                    && checkIfMoveIsSuperEffective(move, arena.p[0], arena.fp[0])) {
                effectiveness = " - " + GREEN + " Super Effective!" + RESET;
            }
            else if ((User.hintMode == User.Hints.SHOW_ENGINE_CHOICES || User.hintMode == User.Hints.SHOW_EFFECTIVENESS)
                    && checkIfMoveIsNotVeryEffective(move, arena.p[0], arena.fp[0])) {
                effectiveness = " - " + RED + "Not Very Effective..." + RESET;
            }

            System.out.printf(
                    moveFormat,
                    i + 1,
                    move.getName(),
                    damage,
                    move.getType(),
                    move.getCurrentPp(),
                    move.getTotalPp(),
                    effectiveness
            );
        }
        System.out.println("[B] Back");
    } //used in wild battles (for now)
    public static void useMove(Arena arena, Move move, Pokemon dealer, Pokemon recipient) throws InterruptedException {
        if (move == null) return;
        Move moveToUse = null;
        if(move.getCurrentPp() <= 0) {
            moveToUse = new Move(Move.getMove("Struggle"));
        } else {
            moveToUse = move;
            moveToUse.lowerPP();
        }
        if (recipient.getCurrentHp() == 0) return;
        if (dealer.getCurrentHp() > 0 && dealer.getWakeUpTurn() == arena.turnNum) {
            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + " woke up!\n");
            Thread.sleep((long)(.75 * User.textSpeed));
        }
        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + " used " + moveToUse.getName() + "!\n");
        Thread.sleep((long) (User.textSpeed * .66));
        if (checkIfMoveShouldFail(arena, moveToUse, dealer, recipient)) {
            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println("But it failed!\n");
            Thread.sleep(User.textSpeed);
            return;
        }
        if (dealer.getStatusCondition().equals("Paralysis") && Math.random() < .25) {
            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + " is paralyzed and failed to move!\n");
            Thread.sleep(User.textSpeed);
            return;
        }
        int moveAccuracy = moveToUse.getAccuracy();
        if (moveToUse.getName().equals("Thunder") && arena.weather.equals("Rainy")) {
            moveAccuracy = 100;
        }
        int accuracyCheck = (int) (Math.random() * 100) + 1;
        if (accuracyCheck <= moveAccuracy) {
            announceEffectiveness(arena, moveToUse, recipient);
            doDamage(arena, moveToUse, dealer, recipient);
            doEffect(arena, moveToUse, dealer, recipient);
        } else {
            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(moveToUse.getName() + " missed!\n");
            Thread.sleep(User.textSpeed);
        }
    }
    public static boolean checkIfMoveShouldFail(Arena arena, Move moveToUse, Pokemon dealer, Pokemon recipient) {
        if (moveToUse.getName().equals("Fake Out") && dealer.getTurnSentOut() + 1 != arena.turnNum) {
            return true;
        }
        else if (moveToUse == Move.getMove("Rain Dance") && arena.weather.equals("Rainy")) {
            return true;
        }
        else if (moveToUse == Move.getMove("Snowy Day") && arena.weather.equals("Snowy")) {
            return true;
        }
        else if (moveToUse == Move.getMove("Sunny Day") && arena.weather.equals("Sunny")) {
            return true;
        }
        return false;
    }
    public static void announceEffectiveness(Arena arena, Move moveToUse, Pokemon recipient) throws InterruptedException {
        if(!arena.playerEngine.battleDialogsAreEnabled) return;
        if (moveToUse.getDamage() > 0) {
            if (getType1Effectiveness(moveToUse, recipient) * getType2Effectiveness(moveToUse, recipient) > 1.0) {
                System.out.println("It's super effective!\n");
                Sound.playSoundOnce("src/main/music/superEffective.mp3");
                Thread.sleep(User.textSpeed);
            } else if (getType1Effectiveness(moveToUse, recipient) * getType2Effectiveness(moveToUse, recipient) == 0) {
                System.out.println("It has no effect on " + recipient.getName() + "!\n");
                Thread.sleep(User.textSpeed);
            } else if (getType1Effectiveness(moveToUse, recipient) * getType2Effectiveness(moveToUse, recipient) < 1.0) {
                System.out.println("It's not very effective!\n");
                Sound.playSoundOnce("src/main/music/cut.mp3");
                Thread.sleep(User.textSpeed);
            }
            else {
                Sound.playSoundOnce("src/main/music/cut.mp3");
                Thread.sleep((long)(.5 * User.textSpeed));
            }
        }
        else if (moveToUse.getName().equalsIgnoreCase("Trick Room")) {
            Sound.playSoundOnce("src/main/music/trickRoom.mp3");
            Thread.sleep((long)(1.25 *User.textSpeed));
        }
    }
    public static void doDamage(Arena arena, Move moveToUse, Pokemon dealer, Pokemon recipient) throws InterruptedException {
        double cHp = recipient.getCurrentHp();
        double dmg = calcDamage(arena, moveToUse, dealer, recipient);
        recipient.setCurrentHp((int) (cHp - dmg));
    }
    public static double calcDamage(Arena arena, Move moveToUse, Pokemon dealer, Pokemon recipient) throws InterruptedException{
        double eAtk; //effective attack
        double eDef; //effective defense
        double crit = getCritMultiplier();
        double dmgRoll = getDamageRoll();
        double sTAB = getSTAB(moveToUse, dealer);
        double t1Eff = getType1Effectiveness(moveToUse, recipient);
        double t2Eff = getType2Effectiveness(moveToUse, recipient);
        double burnMult = 1.0;
        double poisonMult = 1.0;
        double eruptionOrWaterSpout = 1.0;
        double power = moveToUse.getDamage();
        double lvlMult = ((2.0 * dealer.getLevel())/5.0) + 2.0;
        double weather = getWeatherMultiplier(arena, moveToUse, dealer);
        double dmg; //damage that will be calculated to be done to the recipient
        if(moveToUse.getName().equalsIgnoreCase("Water Spout") || moveToUse.getName().equalsIgnoreCase("Eruption")) {
            eruptionOrWaterSpout = (double) dealer.getCurrentHp()/dealer.getCurrentMaxHp();
        }
        if(dealer.getStatusCondition().equals("Burn")){
            burnMult = .5;
        }
        if(dealer.getStatusCondition().equals("Poison")){
            poisonMult = .5;
        }
        if(moveToUse.isUseSpStats()){
            eAtk = dealer.getCurrentSpAtk() * dealer.getStatMultiplier("SpAtk") * poisonMult;
            eDef = recipient.getCurrentSpDef() * recipient.getStatMultiplier("SpDef");
        } else {
            eAtk = dealer.getCurrentAttack() * dealer.getStatMultiplier("Attack") * burnMult;
            eDef = recipient.getCurrentDefense() * recipient.getStatMultiplier("Defense");
        }
        dmg = (((lvlMult * power * (eAtk/eDef)) / 50.0) * crit) * weather * sTAB * t1Eff * t2Eff * eruptionOrWaterSpout * dmgRoll;
        if (arena.playerEngine.battleDialogsAreEnabled && crit == 1.5 && dmg != 0) {
            System.out.println("A critical hit!\n");
            Thread.sleep(User.textSpeed);
        }
        return dmg;
    }
    public static double calcDamageWithoutVariation(Arena arena, Move moveToUse, Pokemon dealer, Pokemon recipient){
        double eAtk; //effective attack
        double eDef; //effective defense
        double crit = 1.0;
        double dmgRoll = .925;
        double sTAB = getSTAB(moveToUse, dealer);
        double t1Eff = getType1Effectiveness(moveToUse, recipient);
        double t2Eff = getType2Effectiveness(moveToUse, recipient);
        double burnMult = 1.0;
        double poisonMult = 1.0;
        double eruptionOrWaterSpout = 1.0;
        double power = moveToUse.getDamage();
        double lvlMult = ((2.0 * dealer.getLevel())/5.0) + 2.0;
        double weather = getWeatherMultiplier(arena, moveToUse, dealer);
        double dmg; //damage that will be calculated to be done to the recipient
        if(moveToUse.getName().equalsIgnoreCase("Water Spout") || moveToUse.getName().equalsIgnoreCase("Eruption")) {
            eruptionOrWaterSpout = (double) dealer.getCurrentHp()/dealer.getCurrentMaxHp();
        }
        if(dealer.getStatusCondition().equals("Burn")){
            burnMult = .5;
        }
        if(dealer.getStatusCondition().equals("Poison")){
            poisonMult = .5;
        }
        if(moveToUse.isUseSpStats()){
            eAtk = dealer.getCurrentSpAtk() * dealer.getStatMultiplier("SpAtk") * poisonMult;
            eDef = recipient.getCurrentSpDef() * recipient.getStatMultiplier("SpDef");
        } else {
            eAtk = dealer.getCurrentAttack() * dealer.getStatMultiplier("Attack") * burnMult;
            eDef = recipient.getCurrentDefense() * recipient.getStatMultiplier("Defense");
        }
        dmg = (((lvlMult * power * (eAtk/eDef)) / 50.0) * crit) * weather * sTAB * t1Eff * t2Eff * eruptionOrWaterSpout * dmgRoll;
        return dmg;
    }
    public static void doEffect(Arena arena, Move moveToUse, Pokemon dealer, Pokemon recipient) throws InterruptedException {
        Random rand = new Random();
        Scanner sc1 = new Scanner(System.in);
        boolean checkAccuracy = (rand.nextInt(0, 100) <= moveToUse.getAccuracyOfEffect());
        boolean targetIsImmune = (getTypeEffectivenessOfMove(moveToUse, recipient) == 0 && checkIfStatusMoveAffectsOpponent(moveToUse)) || (checkIfStatusMoveAffectsOpponent(moveToUse) && recipient.getCurrentHp() == 0);
        if (!moveToUse.getOtherEffect().equalsIgnoreCase("None")) {
            if (checkAccuracy && !targetIsImmune) {
                switch (moveToUse.getOtherEffect()) {
                    case "Opponent Attack -1":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(recipient.getName() + "'s Attack fell!\n");
                        recipient.changeStatStage("Attack", -1);
                        Sound.playSoundOnce("src/main/music/statLower.mp3");
                        break;
                    case "Opponent Attack -2":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(recipient.getName() + "'s Attack harshly fell!\n");
                        recipient.changeStatStage("Attack", -2);
                        Sound.playSoundOnce("src/main/music/statLower.mp3");
                        break;
                    case "Opponent Defense -1":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(recipient.getName() + "'s Defense fell!\n");
                        recipient.changeStatStage("Defense", -1);
                        Sound.playSoundOnce("src/main/music/statLower.mp3");
                        break;
                    case "Opponent Defense -2":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(recipient.getName() + "'s Defense harshly fell!\n");
                        recipient.changeStatStage("Defense", -2);
                        Sound.playSoundOnce("src/main/music/statLower.mp3");
                        break;
                    case "Opponent SpDef -1":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(recipient.getName() + "'s Special Defense fell!\n");
                        recipient.changeStatStage("SpDef", -1);
                        Sound.playSoundOnce("src/main/music/statLower.mp3");
                        break;
                    case "Own Defense +1":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + "'s Defense rose!\n");
                        dealer.changeStatStage("Defense", 1);
                        Sound.playSoundOnce("src/main/music/statRaise.mp3");
                        break;
                    case "Own Speed +1":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + "'s Speed rose!\n");
                        dealer.changeStatStage("Speed", 1);
                        Sound.playSoundOnce("src/main/music/statRaise.mp3");
                        break;
                    case "Own Speed +2":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + "'s Speed sharply rose!\n");
                        dealer.changeStatStage("Speed", 2);
                        Sound.playSoundOnce("src/main/music/statRaise.mp3");
                        break;
                    case "Opponent Speed -1":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(recipient.getName() + "'s Speed fell!\n");
                        recipient.changeStatStage("Speed", -1);
                        Sound.playSoundOnce("src/main/music/statLower.mp3");
                        break;
                    case "Opponent Speed -2":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(recipient.getName() + "'s Speed harshly fell!\n");
                        recipient.changeStatStage("Speed", -2);
                        Sound.playSoundOnce("src/main/music/statLower.mp3");
                        break;
                    case "Faint":
                        if(getTypeEffectivenessOfMove(moveToUse, recipient) == 0) break;
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println("Good God, " + dealer.getName() + " blew itself up!\n");
                        dealer.setCurrentHp(0);
                        break;
                    case "Opponent Sleep":
                        if(!recipient.getStatusCondition().equals("Sleep")) {
                            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(recipient.getName() + " fell asleep!\n");
                            recipient.setStatusCondition("Sleep");
                            recipient.setWakeUpTurnFor3TurnsInTheFuture(arena);
                        }
                        else {
                            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(recipient.getName() + " is already asleep!\n");
                        }
                        break;
                    case "Fall Asleep & Heal to Max Hp":
                        if (dealer.getCurrentHp() == dealer.getCurrentMaxHp()) {
                            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + " couldn't rest because its HP is full!");
                            Thread.sleep((long)(.75* User.textSpeed));
                        }
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + " fell asleep!\n");
                        dealer.setStatusCondition("Sleep");
                        dealer.setWakeUpTurnFor3TurnsInTheFuture(arena);
                        Thread.sleep((long)(.75* User.textSpeed));
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + " restored all of its HP!\n");
                        dealer.setCurrentHp(dealer.getCurrentMaxHp());
                        break;
                    case "Opponent Paralysis":
                        if(!recipient.getStatusCondition().equals("Paralysis") && !recipient.getType1().equals("Electric") & !recipient.getType2().equals("Electric")) {
                            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(recipient.getName() + " is paralyzed! It may be unable to move!\n");
                            recipient.setStatusCondition("Paralysis");
                        }
                        else if(recipient.getType1().equals("Electric") || recipient.getType2().equals("Electric")) {
                            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println("Oops! " + recipient.getName() + " is electric type and therefore immune to paralysis!");
                        }
                        else {
                            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(recipient.getName() + " is already paralyzed!\n");
                        }
                        break;
                    case "Opponent Burn":
                        if(!recipient.getStatusCondition().equals("Burn") && !recipient.getType1().equals("Fire") & !recipient.getType2().equals("Fire")) {
                            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(recipient.getName() + " was burned!\n");
                            recipient.setStatusCondition("Burn");
                        }
                        else if(recipient.getType1().equals("Fire") || recipient.getType2().equals("Fire")) {
                            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println("Oops! " + recipient.getName() + " is fire type and therefore immune to burn!");
                        }
                        else {
                            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(recipient.getName() + " is already burned!\n");
                        }
                        break;
                    case "Opponent Poison":
                        if(!recipient.getStatusCondition().equals("Poison")) {
                            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(recipient.getName() + " was poisoned!\n");
                            recipient.setStatusCondition("Poison");
                        }
                        else if(recipient.getType1().equals("Poison") || recipient.getType2().equals("Poison")) {
                            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println("Oops! " + recipient.getName() + " is poison type and therefore immune to poisoning!");
                        }
                        else {
                            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(recipient.getName() + " is already poisoned!\n");
                        }
                        break;
                    case "Opponent Flinch":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(recipient.getName() + " flinched!\n");
                        recipient.setFlinched(true);
                        break;
                    case "Wait":
                        if(!(moveToUse.getName().equals("Solar Beam") && arena.weather.equals("Sunny"))) {
                            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + " must wait for a turn!\n");
                            dealer.setSkipNextTurn(true);
                            dealer.setRechargeTurn(arena);
                        }
                        break;
                    case "Switch":
                        if(dealer.isFoe()) {
                            switchSimplyAI(arena, true);
                        }
                        else if(arena.isSimulation) {
                            switchSimplyAI(arena, false);
                        }
                        else {
                            Party.switchPokemon(arena,true, sc1);
                            arena.p[0].setTurnSentOut(arena.turnNum);
                        }
                        break;
                    case "Opponent SpAtk -1 & Attack -1 & Switch":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(recipient.getName() + "'s Attack and Special Attack fell!\n");
                        recipient.changeStatStage("SpAtk", -1);
                        recipient.changeStatStage("Attack", -1);
                        Sound.playSoundOnce("src/main/music/statLower.mp3");
                        Thread.sleep(User.textSpeed);
                        if(dealer.isFoe()) {
                            switchSimplyAI(arena, true);
                        }
                        else if(arena.isSimulation) {
                            switchSimplyAI(arena, false);
                        }
                        else {
                            Party.switchPokemon(arena,true, sc1);
                            arena.p[0].setTurnSentOut(arena.turnNum);
                        }
                        break;
                    case "Rainy":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println("It started to rain!\n");
                        arena.setUpWeather("Rainy");
                        break;
                    case "Sunny":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println("The sunlight turned harsh!\n");
                        arena.setUpWeather("Sunny");
                        break;
                    case "Snowy":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println("It started to snow!\n");
                        arena.setUpWeather("Snowy");
                        break;
                    case "Slower Pokemon Move Faster":
                        if (arena.trickRoomIsUp) {
                            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + " untwisted the dimension of spacetime!\n");
                            arena.setTrickRoom(false);
                            arena.setTurnTrickRoomEnds(arena.turnNum-1);
                        }
                        else {
                            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + " twisted the dimension of spacetime!\n");
                            arena.setUpTrickRoom();
                        }
                        break;
                    case "Take On Opponent Stats":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + " copied " + recipient.getName() + "'s stats!\n");
                        Pokemon.takeOnOpponentStats(dealer, recipient);
                        break;
                    case "Own Halve Max Hp & Attack +6":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + " cut its own HP and maximized its Attack!\n");
                        dealer.setCurrentHp(dealer.getCurrentHp() - (int) (0.5 * dealer.getCurrentMaxHp()));
                        dealer.changeStatStage("Attack", 6);
                        Sound.playSoundOnce("src/main/music/statRaise.mp3");
                        break;
                    case "Recoil":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + " was hurt by recoil!\n");
                        dealer.setCurrentHp(dealer.getCurrentHp() - (int) (dealer.getCurrentHp() * 0.25));
                        break;
                    case "Heal Half Hp Dealt":
                        int healAmount = (int) (calcDamageWithoutVariation(arena, moveToUse, dealer, recipient) * 0.5);
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + " regained health!\n");
                        dealer.setCurrentHp(dealer.getCurrentHp() + healAmount);
                        break;
                    case "Heal Half Max Hp":
                        if (dealer.getCurrentHp() != dealer.getCurrentMaxHp()) {
                            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + " restored some of its HP!\n");
                            dealer.setCurrentHp(dealer.getCurrentHp() + (int) (dealer.getCurrentMaxHp() * 0.5));
                        } else {
                            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + " is already at full health!\n");
                        }
                        break;
                    case "Own Defense -1 & SpDef -1":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + "'s Defense and Special Defense fell!\n");
                        dealer.changeStatStage("Defense", -1);
                        dealer.changeStatStage("SpDef", -1);
                        Sound.playSoundOnce("src/main/music/statLower.mp3");
                        break;
                    case "Own SpAtk -2":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + "'s Special Attack harshly fell!\n");
                        dealer.changeStatStage("SpAtk", -2);
                        Sound.playSoundOnce("src/main/music/statLower.mp3");
                        break;
                    case "Own Attack +1 & Defense +1":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + "'s Attack and Defense rose!\n");
                        dealer.changeStatStage("Attack", 1);
                        dealer.changeStatStage("Defense", 1);
                        Sound.playSoundOnce("src/main/music/statRaise.mp3");
                        break;
                    case "Own SpAtk +2":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + "'s Special Attack sharply rose!\n");
                        dealer.changeStatStage("SpAtk", 2);
                        Sound.playSoundOnce("src/main/music/statRaise.mp3");
                        break;
                    case "Opponent SpAtk -1":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(recipient.getName() + "'s Special Attack fell!\n");
                        recipient.changeStatStage("SpAtk", -1);
                        Sound.playSoundOnce("src/main/music/statLower.mp3");
                        break;
                    case "Own SpAtk +1 & SpDef +1":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + "'s Special Attack and Special Defense rose!\n");
                        dealer.changeStatStage("SpAtk", 1);
                        dealer.changeStatStage("SpDef", 1);
                        Sound.playSoundOnce("src/main/music/statRaise.mp3");
                        break;
                    case "Own Attack +1 & Speed +1":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + "'s Attack and Speed rose!\n");
                        dealer.changeStatStage("Attack", 1);
                        dealer.changeStatStage("Speed", 1);
                        Sound.playSoundOnce("src/main/music/statRaise.mp3");
                        break;
                    case "Own Attack +1":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + "'s Attack rose!\n");
                        dealer.changeStatStage("Attack", 1);
                        Sound.playSoundOnce("src/main/music/statRaise.mp3");
                        break;
                    case "Own Attack +2":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + "'s Attack rose sharply!\n");
                        dealer.changeStatStage("Attack", 2);
                        Sound.playSoundOnce("src/main/music/statRaise.mp3");
                        break;
                    case "Own SpAtk +1":
                        if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(dealer.getName() + "'s Special Attack rose!\n");
                        dealer.changeStatStage("SpAtk", 1);
                        Sound.playSoundOnce("src/main/music/statRaise.mp3");
                        break;
                    default:
                        break;
                }
                Thread.sleep((long)(User.textSpeed * .75));
            }
        }
    }
    public static boolean checkIfStatusMoveAffectsOpponent(Move move) {
        if(move.getName().length() >= 8) {
            String firstTad = move.getName().substring(0, 8);
            return firstTad.equalsIgnoreCase("Opponent");
        }
        else return false;
    }

    //calcDamage helpers
    public static double getCritMultiplier() {
        return (Math.random() < 1.0 / 16) ? 1.5 : 1.0;
    }
    public static double getDamageRoll() {
        return 0.85 + (Math.random() * 0.15);
    }
    public static double getType1Effectiveness(Move moveToUse, Pokemon recipient){
        return getTypeEffectiveness(moveToUse.getType(), recipient.getType1());
    }
    public static double getType2Effectiveness(Move moveToUse, Pokemon recipient){
        return getTypeEffectiveness(moveToUse.getType(), recipient.getType2());
    }
    public static double getSTAB(Move moveToUse, Pokemon dealer){
        if (moveToUse.getType().equals(dealer.getType1()) || moveToUse.getType().equals(dealer.getType2())) {
            return 1.5;
        }
        else{
            return 1;
        }
    }
    public static double getTypeEffectiveness(String attackType, String targetType) {
        String[] types = {
                "Normal", "Fire", "Water", "Electric", "Grass", "Ice", "Fighting", "Poison", "Ground",
                "Flying", "Psychic", "Bug", "Rock", "Ghost", "Dragon", "Dark", "Steel", "Fairy", "None"
        };

        double[][] typeChart = {
                //NOR FIR  WAT  ELE  GRA  ICE  FIG  POI  GRO  FLY  PSY  BUG ROCK  GHO  DRA  DAR  STE  FAI  NONE  //ATKing types
                {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 0.0, 1.0, 1.0, 0.5, 1.0, 1.0}, // NORMAL
                {1.0, 0.5, 0.5, 1.0, 2.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 0.5, 1.0, 2.0, 1.0, 1.0}, // FIRE
                {1.0, 2.0, 0.5, 1.0, 0.5, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0}, // WATER
                {1.0, 1.0, 2.0, 0.5, 0.5, 1.0, 1.0, 1.0, 0.0, 2.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0}, // ELECTRIC
                {1.0, 0.5, 2.0, 1.0, 0.5, 1.0, 1.0, 0.5, 2.0, 0.5, 1.0, 0.5, 2.0, 1.0, 0.5, 1.0, 0.5, 1.0, 1.0}, // GRASS
                {1.0, 0.5, 0.5, 1.0, 2.0, 0.5, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 1.0}, // ICE
                {2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 0.5, 0.5, 0.5, 2.0, 0.0, 1.0, 2.0, 2.0, 0.5, 1.0}, // FIGHTING
                {1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5, 0.5, 1.0, 1.0, 1.0, 0.5, 0.5, 1.0, 1.0, 0.0, 2.0, 1.0}, // POISON
                {1.0, 2.0, 1.0, 2.0, 0.5, 1.0, 1.0, 2.0, 1.0, 0.0, 1.0, 0.5, 2.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0}, // GROUND
                {1.0, 1.0, 1.0, 0.5, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0}, // FLYING
                {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0, 0.5, 0.5, 1.0, 1.0, 1.0, 0.0, 0.5, 1.0, 1.0}, // PSYCHIC
                {1.0, 0.5, 1.0, 1.0, 2.0, 1.0, 0.5, 0.5, 1.0, 0.5, 2.0, 1.0, 0.5, 0.5, 1.0, 2.0, 0.5, 0.5, 1.0}, // BUG
                {1.0, 2.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 0.5, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0}, // ROCK
                {0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 1.0, 1.0}, // GHOST
                {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5, 0.0, 1.0}, // DRAGON
                {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 0.5, 1.0}, // DARK
                {1.0, 0.5, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 0.5, 2.0, 1.0}, // STEEL
                {1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 0.5, 1.0, 1.0}, // FAIRY
                {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0}  // NONE
        };

        int attackIndex = -1;
        int targetIndex = -1;

        for (int i = 0; i < types.length; i++) {
            if (types[i].equals(attackType)) attackIndex = i;
            if (types[i].equals(targetType)) targetIndex = i;
        }

        if (attackIndex == -1 || targetIndex == -1) return 1.0;

        return typeChart[attackIndex][targetIndex];
    }
    public static double getWeatherMultiplier(Arena arena, Move moveToUse, Pokemon dealer){
        if (arena.getWeather().equals("Rainy") && moveToUse.getType().equals("Water") && (dealer.getType1().equals("Water") || dealer.getType2().equals("Water"))){
            return 1.5;
        }
        if (arena.getWeather().equals("Sunny") && moveToUse.getType().equals("Fire") && (dealer.getType1().equals("Fire") || dealer.getType2().equals("Fire"))){
            return 1.5;
        }
        if (arena.getWeather().equals("Snowy") && moveToUse.getType().equals("Ice") && (dealer.getType1().equals("Ice") || dealer.getType2().equals("Ice"))){
            return 1.5;
        }
        return 1.0;
    }
    public static boolean checkIfMoveIsSuperEffective(Move moveToUse, Pokemon dealer, Pokemon recipient) {
        return moveToUse.getDamage() != 0 && getType1Effectiveness(moveToUse, recipient) * getType2Effectiveness(moveToUse, recipient) > 1.0;
    }
    public static boolean checkIfMoveIsNotVeryEffective(Move moveToUse, Pokemon dealer, Pokemon recipient) {
        return moveToUse.getDamage() != 0 && getType1Effectiveness(moveToUse, recipient) * getType2Effectiveness(moveToUse, recipient) < 1.0
                && getType1Effectiveness(moveToUse, recipient) * getType2Effectiveness(moveToUse, recipient) != 0;
    }
    //AI
    //move selection
    public static Move chooseAMove(Arena arena, Pokemon dealer, Pokemon recipient) {
        if(User.difficultyMode == User.Difficulty.EASY) {
            double chanceToBeDumb = .5;
            if(Math.random() < chanceToBeDumb) {
                return findRandomMove(dealer);
            }
            else {
                return findGoodMove(arena, dealer, recipient);
            }
        }
        if(User.difficultyMode == User.Difficulty.NORMAL) {
            double chanceToBeDumb = .01;
            if(Math.random() < chanceToBeDumb) {
                return findRandomMove(dealer);
            }
            else {
                return findGoodMove(arena, dealer, recipient);
            }
        }
        if(User.difficultyMode == User.Difficulty.CHALLENGE) {
            double chanceToBeDumb = .001;
            double chanceToChooseGoodMove = .1;
            if(Math.random() < chanceToBeDumb) {
                return findRandomMove(dealer);
            }
            if(Math.random() < chanceToChooseGoodMove) {
                return findGoodMove(arena, dealer, recipient);
            }
            return findBestMove(arena, dealer, recipient);
        }
        if(User.difficultyMode == User.Difficulty.PROFESSIONAL) {
            double chanceToChooseGoodMove = .05;
            if(Math.random() < chanceToChooseGoodMove) {
                return findGoodMove(arena, dealer, recipient);
            }
            return findBestMove(arena, dealer, recipient);
        }
        return findBestMove(arena, dealer, recipient);
        } //depends on difficulty
    public static Move findRandomMove(Pokemon dealer) {
        Move moveToUse = null;
        List<Move> availableMoves = dealer.getMoves();
        Random rand = new Random();
        int moveChoice = rand.nextInt(availableMoves.size()) + 1;  // Random number between 1 and size of moveset

        if (moveChoice <= availableMoves.size()) {
            moveToUse = availableMoves.get(moveChoice - 1);
            if (moveToUse.canUseMove()) {
                return moveToUse;
            } else {
                moveToUse = Move.getMove("Struggle");
            }
        }

        return moveToUse; // Return the chosen move or STRUGGLE! if no valid move was used
    }
    public static Move findGoodMove(Arena arena, Pokemon dealer, Pokemon recipient) {
        Random rand = new Random();

        List<Move> availableMoves = dealer.getMoves();
        List<Move> goodMoves = new ArrayList<>();
        List<Move> goodStatusMoves = new ArrayList<>();
        Move highestDamagingMove = null;
        double highestDamage = -1;

        for (Move move : availableMoves) {
            if (!move.canUseMove()) {
                continue;
            }
            //has fake-out && it doesn't fail-->use fake-out
            if(move.getName().equals("Fake Out") && (getType1Effectiveness(move, recipient) * getType2Effectiveness(move, recipient) != 0)
                    && !checkIfMoveShouldFail(arena, move, dealer, recipient)) {
                return move;
            }
            double damage = calcDamageWithoutVariation(arena, move, dealer, recipient);
            // Track highest-damage move
            if (damage > highestDamage) {
                highestDamage = damage;
                highestDamagingMove = move;
            }

            boolean isStatusMove = (move.getDamage() == 0);
            boolean hasUsefulEffect = !move.getOtherEffect().equalsIgnoreCase("None")
                    && !move.getOtherEffect().equalsIgnoreCase(arena.getWeather())
                    && !(move.getOtherEffect().equalsIgnoreCase("Trick Room") && arena.trickRoomIsUp)
                    && !(move.getName().equals("Rest") || move.getName().equals("Recover") && (double) dealer.getCurrentHp() / dealer.getCurrentMaxHp() > .75 );
            boolean typeEffective = true;
            if(checkIfStatusMoveAffectsOpponent(move)) {
                typeEffective = (getType1Effectiveness(move, recipient) * getType2Effectiveness(move, recipient) != 0);
            }

            if (isStatusMove && hasUsefulEffect && typeEffective) {
                goodStatusMoves.add(move);
            }
            else if (hasUsefulEffect && typeEffective){
                goodMoves.add(move);
            }
        }
        // Fallback to Struggle if no usable moves
        if (highestDamagingMove == null && goodStatusMoves.isEmpty()) {
            return Move.getMove("Struggle");
        }

        boolean isFirstTurnPokemonIsIn = (dealer.getTurnSentOut() == arena.getTurnNum() - 1);
        double chanceForStatusMove = isFirstTurnPokemonIsIn ? .70 : .10;
        double chance = Math.random();
        if (chance <= chanceForStatusMove && !goodStatusMoves.isEmpty()){
            return goodStatusMoves.get(rand.nextInt(goodStatusMoves.size()));
        }
        else if ((chance >= chanceForStatusMove && chance < .75) || goodMoves.isEmpty()) {
            return highestDamagingMove;
        }
        else if (chance >= .75) {
            return goodMoves.get(rand.nextInt(goodMoves.size()));
        }
        return highestDamagingMove;
    }
    public static Move findBestMove(Arena arena, Pokemon dealer, Pokemon recipient) {
        if(dealer == null) return Move.getMove("Move Not Found");
        ArrayList<Move> availableMoves = new ArrayList<>(dealer.getMoves());
        Map<Integer, Integer> ratedMoves = new TreeMap<>(Collections.reverseOrder());

        for (int i = 0; i < availableMoves.size(); i++) {
            Move move = availableMoves.get(i);
            if (!move.canUseMove()) continue;

            int movePoints = rateMove(arena, move, dealer, recipient);
            ratedMoves.put(movePoints, i);
        }

        return ratedMoves.isEmpty() ?
                Move.getMove("Struggle") :
                availableMoves.get(ratedMoves.values().iterator().next());
    }
    public static int rateMove(Arena arena, Move move, Pokemon dealer, Pokemon recipient) {
        boolean alliesAreFoes = dealer.isFoe();
        Engine engine = alliesAreFoes ? arena.foeEngine : arena.playerEngine;
        boolean dealerIsFaster = Encounter.checkIfDealerIsFaster(arena, dealer, recipient);
        boolean isSpeedTie = Encounter.checkIfIsSpeedTie(arena);
        int points = 0;
        double maxDamageThreshold = (double) engine.MAXIMUM_DAMAGE_RATIO_TO_CONSIDER/100;
        double damageRatio = Math.min(maxDamageThreshold, calcDamageWithoutVariation(arena, move, dealer, recipient) / recipient.getCurrentMaxHp());
        double defendingHealthRatio = (double) recipient.getCurrentHp() / recipient.getCurrentMaxHp();

        // Base damage rating
        points += (int) ((engine.BASE_PT_FOR_DAMAGE * damageRatio) * ((double) move.getAccuracy() / 100));

        points -= checkIfPokemonHasAGuranteedKOMove(arena, recipient, dealer) ? engine.PT_PUNISHMENT_FOR_OPPONENT_HAVING_KO_MOVE : 0; //punish if player is cooked if uses this move

        // base effect rating
        if (hasUsefulEffect(arena, move, dealer, recipient) && !checkIfPokemonHasAGuranteedKOMove(arena, recipient, dealer)
                && !checkIfMoveShouldFail(arena, move, dealer, recipient)) {
            points += (int) (engine.BASE_PT_FOR_OTHER_EFFECT  * (double) move.getAccuracyOfEffect() / 100);
            if (move.getDamage() == 0) points += engine.BASE_PT_FOR_PURE_STATUS;
        }

        // good set up move rating
        if (isGoodSetUpMove(move) && hasUsefulEffect(arena, move, dealer, recipient) && !checkIfPokemonHasAKOMove(arena, recipient, dealer)
                && !checkIfPokemonHasAGuranteedKOMove(arena, recipient, dealer)
                && !checkIfMoveShouldFail(arena, move, dealer, recipient)) {
            points += engine.PT_GOOD_SET_UP_MOVE;
        }

        // Move priority bonuses
        if (move.getPriority() > 0 && move.getDamage() > 0 && damageRatio > 0 && !checkIfMoveShouldFail(arena, move, dealer, recipient)) {
            points += engine.BASE_PT_PRIORITY + engine.PRIORITY_PER_STAGE_BONUS * move.getPriority();
            if (checkIfPokemonHasAGuranteedKOMove(arena, recipient, dealer)) points += engine.LAST_DITCH_PRIORITY_BONUS; //Last ditch bonus
            if (checkIfMoveIsAKOAndCanGoOff(arena, move, dealer, recipient)) points += engine.PRIORITY_GUARANTEED_KO; // KO bonus
        }

        // Speed consideration
        if (dealerIsFaster && !isSpeedTie) {
           points += engine.BASE_SPEED_ADVANTAGE_BONUS;
            if (damageRatio > defendingHealthRatio) points += engine.LIKELY_KO_BONUS; //Likely KO Bonus
        }

        // Special move cases
        if (move.getName().equals("Fake Out") &&
                getTypeEffectivenessOfMove(move, recipient) != 0 &&
                !checkIfMoveShouldFail(arena, move, dealer, recipient)) {
            points += engine.FAKE_OUT_BONUS;
        }
        if (move.getName().equals("Trick Room")
                && !dealerIsFaster && !isSpeedTie
                && hasUsefulEffect(arena, move, dealer, recipient)) {
            points += engine.TRICK_ROOM_BONUS;
        }
        if ((move.getOtherEffect().equals("Switch") || move.getOtherEffect().equals("Opponent SpAtk -1 & Attack -1 & Switch"))) {
            points += engine.BASE_SWITCH_MOVE_BONUS;
            if(checkIfPokemonHasAKOMove(arena, recipient, dealer) && dealerIsFaster){
                points += engine.SWITCH_MOVE_BONUS_WHEN_OPP_HAS_A_KO_MOVE;
            }
        }
        if ((move.getOtherEffect().equals("Opponent Sleep")) && !recipient.getStatusCondition().equals("Sleep")) {
            int SLEEP_BONUS_IN_EFF = (int) (engine.SLEEP_BONUS * (double) move.getAccuracy()/100);
            points += SLEEP_BONUS_IN_EFF;
            if(checkIfPokemonHasAKOMove(arena, recipient, dealer) && dealerIsFaster){
                points += SLEEP_BONUS_IN_EFF;
            }
        }

        //punish bad attributes
        if (move.getAccuracy() < 50) {
            points -= engine.PT_PUNISHMENT_FOR_INACCURACY;
        }
        if (move.getOtherEffect().equals("Wait") && !checkIfPokemonHasAKOMove(arena, recipient, dealer)) {
            points -= engine.PT_PUNISHMENT_FOR_WAIT;
        }
        if (move.getOtherEffect().equals("Recoil")) {
            points -= engine.PT_PUNISHMENT_FOR_RECOIL;
        }

        // Special scenarios?

        return points;
    } //rate move
    // helper methods
    public static double getTypeEffectivenessOfMove(Move move, Pokemon target) {
        return getType1Effectiveness(move, target) * getType2Effectiveness(move, target);
    }
    public static boolean hasUsefulEffect(Arena arena, Move move, Pokemon dealer, Pokemon recipient) {
        String effect = move.getOtherEffect();

        // plain bad effects
        if (effect.equalsIgnoreCase("None") ||
                effect.equalsIgnoreCase("Recoil")
                ||  effect.equalsIgnoreCase("Faint")
                || effect.equalsIgnoreCase("Own SpAtk -2")
                || effect.equalsIgnoreCase("Own Defense -1 & SpDef -1")) {
            return false;
        }

        // Weather already active
        if (effect.equalsIgnoreCase(arena.getWeather())) return false;

        // Trick Room already up
        if (effect.equalsIgnoreCase("Trick Room") && arena.trickRoomIsUp) return false;

        if (effect.equalsIgnoreCase("Opponent Sleep") && recipient.getStatusCondition().equalsIgnoreCase("Sleep")) return false;

        // Healing move when already at high health
        if ((move.getName().equals("Rest") || move.getName().equals("Recover") || move.getName().equals("Moonlight")) &&
                ((double) dealer.getCurrentHp() / dealer.getCurrentMaxHp()) >= .60) {
            return false;
        }
        //Belly Drum trade-off figuring
        if ((move.getName().equals("Belly Drum") &&
                (double) dealer.getCurrentHp() / dealer.getCurrentMaxHp() <= 0.9) || checkIfPokemonCanDealHalfHealth(arena, recipient, dealer)) {
            return false;
        }

        else if (move == Move.getMove("Rain Dance") && arena.weather.equals("Rainy")) {
            return false;
        }
        else if (move == Move.getMove("Snowy Day") && arena.weather.equals("Snowy")) {
            return false;
        }
        else if (move == Move.getMove("Sunny Day") && arena.weather.equals("Sunny")) {
            return false;
        }

        // Poison check
        if (effect.equals("Opponent Poison") &&
                (recipient.getType1().equals("Poison") ||
                        recipient.getType2().equals("Poison") ||
                        recipient.getStatusCondition().equals("Poison") ||
                        recipient.getCurrentAttack() > recipient.getCurrentSpAtk())) {
            return false;
        }

        // Burn check
        if (effect.equals("Opponent Burn") &&
                (recipient.getType1().equals("Fire") ||
                        recipient.getType2().equals("Fire") ||
                        recipient.getStatusCondition().equals("Burn") ||
                        recipient.getCurrentSpAtk() > recipient.getCurrentAttack())) {
            return false;
        }

        // Paralysis check
        if (effect.equals("Opponent Paralysis") &&
                (recipient.getType1().equals("Electric") ||
                        recipient.getType2().equals("Electric") ||
                        recipient.getStatusCondition().equals("Paralysis"))) {
            return false;
        }

        return true;
    }
    public static boolean isGoodSetUpMove(Move move) {
        if(move.getAccuracyOfEffect()<30) return false;
        return move.getName().equals("Nasty Plot") || move.getName().equals("Dragon Dance") || move.getName().equals("Swords Dance")
                || move.getName().equals("Bulk Up") || move.getName().equals("Calm Mind") || move.getName().equals("Iron Defense")
                || move.getOtherEffect().equals("Opponent Burn") || move.getOtherEffect().equals("Opponent Poison")
                || move.getOtherEffect().equals("Opponent Paralysis") || move.getOtherEffect().equals("Opponent Sleep");
    }
    // Switch decision-making
    public static boolean switchSimplyAI(Arena arena, boolean alliesAreFoes) throws InterruptedException {
        Pokemon[] parArr = arena.p;
        if (alliesAreFoes) parArr = arena.fp;
        boolean trainerHasMorePokemon = false;
        // Search for the next alive Pokémon
        for (int i = 1; i < parArr.length; i++) {
            if (parArr[i] != null && parArr[i].getCurrentHp() > 0) {
                Pokemon temp = parArr[0];
                parArr[0] = parArr[i];
                parArr[i] = temp;
                temp.resetStages();
                parArr[0].setTurnSentOut(arena.turnNum);
                if(arena.playerEngine.battleDialogsAreEnabled) Graphics.printPokemon(parArr[0]);
                if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(arena.trainer.name + " sent out " + parArr[0].getName() + "!\n");
                Sound.playSoundOnce("src/main/music/catchFail.mp3");
                Thread.sleep(User.textSpeed);
                trainerHasMorePokemon = true;
                break;
            } else if (parArr[0].getCurrentHp() > 0){
                trainerHasMorePokemon = true; //do not switch
            }
        }
        return trainerHasMorePokemon;
    }
    public static void switchCalculatedAI(Arena arena, boolean alliesAreFoes, int slotToSwitchTo) throws InterruptedException {
        Pokemon[] parArr = arena.p;
        if (alliesAreFoes) parArr = arena.fp;
        boolean trainerHasMorePokemon = false;
        // Search for the next alive Pokémon
        if (parArr[slotToSwitchTo] != null && parArr[slotToSwitchTo].getCurrentHp() > 0) {
            Pokemon temp = parArr[0];
            temp.resetStages();
            parArr[0] = parArr[slotToSwitchTo];
            parArr[slotToSwitchTo] = temp;

            parArr[0].setTurnSentOut(arena.turnNum);
            if(arena.playerEngine.battleDialogsAreEnabled) Graphics.printPokemon(parArr[0]);
            String name = "Player";
            if (alliesAreFoes) name = arena.trainer.name;
            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(name + " sent out " + parArr[0].getName() + "!\n");
            Sound.playSoundOnce("src/main/music/catchFail.mp3");
            Thread.sleep(User.textSpeed);
            trainerHasMorePokemon = true;
            parArr[0].incTimesSwitchedInBattle();
        }
    }

    public static boolean decideToSwitchPokemonAndDoItIfWanted(Arena arena, boolean alliesAreFoes, int bestSlot) throws InterruptedException {
        if (bestSlot != 0) {
            switchCalculatedAI(arena, alliesAreFoes, bestSlot);
            return true;
        }
        return false;
    }
    public static boolean decideToSwitchPokemonAndDoItIfWanted(Arena arena, boolean alliesAreFoes) throws InterruptedException {
        int bestSlot = Fight.findBestPokemonSlotToHaveOut(arena, alliesAreFoes);
        if (bestSlot != 0) {
            switchCalculatedAI(arena, alliesAreFoes, bestSlot);
            return true;
        }
        return false;
    }
    public static int findBestPokemonSlotToHaveOut(Arena arena, boolean alliesAreFoes) {
        Engine engine = alliesAreFoes ? arena.foeEngine : arena.playerEngine;
        int SWITCH_THRESHOLD = engine.SWITCH_THRESHOLD_PERCENT;
        double SWITCH_RATIO = (double) SWITCH_THRESHOLD/100;
        return findBestPokemonSlotToHaveOut(arena, alliesAreFoes, SWITCH_RATIO);
    } //has param
    public static int findBestPokemonSlotToHaveOut(Arena arena, boolean alliesAreFoes, double switchThreshold) {
        if (arena == null || arena.fp == null || arena.fp[0] == null || arena.p == null || arena.p[0] == null) return 0;

        Pokemon[] parArr = alliesAreFoes ? arena.fp : arena.p;

        boolean doNotSwitch = false;
        boolean isForcedSwitch = parArr[0].getCurrentHp() <= 0;

        TreeMap<Integer, Integer> ratings = ratePartyByMemberIndex(arena, alliesAreFoes);
        int activeRating = ratings.getOrDefault(0, 0);
        int bestSlot = 0;
        int bestRating = isForcedSwitch ? Integer.MIN_VALUE : activeRating; // For forced, take ANY better mons!

        for (Map.Entry<Integer, Integer> entry : ratings.entrySet()) {
            int slot = entry.getKey();
            if (slot == 0) continue; //skip active slot

            int rating = entry.getValue();
            boolean isBetterForVoluntary = rating > switchThreshold * activeRating;
            boolean isBetterForForced = rating > bestRating;
            if ((isForcedSwitch && isBetterForForced) || (!isForcedSwitch && isBetterForVoluntary)) {
                bestSlot = slot;
                bestRating = rating;
            }
        }
        if (!isForcedSwitch && (bestRating <= 25 || User.difficultyMode == User.Difficulty.EASY)) doNotSwitch = true;

        // Switch if:
        // -forced: any living mon exists
        // -voluntary: meets threshold and is valid
        if (bestSlot != 0 && isValidSwitchTarget(parArr[bestSlot]) && !doNotSwitch) {
            return bestSlot;
        }
        return 0;
    }
    private static boolean isValidSwitchTarget(Pokemon pokemon) {
        return pokemon != null && pokemon.getCurrentHp() > 0;
    }
    public static TreeMap<Integer, Integer> ratePartyByMemberIndex(Arena arena, boolean alliesAreFoes) {
        Pokemon[] team = alliesAreFoes ? arena.fp : arena.p;
        Pokemon[] opponents = alliesAreFoes ? arena.p : arena.fp;
        Engine engine = alliesAreFoes ? arena.foeEngine : arena.playerEngine;
        int NON_ACTIVE_MATCH_UP_PERCENT = engine.NON_ACTIVE_MATCH_UP_WEIGHT_PERCENT;
        double NON_ACTIVE_MATCH_UP_WEIGHT = (double) NON_ACTIVE_MATCH_UP_PERCENT/100;
        TreeMap<Integer, Integer> ratings = new TreeMap<>();
        if (team == null || opponents == null || opponents.length == 0) {
            return ratings;
        }

        for (int slot = 0; slot < team.length; slot++) {
            Pokemon member = team[slot];
            if (member == null || member.getCurrentHp() <= 0) continue;

            int rating = 0;

            // Primary opponent (slot 0)
            if (opponents[0] != null) {
                rating += rateMatchup(arena, member, opponents[0]);
            }

            // less weight for non-active pokemon
            for (int oppSlot = 1; oppSlot < opponents.length; oppSlot++) {
                if (opponents[oppSlot] != null) {
                    rating += (int) (NON_ACTIVE_MATCH_UP_WEIGHT * rateMatchup(arena, member, opponents[oppSlot]));
                }
            }
            if (member.getTimesSwitchedInBattle() >= 10) rating = 0; //prevents infinitely switching in & out!
            ratings.put(slot, rating);
        }

        return ratings;
    } //has param
    public static int rateMatchup(Arena arena, Pokemon dealer, Pokemon recipient) {
        Engine engine = dealer.isFoe() ? arena.foeEngine : arena.playerEngine;
        double dealerPenalty = dealer.isSkipNextTurn() ? engine.OWN_WAIT_PENALTY : 0;
        double recipientPenalty = recipient.isSkipNextTurn() ? engine.OPP_WAIT_PENALTY : 0;

        dealerPenalty += checkIfPokemonHasAGuranteedKOMove(arena, recipient, dealer) ? engine.OPP_HAS_GUARANTEED_KO_PENALTY : 0; //punish if opponent is just going to faint them

        double dealerScore = rateMovesetEffectiveness(arena, dealer, recipient) - dealerPenalty;
        double recipientScore = rateMovesetEffectiveness(arena, recipient, dealer) - recipientPenalty;

        if (dealerScore < 0 && recipientScore < 0) {
            return (int) (100 * Math.min(1, Math.abs(recipientScore / dealerScore)));
        }
        double numerator =       Math.max(0, dealerScore);
        double denominator = 2 * Math.max(1, recipientScore);

        return (int) (100 * Math.min (1, numerator / denominator));
    } //has param
    public static int rateMovesetEffectiveness(Arena arena, Pokemon dealer, Pokemon recipient) {
        if (dealer == null || recipient == null) return 0;

        return dealer.getMoves().stream()
                .filter(Objects::nonNull)
                .filter(Move::canUseMove)
                .mapToInt(move -> rateMove(arena, move, dealer, recipient))
                .sum();
    }
    public static boolean checkIfPokemonHasAGuranteedKOMove(Arena arena, Pokemon dealer, Pokemon recipient) {
        if (dealer == null || recipient == null) return false;
        boolean dealerIsFaster = Encounter.checkIfDealerIsFaster(arena, dealer, recipient);
        boolean isSpeedTie = Encounter.checkIfIsSpeedTie(arena);

        for (Move move : dealer.getMoves()) {
            double damageRatio = Math.min(1.5, calcDamageWithoutVariation(arena, move, dealer, recipient) / recipient.getCurrentMaxHp());
            double defendingHealthRatio = (double) recipient.getCurrentHp() / recipient.getCurrentMaxHp();

            if ((move.getPriority() > 0 || dealerIsFaster) && move.getDamage() > 0 && !checkIfMoveShouldFail(arena, move, dealer, recipient)
                    && damageRatio > defendingHealthRatio) {
                return true;
            }
            else if ((dealerIsFaster && !isSpeedTie) && move.getDamage() > 0 && !checkIfMoveShouldFail(arena, move, dealer, recipient)
                    && damageRatio > defendingHealthRatio) {
                return true;
            }
        }
        return false;
    }
    public static boolean checkIfPokemonHasAKOMove(Arena arena, Pokemon dealer, Pokemon recipient) {
        if (dealer == null || recipient == null) return false;

        for (Move move : dealer.getMoves()) {
            double damageRatio = Math.min(1.5, calcDamageWithoutVariation(arena, move, dealer, recipient) / recipient.getCurrentMaxHp());
            double defendingHealthRatio = (double) recipient.getCurrentHp() / recipient.getCurrentMaxHp();

            if (move.getDamage() > 0 && !checkIfMoveShouldFail(arena, move, dealer, recipient)
                    && damageRatio > defendingHealthRatio) {
                return true;
            }
        }
        return false;
    }
    public static boolean checkIfPokemonCanDealHalfHealth(Arena arena, Pokemon dealer, Pokemon recipient) {
        if (dealer == null || recipient == null) return false;

        for (Move move : dealer.getMoves()) {
            double damageRatio = Math.min(1.5, calcDamageWithoutVariation(arena, move, dealer, recipient) / recipient.getCurrentMaxHp());
            double defendingHealthRatio = (double) recipient.getCurrentHp() / recipient.getCurrentMaxHp();

            if (move.getDamage() > 0 && !checkIfMoveShouldFail(arena, move, dealer, recipient)
                    && damageRatio > .5 * defendingHealthRatio) {
                return true;
            }
        }
        return false;
    }
    public static boolean checkIfMoveIsAKOAndCanGoOff(Arena arena, Move move, Pokemon dealer, Pokemon recipient) {
        if (dealer == null || recipient == null) return false;
        boolean dealerIsFaster = Encounter.checkIfDealerIsFaster(arena, dealer, recipient);
        boolean isSpeedTie = Encounter.checkIfIsSpeedTie(arena);

        double damageRatio = Math.min(1.5, calcDamageWithoutVariation(arena, move, dealer, recipient) / recipient.getCurrentMaxHp());
        double defendingHealthRatio = (double) recipient.getCurrentHp() / recipient.getCurrentMaxHp();

        if ((move.getPriority() > 0 || dealerIsFaster) && move.getDamage() > 0 && !checkIfMoveShouldFail(arena, move, dealer, recipient)
                && damageRatio > defendingHealthRatio) {
            return true;
        }
        else return (dealerIsFaster && isSpeedTie) && move.getDamage() > 0 && !checkIfMoveShouldFail(arena, move, dealer, recipient)
                && damageRatio > defendingHealthRatio;
    }
    //game engine analysis strings
    public static String compareDecisionToEngine (Arena arena, Pokemon dealer, Pokemon recipient, Move moveChoice, Move engineMove, int slotChoice, int engineSlot) {
        StringBuilder comparisonStr = new StringBuilder();
        comparisonStr.append(compareMoveChoiceToEngine(arena, dealer, recipient, moveChoice, engineMove));
        return comparisonStr.toString();
    }
    public static String compareMoveChoiceToEngine(Arena arena, Pokemon dealer, Pokemon recipient, Move moveToCompare, Move engineMove) {
        if (User.hintMode == User.Hints.NO_HINTS || User.hintMode == User.Hints.SHOW_EFFECTIVENESS) return "";
        String fullRatingStr = "";
        String ratingWords = "";
        int scoreBasedOnComparisonToEngine = 0;

        if (moveToCompare == null) return ratingWords;

        final String RESET = "\u001B[0m";
        final String RED = "\u001B[31m";
        final String BLUE = "\u001B[34m";
        final String PURPLE = "\u001B[95m";
        final String GREEN = "\u001B[92m";
        final String YELLOW_GREEN = "\u001B[38;5;154m";
        final String YELLOW = "\u001B[93m";

        String color = "";
        String moveUsedStr = moveToCompare.getName();
        String engineMoveStr = engineMove.getName();

        scoreBasedOnComparisonToEngine = (int) (100 * (double) Math.max(0, rateMove(arena, moveToCompare, dealer, recipient))
                / Math.max(rateMove(arena, moveToCompare, dealer, recipient), rateMove(arena, engineMove, dealer, recipient)));
        if(scoreBasedOnComparisonToEngine < 25) {
            color = RED;
            ratingWords = "Terrible";
        }
        else if(scoreBasedOnComparisonToEngine < 50) {
            color = YELLOW;
            ratingWords = "Poor";
        }
        else if(scoreBasedOnComparisonToEngine < 75) {
            color = YELLOW_GREEN;
            ratingWords = "Okay ✔";
        }
        else if(scoreBasedOnComparisonToEngine < 100) {
            color = GREEN;
            ratingWords = "Good ✔✔";
        }
        else if (moveToCompare.getAccuracy() > 80){
            ratingWords = "Brilliant!";
            color = PURPLE;
        }
        if (moveUsedStr.equals(engineMoveStr)) {
            ratingWords = "Best ✔✔✔";
            scoreBasedOnComparisonToEngine = 100;
            color = BLUE;
        }

        String combinedRating = String.format("%-16s", scoreBasedOnComparisonToEngine + "% " + ratingWords);
        fullRatingStr = "| " + "Engine Rating: " + color + combinedRating + RESET;

        return fullRatingStr;
    }
    public static String makeMatchUpAnalysisStr(Arena arena, Pokemon dealer, Pokemon recipient) {
        if (User.hintMode == User.Hints.NO_HINTS || User.hintMode == User.Hints.SHOW_EFFECTIVENESS) return "";
        String fullRatingStr = "";

        final String RESET = "\u001B[0m";
        final String RED = "\u001B[31m";
        final String BLUE = "\u001B[34m";
        final String PURPLE = "\u001B[95m";
        final String GREEN = "\u001B[92m";
        final String YELLOW_GREEN = "\u001B[38;5;154m";
        final String YELLOW = "\u001B[93m";
        String color = "";

        int rating = rateMatchup(arena, dealer, recipient);

        String ratingWords = "";
        if(rating < 25) {
            color = RED;
            ratingWords = "Severely Outmatched";
        }
        else if(rating < 40) {
            color = YELLOW;
            ratingWords = "Outmatched";
        }
        else if(rating < 60) {
            color = YELLOW_GREEN;
            ratingWords = "Even";
        }
        else if(rating < 75) {
            color = GREEN;
            ratingWords = "Advantaged ✔";
        }
        else if(rating < 100) {
            color = BLUE;
            ratingWords = "Very Advantaged ✔✔";
        }
        else if (rating == 100) {
            ratingWords = "Overwhelmingly Advantaged ✔✔✔";
            color = PURPLE;
        }
        else {
            ratingWords = "Super Advantaged!";
            color = PURPLE;
        }
        fullRatingStr = "| " + "Engine Match-Up Analysis - " + color + rating + "% - " + ratingWords + RESET + " | ";

        return fullRatingStr;
    }
    public static String makeRecommendedSwitchStr(Arena arena, boolean displayArrow) {
        if (User.hintMode == User.Hints.NO_HINTS || User.hintMode == User.Hints.SHOW_EFFECTIVENESS) return "";
        final String RESET = "\u001B[0m";
        final String RED = "\u001B[31m";
        final String BLUE = "\u001B[34m";
        final String PURPLE = "\u001B[95m";
        final String GREEN = "\u001B[92m";
        final String YELLOW_GREEN = "\u001B[38;5;154m";
        final String YELLOW = "\u001B[93m";
        String pkmToSwitchTo = "";
        String arrow = "";
        if (displayArrow) arrow = "-->";
        int bestSlot = findBestPokemonSlotToHaveOut(arena, false);
        int bestSlotTrue = findBestPokemonSlotToHaveOut(arena, false, 1.0);

        if (bestSlot != 0) pkmToSwitchTo = arrow + " Engine Switch-In Choice  - " + GREEN + "Switch to " + arena.p[bestSlot].getName() + "✔" + RESET;
        else if (bestSlotTrue != 0) pkmToSwitchTo = arrow + " Engine Switch-In Choice  - " + BLUE + "Leave " + arena.p[0].getName() +" in✔✔" + RESET;
        else pkmToSwitchTo = arrow + " Engine Switch-In Choice  - " + PURPLE + "Leave " + arena.p[0].getName() +" in✔✔✔" + RESET;

        return  pkmToSwitchTo;
    }
    public static String makeRecommendedSwitchStr(Arena arena) {
        return makeRecommendedSwitchStr(arena, true);
    } //wrapper

}
