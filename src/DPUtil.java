import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class DPUtil {
	private double dmax = 0;
	private int index = 0;

	public static ArrayList DouglasPeucker(ArrayList path, double threshold) {
		for (int i = 0; i < path.size(); ++i) {
//			double d = Geodist(path);
		}
		return null;

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

	public static double Rad(double d) {
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
