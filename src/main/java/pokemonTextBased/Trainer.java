package pokemonTextBased;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Trainer {

    public enum Title {
        //Leaders
        PEWTER_GYM_LEADER("Pewter City Gym Leader", "Brock", 2000),
        CERULEAN_GYM_LEADER("Cerulean City Gym Leader", "Misty", 2000),
        VERMILLION_GYM_LEADER("Vermilion City Gym Leader", "Lt. Surge", 2000),
        CELADON_GYM_LEADER("Celadon City Gym Leader", "Erika", 2000),
        FUCHSIA_GYM_LEADER("Fuchsia City Gym Leader", "Koga", 2000),
        SAFFRON_GYM_LEADER("Saffron City Gym Leader", "Sabrina", 2000),
        CINNABAR_GYM_LEADER("Cinnabar Island Gym Leader", "Blaine", 2000),
        VIRIDIAN_GYM_LEADER("Viridian City Gym Leader", "Blue", 2000),
        ROCKETOPOLIS_GYM_LEADER("Rocketopolis Gym Leader", "Giovanni", 2000),
        VAUGHAN_DISTRICT_GYM_LEADER("Mayor", "Vaughan", 2000),
        //E4
        ELITE_FOUR_LORELEI("Elite Four", "Lorelei", 2000),
        ELITE_FOUR_BRUNO("Elite Four", "Bruno", 2000),
        ELITE_FOUR_AGATHA("Elite Four", "Agatha", 2000),
        ELITE_FOUR_LANCE("Elite Four", "Lance", 2000),
        CHAMPION("Champion", "Red", 5000),
        //Reg trainers
        BUG_CATCHER("Bug Catcher", "m", 50),
        LASS("Lass", "f", 50),
        YOUNGSTER("Youngster", "m", 50),
        HIKER("Hiker", "m", 50),
        SAILOR("Sailor", "m", 50),
        POKEMANIAC("Pokemaniac", "m", 50),
        SUPER_NERD("Super Nerd", "m", 50),
        ENGINEER("Engineer", "m", 50),
        FISHERMAN("Fisherman", "m", 50),
        SWIMMER("Swimmer", "m", 50),
        SWIMMER_F("Swimmer", "f", 50),
        CUE_BALL("Cue Ball", "m", 50),
        GAMBLER("Gambler", "m", 200),
        BEAUTY("Beauty", "f", 50),
        PSYCHIC("Psychic", "m", 50),
        ROCKER("Rocker", "m", 50),
        JUGGLER("Juggler", "m", 50),
        TAMER("Tamer", "m", 50),
        BIRD_KEEPER("Bird Keeper", "m", 50),
        BLACKBELT("Blackbelt", "m", 50),
        SCIENTIST("Scientist", "m", 100),
        CHANNELER("Channeler", "m", 50),
        COOLTRAINER("Cooltrainer", "m", 50),
        GENTLEMAN("Gentleman", "m", 50),
        SIGHTSEER("Sightseer", "m", 50),
        BIKER("Biker", "m", 50),
        BURGLAR("Burglar", "m", 100),
        FIREFIGHTER("Firefighter", "m", 50),
        SOLDIER("Soldier", "m", 50),
        COOLTRAINER_F("Cooltrainer", "f", 50),
        SIGHTSEER_F("Sightseer", "f", 50),
        //Team Rocket
        GRUNT_L("Rocket Grunt", "m", 50),
        GRUNT_L_F("Rocket Grunt", "f", 50),
        GRUNT_M("Rocket Grunt", "m", 60),
        GRUNT_M_F("Rocket Grunt", "f", 60),
        GRUNT_H("Rocket Grunt", "m", 70),
        GRUNT_H_F("Rocket Grunt", "f", 70),
        //COLOSSEUM
        COLOSSEUM_BATTLER("Colosseum Trainer", "m", 50),
        COLOSSEUM_BATTLER_F("Colosseum Trainer", "f", 50),
        //COMPETITIVE TRAINERS
        C_TRAINER_1("Battle Expert", "m", 50),
        C_TRAINER_2("Battle Expert", "m", 50),
        C_TRAINER_3("Battle Expert", "m", 50),
        C_TRAINER_4("Battle Expert", "m", 50),
        C_TRAINER_5("Battle Expert", "m", 50),
        C_TRAINER_6("Battle Expert", "m", 50),
        C_TRAINER_7("Battle Expert", "m", 50),
        C_TRAINER_8("Battle Expert", "m", 50),
        C_TRAINER_9("Battle Expert", "m", 50),
        C_TRAINER_10("Battle Expert", "m", 50),
        C_TRAINER_11("Battle Expert", "m", 50),
        C_TRAINER_12("Battle Expert", "m", 50),
        C_TRAINER_13("Battle Expert", "m", 50),
        C_TRAINER_14("Battle Expert", "m", 50),
        C_TRAINER_15("Battle Expert", "m", 50),
        C_TRAINER_16("Battle Expert", "m", 50),
        C_TRAINER_17("Battle Expert", "m", 50),
        C_TRAINER_18("Battle Expert", "m", 50),
        C_TRAINER_19("Battle Expert", "m", 50),
        C_TRAINER_20("Battle Expert", "m", 50);


        private final String titleString;
        private final String name;
        private final int prize;

        Title(String titleString, String name, int prize) {
            this.titleString = titleString;
            this.name = name;
            this.prize = prize;
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
        this.party = getParty();
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
    public static int getMajorTrainerLevel(){
        return Math.min(User.checkLevelCap() + checkEffectOfRep(), 100);
    }
    public static int getMinorTrainerLevel() {
        return Math.min(Party.getAvgPLvl() + checkEffectOfRep(), 100);
    }
    public static HashMap<Title, Pokemon[]> parties = new HashMap<>();
    static {
        // Gym Leaders
        parties.put(Title.PEWTER_GYM_LEADER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Geodude"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Onix"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Graveler"), getMajorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.CERULEAN_GYM_LEADER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Staryu"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Seel"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Goldeen"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Starmie"), getMajorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.VERMILLION_GYM_LEADER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Voltorb"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Electrode"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Electabuzz"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Pikachu"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Raichu"), getMajorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.CELADON_GYM_LEADER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Tangela"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Weepinbell"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Victreebel"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Gloom"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Vileplume"), getMajorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.FUCHSIA_GYM_LEADER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Koffing"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Muk"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Weezing"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Koffing"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Muk"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Venusaur"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
        });

        parties.put(Title.SAFFRON_GYM_LEADER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Abra"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Venomoth"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Hypno"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Kadabra"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Drowzee"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Alakazam"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
        });

        parties.put(Title.CINNABAR_GYM_LEADER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Growlithe"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Ponyta"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Rapidash"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Arcanine"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
        });

        parties.put(Title.VIRIDIAN_GYM_LEADER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Pidgeot"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Alakazam"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Rhydon"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Raticate"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Exeggutor"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Gyarados"), getMajorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.ROCKETOPOLIS_GYM_LEADER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Persian"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Dragonite"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Moltres"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Zapdos"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Snorlax"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Mewtwo"), getMajorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.VAUGHAN_DISTRICT_GYM_LEADER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Garchomp"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Dragapult"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Tyranitar"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Togekiss"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Umbreon"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Dragonite"), getMajorTrainerLevel(), Pokemon.getShinyOdds())
        }); //incomplete
        // Elite Four Parties
        parties.put(Title.ELITE_FOUR_LORELEI, new Pokemon[]{
                new Pokemon(Species.getSpecies("Dewgong"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Cloyster"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Slowbro"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Mamoswine"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Lapras"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Articuno"), getMajorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.ELITE_FOUR_BRUNO, new Pokemon[]{
                new Pokemon(Species.getSpecies("Rhyperior"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Hitmonchan"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Hitmonlee"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Hitmontop"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Golem"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Machamp"), getMajorTrainerLevel(), Pokemon.getShinyOdds())

        });

        parties.put(Title.ELITE_FOUR_AGATHA, new Pokemon[]{
                new Pokemon(Species.getSpecies("Gengar"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Golbat"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Haunter"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Dragapult"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Arbok"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Zoroark"), getMajorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.ELITE_FOUR_LANCE, new Pokemon[]{
                new Pokemon(Species.getSpecies("Gyarados"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Dragonair"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Salamence"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Charizard"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Aerodactyl"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Dragonite"), getMajorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.CHAMPION, new Pokemon[]{
                new Pokemon(Species.getSpecies("Excadrill"), getMajorTrainerLevel(), true),
                new Pokemon(Species.getSpecies("Dragonite"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Incineroar"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Togekiss"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Tyranitar"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Charizard"), getMajorTrainerLevel(), Pokemon.getShinyOdds())
        });
// Regular Trainers
        parties.put(Title.BUG_CATCHER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Caterpie"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Weedle"), getMinorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.LASS, new Pokemon[]{
                new Pokemon(Species.getSpecies("Pidgey"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("NidoranF"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Growlithe"), getMinorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.YOUNGSTER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Rattata"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Ekans"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("NidoranM"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
        });

        parties.put(Title.HIKER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Geodude"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Machop"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Graveler"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
        });

        parties.put(Title.SAILOR, new Pokemon[]{
                new Pokemon(Species.getSpecies("Poliwhirl"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Tentacool"), getMinorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.POKEMANIAC, new Pokemon[]{
                new Pokemon(Species.getSpecies("Slowpoke"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Lickitung"), getMinorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.SUPER_NERD, new Pokemon[]{
                new Pokemon(Species.getSpecies("Voltorb"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Magnemite"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Magneton"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
        });

        parties.put(Title.ENGINEER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Magneton"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Electrode"), getMinorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.FISHERMAN, new Pokemon[]{
                new Pokemon(Species.getSpecies("Gyarados"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Magikarp"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Seel"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
        });

        parties.put(Title.SWIMMER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Seadra"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Starmie"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Magikarp"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Jynx"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
        });

        parties.put(Title.SWIMMER_F, new Pokemon[]{
                new Pokemon(Species.getSpecies("Goldeen"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Seaking"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Starmie"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Lapras"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
        });

        parties.put(Title.CUE_BALL, new Pokemon[]{
                new Pokemon(Species.getSpecies("Machoke"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Machamp"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Machop"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
        });

        parties.put(Title.GAMBLER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Growlithe"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Vulpix"), getMinorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.BEAUTY, new Pokemon[]{
                new Pokemon(Species.getSpecies("Arcanine"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Vileplume"), getMinorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.PSYCHIC, new Pokemon[]{
                new Pokemon(Species.getSpecies("Abra"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Drowzee"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Hypno"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
        });

        parties.put(Title.ROCKER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Voltorb"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Electrode"), getMinorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.JUGGLER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Voltorb"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Koffing"), getMinorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.TAMER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Arbok"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Sandslash"), getMinorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.BIRD_KEEPER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Pidgey"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Spearow"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Fearow"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Pidgeotto"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Pidgeot"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
        });

        parties.put(Title.BLACKBELT, new Pokemon[]{
                new Pokemon(Species.getSpecies("Machamp"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Machoke"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Primeape"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
        });

        parties.put(Title.SCIENTIST, new Pokemon[]{
                new Pokemon(Species.getSpecies("Magnemite"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Voltorb"), getMinorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.CHANNELER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Gastly"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Haunter"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Haunter"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
        });

        parties.put(Title.COOLTRAINER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Pidgeot"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Rhydon"), getMinorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.GENTLEMAN, new Pokemon[]{
                new Pokemon(Species.getSpecies("Growlithe"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Ponyta"), getMinorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.SIGHTSEER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Pidgeotto"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Raticate"), getMinorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.BIKER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Koffing"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Grimer"), getMinorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.BURGLAR, new Pokemon[]{
                new Pokemon(Species.getSpecies("Growlithe"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Vulpix"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Meowth"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
        });

        parties.put(Title.FIREFIGHTER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Growlithe"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Ponyta"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Rapidash"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
        });

        parties.put(Title.SOLDIER, new Pokemon[]{
                new Pokemon(Species.getSpecies("Machoke"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Electabuzz"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Magmar"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
        });

        parties.put(Title.COOLTRAINER_F, new Pokemon[]{
                new Pokemon(Species.getSpecies("Nidoqueen"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Persian"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Arcanine"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
        });

        parties.put(Title.SIGHTSEER_F, new Pokemon[]{
                new Pokemon(Species.getSpecies("Pidgeot"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Nidorina"), getMinorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.GRUNT_L, new Pokemon[]{
                new Pokemon(Species.getSpecies("Ekans"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Grimer"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Dratini"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
        });

        parties.put(Title.GRUNT_L_F, new Pokemon[]{
                new Pokemon(Species.getSpecies("Raticate"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Grimer"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Koffing"), getMinorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.GRUNT_M, new Pokemon[]{
                new Pokemon(Species.getSpecies("Muk"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Weezing"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Fearow"), getMinorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.GRUNT_M_F, new Pokemon[]{
                new Pokemon(Species.getSpecies("Raticate"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Muk"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Arbok"), getMinorTrainerLevel(), Pokemon.getShinyOdds())
        });

        parties.put(Title.GRUNT_H, new Pokemon[]{
                new Pokemon(Species.getSpecies("Muk"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Meowth"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Dragonair"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Raticate"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Arbok"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
        });

        parties.put(Title.GRUNT_H_F, new Pokemon[]{
                new Pokemon(Species.getSpecies("Meowth"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Dragonair"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Arbok"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Weezing"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Raticate"), getMinorTrainerLevel(), Pokemon.getShinyOdds()),
        });
        //COMPETITIVE TRAINERS
        parties.put(Title.C_TRAINER_1, new Pokemon[]{
                new Pokemon(Species.getSpecies("Dragonite"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Corviknight"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Sylveon"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Charizard"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Amoonguss"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Venusaur"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_2, new Pokemon[]{
                new Pokemon(Species.getSpecies("Garchomp"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Metagross"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Zoroark"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Incineroar"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Moltres"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Tyranitar"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_3, new Pokemon[]{
                new Pokemon(Species.getSpecies("Arcanine"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Charizard"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Excadrill"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Incineroar"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Blaziken"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Sceptile"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_4, new Pokemon[]{
                new Pokemon(Species.getSpecies("Gengar"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Togekiss"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Mamoswine"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Incineroar"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Lucario"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Swampert"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_5, new Pokemon[]{
                new Pokemon(Species.getSpecies("Togekiss"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Aerodactyl"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Houndoom"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Dragapult"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Feraligatr"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Salamence"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_6, new Pokemon[]{
                new Pokemon(Species.getSpecies("Metagross"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Salamence"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Gardevoir"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Machamp"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Snorlax"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Scizor"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_7, new Pokemon[]{
                new Pokemon(Species.getSpecies("Persian"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Metagross"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Snorlax"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Zoroark"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Tyranitar"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Pidgeot"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_8, new Pokemon[]{
                new Pokemon(Species.getSpecies("Corvinight"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Dragapult"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Salamence"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Dragonite"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Metagross"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Tyranitar"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_9, new Pokemon[]{
                new Pokemon(Species.getSpecies("Lugia"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Togekiss"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Gardevoir"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Arcanine"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Pidgeot"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Scyther"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_10, new Pokemon[]{
                new Pokemon(Species.getSpecies("Kyogre"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Gyarados"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Swampert"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Charizard"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Blastoise"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Vaporeon"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_11, new Pokemon[]{
                new Pokemon(Species.getSpecies("Groudon"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Amoonguss"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Incineroar"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Lucario"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Tyranitar"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Flareon"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_12, new Pokemon[]{
                new Pokemon(Species.getSpecies("Butterfree"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Raichu"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Incineroar"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Lucario"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Tyranitar"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Flareon"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_13, new Pokemon[]{
                new Pokemon(Species.getSpecies("Mew"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Jirachi"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Hitmontop"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Lucario"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Metagross"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Gardevoir"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_14, new Pokemon[]{
                new Pokemon(Species.getSpecies("Zekrom"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Raichu"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Pikachu"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Jolteon"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Umbreon"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Mamoswine"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_15, new Pokemon[]{
                new Pokemon(Species.getSpecies("Palkia"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Hypno"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Gengar"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Espeon"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Umbreon"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Sylveon"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_16, new Pokemon[]{
                new Pokemon(Species.getSpecies("Glaceon"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Mamoswine"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Reshiram"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Victini"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Leafon"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Charizard"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_17, new Pokemon[]{
                new Pokemon(Species.getSpecies("Swampert"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Blaziken"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Sceptile"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Rayquaza"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Kyogre"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Groudon"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_18, new Pokemon[]{
                new Pokemon(Species.getSpecies("Blastoise"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Gyarados"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Swampert"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Raichu"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Amoonguss"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Incineroar"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_19, new Pokemon[]{
                new Pokemon(Species.getSpecies("Meganium"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Typhlosion"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Hitmontop"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Raichu"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Amoonguss"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Arcanine"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
        });
        parties.put(Title.C_TRAINER_20, new Pokemon[]{
                new Pokemon(Species.getSpecies("Entei"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Suicune"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Raikou"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Ho-Oh"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Alakazam"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
                new Pokemon(Species.getSpecies("Primeape"), getMajorTrainerLevel(), Pokemon.getShinyOdds()),
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
    public Pokemon[] getParty() {
        Pokemon[] party = parties.getOrDefault(this.title, new Pokemon[]{
                new Pokemon(Species.getSpecies("Pidgey"), getMajorTrainerLevel(), Pokemon.getShinyOdds())
        });

        // Create a copy of the array to shuffle (to avoid modifying the original)
        Pokemon[] shuffledParty = new Pokemon[party.length];

        for (int i = 0; i < party.length; i++) {
            shuffledParty[i] = party[i].clone();
        }

        // Shuffle the party order
        shuffleArray(shuffledParty);

        return shuffledParty;
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
                "Bobby", "Bob"
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
                "Diana", "Brittany", "Natalie", "Sophia", "Rose", "Isabella", "Alexis", "Kayla", "Charlotte", "Ava", "Liz"
        };

        Random rand = new Random();
        int index = rand.nextInt(femaleNames.length);
        return femaleNames[index];
    }
    private static final Random rand = new Random();
    public static Trainer buildBattleLeagueTrainer() {
        Pokemon[] trainerParty = new Pokemon[Party.p.length];
        int thisBST = 0;
        for (int i = 0; i < Party.p.length; i++) {
            if(Party.p[i] == null) continue;
            thisBST = Party.p[i].getBST();
            trainerParty[i] = new Pokemon(getRandomSpeciesNearBST(thisBST), User.checkLevelCap());
        }
        Title thisTitle = (rand.nextInt(0,2) == 0) ? Title.COLOSSEUM_BATTLER : Title.COLOSSEUM_BATTLER_F;
         return new Trainer(thisTitle, trainerParty);
    }
    private static Species getRandomSpeciesNearBST(int targetBST) {
        int lowerBound = targetBST - 40;
        int upperBound = targetBST + 40;
        int thisBST = 0;
        ArrayList<String> viableSpeciesChoices = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : Species.baseStatTotals.entrySet()) {
            thisBST = entry.getValue();
            if(thisBST >= lowerBound && thisBST <= upperBound) {
                viableSpeciesChoices.add(entry.getKey());
            }
        }
        return Species.getSpecies(viableSpeciesChoices.get(rand.nextInt(0, viableSpeciesChoices.size())));
    }
}
