package pokemonTextBased;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SaveSys {

    public static Pokemon[] p = new Pokemon[6]; // party

    public static String serializeParty() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(p);
    }

    public static void deserializeParty(String json) {
        Gson gson = new Gson();
        p = gson.fromJson(json, Pokemon[].class);
    }
}