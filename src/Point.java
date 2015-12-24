
public class Point {
	private double longitude;//¾­¶È
	private double latitude;//Î³¶È
	private int mark;
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public Point(double longitude,double latitude,int mark){
		this.longitude=longitude;
		this.latitude=latitude;
		this.mark = mark;
	}
}
