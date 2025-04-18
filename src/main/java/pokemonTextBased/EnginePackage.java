package pokemonTextBased;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EnginePackage {
    // move ratings
    public static String BASE_PT_FOR_DAMAGE = "BASE_PT_FOR_DAMAGE";
    public static String MAXIMUM_DAMAGE_RATIO_TO_CONSIDER = "MAXIMUM_DAMAGE_RATIO_TO_CONSIDER";
    public static String PT_PUNISHMENT_FOR_OPPONENT_HAVING_KO_MOVE = "PT_PUNISHMENT_FOR_OPPONENT_HAVING_KO_MOVE";
    public static String BASE_PT_FOR_OTHER_EFFECT = "BASE_PT_FOR_OTHER_EFFECT";
    public static String BASE_PT_FOR_PURE_STATUS = "BASE_PT_FOR_PURE_STATUS";
    public static String PT_GOOD_SET_UP_MOVE = "PT_GOOD_SET_UP_MOVE";
    public static String BASE_PT_PRIORITY = "BASE_PT_PRIORITY";
    public static String PRIORITY_PER_STAGE_BONUS = "PRIORITY_PER_STAGE_BONUS";
    public static String LAST_DITCH_PRIORITY_BONUS = "LAST_DITCH_PRIORITY_BONUS";
    public static String PRIORITY_GUARANTEED_KO = "PRIORITY_GUARANTEED_KO";
    public static String BASE_SPEED_ADVANTAGE_BONUS = "BASE_SPEED_ADVANTAGE_BONUS";
    public static String LIKELY_KO_BONUS = "LIKELY_KO_BONUS";
    public static String FAKE_OUT_BONUS = "FAKE_OUT_BONUS";
    public static String TRICK_ROOM_BONUS = "TRICK_ROOM_BONUS";
    public static String BASE_SWITCH_MOVE_BONUS = "BASE_SWITCH_MOVE_BONUS";
    public static String SWITCH_MOVE_BONUS_WHEN_OPP_HAS_A_KO_MOVE = "SWITCH_MOVE_BONUS_WHEN_OPP_HAS_A_KO_MOVE";
    public static String SLEEP_BONUS = "SLEEP_BONUS";
    public static String PT_PUNISHMENT_FOR_INACCURACY = "PT_PUNISHMENT_FOR_INACCURACY";
    public static String PT_PUNISHMENT_FOR_WAIT = "PT_PUNISHMENT_FOR_WAIT";
    public static String PT_PUNISHMENT_FOR_RECOIL = "PT_PUNISHMENT_FOR_RECOIL";

    // switch threshold ratios
    public static String SWITCH_THRESHOLD_PERCENT = "SWITCH_THRESHOLD_PERCENT";
    public static String NON_ACTIVE_MATCH_UP_WEIGHT_PERCENT = "NON_ACTIVE_MATCH_UP_WEIGHT_PERCENT";

    //overall match-up ratios
    public static String OWN_WAIT_PENALTY = "OWN_WAIT_PENALTY";
    public static String OPP_WAIT_PENALTY = "OPP_WAIT_PENALTY";
    public static String OPP_HAS_GUARANTEED_KO_PENALTY = "OPP_HAS_GUARANTEED_KO_PENALTY";


    private HashMap<String, Integer> engineParameters = null;

    public EnginePackage(HashMap<String, Integer> origParamMap) {
        this.engineParameters = new HashMap<>();
        this.engineParameters.putAll(origParamMap);
    }
    public EnginePackage tweakRandomly() {
        Random rand = new Random();
        HashMap<String, Integer> newParams = new HashMap<>(engineParameters);

//        int MAX_PARAMS_TO_TWEAK = 3;
//        int MIN_PARAMS_TO_TWEAK = 1;
//
//        int numTweaks = MIN_PARAMS_TO_TWEAK + rand.nextInt(MAX_PARAMS_TO_TWEAK-MIN_PARAMS_TO_TWEAK);
//
//        Object[] keys = engineParameters.keySet().toArray();
//        for (int i = 0; i < numTweaks; i++) {
//            String key = (String) keys[rand.nextInt(keys.length)];
//            int original = newParams.get(key);
//            int adjustment = rand.nextInt(-30, 300);
//            newParams.put(key, original + adjustment);
//        }
//        newParams.put(MAXIMUM_DAMAGE_RATIO_TO_CONSIDER, rand.nextInt(100, 300));
//        newParams.put(LIKELY_KO_BONUS, rand.nextInt(100, 300));
        newParams.put(SLEEP_BONUS, rand.nextInt(75,200));


        return new EnginePackage(newParams);
    }

    public int get(String key) {
        return engineParameters.getOrDefault(key, 20);
    }
    public void set(String key, int value) {
        engineParameters.put(key, value);
    }
    public static HashMap<String, Integer> defaultEngineParameterMap = new HashMap<>() {{
        put(EnginePackage.BASE_PT_FOR_DAMAGE, 150);
        put(EnginePackage.MAXIMUM_DAMAGE_RATIO_TO_CONSIDER, 150);
        put(EnginePackage.PT_PUNISHMENT_FOR_OPPONENT_HAVING_KO_MOVE, 77);
        put(EnginePackage.BASE_PT_FOR_OTHER_EFFECT, 100);
        put(EnginePackage.BASE_PT_FOR_PURE_STATUS, 20);
        put(EnginePackage.PT_GOOD_SET_UP_MOVE, 50);
        put(EnginePackage.BASE_PT_PRIORITY, 50);
        put(EnginePackage.PRIORITY_PER_STAGE_BONUS, 10);
        put(EnginePackage.LAST_DITCH_PRIORITY_BONUS, 270);
        put(EnginePackage.PRIORITY_GUARANTEED_KO, 250);
        put(EnginePackage.BASE_SPEED_ADVANTAGE_BONUS, 20);
        put(EnginePackage.LIKELY_KO_BONUS, 100);
        put(EnginePackage.FAKE_OUT_BONUS, 200);
        put(EnginePackage.TRICK_ROOM_BONUS, 200);
        put(EnginePackage.BASE_SWITCH_MOVE_BONUS, 50);
        put(EnginePackage.SWITCH_MOVE_BONUS_WHEN_OPP_HAS_A_KO_MOVE, 50);
        put(EnginePackage.SLEEP_BONUS, 110);
        put(EnginePackage.PT_PUNISHMENT_FOR_INACCURACY, 30);
        put(EnginePackage.PT_PUNISHMENT_FOR_WAIT, 125);
        put(EnginePackage.PT_PUNISHMENT_FOR_RECOIL, 10);

        put(EnginePackage.SWITCH_THRESHOLD_PERCENT, 250);
        put(EnginePackage.NON_ACTIVE_MATCH_UP_WEIGHT_PERCENT, -8);

        put(EnginePackage.OWN_WAIT_PENALTY, 147);
        put(EnginePackage.OPP_HAS_GUARANTEED_KO_PENALTY, 100);
    }};

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : engineParameters.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
