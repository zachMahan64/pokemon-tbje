package pokemonTextBased;

import java.util.*;

public class NPC {
    private static final Map<Character, Integer> dialogueCounters = new HashMap<>();

    public enum Character {
        MOM("MOM"),
        PROFESSOR("PROFESSOR OAK"),
        RICHIE("RICHIE"),
        SCHIZO_JOE("SCHIZO JOE"),
        FATHER_PUCHTON("FATHER PUCHTON"),
        BROCK("BROCK"),
        MISTY("MISTY"),
        BLUE("BLUE"),
        BLUE_NIGHTCLUB("BLUE"),
        SABRINA("SABRINA"),
        SABRINA_BARTENDER("SABRINA"),
        SABRINA_NIGHTCLUB("SABRINA");


        private final Map<Integer, List<String>> badgeDialogue = new HashMap<>();
        public final String name;

        Character(String name) {
            List<String> defaultLines = new ArrayList<>();
            defaultLines.add("Hello! (" + 0 + " badges)");
            badgeDialogue.put(0, defaultLines);
            this.name = name;
        }


        public void setDialogue(int badgeCount, String... lines) {
            List<String> dialogueLines = new ArrayList<>();
            for (String line : lines) {
                dialogueLines.add(line);
            }
            badgeDialogue.put(badgeCount, dialogueLines);
        }

        public List<String> getDialogue(int badgeCount) {
            int closestKey = 0;
            for (int key : badgeDialogue.keySet()) {
                if (key <= badgeCount && key > closestKey) {
                    closestKey = key;
                }
            }
            return Collections.unmodifiableList(
                    badgeDialogue.getOrDefault(closestKey, badgeDialogue.get(0))
            );
        }

        public String getNextLine(int badgeCount) {
            dialogueCounters.putIfAbsent(this, 0);
            List<String> dialogue = getDialogue(badgeCount);
            int counter = dialogueCounters.get(this);
            String line = dialogue.get(counter % dialogue.size());
            // avoids overflow if the player really LOVES talking to an NPC
            dialogueCounters.put(this, (counter + 1) % Integer.MAX_VALUE);
            return line;
        }

        public String getName() {
            return name;
        }
    }

