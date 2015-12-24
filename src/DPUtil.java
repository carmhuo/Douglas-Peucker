import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class DPUtil {
	private static double dmax = 0;
	private static int index = 0;
	private static ArrayList<Point> resultList = null;
	/*非递归实现*/
	/*public static ArrayList<Point>  DouglasPeucker(ArrayList<Point> pointList,double threshold){
		for (int i = 1; i <pointList.size()-1; ++i) {
			double d = PerpendicularDistance(pointList.get(i),pointList.get(0),pointList.get(pointList.size()-1));
			if(d>dmax){
				dmax = d;
				index = i;
			}
		}
		if(dmax>=threshold){
			pointList.get(index).setMark(1);
		}
		return null;
	}*/
	/*递归实现*/
	public static ArrayList<Point> DouglasPeucker(ArrayList<Point> pointList,int x,int y,double threshold) {
		resultList = new ArrayList<Point>();
		for (int i = 1; i <pointList.size()-1; ++i) {
			double d = PerpendicularDistance(pointList.get(i),pointList.get(x),pointList.get(y));
			if(d>dmax){
				dmax = d;
				index = i;
			}
		}
		if(dmax>=threshold){
			resultList=DouglasPeucker(pointList,x,index,threshold);
			resultList=DouglasPeucker(pointList,index,y,threshold);
		}else{
			resultList.add(pointList.get(x));
			resultList.add(pointList.get(y));
		}
		return resultList;

	}
	
	private static Object Line(Point point1, Point point2) {
		return null;
	}

	private static double PerpendicularDistance(Point point,Point point1, Point point2) {
		double A = (point2.getLatitude()-point1.getLatitude())/(point2.getLongitude()-point1.getLongitude());
		double B = -1.0;
		double C = point1.getLongitude()-A*point1.getLatitude();
		double d = Math.abs(A*point.getLongitude()+B*point.getLatitude()+C)/Math.sqrt(A*A+B*B);
		return d;
	}
	/*
	 * The code of GeoDistance function: Input: Two coordination {Latitude1,
	 * Longitude1, Latitude2, Longitude2 } (type:double) Output: Distance(Unit:
	 * m) (type:double)
	 */
	public static double Geodist(double lat1, double lon1, double lat2,
			double lon2) {
		double radLat1 = Rad(lat1);
		double radLat2 = Rad(lat2);
		double delta_lon = Rad(lon2 - lon1);
		double top_1 = Math.cos(radLat2) * Math.sin(delta_lon);
		double top_2 = Math.cos(radLat1) * Math.sin(radLat2)
				- Math.sin(radLat1) * Math.cos(radLat2) * Math.cos(delta_lon);
		double top = Math.sqrt(top_1 * top_1 + top_2 * top_2);
		double bottom = Math.sin(radLat1) * Math.sin(radLat2)
				+ Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(delta_lon);
		double delta_sigma = Math.atan2(top, bottom);
		double distance = delta_sigma * 6378137.0;
		return distance;
	}

	private static double Rad(double d) {
		return d * Math.PI / 180.0;
	}
	public static void writeFile(ArrayList<String> aList){
		File f =new File("GPS_log.txt");
		try{
			FileWriter fw = new FileWriter(f);
			BufferedWriter bufw = new BufferedWriter(fw);
			for(int i =0 ;i<aList.size();++i){
				bufw.write(aList.get(i));
				bufw.newLine();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static ArrayList<String> readFile(String fileName){
		File f =new File(fileName);
		ArrayList<String> aList = new ArrayList<String>();
		try{
			FileReader fr = new FileReader(f);
			BufferedReader bufr = new BufferedReader(fr);
			while(bufr.readLine() != null){
				aList.add(bufr.readLine());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return aList;
	}
}
