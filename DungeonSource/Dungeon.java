/**
 * Title: Dungeon.java
 *
 * Description: Driver file for Heroes and Monsters project
 *
 * Copyright:    Copyright (c) 2001
 * Company: Code Dogs Inc.
 * I.M. Knurdy
 *
 * History:
 *  11/4/2001: Wrote program
 *    --created DungeonCharacter class
 *    --created Hero class
 *    --created Monster class
 *    --had Hero battle Monster
 *    --fixed attack quirks (dead monster can no longer attack)
 *    --made Hero and Monster abstract
 *    --created Warrior
 *    --created Ogre
 *    --made Warrior and Ogre battle
 *    --added battleChoices to Hero
 *    --added special skill to Warrior
 *    --made Warrior and Ogre battle
 *    --created Sorceress
 *    --created Thief
 *    --created Skeleton
 *    --created Gremlin
 *    --added game play features to Dungeon.java (this file)
 *  11/27/2001: Finished documenting program
 * version 1.0
 */



/*
  This class is the driver file for the Heroes and Monsters project.  It will
  do the following:

  1.  Allow the user to choose a hero
  2.  Randomly select a monster
  3.  Allow the hero to battle the monster

  Once a battle concludes, the user has the option of repeating the above

*/
public class Dungeon
{
	public Room[][] dungeonRooms;
	
//    public static void main(String[] args)	/** We'll need to remove this and make it a method **/
//	{
//
//		Hero theHero;
//		Monster theMonster;
//
//		do
//		{
//		    theHero = chooseHero();
//		    theMonster = MonsterFactory.generateMonster();
//			battle(theHero, theMonster);
//
//		} while (playAgain());
//
//    }//end main method

	public Dungeon()
	{
		this.dungeonRooms = new Room[5][5];
	}
	
	
/*-------------------------------------------------------------------
chooseHero allows the user to select a hero, creates that hero, and
returns it.  It utilizes a polymorphic reference (Hero) to accomplish
this task
---------------------------------------------------------------------*/
	public static Hero chooseHero()
	{
		int choice;
		Hero theHero;
		
		System.out.print("\t\t\tChoose a hero:\n\t\t\t" 
		                       + "1. Warrior\n\t\t\t" 
				               + "2. Sorceress\n\t\t\t" 
		                       + "3. Thief\n\t\t\t" 
				               + "4. Java Student\n\t\t\t" 
		                       + "5. Psychic\n\n\t\t\t    "
				               + ">");
		choice = Keyboard.readInt();

		theHero = HeroFactory.createHero(choice);

		return theHero;

	}//end chooseHero method


	
/** Moved monster generation to Factory **/



/*-------------------------------------------------------------------
playAgain allows gets choice from user to play another game.  It returns
true if the user chooses to continue, false otherwise.
---------------------------------------------------------------------*/
	public static boolean playAgain()
	{
		char again;

		System.out.println("Play again (y/n)?");
		again = Keyboard.readChar();

		return (again == 'Y' || again == 'y');
	}//end playAgain method


/*-------------------------------------------------------------------
battle is the actual combat portion of the game.  It requires a Hero
and a Monster to be passed in.  Battle occurs in rounds.  The Hero
goes first, then the Monster.  At the conclusion of each round, the
user has the option of quitting.
---------------------------------------------------------------------*/
	public static void battle(Hero theHero, Monster theMonster)
	{
		char pause = 'p';
		System.out.println(theHero.getName() + " battles " + theMonster.getName());
		System.out.println("---------------------------------------------");

		//do battle
		while (theHero.isAlive() && theMonster.isAlive())
		{
		    //hero goes first
			theHero.battleChoices(theMonster);

			//monster's turn (provided it's still alive!)
			if (theMonster.isAlive())
			    theMonster.attack(theMonster, theHero);

		}//end battle loop

		if (!theMonster.isAlive())
		    System.out.println(theHero.getName() + " was victorious!\n");
		else if (!theHero.isAlive())
			System.out.println(theHero.getName() + " was defeated :-(");

	}//end battle method


	
	/** Game Dependent Objects Spawning methods **/
//Places the Entrance, the Exit, and the Pillars of OO Pieces. 
//	RULES: (1) The entrance and exit are empty rooms. 
//   	   (2) The Pillar pieces cannot be at the entrance or the exit. 
//  	   (3) Pillar pieces must not occur in the same room.
	
	/** Player Tracking method **/
// Maintains location of the Hero in the Dungeon
	
	/** toString method**/
// Builds a String containing information about the ENTIRE dungeon.
	
	
}//end Dungeon class