package pokemonTextBased;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Box {
    private static ArrayList<Pokemon> box = new ArrayList<>();
    private static final int BOX_CAPACITY = 1024;

    public static boolean addToBox(Pokemon pokemon) {
        if (box.size() < BOX_CAPACITY) {
            box.add(pokemon);
            System.out.println(pokemon.getName() + " has been stored in your box.");
            return true;
        } else {
            System.out.println("The box is full! Cannot store " + pokemon.getName() + ".");
            return false;
        }
    }
    public static ArrayList<Pokemon> getBox() {
        return new ArrayList<>(box);
    }
    public static void listBox() {
        if (box.isEmpty()) {
            System.out.println("The box is empty.");
        } else {
            for (int i = 0; i < box.size(); i++) {
                System.out.println(i + 1 + ". " + box.get(i).getName());
            }
        }
    }
    public static void clearBox() {
        box.clear();
    }
    public static Pokemon getPokemonFromBox(Scanner sc1) {
        if (box.isEmpty()) {
            System.out.println("The box is empty. No Pokémon to retrieve.\n");
            Game.pressEnterToContinue(sc1);
            return null;
        }

        // Display Pokémon in the box
        System.out.println("Choose a Pokémon to retrieve from the box:");
        for (int i = 0; i < box.size(); i++) {
            System.out.println((i + 1) + ". " + box.get(i));
        }
        if (sc1.hasNextInt()) {
            int choice = sc1.nextInt();
            sc1.nextLine(); // to consume newline

            if (choice < 1 || choice > box.size()) {
                System.out.println("Invalid choice. No Pokémon was retrieved.");
                Game.pressEnterToContinue(sc1);
                return null;
            }

            Pokemon chosenPokemon = box.get(choice - 1);
            box.remove(choice - 1);  // Remove Pokémon from the box
            System.out.println(chosenPokemon.getName() + " was retrieved from the box.");
            Game.pressEnterToContinue(sc1);
            return chosenPokemon;
        }
        return null;
    }

    public static void setBox(List<Pokemon> loadedBox) {
        box = new ArrayList<>(loadedBox);
    }
}
