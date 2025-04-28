package pokemonTextBased;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Trainer {
    private static final Random rand = new Random();
    public enum Title {
        //Leaders
        PEWTER_GYM_LEADER("Pewter City Gym Leader", "Brock", 2000, true),
        CERULEAN_GYM_LEADER("Cerulean City Gym Leader", "Misty", 2000, true),
        VERMILLION_GYM_LEADER("Vermilion City Gym Leader", "Lt. Surge", 2000, true),
        CELADON_GYM_LEADER("Celadon City Gym Leader", "Erika", 2000, true),
        FUCHSIA_GYM_LEADER("Fuchsia City Gym Leader", "Koga", 2000, true),
        SAFFRON_GYM_LEADER("Saffron City Gym Leader", "Sabrina", 2000, true),
        CINNABAR_GYM_LEADER("Cinnabar Island Gym Leader", "Blaine", 2000, true),
        VIRIDIAN_GYM_LEADER("Viridian City Gym Leader", "Blue", 2000, true),
        ROCKETOPOLIS_GYM_LEADER("Rocketopolis Gym Leader", "Giovanni", 2000, true),
        VAUGHAN_DISTRICT_GYM_LEADER("Mayor", "Vaughan", 2000, true),
        //E4
        ELITE_FOUR_LORELEI("Elite Four", "Lorelei", 2000, true),
        ELITE_FOUR_BRUNO("Elite Four", "Bruno", 2000, true),
        ELITE_FOUR_AGATHA("Elite Four", "Agatha", 2000, true),
        ELITE_FOUR_LANCE("Elite Four", "Lance", 2000, true),
        CHAMPION("Champion", "Red", 5000, true),
        //Reg trainers
        BUG_CATCHER("Bug Catcher", "m", 50, false),
        LASS("Lass", "f", 50, false),
        YOUNGSTER("Youngster", "m", 50, false),
        HIKER("Hiker", "m", 50, false),
        SAILOR("Sailor", "m", 50, false),
        POKEMANIAC("Pokemaniac", "m", 50, false),
        SUPER_NERD("Super Nerd", "m", 50, false),
        ENGINEER("Engineer", "m", 50, false),
        FISHERMAN("Fisherman", "m", 50, false),
        SWIMMER("Swimmer", "m", 50, false),
        SWIMMER_F("Swimmer", "f", 50, false),
        CUE_BALL("Cue Ball", "m", 50, false),
        GAMBLER("Gambler", "m", 200, false),
        BEAUTY("Beauty", "f", 50, false),
        PSYCHIC("Psychic", "m", 50, false),
        ROCKER("Rocker", "m", 50, false),
        JUGGLER("Juggler", "m", 50, false),
        TAMER("Tamer", "m", 50, false),
        BIRD_KEEPER("Bird Keeper", "m", 50, false),
        BLACKBELT("Blackbelt", "m", 50, false),
        SCIENTIST("Scientist", "m", 100, false),
        CHANNELER("Channeler", "m", 50, false),
        COOLTRAINER("Cooltrainer", "m", 50, false),
        GENTLEMAN("Gentleman", "m", 50, false),
        SIGHTSEER("Sightseer", "m", 50, false),
        BIKER("Biker", "m", 50, false),
        BURGLAR("Burglar", "m", 100, false),
        FIREFIGHTER("Firefighter", "m", 50, false),
        SOLDIER("Soldier", "m", 50, false),
        COOLTRAINER_F("Cooltrainer", "f", 50, false),
        SIGHTSEER_F("Sightseer", "f", 50, false),
        //Team Rocket
        GRUNT_L("Rocket Grunt", "m", 50, false),
        GRUNT_L_F("Rocket Grunt", "f", 50, false),
        GRUNT_M("Rocket Grunt", "m", 60, false),
        GRUNT_M_F("Rocket Grunt", "f", 60, false),
        GRUNT_H("Rocket Grunt", "m", 70, false),
        GRUNT_H_F("Rocket Grunt", "f", 70, false),
        //COLOSSEUM/SPECIAL TRAINERS
        COLOSSEUM_BATTLER("Colosseum Trainer", "m", 50, true),
        COLOSSEUM_BATTLER_F("Colosseum Trainer", "f", 50, true),
        BATTLE_SPECIALIST("Battle Specialist", "m", 50, true),
        BATTLE_SPECIALIST_F("Battle Specialist", "f", 50, true),
        //COMPETITIVE TRAINERS
        C_TRAINER_1("Battle Expert", "m", 50, true),
        C_TRAINER_2("Battle Expert", "m", 50, true),
        C_TRAINER_3("Battle Expert", "m", 50, true),
        C_TRAINER_4("Battle Expert", "m", 50, true),
        C_TRAINER_5("Battle Expert", "m", 50, true),
        C_TRAINER_6("Battle Expert", "m", 50, true),
        C_TRAINER_7("Battle Expert", "m", 50, true),
        C_TRAINER_8("Battle Expert", "m", 50, true),
        C_TRAINER_9("Battle Expert", "m", 50, true),
        C_TRAINER_10("Battle Expert", "m", 50, true),
        C_TRAINER_11("Battle Expert", "m", 50, true),
        C_TRAINER_12("Battle Expert", "m", 50, true),
        C_TRAINER_13("Battle Expert", "m", 50, true),
        C_TRAINER_14("Battle Expert", "m", 50, true),
        C_TRAINER_15("Battle Expert", "m", 50, true),
        C_TRAINER_16("Battle Expert", "m", 50, true),
        C_TRAINER_17("Battle Expert", "m", 50, true),
        C_TRAINER_18("Battle Expert", "m", 50, true),
        C_TRAINER_19("Battle Expert", "m", 50, true),
        C_TRAINER_20("Battle Expert", "m", 50, true);


        private final String titleString;
        private final String name;
        private final int prize;
        private final boolean isMajorTrainer;

        Title(String titleString, String name, int prize, boolean isMajorTrainer) {
            this.titleString = titleString;
            this.name = name;
            this.prize = prize;
            this.isMajorTrainer = isMajorTrainer;
        }

        public String getTitleString() {
            return titleString;
        }
        public String getBadgeName() {
            return switch (this.name) {
                case "Brock" -> "Boulder Badge";
                case "Misty" -> "Cascade Badge";
                case "Lt. Surge" -> "Thunder Badge";
                case "Erika" -> "Rainbow Badge";
                case "Koga" -> "Soul Badge";
                case "Sabrina" -> "Marsh Badge";
                case "Blaine" -> "Volcano Badge";
                case "Blue" -> "Earth Badge";
                case "Giovanni" -> "Rocket Badge";
                case "Vaughan" -> "District Badge";
                default -> "Unknown Badge";
            };
        }
        public String getName() {
            return name;
        }
        public int getPrize() {
            return prize;
        }
    }
    public Title title;
    public String titleString;
    public String name;
    public Pokemon[] party;
    public int prize;
    public Trainer(Title title) {
        this.title = title;
        this.titleString = title.getTitleString();
        if(title.getName().equalsIgnoreCase("m")){
            this.name = getRandMaleName();
        }
        else if(title.getName().equalsIgnoreCase("f")){
            this.name = getRandFemaleName();
        }
        else this.name = title.getName();
        this.party = getPartyFromTitle();
        this.prize = title.getPrize();
    }
    public Trainer(Title title, Pokemon[] party) {
        this.title = title;
        this.titleString = title.getTitleString();
        if(title.getName().equalsIgnoreCase("m")){
            this.name = getRandMaleName();
        }
        else if(title.getName().equalsIgnoreCase("f")){
            this.name = getRandFemaleName();
        }
        else this.name = title.getName();
        this.party = party;
        this.prize = title.getPrize();
    }
    public static int checkEffectOfRep() {
        if(User.reputation < 0) {
            return (Math.min(-User.reputation / 50, 15));
        }
        return 0;
    }
    public static int getTrainerLevel(Title title) {
        if (title.isMajorTrainer) return Math.min(User.checkLevelCap() + checkEffectOfRep(), 100);
        else return Math.min(Party.getAvgPLvl() + rand.nextInt(-5, 2) + checkEffectOfRep(), 100);
    }
    public static HashMap<Title, Pokemon[]> parties = new HashMap<>();
    static {
        // Gym Leaders
        parties.put(Title.PEWTER_GYM_LEADER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Geodude"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Onix"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Graveler"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.CERULEAN_GYM_LEADER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Staryu"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Seel"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Goldeen"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Starmie"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.VERMILLION_GYM_LEADER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Voltorb"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Electrode"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Electabuzz"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Pikachu"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Raichu"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.CELADON_GYM_LEADER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Tangela"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Weepinbell"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Victreebel"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Gloom"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Vileplume"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.FUCHSIA_GYM_LEADER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Koffing"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Muk"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Weezing"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Koffing"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Muk"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Venusaur"), 20, Pokemon.getShinyOdds()),
        });

        parties.put(Title.SAFFRON_GYM_LEADER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Abra"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Venomoth"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Hypno"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Kadabra"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Drowzee"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Alakazam"), 20, Pokemon.getShinyOdds()),
        });

        parties.put(Title.CINNABAR_GYM_LEADER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Growlithe"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Ponyta"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Rapidash"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Arcanine"), 20, Pokemon.getShinyOdds()),
        });

        parties.put(Title.VIRIDIAN_GYM_LEADER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Pidgeot"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Alakazam"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Rhydon"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Raticate"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Exeggutor"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Gyarados"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.ROCKETOPOLIS_GYM_LEADER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Persian"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Dragonite"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Moltres"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Zapdos"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Snorlax"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Mewtwo"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.VAUGHAN_DISTRICT_GYM_LEADER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Garchomp"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Dragapult"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Tyranitar"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Togekiss"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Umbreon"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Dragonite"), 20, Pokemon.getShinyOdds())
        }); //incomplete
        // Elite Four Parties
        parties.put(Title.ELITE_FOUR_LORELEI, new Pokemon[]{
                new Pokemon(Species.getSpecies("Dewgong"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Cloyster"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Slowbro"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Mamoswine"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Lapras"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Articuno"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.ELITE_FOUR_BRUNO, new Pokemon[]{
                new Pokemon(Species.getSpecies("Rhyperior"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Hitmonchan"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Hitmonlee"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Hitmontop"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Golem"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Machamp"), 20, Pokemon.getShinyOdds())

        });

        parties.put(Title.ELITE_FOUR_AGATHA, new Pokemon[]{
                new Pokemon(Species.getSpecies("Gengar"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Golbat"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Haunter"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Dragapult"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Arbok"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Zoroark"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.ELITE_FOUR_LANCE, new Pokemon[]{
                new Pokemon(Species.getSpecies("Gyarados"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Dragonair"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Salamence"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Charizard"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Aerodactyl"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Dragonite"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.CHAMPION, new Pokemon[]{
                new Pokemon(Species.getSpecies("Excadrill"), 20, true),
                new Pokemon(Species.getSpecies("Dragonite"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Incineroar"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Togekiss"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Tyranitar"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Charizard"), 20, Pokemon.getShinyOdds())
        });
// Regular Trainers
        parties.put(Title.BUG_CATCHER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Caterpie"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Weedle"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.LASS, new Pokemon[]{
                new Pokemon(Species.getSpecies("Pidgey"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("NidoranF"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Growlithe"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.YOUNGSTER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Rattata"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Ekans"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("NidoranM"), 20, Pokemon.getShinyOdds()),
        });

        parties.put(Title.HIKER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Geodude"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Machop"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Graveler"), 20, Pokemon.getShinyOdds()),
        });

        parties.put(Title.SAILOR, new Pokemon[]{
                new Pokemon(Species.getSpecies("Poliwhirl"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Tentacool"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.POKEMANIAC, new Pokemon[]{
                new Pokemon(Species.getSpecies("Slowpoke"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Lickitung"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.SUPER_NERD, new Pokemon[]{
                new Pokemon(Species.getSpecies("Voltorb"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Magnemite"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Magneton"), 20, Pokemon.getShinyOdds()),
        });

        parties.put(Title.ENGINEER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Magneton"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Electrode"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.FISHERMAN, new Pokemon[]{
                new Pokemon(Species.getSpecies("Gyarados"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Magikarp"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Seel"), 20, Pokemon.getShinyOdds()),
        });

        parties.put(Title.SWIMMER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Seadra"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Starmie"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Magikarp"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Jynx"), 20, Pokemon.getShinyOdds()),
        });

        parties.put(Title.SWIMMER_F, new Pokemon[]{
                new Pokemon(Species.getSpecies("Goldeen"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Seaking"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Starmie"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Lapras"), 20, Pokemon.getShinyOdds()),
        });

        parties.put(Title.CUE_BALL, new Pokemon[]{
                new Pokemon(Species.getSpecies("Machoke"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Machamp"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Machop"), 20, Pokemon.getShinyOdds()),
        });

        parties.put(Title.GAMBLER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Growlithe"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Vulpix"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.BEAUTY, new Pokemon[]{
                new Pokemon(Species.getSpecies("Arcanine"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Vileplume"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.PSYCHIC, new Pokemon[]{
                new Pokemon(Species.getSpecies("Abra"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Drowzee"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Hypno"), 20, Pokemon.getShinyOdds()),
        });

        parties.put(Title.ROCKER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Voltorb"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Electrode"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.JUGGLER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Voltorb"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Koffing"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.TAMER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Arbok"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Sandslash"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.BIRD_KEEPER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Pidgey"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Spearow"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Fearow"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Pidgeotto"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Pidgeot"), 20, Pokemon.getShinyOdds()),
        });

        parties.put(Title.BLACKBELT, new Pokemon[]{
                new Pokemon(Species.getSpecies("Machamp"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Machoke"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Primeape"), 20, Pokemon.getShinyOdds()),
        });

        parties.put(Title.SCIENTIST, new Pokemon[]{
                new Pokemon(Species.getSpecies("Magnemite"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Voltorb"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.CHANNELER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Gastly"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Haunter"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Haunter"), 20, Pokemon.getShinyOdds()),
        });

        parties.put(Title.COOLTRAINER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Pidgeot"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Rhydon"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.GENTLEMAN, new Pokemon[]{
                new Pokemon(Species.getSpecies("Growlithe"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Ponyta"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.SIGHTSEER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Pidgeotto"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Raticate"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.BIKER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Koffing"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Grimer"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.BURGLAR, new Pokemon[]{
                new Pokemon(Species.getSpecies("Growlithe"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Vulpix"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Meowth"), 20, Pokemon.getShinyOdds()),
        });

        parties.put(Title.FIREFIGHTER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Growlithe"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Ponyta"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Rapidash"), 20, Pokemon.getShinyOdds()),
        });

        parties.put(Title.SOLDIER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Machoke"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Electabuzz"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Magmar"), 20, Pokemon.getShinyOdds()),
        });

        parties.put(Title.COOLTRAINER_F, new Pokemon[]{
                new Pokemon(Species.getSpecies("Nidoqueen"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Persian"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Arcanine"), 20, Pokemon.getShinyOdds()),
        });

        parties.put(Title.SIGHTSEER_F, new Pokemon[]{
                new Pokemon(Species.getSpecies("Pidgeot"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Nidorina"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.GRUNT_L, new Pokemon[]{
                new Pokemon(Species.getSpecies("Ekans"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Grimer"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Dratini"), 20, Pokemon.getShinyOdds()),
        });

        parties.put(Title.GRUNT_L_F, new Pokemon[]{
                new Pokemon(Species.getSpecies("Rattata"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Grimer"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Koffing"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.GRUNT_M, new Pokemon[]{
                new Pokemon(Species.getSpecies("Muk"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Weezing"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Fearow"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.GRUNT_M_F, new Pokemon[]{
                new Pokemon(Species.getSpecies("Raticate"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Muk"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Arbok"), 20, Pokemon.getShinyOdds())
        });

        parties.put(Title.GRUNT_H, new Pokemon[]{
                new Pokemon(Species.getSpecies("Muk"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Meowth"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Dragonair"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Raticate"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Arbok"), 20, Pokemon.getShinyOdds()),
        });

        parties.put(Title.GRUNT_H_F, new Pokemon[]{
                new Pokemon(Species.getSpecies("Meowth"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Dragonair"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Arbok"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Weezing"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Raticate"), 20, Pokemon.getShinyOdds()),
        });
        //COMPETITIVE TRAINERS
        parties.put(Title.C_TRAINER_1, new Pokemon[]{
                new Pokemon(Species.getSpecies("Dragonite"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Corviknight"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Sylveon"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Charizard"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Amoonguss"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Venusaur"), 20, Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_2, new Pokemon[]{
                new Pokemon(Species.getSpecies("Garchomp"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Metagross"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Zoroark"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Incineroar"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Moltres"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Tyranitar"), 20, Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_3, new Pokemon[]{
                new Pokemon(Species.getSpecies("Arcanine"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Charizard"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Excadrill"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Incineroar"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Blaziken"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Sceptile"), 20, Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_4, new Pokemon[]{
                new Pokemon(Species.getSpecies("Gengar"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Togekiss"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Mamoswine"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Incineroar"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Lucario"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Swampert"), 20, Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_5, new Pokemon[]{
                new Pokemon(Species.getSpecies("Togekiss"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Aerodactyl"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Houndoom"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Dragapult"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Feraligatr"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Salamence"), 20, Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_6, new Pokemon[]{
                new Pokemon(Species.getSpecies("Metagross"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Salamence"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Gardevoir"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Machamp"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Snorlax"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Scizor"), 20, Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_7, new Pokemon[]{
                new Pokemon(Species.getSpecies("Persian"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Metagross"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Snorlax"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Zoroark"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Tyranitar"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Pidgeot"), 20, Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_8, new Pokemon[]{
                new Pokemon(Species.getSpecies("Corvinight"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Dragapult"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Salamence"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Dragonite"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Metagross"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Tyranitar"), 20, Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_9, new Pokemon[]{
                new Pokemon(Species.getSpecies("Lugia"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Togekiss"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Gardevoir"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Arcanine"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Pidgeot"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Scyther"), 20, Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_10, new Pokemon[]{
                new Pokemon(Species.getSpecies("Kyogre"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Gyarados"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Swampert"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Charizard"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Blastoise"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Vaporeon"), 20, Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_11, new Pokemon[]{
                new Pokemon(Species.getSpecies("Groudon"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Amoonguss"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Incineroar"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Lucario"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Tyranitar"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Flareon"), 20, Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_12, new Pokemon[]{
                new Pokemon(Species.getSpecies("Butterfree"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Raichu"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Incineroar"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Lucario"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Tyranitar"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Flareon"), 20, Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_13, new Pokemon[]{
                new Pokemon(Species.getSpecies("Mew"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Jirachi"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Hitmontop"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Lucario"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Metagross"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Gardevoir"), 20, Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_14, new Pokemon[]{
                new Pokemon(Species.getSpecies("Zekrom"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Raichu"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Pikachu"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Jolteon"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Umbreon"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Mamoswine"), 20, Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_15, new Pokemon[]{
                new Pokemon(Species.getSpecies("Palkia"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Hypno"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Gengar"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Espeon"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Umbreon"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Sylveon"), 20, Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_16, new Pokemon[]{
                new Pokemon(Species.getSpecies("Glaceon"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Mamoswine"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Reshiram"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Victini"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Leafon"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Charizard"), 20, Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_17, new Pokemon[]{
                new Pokemon(Species.getSpecies("Swampert"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Blaziken"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Sceptile"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Rayquaza"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Kyogre"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Groudon"), 20, Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_18, new Pokemon[]{
                new Pokemon(Species.getSpecies("Blastoise"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Gyarados"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Swampert"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Raichu"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Amoonguss"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Incineroar"), 20, Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_19, new Pokemon[]{
                new Pokemon(Species.getSpecies("Meganium"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Typhlosion"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Hitmontop"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Raichu"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Amoonguss"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Arcanine"), 20, Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_20, new Pokemon[]{
                new Pokemon(Species.getSpecies("Entei"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Suicune"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Raikou"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Ho-Oh"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Alakazam"), 20, Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Primeape"), 20, Pokemon.getShinyOdds()),
        });

    } //parties
    public static ArrayList<Title> competitiveParties = new ArrayList<>();
    static {
        competitiveParties.add(Title.VAUGHAN_DISTRICT_GYM_LEADER);
        competitiveParties.add(Title.VIRIDIAN_GYM_LEADER);
        competitiveParties.add(Title.ROCKETOPOLIS_GYM_LEADER);
        competitiveParties.add(Title.CHAMPION);
        competitiveParties.add(Title.C_TRAINER_1);
        competitiveParties.add(Title.C_TRAINER_2);
        competitiveParties.add(Title.C_TRAINER_3);
        competitiveParties.add(Title.C_TRAINER_4);
        competitiveParties.add(Title.C_TRAINER_5);
        competitiveParties.add(Title.C_TRAINER_6);
        competitiveParties.add(Title.C_TRAINER_7);
        competitiveParties.add(Title.C_TRAINER_8);
        competitiveParties.add(Title.C_TRAINER_9);
        competitiveParties.add(Title.C_TRAINER_10);
        competitiveParties.add(Title.C_TRAINER_11);
        competitiveParties.add(Title.C_TRAINER_12);
        competitiveParties.add(Title.C_TRAINER_13);
        competitiveParties.add(Title.C_TRAINER_14);
        competitiveParties.add(Title.C_TRAINER_15);
        competitiveParties.add(Title.C_TRAINER_16);
        competitiveParties.add(Title.C_TRAINER_17);
        competitiveParties.add(Title.C_TRAINER_18);
        competitiveParties.add(Title.C_TRAINER_19);
        competitiveParties.add(Title.C_TRAINER_20);
    }
    public static ArrayList<Title> testParties = new ArrayList<>();
    static {
        testParties.add(Title.C_TRAINER_1);
        testParties.add(Title.C_TRAINER_3);
    }
    public Pokemon[] getPartyFromTitle()
    {
        Pokemon[] party = parties.getOrDefault(this.title, new Pokemon[]{
                new Pokemon(Species.getSpecies("Pidgey"), 20, Pokemon.getShinyOdds())
        });

        // Create a copy of the array to shuffle (to avoid modifying the original)
        Pokemon[] shuffledParty = new Pokemon[party.length];

        for (int i = 0; i < party.length; i++) {
            shuffledParty[i] = party[i].clone();
            shuffledParty[i].setLevel(getTrainerLevel(this.title));
            shuffledParty[i].adjustPokemonStatsAfterLevelUp();
        }

        // Shuffle the party order
        shuffleArray(shuffledParty);

        return shuffledParty;
    }
    public Pokemon[] cloneParty() {
        Pokemon[] clonedParty = new Pokemon[party.length];
        for (int i = 0; i < party.length; i++) {
            if(party[i] != null) clonedParty[i] = party[i].clone();
        }
        return clonedParty;
    }
    private static void shuffleArray(Pokemon[] array) {
        Random rnd = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            Pokemon a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }
    public static String getRandMaleName() {
        String[] maleNames = {
                "James", "John", "Robert", "Michael", "William", "David", "Richard", "Joseph", "Thomas", "Charles",
                "Christopher", "Daniel", "Matthew", "Anthony", "Mark", "Donald", "Steven", "Paul", "Andrew", "Joshua",
                "Kenneth", "Kevin", "Brian", "George", "Edward", "Ronald", "Timothy", "Jason", "Jeffrey", "Ryan",
                "Jacob", "Gary", "Nicholas", "Eric", "Jonathan", "Stephen", "Larry", "Justin", "Scott", "Brandon",
                "Benjamin", "Samuel", "Frank", "Gregory", "Raymond", "Alexander", "Patrick", "Jack", "Dennis", "Jerry",
                "Tyler", "Aaron", "Jose", "Adam", "Henry", "Nathan", "Douglas", "Zach", "Peter", "Kyle",
                "Walter", "Ethan", "Jeremy", "Harold", "Keith", "Christian", "Roger", "Noah", "Gerald", "Carl",
                "Terry", "Sean", "Austin", "Arthur", "Lawrence", "Jesse", "Dylan", "Bryan", "Joe", "Jordan",
                "Billy", "Bruce", "Albert", "Willie", "Gabriel", "Logan", "Alan", "Juan", "Wayne", "Roy",
                "Ralph", "Randy", "Eugene", "Vincent", "Russell", "Elijah", "Louis", "Bobby", "Philip", "Johnny",
                "Mike", "John", "Steve", "Matt", "Eduardo", "Smithy", "Gene", "Eugene", "Todd", "Judas", "Joana", "Ricky",
                "Bobby", "Bob", "Mike", "Mikey", "Billy", "Riccardo", "Eugene", "Plinkton"
        };

        Random rand = new Random();
        int index = rand.nextInt(maleNames.length);
        return maleNames[index];
    }
    public static String getRandFemaleName() {
        String[] femaleNames = {
                "Mary", "Patricia", "Jen", "Linda", "Elizabeth", "Barbara", "Susan", "Jessica", "Sarah", "Karen",
                "Nancy", "Lisa", "Margaret", "Betty", "Sandra", "Ashley", "Dorothy", "Kimberly", "Emily", "Donna",
                "Michelle", "Carol", "Amanda", "Melissa", "Deborah", "Stephanie", "Rebecca", "Laura", "Sharon", "Cynthia",
                "Kathleen", "Amy", "Shirley", "Angela", "Helen", "Anna", "Brenda", "Pamela", "Nicole", "Samantha",
                "Katherine", "Emma", "Ruth", "Christine", "Catherine", "Debra", "Rachel", "Carolyn", "Janet", "Virginia",
                "Maria", "Heather", "Diane", "Julie", "Joyce", "Victoria", "Kelly", "Christina", "Lauren", "Joan",
                "Eve", "Olivia", "Judith", "Megan", "Cheryl", "Martha", "Andrea", "Frances", "Hannah", "Jacqueline",
                "Ann", "Gloria", "Jean", "Kathryn", "Alice", "Teresa", "Sara", "Janice", "Doris", "Madison", "Julia",
                "Grace", "Judy", "Abigail", "Marie", "Denise", "Beverly", "Amber", "Theresa", "Marilyn", "Danielle",
                "Diana", "Brittany", "Natalie", "Sophia", "Rose", "Isabella", "Alexis", "Kayla", "Charlotte", "Ava", "Liz",
                "Sassy", "Carla", "Carly", "Millie", "Sophia", "Sophie", "Jasmine", "Jasper", "Amber", "Britney", "Adriana",
                "Anna"
        };

        Random rand = new Random();
        int index = rand.nextInt(femaleNames.length);
        return femaleNames[index];
    }

    //procedurally generated trainers
    public static Trainer buildColosseumTrainer() {
        Pokemon[] trainerParty = getProcedurallyMadeParty(50);
        Title thisTitle = (rand.nextInt(0,2) == 0) ? Title.COLOSSEUM_BATTLER : Title.COLOSSEUM_BATTLER_F;
         return new Trainer(thisTitle, trainerParty);
    }
    public static Trainer buildATypeSpecialistTrainer(Species.Type type) {
        Pokemon[] trainerParty = getProcedurallyMadeParty(50, type);
        Title thisTitle = (rand.nextInt(0,2) == 0) ? Title.BATTLE_SPECIALIST : Title.BATTLE_SPECIALIST_F;
        return new Trainer(thisTitle, trainerParty);
    }
    //helpers for procedurally generated trainers
    public static Pokemon[] getProcedurallyMadeParty(int bSTBound) {
        Pokemon[] trainerParty = new Pokemon[Party.p.length];
        int thisBST = 0;
        for (int i = 0; i < Party.p.length; i++) {
            if(Party.p[i] == null) continue;
            thisBST = Party.p[i].getBST();
            trainerParty[i] = new Pokemon(getRandomSpeciesNearBST(thisBST, bSTBound), User.checkLevelCap());
        }
        return trainerParty;
    }
    private static Species getRandomSpeciesNearBST(int targetBST, int bSTBound) {
        int lowerBound = targetBST - bSTBound;
        int upperBound = targetBST + bSTBound;
        ArrayList<String> viableSpeciesChoices = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : Species.baseStatTotals.entrySet()) {
            int thisBST = entry.getValue();
            if(thisBST >= lowerBound && thisBST <= upperBound) {
                viableSpeciesChoices.add(entry.getKey());
            }
        }
        return Species.getSpecies(viableSpeciesChoices.get(rand.nextInt(0, viableSpeciesChoices.size())));
    }
    public static Pokemon[] getProcedurallyMadeParty(int bSTBound, Species.Type targetType) {
        Pokemon[] trainerParty = new Pokemon[Party.p.length];
        for (int i = 0; i < Party.p.length; i++) {
            if(Party.p[i] == null) continue;
            int thisBST = Party.p[i].getBST();
            trainerParty[i] = new Pokemon(getRandomSpeciesNearBST(thisBST, bSTBound, targetType), User.checkLevelCap());
        }
        return trainerParty;
    }
    private static Species getRandomSpeciesNearBST(int targetBST, int bSTBound, Species.Type targetType) {
        String targetTypeStr = targetType.getStr();
        int lowerBound = targetBST - (bSTBound + 40);
        int upperBound = targetBST + bSTBound;
        ArrayList<String> viableSpeciesChoices = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : Species.baseStatTotals.entrySet()) {
            Species thisSpecies = Species.getSpecies(entry.getKey());
            int thisBST = entry.getValue();
            if(thisBST >= lowerBound
                    && thisBST <= upperBound
                    && (thisSpecies.getType1().equals(targetTypeStr) || thisSpecies.getType2().equals(targetTypeStr))) {
                viableSpeciesChoices.add(entry.getKey());
            }
        }
        if (viableSpeciesChoices.isEmpty()) return getRandomSpeciesNearBST(targetBST, 50);
        else return Species.getSpecies(viableSpeciesChoices.get(rand.nextInt(0, viableSpeciesChoices.size())));
    } //make it take lower bound too
}
