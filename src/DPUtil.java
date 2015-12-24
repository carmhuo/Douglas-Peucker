import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class DPUtil {
	//private static double dmax = 0;
	//private static int index = 0;
	private static ArrayList<Point> resultList = new ArrayList<Point>();
	/*方法一：递归实现*/
	/*public static void DouglasPeucker(int x,int y,double threshold) {
		double dmax = 0;
		int index = 0;
		//resultList = new ArrayList<Point>();
		if(y<=x+1){
			return ;
		}
		for (int i = x+1; i <y; ++i) {
			//double d = PerpendicularDistance(Compress.pointList.get(i),Compress.pointList.get(x),Compress.pointList.get(y));
			double d = PerpendicularDistance(x,y,i);
			if(d>dmax){
				dmax = d;
				index = i;
			}
		}
		Compress.pointList.get(x).setMark(1);
		Compress.pointList.get(y).setMark(1);
		if(dmax>=threshold){
			Compress.pointList.get(index).setMark(1);
			DouglasPeucker(x,index,threshold);
			DouglasPeucker(index,y,threshold);
		}
		return;
	}*/
	/*方法二：递归实现*/
	public static ArrayList<Point> DouglasPeucker(int startIndex,int endIndex,double threshold) {
		double dmax = 0;
		int index = 0;
		if (endIndex <= startIndex + 1) {
			// overlapping indexes, just return
			return  resultList;
		}
		for (int i = startIndex+1; i <endIndex; ++i) {
			double d = PerpendicularDistance(startIndex,endIndex,i);
			if(d>dmax){
				dmax = d;
				index = i;
			}
		}
		if(dmax>=threshold){
			resultList.add(Compress.pointList.get(index));
			resultList=DouglasPeucker(startIndex,index,threshold);
			resultList=DouglasPeucker(index,endIndex,threshold);
		}
		return resultList;
	}
	public static double PerpendicularDistance(int start, int end, int current) {  
		double a=Geodist(Compress.pointList.get(start).getLatitude(),Compress.pointList.get(start).getLongitude(),Compress.pointList.get(end).getLatitude(),Compress.pointList.get(end).getLongitude());
		double b=Geodist(Compress.pointList.get(start).getLatitude(),Compress.pointList.get(start).getLongitude(),Compress.pointList.get(current).getLatitude(),Compress.pointList.get(current).getLongitude());
		double c=Geodist(Compress.pointList.get(current).getLatitude(),Compress.pointList.get(current).getLongitude(),Compress.pointList.get(end).getLatitude(),Compress.pointList.get(end).getLongitude());
		double p=(a+b+c)/2;
		double S=Math.sqrt(p*(p-a)*(p-b)*(p-c));
		double dist=2*S/a;
		return dist;
	} 
	/*public static double PerpendicularDistance(Point point,Point point1, Point point2) {
		double A = (point2.getLatitude()-point1.getLatitude())/(point2.getLongitude()-point1.getLongitude());
		double B = -1.0;
		double C = point1.getLongitude()-A*point1.getLatitude();
		double d = Math.abs(A*point.getLongitude()+B*point.getLatitude()+C)/Math.sqrt(A*A+B*B);
		return d;
	}*/
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
		File f =new File("Compress_Result_GPS_log_.txt");
		try{
			FileWriter fw = new FileWriter(f);
			BufferedWriter bufw = new BufferedWriter(fw);
			for(int i =0 ;i<aList.size();++i){
				bufw.write(aList.get(i));
				bufw.newLine();
			}
			bufw.close();
			fw.close();
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
			String s="";
			while((s=bufr.readLine()) != null){
				aList.add(s);
			}
			bufr.close();
			fr.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return aList;
	}
}
