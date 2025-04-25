package pokemonTextBased;

import com.google.gson.*;
import java.io.*;
import java.util.*;

public class SaveSys {

    private static final String SAVE_FILE_PREFIX = "save";
    private static final String SAVE_FILE_SUFFIX = ".json";
    private static final int MAX_SLOTS = 5;

    public static void saveAll(int slot) {
        if (slot < 0 || slot >= MAX_SLOTS) {
            System.err.println("Invalid save slot. Must be 0 to 4.");
            return;
        }

        SaveData data = new SaveData();

        // Party
        data.party = Party.p;

        // Box
        data.box = new ArrayList<>(Box.getBox());

        // Bag
        data.pokedollars = Bag.getPokedollars();
        data.BP = Bag.getBP();
        data.debt = Bag.getDebt();
        data.goldBars = Bag.getGoldBars();
        data.stockPortfolio = Bag.getStockPortfolio();
        data.battleItems = Bag.getBattleItems();
        data.specialItems = Bag.getSpecialItems();
        data.notes = Bag.getNotes();

        // User
        data.username = User.username;
        data.gender = User.gender.genderString;
        data.textSpeed = User.textSpeed;
        data.difficultyMode = User.difficultyMode.getStr();
        data.hintMode = User.hintMode.getStr();
        data.reputation = User.reputation;
        data.recordColosseumTrainersBeaten = User.recordColosseumTrainersBeaten;
        data.tradeHistory = new ArrayList<>(User.tradeHistory);
        data.badgesEarned = toStringMap(User.badgesEarned);
        data.majorTrainersBeaten = toStringMap(User.majorTrainersBeaten);
        data.trainersBeatenOnARoute = User.trainersBeatenOnARoute;
        data.routesReached = User.routesReached;
        data.areasReached = toStringMap(User.areasReached);
        data.pokemonRegisteredInPokedex = new ArrayList<>(User.pokemonRegisteredInPokedex);

        String fileName = SAVE_FILE_PREFIX + slot + SAVE_FILE_SUFFIX;

        try (FileWriter writer = new FileWriter(fileName)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(data, writer);
            System.out.println("Game saved to slot " + slot + ".");
        } catch (IOException e) {
            System.err.println("Failed to save game to slot " + slot + ": " + e.getMessage());
        }
    }

    public static void loadAll(int slot) {
        if (slot < 0 || slot >= MAX_SLOTS) {
            System.err.println("Invalid load slot. Must be 0 to 4.");
            return;
        }

        String fileName = SAVE_FILE_PREFIX + slot + SAVE_FILE_SUFFIX;

        try (FileReader reader = new FileReader(fileName)) {
            Gson gson = new Gson();
            SaveData data = gson.fromJson(reader, SaveData.class);

            Party.p = data.party;
            Box.setBox(data.box);

            Bag.setPokedollars(data.pokedollars);
            Bag.setBP(data.BP);
            Bag.setDebt(data.debt);
            Bag.setGoldBars(data.goldBars);
            Bag.setStockPortfolio(data.stockPortfolio);
            Bag.setBattleItems(data.battleItems);
            Bag.setSpecialItems(data.specialItems);
            Bag.setNotes(data.notes);

            User.username = data.username;
            User.gender = User.Gender.valueOf(data.gender.toUpperCase());
            User.textSpeed = data.textSpeed;
            User.difficultyMode = User.Difficulty.valueOf(data.difficultyMode);
            User.hintMode = User.Hints.valueOf(data.hintMode);
            User.reputation = data.reputation;
            User.recordColosseumTrainersBeaten = data.recordColosseumTrainersBeaten;
            User.tradeHistory = new ArrayList<>(data.tradeHistory);
            User.badgesEarned = fromStringMap(data.badgesEarned, Trainer.Title.class);
            User.majorTrainersBeaten = fromStringMap(data.majorTrainersBeaten, Trainer.Title.class);
            User.trainersBeatenOnARoute = data.trainersBeatenOnARoute;
            User.routesReached = data.routesReached;
            User.areasReached = fromStringMap(data.areasReached, Location.Area.class);
            User.pokemonRegisteredInPokedex = new ArrayList<>(data.pokemonRegisteredInPokedex);

            System.out.println("Game loaded from slot " + slot + ".");
        } catch (IOException e) {
            System.err.println("Failed to load game from slot " + slot + ": " + e.getMessage());
        }
    }

    private static <E extends Enum<E>> Map<String, Boolean> toStringMap(Map<E, Boolean> original) {
        Map<String, Boolean> result = new TreeMap<>();
        for (Map.Entry<E, Boolean> entry : original.entrySet()) {
            result.put(entry.getKey().name(), entry.getValue());
        }
        return result;
    }

    private static <E extends Enum<E>> Map<E, Boolean> fromStringMap(Map<String, Boolean> map, Class<E> enumClass) {
        Map<E, Boolean> result = new TreeMap<>();
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            result.put(Enum.valueOf(enumClass, entry.getKey()), entry.getValue());
        }
        return result;
    }
}
