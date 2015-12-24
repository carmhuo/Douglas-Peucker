import java.util.ArrayList;

public class Compress {
//	private static Point point = null;
	public static ArrayList<Point> pointList = null;

	public static void main(String[] args) {
		ArrayList<String> al = DPUtil
				.readFile("C:\\Users\\carm\\Desktop\\2007-10-14-GPS.log");
		pointList = new ArrayList<Point>();
		// point = new Point(0.0,0.0);
		for (int i = 0; i < al.size(); ++i) {
			String[] s = al.get(i).split(" ");
			pointList.add(new Point(new Double(s[3]).doubleValue(), new Double(
					s[5]).doubleValue(),0));
			// System.out.println(s[3]+" "+s[5]);
		}
		/*
		 * for(int i=0;i<pointList.size();++i) {
		 * System.out.println(pointList.get
		 * (i).getLongitude()+" "+pointList.get(i).getLatitude()); }
		 */
		/*System.out.println(DPUtil
				.Geodist(pointList.get(0).getLatitude(), pointList.get(0)
						.getLongitude(), pointList.get(1).getLatitude(),
						pointList.get(1).getLongitude()));*/
		//DPUtil.DouglasPeucker(pointList, 0, pointList.size()-1, 30.0);
	}

}
