package GameObject;
import  java.util.ArrayList;

public class BulletCollectionAndManager{
	ArrayList<Bullet> bulletlist=new ArrayList<Bullet>();
	Player player;
	RobotCollectionAndManager robotcollection;
	
	public BulletCollectionAndManager(RobotCollectionAndManager robotlist){
		robotcollection=robotlist;
	}
	
	
	public void hitObjectCheckAndOperation() {
		for(int i=0;i<bulletlist.size();i++) {
			bulletlist.get(i).hitWallAllCheck();
			bulletlist.get(i).hitTankAllCheck(robotcollection);
			if(bulletlist.get(i).getIsHitted()==true) {
				bulletlist.remove(i);
			}
		}
	}
	
	public void addBullet(Bullet bullet) {
		bulletlist.add(bullet);
	}
	
	
	public void moveAllBullet() {
		for(int i=0;i<bulletlist.size();i++) {
			bulletlist.get(i).bulletMove();
		}
	}
	
	public int getBulletNumber() {
		return bulletlist.size();
	}
	
	public Bullet getBullet(int i) {
		return bulletlist.get(i);
	}
}