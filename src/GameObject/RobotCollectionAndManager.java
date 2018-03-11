package GameObject;

import java.util.ArrayList;
import java.math.*;
import MapAndOperation.*;
import processing.core.PImage;

public class RobotCollectionAndManager {
	ArrayList<Robot> robotcollection = new ArrayList<Robot>();
	private int numberofrobot = 0;
	Player player;
	MapRecordObject map;
	String imagetype;

	public RobotCollectionAndManager(int number, MapRecordObject mapnew, Player playernew) {
		numberofrobot = number;
		player = playernew;
		map = mapnew;
		createRobotAndAddToCollection();
		imagetype="left";
	}

	public int getNumberOfRobot() {
		return numberofrobot;
	}

	private void createRobotAndAddToCollection() {

		int xnew=0;
		int ynew=0;
		
		for (int i = 0; i < numberofrobot; i++) {
			int override=0;
			while (map.getPoint(xnew, ynew) != "road"||override==1) {
				xnew = (int) (Math.random() * map.getLen());
				ynew = (int) (Math.random() * map.getLen());
				for(int j=0;j<i;j++) {
					if(xnew==robotcollection.get(j).getX()&&ynew==robotcollection.get(j).getY()) {
						override=1;
					}
				}
			}

			Robot robotunit = new Robot(xnew, ynew, map, player);
			robotcollection.add(robotunit);
		}
	}


	public void calculateOperationLock() {
		for (int i = 0; i < numberofrobot; i++) {
			if (robotcollection.get(i).reachPoint(map) == true) {
				robotcollection.get(i).setRobotFindPathLock(0);
			} else {
				robotcollection.get(i).setRobotFindPathLock(1);
			}
		}
	}

	public void allFindPath() {
		for (int i = 0; i < numberofrobot; i++) {
			if (robotcollection.get(i).getRobotFindPathLock() == 0) {
				robotcollection.get(i).findPath(map, player);
			}
		}
	}

	public void allMove() {
		for (int i = 0; i < numberofrobot; i++) {
			robotcollection.get(i).robotMove(map, player, map);
		}
	}

	public Robot getRobot(int i) {
		return robotcollection.get(i);
	}
	
	public void checkAllIsHitted() {
		for(int i=0;i<numberofrobot;i++) {
			if(robotcollection.get(i).getIshitted()==true) {
				robotcollection.remove(i);
				numberofrobot--;
			}
		}
	}
}