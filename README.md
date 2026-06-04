# ObjectVille II - SE116 City Simulation

A city-building simulation game inspired by SimCity. Zero-player game.

## Team
- Batuhan Bulut
- Dilek Simal Aydin
- Mert Erturk
- Eray Ozkan

## How to Run

    java -jar ObjectVilleGame.jar <map_file> <tick_count>

Example:

    java -jar ObjectVilleGame.jar map00.txt 10

## Map File Format
Each row of the map is written as a string of characters on a single line (no spaces).
Each line represents a row of the city grid. Available symbols:
- H: Housing zone
- I: Industrial zone
- C: Commercial zone
- P: Power Plant
- W: Water Station
- T: Internet Hub
- F: Police Station
- D: Hospital
- S: School
- R: Road
- E: Empty cell