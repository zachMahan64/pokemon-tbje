package pokemonTextBased;

import java.util.HashMap;
import java.util.Map;

public class Move {
    private final String name;
    private final String type;
    private final int damage;
    private final int accuracy;
    private final String otherEffect;
    private final int accuracyOfEffect;
    private final boolean useSpStats;
    private final int priority;
    private int currentPp;
    private final int totalPp;

    // Constructor
    public Move(String name, String type, int damage, int accuracy, String otherEffect, int accuracyOfEffect, boolean useSpStats, int priority, int currentPp, int totalPp) {
        this.name = name;
        this.type = type;
        this.damage = damage;
        this.accuracy = accuracy;
        this.otherEffect = otherEffect;
        this.accuracyOfEffect = accuracyOfEffect;
        this.useSpStats = useSpStats;
        this.priority = priority;
        this.currentPp = totalPp;
        this.totalPp = totalPp;
    }
    public Move(Move other) {
        this.name = other.name;
        this.type = other.type;
        this.damage = other.damage;
        this.accuracy = other.accuracy;
        this.otherEffect = other.otherEffect;
        this.accuracyOfEffect = other.accuracyOfEffect;
        this.useSpStats = other.useSpStats;
        this.priority = other.priority;
        this.currentPp = other.totalPp; // Reset PP to max on clone
        this.totalPp = other.totalPp;
    } //cloning

    // Getters
    public String getName() { return name; }
    public String getType() { return type; }
    public int getDamage() { return damage; }
    public int getAccuracy() { return accuracy; }
    public String getOtherEffect() { return otherEffect; }
    public int getAccuracyOfEffect() { return accuracyOfEffect; }
    public boolean isUseSpStats() { return useSpStats; }
    public int getPriority() { return priority; }
    public int getCurrentPp() { return currentPp; }
    public int getTotalPp() { return totalPp; }

    // Reduce PP when move is used
    public void lowerPP() {
        if (currentPp > 0) {
            currentPp--;
        }
    }
    public void setPP(int PP) {
        this.currentPp = PP;
    }
    public boolean canUseMove() {
        return currentPp > 0;
    }
    public static void resetMovePp(Move move) {
        move.currentPp = move.totalPp;
    }

    // Restore PP (cannot exceed totalPP)
    public void restorePP(int amount) {
        currentPp = Math.min(currentPp + amount, totalPp);
    }

    // String representation
    @Override
    public String toString() {
        return name + " (" + type + " Type, Damage: " + damage + ", Effect: " + otherEffect + ", PP: " + currentPp + "/" + totalPp + ")";
    }

