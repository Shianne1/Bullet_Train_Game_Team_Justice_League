                                                READ ME DOCUMENT

This document will explain everything within the Bullet Train Game Project including:
    MVC
    Classes
    Text files
    What the game is and how to play
    Map of game
    Commands for game
    Glossary


-------------------------------------------MODEL VIEW CONTROLLER SECTIONS-----------------------------------------------
[MODEL:]
GameState.java
-- This model carries the data of the game which is referenced by the controller --

[CONTROLLER:]
GameConsole.java
-- takes in commands to view to run methods based on the data provided by the model

[VIEW:]
View.java
-- receives commands and prints outputs to the player


----------------------------------------------------CLASSES-------------------------------------------------------------
[PLAYER CLASS:]
This class will set up the Player Object that is needed for tracking player stats, location and inventory
This Class contains:

    private int numOfMonstersKilled
    private int maxHealth
    private int currentHealth
    private String name
    private Room location
    private Weapon equippedWeapon
    private Armor equippedArmor
    private ArrayList<Item> inventory;
    private ArrayList<String> codeInventory
    private GameState checkpoint
    boolean hasCheckPoint
    Weapon defaultWeapon

    -Constructor for Rooms with parameters being private variables
    -Getter/Setters for private variables
    -viewCode method will print out most recent code the player has acquired
    -healHealth heals the health of player by set amount, capped to heal to, not beyond, max health
    -checkStatsPlayer method returns formatted string with all relevant player stats
    -takeDamage decreases current player health by set amount
    -inventoryAdd method adds item to player inventory
    -inventoryRemove method removes item from player inventory
    -codeInventoryAdd adds codes to the inventory


[PUZZLE CLASS:]
This class will set up puzzle objects
This class contains:

        private int puzzleID;
        private String puzzleName
        private String puzzleQuestion
        private String hint
        private String answer
        private int attempts
        private String reward1
        private String reward2
        private String puzzleCode
        GameConsole game
        private ArrayList<Puzzle> puzzles
        private ArrayList<Item> items
        private ArrayList<Room> rooms
        Item itemObject
        Puzzle puzzleGame
        Scanner input

        -Constructor for Rooms with parameters being private variables
        -Getter/Setters for private variables
        -dropRewardsItem method will drop puzzle reward item/s into current room
        -removeRewardItem method will remove puzzle reward item/s from current room
        -addPuzzleCodes method will add codes that players get from puzzles to the player code inventory
        -inspectPuzzle prints out the puzzle and asks player if they'd like to solve puzzle
        -solvePuzzle takes in response from player as a puzzle answer, checks if answer is correct or if
            player wishes to exit, if incorrect, that is communicated to player and attempts count is reducted
        -hint method prints out hint for relevant puzzle
        -retryPuzzle method will allow for the player to retry the puzzle if they have failed


[ROOM CLASS:]
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
    private ArrayList<Item> itemsInRoom
    Scanner input

	-Constructor for Rooms with parameters being private variables
	-Getter/Setters for private variables
	-inspectRoom method which constructs String output that details all notable features of room.
    -folderList method will ensure that folders are in correct room.
    -roomItemAdd will add dropped item to RoomItem Arraylist
    -roomItemRemove will remove item from room item arraylist
    -Direction method will facilitate movement through game, checks whether rooms are locked and facilitates checking
        if rooms are locked and if you've been to that room before
    -checkVisited method will check if room has been visited, and if so indicates that to the player.
    -lockRoom handles asking player for room password


[MONSTER CLASS:]
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
    GameConsole game
    private ArrayList<Monster> enemy
    private ArrayList<Item> items
    private ArrayList<Room> roomItems
    Scanner input

	-Constructor for Monsters with parameters being private variables
	-Getter/Setters for private Variables
	-attackMonster which when called deals damage to player, updates player health, displays damage
	    dealt and remaining player health
	-parryMonster which generates random integer 1-100, if variable is greater than 50 the
	    parry boolean returns true and the monster's attack should then miss.
    -monsterDrop method will determine and drop item in room when monster is defeated
    -run method will kick player out of monster room when player health is 1/10 max player health
    -healHealth heals monster
    -takeDamage monster takes damage
    -inspectMonster method will output description of monster


[ITEM CLASS:]
This class will be the basis for all item objects
This Class contains:

     private int itemId;
     private String itemName;
     private String itemDesc;
     private String itemText;
     GameConsole game
     GameState gameConsole
     private ArrayList<Item> items
     private ArrayList<Room> roomItems
     Item itemObject
     Scanner input

     -Constructor for items with parameters being private variables
     -Getter/Setters for private variables
     -inspect method will output item description
     -use lets the player use the item based on its function
     -getFolderCode tells the player the folder code based on what folder is present
     -storeItem adds item to inventory and removes from room
     -discardItem removes item from inventory and adds to remove


