READ ME DOCUMENT

This document will explain everything within the Bullet Train Game Project including:
    MVC
    Classes
    Text files
    What the game is and how to play
    Map of game
    Commands for game
    Glossary

------------------------------------------------------------------------------------------------------------------------

[[MODEL VIEW CONTROLLER SECTIONS:]]

[MODEL:]
GameState.java

-- This model carries the data of the game which is referenced by the controller --

[CONTROLLER:]
GameConsole.java

-- takes in commands to view to run methods based on the data provided by the model

[VIEW:]
View.java

-- receives commands and prints outputs to the player

------------------------------------------------------------------------------------------------------------------------

[CLASSES:]

ROOM CLASS:
This class will set up the room objects that is needed for navigating and calling other elements of game.
This Class contains:
	private int roomId
	private String roomName
	private String roomDesc
	private boolean isVisited
	private boolean isLocked
	private String crates
	private int roomPuzzle
	private int roomMonster
	private String[] connections
	private int north
	private int east
	private int south
	private int west
	private ArrayList<Item> roomItems

	Constructor for Rooms with parameters being private variables
	Getter/Setters for private variables
	inspectRoom method which constructs String output that details all notable features of room.


MONSTER CLASS:
This class will set up the Monster objects that are needed for combat
This Class contains:
	private int monsterId
	private String monsterName
	private String monsterDesc
	private int health
	private int damage
	private String itemDrop1
	private String itemDrop2
	private double dropRate1
	private double dropRate2
	Constructor for Monsters with parameters being private variables
	Getter/Setters for private Variables
	attackMonster which when called deals damage to player, updates player health, displays damage dealt and remaining player health
	parryMonster which generates random integer 1-100, if variable is greater than 50 the parry boolean returns true and the monster's attack should then miss.

------------------------------------------------------------------------------------------------------------------------

[TEXT FILES:]

Help.txt
README.txt
Room.txt
Puzzle.txt
Monster.txt


[BINARY FILES:]

(playername)_data.bin
-- created when players save the game, holding aan object of GameState.java]


------------------------------------------------------------------------------------------------------------------------

WHAT THE GAME IS AND HOW TO PLAY:

GAME INTRO:
“Welcome to the Bullet Train! You, as an assassin have been assigned a task to
swindle a briefcase and any other intel from the world’s most lethal mafia gang leader.
This train will stop at every station and present a mystery or puzzle that you need
to solve to get back in your train cabin and onto the next station. If for any reason
you fail to solve the challenge or are killed you will lose some of your powers and
travel back a station where you may or may not have to fight a monster to get back
on track. While you are on the train, make sure to explore the cabin and collect any
and everything you can to be prepared for your upcoming challenges.
Be sure to consume a good deal of food and drinks as the mafia gang members are not going to be
easy to battle and their leader is not going to be happy to see you trying to get your
hands on the briefcase. Good Luck!”


[[HOW TO PLAY:]]
This will take the user to a page that describes how the player can navigate
through the text-based game and play using the different keyboard commands.


[[ABOUT THE GAME:]]
The game is a mystery solving/puzzle game that involves the player going through
multiple rooms to clear monsters, get stronger by collecting various gear to make the
player stronger, solving a mystery at all the stations, and eventually killing the final
boss and collecting the suitcase. The player will start at station 1 to begin and must
head to the door that leads out to the station that corresponds to the wagon they’re in.
The player must clear all rooms at the station to be able to go to the next train wagon.
This process will repeat until they finish the last mystery then fight the final boss. The
mysteries are simply just paper file item that players will find in a room that allow
them to progress into the next room. The files include information that pertains to the
mafia gang leader and their evil plans.


[[GAMEPLAY WISE:]]
[Death:]
The checkpoints will be the train wagons, every time a new train wagon is
reach the player will unlock that checkpoint. If a player dies, they will return to the
latest train wagon they unlocked and will lose their progress and will have to redo the
fights and obtain their items again.

[Fights:]
The fights will be turned based and the player can choose to initiate the fight
or run away from the fight. The player can use their weapon of their choice when
fighting (Fists, knife, katana, pistol, rifle). Upon encountering a monster, the player
will have a choice to fight the monster or running. Fighting will put you into combat
and the player can choose to run whenever they want. Running will send you back to
the previous room.

[Inventory:]
The player will have a general inventory with all the items they have,
weapons, armor, and healing items. The player can have as many healing items as
possible they want with no limit. When using a healing item, it will -1 from their
stack, stacks cannot go under 0. For weapons, once the katana is obtained it will
replace the knife in their inventory, pistol and rifle are their own object and can both
exist. For armor, the armor will be equipped and change the player’s stats, once
equipped will disappear from the player’s inventory.

[Goal:]
The player will need to complete all mysteries throughout the stations to be
able to access the final boss and then defeat the boss and win.


[[SOFTWARE SYSTEM ATTRIBUTES]]