    private static final Map<String, Move> moveList = new HashMap<>();
    static {
        // Normal-type Moves
        moveList.put("Tackle", new Move("Tackle", "Normal", 40, 100, "None", 0, false, 0, 35, 35));
        moveList.put("Pound", new Move("Pound", "Normal", 40, 100, "None", 0, false, 0, 35, 35));
        moveList.put("Headbutt", new Move("Headbutt", "Normal", 85, 100, "None", 0, false, 0, 15, 15));
        moveList.put("Scratch", new Move("Scratch", "Normal", 40, 100, "None", 0, false, 0, 35, 35));
        moveList.put("Slash", new Move("Slash", "Normal", 70, 100, "None", 0, false, 0, 20, 20));
        moveList.put("Swift", new Move("Swift", "Normal", 60, 100, "None", 0, true, 0, 20, 20));
        moveList.put("Judgement", new Move("Judgement", "Normal", 100, 100, "None", 0, true, 0, 20, 20));
        moveList.put("Struggle", new Move("Struggle", "Normal", 50, 100, "None", 0, false, 0, 100, 100));
        moveList.put("Body Slam", new Move("Body Slam", "Normal", 60, 100, "None", 0, false, 0, 15, 15));
        moveList.put("Horn Drill", new Move("Horn Drill", "Normal", 350, 30, "None", 0, false, 0, 5, 5));
        moveList.put("Growl", new Move("Growl", "Normal", 0, 100, "Opponent Attack -1", 100, false, 0, 40, 40));
        moveList.put("Scary Face", new Move("Scary Face", "Normal", 0, 90, "Opponent Speed -2", 100, false, 0, 15, 15));
        moveList.put("Leer", new Move("Leer", "Normal", 0, 100, "Opponent Defense -1", 100, false, 0, 30, 40));
        moveList.put("Screech", new Move("Screech", "Normal", 0, 100, "Opponent Defense -2", 100, false, 0, 30, 40));
        moveList.put("Sing", new Move("Sing", "Normal", 0, 60, "Opponent Sleep", 100, false, 0, 15, 15));
        moveList.put("Tail Whip", new Move("Tail Whip", "Normal", 0, 100, "Opponent Defense -1", 100, false, 0, 30, 30));
        moveList.put("Quick Attack", new Move("Quick Attack", "Normal", 40, 100, "None", 0, false, 1, 30, 30));
        moveList.put("Extreme Speed", new Move("Extreme Speed", "Normal", 80, 100, "None", 0, false, 2, 5, 5));
        moveList.put("Hyper Fang", new Move("Hyper Fang", "Normal", 80, 90, "None", 0, false, 0, 15, 15));
        moveList.put("Hyper Beam", new Move("Hyper Beam", "Normal", 150, 90, "Wait", 0, false, 0, 15, 15));
        moveList.put("Harden", new Move("Harden", "Normal", 0, 100, "Own Defense +1", 100, false, 0, 30, 30));
        moveList.put("Glare", new Move("Glare", "Normal", 0, 75, "Opponent Paralysis", 100, false, 0, 30, 30));
        moveList.put("Agility", new Move("Agility", "Normal", 0, 100, "Own Speed +1", 100, false, 0, 30, 30));
        moveList.put("Fake Out", new Move("Fake Out", "Normal", 40, 100, "Opponent Flinch", 100, false, 3, 10, 10));
        moveList.put("Defense Curl", new Move("Defense Curl", "Normal", 0, 100, "Own Defense +1", 100, false, 0, 40, 40));
        moveList.put("Giga Impact", new Move("Giga Impact", "Normal", 150, 90, "Wait", 100, false, 0, 5, 5));
        moveList.put("Recover", new Move("Recover", "Normal", 0, 100, "Heal Half Max Hp", 100, true, 0, 10, 10));
        moveList.put("Moonlight", new Move("Moonlight", "Normal", 0, 100, "Heal Half Max Hp", 100, true, 0, 5, 5));
        moveList.put("Explosion", new Move("Explosion", "Normal", 250, 100, "Faint", 100, false, 0, 5, 5));
        moveList.put("Self-Destruct", new Move("Self-Destruct", "Normal", 250, 100, "Faint", 100, false, 0, 5, 5));
        moveList.put("Horn Attack", new Move("Horn Attack", "Normal", 65, 100, "None", 0, false, 0, 25, 25));
        moveList.put("Rapid Spin", new Move("Rapid Spin", "Normal", 50, 100, "Own Speed +1", 100, false, 0, 40, 40));
        moveList.put("Transform", new Move("Transform", "Normal", 0, 100, "Take on Opponent Stats", 100, true, 0, 10, 10));
        moveList.put("Belly Drum", new Move("Belly Drum", "Normal", 0, 100, "Own Halve Max Hp & Attack +6", 100, true, 0, 10, 10));
        // Fire-type Moves
        moveList.put("Ember", new Move("Ember", "Fire", 40, 100, "None", 0, true, 0, 25, 25));
        moveList.put("Flamethrower", new Move("Flamethrower", "Fire", 90, 100, "None", 0, true, 0, 15, 15));
        moveList.put("Fusion Flare", new Move("Fusion Flare", "Fire", 100, 100, "None", 0, true, 0, 5, 5));
        moveList.put("Mystical Fire", new Move("Mystical Fire", "Fire", 75, 100, "Opponent Burn", 10, true, 0, 10, 10));
        moveList.put("Heat Wave", new Move("Heat Wave", "Fire", 100, 95, "Opponent Burn", 10, true, 0, 10, 10));
        moveList.put("Will-O-Wisp", new Move("Will-O-Wisp", "Fire", 0, 85, "Opponent Burn", 100, false, 0, 15, 15));
        moveList.put("Fire Spin", new Move("Fire Spin", "Fire", 35, 85, "Opponent Burn", 75, true, 0, 15, 15));
        moveList.put("Fire Fang", new Move("Fire Fang", "Fire", 55, 95, "Opponent Burn", 20, false, 0, 25, 25));
        moveList.put("Flame Charge", new Move("Flame Charge", "Fire", 50, 100, "Own Attack +1", 100, false, 0, 20, 20));
        moveList.put("Fire Blast", new Move("Fire Blast", "Fire", 110, 85, "Opponent Burn", 10, true, 0, 5, 5));
        moveList.put("Flare Blitz", new Move ("Flare Blitz", "Fire", 120, 100, "Recoil", 100, false, 0, 15, 15));
        moveList.put("Flame Wheel", new Move("Flame Wheel", "Fire", 60, 100, "Opponent Burn", 10, true, 0, 25, 25));
        moveList.put("Sunny Day", new Move("Sunny Day", "Fire", 0, 100, "Sunny", 100, true, 0, 5, 5));
        moveList.put("Fire Punch", new Move("Fire Punch", "Fire", 75, 100, "Opponent Burn", 10, false, 0, 15, 15));
        moveList.put("Blaze Kick", new Move("Blaze Kick", "Fire", 85, 90, "Opponent Burn", 10, false, 0, 10, 10));
        moveList.put("Eruption", new Move("Eruption", "Fire", 150, 100, "Damage Scales With User Hp", 100, true, 0, 5, 5));
        moveList.put("V-create", new Move("V-create", "Fire", 180, 95, "Own Defense -1 & SpDef -1", 100, false, 0, 5, 5));
        moveList.put("Sacred Fire", new Move("Sacred Fire", "Fire", 100, 95, "Opponent Burn", 50, false, 0, 5, 5));

        // Water-type Moves
        moveList.put("Bubble", new Move("Bubble", "Water", 40, 100, "None", 0, true, 0, 30, 30));
        moveList.put("Water Gun", new Move("Water Gun", "Water", 40, 100, "None", 0, true, 1, 25, 25));
        moveList.put("Water Pulse", new Move("Water Pulse", "Water", 60, 100, "None", 0, true, 1, 25, 25));
        moveList.put("Hydro Pump", new Move("Hydro Pump", "Water", 110, 80, "None", 0, true, 0, 5, 5));
        moveList.put("Origin Pulse", new Move("Origin Pulse", "Water", 110, 85, "None", 0, true, 0, 5, 5));
        moveList.put("Water Spout", new Move("Water Spout", "Water", 150, 100, "Damage Scales With User Hp", 100, true, 0, 5, 5));
        moveList.put("Surf", new Move("Surf", "Water", 90, 100, "None", 0, true, 0, 15, 15));
        moveList.put("Aqua Tail", new Move("Aqua Tail", "Water", 85, 90, "None", 0, false, 0, 10, 10));
        moveList.put("Waterfall", new Move("Waterfall", "Water", 80, 100, "None", 0, false, 0, 15, 15));
        moveList.put("Scald", new Move("Scald", "Water", 80, 100, "Burn", 10, true, 0, 15, 15));
        moveList.put("Bubble Beam", new Move("Bubble Beam", "Water", 65, 100, "Opponent Speed -1", 10, true, 0, 20, 20));
        moveList.put("Rain Dance", new Move("Rain Dance", "Water", 0, 100, "Rainy", 100, false, 0, 5, 5));
        moveList.put("Crabhammer", new Move("Crabhammer", "Water", 100, 90, "None", 0, false, 0, 10, 10));
        moveList.put("Splash", new Move("Splash", "Normal", 0, 100, "None", 0, true, 0, 40, 40));
        // Grass-type Moves
        moveList.put("Vine Whip", new Move("Vine Whip", "Grass", 45, 100, "None", 0, false, 0, 25, 25));
        moveList.put("Razor Leaf", new Move("Razor Leaf", "Grass", 55, 95, "None", 0, false, 0, 25, 25));
        moveList.put("Solar Beam", new Move("Solar Beam", "Grass", 120, 100, "Wait", 100, true, 0, 10, 10));
        moveList.put("Leaf Blade", new Move("Leaf Blade", "Grass", 90, 100, "None", 0, false, 0, 15, 15));
        moveList.put("Giga Drain", new Move("Giga Drain", "Grass", 75, 100, "Heal Half Hp Dealt", 100, true, 0, 10, 10));
        moveList.put("Mega Drain", new Move("Mega Drain", "Grass", 60, 100, "Heal Half Hp Dealt", 100, true, 0, 10, 10));
        moveList.put("Seed Bomb", new Move("Seed Bomb", "Grass", 80, 100, "None", 0, false, 0, 15, 15));
        moveList.put("Energy Ball", new Move("Energy Ball", "Grass", 90, 100, "Opponent SpDef -1", 10, true, 0, 10, 10));
        moveList.put("Magical Leaf", new Move("Magical Leaf", "Grass", 60, 100, "None", 0, true, 0, 20, 20));
        moveList.put("Leaf Storm", new Move("Leaf Storm", "Grass", 130, 90, "Opponent SpAtk -1", 10, true, 0, 10, 10));
        moveList.put("Absorb", new Move("Absorb", "Grass", 20, 100, "Heal Half Hp Dealt", 100, true, 0, 25, 25));
        moveList.put("Sleep Powder", new Move("Sleep Powder", "Grass", 0, 75, "Opponent Sleep", 100, true, 0, 15, 15));
        moveList.put("Petal Dance", new Move("Petal Dance", "Grass", 120, 100, "Recoil", 100, true, 0, 10, 10));
        moveList.put("Spore", new Move("Spore", "Grass", 0, 100, "Opponent Sleep", 100, true, 0, 15, 15));

        // Electric-type Moves
        moveList.put("Thunder Shock", new Move("Thunder Shock", "Electric", 40, 100, "None", 0, true, 1, 30, 30));
        moveList.put("Thunderbolt", new Move("Thunderbolt", "Electric", 90, 100, "None", 0, true, 0, 15, 15));
        moveList.put("Volt Switch", new Move("Volt Switch", "Electric", 70, 100, "Switch", 100, true, 0, 15, 15));
        moveList.put("Thunder", new Move("Thunder", "Electric", 110, 70, "None", 0, true, 0, 10, 10));
        moveList.put("Electro Ball", new Move("Electro Ball", "Electric", 80, 100, "None", 0, true, 0, 15, 15));
        moveList.put("Spark", new Move("Spark", "Electric", 65, 100, "None", 0, true, 0, 20, 20));
        moveList.put("Thunder Punch", new Move("Thunder Punch", "Electric", 75, 100, "Opponent Paralysis", 10, false, 0, 15, 15));
        moveList.put("Thunder Wave", new Move("Thunder Wave", "Electric", 0, 90, "Opponent Paralysis", 100, true, 0, 20, 20));
        moveList.put("Wild Charge", new Move ("Wild Charge", "Electric", 90, 100, "Recoil", 100, false, 0, 15, 15));
        moveList.put("Fusion Bolt", new Move("Fusion Bolt", "Electric", 100, 100, "None", 0, true, 0, 5, 5));
        // Flying-type Moves
        moveList.put("Gust", new Move("Gust", "Flying", 40, 100, "None", 0, true, 0, 35, 35));
        moveList.put("Peck", new Move("Peck", "Flying", 35, 100, "None", 0, false, 0, 35, 35));
        moveList.put("Drill Peck", new Move("Drill Peck", "Flying", 80, 100, "None", 0, false, 0, 20, 20));
        moveList.put("Wing Attack", new Move("Wing Attack", "Flying", 60, 100, "None", 0, false, 0, 35, 35));
        moveList.put("Brave Bird", new Move("Brave Bird", "Flying", 120, 100, "Recoil", 100, false, 0, 10, 10));
        moveList.put("Dragon Ascent", new Move("Dragon Ascent", "Flying", 120, 100, "Own Defense -1 & SpDef -1", 100, false, 0, 5, 5));
        moveList.put("Aerial Ace", new Move("Aerial Ace", "Flying", 60, 100, "None", 0, false, 0, 20, 20));
        moveList.put("Fly", new Move("Fly", "Flying", 95, 95, "Wait", 100, false, 0, 5, 5));
        moveList.put("Air Slash", new Move("Air Slash", "Flying", 75, 95, "Opponent Flinch", 30, true, 0, 15, 15));
        moveList.put("Aeroblast", new Move("Aeroblast", "Flying", 100, 95, "Own SpAtk +1", 10, true, 0, 5, 5));
        moveList.put("Tailwind", new Move("Tailwind", "Flying", 0, 100, "Own Speed +2", 100, true, 0, 15, 15));

        // Bug-type Moves
        moveList.put("String Shot", new Move("String Shot", "Bug", 0, 95, "Opponent Speed -2", 100, false, 0, 20, 20));
        moveList.put("Bug Bite", new Move("Bug Bite", "Bug", 60, 100, "None", 0, false, 0, 20, 20));
        moveList.put("X-Scissor", new Move("X-Scissor", "Bug", 80, 100, "None", 0, false, 0, 15, 15));
        moveList.put("Megahorn", new Move("Megahorn", "Bug", 120, 85, "None", 0, false, 0, 10, 10));
        moveList.put("Fury Cutter", new Move("Fury Cutter", "Bug", 40, 100, "None", 0, false, 1, 20, 20));
        moveList.put("Bug Buzz", new Move("Bug Buzz", "Bug", 75, 100, "None", 0, true, 0, 10, 10));
        moveList.put("Twineedle", new Move("Twineedle", "Bug", 65, 95, "Opponent Poison", 0, false, 0, 20, 20));
        moveList.put("Leech Life", new Move("Leech Life", "Bug", 60, 100, "Heal Half Hp Dealt", 100, false, 0, 10, 10));

        // Poison-type Moves
        moveList.put("Poison Sting", new Move("Poison Sting", "Poison", 15, 100, "Opponent Poison", 95, false, 0, 35, 35));
        moveList.put("Poison Jab", new Move("Poison Jab", "Poison", 80, 100, "Opponent Poison", 10, false, 0, 20, 20));
        moveList.put("Poison Fang", new Move("Poison Fang", "Poison", 50, 100, "Opponent Poison", 50, false, 0, 15, 15));
        moveList.put("Acid", new Move("Acid", "Poison", 40, 100, "Opponent Poison", 25, true, 0, 30, 30));
        moveList.put("Sludge Bomb", new Move("Sludge Bomb", "Poison", 90, 100, "None", 0, true, 0, 15, 15));
        moveList.put("Poison Powder", new Move("Poison Powder", "Poison", 20, 100, "Opponent Poison", 75, true, 0, 35, 35));
        moveList.put("Toxic", new Move("Toxic", "Poison", 0, 90, "Opponent Poison", 100, true, 0, 10, 10));

        // Ground-type Moves
        moveList.put("Earthquake", new Move("Earthquake", "Ground", 100, 100, "None", 0, false, 0, 10, 10));
        moveList.put("High Horsepower", new Move("High Horsepower", "Ground", 95, 95, "None", 0, false, 0, 10, 10));
        moveList.put("Bulldoze", new Move("Bulldoze", "Ground", 60, 100, "Opponent Speed -1", 100, false, 0, 20, 20));
        moveList.put("Drill Run", new Move("Drill Run", "Ground", 80, 95, "None", 0, false, 0, 10, 10));
        moveList.put("Earth Power", new Move("Earth Power", "Ground", 80, 100, "None", 0, true, 0, 10, 10));
        moveList.put("Magnitude", new Move("Magnitude", "Ground", 50, 100, "None", 0, false, 0, 30, 30));
        moveList.put("Sand Tomb", new Move("Sand Tomb", "Ground", 50, 85, "None", 100, false, 0, 15, 15));
        moveList.put("Dig", new Move("Dig", "Ground", 80, 100, "Wait", 0, false, 0, 10, 10));
        moveList.put("Mud-Slap", new Move("Mud-Slap", "Ground", 40, 100, "Opponent Speed -1", 0, false, 0, 20, 20));
        moveList.put("Bone Club", new Move("Bone Club", "Ground", 65, 85, "Opponent Flinch", 10, false, 0, 20, 20));

        // Psychic-type Moves
        moveList.put("Confusion", new Move("Confusion", "Psychic", 50, 100, "None", 0, true, 0, 25, 25));
        moveList.put("Psybeam", new Move("Psybeam", "Psychic", 65, 100, "None", 0, true, 0, 20, 20));
        moveList.put("Zen Headbutt", new Move("Zen Headbutt", "Psychic", 80, 90, "Opponent Flinch", 20, false, 0, 15, 15));
        moveList.put("Psychic", new Move("Psychic", "Psychic", 90, 100, "None", 0, true, 0, 10, 10));
        moveList.put("Luster Purge", new Move("Luster Purge", "Psychic", 95, 100, "Opponent SpDef -1", 50, true, 0, 5, 5));
        moveList.put("Hypnosis", new Move("Hypnosis", "Psychic", 0, 60, "Opponent Sleep", 100, true, 0, 20, 20));
        moveList.put("Rest", new Move("Rest", "Psychic", 0, 100, "Fall Asleep & Heal to Max Hp", 100, true, 0, 20, 20));
        moveList.put("Trick Room", new Move("Trick Room", "Psychic", 0, 100, "Slower Pokemon Move Faster", 100, true, -7, 5, 5));
        moveList.put("Psycho Boost", new Move("Psycho Boost", "Psychic", 140, 90, "Own SpAtk -2", 100, true, 0, 5, 5));

        // Fighting-type Moves
        moveList.put("Karate Chop", new Move("Karate Chop", "Fighting", 50, 100, "None", 0, false, 0, 25, 25));
        moveList.put("Low Kick", new Move("Low Kick", "Fighting", 70, 100, "None", 0, false, 0, 20, 20));
        moveList.put("Brick Break", new Move("Brick Break", "Fighting", 75, 100, "None", 0, false, 0, 15, 15));
        moveList.put("Aura Sphere", new Move("Aura Sphere", "Fighting", 80, 100, "None", 0, true, 0, 20, 20));
        moveList.put("Close Combat", new Move("Close Combat", "Fighting", 120, 100, "Own Defense -1 & SpDef -1", 100, false, 0, 5, 5));
        moveList.put("Mach Punch", new Move("Mach Punch", "Fighting", 40, 100, "None", 0, false, 0, 30, 30));
        moveList.put("Power-Up Punch", new Move("Power-Up Punch", "Fighting", 40, 100, "Own Attack +1", 100, false, 0, 20, 20));
        moveList.put("Bulk Up", new Move("Bulk Up", "Fighting", 0, 100, "Own Attack +1 & Defense +1", 100, true, 0, 20, 20));

        // Rock-type Moves
        moveList.put("Rock Throw", new Move("Rock Throw", "Rock", 50, 90, "None", 0, false, 0, 15, 15));
        moveList.put("Rock Slide", new Move("Rock Slide", "Rock", 75, 90, "Opponent Flinch", 10, false, 0, 10, 10));
        moveList.put("Stone Edge", new Move("Stone Edge", "Rock", 100, 80, "None", 0, false, 0, 5, 5));
        moveList.put("Ancient Power", new Move("Ancient Power", "Rock", 60, 100, "None", 0, true, 0, 10, 10));

        // Ice-type Moves
        moveList.put("Ice Shard", new Move("Ice Shard", "Ice", 40, 100, "None", 0, true, 0, 35, 35));
        moveList.put("Ice Fang", new Move("Ice Fang", "Ice", 65, 95, "None", 0, false, 0, 35, 35));
        moveList.put("Ice Punch", new Move("Ice Punch", "Ice", 75, 100, "None", 0, false, 0, 20, 20));
        moveList.put("Icicle Crash", new Move("Icicle Crash", "Ice", 85, 90, "Opponent Flinch", 30, false, 0, 10, 10));
        moveList.put("Ice Beam", new Move("Ice Beam", "Ice", 90, 100, "None", 0, true, 0, 15, 15));
        moveList.put("Blizzard", new Move("Blizzard", "Ice", 110, 70, "None", 0, true, 0, 5, 5));
        moveList.put("Powder Snow", new Move("Powder Snow", "Ice", 40, 100, "None", 0, false, 0, 20, 20));
        moveList.put("Snowy Day", new Move("Snowy Day", "Ice", 0, 100, "Snowy", 100, false, 0, 10, 10));

        // Ghost-type Moves
        moveList.put("Lick", new Move("Lick", "Ghost", 30, 100, "None", 0, false, 0, 35, 35));
        moveList.put("Shadow Punch", new Move("Shadow Punch", "Ghost", 60, 100, "None", 0, false, 0, 20, 20));
        moveList.put("Shadow Claw", new Move("Shadow Claw", "Ghost", 70, 100, "None", 0, false, 0, 20, 20));
        moveList.put("Shadow Ball", new Move("Shadow Ball", "Ghost", 80, 100, "None", 0, true, 0, 15, 15));
        moveList.put("Night Shade", new Move("Night Shade", "Ghost", 50, 100, "None", 0, true, 0, 25, 25));
        moveList.put("Shadow Force", new Move("Shadow Force", "Ghost", 120, 100, "Wait", 100, false, 0, 5, 5));
        moveList.put("Shadow Sneak", new Move("Shadow Sneak", "Ghost", 40, 100, "None", 0, false, 1, 30, 30));
        // Dragon-type Moves
        moveList.put("Dragon Rage", new Move("Dragon Rage", "Dragon", 50, 100, "None", 0, false, 0, 30, 30));
        moveList.put("Dragon Breath", new Move("Dragon Breath", "Dragon", 60, 100, "Opponent Paralysis", 30, true, 0, 20, 20));
        moveList.put("Dragon Claw", new Move("Dragon Claw", "Dragon", 80, 100, "None", 0, false, 0, 15, 15));
        moveList.put("Dragon Darts", new Move("Dragon Darts", "Dragon", 100, 100, "None", 0, false, 0, 10, 10));
        moveList.put("Dragon Pulse", new Move("Dragon Pulse", "Dragon", 85, 100, "None", 0, true, 0, 15, 15));
        moveList.put("Spacial Rend", new Move("Spacial Rend", "Dragon", 100, 95, "Own SpAtk +1", 10, true, 0, 5, 5));
        moveList.put("Outrage", new Move("Outrage", "Dragon", 120, 100, "Recoil", 30, false, 0, 10, 10));
        moveList.put("Draco Meteor", new Move("Draco Meteor", "Dragon", 130, 90, "Own SpAtk -2", 100, true, 0, 5, 5));

        // Dark-type Moves
        moveList.put("Bite", new Move("Bite", "Dark", 60, 100, "None", 0, false, 0, 30, 30));
        moveList.put("Crunch", new Move("Crunch", "Dark", 80, 100, "None", 0, false, 0, 25, 25));
        moveList.put("Throat Chop", new Move("Throat Chop", "Dark", 80, 100, "None", 0, false, 0, 25, 25));
        moveList.put("Foul Play", new Move("Foul Play", "Dark", 95, 100, "None", 0, false, 0, 20, 20));
        moveList.put("Pursuit", new Move("Pursuit", "Dark", 40, 100, "None", 0, false, 0, 35, 35));
        moveList.put("Snarl", new Move("Snarl", "Dark", 55, 95, "Opponent SpAtk -1", 100, true, 0, 30, 30));
        moveList.put("Dark Pulse", new Move("Dark Pulse", "Dark", 80, 100, "Opponent Flinch", 20, true, 0, 15, 15));
        moveList.put("Parting Shot", new Move("Parting Shot", "Dark", 0, 100, "Opponent SpAtk -1 & Attack -1 & Switch", 100, false, 0, 20, 20));

        // Fairy-type Moves
        moveList.put("Moonblast", new Move("Moonblast", "Fairy", 95, 100, "Opponent SpAtk -1", 30, true, 0, 20, 20));
        moveList.put("Dazzling Gleam", new Move("Dazzling Gleam", "Fairy", 80, 100, "None", 0, true, 0, 25, 25));
        moveList.put("Play Rough", new Move("Play Rough", "Fairy", 90, 90, "Opponent Attack -1", 10, false, 0, 15, 15));
        moveList.put("Fairy Wind", new Move("Fairy Wind", "Fairy", 40, 100, "None", 0, true, 0, 30, 30));
        moveList.put("Charm", new Move("Charm", "Fairy", 0, 100, "Opponent Attack -2", 100, true, 0, 10, 10));

        // Steel-type Moves
        moveList.put("Metal Claw", new Move("Metal Claw", "Steel", 50, 95, "None", 0, false, 0, 30, 30));
        moveList.put("Bullet Punch", new Move("Bullet Punch", "Steel", 40, 100, "None", 0, false, 1, 30, 30));
        moveList.put("Meteor Mash", new Move("Meteor Mash", "Steel", 90, 90, "Own Attack +1", 20, false, 0, 10, 10));
        moveList.put("Iron Tail", new Move("Iron Tail", "Steel", 100, 75, "None", 0, false, 0, 15, 15));
        moveList.put("Iron Head", new Move("Iron Head", "Steel", 80, 100, "None", 0, false, 0, 10, 10));
        moveList.put("Flash Cannon", new Move("Flash Cannon", "Steel", 80, 100, "None", 0, true, 0, 10, 10));
        moveList.put("Steel Wing", new Move("Steel Wing", "Steel", 70, 90, "None", 0, false, 0, 25, 25));

        // Misc Stat-Boosting Moves
        moveList.put("Swords Dance", new Move("Swords Dance", "Normal", 0, 100, "Own Attack +2", 100, false, 0, 20, 20));
        moveList.put("Iron Defense", new Move("Iron Defense", "Steel", 0, 100, "Own Defense +1", 100, false, 0, 25, 25));
        moveList.put("Calm Mind", new Move("Calm Mind", "Psychic", 0, 100, "Own SpAtk +1 & SpDef +1", 100, false, 0, 20, 20));
        moveList.put("Dragon Dance", new Move("Dragon Dance", "Dragon", 0, 100, "Own Attack +1 & Speed +1", 100, true, 0, 15, 15));
        moveList.put("Nasty Plot", new Move("Nasty Plot", "Dark", 0, 100, "Own SpAtk +2", 100, true, 0, 10, 10));

        // Default
        moveList.put("Move Not Found", new Move("Move Not Found", "Normal", 0, 0, "None", 0, false, 0, 10, 10));
    }

    // Method to get a move from the list
    public static Move getMove(String moveName) {
        return moveList.getOrDefault(moveName, moveList.get("Move Not Found"));
    }
}