[ARMOR CLASS:]
This class will be the basis for armor items
This Class contains:

    private int armorMod
    private ArrayList<Armor> armorInventory
    GameConsole game
    private ArrayList<Item> items
    private ArrayList<Room> roomItems

    -Constructor for items with parameters being private variables
    -Getter/Setters for private variables
    -equip method will equip armor to player
    -addingArmor method


[WEAPON CLASS:]
This class will be the basis for weapon items
This Class Contains:

    private int durability
    private int strength
    GameConsole game
    private ArrayList<Item> items
    private ArrayList<Room> roomItems
    Scanner input
    private ArrayList<Weapon> weaponsInventory
    private ArrayList<Monster> enemy
    Item itemObjectWeapon
    Weapon weaponItem

    -Constructor for items with parameters being private variables
    -Getter/Setters for private variables
    -useWeapon method allows player use of the weapon to fight monsters
    -addingWeapon adds weapon to weapon inventory
    -EquipWeapon method equips to player
    -unequipWeapon method unequips weapon from player


[HEALING CLASS:]
This class will be the basis for healing items
This Class Contains:

    private int healAmount
    private int stackAmount
    GameConsole game
    private ArrayList<Item> items
    private ArrayList<Room> roomItems
    private ArrayList<Healing> healingInventory
    Scanner input
    Item itemObjectHeal

    -Constructor for items with parameters being private variables
    -Getter/Setters for private variables
    -addHealingItems methods adds healing item to healing item inventory
    -useHealing method heals player and decreases item stack


[FOLDER CLASS:]
This Class will be the basis for folder items
This Class contains:

    -Constructor for folder item


[CRATE CLASS:]
This class will set up Crate objects
This Class contains:

    private String crateName
    private String itemName
    private int crateLocation
    GameConsole game
    private ArrayList<Crate> crates
    private ArrayList<Item> items
    private ArrayList<Room> roomItems

    -Constructor for items with parameters being private variables
    -Getter/Setters for private variables
    -examineCrate method will report what item is in the crate
    -removeItemFromCrate method will remove item from crate


[GAME CONSOLE CLASS:]
This Class contains the main method for the entire game, all major actions originate from GameConsole
This Class Contains:

    -main
    -parseCommand method will parse commands, and run methods based on the command received by the view
    -startGame Runs at execution, takes in command whether it is new game, load game or exit
    -newGame will begin new game
    -loadGame will load binary file of game
    -saveGame saves game information to binary file
    -endGame exits game, closing system.
    -playerDeath runs when player has hit 0 HP
    -Various Read methods which will read data in from .txt files


[GAME STATE CLASS:]
This class is used as backend for game
This class contains:

    private Player player
    private ArrayList<Item> itemsInGame
    private ArrayList<Room> roomsInGame
    private ArrayList<Puzzle> puzzlesInGame
    private ArrayList<Monster> monstersInGame
    private ArrayList<Crate> cratesInGame
    boolean isRunning

    -Constructor for GameState with parameters being private variables
    -Getter/Setters for private variables
    -setGameState methods allows for loading data from a different GameState
        into another, used for loading the game


[VIEW CLASS:]
This Class is used as a front end for game
This Class contains:

    Scanner userInput
    GameConsole game

    -printItemsText prints formatted string for inventory
    -printStatsText prints out the stats of the player
    -inputCommand basic call for the view to ask to input command
    -printGameLoop code is for printing out the start of the game loop, while asking the player for an input
    -setSaveNameText sets the text for the save game
    -loadingGameText prints the text asking to load, asking the player to input the name for the data
    -savingGameText code prints the text asking to save
    -startOfGameText prints at the very start of the game, before heading into the loop,
        and asks the player to choose how they want to start the game
    -printBasicText basic printer, just to keep things tied to the view
    -printExitMessage ran to print out when the game is preparing to exit
    -printDeathMessage ran to tell the player they've died and will be returned to the checkpoint


------------------------------------------------------TEXT FILE---------------------------------------------------------
Help.txt
-- a text file that contains the commands the player can use

README.txt
-- a text file that contains everything within this package

Room.txt
-- a text file that contains roomID, room name, room description, room connections, lock rooms, crates, puzzle, item, &
monsters in room

Puzzle.txt
-- a text file that contains puzzleID, puzzle name, puzzle question, hint to puzzle, answer, codes, attempts to puzzle
puzzle rewards

Monster.txt
-- a text file that contains monsterID, monster name, monster description, monster health & damage, monster percentage of
dropping items

