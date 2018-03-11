package MapAndOperation;

import java.util.ArrayList;

public class PathTest {

	MapRecordObject map;
	Path pathtest;
	private int xend=1;
	private int yend=1;
	private ArrayList<PointRecordPath> pathArrayListResult = new ArrayList<PointRecordPath>();

	public PathTest(Path pathtest1, MapRecordObject map1) {
		pathtest = pathtest1;
		map = map1;
	}
/*
	public static void main(String args[]) {
		MapRecordObject map = new MapRecordObject(1, 1, 8, 8, 500);
		Path path = new Path(map);
		path.setInitial(8, 8);
		PathTest pathtest = new PathTest(path, map);

		pathtest.test();
	}
*/
	public void findPath(MapRecordObject m) {
		// insert the beginning to the grey point array

		if (pathArrayListResult.isEmpty() == false) {
			pathArrayListResult.clear();
		}
		pathtest.clear(m);

		pathtest.setInitial(8, 8);

		while (true) {
			if (pathtest.getGreyPoint().isEmpty() == true) {
				System.out.println("The path is not found! please check!");
				break;
			}
			pathtest.visitFirstGrey();
			if (pathtest.getBlackPoint().getX() == xend && pathtest.getBlackPoint().getY() == yend) {
				for (PointRecordPath Ptemp = pathtest.getBlackPoint(); Ptemp.getFatherPoint() != null; Ptemp = Ptemp
						.getFatherPoint()) {
					pathArrayListResult.add(Ptemp);
				}
				break;
			} else {
				pathtest.findAndAddGreyPoints(m);
			}
		}
	}

	public void test() {
		this.findPath(map);
		for (int i = this.pathArrayListResult.size() - 1; i >= 0; i--) {
			System.out.print(this.pathArrayListResult.get(i).getX()+" ");
			System.out.println(this.pathArrayListResult.get(i).getY());
		}
	}
}