import java.security.Key;
import java.util.*;

public class DungeonAdventure {

	
	private static int roomEntries = 0;
	private static boolean quitGame = false;
	private Dungeon dungeon;
	private Hero player;
	
	public static void main(String[] args) 
	{
		DungeonAdventure curGame = new DungeonAdventure();
			curGame.player = null;
			curGame.dungeon = null;
		

		/** Could we implement the save game feature here? --Austin**/
		gameTutorial();
		
		
		curGame.player = Dungeon.chooseHero();
		
		curGame.dungeon = new Dungeon();
		int[] entranceCoords = {0,0};
		int[] exitCoords = {0,0};
		int[] pillarCoords = new int[8];
		
		generatePortals(entranceCoords, exitCoords);	//Entrances & Exits placed & values set...
		generatePillars(pillarCoords, entranceCoords, exitCoords); //Pillar Coordinates selected and set...
		generateROOMS(curGame.dungeon, pillarCoords, entranceCoords, exitCoords, curGame.player); //Begins filling rooms with appropriate GAME OBJECTS
		System.out.println(curGame.dungeon);
		while(curGame.player.isAlive()&&!quitGame)
		{
			System.out.println(curGame.player.stats());
			movementCommand(curGame.dungeon, curGame.player);
			evaluateRoom(curGame.dungeon, curGame.player);
		}
		if(!curGame.player.isAlive()) {
			displayDeathScreen(curGame.dungeon);
		}
			
	}
	
	public static void gameTutorial() 
	{
		//DEBUG: REMOVE ONCE MAIN MENU IS DONE
		System.out.println("\n-------------------Symbol Examples---------------------");
		System.out.println("  Multiple Items = M      |  Empty room = E");
		System.out.println("  Entrance = I            |  Exit = O");
		System.out.println("  Monster = X             |  Pit = P");
		System.out.println("  Healing Potion = H      |  Vision Potion = V");
		System.out.println(" ");
		System.out.println(" -----------------Pillars to be Found-------------------");
		System.out.println("  Pillar Abstraction = PA |  Pillar Encapsulation = PE");
		System.out.println("  Pillar Inheritance = PI |  Pillar Polymorphism = PP");
		System.out.println("  ");
		System.out.println("  ");
		System.out.println("                   Example of Room");
		System.out.println("\t\t\t* - *\r\n" 
						+ "\t\t\t| P | \n\t\t\t* - *\n--------------------------------------------------------\n");
													
	 }//end of GameTutorial
	
	  /** Game introduction Text **/
	 public static void gameIntroduction() {
		System.out.println("_______________________________" + "\nWELCOME TO DUNGEON ADVENTURE!\n\n");
		System.out.println("Your quest to seek the four pillars of O.O. has brought you"
				         + "\nto the foulest, most dankest dungeon in all the land...\n  ");
		
		System.out.println("To escape the dungeon, you must brave the monsters, dodge the pits, "
						 + "\nand seek the four Pillars of O.O.\n");
																	//"or press L to load a saved game."
	 }