Item.txt
-- a text file that contains itemID, item name, item description, stack amount, health, weapons, armor, folders

Crate.txt
-- a text file that contains crate name, item within crate, crate location

Map.txt
-- a text file that will print out the map to player

Game Instructions.txt
-- a text file that will print out the instructions to player


-------------------------------------------------------BINARY FILES-----------------------------------------------------
(playername)_data.bin
-- created when players save the game, holding aan object of GameState.java]


-----------------------------------------WHAT THE GAME IS AND HOW TO PLAY-----------------------------------------------
[GAME INTRO:]
“Welcome to the Bullet Train! You, as an assassin have been assigned a task to
swindle a briefcase and any other intel from the world’s most lethal mafia gang leader.
This train will stop at every station and present a mystery or puzzle that you need
to solve to get back in your train cabin and onto the next station. If for any reason
you fail to solve the challenge or are killed you will lose some of your powers and
travel back to your checkpoint that you have save While you are on the train, make sure to explore the cabin and collect
any and everything you can to be prepared for your upcoming challenges.
Be sure to consume a good deal of bandages as the mafia gang members are not going to be
easy to battle and their leader is not going to be happy to see you trying to get your
hands on the briefcase. Good Luck!”


[ABOUT THE GAME:]
The game is a mystery solving/puzzle game that involves the player going through
multiple rooms to clear monsters, get stronger by collecting various gear to make the
player stronger, solving a mystery at the mystery rooms, and eventually killing the final
boss and collecting the suitcase. The player will start at station 1 to begin and must
head to the door that leads out to the station that corresponds to the wagon they’re in.
The player must clear all rooms at the station to be able to go to the next train wagon. The
mysteries are simply just paper file item that players will find in a room that allow
them to progress into the next room. The files include information that pertains to the
mafia gang leader and their evil plans.


[DEATH:]
The checkpoints will be the train wagons, every time a new train wagon is
reach the player will unlock that checkpoint. If a player dies, they will return to the
latest train wagon they unlocked and will lose their progress and will have to redo the
fights and obtain their items again.


[FIGHTS:]
The fight will start with the player choosing to use equip weapon to attack monster, then
the monster can attack back. Player can have the chance to parry a monster that can double up their points of use weapon.
The player can use their weapon of their choice when
fighting (Fists, knife, katana, pistol, rifle)


[INVENTORY:]
The player will have a general inventory with all the items they have,
weapons, armor, and healing items. The player can have as many healing items as
possible they want with no limit. When using a healing item, it will -1 from their
stack, stacks cannot go under 0. For weapons, once the katana is obtained it will
replace the knife in their inventory, pistol and rifle are their own object and can both
exist. For armor, the armor will be equipped and change the player’s stats, once
equipped will disappear from the player’s inventory.
The player will have a code inventory that record all the codes they have collect.


[GOAL:]
The player will need to complete all mysteries throughout the stations to be
able to access the final boss and then defeat the boss and win.


--------------------------------------------SOFTWARE SYSTEM ATTRIBUTES--------------------------------------------------
[USABILITY:]
The system should be accessible and reusable by users on devices that support java. Players
should be able to save their progress through the game and revisit later without losing their
information and place in the game.

[RELIABILITY:]
This text-based adventure game’s probability of failure on demand (POFOD) shall be 1 out of
1000 (i.e., .0001)

[AVAILABILITY:]
The game should be available to users in Fall 2022 and should achieve 99.5% uptime, as it's an entirely offline game.

[READABILITY:]
The game is legible by anyone with a standard reading ability, and all information and commands are laid out in simple
terms

[LOGIC DATABASE REQUIREMENT:]
The database will store every players’ progress in the game that they can save by using the “save game” command at any
time during the game. The database will store a .bin file with the player’s information, based on the player's given name.
This save data can then be loaded in through the "load game" command


------------------------------------------------------MAP---------------------------------------------------------------
BULLET TRAIN MAP:

M = MONSTER
P = PUZZLE
LK = LOCK ROOM
C#NUMBER = CRATE

                  [Station 1 Mystery (LK)=(FOLDER 1)]
[Train Wagon 1]---[Station 1 Puzzle (P0 = HIT POINTS, KNIFE, BANDAGE)]---[Station 1 Loot(C1, C2, C3)(C3 = MED KIT)]
       |          [Station 1 Monster (M0 = DOG,BANDAGE, SYRINGE)]
       |
       |
       |               [Station 2 Monster (M4 = SCRAP, BANDAGE, SYRINGE)]
