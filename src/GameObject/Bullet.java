package GameObject;
import java.math.*;
import MapAndOperation.*;

public class Bullet {
	private float xreal;
	private float yreal;
	private String direction; // move direction
	private boolean ishitobject = false;
	private MapRecordObject map;

	public Bullet(float xrealinitial, float yrealinitial, String directioninitial,MapRecordObject mapnew) {
		xreal = xrealinitial;
		yreal = yrealinitial;
		if (directioninitial != "left" && directioninitial != "right" && directioninitial != "up"
				&& directioninitial != "down") {
			System.out.println("The direction of the bullet is not correct! please check!");
		} else {
			direction = directioninitial;
		}
		map=mapnew;
	}

	public float getXReal() {
		return xreal;
	}

	public float getYReal() {
		return yreal;
	}

	public String getDirection() {
		return direction;
	}

	private void moveRealRight() {
		xreal+=5;
	}

	private void moveRealLeft() {
		xreal-=5;
	}

	private void moveRealUp() {
		yreal-=5;
	}

	private void moveRealDown() {
		yreal+=5;
	}

	private void changeIsHitObjectToTrue() {
		if (ishitobject == true) {
			System.out.println("The ishitobject is already true! please check");
		} else {
			ishitobject = true;
		}
	}

	public void bulletMove() {
		if (direction == "left") {
			moveRealLeft();
		} else if (direction == "right") {
			moveRealRight();
		} else if (direction == "up") {
			moveRealUp();
		} else if (direction == "down") {
			moveRealDown();
		}else {
			System.out.println("The bulletmove's direction is not correct! check!");
		}
	}
	
	public void hitTankAllCheck(RobotCollectionAndManager list) {
		for(int i=0;i<list.getNumberOfRobot();i++) {
			if(Math.abs(this.getXReal()-list.getRobot(i).getXReal())<map.getCellWidth()&&Math.abs(this.getYReal()-list.getRobot(i).getYReal())<map.getCellWidth()) {
				list.getRobot(i).changeToHitted();
				changeIsHitObjectToTrue();
			}
		}
	}
	
	public void hitWallAllCheck() {
		if(map.maprecordobjectinitial[(int)(this.getYReal()/(float)map.getCellWidth())][(int)(this.getXReal()/(float)map.getCellWidth())]==1) {
			changeIsHitObjectToTrue();
			System.out.println("**********************");
		}
	}
	
	
	public boolean getIsHitted() {
		return ishitobject;
	}
	

}