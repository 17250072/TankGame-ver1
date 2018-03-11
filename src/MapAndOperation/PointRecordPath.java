package MapAndOperation;

public class PointRecordPath extends Point{
	private String type; //white, black, grey
	private PointRecordPath fatherpoint;
	
	public PointRecordPath(int x,int y,String typeinput,PointRecordPath p) {
		super(x,y);
		if(typeinput!="white"&&typeinput!="black"&&typeinput!="grey") {
			System.out.println("The typeinput of the PointRecordPath is not correct!");
		}else {
			type=typeinput;
		}
		fatherpoint=p;   //if the father point of one particular point is null, then the point is the start.
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String newtype) {
		if(newtype!="black"&&newtype!="white"&&newtype!="grey") {
			System.out.println("The newtype of the ");
		}else {
			type=newtype;
		}
	}
	
	public PointRecordPath getFatherPoint() {
		return fatherpoint;
	}
}