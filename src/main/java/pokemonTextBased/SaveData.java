package pokemonTextBased;

import java.util.*;

public class SaveData {
    public Pokemon[] party;
    public List<Pokemon> box;

    // Bag
    public long pokedollars;
    public long BP;
    public long debt;
    public int goldBars;
    public Map<String, Integer> stockPortfolio;
    public Map<String, Integer> battleItems;
    public Map<String, Integer> specialItems;
    public Map<String, String[]> notes;

    // User
    public String username;
    public String gender;
    public int textSpeed;
    public String difficultyMode;
    public String hintMode;
    public int reputation;
    public int recordColosseumTrainersBeaten;
    public List<String> tradeHistory;
    public Map<String, Boolean> badgesEarned;
    public Map<String, Boolean> majorTrainersBeaten;
    public Map<Integer, Integer> trainersBeatenOnARoute;
    public Map<Integer, Boolean> routesReached;
    public Map<String, Boolean> areasReached;
    public List<String> pokemonRegisteredInPokedex;
}
