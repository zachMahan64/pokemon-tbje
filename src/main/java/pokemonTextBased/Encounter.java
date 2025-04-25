package pokemonTextBased;//make para have a chance to not move

import java.util.*;
import java.util.concurrent.ExecutionException;

public class Encounter {
    //Generate Pokemon
    public static Pokemon genWildPkmUpTo149() throws InterruptedException {
        int currentEncNum = getEncNumUpToSomeDexNum(149); //change what method is used in future when encounters are customized per location
        Random rand = new Random();
        int level = Party.getAvgPLvl() + 5;
        Species wildSpecies = Species.getSpecies(Species.getNameFromDexNum(currentEncNum));
        Pokemon wildPokemon = new Pokemon(wildSpecies, level, false);
        return wildPokemon;
    }
    public static int getEncNumUpToSomeDexNum(int highestDexNum) {
        Random rand = new Random();
        return rand.nextInt(highestDexNum) + 1;
    }
    public static final Map<String, Map<String, Integer>> pokemonInAreas = new HashMap<>();
    static {
        Map<String, Integer> route1Pokemon = new HashMap<>();
        route1Pokemon.put("Pidgey", 30);  // Common
        route1Pokemon.put("Rattata", 30); // Common
        route1Pokemon.put("Caterpie", 30); // Uncommon
        route1Pokemon.put("Weedle", 30);   // Uncommon
        route1Pokemon.put("Pikachu", 50);   // Rare
        pokemonInAreas.put("Route 1", route1Pokemon);

        Map<String, Integer> route2Pokemon = new HashMap<>();
        route2Pokemon.put("Pidgey", 30);
        route2Pokemon.put("Oddish", 30);
        route2Pokemon.put("Rattata", 30);
        route2Pokemon.put("NidoranF", 30);
        route2Pokemon.put("Nidorina", 15);
        route2Pokemon.put("NidoranM", 30);
        route2Pokemon.put("Nidorino", 15);
        route2Pokemon.put("Jigglypuff", 20);
        route2Pokemon.put("Bulbasaur", 3);
        route2Pokemon.put("Pikachu", 50);
        pokemonInAreas.put("Route 2", route2Pokemon);

        Map<String, Integer> route3Pokemon = new HashMap<>();
        route3Pokemon.put("Pidgey", 30);  // Common
        route3Pokemon.put("Spearow", 30); // Common
        route3Pokemon.put("Mankey", 40);  // Uncommon
        route3Pokemon.put("Jigglypuff", 20);
        route3Pokemon.put("Eevee", 15);// Rare
        route3Pokemon.put("Abra", 20);     // Very Rare
        pokemonInAreas.put("Route 3", route3Pokemon);

        Map<String, Integer> route4Pokemon = new HashMap<>();
        route4Pokemon.put("Rattata", 15);  // Common
        route4Pokemon.put("Spearow", 20);  // Common
        route4Pokemon.put("Ekans", 21);
        route4Pokemon.put("Eevee", 23);
        route4Pokemon.put("Growlithe", 22);
        route4Pokemon.put("Sandshrew", 20); // Rare
        route4Pokemon.put("Clefairy", 25);
        pokemonInAreas.put("Route 4", route4Pokemon);

        Map<String, Integer> route5Pokemon = new HashMap<>();
        route5Pokemon.put("Pidgey", 30);  // Common
        route5Pokemon.put("Meowth", 25);  // Common
        route5Pokemon.put("Oddish", 22);  // Uncommon
        route5Pokemon.put("Vulpix", 21);
        route5Pokemon.put("Bellsprout", 20); // Uncommon
        route5Pokemon.put("Psyduck", 19);  // Rare
        pokemonInAreas.put("Route 5", route5Pokemon);

        Map<String, Integer> route6Pokemon = new HashMap<>();
        route6Pokemon.put("Pidgey", 35);
        route6Pokemon.put("Meowth", 30);
        route6Pokemon.put("Oddish", 20);
        route6Pokemon.put("Bellsprout", 20);
        route6Pokemon.put("Psyduck", 15);
        pokemonInAreas.put("Route 6", route6Pokemon);

        Map<String, Integer> route7Pokemon = new HashMap<>();
        route7Pokemon.put("Pidgey", 30);
        route7Pokemon.put("Meowth", 30);
        route7Pokemon.put("Oddish", 20);
        route7Pokemon.put("Bellsprout", 20);
        route7Pokemon.put("Vulpix", 20);
        route7Pokemon.put("Ninetales", 5);
        pokemonInAreas.put("Route 7", route7Pokemon);

        Map<String, Integer> route8Pokemon = new HashMap<>();
        route8Pokemon.put("Pidgey", 30);
        route8Pokemon.put("Pidgeotto", 10);
        route8Pokemon.put("Pidgeot", 3);
        route8Pokemon.put("Meowth", 25);
        route8Pokemon.put("Growlithe", 50);
        route8Pokemon.put("Vulpix", 20);
        route8Pokemon.put("Abra", 10);
        pokemonInAreas.put("Route 8", route8Pokemon);

        Map<String, Integer> route9Pokemon = new HashMap<>();
        route9Pokemon.put("Rattata", 10);
        route9Pokemon.put("Spearow", 10);
        route9Pokemon.put("Ekans", 20);
        route9Pokemon.put("Sandshrew", 20);
        route9Pokemon.put("Mankey", 20);
        route9Pokemon.put("Eevee", 5);
        pokemonInAreas.put("Route 9", route9Pokemon);

        Map<String, Integer> route10Pokemon = new HashMap<>();
        route10Pokemon.put("Voltorb", 40);
        route10Pokemon.put("Magnemite", 30);
        route10Pokemon.put("Spearow", 20);
        route10Pokemon.put("Sandshrew", 10);
        route10Pokemon.put("Electabuzz", 5);
        pokemonInAreas.put("Route 10", route10Pokemon);

        Map<String, Integer> route11Pokemon = new HashMap<>();
        route11Pokemon.put("Drowzee", 40);
        route11Pokemon.put("Raticate", 30);
        route11Pokemon.put("Spearow", 20);
        route11Pokemon.put("Sandshrew", 10);
        route11Pokemon.put("Ditto", 5);
        pokemonInAreas.put("Route 11", route11Pokemon);

        Map<String, Integer> route12Pokemon = new HashMap<>();
        route12Pokemon.put("Oddish", 30);
        route12Pokemon.put("Gloom", 30);
        route12Pokemon.put("Venonat", 20);
        route12Pokemon.put("Bellsprout", 20);
        route12Pokemon.put("Farfetch'd", 5);
        pokemonInAreas.put("Route 12", route12Pokemon);

        Map<String, Integer> route13Pokemon = new HashMap<>();
        route13Pokemon.put("Oddish", 30);
        route13Pokemon.put("Gloom", 30);
        route13Pokemon.put("Venonat", 20);
        route13Pokemon.put("Bellsprout", 20);
        route13Pokemon.put("Ditto", 5);
        pokemonInAreas.put("Route 13", route13Pokemon);

        Map<String, Integer> route14Pokemon = new HashMap<>();
        route14Pokemon.put("Oddish", 30);
        route14Pokemon.put("Gloom", 30);
        route14Pokemon.put("Venonat", 20);
        route14Pokemon.put("Bellsprout", 20);
        route14Pokemon.put("Ditto", 5);
        pokemonInAreas.put("Route 14", route14Pokemon);

        Map<String, Integer> route15Pokemon = new HashMap<>();
        route15Pokemon.put("Oddish", 30);
        route15Pokemon.put("Gloom", 30);
        route15Pokemon.put("Venonat", 20);
        route15Pokemon.put("Bellsprout", 20);
        route15Pokemon.put("Ditto", 5);
        pokemonInAreas.put("Route 15", route15Pokemon);

        Map<String, Integer> route16Pokemon = new HashMap<>();
        route16Pokemon.put("Rattata", 20);
        route16Pokemon.put("Raticate", 20);
        route16Pokemon.put("Spearow", 20);
        route16Pokemon.put("Doduo", 10);
        route16Pokemon.put("Snorlax", 10);
        route16Pokemon.put("Eevee", 10);
        pokemonInAreas.put("Route 16", route16Pokemon);

        Map<String, Integer> route17Pokemon = new HashMap<>();
        route17Pokemon.put("Raticate", 11);
        route17Pokemon.put("Eevee", 40);
        route17Pokemon.put("Spearow", 15);
        route17Pokemon.put("Fearow", 10);
        route17Pokemon.put("Doduo", 10);
        route17Pokemon.put("Dodrio", 10);
        route17Pokemon.put("Dratini", 3);
        route17Pokemon.put("Dragonair", 2);
        route17Pokemon.put("Dragonite", 1);
        route17Pokemon.put("Charmander", 3);
        route17Pokemon.put("Charmeleon", 2);
        route17Pokemon.put("Charizard", 1);
        route17Pokemon.put("Aerodactyl", 3);
        pokemonInAreas.put("Route 17", route17Pokemon);

        Map<String, Integer> route18Pokemon = new HashMap<>();
        route18Pokemon.put("Raticate", 10);
        route18Pokemon.put("Spearow", 30);
        route18Pokemon.put("Fearow", 30);
        route18Pokemon.put("Doduo", 10);
        route18Pokemon.put("Dodrio", 5);
        route18Pokemon.put("Eevee", 5);
        route18Pokemon.put("Growlithe", 15);
        route18Pokemon.put("Arcanine", 5);
        route18Pokemon.put("Scyther", 3);
        pokemonInAreas.put("Route 18", route18Pokemon);

        Map<String, Integer> route19Pokemon = new HashMap<>();
        route19Pokemon.put("Tentacool", 20);
        route19Pokemon.put("Tentacruel", 30);
        route19Pokemon.put("Lapras", 20);
        route19Pokemon.put("Seel", 30);
        route19Pokemon.put("Squirtle", 5);
        route19Pokemon.put("Magikarp", 40);
        route19Pokemon.put("Staryu", 25);
        route19Pokemon.put("Vaporeon", 5);
        pokemonInAreas.put("Route 19", route19Pokemon);

        Map<String, Integer> route20Pokemon = new HashMap<>();
        route20Pokemon.put("Tentacool", 40);
        route20Pokemon.put("Tentacruel", 30);
        route20Pokemon.put("Magikarp", 20);
        route20Pokemon.put("Seel", 20);
        route20Pokemon.put("Dewgong", 15);
        route20Pokemon.put("Staryu", 25);
        route20Pokemon.put("Horsea", 20);
        route20Pokemon.put("Seadra", 10);
        pokemonInAreas.put("Route 20", route20Pokemon);

        Map<String, Integer> route21Pokemon = new HashMap<>();
        route21Pokemon.put("Tentacool", 30);
        route21Pokemon.put("Tentacruel", 10);
        route21Pokemon.put("Horsea", 20);
        route21Pokemon.put("Seadra", 10);
        route21Pokemon.put("Lapras", 30);
        route21Pokemon.put("Magikarp", 20);
        route21Pokemon.put("Staryu", 15);
        pokemonInAreas.put("Route 21", route21Pokemon);

        Map<String, Integer> route22Pokemon = new HashMap<>();
        route22Pokemon.put("Rattata", 40);
        route22Pokemon.put("Spearow", 30);
        route22Pokemon.put("Mankey", 20);
        route22Pokemon.put("Poliwag", 30);
        route22Pokemon.put("Poliwhirl", 15);
        route22Pokemon.put("Poliwrath", 5);
        pokemonInAreas.put("Route 22", route22Pokemon);

        Map<String, Integer> route23Pokemon = new HashMap<>();
        route23Pokemon.put("Fearow", 20);
        route23Pokemon.put("Spearow", 20);
        route23Pokemon.put("Ekans", 20);
        route23Pokemon.put("Arbok", 10);
        route23Pokemon.put("Sandslash", 5);
        pokemonInAreas.put("Route 23", route23Pokemon);

        Map<String, Integer> route24Pokemon = new HashMap<>();
        route24Pokemon.put("Pikachu", 20);
        route24Pokemon.put("Bulbasaur", 5);
        route24Pokemon.put("Caterpie", 20);
        route24Pokemon.put("Metapod", 10);
        route24Pokemon.put("Butterfree", 10);
        pokemonInAreas.put("Route 24", route24Pokemon);

        Map<String, Integer> route25Pokemon = new HashMap<>();
        route25Pokemon.put("Pikachu", 20);
        route25Pokemon.put("Bulbasaur", 5);
        route25Pokemon.put("Squirtle", 20);
        route25Pokemon.put("Vulpix", 20);
        route25Pokemon.put("Ninetales", 10);
        route25Pokemon.put("Growlithe", 20);
        route25Pokemon.put("Arcanine", 10);
        pokemonInAreas.put("Route 25", route25Pokemon);

        Map<String, Integer> mtMoonPokemon = new HashMap<>();
        mtMoonPokemon.put("Zubat", 40);
        mtMoonPokemon.put("Geodude", 30);
        mtMoonPokemon.put("Paras", 20);
        mtMoonPokemon.put("Clefairy", 10);
        pokemonInAreas.put("Mt. Moon", mtMoonPokemon);

        Map<String, Integer> rockTunnelPokemon = new HashMap<>();
        rockTunnelPokemon.put("Zubat", 40);
        rockTunnelPokemon.put("Geodude", 30);
        rockTunnelPokemon.put("Machop", 20);
        rockTunnelPokemon.put("Onix", 10);
        pokemonInAreas.put("Rock Tunnel", rockTunnelPokemon);

        Map<String, Integer> pokemonTowerPokemon = new HashMap<>();
        pokemonTowerPokemon.put("Gastly", 50);
        pokemonTowerPokemon.put("Cubone", 30);
        pokemonTowerPokemon.put("Haunter", 20);
        pokemonInAreas.put("Pokemon Tower", pokemonTowerPokemon);

        Map<String, Integer> pokemonMansionPokemon = new HashMap<>();
        pokemonMansionPokemon.put("Rattata", 30);
        pokemonMansionPokemon.put("Raticate", 30);
        pokemonMansionPokemon.put("Grimer", 20);
        pokemonMansionPokemon.put("Muk", 10);
        pokemonMansionPokemon.put("Koffing", 10);
        pokemonInAreas.put("Pokemon Mansion", pokemonMansionPokemon);

        Map<String, Integer> safariZonePokemon = new HashMap<>();
        safariZonePokemon.put("NidoranF", 20);
        safariZonePokemon.put("NidoranM", 20);
        safariZonePokemon.put("Paras", 20);
        safariZonePokemon.put("Exeggcute", 15);
        safariZonePokemon.put("Rhyhorn", 15);
        safariZonePokemon.put("Chansey", 5);
        safariZonePokemon.put("Scyther", 5);
        safariZonePokemon.put("Pinsir", 5);
        safariZonePokemon.put("Tauros", 5);
        pokemonInAreas.put("Safari Zone", safariZonePokemon);

        Map<String, Integer> seafoamIslandsPokemon = new HashMap<>();
        seafoamIslandsPokemon.put("Zubat", 30);
        seafoamIslandsPokemon.put("Golbat", 20);
        seafoamIslandsPokemon.put("Psyduck", 20);
        seafoamIslandsPokemon.put("Golduck", 10);
        seafoamIslandsPokemon.put("Seel", 10);
        seafoamIslandsPokemon.put("Dewgong", 10);
        pokemonInAreas.put("Seafoam Islands", seafoamIslandsPokemon);

        Map<String, Integer> victoryRoadPokemon = new HashMap<>();
        victoryRoadPokemon.put("Zubat", 30);
        victoryRoadPokemon.put("Golbat", 20);
        victoryRoadPokemon.put("Machop", 20);
        victoryRoadPokemon.put("Geodude", 10);
        victoryRoadPokemon.put("Graveler", 10);
        victoryRoadPokemon.put("Onix", 10);
        pokemonInAreas.put("Victory Road", victoryRoadPokemon);

        Map<String, Integer> powerPlantPokemon = new HashMap<>();
        powerPlantPokemon.put("Voltorb", 40);
        powerPlantPokemon.put("Magnemite", 30);
        powerPlantPokemon.put("Pikachu", 20);
        powerPlantPokemon.put("Electabuzz", 10);
        pokemonInAreas.put("Power Plant", powerPlantPokemon);

        Map<String, Integer> ceruleanCavePokemon = new HashMap<>();
        ceruleanCavePokemon.put("Golbat", 30);
        ceruleanCavePokemon.put("Parasect", 20);
        ceruleanCavePokemon.put("Golduck", 20);
        ceruleanCavePokemon.put("Magneton", 10);
        ceruleanCavePokemon.put("Ditto", 10);
        ceruleanCavePokemon.put("Mewtwo", 1); // Very rare
        pokemonInAreas.put("Cerulean Cave", ceruleanCavePokemon);

        Map<String, Integer> diglettsCavePokemon = new HashMap<>();
        diglettsCavePokemon.put("Diglett", 90);
        diglettsCavePokemon.put("Dugtrio", 10);
        pokemonInAreas.put("Diglett's Cave", diglettsCavePokemon);

        Map<String, Integer> viridianForestPokemon = new HashMap<>();
        viridianForestPokemon.put("Caterpie", 40);
        viridianForestPokemon.put("Metapod", 20);
        viridianForestPokemon.put("Weedle", 30);
        viridianForestPokemon.put("Kakuna", 10);
        pokemonInAreas.put("Viridian Forest", viridianForestPokemon);

        Map<String, Integer> celadonGameCornerPokemon = new HashMap<>();
        celadonGameCornerPokemon.put("Abra", 50);
        celadonGameCornerPokemon.put("Clefairy", 30);
        celadonGameCornerPokemon.put("Dratini", 20);
        pokemonInAreas.put("Celadon Game Corner", celadonGameCornerPokemon);

        Map<String, Integer> silphCoPokemon = new HashMap<>();
        silphCoPokemon.put("Machop", 30);
        silphCoPokemon.put("Magnemite", 30);
        silphCoPokemon.put("Voltorb", 20);
        silphCoPokemon.put("Electrode", 10);
        silphCoPokemon.put("Koffing", 10);
        pokemonInAreas.put("Silph Co.", silphCoPokemon);

        Map<String, Integer> cinnabarLabPokemon = new HashMap<>();
        cinnabarLabPokemon.put("Omanyte", 50);
        cinnabarLabPokemon.put("Kabuto", 50);
        pokemonInAreas.put("Cinnabar Lab", cinnabarLabPokemon);
    }
    public static Pokemon getPkmInArea(String areaName) {
        Map<String, Integer> area = getPkmsInArea(areaName);
        if (area == null || area.isEmpty()) {
            System.out.println("ERROR: AREA IS EMPTY OR NOT CALLED CORRECTLY!");
            return new Pokemon(Species.getSpecies("Missingno"), 10, false);
        }

        Random rand = new Random();
        int totalWeight = area.values().stream().mapToInt(Integer::intValue).sum();
        int randomValue = rand.nextInt(totalWeight);

        // get spawn from weighted odds
        String selectedPokemon = null;
        int cumulativeWeight = 0;
        for (Map.Entry<String, Integer> entry : area.entrySet()) {
            cumulativeWeight += entry.getValue();
            if (randomValue < cumulativeWeight) {
                selectedPokemon = entry.getKey();
                break;
            }
        }

        int level = Math.min(Party.getAvgPLvl() - 3 + rand.nextInt(3), 100);
        assert selectedPokemon != null;
        Species wildSpecies = Species.getSpecies(selectedPokemon);
        return new Pokemon(wildSpecies, level, false);
    }
    public static Map<String, Integer> getPkmsInArea(String areaName) {
        return pokemonInAreas.getOrDefault(areaName, new HashMap<>());
    }
    //Generate trainers
    public static final Map<String, List<Trainer>> areaTrainers = new HashMap<>(); //make this hold titles rather than trainer objects
    static {
        List<Trainer> route1Trainers = new ArrayList<>();
        route1Trainers.add(new Trainer(Trainer.Title.YOUNGSTER));
        route1Trainers.add(new Trainer(Trainer.Title.LASS));
        route1Trainers.add(new Trainer(Trainer.Title.GRUNT_L));
        route1Trainers.add(new Trainer(Trainer.Title.GRUNT_L_F));
        areaTrainers.put("Route 1", route1Trainers);

        List<Trainer> route2Trainers = new ArrayList<>();
        route2Trainers.add(new Trainer(Trainer.Title.BUG_CATCHER));
        route2Trainers.add(new Trainer(Trainer.Title.YOUNGSTER));
        route2Trainers.add(new Trainer(Trainer.Title.GRUNT_L));
        route2Trainers.add(new Trainer(Trainer.Title.GRUNT_L_F));
        areaTrainers.put("Route 2", route2Trainers);

        List<Trainer> route3Trainers = new ArrayList<>();
        route3Trainers.add(new Trainer(Trainer.Title.BUG_CATCHER));
        route3Trainers.add(new Trainer(Trainer.Title.YOUNGSTER));
        route3Trainers.add(new Trainer(Trainer.Title.LASS));
        route3Trainers.add(new Trainer(Trainer.Title.GRUNT_L));
        route3Trainers.add(new Trainer(Trainer.Title.GRUNT_L_F));
        areaTrainers.put("Route 3", route3Trainers);

        List<Trainer> route4Trainers = new ArrayList<>();
        route4Trainers.add(new Trainer(Trainer.Title.HIKER));
        route4Trainers.add(new Trainer(Trainer.Title.BUG_CATCHER));
        route4Trainers.add(new Trainer(Trainer.Title.GRUNT_M));
        route4Trainers.add(new Trainer(Trainer.Title.GRUNT_M_F));
        route4Trainers.add(new Trainer(Trainer.Title.GRUNT_L));
        route4Trainers.add(new Trainer(Trainer.Title.GRUNT_L_F));
        areaTrainers.put("Route 4", route4Trainers);

        List<Trainer> route5Trainers = new ArrayList<>();
        route5Trainers.add(new Trainer(Trainer.Title.LASS));
        route5Trainers.add(new Trainer(Trainer.Title.BEAUTY));
        route5Trainers.add(new Trainer(Trainer.Title.GRUNT_L));
        route5Trainers.add(new Trainer(Trainer.Title.GRUNT_L_F));
        areaTrainers.put("Route 5", route5Trainers);

        List<Trainer> route6Trainers = new ArrayList<>();
        route6Trainers.add(new Trainer(Trainer.Title.BUG_CATCHER));
        route6Trainers.add(new Trainer(Trainer.Title.LASS));
        route6Trainers.add(new Trainer(Trainer.Title.GRUNT_L));
        route6Trainers.add(new Trainer(Trainer.Title.GRUNT_L_F));
        areaTrainers.put("Route 6", route6Trainers);

        List<Trainer> route7Trainers = new ArrayList<>();
        route7Trainers.add(new Trainer(Trainer.Title.BIKER));
        route7Trainers.add(new Trainer(Trainer.Title.COOLTRAINER));
        route7Trainers.add(new Trainer(Trainer.Title.GRUNT_L));
        route7Trainers.add(new Trainer(Trainer.Title.GRUNT_L_F));
        areaTrainers.put("Route 7", route7Trainers);

        List<Trainer> route8Trainers = new ArrayList<>();
        route8Trainers.add(new Trainer(Trainer.Title.SUPER_NERD));
        route8Trainers.add(new Trainer(Trainer.Title.GAMBLER));
        route8Trainers.add(new Trainer(Trainer.Title.GRUNT_M));
        route8Trainers.add(new Trainer(Trainer.Title.GRUNT_M_F));
        route8Trainers.add(new Trainer(Trainer.Title.GRUNT_L));
        route8Trainers.add(new Trainer(Trainer.Title.GRUNT_L_F));
        areaTrainers.put("Route 8", route8Trainers);

        List<Trainer> route9Trainers = new ArrayList<>();
        route9Trainers.add(new Trainer(Trainer.Title.HIKER));
        route9Trainers.add(new Trainer(Trainer.Title.BIKER));
        route9Trainers.add(new Trainer(Trainer.Title.GRUNT_M));
        route9Trainers.add(new Trainer(Trainer.Title.GRUNT_M_F));
        route9Trainers.add(new Trainer(Trainer.Title.GRUNT_L));
        route9Trainers.add(new Trainer(Trainer.Title.GRUNT_L_F));
        areaTrainers.put("Route 9", route9Trainers);

        List<Trainer> route10Trainers = new ArrayList<>();
        route10Trainers.add(new Trainer(Trainer.Title.SCIENTIST));
        route10Trainers.add(new Trainer(Trainer.Title.ENGINEER));
        route10Trainers.add(new Trainer(Trainer.Title.GRUNT_M));
        route10Trainers.add(new Trainer(Trainer.Title.GRUNT_M_F));
        route10Trainers.add(new Trainer(Trainer.Title.GRUNT_L));
        route10Trainers.add(new Trainer(Trainer.Title.GRUNT_L_F));
        areaTrainers.put("Route 10", route10Trainers);

        List<Trainer> route11Trainers = new ArrayList<>();
        route11Trainers.add(new Trainer(Trainer.Title.GAMBLER));
        route11Trainers.add(new Trainer(Trainer.Title.YOUNGSTER));
        route11Trainers.add(new Trainer(Trainer.Title.GRUNT_M));
        route11Trainers.add(new Trainer(Trainer.Title.GRUNT_M_F));
        route11Trainers.add(new Trainer(Trainer.Title.GRUNT_L));
        route11Trainers.add(new Trainer(Trainer.Title.GRUNT_L_F));
        areaTrainers.put("Route 11", route11Trainers);

        List<Trainer> route12Trainers = new ArrayList<>();
        route12Trainers.add(new Trainer(Trainer.Title.FISHERMAN));
        route12Trainers.add(new Trainer(Trainer.Title.SAILOR));
        route12Trainers.add(new Trainer(Trainer.Title.GRUNT_M));
        route12Trainers.add(new Trainer(Trainer.Title.GRUNT_M_F));
        route12Trainers.add(new Trainer(Trainer.Title.GRUNT_L));
        route12Trainers.add(new Trainer(Trainer.Title.GRUNT_L_F));
        route12Trainers.add(new Trainer(Trainer.Title.GRUNT_H));
        areaTrainers.put("Route 12", route12Trainers);

        List<Trainer> route13Trainers = new ArrayList<>();
        route13Trainers.add(new Trainer(Trainer.Title.BIRD_KEEPER));
        route13Trainers.add(new Trainer(Trainer.Title.COOLTRAINER));
        route13Trainers.add(new Trainer(Trainer.Title.GRUNT_M));
        route13Trainers.add(new Trainer(Trainer.Title.GRUNT_M_F));
        route13Trainers.add(new Trainer(Trainer.Title.GRUNT_H));
        route13Trainers.add(new Trainer(Trainer.Title.GRUNT_H_F));
        areaTrainers.put("Route 13", route13Trainers);

        List<Trainer> route14Trainers = new ArrayList<>();
        route14Trainers.add(new Trainer(Trainer.Title.BIRD_KEEPER));
        route14Trainers.add(new Trainer(Trainer.Title.COOLTRAINER));
        route14Trainers.add(new Trainer(Trainer.Title.GRUNT_M));
        route14Trainers.add(new Trainer(Trainer.Title.GRUNT_M_F));
        route14Trainers.add(new Trainer(Trainer.Title.GRUNT_H));
        route14Trainers.add(new Trainer(Trainer.Title.GRUNT_H_F));
        areaTrainers.put("Route 14", route14Trainers);

        List<Trainer> route15Trainers = new ArrayList<>();
        route15Trainers.add(new Trainer(Trainer.Title.BIRD_KEEPER));
        route15Trainers.add(new Trainer(Trainer.Title.COOLTRAINER));
        route15Trainers.add(new Trainer(Trainer.Title.GRUNT_M));
        route15Trainers.add(new Trainer(Trainer.Title.GRUNT_M_F));
        route15Trainers.add(new Trainer(Trainer.Title.GRUNT_H));
        route15Trainers.add(new Trainer(Trainer.Title.GRUNT_H_F));
        areaTrainers.put("Route 15", route15Trainers);

        List<Trainer> route16Trainers = new ArrayList<>();
        route16Trainers.add(new Trainer(Trainer.Title.BIKER));
        route16Trainers.add(new Trainer(Trainer.Title.CUE_BALL));
        route16Trainers.add(new Trainer(Trainer.Title.GRUNT_L));
        route16Trainers.add(new Trainer(Trainer.Title.GRUNT_L_F));
        route16Trainers.add(new Trainer(Trainer.Title.GRUNT_M));
        route16Trainers.add(new Trainer(Trainer.Title.GRUNT_M_F));
        route16Trainers.add(new Trainer(Trainer.Title.GRUNT_H_F));
        areaTrainers.put("Route 16", route16Trainers);

        List<Trainer> route17Trainers = new ArrayList<>();
        route17Trainers.add(new Trainer(Trainer.Title.BIKER));
        route17Trainers.add(new Trainer(Trainer.Title.CUE_BALL));
        route17Trainers.add(new Trainer(Trainer.Title.GRUNT_L));
        route17Trainers.add(new Trainer(Trainer.Title.GRUNT_L_F));
        route17Trainers.add(new Trainer(Trainer.Title.GRUNT_M));
        route17Trainers.add(new Trainer(Trainer.Title.GRUNT_M_F));
        areaTrainers.put("Route 17", route17Trainers);

        List<Trainer> route18Trainers = new ArrayList<>();
        route18Trainers.add(new Trainer(Trainer.Title.BIRD_KEEPER));
        route18Trainers.add(new Trainer(Trainer.Title.COOLTRAINER));
        route18Trainers.add(new Trainer(Trainer.Title.GRUNT_L));
        route18Trainers.add(new Trainer(Trainer.Title.GRUNT_L_F));
        route18Trainers.add(new Trainer(Trainer.Title.GRUNT_M));
        route18Trainers.add(new Trainer(Trainer.Title.GRUNT_M_F));
        areaTrainers.put("Route 18", route18Trainers);

        List<Trainer> route19Trainers = new ArrayList<>();
        route19Trainers.add(new Trainer(Trainer.Title.SWIMMER));
        route19Trainers.add(new Trainer(Trainer.Title.SWIMMER_F));
        route19Trainers.add(new Trainer(Trainer.Title.FISHERMAN));
        route19Trainers.add(new Trainer(Trainer.Title.GRUNT_M));
        route19Trainers.add(new Trainer(Trainer.Title.GRUNT_M_F));
        route19Trainers.add(new Trainer(Trainer.Title.GRUNT_H));
        route19Trainers.add(new Trainer(Trainer.Title.GRUNT_H_F));
        areaTrainers.put("Route 19", route19Trainers);

        List<Trainer> route20Trainers = new ArrayList<>();
        route20Trainers.add(new Trainer(Trainer.Title.SWIMMER));
        route20Trainers.add(new Trainer(Trainer.Title.SWIMMER_F));
        route20Trainers.add(new Trainer(Trainer.Title.FISHERMAN));
        route20Trainers.add(new Trainer(Trainer.Title.GRUNT_M));
        route20Trainers.add(new Trainer(Trainer.Title.GRUNT_M_F));
        route20Trainers.add(new Trainer(Trainer.Title.GRUNT_H));
        route20Trainers.add(new Trainer(Trainer.Title.GRUNT_H_F));
        areaTrainers.put("Route 20", route20Trainers);

        List<Trainer> route21Trainers = new ArrayList<>();
        route21Trainers.add(new Trainer(Trainer.Title.SWIMMER));
        route21Trainers.add(new Trainer(Trainer.Title.SWIMMER_F));
        route21Trainers.add(new Trainer(Trainer.Title.FISHERMAN));
        route21Trainers.add(new Trainer(Trainer.Title.GRUNT_M));
        route21Trainers.add(new Trainer(Trainer.Title.GRUNT_M_F));
        route21Trainers.add(new Trainer(Trainer.Title.GRUNT_H));
        route21Trainers.add(new Trainer(Trainer.Title.GRUNT_H_F));
        areaTrainers.put("Route 21", route21Trainers);

        List<Trainer> route22Trainers = new ArrayList<>();
        route22Trainers.add(new Trainer(Trainer.Title.YOUNGSTER));
        route22Trainers.add(new Trainer(Trainer.Title.BLACKBELT));
        route22Trainers.add(new Trainer(Trainer.Title.GRUNT_M));
        route22Trainers.add(new Trainer(Trainer.Title.GRUNT_M_F));
        route22Trainers.add(new Trainer(Trainer.Title.GRUNT_H));
        route22Trainers.add(new Trainer(Trainer.Title.GRUNT_H_F));
        areaTrainers.put("Route 22", route22Trainers);

        List<Trainer> route23Trainers = new ArrayList<>();
        route23Trainers.add(new Trainer(Trainer.Title.BLACKBELT));
        route23Trainers.add(new Trainer(Trainer.Title.COOLTRAINER));
        route23Trainers.add(new Trainer(Trainer.Title.GRUNT_M));
        route23Trainers.add(new Trainer(Trainer.Title.GRUNT_M_F));
        route23Trainers.add(new Trainer(Trainer.Title.GRUNT_H));
        route23Trainers.add(new Trainer(Trainer.Title.GRUNT_H_F));
        areaTrainers.put("Route 23", route23Trainers);

        List<Trainer> route24Trainers = new ArrayList<>();
        route24Trainers.add(new Trainer(Trainer.Title.BUG_CATCHER));
        route24Trainers.add(new Trainer(Trainer.Title.LASS));
        route24Trainers.add(new Trainer(Trainer.Title.GRUNT_M));
        route24Trainers.add(new Trainer(Trainer.Title.GRUNT_M_F));
        route24Trainers.add(new Trainer(Trainer.Title.GRUNT_H));
        route24Trainers.add(new Trainer(Trainer.Title.GRUNT_H_F));
        areaTrainers.put("Route 24", route24Trainers);

        List<Trainer> route25Trainers = new ArrayList<>();
        route25Trainers.add(new Trainer(Trainer.Title.BUG_CATCHER));
        route25Trainers.add(new Trainer(Trainer.Title.LASS));
        route25Trainers.add(new Trainer(Trainer.Title.GRUNT_M));
        route25Trainers.add(new Trainer(Trainer.Title.GRUNT_M_F));
        route25Trainers.add(new Trainer(Trainer.Title.GRUNT_H));
        route25Trainers.add(new Trainer(Trainer.Title.GRUNT_H_F));
        areaTrainers.put("Route 25", route25Trainers);

        List<Trainer> mtMoonTrainers = new ArrayList<>();
        mtMoonTrainers.add(new Trainer(Trainer.Title.SUPER_NERD));
        mtMoonTrainers.add(new Trainer(Trainer.Title.BUG_CATCHER));
        areaTrainers.put(Location.Area.MT_MOON.getDisplayName(), mtMoonTrainers);

        List<Trainer> rockTunnelTrainers = new ArrayList<>();
        rockTunnelTrainers.add(new Trainer(Trainer.Title.HIKER));
        rockTunnelTrainers.add(new Trainer(Trainer.Title.POKEMANIAC));
        areaTrainers.put(Location.Area.ROCK_TUNNEL.getDisplayName(), rockTunnelTrainers);

        List<Trainer> pokemonTowerTrainers = new ArrayList<>();
        pokemonTowerTrainers.add(new Trainer(Trainer.Title.CHANNELER));
        pokemonTowerTrainers.add(new Trainer(Trainer.Title.PSYCHIC));
        areaTrainers.put(Location.Area.POKEMON_TOWER.getDisplayName(), pokemonTowerTrainers);

        List<Trainer> pokemonMansionTrainers = new ArrayList<>();
        pokemonMansionTrainers.add(new Trainer(Trainer.Title.SCIENTIST));
        pokemonMansionTrainers.add(new Trainer(Trainer.Title.BURGLAR));
        areaTrainers.put(Location.Area.POKEMON_MANSION.getDisplayName(), pokemonMansionTrainers);

        List<Trainer> safariZoneTrainers = new ArrayList<>();
        safariZoneTrainers.add(new Trainer(Trainer.Title.COOLTRAINER));
        safariZoneTrainers.add(new Trainer(Trainer.Title.BEAUTY));
        areaTrainers.put(Location.Area.SAFARI_ZONE.getDisplayName(), safariZoneTrainers);

        List<Trainer> seafoamIslandsTrainers = new ArrayList<>();
        seafoamIslandsTrainers.add(new Trainer(Trainer.Title.SWIMMER));
        seafoamIslandsTrainers.add(new Trainer(Trainer.Title.BIRD_KEEPER));
        areaTrainers.put(Location.Area.SAFARI_ZONE.getDisplayName(), seafoamIslandsTrainers);

        List<Trainer> victoryRoadTrainers = new ArrayList<>();
        victoryRoadTrainers.add(new Trainer(Trainer.Title.COOLTRAINER));
        victoryRoadTrainers.add(new Trainer(Trainer.Title.BLACKBELT));
        areaTrainers.put(Location.Area.VICTORY_ROAD.getDisplayName(), victoryRoadTrainers);

        List<Trainer> powerPlantTrainers = new ArrayList<>();
        powerPlantTrainers.add(new Trainer(Trainer.Title.ENGINEER));
        powerPlantTrainers.add(new Trainer(Trainer.Title.SCIENTIST));
        areaTrainers.put(Location.Area.POWER_PLANT.getDisplayName(), powerPlantTrainers);

        List<Trainer> ceruleanCaveTrainers = new ArrayList<>();
        ceruleanCaveTrainers.add(new Trainer(Trainer.Title.COOLTRAINER));
        ceruleanCaveTrainers.add(new Trainer(Trainer.Title.BLACKBELT));
        areaTrainers.put("Cerulean Cave", ceruleanCaveTrainers);

        List<Trainer> diglettsCaveTrainers = new ArrayList<>();
        diglettsCaveTrainers.add(new Trainer(Trainer.Title.HIKER));
        diglettsCaveTrainers.add(new Trainer(Trainer.Title.BLACKBELT));
        areaTrainers.put("Diglett's Cave", diglettsCaveTrainers);

        List<Trainer> viridianForestTrainers = new ArrayList<>();
        viridianForestTrainers.add(new Trainer(Trainer.Title.BUG_CATCHER));
        viridianForestTrainers.add(new Trainer(Trainer.Title.LASS));
        areaTrainers.put("Viridian Forest", viridianForestTrainers);

        List<Trainer> celadonGameCornerTrainers = new ArrayList<>();
        celadonGameCornerTrainers.add(new Trainer(Trainer.Title.GAMBLER));
        celadonGameCornerTrainers.add(new Trainer(Trainer.Title.COOLTRAINER));
        areaTrainers.put("Celadon Game Corner", celadonGameCornerTrainers);

        List<Trainer> silphCoTrainers = new ArrayList<>();
        silphCoTrainers.add(new Trainer(Trainer.Title.SCIENTIST));
        silphCoTrainers.add(new Trainer(Trainer.Title.BLACKBELT));
        areaTrainers.put("Silph Co.", silphCoTrainers);

        List<Trainer> cinnabarLabTrainers = new ArrayList<>();
        cinnabarLabTrainers.add(new Trainer(Trainer.Title.SCIENTIST));
        cinnabarLabTrainers.add(new Trainer(Trainer.Title.ENGINEER));
        areaTrainers.put("Cinnabar Lab", cinnabarLabTrainers);
    } //improve upon
    public static List<Trainer> getTrainersFromArea(String areaName) {
        return areaTrainers.getOrDefault(areaName, new ArrayList<>());
    }
    public static Trainer getTrainerInArea(String areaName) {
        List<Trainer> trainers = areaTrainers.get(areaName);
        if (trainers == null || trainers.isEmpty()) {
            System.out.println("ERROR: MISDIRECTED AREA OR NO TRAINERS IN THIS AREA");
            return new Trainer(Trainer.Title.SUPER_NERD);
        }
        Random random = new Random();
        int index = random.nextInt(trainers.size());
        return trainers.get(index);
    }