[Usability:]
The system should be accessible and reusable by users on devices that support java. Players
should be able to save their progress through the game and revisit later without losing their
information and place in the game.

[Reliability:]
This text-based adventure game’s probability of failure on demand (POFOD) shall be 1 out of
1000 (i.e., .0001)

[Availability:]
The game should be available to users in Fall 2022 and should achieve 99.5% uptime, as it's an entirely offline game.

[Readability:]
The game is legible by anyone with a standard reading ability, and all information and commands are laid out in simple terms

[Logical Database Requirement:]
The database will store every players’ progress in the game that they can save by using the “save game” command at any time during the game.
The database will store a .bin file with the player’s information, based on the player's given name. This save data can then be
loaded in through the "load game" command


------------------------------------------------------------------------------------------------------------------------

MAP:

BULLET TRAIN MAP:

                  [Station 1 Mystery (FOLDER 1)]
[Train Wagon 1]---[Station 1 Puzzle (P1 = HIT POINTS)  (KNIFE, BANDAGE)]---[Station 1 Loot(MED KIT)]
       |          [Station 1 Monster (DOG)  (BANDAGE, SYRINGE)]
       |
       |
       |          [Station 2 Monster (SCRAP)  (BANDAGE, SYRINGE)]
[Train Wagon 2]---[Station 2 Puzzle (P3 = TRAINS)  (LIGHT ARMOR, BANDAGE)]---[Station 2 Mystery (FOLDER 2)]
       |          [Station 2 Loot (MED KIT)]
       |
       |
       |          [Station 3 Loot & Puzzle (P6 = FILL IN THE BLANK)  (PISTOL, BANDAGE)]
[Train Wagon 3]---[Station 3 Puzzle (P2 = WHAT DOES IT SAY)  (KATANA, BANDAGE)]---[Station 3 Mystery (FOLDER 3)]
       |          [Station 3 Monster (TIGER)  (BANDAGE, SYRINGE)]
       |
       |
       |          [Station 4 Monster (SPARK)  (BANDAGE, SYRINGE)]
[Train Wagon 4]---[Station 4 Puzzle (P5 = IT'S SHARP)  (MEDIUM ARMOR, BANDAGE)]---[Station 4 Loot & Puzzle (P7 = WHEELS)  (RIFLE, BANDAGE)]
       |          [Station 4 Mystery (FOLDER 4)]
       |
       |
       |          [Station 5 Loot & Monster (GOODFELLA)  (BANDAGE, SYRINGE)]
[Train Wagon 5]---[Station 5 Puzzle (P4 = MILES PER HOURS)  (HEAVY ARMOR, BANDAGE)]---[Station 5 Monster (GOOMBAH)  (BANDAGE, MED KIT)]
       |
       |
       |
       |
[Final Locomotive (MAFIA BOSS)  (SUITCASE)]
------------------------------------------------------------------------------------------------------------------------

[[[COMMANDS]]]


[[GAME FUNCTION COMMANDS]]
[Start game] - starts the game
[Save game] - saves the game
[Load game] - loads the game
[End game] - ends the game

[[PLAYER COMMANDS:]]
[Help] - prints Help.txt
[Check inventory] - prints the players' inventory
[Check stats] - prints the player's stats
[Check map] - prints Map.txt

[[Room COMMANDS:]]
[Inspect room] - gives a description of the room and the items in it

[[Puzzle COMMANDS:]]
[Solve "puzzle name"] - lets the users solve the puzzle
[Get hint] - gives the user a hint
[Inspect puzzle] - gives a description of the puzzle
[Exit puzzle] - exits trying to attempt the puzzle
[Retry "puzzle name"] - allows the player to retry the puzzle
[Claim Prize] - lets the player claim the prize from the puzzle



[[MONSTER/BATTLE COMMANDS:]]
[Inspect "monster name"] - uses a turn to print the monster's description
[Fight Monster] - lets the player take an initiative attack on a monster in the room
[Attack Monster] - lets the player attack the monster in battle
[Parry monster] - gives the player a chance for a double or nothing attack
[Heal] - lets the player look in their inventory and use a healing item to recover health
[Run] - allows the player to run away from a fight, is done automatically if health gets below 10


[[ITEM COMMANDS]]
[Examine crate] - looks inside crates for items
[Inspect "item name"] -  gives the description of an item and how it is used
[Store "item name"] - puts the item in the inventory
[Use "item name"] - uses the item
[Equip "item name"] - equips the item to the appropriate slot
[Discard "item name"] - removes the item from the inventory
[View code] - reads out the codes the player has
[Use code] - allows the player to

[Move "direction"]]

------------------------------------------------------------------------------------------------------------------------

[[[GLOSSARY:]]]

Loot = means treasure/ item

Mystery = Is a room to find the mystery folders which are items

Health = how much damage a player can take before they die and their gamestate is restarted

Checkpoint = a saved point in the game that the player is sent back to upon death

------------------------------------------------------------------------------------------------------------------------

                                       THIS IS THE END OF THE DOCUMENT