[Train Wagon 2 (LK)]---[Station 2 Puzzle (P3 = TRAINS, LIGHT ARMOR, BANDAGE)]---[Station 2 Mystery (LK)=(FOLDER 2)]
       |               [Station 2 Loot (C4, C5, C6)(C4 = MED KIT)]
       |
       |
       |               [Station 3 Loot & Puzzle (P6 = FILL IN THE BLANK, PISTOL, BANDAGE) (C7, C8, C9)(C8 = MED KIT)]
[Train Wagon 3 (LK)]---[Station 3 Puzzle (P2 = WHAT DOES IT SAY, KATANA, BANDAGE)]---[Station 3 Mystery (LK)=(FOLDER 3)]
       |               [Station 3 Monster (M1 = TIGER, BANDAGE, SYRINGE)]
       |
       |
       |               [Station 4 Monster (M5 = SPARK, BANDAGE, SYRINGE)]
[Train Wagon 4 (LK)]---[Station 4 Puzzle (P5 = IT'S SHARP, MEDIUM ARMOR, BANDAGE)]---[Station 4 Loot & Puzzle (P7 = WHEELS, RIFLE, BANDAGE) (C10, C11, C12)(C10 = MED KIT)]
       |               [Station 4 Mystery (LK)=(FOLDER 4)]
       |
       |
       |           [Station 5 Loot & Monster (M2 = GOODFELLA, BANDAGE, SYRINGE) (C13, C14, C15)(C14 = MED KIT)]
[Train Wagon 5 ]---[Station 5 Puzzle (P4 = MILES PER HOURS, HEAVY ARMOR, BANDAGE)]---[Station 5 Monster (M3 = GOOMBAH, BANDAGE, MED KIT)]
       |
       |
       |
       |
[Final Locomotive (M6 = MAFIA BOSS) (SUITCASE)]


------------------------------------------------------------COMMANDS----------------------------------------------------
GAME:
[Start game]
Starts a new game, only works at the start of the session
[Save game]
Prompts the player if they want to play a game, which can then be answered with "Yes" or "No"
[Load game]
Prompts the player if they want to play a game, which can then be answered with "Yes" or "No"
If Yes, the player can then input the name they gave the player of the game they want to load, to load that game
[End game]
Prompts the player if they want to save the game, and then ends the game session


PLAYER:
[Help]
Shows the help menu to the player
[check inventory]
Lists out the items within the player's inventory
[check stats]
Lists out the player's currents stats (health, ammo, number of monsters killed, etc)
[check map]


ROOM:
[checkout room]
Gives the description of the room the player is currently in: also lists any puzzle, monsters, or items in the room


PUZZLE:
[Solve puzzle]
Lets the player solve the puzzle of the name given
[Get hint]
While in a puzzle, the player can ask for a hint to solve it
[Checkout puzzle]
Provides the player with the name and question of the puzzle
[Exit puzzle]
While the player is trying to solve the puzzle, this allows the player to exit the puzzle
[Retry "puzzle name"]
Lets the player retry the puzzle
[Claim Prize]
Allows the player to add the award from the puzzle into their inventory


MONSTER:
[Inspect "monster name"]
Gets the description of monster with the inputted name, however the monster will get to attack first
[Attack Monster]
Attacks the monster with damage being based on the player's current weapon
[Parry monster]
Player makes an attempt to parry the monster:
(has a 50% chance of succeeding which deals double damage)
(If the player fails, no damage is dealt)
[Run]
Allows the player to leave the fight into the previous room
(the player will automatically attempt to run when health is less than 10)


ITEM:
[Examine "crate name"]
will allow the player to look inside the crate
[Inspect "item name"]
Inspects the item of the given name, giving a description of the item and how it's used
[Store "item name"]
Puts the item of the given name into the player's inventory
[Use "item name"]
Uses the item of the given name, with the effects being based on the item's usage (healing items will heal the player)
[Equip "item name"]
Equips an equitable item of the given name, increasing the damage stat or max health depending on if it's armor or a weapon
[unequip "item name"]
Unequips an equitable item of the given name
[Discard "item name"]
Removes the item of the given name from their inventory
[View code]
views the recent code that the player has gotten
[Use code]
Uses the inputted code to open specific folders


MOVEMENT:
[Move "direction"]]
Moves the player into the inputted direction, if there is a room to move into
[Recognized Directions:]
North/N
East/S
South/S
West/W


----------------------------------------------------GLOSSARY------------------------------------------------------------
Loot = means treasure/ item

Mystery = Is a room to find the mystery folders which are items

Health = how much damage a player can take before they die and their gamestate is restarted

Checkpoint = a saved point in the game that the player is sent back to upon death


------------------------------------------------------------------------------------------------------------------------

                                           THIS IS THE END OF THE DOCUMENT