package GameObject;

import MapAndOperation.*;
import java.math.*;

public class Player extends Tank {
	private int playerkeyloadlock=0;
	private MapRecordObject map;
	private String imagetype="left";
	
	public Player(int xinitial, int yinitial, MapRecordObject m) {
		super(xinitial, yinitial, m);
		super.setRealPosition(super.calculateRealPosition(getX(), getY(), m)[0],
		super.calculateRealPosition(getX(), getY(), m)[1]);
		map=m;
	}
	
	public void move(MapRecordObject map) {
		this.goReal(map);
		
		if (Math.abs(this.getXReal() - this.calculateRealPosition(getX(), getY(), map)[0]) >= map.getCellWidth() || Math
				.abs(this.getYReal() - this.calculateRealPosition(getX(), getY(), map)[1]) >= map.getCellWidth()) {
			this.changeDirection("stop");
		}
		
		if(super.getXReal()-super.calculateRealPosition(super.getX(), super.getY(), map)[0]>=map.getCellWidth()) {
			super.goRight(map);
		}else if(super.calculateRealPosition(super.getX(), super.getY(), map)[0]-super.getXReal()>=map.getCellWidth()) {
			super.goLeft(map);
		}else if(super.getYReal()-super.calculateRealPosition(super.getX(), super.getY(), map)[1]>=map.getCellWidth()) {
			super.goDown(map);
		}else if(super.calculateRealPosition(super.getX(), super.getY(), map)[1]-super.getYReal()>=map.getCellWidth()) {
			super.goUp(map);
		}
	}
	
	public void goReal(MapRecordObject map) {
		if(this.getDirection()=="right"&&map.getRightPoint(getX(), getY())!="robot") {
			super.moveTankPerUnit("x", 5);
		}else if(this.getDirection()=="left"&&map.getLeftPoint(getX(), getY())!="robot") {
			super.moveTankPerUnit("x", -5);
		}else if(this.getDirection()=="up"&&map.getUpPoint(getX(), getY())!="robot") {
			super.moveTankPerUnit("y", -5);
		}else if(this.getDirection()=="down"&&map.getDownPoint(getX(), getY())!="robot") {
			super.moveTankPerUnit("y", 5);
		}else if(this.getDirection()=="stop") {
			//do nothing
		}
	}
	
	public void calculatePlayerKeyLoadLock() {
		if (this.reachPoint(map) == true) {
			playerkeyloadlock = 0;
		} else {
			playerkeyloadlock = 1;
		}
	}
	
	public int getPlayerKeyLoadLock() {
		return playerkeyloadlock;
	}
	
	public String getImageType() {
		return imagetype;
	}
	
	public void setImageType(String newtype) {
		if(newtype!="left"&&newtype!="right"&&newtype!="down"&&newtype!="up") {
			System.out.println("The player's imagetype is no coeerect int setImageType");
		}else {
			imagetype=newtype;
		}
	}
	
	public void shoot(BulletCollectionAndManager bulletlist) {
		if(imagetype=="right") {
			Bullet newbullet=new Bullet(super.getXReal()+map.getCellWidth(),super.getYReal(),"right",map);
			bulletlist.addBullet(newbullet);
		}else if(imagetype=="left") {
			Bullet newbullet=new Bullet(super.getXReal()-map.getCellWidth(),super.getYReal(),"left",map);
			bulletlist.addBullet(newbullet);
		}else if(imagetype=="down") {
			Bullet newbullet=new Bullet(super.getXReal(),super.getYReal()+map.getCellWidth(),"down",map);
			bulletlist.addBullet(newbullet);
		}else if(imagetype=="up") {
			Bullet newbullet=new Bullet(super.getXReal(),super.getYReal()-map.getCellWidth(),"up",map);
			bulletlist.addBullet(newbullet);
		}
	}
	
}