	 /**-----------------------------------------------------------------------------------------------
	  
	  - Austin Lidey 06/08/2020, Mia Hunt 06/10/2020 
	  -------------------------------------------------------------------------------------------------*/
	 private static void evaluateRoom(Dungeon dungeon, Hero hero) 
	 {	int row = hero.getRow(), col = hero.getCol();
		 if(dungeon.dungeonRooms[hero.getRow()][hero.getCol()].hasHealthPOTION())
		 {
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setHasHealthPOTION(false);
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].minusItem();
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setItem();
			 hero.setHealthPotions(hero.getHealthPotions()+1);;
			 System.out.println("\n***Health potion found!***");
			 
		 }
		 if(dungeon.dungeonRooms[hero.getRow()][hero.getCol()].hasVisionPOTION())
		 {
			dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setHasVisionPOTION(false);
			dungeon.dungeonRooms[hero.getRow()][hero.getCol()].minusItem();
			dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setItem();
			hero.setVisionPotions(hero.getVisionPotions()+1); 
			System.out.println("\n***Vision potion found!***");
			 
		 }
		 if(dungeon.dungeonRooms[hero.getRow()][hero.getCol()].hasPILLAR_A())
		 {
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setHasPILLAR_A(false);
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].minusItem();
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setItem();
			 hero.setOoPillars(hero.getOoPillars()+1);
			 System.out.println("\n***(" + hero.getOoPillars() + "/4) Pillar of Abstraction found!***");
	 
		 }
		 if(dungeon.dungeonRooms[hero.getRow()][hero.getCol()].hasPILLAR_E())
		 {
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setHasPILLAR_E(false);
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].minusItem();
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setItem();
			 hero.setOoPillars(hero.getOoPillars()+1);
			 System.out.println("\n***(" + hero.getOoPillars() + "/4) Pillar of Encapsulation found!***");
		 }
		 if(dungeon.dungeonRooms[hero.getRow()][hero.getCol()].hasPILLAR_I())
		 {
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setHasPILLAR_I(false);
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].minusItem();
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setItem();
			 hero.setOoPillars(hero.getOoPillars()+1);
			 System.out.println("\n***(" + hero.getOoPillars() + "/4) Pillar of Inheritance found!***");
		 }
		 if(dungeon.dungeonRooms[hero.getRow()][hero.getCol()].hasPILLAR_P())
		 {
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setHasPILLAR_P(false);
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].minusItem();
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setItem();
			 hero.setOoPillars(hero.getOoPillars()+1);
			 System.out.println("\n***(" + hero.getOoPillars() + "/4) Pillar of Polymorphism found!***");
		 }
		 if(dungeon.dungeonRooms[row][col].hasPIT())
		{
			int pitDamage = (int)( Math.random() * 21);
			System.out.println("\nYou fell into a pit, taking " + pitDamage + " points of damage!");
			hero.setHitPoints(hero.getHitPoints() - pitDamage);
		}
		 if(dungeon.dungeonRooms[hero.getRow()][hero.getCol()].hasMONSTER())
		 {
			 System.out.println("***MONSTER!!!***\n");
			 System.out.println("You have stumbled upon a room with a monster, fight to the death!");
			 Monster theMonster = MonsterFactory.generateMonster();
			 Dungeon.battle(hero, theMonster);
			 dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setHasMONSTER(theMonster.isAlive());
			 if(hero.isAlive()) {
				dungeon.dungeonRooms[hero.getRow()][hero.getCol()].minusItem();
			 	dungeon.dungeonRooms[hero.getRow()][hero.getCol()].setItem();
			 	hero.setHealthPotions(theMonster.dropHealthPotion());
			 }
		 }
		 if(dungeon.dungeonRooms[hero.getRow()][hero.getCol()].isEXIT())
		 {
			 roomEntries++;
			 if(roomEntries == 1) 
			 {
				 System.out.println("You enter a room through a door labeled \"Portal Room\" to discover\n"
								  + "a large obsidian structure -- a dead portal. It can be restored by finding\n"
								  + "the Four Pillars of OO. This is your ticket home!\n\n");
			 //Add check for existing pillars to be placed
			 }
			 if(roomEntries > 1 && hero.getOoPillars() < 4)
			 {
				 System.out.println("You re-enter the portal room, but without the pillars, you cannot use it.\n"
								  + "Tip: Did you try looking up yet?");
			 }
			 if(hero.getOoPillars() == 4)
			 {
				System.out.println("Woah, you found all the pillars! Good job, the portal has now been activated.\n"
								  + "\t>>" + hero.getName() + "steps into the purple glow of the obsidian portal, fading from the dungeon");
				//Game is over, need to figure out the right way to handle this
				System.out.print("\nYou beat the game! Want to play again(y/n)?\n>");
				char choice = Keyboard.readChar();
				if(choice == 'Y' || choice == 'y')
				{
					setQuitGame(true);
				} else
					System.exit(0);
				}
		 }
		 
	 }
	 
	 /**-----------------------------------------------------------------------------------------------
	  
	  - Austin Lidey 06/08/2020, Mia Hunt 06/10/2020 
	  -------------------------------------------------------------------------------------------------*/
	 private static void generatePortals(int[] entrance, int[] exit)
	 {
		 entrance[0] = 0; // Sets entrance row coordinate
		 entrance[1] = (int)(Math.random() * 5); // sets entrance col coordinate
		 exit[0] = 4; // Sets exit row coordinate
		 exit[1] = (int)(Math.random() * 5); // Sets exit col coordinate
	 }
	 
	 /** --------------------------------------------------------------------------------------------
	   Randommly generates pillar coordinates while checks to make sure it's not an entrance, exit, 
	   or an existing pillar coordinate.
	   - Austin Lidey 06/08/2020, Mia Hunt 06/10/2020
	  -----------------------------------------------------------------------------------------------*/
	 public static void generatePillars(int[] pillarCoords, int [] entrance, int[] exit)
	{

		for(int index = 0; index < pillarCoords.length - 1; index++)
		{
			boolean ValidCoords = false;
			int pillarRow = 0, pillarCol = 0;
			while(!ValidCoords)
			{
			pillarRow = (int)(Math.random() * 5);
			pillarCol = (int)(Math.random() * 5);
			
				if(( pillarRow != entrance[0] || pillarCol != entrance[1]) && (pillarRow != exit[0] || pillarCol != exit[1]))
				{	//It's not at the entrance or exit, but is it the same spot as a previous pillar?
					if(!samePillarCoords(pillarRow, pillarCol, pillarCoords))
					{
						ValidCoords = true; 	//Valid coords to place in Pillar, becase there's no portals or Pillars :-)
					}
				}
			}	//We now have valid coordinates
			pillarCoords[index] = pillarRow;
			pillarCoords[index + 1] = pillarCol;
			index++;
		}

	}

	 /**-----------------------------------------------------------------------------------------------
	  Checks if randomly generated integer pillar coordinates are valid by comparing to
	  the array of already created pillar coordinates to be sure no coordinates are the same.
	  - Austin Lidey 06/08/2020, Mia Hunt 06/10/2020 
	  -------------------------------------------------------------------------------------------------*/
	  private static boolean samePillarCoords(int pillarRow, int pillarCol, int[] pillarCoords)
	  {
		 for(int index = 0; index < pillarCoords.length - 1; index++) {    
			 if(index % 2 == 0) { // Checks for row coordinate in the even index.
				  if(pillarCoords[index] == pillarRow && pillarCoords[index + 1] == pillarCol) {
					  return true;
				  }
			 } 
		  }
		  return false;
	  }
	 
	 /**-----------------------------------------------------------------------------------------------
	  Generates dungeon rooms by placing entrance, exit, pillars of OO, and items.
	  - Austin Lidey 06/08/2020, Mia Hunt 06/10/2020 
	  -------------------------------------------------------------------------------------------------*/
	 private static void generateROOMS(Dungeon dungeon, int[] pillarCoords, int[] entrance, int exit[], Hero hero)
	 {
		 for(int row = 0; row < 5; row++)
		 {
			 for(int col = 0; col < 5; col++)
			 {
				 if(row == entrance[0] && col == entrance[1])
				 {
				   //Then only place the entrance, nothing else!
					 dungeon.dungeonRooms[row][col] = new Room();
					 dungeon.dungeonRooms[row][col].setRoomCoords(row, col);
					 dungeon.dungeonRooms[row][col].setIsEntrance(true);
					 hero.setPosition(row, col);
					 continue;
				 }
				 if(row == exit[0] && col == exit[1])
				 {
				   //Then only place the exit, nothing else!
					 dungeon.dungeonRooms[row][col] = new Room();
					 dungeon.dungeonRooms[row][col].setRoomCoords(row, col);
					 dungeon.dungeonRooms[row][col].setIsExit(true);
					 continue;
				 }
				 else {
				   //Valid spot to place GAME OBJECTS
					 if(samePillarCoords(row, col, pillarCoords))
					 {
						 //Valid pillarCoords, therefore place pillar here!
						 dungeon.dungeonRooms[row][col] = new Room();
						 dungeon.dungeonRooms[row][col].setRoomCoords(row, col);
						 dungeon.dungeonRooms[row][col].placeRandomPillar();
						 dungeon.dungeonRooms[row][col].generateRoomItems();
						 continue;
					 }
					 else {
						 //General case for placing items -- No pillars, no entrance, no exit. Wildcard. 
					 dungeon.dungeonRooms[row][col] = new Room();
					 dungeon.dungeonRooms[row][col].setRoomCoords(row, col);
					 dungeon.dungeonRooms[row][col].generateRoomItems();
					 }
				}		
			}
		}
	 }
	 
	 /**-----------------------------------------------------------------------------------------------
	  
	  - Austin Lidey 06/08/2020, Mia Hunt 06/10/2020 
	  -------------------------------------------------------------------------------------------------*/
	 private static void moveHero(String direction, Hero hero)
	 {
		 int row = hero.getRow(); int col = hero.getCol();
		 //This will change the X/Y coords of the hero
		 switch(direction.toLowerCase())
		 {
			 case "n":
				 if(row == 0)
				 {
					 System.out.println("\nAlas, it appears that there's a wall made of mineral in my path... \n\tLet's try another direction!\n");
				 }else 
				 hero.setRow(row - 1);;
				 return;
			 case "e":
				 if(col == 4)
				 {
					 System.out.println("\nAlas, it appears that there's a wall made of mineral in my path... \nLet's try another direction!\n");
				 }else 
				 hero.setCol(col + 1);
				 return;
			 case "s": 
				 if(row == 4)
				 {
					 System.out.println("\nAlas, it appears that there's a wall made of mineral in my path... \nLet's try another direction!\n");
				 }else 
				 hero.setRow(row + 1);
				 return;
			 case "w": 
				 if(col == 0)
				 {
					 System.out.println("\nAlas, it appears that there's a wall made of mineral in my path... \nLet's try another direction!\n");
				 }else 
				 hero.setCol(col - 1);
				 return;
			case "i":
				 hero.playerInventory(hero);
		 }
	 }
	 
	 /**-----------------------------------------------------------------------------------------------
	  
	  - Austin Lidey 06/08/2020, Mia Hunt 06/10/2020 
	  -------------------------------------------------------------------------------------------------*/
	 private static void movementCommand(Dungeon dungeon, Hero hero)
	 {
		 String direction;
		 System.out.println(dungeon.dungeonRooms[hero.getRow()][hero.getCol()].displayStat());	//Displays the current room of the Hero
		 System.out.print("(i = inventory, n = north, s = south, e = east, w = west)\nEnter a command:\n>>");
		 direction = Keyboard.readString();
		 moveHero(direction, hero);
	 }
	 
	/**-----------------------------------------------------------------------------------------------
	 
	- Austin Lidey 06/08/2020, Mia Hunt 06/10/2020 
	-------------------------------------------------------------------------------------------------*/
	 private static void displayDeathScreen(Dungeon dungeon) 
	 {
		System.out.println("Dang dude, you suck\n" + dungeon +"\nQuitting...");
		System.exit(0);
	 }

	 /**-----------------------------------------------------------------------------------------------
	  
	 - Austin Lidey 06/08/2020, Mia Hunt 06/10/2020 
	  -------------------------------------------------------------------------------------------------*/
	 private static void setQuitGame(boolean quitGame1) {
		 quitGame = quitGame1;
	 }
 }//end of CLASS 
