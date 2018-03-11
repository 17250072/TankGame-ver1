package GameObject;

import MapAndOperation.*;
import java.util.ArrayList;
import processing.core.PImage;

public class Robot extends Tank {
	private int xend;
	private int yend;
	private Path pathrecord;
	private ArrayList<PointRecordPath> pathArrayListResult = new ArrayList<PointRecordPath>();
	private int[][] pathnotinverse;
	private int robotfindpathlock = 0;
	private String imagetype;
	private boolean ishitted=false;
	
	

	public Robot(int xinitial, int yinitial, MapRecordObject m, Player P) {
		super(xinitial, yinitial, m);
		xend = P.getX();
		yend = P.getY();
		super.setRealPosition(super.calculateRealPosition(getX(), getY(), m)[0],
				super.calculateRealPosition(getX(), getY(), m)[1]);

		pathrecord = new Path(m);
		pathnotinverse = new int[m.getLen()][m.getLen()];
		imagetype = "left";
	}

	public void findPath(MapRecordObject m, Player p) {
		// insert the beginning to the grey point array

		if (pathArrayListResult.isEmpty() == false) {
			pathArrayListResult.clear();
		}
		pathrecord.clear(m);

		pathrecord.setInitial(this.getX(), this.getY());
		xend = p.getX();
		yend = p.getY();

		while (true) {
			if (pathrecord.getGreyPoint().isEmpty() == true) {
				System.out.println("The path is not found! please check!");
				break;
			}
			pathrecord.visitFirstGrey();
			if (pathrecord.getBlackPoint().getX() == xend && pathrecord.getBlackPoint().getY() == yend) {
				for (PointRecordPath Ptemp = pathrecord.getBlackPoint(); Ptemp.getFatherPoint() != null; Ptemp = Ptemp
						.getFatherPoint()) {
					pathArrayListResult.add(Ptemp);
				}
				break;
			} else {
				pathrecord.findAndAddGreyPoints(m);
			}
		}
	}

	public void goReal(MapRecordObject map) {
		if (this.getDirection() == "right" && map.getRightPoint(getX(), getY()) != "player"
				&& map.getRightPoint(getX(), getY()) != "robot") {
			super.moveTankPerUnit("x", 3);
		} else if (this.getDirection() == "left" && map.getLeftPoint(getX(), getY()) != "player"
				&& map.getLeftPoint(getX(), getY()) != "robot") {
			super.moveTankPerUnit("x", -3);
		} else if (this.getDirection() == "up" && map.getUpPoint(getX(), getY()) != "player"
				&& map.getUpPoint(getX(), getY()) != "robot") {
			super.moveTankPerUnit("y", -3);
		} else if (this.getDirection() == "down" && map.getDownPoint(getX(), getY()) != "player"
				&& map.getDownPoint(getX(), getY()) != "robot") {
			super.moveTankPerUnit("y", 3);
		} else if (this.getDirection() == "stop") {
			// do nothing
		}
	}

	public void robotMove(MapRecordObject m, Player p, MapRecordObject map) {
		for (int i = 1; i < pathArrayListResult.size(); i++) {
			pathnotinverse[pathArrayListResult.get(i).getY()][pathArrayListResult.get(i).getX()] = 2;
			pathnotinverse[p.getY()][p.getX()] = 3;
		}

		if (pathnotinverse[super.getY()][super.getX()] == 3) {
			super.changeDirection("stop");
		} else if (pathnotinverse[super.getY()][super.getX() + 1] == 2) {
			super.changeDirection("right");
		} else if (pathnotinverse[super.getY()][super.getX() - 1] == 2) {
			super.changeDirection("left");
		} else if (pathnotinverse[super.getY() - 1][super.getX()] == 2) {
			super.changeDirection("up");
		} else if (pathnotinverse[super.getY() + 1][super.getX()] == 2) {
			super.changeDirection("down");
		} else {
			super.changeDirection("stop");
			System.out.println("error! the robotmove cannot find the path!");
		}
		goReal(map);

		if (super.getXReal() - super.calculateRealPosition(super.getX(), super.getY(), m)[0] >= m.getCellWidth()
				&& map.getRightPoint(getX(), getY()) != "player" && map.getRightPoint(getX(), getY()) != "robot") {
			super.goRight(m);
			pathnotinverse[this.getY()][this.getX() - 1] = 0;
		} else if (super.calculateRealPosition(super.getX(), super.getY(), m)[0] - super.getXReal() >= m.getCellWidth()
				&& map.getLeftPoint(getX(), getY()) != "player" && map.getLeftPoint(getX(), getY()) != "robot") {
			super.goLeft(m);
			pathnotinverse[this.getY()][this.getX() + 1] = 0;
		} else if (super.getYReal() - super.calculateRealPosition(super.getX(), super.getY(), m)[1] >= m.getCellWidth()
				&& map.getDownPoint(getX(), getY()) != "player" && map.getDownPoint(getX(), getY()) != "robot") {
			super.goDown(m);
			pathnotinverse[this.getY() - 1][this.getX()] = 0;
		} else if (super.calculateRealPosition(super.getX(), super.getY(), m)[1] - super.getYReal() >= m.getCellWidth()
				&& map.getUpPoint(getX(), getY()) != "player" && map.getUpPoint(getX(), getY()) != "robot") {
			super.goUp(m);
			pathnotinverse[this.getY() + 1][this.getX()] = 0;
		}
	}

	public int getRobotFindPathLock() {
		return robotfindpathlock;
	}

	public void setRobotFindPathLock(int value) {
		if (value != 1 && value != 0) {
			System.out.println("The input value of teh setRobotFindPathLock is not correct! please check!");
		} else {
			robotfindpathlock = value;
		}
	}

	public void changeImageType(String imagetypenew) {
		if (imagetypenew != "left" && imagetypenew != "right" && imagetypenew != "up" && imagetypenew != "down") {
			System.out.println("The imagetypenew in the changeImageType is not correct!");
		} else {
			imagetype = imagetypenew;
		}
	}

	public String getImageType() {
		return imagetype;
	}
	
	public boolean getIshitted() {
		return ishitted;
	}
	
	public void changeToHitted() {
		ishitted=true;
	}

}