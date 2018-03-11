package MapAndOperation;
public class PointRecordObject extends Point{
	private String objecttype;
	
	public PointRecordObject(int x,int y,String objecttypeinput) {
		super(x,y);
		if(objecttypeinput!="wall"&&objecttypeinput!="player"&&objecttypeinput!="robot"&&objecttypeinput!="road") {
			System.out.println("The objecttype of the PointRecordObject is not correct!");
		}else {
			objecttype=objecttypeinput;
		}
	}
	
	public String getObjectType() {
		return objecttype;
	}
	public void setObjectType(String newtype) {
		if(newtype!="wall"&&newtype!="robot"&&newtype!="road"&&newtype!="player") {
			System.out.println("The newtype of the setObjectType function is not correct!");
		}else {
			objecttype=newtype;
		}
	}
}