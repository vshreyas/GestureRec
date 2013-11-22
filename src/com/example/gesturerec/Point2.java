package com.example.gesturerec;


public class Point2 {
	double x;
	double y;
	int t;
	public Point2(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public static double Distance(Point2 p1, Point2 p2) {
		return Math.sqrt((p1.x -p2.x)*(p1.x -p2.x) + (p1.y - p2.y)*(p1.y - p2.y));		
	} 
	public static void main(String args[]) {
		Point2 p1 = new Point2(3,2);
		Point2 p2 = new Point2(4,6);
		System.out.println(Distance(p1,p2));
	}
	
	@Override public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("(");
		result.append(x);
		result.append(",");
		result.append(y);
		result.append(")");
		return result.toString();
	}
}