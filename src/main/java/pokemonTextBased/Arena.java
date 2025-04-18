package pokemonTextBased;

/*this class should handle:
- light screens/reflects (those moves need to be added)
- Tailwind (needs to be implemented properly)
- Trick room
 */
public class Arena {
     // Battle state
    public boolean isSimulation;
    public int turnNum;
    public String weather;
    public int turnWeatherEnds;
    public boolean trickRoomIsUp;
    public int turnTrickRoomEnds;
    public boolean isCaught = false;
    public Pokemon[] p; //party
    public Pokemon[] fp; //foe party
    public Trainer trainer;
    public Engine playerEngine;
    public Engine foeEngine;

    public Arena(Pokemon[] p, Pokemon[] fp, Engine playerEngine, Engine foeEngine) {
        // Initialize battle state
        this.turnNum = 1;
        this.weather = "None";
        this.turnWeatherEnds = 0;
        this.trickRoomIsUp = false;
        this.turnTrickRoomEnds = 0;
        this.p = p;
        this.fp = fp;
        this.playerEngine = playerEngine;
        this.foeEngine = foeEngine;
    }
    public Arena(Pokemon[] p, Pokemon[] fp, Trainer trainer, Engine playerEngine, Engine foeEngine) {
        // Initialize battle state
        this.turnNum = 1;
        this.weather = "None";
        this.turnWeatherEnds = 0;
        this.trickRoomIsUp = false;
        this.turnTrickRoomEnds = 0;
        this.p = p;
        this.fp = fp;
        this.trainer = trainer;
        this.playerEngine = playerEngine;
        this.foeEngine = foeEngine;
    }
    public Arena(Pokemon[] p, Trainer trainer, Engine playerEngine, Engine foeEngine) {
        // Initialize battle state
        this.turnNum = 1;
        this.weather = "None";
        this.turnWeatherEnds = 0;
        this.trickRoomIsUp = false;
        this.turnTrickRoomEnds = 0;
        this.p = p;
        this.fp = new Pokemon[trainer.party.length];
        for (int i = 0; i < trainer.party.length; i++) {
            if (trainer.party[i] != null) {
                this.fp[i] = trainer.party[i].clone();
            }
        }
        this.trainer = trainer;
        this.playerEngine = playerEngine;
        this.foeEngine = foeEngine;

    }

    public boolean isFacingGymLeader() {
        return User.badgesEarned.containsKey(this.trainer.title);
    }

    public boolean isFacingMajorTrainer() {
        return User.majorTrainersBeaten.containsKey(this.trainer.title);
    }
    public void resetArena(Arena arena) {
        this.turnNum = 1;
        this.weather = "None";
    }

    // Getters, setters, & other shenanigans for battle state
    public void setTurnWeatherEnds(Arena arena) {this.turnWeatherEnds = arena.getTurnNum() + 4;}
    public int getTurnNum() {
        return turnNum;
    }
    public void incrementTurns() {
        turnNum++;
    }
    public String getWeather() {
        return weather;
    }
    public void setUpWeather(String weather) {
        this.weather = weather;
        setTurnWeatherEnds(this);
    }
    public void setWeather(String weather) {
        this.weather = weather;
    }
    public void setUpTrickRoom() {
        this.trickRoomIsUp = true;
        this.turnTrickRoomEnds = this.getTurnNum() + 4;
    }
    public void setTrickRoom(boolean set) {
        this.trickRoomIsUp = set;
    }
    public void setTurnTrickRoomEnds(int turnTrickRoomEnds) {
        this.turnTrickRoomEnds = turnTrickRoomEnds;
    }
}
