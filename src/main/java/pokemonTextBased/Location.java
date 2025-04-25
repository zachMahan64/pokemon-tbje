package pokemonTextBased;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class Location {
    public enum Area {
        // Cities and Towns
        PALLET_TOWN("Pallet Town"),
        VIRIDIAN_CITY("Viridian City"),
        PEWTER_CITY("Pewter City"),
        CERULEAN_CITY("Cerulean City"),
        VERMILION_CITY("Vermilion City"),
        LAVENDER_TOWN("Lavender Town"),
        CITY_CENTER("Celadon City Center"),
        FUCHSIA_CITY("Fuchsia City"),
        SAFFRON_CITY("Saffron City"),
        CINNABAR_ISLAND("Cinnabar Island"),
        ROCKETOPOLIS("Rocketopolis"),
        VAUGHAN_DISTRICT("Vaughan District"),

        // Dungeons and Special Areas
        MT_MOON("Mt. Moon"),
        ROCK_TUNNEL("Rock Tunnel"),
        POKEMON_TOWER("Pokemon Tower"),
        POKEMON_MANSION("Pokemon Mansion"),
        SAFARI_ZONE("Safari Zone"),
        SEAFOAM_ISLANDS("Seafoam Islands"),
        VICTORY_ROAD("Victory Road"),
        INDIGO_PLATEAU("Indigo Plateau"),
        POWER_PLANT("Power Plant"),
        CERULEAN_CAVE("Cerulean Cave"),
        DIGLETTS_CAVE("Diglett's Cave"),
        VIRIDIAN_FOREST("Viridian Forest"),

        // Buildings
        CELADON_GAME_CORNER("Celadon Game Corner"),
        SILPH_CO("Silph Co."),
        CINNABAR_LAB("Cinnabar Lab");

        private final String displayName;

        Area(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }
    //map logic
    public static void openMap(Scanner sc1) throws InterruptedException, ExecutionException {
        Sound.stopAllSounds();
        label:
        while (!Party.checkIfEveryPkmHasFainted()) {
            Sound.playMusicOnLoop("src/main/music/creditsTheme.mp3");
            Graphics.printMap();
            System.out.println("Where would you like to go?");

            String choiceMap = sc1.nextLine().trim().toUpperCase();

            try {
                int routeNumber = Integer.parseInt(choiceMap);
                if (routeNumber >= 1 && routeNumber <= 25) {
                    goOnRoute(routeNumber, sc1);
                    continue;
                } else {
                    System.out.println("Invalid route! Please enter a number between 1 and 25.");
                    Thread.sleep(User.textSpeed);
                    continue;
                }
            } catch (NumberFormatException e) {
                //proceed! :D
            }

            switch (choiceMap) {
                case "P":
                    goToPalletTown(sc1);
                    break;
                case "V":
                    goToViridianCity(sc1);
                    break;
                case "W":
                    goToPewterCity(sc1);
                    break;
                case "C":
                    goToCeruleanCity(sc1);
                    break;
                case "M":
                    goToVermillionCity(sc1);
                    break;
                case "L":
                    goToLavenderTown(sc1);
                    break;
                case "S":
                    goToSaffronCity(sc1);
                    break;
                case "F":
                    goToFuchsiaCity(sc1);
                    break;
                case "I":
                    goToCinnabarIsland(sc1);
                    break;
                case "T":
                    goToIndigoPlateau(sc1);
                    break;
                case "R":
                    goToRocketopolis(sc1);
                    break;
                case "D":
                    goToCityCenter(sc1);
                    break;
                case "G":
                    goToVaughanDistrict(sc1);
                    break;
                case "B":
                    break label;
                default:
                    System.out.println("Invalid input! Please enter a route (1-25) or another valid location.");
                    Game.pressEnterToContinue();
                    break;
            }
        }
        Sound.stopAllSounds();
        rushToNearestPokemonCenterIfFainted();
    }
    public static void rushToNearestPokemonCenterIfFainted() throws InterruptedException {
        if (Party.checkIfEveryPkmHasFainted()) {
            System.out.println("You whited out and were rushed to the nearest Pokemon Center!");
            Party.healParty();
            Sound.playSoundOnce("src/main/music/heal.mp3");
            Game.pressEnterToContinue();
        }
    }
    //city logic
    //Pallet Town
    public static void goToPalletTown(Scanner sc1) throws InterruptedException {
        Sound.stopAllSounds();
        String choice= " ";
        do{
            Sound.playMusicOnLoop("src/main/music/palletTownTheme.mp3");
            Graphics.printPalletTown();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("H")){
                goHome(sc1);
            }
            if(choice.equals("K")){
                goToOakLabs(sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
        Sound.stopAllSounds();
    }
    public static void goHome(Scanner sc1) throws InterruptedException{
        String choice= "";
        do{
            Sound.playMusicOnLoop("src/main/music/palletTownTheme.mp3");
            Graphics.printHome();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("T")){
                NPC.talkTo(NPC.Character.MOM, sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
    }
    public static void goToOakLabs(Scanner sc1) throws InterruptedException {
        Sound.stopAllSounds();
        String choice= " ";
        do{
            Sound.playMusicOnLoop("src/main/music/labTheme.mp3");
            Graphics.printOakLabs();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("T")){
                NPC.talkTo(NPC.Character.PROFESSOR, sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
        Sound.stopAllSounds();
    }
    //Viridian City
    public static void goToViridianCity(Scanner sc1) throws InterruptedException, ExecutionException {
        Sound.stopAllSounds();
        String choice= " ";
        do{
            Sound.playMusicOnLoop("src/main/music/viridianTheme.mp3");
            Graphics.printViridianCity();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("P")){
                enterPokemonCenter(sc1);
            }
            if(choice.equals("M")) {
                enterPokemart(sc1);
            }
            if(choice.equals("G")) {
                goToViridianGym(sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
        Sound.stopAllSounds();
    }
    public static void goToViridianGym(Scanner sc1) throws InterruptedException, ExecutionException {
        String choice= " ";
        do{
            Sound.playMusicOnLoop("src/main/music/viridianTheme.mp3");
            Graphics.printViridianGymExterior();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("E")){
                enterGym(Trainer.Title.VIRIDIAN_GYM_LEADER, sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
    }
    //Pewter City
    public static void goToPewterCity(Scanner sc1) throws InterruptedException, ExecutionException {
        Sound.stopAllSounds();
        String choice= "";
        do{
            Sound.playMusicOnLoop("src/main/music/pewterTheme.mp3");
            Graphics.printPewterCity();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("P")){
                enterPokemonCenter(sc1);
            }
            if(choice.equals("M")) {
                enterPokemart(sc1);
            }
            if(choice.equals("G")) {
                goToPewterGym(sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
        Sound.stopAllSounds();
    }
    public static void goToPewterGym(Scanner sc1) throws InterruptedException, ExecutionException {
        String choice= " ";
        do{
            Sound.playMusicOnLoop("src/main/music/pewterTheme.mp3");
            Graphics.printPewterGymExterior();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("E")){
                enterGym(Trainer.Title.PEWTER_GYM_LEADER, sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
    }
    //Cerulean City
    public static void goToCeruleanCity(Scanner sc1) throws InterruptedException, ExecutionException {
        Sound.stopAllSounds();
        String choice= "";
        do{
            Sound.playMusicOnLoop("src/main/music/ceruleanTheme.mp3");
            Graphics.printCeruleanCity();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("P")){
                enterPokemonCenter(sc1);
            }
            if(choice.equals("M")) {
                enterPokemart(sc1);
            }
            if(choice.equals("G")) {
                goToCeruleanGym(sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
        Sound.stopAllSounds();
    }
    public static void goToCeruleanGym(Scanner sc1) throws InterruptedException, ExecutionException {
        String choice= " ";
        do{
            Sound.playMusicOnLoop("src/main/music/ceruleanTheme.mp3");
            Graphics.printCeruleanGymExterior();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("E")){
                enterGym(Trainer.Title.CERULEAN_GYM_LEADER, sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
    }
    //Vermillion City
    public static void goToVermillionCity(Scanner sc1) throws InterruptedException, ExecutionException {
        Sound.stopAllSounds();
        String choice= "";
        do{
            Sound.playMusicOnLoop("src/main/music/vermillionTheme.mp3");
            Graphics.printVermillionCity();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("P")){
                enterPokemonCenter(sc1);
            }
            if(choice.equals("M")) {
                enterPokemart(sc1);
            }
            if(choice.equals("G")) {
                goToVermillionGym(sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
        Sound.stopAllSounds();
    }
    public static void goToVermillionGym(Scanner sc1) throws InterruptedException, ExecutionException {
        String choice= " ";
        do{
            Sound.playMusicOnLoop("src/main/music/vermillionTheme.mp3");
            Graphics.printVermillionGymExterior();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("E")){
                enterGym(Trainer.Title.VERMILLION_GYM_LEADER, sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
    }
    //Lavender Town
    public static void goToLavenderTown(Scanner sc1) throws InterruptedException {
        Sound.stopAllSounds();
        String choice= "";
        do{
            Sound.playMusicOnLoop("src/main/music/lavenderTownTheme.mp3");
            Graphics.printLavenderTown();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("P")){
                enterPokemonCenter(sc1);
            }
            if(choice.equals("M")) {
                enterPokemart(sc1);
            }
            if(choice.equals("T")) {
                goToPokemonTower(sc1);
            }
            if(choice.equals("S")) {
                goToStVagaChurch(sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
        Sound.stopAllSounds();
    }
    public static void goToPokemonTower(Scanner sc1) throws InterruptedException {
        String choice= "";
        do{
            Sound.playMusicOnLoop("src/main/music/lavenderTownTheme.mp3");
            Graphics.printPokemonTower();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("E")){
                System.out.println("Sorry, this building is closed to visitors.");
                Thread.sleep(User.textSpeed);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
    }
    public static void goToStVagaChurch(Scanner sc1) throws InterruptedException {
        Sound.stopAllSounds();
        String choice= "";
        do{
            Sound.playMusicOnLoop("src/main/music/churchTheme.mp3");
            Graphics.printStVagaChurchExterior();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("E")){
                enterStVagaChurch(sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
        Sound.stopAllSounds();
    }
    public static void enterStVagaChurch(Scanner sc1) throws InterruptedException {
        String choice= "";
        do{
            Graphics.printStVagaChurchInterior();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("T")){
                NPC.talkTo(NPC.Character.FATHER_PUCHTON, sc1);
            }
            if(choice.equals("C")){
                confessSins(sc1);
            }
            if(choice.equals("M")){
                makeADonationToStVagaChurch(sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
    }
    public static void confessSins(Scanner sc1) throws InterruptedException {
        Random rand = new Random();
        Graphics.printNPC(NPC.Character.FATHER_PUCHTON);
        System.out.println("FATHER PUCHTON:");
        String[] lines = {"May the Lord forgive you.",
                "Your sins are forgiven.",
                "Fear not, my child, you are forgiven.",
                "The Lord shall forgive you, I am sure.",
                "May the Lord bless you, my child."};
        String line = lines[rand.nextInt(lines.length-1)];
        System.out.println(line);
        Game.pressEnterToContinue(sc1);
                System.out.println("A healing feeling permeates your being...");
        if(User.reputation < 0) User.increaseReputation(10);
        Game.pressEnterToContinue();
    }
    public static void makeADonationToStVagaChurch(Scanner sc1) throws InterruptedException{
        Graphics.printNPC(NPC.Character.FATHER_PUCHTON);
        System.out.println("FATHER PUCHTON:");
        System.out.println("How much would you like to donate? (0 to cancel)");
        long donation = getValidLong(sc1);
        if(donation == 0) {
            Graphics.printNPC(NPC.Character.FATHER_PUCHTON);
            System.out.println("FATHER PUCHTON:");
            System.out.println("Oh, you decided not to donate?");
            Game.pressEnterToContinue();
        }
        else if(donation > 0) {
            User.increaseReputation((int) donation/10);
            Graphics.printNPC(NPC.Character.FATHER_PUCHTON);
            System.out.println("FATHER PUCHTON:");
            if (donation < Bag.getPokedollars() && donation < 1000) {
                System.out.println("Wow, I really appreciate that--you're a blessed soul.");
                System.out.println();
                Bag.spendPokedollars(donation);
            }
            else if (donation < Bag.getPokedollars()){
                System.out.println("Wow, God bless your soul. That is so generous.");
                System.out.println();
                Bag.spendPokedollars(donation);
            }
            else {
                System.out.println("Come back when you're richer.");
            }
            Game.pressEnterToContinue();
        }
    }
    //Saffron City
    public static void goToSaffronCity(Scanner sc1) throws InterruptedException, ExecutionException {
        Sound.stopAllSounds();
        String choice= "";
        do{
            Sound.playMusicOnLoop("src/main/music/saffronTheme.mp3");
            Graphics.printSaffronCity();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("P")){
                enterPokemonCenter(sc1);
            }
            if(choice.equals("M")) {
                enterPokemart(sc1);
            }
            if(choice.equals("G")) {
                goToSaffronGym(sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
        Sound.stopAllSounds();
    }
    public static void goToSaffronGym(Scanner sc1) throws InterruptedException, ExecutionException {
        String choice= " ";
        do{
            Sound.playMusicOnLoop("src/main/music/saffronTheme.mp3");
            Graphics.printSaffronGymExterior();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("E")){
                enterGym(Trainer.Title.SAFFRON_GYM_LEADER, sc1);
            }
            if(choice.equals("S")){
                slideIntoSaffronNightClub(sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
    }
    public static void slideIntoSaffronNightClub(Scanner sc1) throws InterruptedException {
        Sound.stopAllSounds();
        Sound.playMusicOnLoop("src/main/music/nightClubTheme.mp3");
        String choice= " ";
        do{
            Graphics.printNightClub();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("C")) {
                System.out.println("[ENTER] to stop chilling");
                sc1.nextLine();
            }
            if(choice.equals("S")) {
                NPC.talkTo(NPC.Character.SABRINA_NIGHTCLUB, sc1);
            }
            if(choice.equals("B")) {
                NPC.talkTo(NPC.Character.BLUE_NIGHTCLUB, sc1);
            }
            if(choice.equals("R")) {
                label:
                while (true) {
                    System.out.println("What should the band play?");
                    System.out.println(" [1] A relaxing song");
                    System.out.println(" [2] A song sung by Sabrina");
                    System.out.println(" [3] A happy song");
                    System.out.println(" [C] Cancel");
                    String choiceSong = sc1.nextLine().trim().toUpperCase();
                    switch (choiceSong) {
                        case "C" -> {
                            break label;
                        }
                        case "1" -> {
                            Sound.stopAllSounds();
                            Sound.playMusicOnLoop("src/main/music/nightClubTheme.mp3");
                            break label;
                        }
                        case "2" -> {
                            Sound.stopAllSounds();
                            Sound.playMusicOnLoop("src/main/music/sabrinasSong.mp3");
                            break label;
                        }
                        case "3" -> {
                            Sound.stopAllSounds();
                            Sound.playMusicOnLoop("src/main/music/happySong.mp3");
                            break label;
                        }
                        default -> {
                            System.out.println("... That wasn't an option!");
                            Game.pressEnterToContinue(sc1);
                        }
                    }
                }
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
        Sound.stopAllSounds();
    }
    //Fuchsia City
    public static void goToFuchsiaCity(Scanner sc1) throws InterruptedException, ExecutionException {
        Sound.stopAllSounds();
        String choice= "";
        do{
            Sound.playMusicOnLoop("src/main/music/fuchsiaTheme.mp3");
            Graphics.printFuchsiaCity();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("P")){
                enterPokemonCenter(sc1);
            }
            if(choice.equals("M")) {
                enterPokemart(sc1);
            }
            if(choice.equals("G")) {
                goToFuchsiaGym(sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
        Sound.stopAllSounds();
    }
    public static void goToFuchsiaGym(Scanner sc1) throws InterruptedException, ExecutionException {
        String choice= " ";
        do{
            Sound.playMusicOnLoop("src/main/music/fuchsiaTheme.mp3");
            Graphics.printFuchsiaGymExterior();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("E")){
                enterGym(Trainer.Title.FUCHSIA_GYM_LEADER, sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
    }
    //Cinnabar Island
    public static void goToCinnabarIsland(Scanner sc1) throws InterruptedException, ExecutionException {
        Sound.stopAllSounds();
        String choice= "";
        do{
            Sound.playMusicOnLoop("src/main/music/cinnabarTheme.mp3");
            Graphics.printCinnabarIsland();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("P")){
                enterPokemonCenter(sc1);
            }
            if(choice.equals("M")) {
                enterPokemart(sc1);
            }
            if(choice.equals("G")) {
                goToCinnabarGym(sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
        Sound.stopAllSounds();
    }
    public static void goToCinnabarGym(Scanner sc1) throws InterruptedException, ExecutionException {
        String choice= "";
        do{
            Sound.playMusicOnLoop("src/main/music/cinnabarTheme.mp3");
            Graphics.printCinnabarGym();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("E")){
                enterGym(Trainer.Title.CINNABAR_GYM_LEADER, sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
    }
    //Indigo Plateau
    public static void goToIndigoPlateau(Scanner sc1) throws InterruptedException, ExecutionException {
        Sound.stopAllSounds();
        String choice= "";
        do{
            Sound.playMusicOnLoop("src/main/music/indigoPlateauTheme.mp3");
            Graphics.printIndigoPlateau();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("P")){
                enterPokemonCenter(sc1);
            }
            if(choice.equals("M")) {
                enterPokemart(sc1);
            }
            if(choice.equals("F")) {
                faceThePokemonLeague(sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
        Sound.stopAllSounds();
    }
    public static void faceThePokemonLeague(Scanner sc1) throws InterruptedException, ExecutionException {
        String choice= "";
        do{
            Sound.playMusicOnLoop("src/main/music/indigoPlateauTheme.mp3");
            Graphics.printPokemonLeagueStadium();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("B")){
                battleTheEliteFour(sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
    }
    public static void battleTheEliteFour(Scanner sc1) throws InterruptedException, ExecutionException {
            boolean beatLorelei = false;
            boolean beatBruno = false;
            boolean beatAgatha = false;
            boolean beatLance = false;
            openOptionsOrContinue(sc1);
            beatLorelei = Encounter.enterTrainerBattle(new Trainer(Trainer.Title.ELITE_FOUR_LORELEI), sc1);
            Sound.playMusicOnLoop("src/main/music/indigoPlateauTheme.mp3");
            if (beatLorelei) {
                openOptionsOrContinue(sc1);
                beatBruno = Encounter.enterTrainerBattle(new Trainer(Trainer.Title.ELITE_FOUR_BRUNO), sc1);
                Sound.playMusicOnLoop("src/main/music/indigoPlateauTheme.mp3");
            }
            if (beatBruno) {
                openOptionsOrContinue(sc1);
                beatAgatha = Encounter.enterTrainerBattle(new Trainer(Trainer.Title.ELITE_FOUR_AGATHA), sc1);
                Sound.playMusicOnLoop("src/main/music/indigoPlateauTheme.mp3");
            }
            if (beatAgatha) {
                openOptionsOrContinue(sc1);
                beatLance = Encounter.enterTrainerBattle(new Trainer(Trainer.Title.ELITE_FOUR_LANCE), sc1);
                Sound.playMusicOnLoop("src/main/music/indigoPlateauTheme.mp3");
            }
            if (beatLance) {
                System.out.println("You beat the elite four...");
                System.out.println("But the CHAMPION, RED, is no where to be seen...");
                Game.pressEnterToContinue(sc1);
            }
        rushToNearestPokemonCenterIfFainted();
    }
    //Rocketopolis
    public static void goToRocketopolis(Scanner sc1) throws InterruptedException, ExecutionException{
        Sound.stopAllSounds();
        String choice= " ";
        do{
            Sound.playMusicOnLoop("src/main/music/vaughanDistrictTheme.mp3");
            Graphics.printRocketopolis();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
            if(choice.equals("P")){
                enterPokemonCenter(sc1);
            }
            if(choice.equals("M")){
                enterPokemart(sc1);
            }
            if(choice.equals("C")){
                enterCasino(sc1);
            }
            if(choice.equals("R")){
                enterRapidashRacetrack(sc1);
            }
            if(choice.equals("S")){
                goOntoTheStreetsOfRocketopolis(sc1);
            }
            if(choice.equals("G")){
                goToRocketopolisGym(sc1);
            }
            if(choice.equals("T")){
                goToTheColosseum(sc1);
            }
        } while (!choice.equals("L"));
        Sound.stopAllSounds();
    }
    public static void goToRocketopolisGym(Scanner sc1) throws InterruptedException, ExecutionException {
        String choice= "";
        do{
            Sound.playMusicOnLoop("src/main/music/vaughanDistrictTheme.mp3");
            Graphics.printRocketopolisGymExterior();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("E")){
                enterGym(Trainer.Title.ROCKETOPOLIS_GYM_LEADER, sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
    }
    public static void enterCasino(Scanner sc1) throws  InterruptedException{
        Sound.stopAllSounds();
        Sound.playMusicOnLoop("src/main/music/casinoTheme.mp3");
        String choice;
        do{
            Graphics.printCasinoLogo();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("B")){
                Casino.startBlackjack();
            }
            if(choice.equals("W")){
                Casino.startWhosThatPokemon(sc1);
            }
            if(choice.equals("R")){
                Casino.startRoulette();
            }
        } while (!choice.equals("L"));
        System.out.println("Thanks for supporting Team Rocket. Come back soon!");
        Thread.sleep(User.textSpeed);
        Sound.stopAllSounds();
    }
    public static void enterRapidashRacetrack(Scanner sc1) throws InterruptedException {
        String choice;
        do{
            Graphics.printRapidashTrack();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("B")){
                RapidashRace.simulateRace();
            }
        } while (!choice.equals("L"));
        System.out.println("See you at the track again soon!");
        Thread.sleep(User.textSpeed);
    }
    public static void goOntoTheStreetsOfRocketopolis(Scanner sc1) throws InterruptedException{
        Sound.stopAllSounds();
        String choice;
        do{
            Sound.playMusicOnLoop("src/main/music/rocketopolisTheme.mp3");
            Graphics.printRocketopolisStreets();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
            if(choice.equals("H")){
                goToPokemonBlackMarket(sc1);
            }
            if(choice.equals("R")){
                robSomeone(sc1);
            }
            if(choice.equals("V")){
                visitSchizoJoe(sc1);
            }
        } while (!choice.equals("L"));
        Sound.stopAllSounds();
    }
    public static void robSomeone(Scanner sc1) throws InterruptedException{
        String choice;
        Random rand = new Random();
        do{
            double chance = Math.random();
            if(chance < 0.85) {
                int moneyStolen = (int) (Math.random() * 80);
                String[] innocentPeople = {
                        "an innocent old man", "a mother and her child", "a teenage girl", "a teenage boy", "a middle-aged woman",
                        "an innocent passerby", "an old lady carrying her groceries", "a child", "a father and his son", "a mother and her son",
                        "a mother and her daughter", "a retired Team Rocket member", "Nurse Joy", "Erika", "a hiker", "a cyclist", "a teacher",
                        "a man walking his dog", "a kid riding a scooter", "a veteran", "an innocent passerby", "a pedestrian"
                };
                String randomPerson = innocentPeople[rand.nextInt(innocentPeople.length)];
                System.out.println("You stole " + moneyStolen + " Pokedollars from " + randomPerson + ".\n");
                Thread.sleep((long) (.5 * User.textSpeed));
                Bag.adjustPokedollarBalance(moneyStolen);
                Game.pressEnterToContinue(sc1);
            }
            else{
                int moneyStolenFromPlayer = (int) (Math.random() * 100);
                System.out.println("You tried to steal from a Team Rocket member and got jumped! Serves you right!");
                Thread.sleep((long) (.5 * User.textSpeed));
                Bag.losePokedollars(moneyStolenFromPlayer);
                Game.pressEnterToContinue(sc1);
            }
            User.decreaseReputation(5);
            System.out.println("Try to rob someone else? (Y/N)");
            choice = sc1.nextLine().trim().toUpperCase();
        } while (!choice.equals("N"));
    }
    public static void visitSchizoJoe(Scanner sc1) throws InterruptedException{
        String choice;
        do{
            Sound.playMusicOnLoop("src/main/music/rocketopolisTheme.mp3");
            Graphics.printSchizoJoe();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("T")) {
                NPC.talkTo(NPC.Character.SCHIZO_JOE, sc1);
            }
            if(choice.equals("W")) {
                whisperToSchizoJoe(sc1);
            }
            if(choice.equals("O")) {
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
    }
    public static void whisperToSchizoJoe(Scanner sc1) throws InterruptedException{
        String whisperChoice;
        do{
            Sound.playMusicOnLoop("src/main/music/rocketopolisTheme.mp3");
            Graphics.printNPC(NPC.Character.SCHIZO_JOE);
            System.out.println("Whisper to Schizo Joe or [C] to cancel: ");
            whisperChoice = sc1.nextLine().trim().toUpperCase();
            if (whisperChoice.equalsIgnoreCase("odeToJoe") || whisperChoice.equalsIgnoreCase("odeToJoy")
                    || whisperChoice.equalsIgnoreCase("ode to joy") || whisperChoice.equalsIgnoreCase("ode to joe")) {
                Graphics.printNPC(NPC.Character.SCHIZO_JOE);
                System.out.println("SCHIZO JOE:");
                System.out.println("Yes... yes... that's it... Ode to Joe!");
                Game.pressEnterToContinue(sc1);
                enterSchizoGodMode(sc1);
            }
            else if (!whisperChoice.equalsIgnoreCase("C")) {
                Graphics.printNPC(NPC.Character.SCHIZO_JOE);
                System.out.println("SCHIZO JOE:");
                System.out.println("Hmm... no... no.");
                Game.pressEnterToContinue(sc1);
            }
        } while (!whisperChoice.equals("C"));
    }
    public static void enterSchizoGodMode(Scanner sc1) throws InterruptedException{
        Sound.stopAllSounds();
        Sound.playMusicOnLoop("src/main/music/odeToJoy.mp3");
        String choice;
        do{
            Graphics.printSchizoJoeAscension();
            choice = sc1.nextLine().trim().toUpperCase();
            if (choice.equals("1")) {
                getPkmFromJoe(sc1);
            }
            if (choice.equals("2")) {
                getPokedollarsFromJoe(sc1);
            }
            if (choice.equals("O")) {
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("C"));
        Sound.stopAllSounds();
    }
    public static void getPkmFromJoe(Scanner sc1) throws InterruptedException {
        String nameChoice;
        int level;
        String choiceShiny;
        boolean shiny;
        String choiceToContinue = "";
        do{
            System.out.println("Tell Arceus the name of the Pokemon you wish to summon:");
            nameChoice = sc1.nextLine().trim().toUpperCase();
            System.out.println("Tell Arceus the level of the Pokemon you wish to summon:");
            try {
                level = sc1.nextInt();
                sc1.nextLine();
                if (level <= 0 || level > 100) {
                    System.out.println("Arceus laughs: LEVEL MUST BE 1-100.");
                    Game.pressEnterToContinue(sc1);
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Arceus booms: INVALID LEVEL. YOU ARE UNWORTHY.");
                sc1.nextLine();
                Game.pressEnterToContinue(sc1);
                continue;
            }
            do {
                System.out.println("Shall the Pokemon be shiny? (Y/N)");
                choiceShiny = sc1.nextLine().trim().toUpperCase();
            } while (!choiceShiny.equals("Y") && !choiceShiny.equals("N"));
            shiny = choiceShiny.equals("Y");
            Party.addToParty(new Pokemon(Species.getSpecies(nameChoice), level, shiny), sc1);
            System.out.println("Summon another Pokemon? (Y/N)");
            choiceToContinue = sc1.nextLine().trim().toUpperCase();
        } while (choiceToContinue.equals("Y"));
    }
    public static void getPokedollarsFromJoe(Scanner sc1) throws InterruptedException {
        String moneyInput;
        long money;
        String choiceToContinue = "";
        do{
            System.out.println("Tell Mr. Mime how many Pokedollars you want:");
            try {
                moneyInput = sc1.nextLine().trim();
                money = Long.parseLong(moneyInput);
                if (money == 0) {
                    System.out.println("Mr. Mime screeches: YOU AWOKE ME FOR NOTHING?");
                    Game.pressEnterToContinue(sc1);
                    continue;
                }
                if (money <= 0) {
                    System.out.println("Mr. Mime screeches: YOU FOOL!");
                    Game.pressEnterToContinue(sc1);
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Mr. Mime booms: INVALID REQUEST. YOU ARE UNWORTHY.");
                Game.pressEnterToContinue(sc1);
                continue;
            }
            System.out.println("Mr. Mime printed " + money + " tainted Pokedollars and put them in your greedy pockets...");
            Bag.adjustPokedollarBalance(money);
            Game.pressEnterToContinue(sc1);
            System.out.println("Print more money? (Y/N)");
            choiceToContinue = sc1.nextLine().trim().toUpperCase();
        } while (choiceToContinue.equals("Y"));
    }
    public static void goToPokemonBlackMarket(Scanner sc1) throws InterruptedException{
        String choice;
        do{
            Graphics.printHotDogStand();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("R")){
                System.out.println("Buy a regular hot dog? It's 3 Pokedollars. (Y/N)");
                String choiceBuyRegularDog = sc1.nextLine().trim().toUpperCase();
                if (choiceBuyRegularDog.equals("Y")) {
                    System.out.println("You bought a regular ol' dog.");
                    Game.pressEnterToContinue(sc1);
                    System.out.println("You just devoured it!");
                    Game.pressEnterToContinue(sc1);
                }
                else {
                    continue;
                }
            }
            if(choice.equals("B")){
                System.out.println("Buy a special BIG dog? It's 5 Pokedollars. (Y/N)");
                String choiceBuyBigDog = sc1.nextLine().trim().toUpperCase();
                if (choiceBuyBigDog.equals("Y")) {
                    System.out.println("You bought a BIG dog. It's real BIG.");
                    Game.pressEnterToContinue(sc1);
                    System.out.println("You just completely devoured it!");
                    Game.pressEnterToContinue(sc1);
                    System.out.println("\nYour party has been restored back to full health!\n");
                    Game.pressEnterToContinue(sc1);
                }
                else {
                    continue;
                }
            }
            if(choice.equals("S")){
                askServerAboutSomethingElse(sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
    }
    public static void askServerAboutSomethingElse(Scanner sc1) throws InterruptedException{
        String choice;
        do{
            System.out.println("     Which hot dogs interest you?");
            System.out.println("=========================================");
            System.out.println("[1] See Kanto-original hot dogs");
            System.out.println("[2] See imported hot dogs");
            System.out.println("[C] Cancel");
            System.out.println("=========================================");
            System.out.println("You have: " + Bag.getPokedollars() + " Pokedollars");
            System.out.println("-----------------------------------------");
            System.out.println("Make a selection: ");
            choice = sc1.nextLine().trim().toUpperCase();
            if (choice.equals("1")) {
                seeKantoHotDogs(sc1);
            }
            if (choice.equals("2")) {
                seeImportedHotDogs(sc1);
            }
        } while (!choice.equals("C"));
    }
    public static void seeKantoHotDogs(Scanner sc1) throws InterruptedException {
        String pokemonChoice = "";
        do {
            System.out.println("     Which hot dog interests you?");
            System.out.println("=========================================");
            System.out.println("[1] NidoranM   |  2000 Pokedollars");
            System.out.println("[2] Growlithe  |  2000 Pokedollars");
            System.out.println("[3] Meowth     |  2000 Pokedollars");
            System.out.println("[4] Magikarp   |  5000 Pokedollars");
            System.out.println("[5] Dratini    | 10000 Pokedollars");
            System.out.println("[6] Scyther    | 10000 Pokedollars");
            System.out.println("[7] Pinsir     | 10000 Pokedollars");
            System.out.println("[8] Bulbasaur  | 30000 Pokedollars");
            System.out.println("[9] Charmander | 30000 Pokedollars");
            System.out.println("[10] Squirtle  | 30000 Pokedollars");
            System.out.println("[C] Cancel");
            System.out.println("=========================================");
            System.out.println("You have: " + Bag.getPokedollars() + " Pokedollars");
            System.out.println("-----------------------------------------");
            System.out.println("Make a selection: ");

            pokemonChoice = sc1.nextLine().toUpperCase().trim();
            switch (pokemonChoice) {
                case "1":
                    buyPokemon("NidoranM", 2000, sc1);
                    break;
                case "2":
                    buyPokemon("Growlithe", 2000, sc1);
                    break;
                case "3":
                    buyPokemon("Meowth", 2000, sc1);
                    break;
                case "4":
                    buyPokemon("Magikarp", 5000, sc1);
                    break;
                case "5":
                    buyPokemon("Dratini", 10000, sc1);
                    break;
                case "6":
                    buyPokemon("Scyther", 10000, sc1);
                    break;
                case "7":
                    buyPokemon("Pinsir", 10000, sc1);
                    break;
                case "8":
                    buyPokemon("Bulbasaur", 30000, sc1);
                    break;
                case "9":
                    buyPokemon("Charmander", 30000, sc1);
                    break;
                case "10":
                    buyPokemon("Squirtle", 30000, sc1);
                    break;
                case "C":
                    System.out.println("Don't want a hot dog?");
                    Game.pressEnterToContinue(sc1);
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    Game.pressEnterToContinue(sc1);
            }
        } while (!pokemonChoice.equals("C"));
    }
    public static void seeImportedHotDogs(Scanner sc1) throws InterruptedException {
        String pokemonChoice = "";
        do {
            System.out.println("      Which hot dog interests you?");
            System.out.println("=========================================");
            System.out.println("[1] Treecko  |  30000 Pokedollars");
            System.out.println("[2] Torchic  |  30000 Pokedollars");
            System.out.println("[3] Mudkip   |  30000 Pokedollars");
            System.out.println("[C] Cancel");
            System.out.println("=========================================");
            System.out.println("You have: " + Bag.getPokedollars() + " Pokedollars");
            System.out.println("-----------------------------------------");
            System.out.println("Make a selection: ");

            pokemonChoice = sc1.nextLine().toUpperCase().trim();
            switch (pokemonChoice) {
                case "1":
                    buyPokemon("Treecko", 30000, sc1);
                    break;
                case "2":
                    buyPokemon("Torchic", 30000, sc1);
                    break;
                case "3":
                    buyPokemon("Mudkip", 30000, sc1);
                    break;
                case "C":
                    System.out.println("Don't want a hot dog?");
                    Game.pressEnterToContinue(sc1);
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    Game.pressEnterToContinue(sc1);
            }
        } while (!pokemonChoice.equals("C"));
    }
    public static void buyPokemon(String pokemonName, int price, Scanner sc1) throws InterruptedException {
        Pokemon pokemon = new Pokemon(Species.getSpecies(pokemonName), User.checkLevelCap() - 5, Pokemon.getShinyOdds());
        Graphics.printPokemon(pokemon);
        System.out.println("Are you sure you want to buy " + pokemonName + " for " + price + " Pokedollars? (Y/N)");
        String confirmation = "";
        confirmation = sc1.nextLine().trim().toUpperCase();
        if (confirmation.equals("Y")) {
            if (Bag.getPokedollars() >= price) {
                System.out.println("You bought " + pokemonName + " from the black market.");
                User.decreaseReputation(20);
                Bag.spendPokedollars(price);
                Party.addToParty(pokemon, sc1);
            } else {
                System.out.println("You can't afford " + pokemonName + "!");
                Game.pressEnterToContinue(sc1);
            }
        }
        else {
            System.out.println("Oh, you changed your mind?");
            Game.pressEnterToContinue(sc1);
        }
    }
    public static void goToTheColosseum(Scanner sc1) throws InterruptedException, ExecutionException {
        Sound.stopAllSounds();
        String choice= "";
        do{
            Sound.playMusicOnLoop("src/main/music/indigoPlateauTheme.mp3");
            Graphics.printTheColosseum();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("E")){
                enterTheColosseum(sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
        Sound.stopAllSounds();
    }
    public static void enterTheColosseum(Scanner sc1) throws InterruptedException, ExecutionException {
        String choice= "";
        int numTrainersBeaten = 0;
        int startingHighScore = User.recordColosseumTrainersBeaten;
        do{
            Sound.playMusicOnLoop("src/main/music/indigoPlateauTheme.mp3");
            Graphics.printInsideOfTheColosseum(numTrainersBeaten);
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("F")){
                Trainer thisTrainerToBattle = Trainer.buildColosseumTrainer();
                if(Encounter.enterTrainerBattle(thisTrainerToBattle, sc1)) {
                    numTrainersBeaten++;
                    Bag.earnBP(numTrainersBeaten);
                    if (numTrainersBeaten > User.recordColosseumTrainersBeaten) {
                        User.recordColosseumTrainersBeaten = numTrainersBeaten;
                    }
                    if (User.recordColosseumTrainersBeaten >= 25) {
                        letUserProcureAPkmOfTheirChoice(thisTrainerToBattle.party, sc1);
                    }
                    healPartyWithDialogueAndSound();
                }
            }
            if(choice.equals("S")){
                seeNurseJoyToHealPokemon(sc1);
            }
            if(choice.equals("H")){
                goToHelpDeskInColosseum(sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
            if (choice.equals("L")) {
                String choiceConfirmLeave = "";
                while(true) {
                    System.out.println("Are you sure you want to leave? This will end your current run. (Y/N)");
                    choiceConfirmLeave = sc1.nextLine().trim().toUpperCase();
                    if (choiceConfirmLeave.equals("N")) {
                        choice = "";
                        Sound.click();
                        break;
                    } else if (choiceConfirmLeave.equals("Y")) {
                        Sound.click();
                        break;
                    }
                }
            }
        } while (!Party.checkIfEveryPkmHasFainted() && !choice.equals("L"));
        if (numTrainersBeaten > startingHighScore) {
            System.out.println("New high score! You beat: " + numTrainersBeaten + " trainers!");
            Game.pressEnterToContinue(sc1);
        }
        rushToNearestPokemonCenterIfFainted();
    }
    private static void letUserProcureAPkmOfTheirChoice(Pokemon[] party, Scanner sc1) throws InterruptedException {
    }
    public static void goToHelpDeskInColosseum(Scanner sc1) throws InterruptedException {
        String choice = "";
        do {
            Graphics.printHelpDeskInTheColosseum();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("T")){
                NPC.talkTo(NPC.Character.RICHIE, sc1);
            }
            if(choice.equals("G")){
                Bag.addNote("COLOSSEUM TIPS", sc1);
            }
            if(choice.equals("R")){
                redeemPrizeInColosseum(sc1);
            }
        } while(!Party.checkIfEveryPkmHasFainted() && !choice.equals("L"));
    }
    public static void redeemPrizeInColosseum(Scanner sc1) throws InterruptedException {
        String choice = "";
        do {
            System.out.println("        PRIZES         |  COST IN BP");
            System.out.println("=========================================");
            System.out.println(" [1] Rare Candy x10    |    3 BP");
            System.out.println(" [2] Pokeball x10      |    5 BP");
            System.out.println(" [3] Pokedollars x5000 |   20 BP");
            System.out.println(" [4] Mystery Egg       |   10 BP");
            System.out.println(" [C] Cancel");
            System.out.println("-----------------------------------------");
            System.out.println(" Make a selection: ");

            choice = sc1.nextLine().toUpperCase().trim();
            switch (choice) {
                case "1":
                    Bag.addSpecialItem("Rare Candy", 10);
                    Bag.spendBP(1);
                    break;
                case "2":
                    Bag.addItem("Pokeball", 10);
                    Bag.spendBP(2);
                    break;
                case "3":
                    Bag.adjustPokedollarBalance(5000);
                    Bag.spendBP(3);
                    break;
                case "4":
                    Bag.addSpecialItem("Mystery Egg", 1);
                    Bag.spendBP(10);
                    break;
                case "C":
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    Game.pressEnterToContinue(sc1);
            }
        } while (!choice.equals("C"));
    }
    //Vaughan District
    public static void goToVaughanDistrict(Scanner sc1) throws InterruptedException, ExecutionException{
        Sound.stopAllSounds();
        String choice= " ";
        do{
            Sound.playMusicOnLoop("src/main/music/vaughanDistrictTheme.mp3");
            Graphics.printVaughanDistrict();
            choice = sc1.nextLine().trim().toUpperCase();
            switch (choice) {
                case "O" -> openOptionsMenu(sc1);
                case "P" -> enterPokemonCenter(sc1);
                case "M" -> enterPokemart(sc1);
                case "G" -> goToVaughanGym(sc1);
                case "B" -> enterVaughanBank(sc1);
                case "C" -> Brokerage.enterBrokerage(sc1);
            }
        } while (!choice.equals("L"));
        Sound.stopAllSounds();
    }
    public static void enterVaughanBank(Scanner sc1) throws InterruptedException {
        String choice = " ";
        while (!choice.trim().equalsIgnoreCase("L")) {
            int numBadges = User.checkNumBadges();
            long goldPrice = 1000 + (numBadges * 200L);
            Graphics.printBank();
            System.out.println("Welcome to Vaughan Bank! What would you like to do?");
            System.out.println("===================================================");
            System.out.println("[1] Take out a loan");
            System.out.println("[2] Pay off debt");
            System.out.println("[3] Buy Gold Bars (" + goldPrice + " Pokedollars each)");
            System.out.println("[4] Sell Gold Bars (" + (goldPrice - 50) + " Pokedollars each)");
            System.out.println("[5] Check Balance");
            System.out.println("[6] Declare Bankruptcy");
            System.out.println("[L] Leave");
            System.out.println("===================================================");
            System.out.println("You have " + Bag.getPokedollars() + " Pokedollars.");
            System.out.println("---------------------------------------------------");
            System.out.println("Make a selection:");

            choice = sc1.nextLine().trim().toUpperCase();

            switch (choice) {
                case "1":
                    System.out.print("Enter loan amount or [0] to cancel: ");
                    long loan = getValidLong(sc1);
                    if (loan > 0 && loan < 50000) {
                        if (Bag.getDebt() > 10000) {
                            System.out.println("You have too much debt! Pay back some of it before taking out another loan.");
                        } else {
                            Bag.addDebt(loan);
                            Bag.adjustPokedollarBalance(loan);
                            System.out.println("You took out a loan of " + loan + " Pokedollars.");
                        }
                    }
                    else if (loan == 0) {
                        break;
                    }
                    else {
                        System.out.println("Invalid loan amount.");
                    }
                    Game.pressEnterToContinue(sc1);
                    break;
                case "2":
                    System.out.print("Enter debt to pay off or [0] to cancel (you have " + Bag.getDebt() + " Pokedollars of debt): ");
                    long debtToPayOff = getValidLong(sc1);
                    if (debtToPayOff > 0 && debtToPayOff <= Bag.getPokedollars() && Bag.getDebt() > 0) {
                        Bag.addDebt(-debtToPayOff);
                        Bag.spendPokedollarsSilent(debtToPayOff);
                        System.out.println("You paid off " + debtToPayOff + " Pokedollars of debt! Thanks for being a good citizen!");
                    } else if (debtToPayOff == 0) {
                            break;
                    } else {
                        System.out.println("Invalid amount.");
                    }
                    Game.pressEnterToContinue(sc1);
                    break;
                case "3":
                    int buyAmount = getValidIntWithPrompt(sc1, "Enter number of gold bars to buy or [0] to cancel: ");
                    long totalBuyCost = buyAmount * goldPrice;
                    if (buyAmount > 0 && Bag.getPokedollars() >= totalBuyCost) {
                        Bag.spendPokedollars((int) totalBuyCost);
                        Bag.addGoldBars(buyAmount);
                        System.out.println("You bought " + buyAmount + " gold bars. We'll keep them safe for you!");
                    } else if (buyAmount == 0) {
                        break;
                    } else {
                        System.out.println("Not enough funds or invalid amount.");
                    }
                    Game.pressEnterToContinue(sc1);
                    break;
                case "4":
                    int sellAmount = getValidIntWithPrompt(sc1, "Enter number of gold bars to sell or [0] to cancel (you have " + Bag.getNumGoldBars() + "): ");
                    long totalSellValue = sellAmount * (goldPrice - 50);
                    if (sellAmount > 0 && Bag.getNumGoldBars() >= sellAmount) {
                        Bag.addGoldBars(-sellAmount);
                        Bag.adjustPokedollarBalance((int) totalSellValue);
                        System.out.println("You sold " + sellAmount + " gold bars.");
                        Thread.sleep((long)(1.5 * User.textSpeed));
                    } else if (sellAmount == 0) {
                        break;
                    } else {
                        System.out.println("Not enough gold bars or invalid amount.");
                    }
                    Game.pressEnterToContinue(sc1);
                    break;
                case "5":
                    String choiceBack;
                    do {
                        System.out.println("Pokedollars: " + Bag.getPokedollars());
                        System.out.println("Debt: " + Bag.getDebt());
                        System.out.println("Gold Bars: " + Bag.getNumGoldBars());
                        System.out.println("[B] Back");
                        choiceBack = sc1.nextLine().trim().toUpperCase();
                    } while (!choiceBack.equals("B"));
                    break;
                case "6":
                    if(Bag.getPokedollars() < 100) {
                        System.out.println("You declared bankruptcy!");
                        Game.pressEnterToContinue();
                        System.out.println("Here's a 500 Pokedollar loan to get you back on your feet.");
                        Bag.adjustPokedollarBalance(500);
                        Bag.addDebt(+500);
                        Game.pressEnterToContinue();
                        System.out.println("Please try to be more financially responsible!");
                        Game.pressEnterToContinue();
                    }
                    else {
                        System.out.println("Wait, you have " + Bag.getPokedollars() + " Pokedollars. You're doing just fine.");
                        Game.pressEnterToContinue();
                    }
                    break;
                case "L":
                    System.out.println("Come again soon!");
                    Thread.sleep(User.textSpeed);
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    public static void goToVaughanGym(Scanner sc1) throws InterruptedException, ExecutionException {
        String choice= "";
        do{
            Sound.playMusicOnLoop("src/main/music/vaughanDistrictTheme.mp3");
            Graphics.printVaughanGymExterior();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("E")){
                enterGym(Trainer.Title.VAUGHAN_DISTRICT_GYM_LEADER, sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
    }
    //City Center
    public static void goToCityCenter(Scanner sc1) throws InterruptedException, ExecutionException {
        Sound.stopAllSounds();
        String choice= " ";
        do{
            Sound.playMusicOnLoop("src/main/music/celadonTheme.mp3");
            Graphics.printCityCenter();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("P")){
                enterPokemonCenter(sc1);
            }
            if(choice.equals("M")) {
                enterPokemart(sc1);
            }
            if(choice.equals("G")) {
                goToCeladonGym(sc1);
            }
            if(choice.equals("D")) {
                goToCeladonDepartmentStore(sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
        Sound.stopAllSounds();
    }
    public static void goToCeladonGym(Scanner sc1) throws InterruptedException, ExecutionException {
        String choice= " ";
        do{
            Sound.playMusicOnLoop("src/main/music/celadonTheme.mp3");
            Graphics.printCeladonGymExterior();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("E")){
                enterGym(Trainer.Title.CELADON_GYM_LEADER, sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
    }
    public static void goToCeladonDepartmentStore(Scanner sc1) throws InterruptedException {
        String choice= "";
        do{
            Sound.playMusicOnLoop("src/main/music/celadonTheme.mp3");
            Graphics.printCeladonDepartmentStore();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("E")){
                enterCeladonDepartmentStore(sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
    }
    private static void enterCeladonDepartmentStore(Scanner sc1) throws InterruptedException {
        Sound.stopAllSounds();
        String choice= "";
        do{
            Graphics.printCeladonDepartmentStoreInterior();
            Sound.playMusicOnLoop("src/main/music/boutiqueTheme.mp3");
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("B")){
                buyItemMenu(sc1);
            }
            if(choice.equals("S")){
                buySpecialItemMenu(sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
        Sound.stopAllSounds();
    }
    //route logic
    public static void goOnRouteWithInfinite149Enc(Scanner sc1) throws InterruptedException, ExecutionException{
        Sound.stopAllSounds();
        while (!Party.checkIfEveryPkmHasFainted()) {
            Sound.playMusicOnLoop("src/main/music/earlyRouteTheme.mp3");
            Graphics.printTallGrass();
            System.out.println("Walk into the tall grass?\n[W] Walk in | [V] View Party | [B] Open Bag | [T] Turn Back");
            String choiceGrass = sc1.nextLine().trim().toUpperCase();
            if (choiceGrass.equals("W")) {
                Encounter.enterWildPkmBattle(Encounter.genWildPkmUpTo149(),sc1);
            } else if (choiceGrass.equals("V")){
                Party.enterPartyMenu(sc1);
            } else if (choiceGrass.equals("B")){
                Bag.openBagMenu(sc1);
            } else if (choiceGrass.equals("T")) {
                System.out.println("You decided not to enter the tall grass.");
                break;
            } else {
                System.out.println("Invalid input. Please type W or T.");
            }
        }
        Sound.stopAllSounds();
    }
    public static void goOnRoute(int routeNum, Scanner sc1) throws InterruptedException, ExecutionException {
        Sound.stopAllSounds();
        label:
        while (!Party.checkIfEveryPkmHasFainted()) {
            if(routeNum < 4) {
                Sound.playMusicOnLoop("src/main/music/earlyRouteTheme.mp3");
            }
            else if(routeNum < 11) {
                Sound.playMusicOnLoop("src/main/music/route4Theme.mp3");
            }
            else if(routeNum < 24) {
                Sound.playMusicOnLoop("src/main/music/route11Theme.mp3");
            }
            else if(routeNum == 24 || routeNum == 25) {
                Sound.playMusicOnLoop("src/main/music/route24Theme.mp3");
            }

            Graphics.printRoute(routeNum);

            String routeCompletionStatus = "";
            String trainerPrompt = "";
            if(User.trainersBeatenOnARoute.get(routeNum) == 0) {
                routeCompletionStatus = "Battle more trainers to progress along the route!";
                trainerPrompt = "Battle first trainer";
            }
            else if(User.trainersBeatenOnARoute.get(routeNum) < 3) {
                routeCompletionStatus = "Battle more trainers to progress along the route!";
                trainerPrompt = "Battle next trainer";
            }
            else {
                routeCompletionStatus = "You've conquered this route!";
                trainerPrompt = "Battle a trainer";
                //unlockAreaOrRoute
            }
            System.out.println("=========================================================================================");
            System.out.println(" ROUTE " + routeNum + " | " + routeCompletionStatus + " | Trainers beaten: " + User.trainersBeatenOnARoute.get(routeNum));
            System.out.println("=========================================================================================");
            System.out.println("[W] Go into tall grass | [F] " + trainerPrompt + " | [I] Info | | [O] Options | [L] Leave");
            System.out.println("-----------------------------------------------------------------------------------------");

            String choiceGrass = sc1.nextLine().trim().toUpperCase();
            switch (choiceGrass) {
                case "W":
                    Graphics.printTallGrass();
                    Game.pressEnterToContinue(sc1);
                    Encounter.enterWildPkmBattle(Encounter.getPkmInArea("Route " + routeNum), sc1);
                    break;
                case "F":
                    boolean playerBeatATrainer = Encounter.enterTrainerBattle(Encounter.getTrainerInArea("Route " + routeNum), sc1);
                    if (playerBeatATrainer) {
                        User.trainersBeatenOnARoute.put(routeNum, User.trainersBeatenOnARoute.get(routeNum) + 1);
                    }
                    break;
                case "I":
                    checkRouteInfo(routeNum, sc1);
                    break;
                case "O":
                    openOptionsMenu(sc1);
                    break;
                case "L":
                    break label;
                default:
                    System.out.println("Invalid input. Please type W or T.");
                    break;
            }
        }
        Sound.stopAllSounds();
    }
    private static void checkRouteInfo(int routeNum, Scanner sc1) {
        Map<String, Integer> routeInfoMap = Encounter.getPkmsInArea("Route " + routeNum);
        double totalEncounterPoints = routeInfoMap.values().stream().mapToInt(Integer::intValue).sum();

        // finds the longest name for alignment
        int maxNameLength = routeInfoMap.keySet().stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);

        final int PERCENTAGE_COLUMN_WIDTH = 8; // " 100.00%".length()
        if(User.trainersBeatenOnARoute.get(routeNum) >= 3) {
            System.out.println("   Encounter Rates");
            String divider = "=".repeat(6 + maxNameLength + PERCENTAGE_COLUMN_WIDTH);
            System.out.println(divider);
            routeInfoMap.entrySet().stream()
                    .sorted((a, b) -> Double.compare(
                            (100 * b.getValue() / totalEncounterPoints),
                            (100 * a.getValue() / totalEncounterPoints)
                    ))
                    .forEach(entry -> {
                        String pkmName = entry.getKey();
                        double percentage = (100 * entry.getValue()) / totalEncounterPoints;

                        System.out.printf("| %-" + maxNameLength + "s | %6.2f%% |%n",
                                pkmName,
                                percentage);
                    });
            System.out.println(divider);
        } else {
            System.out.println("     Encounter Rates");
            System.out.println("==========================");
            System.out.println("| Complete this route to |");
            System.out.println("|  see encounter rates!  |");
            System.out.println("==========================");
        }

        Game.pressEnterToContinue(sc1);
    }
    //general POI logic
    public static void enterPokemonCenter(Scanner sc1) throws InterruptedException{
        String choice;
        Sound.stopAllSounds();
        Sound.playMusicOnLoop("src/main/music/pokemonCenterTheme.mp3");
        do{
            Graphics.printPokemonCenterInterior();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("B")){
                Party.enterPartyMenu(sc1);
            }
            if(choice.equals("H")){
                seeNurseJoyToHealPokemon(sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while (!choice.equals("L"));
        Sound.stopAllSounds();
    }
    public static void seeNurseJoyToHealPokemon(Scanner sc1) throws InterruptedException {
        String choice;
        do{
            Graphics.printNurseJoy();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("Y")){
                Party.healParty();
                Sound.playSoundOnce("src/main/music/heal.mp3");
                System.out.println("Healing...");
                Thread.sleep(User.textSpeed);
                System.out.println("\nYour party has been restored back to full health!\n");
                Game.pressEnterToContinue(sc1);
                break;
            }
            if(choice.equals("N")){
                break;
            }
        } while (true);
    }
    public static void enterPokemart(Scanner sc1) throws InterruptedException {
        while (true) {
            Graphics.printPokemart();
            String choice = sc1.nextLine().toUpperCase().trim();
            switch (choice) {
                case "L" -> {
                    return;
                }
                case "O" -> openOptionsMenu(sc1);
                case "B" -> buyItemMenu(sc1);
                default -> {
                    System.out.println("Invalid option. Please select again.");
                    Game.pressEnterToContinue(sc1);
                }
            }
        }
    }
    private static void buyItemMenu(Scanner sc1) throws InterruptedException {
        Graphics.printBuyItemsDesk();
        System.out.println("     What items interest you?");
        System.out.println("=========================================");
        System.out.println("[1] Pokeball - 100 Pokedollars");
        System.out.println("[2] Potion - 200 Pokedollars");
        System.out.println("[3] Super Potion - 600 Pokedollars");
        System.out.println("[4] Hyper Potion - 1200 Pokedollars");
        System.out.println("[5] Revive - 3000 Pokedollars");
        System.out.println("[6] Full Heal - 500 Pokedollars");
        System.out.println("[C] Cancel");
        System.out.println("=========================================");
        System.out.println("You have: " + Bag.getPokedollars() + " Pokedollars");
        System.out.println("-----------------------------------------");
        System.out.println("Make a selection: ");

        String itemChoice = sc1.nextLine().toUpperCase().trim();
        switch (itemChoice) {
            case "1":
                buyItem("Pokeball", 100, sc1);
                break;
            case "2":
                buyItem("Potion", 200, sc1);
                break;
            case "3":
                buyItem("Super Potion", 600, sc1);
                break;
            case "4":
                buyItem("Hyper Potion", 1200, sc1);
                break;
            case "5":
                buyItem("Revive", 3000, sc1);
                break;
            case "6":
                buyItem("Full Heal", 500, sc1);
                break;
            case "C":
                System.out.println("Oh, you changed your mind?");
                Game.pressEnterToContinue(sc1);
                break;
            default:
                System.out.println("Invalid selection. Please try again.");
                Game.pressEnterToContinue(sc1);
        }
    }
    private static void buyItem(String item, int price, Scanner sc1) throws InterruptedException {
        System.out.println("How many " + item + "s would you like to buy?");
        System.out.print("Enter the amount or [0] to cancel: ");

        try {
            int amount = Integer.parseInt(sc1.nextLine().trim());
            if (amount > 0) {
                int totalCost = price * amount;
                if (Bag.getPokedollars() >= totalCost) {  // Check AFTER getting quantity
                    Bag.spendPokedollars(totalCost);
                    Bag.addItem(item, amount);
                    System.out.println("You bought " + amount + " " + item + "(s).");
                } else {
                    System.out.println("You can't afford " + amount + " " + item + "(s).");
                }
                Game.pressEnterToContinue(sc1);
            }
            else if (amount == 0) {
                System.out.println("Oh, you changed your mind?");
                Game.pressEnterToContinue(sc1);
            }
            else {
                System.out.println("Invalid amount. Please enter a positive number.");
                Game.pressEnterToContinue(sc1);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            Game.pressEnterToContinue(sc1);
        }
    }
    private static void buySpecialItemMenu(Scanner sc1) throws InterruptedException {
        Graphics.printBuyItemsDesk();
        System.out.println("             Special Items");
        System.out.println("=========================================");
        System.out.println("[1] Rare Candy - 100 Pokedollars");
        System.out.println("[2] Fire Stone - 5000 Pokedollars");
        System.out.println("[3] Thunder Stone - 5000 Pokedollars");
        System.out.println("[4] Water Stone - 5000 Pokedollars");
        System.out.println("[C] Cancel");
        System.out.println("=========================================");
        System.out.println("You have: " + Bag.getPokedollars() + " Pokedollars");
        System.out.println("-----------------------------------------");
        System.out.println("Make a selection: ");

        String itemChoice = sc1.nextLine().toUpperCase().trim();
        switch (itemChoice) {
            case "1":
                buySpecialItem("Rare Candy", 100, sc1);
                break;
            case "2":
                buySpecialItem("Fire Stone", 5000, sc1);
                break;
            case "3":
                buySpecialItem("Thunder Stone", 5000, sc1);
                break;
            case "4":
                buySpecialItem("Water Stone", 5000, sc1);
                break;
            case "C":
                System.out.println("Oh, you changed your mind?");
                Game.pressEnterToContinue();
                break;
            default:
                System.out.println("Invalid selection. The vendor glares at you.");
                Game.pressEnterToContinue(sc1);
        }
    }
    private static void buySpecialItem(String item, int price, Scanner sc1) throws InterruptedException {
        System.out.println("How many " + item + "s would you like to buy?");
        System.out.print("Enter the amount or [0] to cancel: ");

        try {
            int amount = Integer.parseInt(sc1.nextLine().trim());
            if (amount > 0) {
                int totalCost = price * amount;
                if (totalCost > 50000) { // Bulk purchase warning
                    System.out.println("\nThat's a lot... are you sure? (Y/N)");
                    if (!sc1.nextLine().trim().equalsIgnoreCase("Y")) {
                        System.out.println("Transaction cancelled.");
                        Game.pressEnterToContinue(sc1);
                        return;
                    }
                }

                if (Bag.getPokedollars() >= totalCost) {  // Check AFTER getting quantity
                    Bag.spendPokedollars(totalCost);
                    Bag.addSpecialItem(item, amount);
                    System.out.println("You received " + amount + " " + item + "(s).");
                } else {
                    System.out.println("You don't have enough Pokedollars for that!");
                }
                Game.pressEnterToContinue(sc1);
            }
            else if (amount == 0) {
                System.out.println("Changed your mind?");
                Game.pressEnterToContinue(sc1);
            }
            else {
                System.out.println("Positive quantities only!");
                Game.pressEnterToContinue(sc1);
            }
        } catch (NumberFormatException e) {
            System.out.println("Integers only!");
            Game.pressEnterToContinue(sc1);
        }
    }
    public static void enterGym(Trainer.Title gymLeadearTitle, Scanner sc1) throws InterruptedException, ExecutionException {
        Sound.stopAllSounds();
        String choice = "";
        do {
            Graphics.printGymInterior(gymLeadearTitle);
            Sound.playMusicOnLoop("src/main/music/gymTheme.mp3");
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("F")){
                Trainer gymLeader = buildGymLeader(gymLeadearTitle);
                Encounter.enterTrainerBattle(gymLeader, sc1);
            }
            if(choice.equals("T")){
                talkToGymLeader(gymLeadearTitle, sc1);
            }
            if(choice.equals("H")){
                goToHelpDesk(sc1);
            }
            if(choice.equals("O")){
                openOptionsMenu(sc1);
            }
        } while(!Party.checkIfEveryPkmHasFainted() && !choice.equals("L"));
        rushToNearestPokemonCenterIfFainted();
        Sound.stopAllSounds();
    } //WIP
    public static Trainer buildGymLeader(Trainer.Title gymLeaderTitle) {
        Species.Type typeSpeciality = Species.Type.NONE;
        switch (gymLeaderTitle) {
            case PEWTER_GYM_LEADER -> typeSpeciality = Species.Type.ROCK;
            case CERULEAN_GYM_LEADER -> typeSpeciality = Species.Type.WATER;
            case CELADON_GYM_LEADER -> typeSpeciality = Species.Type.GRASS;
            case CINNABAR_GYM_LEADER -> typeSpeciality = Species.Type.FIRE;
            case FUCHSIA_GYM_LEADER -> typeSpeciality = Species.Type.POISON;
            case SAFFRON_GYM_LEADER -> typeSpeciality = Species.Type.PSYCHIC;
        }
        if (typeSpeciality == Species.Type.NONE) return new Trainer(gymLeaderTitle);
        else {
            return  new Trainer(gymLeaderTitle, Trainer.getProcedurallyMadeParty(60, typeSpeciality));
        }
    }
    public static void talkToGymLeader(Trainer.Title gymLeaderTitle, Scanner sc1) throws InterruptedException {
        NPC.Character whomToTalkTo = null;
        switch (gymLeaderTitle) {
            case CERULEAN_GYM_LEADER -> whomToTalkTo = NPC.Character.MISTY;
            case PEWTER_GYM_LEADER -> whomToTalkTo = NPC.Character.BROCK;
            case SAFFRON_GYM_LEADER -> whomToTalkTo = NPC.Character.SABRINA;
            case VIRIDIAN_GYM_LEADER -> whomToTalkTo = NPC.Character.BLUE;
            default -> whomToTalkTo = NPC.Character.RICHIE;
        }
        if (whomToTalkTo == NPC.Character.RICHIE) {
            System.out.println(gymLeaderTitle.getName() + " is unavailable to talk right now, but Richie's here!");
            Thread.sleep(User.textSpeed);
        }
        NPC.talkTo(whomToTalkTo, sc1);
    }
    public static void goToHelpDesk(Scanner sc1) throws InterruptedException {
        String choice = "";
        do {
            Graphics.printHelpDesk();
            choice = sc1.nextLine().trim().toUpperCase();
            if(choice.equals("T")){
                NPC.talkTo(NPC.Character.RICHIE, sc1);
            }
            if(choice.equals("G")){
                Bag.addNote("GYM TIPS", sc1);
            }
        } while(!Party.checkIfEveryPkmHasFainted() && !choice.equals("L"));
    }
    //options logic
    public static void openOptionsMenu(Scanner sc1) throws InterruptedException {
        label:
        while (true) {
            System.out.println("                             Options");
            System.out.println("============================================================================");
            System.out.println("[V] Party | [B] Bag | [P] Pokedex | [S] Save | [M] More Options | [R] Return");
            System.out.println("----------------------------------------------------------------------------");
            String choicePlayMenu = sc1.nextLine().trim().toUpperCase();
            switch (choicePlayMenu) {
                case "V":
                    Party.enterPartyMenu(sc1);
                    break;
                case "B":
                    Bag.openBagMenu(sc1);
                    break;
                case "P":
                    Species.enterPokedexMenu(sc1);
                    break;
                case "S":
                    SaveSys.promptUserToSaveGame(sc1);
                    break;
                case "M":
                    openMoreOptionsMenu(sc1);
                    break;
                case "R":
                    break label;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }
    }
    public static void openMoreOptionsMenu(Scanner sc1) {
        label:
        while (true) {
            System.out.println("                                       More Options");
            System.out.println("=========================================================================================");
            System.out.println(" [T] Trainer Card | [S] Change Text Speed | [H] Hints | [D] Change Difficulty | [B] Back ");
            System.out.println("-----------------------------------------------------------------------------------------");
            String choicePlayMenu = sc1.nextLine().trim().toUpperCase();
            switch (choicePlayMenu) {
                case "T":
                    viewTrainerCard(sc1);
                    break;
                case "S":
                    User.getTextSpeed(sc1);
                    break;
                case "D":
                    User.askUserToSetDifficulty(sc1);
                    break;
                case "H":
                    User.askUserToSetHints(sc1);
                    break;
                case "B":
                    break label;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }
    }
    public static void viewTrainerCard(Scanner sc1) {
        label:
        while (true) {
            Graphics.printPlayer(true);
            System.out.println("==================================================================");
            System.out.println("|                    [V] View Badges | [B] Back                  |");
            System.out.println("------------------------------------------------------------------");
            String choicePlayMenu = sc1.nextLine().trim().toUpperCase();
            switch (choicePlayMenu) {
                case "V":
                    viewBadges(sc1);
                    break;
                case "B":
                    break label;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }
    }
    public static void viewBadges(Scanner sc1) {
        List<Trainer.Title> listOfBadges = new ArrayList<>();
        for(Map.Entry<Trainer.Title, Boolean> title : User.badgesEarned.entrySet()) {
            if (title.getValue()) {
                listOfBadges.add(title.getKey());
            }
        }
        while (true) {
            System.out.println();
            System.out.println(" Badges Collected (" + listOfBadges.size() + "/10)");
            System.out.println("==========================");
            if(listOfBadges.isEmpty()) System.out.println("   You have no badges!");
            int i = 0;
            for (Trainer.Title badge : listOfBadges) {
                i++;
                System.out.printf("| %2d. %-19s|%n", i, listOfBadges.get(i-1).getBadgeName());
            }
            String totalBadgeStr = (listOfBadges.size() > 1) ? "-" + listOfBadges.size() : "";
            System.out.println("================================");
            System.out.printf("| %-27s |%n", "[1" + totalBadgeStr + "] Inspect Badge");
            System.out.printf("| %-27s |%n", "[B] Back");
            System.out.println("================================");
            String choice = sc1.nextLine().trim().toUpperCase();
            if (choice.equals("B")) {
                break;
            }
            else {
                try {
                    int badgeNum = Integer.parseInt(choice);
                    Trainer.Title badge = listOfBadges.get(badgeNum - 1);
                    Graphics.printClearLines(3);
                    System.out.println("         " + badge.getBadgeName());
                    Graphics.printBadge(badge);
                    Game.pressEnterToContinue();
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a number or [B] to go back!");
                    Game.pressEnterToContinue();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("You don't have a badge associated with that number!");
                    Game.pressEnterToContinue();
                }
            }
        }
    }

    public static void openOptionsOrContinue(Scanner sc1) throws InterruptedException {
        String choice = "";
        do {
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("| [O] Options         |");
            System.out.println("| [Enter] to continue |");
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=");
            choice = sc1.nextLine().trim().toUpperCase();
            if (choice.equals("O")) {
                openOptionsMenu(sc1);
            }
            else if (!choice.isEmpty()) {
                System.out.println("Invalid input.\n");
                Thread.sleep(User.textSpeed);
            }
        } while (!choice.isEmpty());
        Sound.click();
    }
    //helpers
    public static int getValidInt(Scanner sc1) {
        while (true) {
            try {
                return Integer.parseInt(sc1.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a valid integer: ");
            }
        }
    }
    public static long getValidLong(Scanner sc1) {
        while (true) {
            try {
                return Long.parseLong(sc1.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a valid number: ");
            }
        }
    }
    public static int getValidIntWithPrompt(Scanner sc, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = sc.nextLine().trim();
                int value = Integer.parseInt(input);
                if (value >= 0) {
                    return value;
                } else {
                    System.out.println("Please enter a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }
    public static void healPartyWithDialogueAndSound() throws InterruptedException{
        Party.healParty();
        Sound.playSoundOnce("src/main/music/heal.mp3");
        System.out.println("Healing your party...");
        Thread.sleep(User.textSpeed);
        System.out.println("\nYour party has been restored back to full health!\n");
        Game.pressEnterToContinue();
    }
}
