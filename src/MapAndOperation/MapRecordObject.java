package MapAndOperation;
import GameObject.*;

public class MapRecordObject{
	public int[][] maprecordobjectinitial= {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,1,1,0,1,1,0,0,1,0,0,1,1,0,1,1,0,1},
			{1,0,0,1,1,0,1,1,0,0,1,0,0,1,1,0,1,1,0,1},
			{1,0,0,1,1,0,1,1,0,0,1,0,0,1,1,0,1,1,0,1},
			{1,0,0,1,1,0,1,1,0,0,1,0,0,1,1,0,1,1,0,1},
			{1,0,0,1,1,0,1,1,0,0,1,0,0,1,1,0,1,1,0,1},
			{1,0,0,1,1,0,1,1,0,0,1,0,0,1,1,0,1,1,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,1},
			{1,0,0,1,1,0,1,1,0,1,1,1,1,1,1,0,0,0,0,1},
			{1,0,0,1,1,0,1,1,0,0,0,0,0,0,0,0,1,1,0,1},
			{1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1},
			{1,0,0,1,1,0,1,1,0,0,0,0,1,1,1,0,0,0,0,1},
			{1,0,0,1,1,0,1,1,0,0,0,0,1,1,1,0,0,0,0,1},
			{1,0,0,1,1,0,0,0,0,0,0,0,1,1,1,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
	};
	private int len=20;
	private String[][] maprecordobject;
	private int cellwidth;
	private int windowwidth;
	
	int xplayer;
	int yplayer;
	int xrobot;
	int yrobot;
	public MapRecordObject(int playerinitialx,int playerinitialy,int robotinitialx,int robotinitialy,int windowwidthinput) {
		len=maprecordobjectinitial.length;
		maprecordobject=new String[len][len];
		xplayer=playerinitialx;
		yplayer=playerinitialy;
		xrobot=robotinitialx;
		yrobot=robotinitialy;
		windowwidth=windowwidthinput;
		cellwidth=windowwidth/len;
		
		
		
		for(int i=0;i<len;i++) {
			for(int j=0;j<len;j++) {
				if(maprecordobjectinitial[i][j]==1) {
					maprecordobject[i][j]="wall";
				}else if(maprecordobjectinitial[i][j]==0) {
					maprecordobject[i][j]="road";
				}else if(maprecordobjectinitial[i][j]==2) {
					maprecordobject[i][j]="player";
				}else if(maprecordobjectinitial[i][j]==3) {
					maprecordobject[i][j]="robot";
				}else {
					System.out.println("The maprecordobjectinitial array is not correct!");
				}
			}
		}
	}
	
	
	public void upDate(Robot R,Player P) {
		if(maprecordobject[R.getY()][R.getX()]!="road"||maprecordobject[P.getY()][P.getX()]!="road") {
			System.out.println("The coordinate of the robot or the palyer is not correct in the function update of the MapRecordObject class");
		}else {
			maprecordobject[yplayer][xplayer]="road";
			maprecordobject[yrobot][xrobot]="road";
			
			maprecordobject[R.getY()][R.getX()]="robot";
			maprecordobject[P.getY()][P.getX()]="player";
			xplayer=P.getX();
			yplayer=P.getY();
			xrobot=R.getX();
			yrobot=R.getY();
		}
	}
	
	
	public void upDate(Tank T) {
		if(T instanceof Robot) {
			if(maprecordobject[T.getY()][T.getX()]!="road") {
				System.out.println("The coordinate of the upDate in the class MapRecordObject is not correct!");
			}else {
				maprecordobject[yrobot][xrobot]="road";
				maprecordobject[T.getY()][T.getX()]="robot";
				xrobot=T.getX();
				yrobot=T.getY();
			}
		}else {
			if(maprecordobject[T.getY()][T.getX()]!="road") {
				System.out.println("The coordinate of the upDate in the class MapRecordObject is not correct!");
			}else {
				maprecordobject[yplayer][xplayer]="road";
				maprecordobject[T.getY()][T.getX()]="player";
				xplayer=T.getX();
				yplayer=T.getY();
			}
		}
	}
	
	
	public String getUpPoint(int x,int y) {
		if(y-1<0||y>=len||x<0||x>=len) {
			System.out.println("The position x and y of the getUpPosition function is not correct!");
			return "Error";
		}else {
			return maprecordobject[y-1][x];
		}
	}
	
	public String getDownPoint(int x,int y) {
		if(y+1>=len||y<0||x>=len||x<0) {
			System.out.println("The position x or y of the getDownPosition function is not correct!");
			return "Error!";
		}else {
			return maprecordobject[y+1][x];
		}
	}
	
	public String getLeftPoint(int x,int y) {
		if(x-1<0||x>=len||y<0||y>=len) {
			System.out.println("The position of the x or y is not correct in the function getLeftPoint!");
			return "Error";
		}else {
			return maprecordobject[y][x-1];
		}
	}
	
	public String getRightPoint(int x,int y) {
		if(x<0||x+1>=len||y<0||y>=len) {
			System.out.println("The position of the function of the getRightPoint is not correct!");
			return "Error";
		}else {
			return maprecordobject[y][x+1];
		}
	}
	
	public int getLen() {
		return len;
	}
	
	public int getCellWidth() {
		return cellwidth;
	}
	public int getWindowWidth() {
		return windowwidth;
	}	
	
	
	public String getPoint(int xinput,int yinput) {
		if(xinput<0||xinput>=this.getLen()||yinput<0||yinput>=this.getLen()) {
			System.out.println("The inout x or inout y is not correct! please check in the getPoint or relevant class");
			return null;
		}else {
			return this.maprecordobject[yinput][xinput];
		}
	}
}