# pokemonTextBased

POKEMON TEXT-BASED JAVA EDITION

A full-featured terminal-based Pokémon battle game, written entirely in Java, complete with 250 species, real mechanics, dynamic AI, and custom graphics. Built from scratch over 2 months.

## Features

- 250 Pokémon with stats, types, evolutions, and images
- Real battle mechanics: type matchups, stat changes, status effects
- Smart AI trained via custom in-game ML multi-threaded simulation engine
- Dynamic difficulty scaling
- Full single-player campaign: gym leaders, wild encounters, Team Rocket
- Terminal UI using Unicode + ANSI (Mac/Linux recommended)
- Catching system, items, switching, trick room, and more

## AI Engine

The AI/Analysis Engine:
- Analyzes matchups in real-time based on game state and 30+ engine parameters
- Provides live move/switch recommendations & shows match-up & move ratings
- Allows battle simulations mid-game to estimate win probabilities
- Avoids blunders and scales in strength
- This was probably the hardest part of the entire game to program (besides, well, writing the entire battle logic for Pokémon from scratch)

## Visuals & Audio

- Uses Unicode braille characters as pixel-art dot matrices. Also uses ANSI strings for color.
- Dot-converted Pokémon, trainer, and location images
- Background music & sound effects via JavaFX (YouTube-sourced MP3s)

## Platform Support

Works best on **Mac/Linux** terminals.
Windows support is limited due to character spacing issues.

## Demo (I will upload a video at some point soon)

Video walkthrough explaining:
- Gameplay and mechanics
- AI design
- Lessons learned

## Notes

- This was my first project. Some early code is messy. Most of it has been painstakingly refactored. Multiple times.
- Project on hold for now, might do a proper GUI version at some point in the future.

## How to Run (as of now) 

1. Clone the repo
2. Compile with Java 17+ (adjust paths if needed)
3. Run from the terminal, or better yet, straight out of an IDE
4. Make sure terminal font is size 12 or 13 and line spacing is set to 1.0!
5. Remember, the visuals will not look right if you're on Windows. Maybe try a Linux VM is you really want to play and have it not look like garbage.
