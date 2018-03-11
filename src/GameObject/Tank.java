package GameObject;

import MapAndOperation.MapRecordObject;
import java.math.*;

public class Tank{
	private int x;
	private int y;
	private float xreal;
	private float yreal;
	private String direction; //left right down up
	MapRecordObject map;
	
	public Tank(int xinput,int yinput,MapRecordObject m) {
		x=xinput;
		y=yinput;
		xreal=calculateRealPosition(x,y,m)[0];
		yreal=calculateRealPosition(x,y,m)[1];
		direction="stop";
		map=m;
	}
	
	public int getX() {
		return x;
	}
	public float getXReal() {
		return xreal;
	}
	public int getY() {
		return y;
	}
	public float getYReal() {
		return yreal;
	}
	
	public float[] calculateRealPosition(int x1,int y1,MapRecordObject m) {
		float[] result=new float[2];
		int cellwidth=m.getCellWidth();
		int length=m.getLen();
		if(x1>=length||x1<0||y1>=length||y1<0) {
			System.out.println("The x1 or y1 of the calculateRealPosition is not correct!");
			return null;
		}
		result[0]=(float)x1*cellwidth+(float)cellwidth/2.0f;
		result[1]=(float)y1*cellwidth+(float)cellwidth/2.0f;
		return result;
	}
	
	public void setPosition(int xnew,int ynew,MapRecordObject maprecordobject,Robot R) {
		if(xnew<0||xnew>maprecordobject.getLen()||ynew<0||ynew>maprecordobject.getLen()) {
			System.out.println("The xnew or ynew of the setPosition function is not correct!");
		}else {
			x=xnew;
			y=ynew;
			maprecordobject.upDate(R);
		}
	}
	
	public void setRealPosition(float xrealnew,float yrealnew) {
		xreal=xrealnew;
		yreal=yrealnew;
	}
	
	public void changeDirection(String directionnew) {
		if(directionnew!="up"&&directionnew!="down"&&directionnew!="left"&&directionnew!="right"&&directionnew!="stop") {
			System.out.println("The directionnew is not correct in the changeDirection function!");
		}else {
			direction=directionnew;
		}
	}
	
	public String getDirection() {
		return this.direction;
	}
	
	public void goRight(MapRecordObject m) {
		if(this.getX()+1>=m.getLen()||this.getY()>=m.getLen()||this.getX()<0||this.getY()<0) {
			System.out.println("The Tank can not do the goRight Function in the class Tank due to domain exception");
		}else if(m.getRightPoint(x, y)!="road"){
			System.out.println("The Tank can not go right for no road in the right in the maprecordobject!");
		}else {
			x++;
			m.upDate(this);
		}
	}
	
	public void goLeft(MapRecordObject m) {
		if(this.getX()-1<0||this.getX()>=m.getLen()||this.getY()<0||this.getY()>=m.getLen()) {
			System.out.println("The Tank can not do the goLeft Function in the class Tank due to the domain exception");
		}else if(m.getLeftPoint(x, y)!="road") {
			System.out.println("The Tank can not go left for no road in the left in the maprecordobject!");
		}else {
			x--;
			m.upDate(this);
		}
	}
	
	public void goUp(MapRecordObject m) {
		if(this.getY()-1<0||this.getY()>=m.getLen()||this.getX()<0||this.getX()>=m.getLen()) {
			System.out.println("The Tank can not do the goUp function in the class goUp due to the domain exception");
		}else if(m.getUpPoint(x, y)!="road") {
			System.out.println("The Tank can not go up for no road at the top");
		}else {
			y--;
			m.upDate(this);
		}
	}
	
	public void goDown(MapRecordObject m) {
		if(this.getY()+1>=m.getLen()||this.getY()<0||this.getX()<0||this.getX()>=m.getLen()) {
			System.out.println("The Tank can not do the function goDown due to the domain overflow");
		}else if(m.getDownPoint(x, y)!="road") {
			System.out.println("The Tank can not do goDown for no road down the Tank");
		}else {
			y++;
			m.upDate(this);
		}
	}
	
	public boolean reachPoint(MapRecordObject m) {
		float xline=(float)this.getX()*(float)m.getCellWidth()+(float)m.getCellWidth()/2.0f;
		float yline=(float)this.getY()*(float)m.getCellWidth()+(float)m.getCellWidth()/2.0f;
		
		float s=4.0f;
		if(Math.abs(xline-(float)this.getXReal())<s&&Math.abs(yline-(float)this.getYReal())<s) {
			return true;
		}else {
			return false;
		}
	}
	
	public void moveTankPerUnit(String dimention,int value) {
		if(dimention=="x") {
			xreal=xreal+value;
		}else if(dimention=="y") {
			yreal=yreal+value;
		}else {
			System.out.println("The moveTankPerUnit do nothing for the dimwntion inputed is not correct!");
		}
	}
	
	 
}
