package MapAndOperation;

import java.util.Queue;
import java.util.LinkedList;

public class Path {
	// Queue<PointRecordPath> pathwhite = new LinkedList<PointRecordPath>();
	LinkedList<PointRecordPath> pathblack = new LinkedList<PointRecordPath>();
	LinkedList<PointRecordPath> pathgrey = new LinkedList<PointRecordPath>();
	private int[][] visitrecord;

	public Path(MapRecordObject m) {
		visitrecord = new int[m.getLen()][m.getLen()];
	}

	public PointRecordPath getBlackPoint() {
		return pathblack.get(pathblack.size()-1);
	}

	public void clear(MapRecordObject m) {
		if (pathblack.isEmpty() == false) {
			pathblack.clear();
		}
		if (pathgrey.isEmpty() == false) {
			pathgrey.clear();
		}
		this.clearVisit(m);
	}

	public Queue<PointRecordPath> getGreyPoint() {
		return pathgrey;
	}

	public int getVisit(int x, int y) {
		return visitrecord[y][x];
	}

	public void setVisit(int x, int y, int newvalue) {
		if (newvalue != 0 && newvalue != 1) {
			System.out.println("The setVisit value of the Path's visitrecord is not correct, should be 1 or 0!");
		} else {
			visitrecord[y][x] = newvalue;
		}
	}

	public void setInitial(int xinitial, int yinitial) {
		PointRecordPath point = new PointRecordPath(xinitial, yinitial, "grey", null);
		pathgrey.add(point);
	}

	public void clearVisit(MapRecordObject m) {
		int length = m.getLen();
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				visitrecord[i][j] = 0;
			}
		}
	}

	public void insertGreyPoint(int x, int y) {
		if (pathblack.isEmpty() == true) {
			System.out.println(
					"The black node of the path is empty! the insertPoint operation can not be done, please check!");
		} else {
			PointRecordPath fatherpoint = pathblack.getLast();
			PointRecordPath p = new PointRecordPath(x, y, "grey", fatherpoint);
			pathgrey.offer(p);
		}
	}

	private PointRecordPath removeGrey() {
		if (pathgrey.isEmpty() == true) {
			System.out.println("The pathgrey linked list is empty!");
			return null;
		} else {
			return pathgrey.remove();
		}
	}

	private void insertBlack(PointRecordPath p) {
		pathblack.offer(p);
	}

	public void visitFirstGrey() {
		PointRecordPath ptemp = removeGrey();
		ptemp.setType("black");
		insertBlack(ptemp);
	}

	public void findAndAddGreyPoints(MapRecordObject m) {
		int xcurrent = pathblack.get(pathblack.size()-1).getX();  //8
		int ycurrent = pathblack.get(pathblack.size()-1).getY();  //7

		if (pathblack.isEmpty() == true) {
			System.out.println("The black node is empty, can not create grey point without father point!");
		} else {
			// up:
			if (ycurrent - 1 >= 0 && m.getUpPoint(xcurrent, ycurrent) != "wall"
					&& this.getVisit(xcurrent, ycurrent - 1) == 0) {
				PointRecordPath pointgreynew = new PointRecordPath(xcurrent, ycurrent - 1, "grey", pathblack.getLast());
				pathgrey.add(pointgreynew);
				this.setVisit(xcurrent, ycurrent-1, 1);
			}
			// down:
			if (ycurrent + 1 < m.getLen() && m.getDownPoint(xcurrent, ycurrent) != "wall"
					&& this.getVisit(xcurrent, ycurrent + 1) == 0) {
				PointRecordPath pointgreynew = new PointRecordPath(xcurrent, ycurrent + 1, "grey", pathblack.getLast());
				pathgrey.add(pointgreynew);
				this.setVisit(xcurrent, ycurrent+1, 1);
			}
			// right:
			if (xcurrent + 1 < m.getLen() && m.getRightPoint(xcurrent, ycurrent) != "wall"
					&& this.getVisit(xcurrent + 1, ycurrent) == 0) {
				PointRecordPath pointgreynew = new PointRecordPath(xcurrent + 1, ycurrent, "grey", pathblack.getLast());
				pathgrey.add(pointgreynew);
				this.setVisit(xcurrent+1, ycurrent, 1);
			}
			// left:
			if (xcurrent - 1 >= 0 && m.getLeftPoint(xcurrent, ycurrent) != "wall"
					&& this.getVisit(xcurrent - 1, ycurrent) == 0) {
				PointRecordPath pointgreynew = new PointRecordPath(xcurrent - 1, ycurrent, "grey", pathblack.getLast());
				pathgrey.add(pointgreynew); // 出现了greypath中有两个相同元素的情况
				this.setVisit(xcurrent-1, ycurrent, 1);
			}
		}
	}
}