    static {
        Character.MOM.setDialogue(0,
                "Be careful out there!",
                "Have you used the potions I gave you yet?",
                "That map is pretty nifty, isn't it?",
                "Did professor Oak give you a Pokedex?",
                "I can't believe you're leaving Pallet Town.",
                "Your father would be proud of you.");

        Character.MOM.setDialogue(1,
                "Wow, you already got a badge!",
                "The house feels empty without you...",
                "I bet pretty soon you'll become famous!",
                "You haven't been in Rocketopolis, have you?",
                "It's good to see you!");

        Character.MOM.setDialogue(2,
                "Wow, you already got 2 badges!",
                "The house feels empty without you...",
                "I read about your battle in the paper!",
                "Everyone's talking about you around town!",
                "You haven't been in Rocketopolis, have you?",
                "Call me sometimes!");

        Character.MOM.setDialogue(4,
                "Four badges, you're really on your way to becoming Champion!",
                "The neighbors won't stop asking about you!",
                "I baked your favorite cookies - they're in the fridge.",
                "Please tell me you're still not going to Rocketopolis, right?",
                "Maybe just stay out of Celadon altogether?");

        Character.MOM.setDialogue(6,
                "Six badges! Woohoo!",
                "I framed a picture of you winning your sixth badge!",
                "Reporters keep calling!",
                "Your fanmail is piling up!",
                "I heard you've caught the attention of Mayor Vaughan...");

        Character.MOM.setDialogue(7,
                "Seven badges! You're almost champion!",
                "Reporters are still calling, they're asking about your stance on Mayor Vaughan.",
                "Everyone around town is talking about your activities in Rocketopolis. What did I tell you?",
                "You need to stop going there. Don't talk to the mayor or the press.");

        Character.MOM.setDialogue(8,
                "Eight badges! Are you going to battle the Elite Four?",
                "Reporters are still calling, they're asking about your stance on Mayor Vaughan.",
                "Everyone around town is talking about your activities in Rocketopolis. What did I tell you?",
                "You need to stop going there. Don't talk to the mayor or the press.");

        Character.MOM.setDialogue(9,
                "You beat Giovanni, that's incredible!",
                "I'm so proud of you!",
                "You need to stop going there. Don't talk to the mayor or the press.");

        Character.MOM.setDialogue(10,
                "Did Team Rocket get to you?",
                "What have you done...");
    } //MOM
    static {
        Character.PROFESSOR.setDialogue(0,
                "Good luck on your adventure!",
                "You can catch all kinds of Pokemon on routes!",
                "Have you tried out your Pokedex?",
                "Red, whom you know is from Pallet Town too, already filled it out for you!"
                );

        Character.PROFESSOR.setDialogue(1,
                "Nice job beating your first gym!",
                "You're on your way to becoming a fantastic Pokemon trainer.",
                "Have you tried out your Pokedex?",
                "Red, whom you know is from Pallet Town too, already filled it out for you!");
        Character.PROFESSOR.setDialogue(2,
                "You're becoming such an amazing Pokemon trainer.",
                "I see you've been making good use of your Pokedex");
        Character.PROFESSOR.setDialogue(10,
                "Why?",
                "What made you do that to Vaughan?",
                "I can't believe I helped you become a Pokemon trainer...");
    } //PROF
    static {
        Character.RICHIE.setDialogue(0,
                "What are you doing here?",
                "Eh, you have no badges? I'm not impressed.",
                "You're just a kid. You'll never make it to the Pokemon League.",
                "You actually know professor Oak?",
                "Oh, you're from Pallet Town.",
                "You're family friends with Red? Seriously!?"
                );
        Character.RICHIE.setDialogue(1,
                "Well, well, well...",
                "You again... One badge is nothing special...",
                "What are you coming to the help desk for...",
                "The little kid from Pallet Town.. at another gym again...",
                "No, I don't support what Vaughan is doing. But we shouldn't talk about that here."
        );
        Character.RICHIE.setDialogue(2,
                "Well, well, well...",
                "You again... Two badges is nothing special...",
                "What are you coming to the help desk for...",
                "The little kid from Pallet Town.. at another gym again...",
                "No, I don't support what Vaughan is doing. But we shouldn't talk about that here."
        );
        Character.RICHIE.setDialogue(3,
                "Well, well, well...",
                "You again... a few badges is nothing special...",
                "What are you coming to the help desk for...",
                "The little kid from Pallet Town... at another gym again...",
                "Brock is my favorite gym leader, but don't tell him I told you that...",
                "No, I don't support what Vaughan is doing. But we shouldn't talk about that here."
        );
        Character.RICHIE.setDialogue(4,
                "Oh, hi! I mean... eh... Hi there.",
                "You again... a few badges is nothing special...",
                "Brock is my favorite gym leader, but don't tell him I told you that...",
                "What are you coming to the help desk for...",
                "The little kid from Pallet Town... at another gym again...",
                "No, I don't support what Vaughan is doing. But we shouldn't talk about that here."
        );
        Character.RICHIE.setDialogue(5,
                "For a little kid, you're not too bad.",
                "You again... five badges is pretty good...",
                "Brock is my favorite gym leader, but don't tell him I told you that...",
                "What are you coming to the help desk for...",
                "The little kid from Pallet Town... at another gym again...",
                "No, I don't support what Vaughan is doing. But we shouldn't talk about that here."
        );
        Character.RICHIE.setDialogue(6,
                "For a little kid, you're not too bad.",
                "You again... six badges is pretty damn good...",
                "What are you coming to the help desk for...",
                "The little kid from Pallet Town... at another gym again...",
                "Brock is my favorite gym leader, but don't tell him I told you that...",
                "No, I don't support what Vaughan is doing. But we shouldn't talk about that here."
        );
        Character.RICHIE.setDialogue(7,
                "Well, well, well...",
                "You again... seven badges? You're close to becoming champion...",
                "Brock is my favorite gym leader, but don't tell him I told you that...",
                "What are you coming to the help desk for...",
                "The little kid from Pallet Town... at another gym again...",
                "No, I don't support what Vaughan is doing. But we shouldn't talk about that here."
        );
        Character.RICHIE.setDialogue(8,
                "Well, well, well...",
                "You again... You're close to becoming champion... Time to beat Red...",
                "Yeah, I'm actually a decent pokemon trainer myself, that's how I got this job.",
                "What are you coming to the help desk for...",
                "And, no, I don't support what Vaughan is doing. But we shouldn't talk about that here."
        );
        Character.RICHIE.setDialogue(9,
                "You beat Giovanni and you're Champion... Impressive for a little kid.",
                "Giovanni really isn't so bad, you know...",
                "Vaughan... Giovanni... Vanni... Their names are so similar...",
                "They even look similar...",
                "I met both of them before, you know...",
                "I wonder if they're related..."
        );
        Character.RICHIE.setDialogue(10,
                "...",
                "... You did the right thing..."
        );
    } //RICHIE
    static {
        Character.SCHIZO_JOE.setDialogue(0,
                "...",
                "... The voices... they sing to me...",
                "Oh you? Do you know it...",
                "... my favorite song?"
        );
    } //SCHIZO JOE
    static {
        Character.FATHER_PUCHTON.setDialogue(0,
                "Welcome to St. Vaga Catholic Church!",
                "Although we support Mr. Vaughan, our church's name has no relation to his.",
                "Please, remember not to send out your pokemon when church is in service.",
                "...",
                "Are you part of Team Rocket? We do not allow their kind in here...",
                "..."
        );
    } //FATHER PUCHTON
    static {
        Character.MISTY.setDialogue(0,
                "You're not so tough!",
                "Water types are fantastic, they're only weak to two types!",
                "Mayor Vaughan's new climate stance is letting the water get polluted. At least he's helping us stop Team Rocket!",
                "Brock is my good friend. Have you met him yet?",
                "You remind me of a young trainer named Red! He came through this gym a couple years back.",
                "Good luck on your journey!"
        );
        Character.MISTY.setDialogue(6,
                "You're pretty tough!",
                "Mayor Vaughan's new climate stance keeps polluting the water. It's getting bad!",
                "You still remind me of Red!",
                "I think you'll be a champion, just like him!"
        );
        Character.MISTY.setDialogue(8,
                "What are you waiting for!? Become the CHAMPION!",
                "Maybe, if you're champion, you can help me restore the water!",
                "... I'm thinking about something. Sorry... a lot is on my mind..."
        );
        Character.MISTY.setDialogue(9,
                "CHAMPION! Awesome!",
                "Please, help me heal the ocean! Kanto needs you!",
                "You even beat Giovanni! Awesome!"
        );
        Character.MISTY.setDialogue(10,
                "... You did what had to be done...",
                "Surely now you can help me clean up the water around here!",
                "... I can't stop thinking about what you did...",
                "Don't worry, I'm not mad at you!"
        );
    } //MISTY
    static {
        Character.BROCK.setDialogue(0,
                "I love rocks and rock Pokemon!",
                "Tell me if you see any pretty girls on your journey... Ask them if they have their Boulder Badge yet.",
                "You should come train with us on Saturday! We do 4 x 12 sets of Boulder bench and 3 x 8 sets of Defense Curls!",
                "No, I'm not a big fan of Vaughan, but a lot of people in Pewter city love him...",
                "Have you talked to Richie yet? He's remarkable... I wonder how he manages to work at so many gyms...",
                "Richie and I are pretty close, but he won't admit it!",
                "Have you been to Cerulean City yet? Misty is the gym leader there!",
                "If you see Erika in Celadon, tell her I'm here if she needs me. She's not too happy about everything that's going on there!"
        );
        Character.BROCK.setDialogue(4,
                "You're becoming a pretty serious trainer!",
                "Tell me if you see any pretty girls on your journey... Ask them if they have their Boulder Badge yet.",
                "You should come train with us on Saturday! We do 4 x 12 sets of Boulder bench and 3 x 8 sets of Defense Curls!",
                "No, I'm not a big fan of Vaughan, but a lot of people in Pewter city love him...",
                "Have you talked to Richie yet? He's remarkable... I wonder how he manages to work at so many gyms...",
                "Richie and I are pretty close, but he won't admit it!",
                "Have you been to Cerulean City yet? Misty is the gym leader there!",
                "If you see Erika in Celadon, tell her I'm here if she needs me. She's not too happy about everything that's going on there!"
        );
        Character.BROCK.setDialogue(8,
                "You're almost CHAMPION!",
                "Tell me if you see any pretty girls on your journey... Ask them if they have their Boulder Badge yet.",
                "You should come train with us on Saturday! We do 4 x 12 sets of Boulder bench and 3 x 8 sets of Defense Curls!",
                "No, I'm not a big fan of Vaughan, but a lot of people in Pewter city love him...",
                "Have you talked to Richie yet? He's remarkable... I wonder how he manages to work at so many gyms...",
                "Richie and I are pretty close, but he won't admit it!",
                "Have you been to Cerulean City yet? Misty is the gym leader there!",
                "If you see Erika in Celadon, tell her I'm here if she needs me. She's not too happy about everything that's going on there!"
        );
        Character.BROCK.setDialogue(9,
                "You beat Giovanni!? Wow, dude, that's awesome!",
                "Tell me if you see any pretty girls on your journey... Ask them if they have their Boulder Badge yet.",
                "You should come train with us on Saturday! We do 4 x 12 sets of Boulder bench and 3 x 8 sets of Defense Curls!",
                "No, I'm really not a big fan of Vaughan at all, but sooooo many people in Pewter city love him...",
                "Richie is actually a very accomplished trainer! Even stronger than me!",
                "Richie and I are pretty close, but he won't admit it!",
                "Have you been to Cerulean City yet? Misty is the gym leader there!",
                "If you see Erika in Celadon, tell her I'm here if she needs me. She's not too happy about everything that's going on there!"
        );
        Character.BROCK.setDialogue(10,
                "Dude, don't worry, we're still tight!",
                "I was never a big fan of Vaughan, but a lot of people in Pewter city loved him...",
                "Tell me if you see any pretty girls on your journey... Ask them if they have they're Boulder Badge yet.",
                "You should come train with us on Saturday! We do 4 x 12 sets of Boulder bench and 3 x 8 sets of Defense Curls!",
                "Richie is actually a very accomplished trainer! Even stronger than me!",
                "Richie and I are homies, don't forget it!",
                "Have you been to Cerulean City yet? Misty is the gym leader there!",
                "If you see Erika in Celadon, tell her I'm here if she needs me. She's not too happy about everything that's going on there!"
        );
    } //BROCK
    static {
        Character.BLUE.setDialogue(0,
                "What's a little runt like you doing in my gym?",
                "Not one badge, you're nothing...",
                "Scram!",
                "You remind me of Red... and I hate Red! So beat it!"
        );
        Character.BLUE.setDialogue(1,
                "What's a little runt like you doing in my gym?",
                "You're weak...",
                "Scram!",
                "You remind me of Red... and I hate Red! So beat it!"
        );
        Character.BLUE.setDialogue(7,
                "Seven badges, I bet you think you're something special",
                "Guess what, I have eight badges! Ha!",
                "You'll never beat me. Not in a million years."
        );
        Character.BLUE.setDialogue(8,
                "..."
        );
        Character.BLUE.setDialogue(9,
                "I admit it... you're pretty strong.",
                "...",
                "No, I'm not sulking. You beat Giovanni... good for you.",
                "..."
        );
        Character.BLUE.setDialogue(10,
                "That took balls. I was wrong about you.",
                "Richie and I have our differences, but we agree what's done is done and that's okay.",
                "Honestly, good riddance, haha! Giovanni was always the cooler vaaaaaaan!",
                "I'm honored whenever you want to rematch me! Let's train together sometime!",
                "I free on the weekends and anytime after 5 during the work week if you want to hang out too!",
                "Do you think I have a chance with Sabrina. Oh, never mind...",
                "...",
                "Maybe? I'm pretty cool trainer. Yeah, no, no, you're right. It's hopeless",
                "... Man...",
                "..."
        );
    } //BLUE
    static {
        Character.BLUE_NIGHTCLUB.setDialogue(0,
                "...",
                "... What am I doing here..."
        );
        Character.BLUE_NIGHTCLUB.setDialogue(1,
                "... I'm wasting my time",
                "What am I doing?"
        );
        Character.BLUE_NIGHTCLUB.setDialogue(3,
                "...",
                "Hey, I see you here pretty often."
        );
        Character.BLUE_NIGHTCLUB.setDialogue(6,
                "... You think... maybe...",
                "Nah, nah, never mind..."
        );
        Character.BLUE_NIGHTCLUB.setDialogue(7,
                "... Hm..... hm.",
                "..."
        );
        Character.BLUE_NIGHTCLUB.setDialogue(10,
                "Someday, I'll go for it",
                "..."
        );
    } //BLUE NIGHT CLUB
    static {
        Character.SABRINA.setDialogue(0,
                "...",
                "Have you been to the night club yet?",
                "We don't card, but we should start.",
                "Trainers are just getting younger and younger these days...",
                "How old are you again?",
                "Hm...",
                "... Do you know Red?",
                "... Really, oh, you're from Pallet Town? That's pretty far."
        );
        Character.SABRINA.setDialogue(4,
                "Hey! What's up?",
                "You're really improving as trainer!",
                "Visit me whenever you want!",
                "... Red has never mentioned me, has he?"
        );
        Character.SABRINA.setDialogue(9,
                "Hey Champ!",
                "Nice job beating Giovanni! And...",
                "I can't believe you beat Red!"
        );
        Character.SABRINA.setDialogue(10,
                "Hey Champ!",
                "Nice job beating Giovanni! And...",
                "I can't believe you beat Red!",
                "And you took down Vaughan too!"
        );
    } //SABRINA
    static {
        Character.SABRINA_NIGHTCLUB.setDialogue(0,
                "Aren't you a little young to be here?",
                "You haven't had anything to drink, have you?",
                "Are you having fun?",
                "The band is pretty good tonight."
        );
        Character.SABRINA_NIGHTCLUB.setDialogue(4,
                "I love Saffron City!!",
                "Here, wait, I'll buy you a drink... It's on me.",
                "Yeah! You're having fun?",
                "I'm glad I hired this band full time"
        );
        Character.SABRINA_NIGHTCLUB.setDialogue(6,
                "Good to see you!",
                "Go dance, don't sit here talking to me!",
                "...",
                "Hey, do you know who that is over there?",
                "..."
        );
    } //SABRINA NIGHTCLUB

    public static void talkTo(Character character, Scanner sc1) {
        int badgeCount = User.checkNumBadges();
        Graphics.printNPC(character);
        System.out.println(character.getName() + ":");
        System.out.println(character.getNextLine(badgeCount));
        Game.pressEnterToContinue(sc1);
    }
    public static void talkToRand(Character character, Scanner sc1) {
        int badgeCount = User.checkNumBadges();
        List<String> dialogue = character.getDialogue(badgeCount);
        Graphics.printNPC(character);
        System.out.println(character.getName() + ":");

        if (dialogue == null || dialogue.isEmpty()) {
            System.out.println("...");
        } else {
            System.out.println(dialogue.get(new Random().nextInt(dialogue.size())));
        }
        Game.pressEnterToContinue(sc1);
    }
}