

/*
 * Title: Hero.java
 *
 * Description: Abstract base class for a hierarchy of heroes.  It is derived
 *  from DungeonCharacter.  A Hero has battle choices: regular attack and a
 *  special skill which is defined by the classes derived from Hero.
 *
 *  class variables (all are directly accessible from derived classes):
 *    chanceToBlock -- a hero has a chance to block an opponents attack
 *    numTurns -- if a hero is faster than opponent, their is a possibility
 *                for more than one attack per round of battle
 *
 *  class methods (all are public):
 *    public Hero(String name, int hitPoints, int attackSpeed,
				     double chanceToHit, int damageMin, int damageMax,
					 double chanceToBlock)
	  public void readName()
	  public boolean defend()
	  public void subtractHitPoints(int hitPoints)
	  public void battleChoices(DungeonCharacter opponent)

 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */


public abstract class Hero extends DungeonCharacter
{
	protected double chanceToBlock;
	protected int healthPotions, visionPotions, ooPillars; //Fundamental Hero info
	protected int numTurns;

/*-----------------------------------------------------------------
calls base constructor and gets name of hero from user*/

  public Hero(String name, int hitPoints, int attackSpeed, double chanceToHit, int damageMin, int damageMax, double chanceToBlock)
  {
	  super(name, hitPoints, attackSpeed, chanceToHit, damageMin, damageMax);
	  this.chanceToBlock = chanceToBlock;
	  this.healthPotions = 0;
	  this.visionPotions = 0;
	  this.ooPillars = 0;
	  readName();
  }

  public int getNumTurns() { return this.numTurns; } //Added for SurpriseAttack class to get numTurns. (Nick 6/4/20)
  
  public void setNumTurns(int newNumTurns) { this.numTurns = newNumTurns; } //Added for SurpriseAttack class to set numTurns. (Nick 6/4/20)
  
/*-------------------------------------------------------
readName obtains a name for the hero from the user

Receives: nothing
Returns: nothing

This method calls: nothing
This method is called by: hero constructor
---------------------------------------------------------*/
  public void readName()
  {
		System.out.print("\tEnter character name: ");
		name = Keyboard.readString();
  }//end readName method

  
/****************************************************
 * toString() displays fundamental hero information *
 ****************************************************/
  public String toString() {
	  return "\n\t        -" + this.name + "'s Stats-" 
	  + " \n\t     | Hitpoints: " + this.hitPoints 
	  + " \n\t     | Healing Potions: " + this.healthPotions
	  + " \n\t     | Vision Potions: " + this.visionPotions
	  + " \n\t     | OO Pillars found: " + this.ooPillars + "\n";
}//end toString method
  
  
/*-------------------------------------------------------
defend determines if hero blocks attack

Receives: nothing
Returns: true if attack is blocked, false otherwise

This method calls: Math.random()
This method is called by: subtractHitPoints()
---------------------------------------------------------*/
  public boolean defend()
  {
		return Math.random() <= chanceToBlock;

  }//end defend method

/*-------------------------------------------------------
subtractHitPoints checks to see if hero blocked attack, if so a message
is displayed, otherwise base version of this method is invoked to
perform the subtraction operation.  This method overrides the method
inherited from DungeonCharacter promoting polymorphic behavior

Receives: hit points to subtract
Returns: nothing

This method calls: defend() or base version of method
This method is called by: attack() from base class
---------------------------------------------------------*/
public void subtractHitPoints(int hitPoints)
	{
		if (defend())
		{
			System.out.println(name + " BLOCKED the attack!");
		}
		else
		{
			super.subtractHitPoints(hitPoints);
		}


	}//end method

/*-------------------------------------------------------
battleChoices will be overridden in derived classes.  It computes the
number of turns a hero will get per round based on the opponent that is
being fought.  The number of turns is reported to the user.  This stuff might
go better in another method that is invoked from this one...

Receives: opponent
Returns: nothing

This method calls: getAttackSpeed()
This method is called by: external sources
---------------------------------------------------------*/
	public void battleChoices(DungeonCharacter opponent)
	{
	    numTurns = attackSpeed/opponent.getAttackSpeed();
		if (numTurns == 0)
			numTurns++;
		System.out.println("Number of turns this round is: " + numTurns);
		
		int choice;

		do
		{
		    System.out.println("1. Attack Opponent");
		    System.out.println("2. " + this.specialBehavior.toString());
		    System.out.println("3. Use Health Potion" + " ["+ this.healthPotions +" available]");
		    System.out.print("Choose an option: ");
		    choice = Keyboard.readInt();

		    switch (choice)
		    {
			    case 1: attack(this, opponent);
			        break;
			    case 2: this.specialBehavior.attack(this, opponent);
			        break;
			    case 3: 
			    	if(this.healthPotions == 0)
			    	{
					System.out.println("You look in your potion bag and admire the dust...\n\t>No health potions available\n");
					break;
			    	} else
						this.hitPoints += hpPotion();
		    			System.out.println("\n*Health Potion consumed*\n" + this.name + "'s health is now " + this.hitPoints + "\n");
			    		break;
			    default:
			        System.out.println("invalid choice!");
		    }//end switch

			numTurns--;
			if (numTurns > 0)
			    System.out.println("Number of turns remaining is: " + numTurns);

		} while(numTurns > 0);

	}//end battleChoices

	
/** Player choice methods **/	
	public void playerInventory(Hero player)
	{
		System.out.println("1) Use Health Potion   "
						 + "2) Use Vision Potion   "
						 + "3) Continue");
		int choice = Keyboard.readInt();
		while(choice > 1 || choice < 3) {
			switch(choice)
			{
				case 1:
					if(this.healthPotions > 0)
					{
						int prevHP = this.hitPoints;
						this.hitPoints += hpPotion();
						System.out.println("You wipe the red elixir from your lips, health went up " 
												+ (this.hitPoints - prevHP) + " points");
						System.out.println("\t>Current Hitpoints: " + this.hitPoints);
						return;
					}else
						System.out.println("You look in your potion bag and admire the dust...\n\t>No health potions available");
						return;
					
				case 2:
					if(this.visionPotions > 0)
					{
						this.visionPotions--;
						//Mia is doing this	
					return;
					} else
						System.out.println("You look in your potion bag and admire the dust...\n\t>No vision potions available");
					return;
				case 3:
					return;
					
				default:
					System.out.println("Invalid choice, try again");
					System.out.println("1) Use Health Potion   "
							 + "2) Use Vision Potion   "
							 + "3) Continue");
					choice = Keyboard.readInt();
			}
		}
	}
	
	
/** Health potion methods **/		
private int hpPotion() { this.healthPotions--; return (int)( Math.random() * 11) + 5; }


/** Player tracking methods **/	

	
}//end Hero class