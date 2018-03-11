package GameManager;
import MapAndOperation.*;
import GameObject.*;
import processing.core.PApplet;
import processing.core.PImage;

public class GameManager extends PApplet {
	public static void main(String args[]) {
		PApplet.main("GameManager.GameManager");
	}

	// the global variables:
	// ________________________________________________BEGINGLOBALVARIABLES_______________________________________//
	PImage start;
	PImage botton_start;
	PImage botton_instruction;
	PImage robot_left_img;
	PImage robot_right_img;
	PImage robot_up_img;
	PImage robot_down_img;
	PImage robot_img;
	PImage wall_img;
	PImage player_img;
	PImage playerl_img;
	PImage playerr_img;
	PImage playeru_img;
	PImage playerd_img;
	PImage back_img;
	PImage bullet_img;
	

	MapRecordObject map = new MapRecordObject(1, 1, 18, 18, 600);
	//Robot robot;
	Player player;
	RobotCollectionAndManager robotcollection;//************
	int robotnumber=5;//**********************
	
	//bullet list:
	BulletCollectionAndManager bulletlist;

	//private int robotfindpathlock = 0;
	private int playerkeyloadlock = 0;
	// ________________________________________________ENDGLOBALVARIABLES_________________________________________//

	public void settings() {
		size(600, 600);
	}

	public void setup() {
		wall_img = loadImage("wall.png");
		start = loadImage("start.jpg");
		botton_start = loadImage("botton_start.jpg");
		botton_instruction = loadImage("botton_instruction.png");
		robot_left_img = loadImage("robotl.png");
		robot_right_img = loadImage("robotr.png");
		robot_up_img = loadImage("robotu.png");
		robot_down_img = loadImage("robotd.png");
		robot_img = loadImage("robotl.png");
		player_img = loadImage("robotl.png");
		playerl_img = loadImage("robotl.png");
		playerr_img = loadImage("robotr.png");
		playeru_img = loadImage("robotu.png");
		playerd_img = loadImage("robotd.png");
		back_img=loadImage("back.jpg");
		bullet_img=loadImage("bullet.png");
		
		

		imageMode(CENTER);
		player = new Player(1, 1, map);
		//robot = new Robot(18, 18, map, player);
		robotcollection=new RobotCollectionAndManager(robotnumber,map,player);  //********
		bulletlist=new BulletCollectionAndManager(robotcollection);
	}

	
	public void draw() {
		background(0);
		
		robotcollection.calculateOperationLock();  //***************
		player.calculatePlayerKeyLoadLock();
		
		if (player.reachPoint(map) == true) {
			playerkeyloadlock = 0;
		} else {
			playerkeyloadlock = 1;
		}
		
		
		// __________________________________________End lock
		// calculation___________________________________________//
		
		
		
		
		
		
		//_____________________________________Graph component of the player and the robot_____________//
		if(player.getDirection()=="left") {
			player_img=playerl_img;
		}else if(player.getDirection()=="right") {
			player_img=playerr_img;
		}else if(player.getDirection()=="up") {
			player_img=playeru_img;
		}else if(player.getDirection()=="down") {
			player_img=playerd_img;
		}else {
			
		}
		robotGraphCalculation();
		//______________________________________End of the Graph component____________________________________//
		
		
		
		
		//__________________________________the main operation of the program__________________________________//
		//print the map
		printmap();
		
		
		//check the hit of the robot:
		robotcollection.checkAllIsHitted();
		//check of the player:(now empty)
		
		//print the robot:
		allRobotPrint();
		//print the player:
		playerPrint();
		//print the bullet:
		bulletprint();
		
		
		//the path finding of the robot list:
		robotcollection.allFindPath();
		//move the position of the robot list:
		robotcollection.allMove();
		//move the player's position:
		player.move(map);
		//move of the bullet:
		bulletlist.moveAllBullet();
		
		
		//hit of the bullet check:
		bulletlist.hitObjectCheckAndOperation();
		
	}

	
	
	
	
	
	
	
	
	
	public void printmap() {
		for (int i1 = 0; i1 < map.getLen(); i1++) {
			for (int i2 = 0; i2 < map.getLen(); i2++) {
				if (map.maprecordobjectinitial[i1][i2] == 1) {
					image(wall_img, map.getCellWidth() / 2 + i2 * map.getCellWidth(),
							map.getCellWidth() / 2 + i1 * map.getCellWidth(), map.getCellWidth(), map.getCellWidth());
				}
			}
		}
	}


