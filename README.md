# Bullet Train

# Table of Content

- [Game-Play](#game-play)
- [Map](#map)
- [Commands](#commands)

  
# Game Play

### Game Intro
“Welcome to the Bullet Train! You, as an assassin, have been assigned a task to
swindle a briefcase and any other intel from the world’s most lethal mafia gang leader.
This train will stop at every station and present a mystery or puzzle that you need
to solve to get back in your train cabin and onto the next station. If for any reason
you fail to solve the challenge or are killed, you will lose some of your powers and
travel back to the checkpoint that you have saved While you are on the train, make sure to explore the cabin and collect
any and everything you can to be prepared for your upcoming challenges.
Be sure to consume a good deal of bandages as the mafia gang members are not going to be
easy to battle and their leader is not going to be happy to see you trying to get your
hands on the briefcase. Good Luck!”


### About the Game
The game is a mystery-solving/puzzle game that involves the player going through
multiple rooms to clear monsters, get stronger by collecting various gear to make the
player stronger, solve a mystery at the mystery rooms, and eventually kill the final
boss and collect the suitcase. The player will start at station 1 and must
head to the door that leads out to the station that corresponds to the wagon they’re in.
The player must clear all rooms at the station to be able to go to the next train wagon. The
mysteries are simply just paper file items that players will find in a room that allows
them to progress into the next room. The files include information that pertains to the
mafia gang leader and their evil plans.


### Death
The checkpoints will be the train wagons, every time a new train wagon is
reach the player will unlock that checkpoint. If a player dies, they will return to the
latest train wagon they unlocked and will lose its progress and will have to redo the
fights and obtain their items again.


### Fights
The fight will start with the player choosing to use an equipped weapon to attack the monster, then
the monster can attack back. The player can have the chance to parry a monster that can double up their points of use weapon.
The player can use the weapon of their choice when
fighting (Fists, knife, katana, pistol, rifle)


### Inventory
The player will have a general inventory with all the items they have,
weapons, armor, and healing items. The player can have as many healing items as
possible they want with no limit. When using a healing item, it will be -1 from their
stack, stacks cannot go under 0. For weapons, once the katana is obtained it will
replace the knife in their inventory, pistol, and rifle are their own object and can both
exist. For armor, the armor will be equipped and change the player’s stats, once
equipped will disappear from the player’s inventory. The player will have a code 
inventory that records all the codes they have collected.

### Goal
The player will need to complete all mysteries throughout the stations to be
able to access the final boss and then defeat the boss and win.

# Map


# Commands

### GAME
#### Start game: 
- Starts a new game, only works at the start of the session
#### Save game: 
- Prompt the player if they want to play a game, which can then be answered with "Yes" or "No"
#### Load game: 
- Prompt the player if they want to play a game, which can then be answered with "Yes" or "No"
- If Yes, the player can then input the name they gave the player of the game they want to load, to load that game
#### End game
- Prompts the player if they want to save the game, and then ends the game session

&nbsp;
&nbsp;
&nbsp;

### PLAYER
#### Help
- Shows the help menu to the player
#### check inventory
- Lists out the items within the player's inventory
#### check stats
- Lists out the player's current stats (health, ammo, number of monsters killed, etc)
#### check map
- will print out the map and show the location of the player

&nbsp;
&nbsp;
&nbsp;

### ROOM
#### checkout room
- Describes the room the player is currently in: also lists any puzzle, monsters, or items in the room

&nbsp;
&nbsp;
&nbsp;

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

&nbsp;
&nbsp;
&nbsp;

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

&nbsp;
&nbsp;
&nbsp;

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

&nbsp;
&nbsp;
&nbsp;

MOVEMENT:
[Move "direction"]]
Moves the player into the inputted direction, if there is room to move into
[Recognized Directions:]
North/N
East/S
South/S
West/W