    // Wild Encounter Logic
    public static void enterWildPkmBattle(Pokemon wildPokemon, Scanner sc1) throws InterruptedException, ExecutionException{
        Sound.stopAllSounds();
        Pokemon[] fp = {wildPokemon};
        Engine engine = new Engine(new EnginePackage(EnginePackage.defaultEngineParameterMap), true);
        Arena arena = new Arena(Party.p, fp, engine, engine);
        Sound.playMusicOnLoop("src/main/music/wildBattleTheme.mp3");
        Graphics.printPokemon(wildPokemon.getName(), false, wildPokemon.isShiny());
        System.out.println("A wild " + arena.fp[0].getName() + " appeared!");
        if(arena.fp[0].isShiny()) Sound.playSoundOnce("src/main/music/shinySparkles.mp3");
        Thread.sleep(User.textSpeed);
        Graphics.printPokemon(arena.p[0].getName(), false, arena.p[0].isShiny());
        System.out.println("You sent out " + arena.p[0].getName() + "!");
        Sound.exitBall(arena.p[0]);
        Thread.sleep(User.textSpeed);
        arena.fp[0] = wildPokemon;
        playWildPkmBattle(arena, sc1);
        arena.fp[0] = null;
        Game.pressEnterToContinue(sc1);
        Sound.stopAllSounds();
    }
    public static void playWildPkmBattle(Arena arena, Scanner sc1) throws InterruptedException, ExecutionException{
        boolean wildPkmIsCaught = arena.isCaught;
        boolean playerHasRunAway = false;
        boolean playerSwitched = false;
        Move playerMove = null;
        Move foeMove = null;
        do {
            displayBattleStatus(arena, false);
            foeMove = Fight.chooseAMove(arena, arena.fp[0], arena.p[0]);
            if (arena.p[0].isSkipNextTurn()) {
                System.out.println(arena.p[0].getName() + " is waiting this turn.\n");
                Thread.sleep(User.textSpeed);
            }
            else {
                String choice = getPlayerChoice(arena, sc1);
                switch (choice) {
                    case "F":
                        Move engineBestMove = Fight.findBestMove(arena, arena.p[0], arena.fp[0]);
                        playerMove = Fight.askUserToChooseAMove(arena, arena.p[0], arena.fp[0], engineBestMove, sc1);
                        if (playerMove == null) continue; // meaning player canceled move selection
                        break;

                    case "B":
                        if (Bag.openBattlePocketWildEncounter(arena, sc1)) {
                            playerMove = null;
                            wildPkmIsCaught = (arena.isCaught);
                            break; //meaning player used an item from the bag
                        }
                        continue;
                    case "S":
                        playerSwitched = Party.switchPokemon(arena, false, sc1);
                        if (!playerSwitched) continue;
                        else {
                            arena.p[0].setTurnSentOut(arena.turnNum);
                            Thread.sleep((long) (User.textSpeed * .75));
                            System.out.println();
                        }
                        break;
                    case "I":
                        viewBattleInfo(arena, arena.p[0], arena.fp[0], sc1);
                        continue;
                    case "R":
                        System.out.println("You ran away from the wild " + arena.fp[0].getName() + ".");
                        Sound.playSoundOnce("src/main/music/runAway.mp3");
                        playerHasRunAway = true;
                        break;
                    default: continue;
                }
            }
            //start of turn stuff
            if (!arena.isCaught) handleStatusConditionsAtBeginningOfTurn(arena);

                // Handles turns (normal switches & move order based on speed)
            if (!playerHasRunAway && !wildPkmIsCaught) {
                processMoveOrder(arena, playerMove, foeMove, playerSwitched);
            }
            //Handle things like burn and poison
            handleEndOfTurnInteractions(arena);

            // Handle forced switch or defeat
            if (!playerHasRunAway && arena.p[0].getCurrentHp() == 0) {
                System.out.println("Your " + arena.p[0].getName() + " fainted!");
                Thread.sleep((long) (User.textSpeed * 0.5));
                playerSwitched = Party.switchPokemon(arena,false, sc1);
                if (playerSwitched) {
                    arena.p[0].setTurnSentOut(arena.turnNum);
                }
                else {
                    System.out.println("You have no Pokémon left to fight!");
                    Game.pressEnterToContinue(sc1);
                    playerHasRunAway = true; // Forced run if no Pokémon left
                }
            }

            if (!playerHasRunAway && arena.fp[0].getCurrentHp() > 0 && arena.p[0].getCurrentHp() > 0 && !wildPkmIsCaught) {
                playerMove = null;
                Thread.sleep((long) (User.textSpeed * 0.5));
            }
            if (arena.p[0].isSkipNextTurn() && (arena.p[0].getRechargeTurn() == arena.turnNum)){
                System.out.println(arena.p[0].getName() + " is waiting!\n");
                Thread.sleep(User.textSpeed);
            }
            //things to do at the end of each turn
            handleTurnChangeInteractions(arena);
            playerSwitched = false;
            arena.incrementTurns();
        } while (!playerHasRunAway && arena.fp[0].getCurrentHp() > 0 && arena.p[0].getCurrentHp() > 0 && !wildPkmIsCaught); //ends a turn
        // end-of-battle messages & reward player
        doWildPkmBattleOutcome(arena, wildPkmIsCaught, playerHasRunAway);
    }
    public static void doWildPkmBattleOutcome(Arena arena, boolean wildPkmIsCaught, boolean playerHasRunAway) throws InterruptedException {
        if (arena.fp[0].getCurrentHp() == 0) {
            Sound.stopAllSounds();
            System.out.println("The wild " + arena.fp[0].getName() + " fainted!\n");
            Sound.playMusicOnLoop("src/main/music/victoryVsWildPkmTheme.mp3");
            Thread.sleep((long) (User.textSpeed * .75));
        } else if (wildPkmIsCaught) {
            //handled in bag!
        }
        Thread.sleep((long) (User.textSpeed * 0.5));
        if (!playerHasRunAway) Party.levelUpEntirePartyByOne();
        Party.resetPartyAfterBattle();
    }
    //General logic
    public static void displayBattleStatus(Arena arena, boolean isTrainerBattle) {
        String foePartyGraphic = "";
        if(isTrainerBattle) foePartyGraphic = makeTrainerPartyGraphic(arena,true);
        String playerPartyGraphic = makeTrainerPartyGraphic(arena,false);

        Pokemon foe = arena.fp[0];
        Pokemon pkm = arena.p[0];

        Graphics.printPokemon(foe, true);
        System.out.print("                                  " +
                foe.getName() + " lv. " + foe.getLevel() + " | HP ");
        printHealthBar(foe, 15);
        System.out.println(" " + foe.getCurrentHp() + "/" + foe.getCurrentMaxHp() + " | " + foePartyGraphic);

        Graphics.printPokemon(pkm, false);
        System.out.print(pkm.getName() + " lv. " + pkm.getLevel() + " | HP ");
        printHealthBar(pkm, 15);
        System.out.println(" " + pkm.getCurrentHp() + "/" + pkm.getCurrentMaxHp() + " | " + playerPartyGraphic);

        String weatherMessage = " ";
        if (!arena.weather.equals("None")) {
            weatherMessage = "It's " + arena.getWeather() + ".";
        }
        System.out.println("                                                        " + weatherMessage + " " +  "~Turn " + arena.getTurnNum() + "~");
        System.out.println("                                                        " + Fight.makeMatchUpAnalysisStr(arena, pkm, foe));

    }
    public static void printHealthBar(Pokemon pokemon, int length) {
        final String RESET = "\u001B[0m";
        final String RED = "\u001B[31m";
        final String GREEN = "\u001B[92m";
        final String YELLOW_GREEN = "\u001B[38;5;154m";
        final String YELLOW = "\u001B[93m";

        int barLength = length;

        double maxHp = pokemon.getCurrentMaxHp();
        double currentHp = pokemon.getCurrentHp();
        double unroundedHpRatio =  (currentHp/maxHp);
        double hp = Math.round(unroundedHpRatio * barLength);

        String emptyStart = "⣏";
        String emptyEnd = "⣹";
        String full = "⣿";
        String empty = "⣉";

        String color = RESET;
        if (unroundedHpRatio < .2) color = RED;
        else if (unroundedHpRatio < .5) color = YELLOW;
        else if (unroundedHpRatio < .80) color = YELLOW_GREEN;
        else color = GREEN;

        String[] healthBar = new String[barLength];
        for (int i = 0; i < barLength; i++) {
            if (i<hp) {
                healthBar[i] = full;
            } else if(i == 0){
                healthBar[i] = emptyStart;
            } else if(i < barLength - 1){
                healthBar[i] = empty;
            } else {
                healthBar[i] = emptyEnd;
            }
        }
        System.out.print("[" + color);
        for (String string : healthBar) {
            System.out.print(string);
        }
        System.out.print(RESET + "]");

    }
    public static String makeTrainerPartyGraphic(Arena arena, boolean isFoe) {
        Pokemon[] partyArr = isFoe ? arena.fp : arena.p;
        String[] graphicArr = new String[6];
        StringBuilder graphic = new StringBuilder();
        for(int i = 0; i < partyArr.length; i++) {
            if (partyArr[i] == null) graphicArr[i] = "."; //empty
            else if (partyArr[i].getCurrentHp() > 0) graphicArr[i] = "o"; //alive
            else graphicArr[i] = "x"; //dead :(
        }
        for(String slot : graphicArr) {
            if (slot != null && slot.equals("o")) graphic.append(slot);
        }
        for(String slot : graphicArr) {
            if (slot != null && slot.equals("x")) graphic.append(slot);
        }
        int numEmptySlots = 0;
        if (isFoe) numEmptySlots = 6 - partyArr.length;
        else {
            for (Pokemon pkm : partyArr) {
                if (pkm == null) numEmptySlots++;
            }
        }
        graphic.append("-".repeat(Math.max(0, numEmptySlots)));
        return graphic.toString();
    }
    public static String getPlayerChoice(Arena arena, Scanner sc1) {
        String choice;
        System.out.println("Fight [F] | Bag [B] | Switch [S] | [I] Info | Run [R] " + Fight.makeRecommendedSwitchStr(arena));
        choice = sc1.nextLine().trim().toUpperCase();

        if (choice.matches("[FBSIR]")) return choice;
        System.out.println("Invalid input.");
        return "-1";
    }
    public static void handleStatusConditionsAtBeginningOfTurn(Arena arena) throws InterruptedException {
        if(arena.p[0].getStatusCondition().equalsIgnoreCase("Sleep") && (arena.p[0].getWakeUpTurn() <= arena.turnNum || Math.random() < .33)){
            arena.p[0].setStatusCondition("None");
            arena.p[0].setWakeUpTurnToTurnNum(arena);
        }
        if(arena.fp[0].getStatusCondition().equalsIgnoreCase("Sleep") && (arena.fp[0].getWakeUpTurn() <= arena.turnNum ||Math.random() < .33)){
            arena.fp[0].setStatusCondition("None");
            arena.fp[0].setWakeUpTurnToTurnNum(arena);
        }
    }
    public static void handleTurnChangeInteractions(Arena arena) throws InterruptedException{
        arena.p[0].setFlinched(false);
        arena.fp[0].setFlinched(false);
        String originalWeather = arena.weather;
        boolean wasOriginallyTrickRoom = arena.trickRoomIsUp;
        if(arena.turnWeatherEnds == arena.turnNum){
            arena.setWeather("None");
            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println("It stopped being " + originalWeather + ".\n");
            Thread.sleep(User.textSpeed);
        }
        if(wasOriginallyTrickRoom && arena.turnTrickRoomEnds == arena.turnNum){
            arena.setTrickRoom(false);
            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println("The dimension of spacetime went back to normal!");
            Thread.sleep(User.textSpeed);
        }
        if(arena.fp[0].isSkipNextTurn() && arena.fp[0].getRechargeTurn() <= arena.turnNum){
            arena.fp[0].setSkipNextTurn(false);
        }
        if(arena.p[0].isSkipNextTurn() && arena.p[0].getRechargeTurn() <= arena.turnNum){
            arena.p[0].setSkipNextTurn(false);
        }
    }
    public static void handleEndOfTurnInteractions(Arena arena) throws InterruptedException {
        if(arena.p[0].getStatusCondition().equals("Burn") || arena.p[0].getStatusCondition().equals("Poison")) {
            arena.p[0].setCurrentHp(Math.max(arena.p[0].getCurrentHp() - (int) (arena.p[0].getCurrentMaxHp()*.125), 0));
            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(arena.p[0].getName() + " took damage due to its " + arena.p[0].getStatusCondition().toLowerCase() + "!\n");
            Sound.playSoundOnce("src/main/music/cut.mp3");
            Thread.sleep(User.textSpeed);
        }
        if(arena.fp[0].getStatusCondition().equals("Burn") || arena.fp[0].getStatusCondition().equals("Poison")) {
            arena.fp[0].setCurrentHp(Math.max(arena.fp[0].getCurrentHp() - (int) (arena.fp[0].getCurrentMaxHp()*.125), 0));
            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(arena.fp[0].getName() + " took damage due to its " + arena.fp[0].getStatusCondition().toLowerCase() + "!\n");
            Sound.playSoundOnce("src/main/music/cut.mp3");
            Thread.sleep(User.textSpeed);
        }
    }
    public static void viewBattleInfo(Arena arena, Pokemon playerPokemon, Pokemon opponentPokemon, Scanner sc1) throws InterruptedException, ExecutionException {
        boolean displayWinPercentage = false;
        int accuracy = 50;
        if(User.hintMode == User.Hints.SHOW_ENGINE_CHOICES) {
            System.out.println("                  Display win %?");
            System.out.println("===================================================");
            System.out.println(" [1] Low Accuracy         (fast load times!)");
            System.out.println(" [2] High Accuracy        (longer load times)");
            System.out.println(" [3] Very High Accuracy   (much longer load times)");
            System.out.println(" [D] Don't display win %");
            String accuracySelection = "1";
            displayWinPercentage = true;
            accuracySelection = sc1.nextLine().trim().toUpperCase();
            if (accuracySelection.equalsIgnoreCase("D")) displayWinPercentage = false;
            else if (accuracySelection.equals("1")) accuracy = 100;
            else if (accuracySelection.equals("2")) accuracy = 500;
            else if (accuracySelection.equals("3")) accuracy = 2000;
            else System.out.println("Invalid input. Defaulting to low accuracy...");
            if(displayWinPercentage) System.out.println("Loading... (this may take some time at higher accuracies)");
        }
        String winPercentMessage = "";
        if(displayWinPercentage) winPercentMessage = Engine.calculateWinPercentage(arena.p, arena.fp, accuracy);
        System.out.println("=================== Battle Info ===================");
        displayBattleInfo(arena, playerPokemon, opponentPokemon);
        System.out.println("===================================================");
        System.out.println(" " + winPercentMessage);
        System.out.println("===================================================");
        Game.pressEnterToContinue(sc1);

    }
    public static void displayBattleInfo(Arena arena, Pokemon playerPokemon, Pokemon opponentPokemon) {
        System.out.printf("%-30s vs %-30s%n",
                playerPokemon.getName() + " (Lv. " + playerPokemon.getLevel() + ")",
                opponentPokemon.getName() + " (Lv. " + opponentPokemon.getLevel() + ")");

        System.out.printf("%-30s | %-30s%n",
                "Type: " + playerPokemon.getTyping(),
                "Type: " + opponentPokemon.getTyping());

        System.out.printf("%-30s | %-30s%n",
                "HP: " + playerPokemon.getCurrentHp() + "/" + playerPokemon.getCurrentMaxHp(),
                "HP: " + opponentPokemon.getCurrentHp() + "/" + opponentPokemon.getCurrentMaxHp());


        displayStatLine("Attack",
                (double) playerPokemon.getCurrentAttack() * playerPokemon.getStatMultiplier("Attack"), playerPokemon.attackStage,
                (double) opponentPokemon.getCurrentAttack() * opponentPokemon.getStatMultiplier("Attack"), opponentPokemon.attackStage);

        displayStatLine("Defense",
                (double) playerPokemon.getCurrentDefense() * playerPokemon.getStatMultiplier("Defense"), playerPokemon.defenseStage,
                (double) opponentPokemon.getCurrentDefense() * opponentPokemon.getStatMultiplier("Defense"), opponentPokemon.defenseStage);

        displayStatLine("Sp. Atk",
                (double) playerPokemon.getCurrentSpAtk() * playerPokemon.getStatMultiplier("SpAtk"), playerPokemon.spAtkStage,
                (double) opponentPokemon.getCurrentSpAtk() * opponentPokemon.getStatMultiplier("SpAtk"), opponentPokemon.spAtkStage);

        displayStatLine("Sp. Def",
                (double) playerPokemon.getCurrentSpDef() * playerPokemon.getStatMultiplier("SpDef"), playerPokemon.spDefStage,
                (double) opponentPokemon.getCurrentSpDef() * opponentPokemon.getStatMultiplier("SpDef"), opponentPokemon.spDefStage);

        displayStatLine("Speed",
                (double) playerPokemon.getCurrentSpeed() * playerPokemon.getStatMultiplier("Speed"), playerPokemon.speedStage,
                (double) opponentPokemon.getCurrentSpeed() * opponentPokemon.getStatMultiplier("Speed"), opponentPokemon.speedStage);

        System.out.printf("%-30s | %-30s%n",
                "Status: " + playerPokemon.getStatusCondition(),
                "Status: " + opponentPokemon.getStatusCondition());

        if (!arena.weather.equals("None")) {
            System.out.println("It's " + arena.getWeather() + ". Ends in " + (arena.turnWeatherEnds - arena.turnNum + 1) + " turn(s).");
        }
        if (arena.trickRoomIsUp) {
            System.out.println("Trick Room is set! Ends in " + (arena.turnTrickRoomEnds - arena.turnNum + 1) + " turn(s).");
        }
    }
    public static void displayStatLine(String statName, double playerStat, int playerStage, double opponentStat, int opponentStage) {
        String playerStatStr = statName + ": " + (int) playerStat +
                (playerStage != 0 ? " (" + formatStage(playerStage) + ")" : "");
        String opponentStatStr = statName + ": " + (int) opponentStat +
                (opponentStage != 0 ? " (" + formatStage(opponentStage) + ")" : "");

        System.out.printf("%-30s | %-30s%n", playerStatStr, opponentStatStr);
    }
    public static String formatStage(int stage) {
        if (stage > 0) return "+" + stage;
        return String.valueOf(stage);
    }
    //turn order
    public static void executePlayerMove(Arena arena, Move playerMove) throws InterruptedException {
        if (arena.p[0].getCurrentHp() > 0 && !arena.p[0].isFlinched() && !arena.p[0].isSkipNextTurn() && !arena.p[0].getStatusCondition().equals("Sleep")) {
            Fight.useMove(arena, playerMove, arena.p[0], arena.fp[0]);
        }
        else if (arena.p[0].getCurrentHp() > 0 && arena.p[0].getStatusCondition().equals("Sleep")) {
            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(arena.p[0].getName() + " is fast asleep!\n");
            Thread.sleep((long) (.75 * User.textSpeed));
        }
        //do nothing if player fainted
    }
    public static void executeFoeMove(Arena arena, Move foeMove) throws InterruptedException {
        if (arena.fp[0].getCurrentHp() > 0 && !arena.fp[0].isFlinched() && !arena.fp[0].isSkipNextTurn() && !arena.fp[0].getStatusCondition().equals("Sleep")) {
            Fight.useMove(arena, foeMove, arena.fp[0], arena.p[0]);
        }
        if (arena.fp[0].getCurrentHp() > 0 && arena.fp[0].isSkipNextTurn() && (arena.fp[0].getRechargeTurn()+1 != arena.getTurnNum())){
            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(arena.fp[0].getName() + " is waiting!\n");
            Thread.sleep((long)(.75* User.textSpeed));
        }
        if (arena.fp[0].getCurrentHp() > 0 && arena.fp[0].getStatusCondition().equals("Sleep") && (arena.fp[0].getWakeUpTurn() != arena.getTurnNum() +3)){
            if(arena.playerEngine.battleDialogsAreEnabled) System.out.println(arena.fp[0].getName() + " is fast asleep!\n");
            Thread.sleep((long) (.75 * User.textSpeed));
        }
    }
    public static void processMoveOrder(Arena arena, Move playerMove, Move foeMove, boolean playerSwitched) throws InterruptedException {
        boolean foeHasSpeedOrPriorityAdvantage = checkIfFoeHasSpeedOrPriorityAdvantage(arena, playerMove, foeMove);
        boolean isSpeedTie = checkIfIsSpeedTie(arena);
        if (playerSwitched) {
            executeFoeMove(arena, foeMove);
            return; // --> turn always ends after switching
        }
        int foePriority = (foeMove == null) ? 0 : foeMove.getPriority();
        int playerPriority = (playerMove == null) ? 0 : playerMove.getPriority();
        if(isSpeedTie && foePriority == playerPriority) {
            foeHasSpeedOrPriorityAdvantage = Math.random() < .5;
        }
        if (foeHasSpeedOrPriorityAdvantage) {
            if (foeMove != null) executeFoeMove(arena, foeMove);
            if (playerMove != null) executePlayerMove(arena, playerMove);
        } else {
            if (playerMove != null) executePlayerMove(arena, playerMove);
            if (foeMove != null) executeFoeMove(arena, foeMove);
        }
    }
    public static boolean checkIfFoeHasSpeedOrPriorityAdvantage(Arena arena, Move playerMove, Move foeMove) {
        boolean foeIsFaster = checkIfDealerIsFaster(arena, arena.fp[0], arena.p[0]);
        boolean foeHasPriorityAdvantage = checkIfFoeHasPriotityAdvantage(playerMove, foeMove);
        boolean playerHasPriorityAdvantage = checkIfPlayerHasPriotityAdvantage(playerMove, foeMove);

        if(foeHasPriorityAdvantage) return true;
        if(playerHasPriorityAdvantage) return false;
        else return foeIsFaster;
    }
    public static boolean checkIfFoeHasPriotityAdvantage(Move playerMove, Move foeMove) {
        int playerPriority = 0;
        int foePriority = 0;
        if (foeMove != null) foePriority = foeMove.getPriority();
        if (playerMove != null) playerPriority = playerMove.getPriority();
        return foePriority > playerPriority;
    }
    public static boolean checkIfPlayerHasPriotityAdvantage(Move playerMove, Move foeMove) {
        int playerPriority = 0;
        int foePriority = 0;
        if (foeMove != null) foePriority = foeMove.getPriority();
        if (playerMove != null) playerPriority = playerMove.getPriority();
        return playerPriority > foePriority;
    }
    public static boolean checkIfDealerIsFaster(Arena arena, Pokemon dealer, Pokemon recipient) {
        double dealerParalysisMult = 1.0;
        double recipientParalysisMult = 1.0;
        double dealerSpeed = dealer.getCurrentSpeed() * dealer.getStatMultiplier("Speed") * dealerParalysisMult;
        double recipientSpeed = recipient.getCurrentSpeed() * recipient.getStatMultiplier("Speed") * recipientParalysisMult;
        if(dealer.getStatusCondition().equals("Paralysis")){
            dealerParalysisMult = .25;
        }
        if(recipient.getStatusCondition().equals("Paralysis")){
            recipientParalysisMult = .25;
        }
        dealerSpeed = dealerSpeed * dealerParalysisMult;
        recipientSpeed = recipientSpeed * recipientParalysisMult;
        boolean dealerIsFaster = false;
        if (dealerSpeed > recipientSpeed) dealerIsFaster = true;
        if (arena.trickRoomIsUp) dealerIsFaster = !dealerIsFaster;
        return dealerIsFaster;
    }
    public static boolean checkIfIsSpeedTie(Arena arena) {
        double playerParalysisMult = 1.0;
        double foeParalysisMult = 1.0;
        double foeSpeed = arena.fp[0].getCurrentSpeed() * arena.fp[0].getStatMultiplier("Speed") * foeParalysisMult;
        double playerSpeed = arena.p[0].getCurrentSpeed() * arena.p[0].getStatMultiplier("Speed") * playerParalysisMult;
        if(arena.p[0].getStatusCondition().equals("Paralysis")){
            playerParalysisMult = .25;
        }
        if(arena.fp[0].getStatusCondition().equals("Paralysis")){
            foeParalysisMult = .25;
        }
        foeSpeed = foeSpeed * foeParalysisMult;
        playerSpeed = playerSpeed * playerParalysisMult;
        return foeSpeed == playerSpeed;
    }
    //Trainer LOGIC (WIP)
    public static boolean enterTrainerBattle(Trainer trainer, Scanner sc1) throws InterruptedException, ExecutionException {
        Sound.stopAllSounds();
        Engine engine = new Engine(new EnginePackage(EnginePackage.defaultEngineParameterMap),true);
        Arena arena = new Arena(Party.p, trainer, engine, engine);

        if (trainer.title == Trainer.Title.VAUGHAN_DISTRICT_GYM_LEADER) {
            Sound.playMusicOnLoop("src/main/music/vaughanBattleTheme.mp3");
        } else if (arena.isFacingGymLeader()) {
            Sound.playMusicOnLoop("src/main/music/gymLeaderBattleTheme.mp3");
        } else {
            Sound.playMusicOnLoop("src/main/music/trainerBattleTheme.mp3");
        }

        // Display trainer's intro message & GRAPHIC!!
        Graphics.printTrainer(trainer);
        if (arena.isFacingGymLeader()) {
            System.out.println("You challenged " + trainer.titleString + " " + trainer.name + "!");
        } else {
            System.out.println(trainer.titleString + " " + trainer.name + " wants to battle!");
        }
        Thread.sleep((long) (.75 * User.textSpeed));
        Game.pressEnterToContinue(sc1);

        // Start the battle!
        boolean playerHasWon = playTrainerBattle(arena, sc1);
        // Reset after the battle
        Game.pressEnterToContinue(sc1);
        Sound.stopAllSounds();

        return playerHasWon;
    }
    public static boolean playTrainerBattle(Arena arena, Scanner sc1) throws InterruptedException, ExecutionException {
        Graphics.printPokemon(arena.fp[0].getName(), false, arena.fp[0].isShiny());
        System.out.println(arena.trainer.name + " sent out " + arena.fp[0].getName() + "!");
        Sound.exitBall(arena.fp[0]);
        Thread.sleep(User.textSpeed);

        Graphics.printPokemon(arena.p[0].getName(), false, arena.p[0].isShiny());
        System.out.println("You sent out " + arena.p[0].getName() + "!");
        Sound.exitBall(arena.p[0]);
        Thread.sleep(User.textSpeed);

        boolean playerHasWon = false;
        boolean playerHasRunAway = false;
        boolean playerSwitched = false;
        Move playerMove = null;
        Move foeMove = null;
        int engineBestSlot = 0;
        int foeSwitchSlot = 0;
        do {
            //graphics
            displayBattleStatus(arena, true);

            // foe decisions
            foeMove = Fight.chooseAMove(arena, arena.fp[0], arena.p[0]);
            foeSwitchSlot = Fight.findBestPokemonSlotToHaveOutBasedOnSwitchThreshold(arena, true);

            //skip player turn
            if (arena.p[0].isSkipNextTurn()) {
                System.out.println(arena.p[0].getName() + " is waiting this turn.\n");
                Thread.sleep(User.textSpeed);
            }

            //player decisions
            else {
                String choice = getPlayerChoice(arena, sc1);
                switch (choice) {
                    case "F":
                        Move engineBestMove = Fight.findBestMove(arena, arena.p[0], arena.fp[0]);
                        playerMove = Fight.askUserToChooseAMove(arena, arena.p[0], arena.fp[0], engineBestMove, sc1);
                        if (playerMove == null) continue; // Player canceled move selection
                        break;

                    case "B":
                        if (Bag.openBattlePocketTrainerEncounter(sc1)) {
                            playerMove = null;
                            break; // Player used an item from the bag
                        }
                        continue;

                    case "S":
                        int playerSwitchSlot = Party.getSwitchSlotFromUser(arena,false, sc1);
                        playerSwitched = Party.switchPokemonToSlot(playerSwitchSlot);
                        if (!playerSwitched) continue;
                        else {
                            arena.p[0].setTurnSentOut(arena.turnNum);
                            Thread.sleep((long) (User.textSpeed * .75));
                            System.out.println();
                        }
                        //Game Engine choice
                        engineBestSlot = Fight.findBestPokemonSlotToHaveOutBasedOnSwitchThreshold(arena, false);
                        break;

                    case "I":
                        viewBattleInfo(arena, arena.p[0], arena.fp[0], sc1);
                        continue;

                    case "R":
                        System.out.println("You can't run from a trainer battle!");
                        Game.pressEnterToContinue(sc1);
                        continue;

                    default: continue;
                }
            }

            //check if foe wanted to switch and switch if so desired
            boolean foeSwitched = Fight.decideToSwitchPokemonAndDoItIfWanted(arena, true, foeSwitchSlot);
            if(foeSwitched) foeMove = null;

            //compare to game engine choice

            //start of turn stuff
            handleStatusConditionsAtBeginningOfTurn(arena);

            // Handle turns (normal switches & move order based on speed)
            processMoveOrder(arena, playerMove, foeMove, playerSwitched);

            //Handle things like burn and poison
            handleEndOfTurnInteractions(arena);

            // Handle forced switch or defeat
            if (arena.p[0].getCurrentHp() == 0) {
                System.out.println("Your " + arena.p[0].getName() + " fainted!");
                Thread.sleep((long) (User.textSpeed * 0.5));
                playerSwitched = Party.switchPokemon(arena, false, sc1);
                if (playerSwitched) {
                    arena.p[0].setTurnSentOut(arena.turnNum);
                }
                else {
                    System.out.println("You have no Pokémon left to fight!");
                    Game.pressEnterToContinue(sc1);
                    playerHasRunAway = true; // Forced run if no Pokémon left
                }
            }

            // Check if the trainer's Pokémon has fainted
            if (arena.fp[0].getCurrentHp() == 0) {
                System.out.println(arena.fp[0].getName() + " fainted!\n");
                Thread.sleep((long) (User.textSpeed * 0.5));

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

            // Handle turn-changing interactions
            handleTurnChangeInteractions(arena);
            playerSwitched = false;
            arena.incrementTurns();
        } while (!playerHasRunAway && !playerHasWon && arena.p[0].getCurrentHp() > 0);

        // End-of-battle messages & rewards
        doTrainerBattleOutcome(arena, playerHasWon, playerHasRunAway, sc1);
        return playerHasWon;
    }
    public static void unlockBadgeOrGiveNodOfRespect(Arena arena, Scanner sc1) throws InterruptedException {
        boolean isGymLeader = arena.isFacingGymLeader();
        boolean isOtherMajorTrainer = arena.isFacingMajorTrainer();
        if (isGymLeader && !User.badgesEarned.get(arena.trainer.title)) {
            User.badgesEarned.put(arena.trainer.title, true);
            Graphics.printBadge(arena.trainer.title);
            System.out.println(arena.trainer.title.getName() + " handed you the " + arena.trainer.title.getBadgeName() + "!");
            Sound.playSoundOnce("src/main/music/importantItemGotten.mp3");
            Thread.sleep((long) (1.5 * User.textSpeed));
            Game.pressEnterToContinue(sc1);
        }
        if (isOtherMajorTrainer && !User.majorTrainersBeaten.get(arena.trainer.title)) {
            User.majorTrainersBeaten.put(arena.trainer.title, true);
            System.out.println(arena.trainer.title.getName() + " gave you a nod of respect!");
            Thread.sleep((long) (1.25 * User.textSpeed));
            Game.pressEnterToContinue(sc1);
        }
    }
    public static void givePlayerMoneyAfterBeatingTrainer(Arena arena, double rewardMultiplier) throws InterruptedException {
        int moneyReward = (int) (rewardMultiplier * arena.trainer.prize * (User.checkNumBadges() / 2.0))
                + (int) (Math.random()*100 + Math.random()*10*User.checkNumBadges());
        System.out.println("You earned " + moneyReward + " Pokedollars!\n");
        Bag.adjustPokedollarBalance(moneyReward);
    }
    public static void doTrainerBattleOutcome(Arena arena, boolean playerHasWon, boolean playerHasRunAway, Scanner sc1) throws InterruptedException {
        if (playerHasWon) {
            Sound.stopAllSounds();
            Sound.playMusicOnLoop("src/main/music/victoryVsTrainerTheme.mp3");
            double rewardMultiplier = 1.0;
            if((arena.isFacingGymLeader() && User.badgesEarned.get(arena.trainer.title))
                    || (arena.isFacingMajorTrainer() && User.majorTrainersBeaten.get(arena.trainer.title))) {
                rewardMultiplier = .1;
            }

            Graphics.printTrainer(arena.trainer);
            System.out.println("You defeated " + arena.trainer.titleString + " " + arena.trainer.name + "!\n");
            Thread.sleep(User.textSpeed);
            unlockBadgeOrGiveNodOfRespect(arena, sc1);

            Party.levelUpEntirePartyByOne();
            givePlayerMoneyAfterBeatingTrainer(arena, rewardMultiplier);
        } else if (playerHasRunAway) {
            int losses = (int) (Bag.getPokedollars()*.05);
            System.out.println("You lost " +  losses + " Pokedollars to " + arena.trainer.name +"...");
            Bag.spendPokedollarsSilent(losses);
        }
        Party.resetPartyAfterBattle();
    }
}