	public void playerPrint() {
		image(player_img, player.getXReal(), player.getYReal(), map.getCellWidth(), map.getCellWidth());
	}

	public void keyPressed() {
		if (playerkeyloadlock == 0) {
			if (keyCode == UP&&map.getUpPoint(player.getX(), player.getY())!="wall"&&map.getUpPoint(player.getX(), player.getY())!="robot") {
				player.changeDirection("up");
				player.setImageType("up");
			} else if (keyCode == DOWN&&map.getDownPoint(player.getX(), player.getY())!="wall"&&map.getDownPoint(player.getX(), player.getY())!="robot") {
				player.changeDirection("down");
				player.setImageType("down");
			} else if (keyCode == RIGHT&&map.getRightPoint(player.getX(), player.getY())!="wall"&&map.getRightPoint(player.getX(), player.getY())!="robot") {
				player.changeDirection("right");
				player.setImageType("right");
			} else if (keyCode == LEFT&&map.getLeftPoint(player.getX(), player.getY())!="wall"&&map.getLeftPoint(player.getX(), player.getY())!="robot") {
				player.changeDirection("left");
				player.setImageType("left");
			} else {
				// no action
			}
		}
		if(keyCode==SHIFT) {
			player.shoot(bulletlist);
		}
	}
	
	public void allRobotPrint() {
		for(int i=0;i<robotcollection.getNumberOfRobot();i++) {
			if(robotcollection.getRobot(i).getImageType()=="left") {
				image(robot_left_img,robotcollection.getRobot(i).getXReal(),robotcollection.getRobot(i).getYReal());
			}else if(robotcollection.getRobot(i).getImageType()=="right") {
				image(robot_right_img,robotcollection.getRobot(i).getXReal(),robotcollection.getRobot(i).getYReal());
			}else if(robotcollection.getRobot(i).getImageType()=="up") {
				image(robot_up_img,robotcollection.getRobot(i).getXReal(),robotcollection.getRobot(i).getYReal());
			}else if(robotcollection.getRobot(i).getImageType()=="down") {
				image(robot_down_img,robotcollection.getRobot(i).getXReal(),robotcollection.getRobot(i).getYReal());
			}
		}
	}
	
	public void robotGraphCalculation() {
		for (int i = 0; i < robotcollection.getNumberOfRobot(); i++) {
			if (robotcollection.getRobot(i).getDirection() == "left") {
				robotcollection.getRobot(i).changeImageType("left");;
			} else if (robotcollection.getRobot(i).getDirection() == "right") {
				// robot_img=robot_right_img;
				robotcollection.getRobot(i).changeImageType("right");;
			} else if (robotcollection.getRobot(i).getDirection() == "up") {
				// robot_img=robot_up_img;
				robotcollection.getRobot(i).changeImageType("up");;
			} else if (robotcollection.getRobot(i).getDirection() == "down") {
				// robot_img=robot_down_img;
				robotcollection.getRobot(i).changeImageType("down");;
			} else {

			}
		}
	}
	
	public void bulletprint() {
		for(int i=0;i<bulletlist.getBulletNumber();i++) {
			image(bullet_img,bulletlist.getBullet(i).getXReal(),bulletlist.getBullet(i).getYReal(),map.getCellWidth(),map.getCellWidth());
		}
	}
}