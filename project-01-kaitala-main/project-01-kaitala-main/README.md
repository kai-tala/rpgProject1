# Project 1: Ordered and Unordered Linear Data Structures

## 44-242: Data Structures

## 25 points

### Runnables

Project Name: `pnprpg`

This lab contains:

* Tests
* A `main` method

Consult the course website for execution instructions for your chosen dev environment.

---

One of the biggest barriers of entry to pen and paper style games is the complicated rules system, a situation often exacerbated by different interpretations of the Rules as Written, players determined to subtly... influence the outcome of events in their favor, and the fact that the logic of these rules is not always immediately clear.  As such, we will be designing our own, modern pen and paper RPG, just not run on pen and paper.  You will write a program that will simulate a Character object, which will read a character sheet, and compete with other characters in events.  Given that this game system is currently not even in beta status yet (heck, it's pre-alpha...), you will design the software to be flexible in terms of the characteristics a character may have.

This project has three parts;  at the time this is assigned, you will not be able to complete the entire project.  However, you should have enough information to complete at least the first part of the project.
	
The following sections will explain the different elements your Character class requires; how these are represented in the character sheet files will be detailed later.

### General Behavior

Your class should provide a `readCharacterSheet` method that reads the specified character sheet and stores the information in the instance of the class.  Additionally, you should provide a constructor that takes a file name, and reads the character sheet in.

As you go through the parts of this assignment, you can modify the `readCharacterSheet` method to perform more of the required functionality.  In Part 1, you should only need to read in the character's name and objects in the inventory.  In part 3, you will add the reading in of skills and attributes.

### Milestones


| Description | Tests passed|
| --- | --- |
| Add required comment header to `.java` files in the Source Package | |
| Implement the `ObjectNotOwnedException` class | Tests will run, but not necessarily pass |
| Complete Part 1: `getName`, `setName`, read name in `readCharacterSheet`| `testGetName`, `testSetName`|
| Complete Part 1: `obtain`, `consume`, `count`, read inventory in `readCharacterSheet`  | `testObtain`, `testConsume`, `testConsumeException`, `testCount`, `testReadCharacterSheetNameInventory` |
| Complete Part 1: `addWin`, `countWins`, `addLoss`, `countLosses` | `testAddWin`|
| Complete Part 1: `addLoss`, `countLosses` | `testAddLoss` |
| Complete Part 2: `earnAchievement`, read attributes and skills in `readCharacterSheet` | `testEarnAchievement` |
| Complete Part 3: Tournament | no unit test, but GitHub will report failure if output of main doesn't match|

While you are required to have at least one unique commit per milestone, you can have multiple commits for any one milestone, or fix mistakes in later commits.  You are not limited to only one commit/milestone, and you can make fixes in later commits as necessary.

### Part 1: Name, Inventory, and Character History

Every character has a name; that is a given.  This name should be a String member variable in your Character class.  Additionally, every character has an inventory.  The inventory is the list of things that the character owns;  we will say that the inventory of your character will be both potentially infinite and allow duplicates of objects (for example, every book the character has with them will take one inventory slot).  As such, you should represent your inventory as an `ArrayList<String>` instance variable.

Your character should also keep track of the different events they compete in.  Your class should have two `LinkedList<String>` instance variables: `winHistory` and `lossHistory`. 

For this part of this project, you should implement the following functionality:

* `String getName()`: Returns the name of the character
* `void setName(String newname)`: Sets the name of the character to the string specified
* `void obtain(String thing)`: Adds the thing to the character's inventory
* `void consume(String thing)`: Should remove the first instance of the thing from the inventory.  If the thing does not exist in the inventory, your program should throw an `ObjectNotOwnedException` (see below)
* `void count(String thing)`: Should return the number of times the thing occurs in the inventory.  If the thing does not exist in the inventory, return 0.
* `void addWin(String eventName)`: Should add the string to the `winHistory` instance variable.
* `void addLoss(String eventName)`: Should add the string to the `lossHistory` instance variable.
* `void countWins(String eventName)`: Returns the number of times the event appears in the `winHistory` instance variable.
* `void countLosses(String eventName)`: Returns the number of times the event appears in the `lossHistory` instance variable.

**New Exception** You must define a new exception: `ObjectNotOwnedException` that extends `RuntimeException`.  

### Part 2: Achievements

I have been lead to believe that a game is more fun if it has achievements.  As such, you will award achievements to your characters.  The class should store these achievements in a `HashSet<String>` instance variable.  You will need to implement the following functionality:

* `boolean earnAchievement(String achievement)`: adds the achievement to the set of achievements.  If the achievement already exists in the set, it should return false.  Otherwise, it should print a message saying `<character> got the <achievement> achievement` (e.g. `Leela got the Staring Contest 1 achievement`) and return true.
* `HashSet<String> achievements()`: returns the set of achievements the character has earned.

### Part 3: Attributes and Skills
We are going to characterize our characters in two different ways: attributes and skills.  An attribute is an implicit property of a character: how smart they are, how strong they are, how fast they are, etc.  A skill is something the character is good at (or not very good at), such as programming, sportsball, or playing the keyboard at a jazz concert.  We will use these attributes and skills to allow the characters to compete at different events.

#### Attributes

In the current rules, the attributes of a character are defined as: smarts, buffness, smarminess, sneakiness, common\_sense, and liveness.  Every character will have a number associated with each of these attributes that will determine how good or bad they are in a given area.  The names of attributes may some day change.  As such, you should represent the attributes of a character with a `HashMap<String, Integer>` instance variable, with the key being the attribute name and the value being the integer value representing how good they are in that aspect.

#### Skills
There are an infinite number of skills out there.  As such, you should represent a characterâ€™s skills as either a `HashMap<String, Integer>` instance variable.  The same rules apply to skills that do attributes; the key is the name of the skill and the value is how good they are at the skill.

#### Rolling Rules

Characters will compete with each other in some competitions.  To do this, we need a way to determine how well a character does at a given activity.  The following rules (which, henceforth, will be referred to as _how we roll_, or HWR) will allow us to pit our characters against each other in exciting feats of daring (or truth-ing?).

Note: throughout the following, I will reference a function `d(int numsides)`.  This will roll a die with the number of sides specified (in other words, a random number from 1 to numsides, inclusive).  Thus, d(4) will return either a 1, 2, 3, or 4, randomly.  This is not a required function for you to write, but may be helpful.

Note 2: If at any time you try to access a skill or ability that is NOT in the maps you have defined in your character, you should default to 0.  Thus, if you try to get the skill value for the key "programming", and that key is not in the dictionary, the skill value should be 0.  You may want to define functions named `getAttr` and `getSkill` that return 0 if the specified key does not exist in the respective maps.

#### Raw Attribute Rolls

Your character must be able to attempt things that have no particular skill associated with them.  To do this, you should define a function that takes a string (which I will call `attr`) as an argument.  This function should determine the value associated with the key attr (which I will call `attrvalue`), and return `(attrval / 5 + 1) * d(attrval / 2 + 1) + (attrval - 10) / 3`

This function is referred to as 'attrRoll' throughout the rest of these rules.

#### Skill Rolls

Your character must be able to attempt things with skills associated with them.  To do this, you should define a function that takes two strings: an attribute and a skill.  It should determine the value associated with the skill (called `skillvalue`) and return `attrRoll(attr) + (skillvalue / 3)`

To define the characters, your program must be able to read in a "character sheet".  Here is a sample of a character sheet:

```text
--name
Jaque Muscleman
--inventory
protein shake
--inventory
sweatband
--inventory
protein bar
--inventory
protein bar
--attr
smarts 9
--attr
buffness 17
--attr
smarminess 9
--attr
sneakiness 6
--attr
common_sense 10
--attr
liveness 18
--skill
sports 8
--skill
sprinting 2
```

Keep in mind that a character may have any number of skills and (potentially) attributes.  The important thing is that the line before the name will always be "`--name`".  The line before an inventory element will always bee"`--inventory`".  The name and inventory values may be multiple words, and as such you will want to use `nextLine()`.  Attributes will always be preceeded by "`--attr`" and skills with "`--skill`".  Additionally, skills and attributes will always be a single word (so you can use `.next()` and `.nextInt()` in your scanner to read in skills and attributes).  You will want to use `.nextLine()` after you read the `.nextInt()` to clear the newline from the buffer.

### The Tournament

It is difficult to test the third part of this project due to the random nature of the rolling rules.  As such, you will pit them in a battle royale of epic proportions.  To do this, you will read in the character sheets provided with this assignment.  Then you will have every character compete against every other character in certain events.  For every match up, the characters will compete 5 times.

To have two characters compete, have them make a skill roll with the skill attribute listed in the table. The character who gets the highest score wins.  If character A wins against character B, A should add the event name to it's wins history instance variable, and B should add the event name to it's loss history instance variable. 

| Event | Skill | Attribute |
| ----- | ----- | --------- |
| Hacking | programming | smarts |
| Foosball | sports | buffness |
| Politicking | bluffing | smarminess |
| Crossing the Street | sprinting | common_sense|
| Staring Contest | | liveness |

Note that the staring contest does not have any skill associated with it; it should just use a raw attribute roll.

One way to do this is (if you use an array as your list of contestants) is to use a double for loop:

```java
for (int i=0; i<players.size(); i++)
{
	for (int j=i+1; j<players.size(); j++)
	    compete(players[i], players[j], event_name);
}
``` 

How you write the compete method is up to you (if you even use a method).  You might want to use more hash maps to make your life easier.  However you do it, the two players should make a grand total of 5 competitive rolls against each other in each competition (In other words, it will be a best out of 5 between any two players).  This for loop just ensures that every player competes against every other character exactly once for a given event name; you may need to have another for loop inside to make sure each player plays against the other 5 times.

After the tournament, you should go through every character's win history and count the number of time they won every event.  The character should get an achievement for winning an event once, three times, five times, etc.  As such, in theory, a character could win many achievements for the same event. The achievement name *must* be `event name #` (for example, if someone wins Hacking 3 times, they would get the `Hacking 3` achievement).

You should use the files `fry.txt`, `leela.txt`, and `zoidberg.txt` that are included with the assignment to run your tournament.  You can feel free to define new characters if you like, but include these characters in your final output.

You must write the code that runs the tournament in `Main.java`'s `main` method.

Sample Output (yours may differ because of randomness):

```text
Philip Fry got the Hacking 1 achievement
Philip Fry got the Politicking 1 achievement
Philip Fry got the Politicking 3 achievement
Philip Fry got the Crossing the Street 1 achievement
Philip Fry got the Crossing the Street 3 achievement
Philip Fry got the Staring Contest 1 achievement
Turanga Leela got the Hacking 1 achievement
Turanga Leela got the Hacking 3 achievement
Turanga Leela got the Hacking 5 achievement
Turanga Leela got the Hacking 7 achievement
Turanga Leela got the Hacking 9 achievement
Turanga Leela got the Foosball 1 achievement
Turanga Leela got the Foosball 3 achievement
Turanga Leela got the Politicking 1 achievement
Turanga Leela got the Politicking 3 achievement
Turanga Leela got the Politicking 5 achievement
Turanga Leela got the Crossing the Street 1 achievement
Turanga Leela got the Crossing the Street 3 achievement
Turanga Leela got the Crossing the Street 5 achievement
Turanga Leela got the Crossing the Street 7 achievement
Turanga Leela got the Crossing the Street 9 achievement
Turanga Leela got the Staring Contest 1 achievement
Zoidburg got the Foosball 1 achievement
Zoidburg got the Foosball 3 achievement
Zoidburg got the Foosball 5 achievement
Zoidburg got the Foosball 7 achievement
Zoidburg got the Foosball 9 achievement
Zoidburg got the Politicking 1 achievement
Zoidburg got the Staring Contest 1 achievement
Zoidburg got the Staring Contest 3 achievement
Zoidburg got the Staring Contest 5 achievement
Zoidburg got the Staring Contest 7 achievement```

### Grading
For each category, evenly divide the number of points across the number of elements. 

|Criteria|Total Points|
| --- | --- |
| Correctness (tests) | 10 |
| Implementing the tournament | 5 |
| Presence of comment header(s) | 2 |
| At least one unique commit per milestone | 4 |
| Following a consistent and clean coding style | 4 |
