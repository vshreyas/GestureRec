package com.example.gesturerec;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.view.View.OnHoverListener;
import android.view.View;
import android.widget.TextView;

public class Draw_Surface extends View implements OnTouchListener{
	
	public static ArrayList<Float> points_x = new ArrayList<Float>();
	public static ArrayList<Float> points_y = new ArrayList<Float>();
	public static boolean flag = false;
	public static ArrayList<Point> resampled_points = new ArrayList<Point>();
	public static ArrayList<ArrayList<Point>> letter = new ArrayList<ArrayList<Point>>();
	public static ArrayList<ArrayList<Point2>> word = new ArrayList<ArrayList<Point2>>();
	public static ArrayList<ArrayList<ArrayList<Point2>>> word2 = new ArrayList<ArrayList<ArrayList<Point2>>>();
	public static boolean is_stroke_being_drawn = false;
	
	public Draw_Surface(Context context, AttributeSet attributeSet)
	{
		super(context);
		 points_x = new ArrayList<Float>();
		 points_y = new ArrayList<Float>();
		 flag = false;
		 resampled_points = new ArrayList<Point>();
		 letter = new ArrayList<ArrayList<Point>>();
		 word = new ArrayList<ArrayList<Point2>>();
		 is_stroke_being_drawn = false;
		
	}
	
	public Draw_Surface(Context context)
	{
		super(context);
		points_x = new ArrayList<Float>();
		 points_y = new ArrayList<Float>();
		 flag = false;
		 resampled_points = new ArrayList<Point>();
		 letter = new ArrayList<ArrayList<Point>>();
		 word = new ArrayList<ArrayList<Point2>>();
		 is_stroke_being_drawn = false;
	}
	
	public static void initialize()
	{
		points_x = new ArrayList<Float>();
		 points_y = new ArrayList<Float>();
		 flag = false;
		 resampled_points = new ArrayList<Point>();
		 letter = new ArrayList<ArrayList<Point>>();
		 word = new ArrayList<ArrayList<Point2>>();
		 is_stroke_being_drawn = false;
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{
		super.onTouchEvent(event);
		points_x.add(event.getX());
		points_y.add(event.getY());
		if(event.getActionMasked() == MotionEvent.ACTION_DOWN)
		{
			points_x.clear();
			points_y.clear();
			is_stroke_being_drawn = true;
		}
		if(event.getActionMasked() == MotionEvent.ACTION_UP)
		{
			letter.add(resampled_points);
			is_stroke_being_drawn = false;
		}
		Log.v("point_x_y", event.getX() + "" + event.getY());
		invalidate();
		// TODO Auto-generated method stub
//		Log.v("touch", arg1.getX() + " " +  arg1.getY());
		return true;
	}
	
	
	@Override
	protected void onDraw (Canvas canvas)
    {
		int iii;
		super.onDraw(canvas);
		Paint paint = new Paint(0);
		paint.setColor(Color.RED);
		
		
		
		resampled_points = new ArrayList<Point>();
		resampled_points = resample(points_x, points_y, points_x.size());
		
		
		for(int k = 0; k < word2.size(); k++)
		{
			for(int j = 0; j < word2.get(k).size(); j++)
			{
				for(int i = 0; i < word2.get(k).get(j).size(); i++)
				{
					canvas.drawCircle((float)word2.get(k).get(j).get(i).x, (float)word2.get(k).get(j).get(i).y, 3, paint);
				}
			}
			
			
			paint.setStrokeWidth(6);
			for(int j = 0; j < word2.get(k).size(); j++)
			{
				for(int i = 0; i < word2.get(k).get(j).size() - 1; i++)
				{
					canvas.drawLine((float)word2.get(k).get(j).get(i).x, (float)word2.get(k).get(j).get(i).y, (float)word2.get(k).get(j).get(i + 1).x, (float)word2.get(k).get(j).get(i + 1).y, paint);	
				}
			}
		}
		
		for(int j = 0; j < letter.size(); j++)
		{
			for(int i = 0; i < letter.get(j).size(); i++)
			{
				canvas.drawCircle(letter.get(j).get(i).X, letter.get(j).get(i).Y, 3, paint);
			}
		}
		paint.setStrokeWidth(6);
		for(int j = 0; j < letter.size(); j++)
		{
			for(int i = 0; i < letter.get(j).size() - 1; i++)
			{
				canvas.drawLine(letter.get(j).get(i).X, letter.get(j).get(i).Y, letter.get(j).get(i + 1).X, letter.get(j).get(i + 1).Y, paint);
			}
		}

		
    	Log.v("reached here", "at least");
    	if(resampled_points != null && resampled_points.size() != 0)
		{
			for(int i = 0; i < resampled_points.size(); i++)
			{
				canvas.drawCircle(resampled_points.get(i).X, resampled_points.get(i).Y, 3, paint);
			}
			paint.setStrokeWidth(6);
			for(int i = 0; i < resampled_points.size() - 1; i++)
			{
				canvas.drawLine(resampled_points.get(i).X, resampled_points.get(i).Y, resampled_points.get(i + 1).X, resampled_points.get(i + 1).Y, paint);
			}
	    	Log.v("Yay", "Praise be!");
		}
		
		if(Point.flag == true && resampled_points != null)
		{
			Point.flag = false;
//			flag = true;
/*			Recognizer r = new Recognizer(5,Math.PI/4, Math.PI /90, 40);
	    	ArrayList<Point2> stroke = new ArrayList<Point2>();
	    	for(int j = 0; j < letter.size(); j++)
			{
				for(int i = 0; i < letter.get(j).size(); i++)
				{
					Point2 p = new Point2(letter.get(j).get(i).X, letter.get(j).get(i).Y);
		    		stroke.add(p);
				}
			}
*/	    	WordRecognizer w = new WordRecognizer();
			String result = new String();
			Vector<String> v = w.recognize(word);
	    	for(int i = 0; i < v.size();i++) {
	    		result += ", " + v.get(i);
	    	}
	    	if(result.length() > 0) GestureRec.tv.setText("You got all the letters right except ---> " + result);
	    	else GestureRec.tv.setText("You got all the letters right");
	    	Log.v("Recognized Stroke ---> ", result);
	    	invalidate();
		}
    }

	public void letter_completed()
	{
		points_x.clear();
		points_y.clear();
		resampled_points.clear();
		ArrayList<Point2> letter2 = new ArrayList<Point2>();
		for(int i = 0; i < letter.size(); i++)
		{
			for(int j = 0; j < letter.get(i).size(); j++)
			{
				Point2 p = new Point2(letter.get(i).get(j).X, letter.get(i).get(j).Y);
				letter2.add(p);
			}
			
		}
		word.add(letter2);
		
		ArrayList<ArrayList<Point2>> letter3 = new ArrayList<ArrayList<Point2>>();
		for(int i = 0; i < letter.size(); i++)
		{
			ArrayList<Point2> stroke = new ArrayList<Point2>();
			for(int j = 0; j < letter.get(i).size(); j++)
			{
				Point2 p = new Point2(letter.get(i).get(j).X, letter.get(i).get(j).Y);
				stroke.add(p);
			}
			letter3.add(stroke);
		}
		word2.add(letter3);
		
//		letter.clear();
		invalidate();
	}
	
	@Override
	public boolean onTouch(View arg0, MotionEvent event) {
	//	points_x.add(event.getX());
	//	points_y.add(event.getY());
		Log.v("point_x_y", event.getX() + "" + event.getY());
		// TODO Auto-generated method stub
		return true;
	}
	
	public float calculate_path_length(ArrayList<Float> x, ArrayList<Float> y)
	{
		float length = 0;
		for(int i = 1; i < x.size(); i++)
		{
			length += Math.sqrt(((x.get(i) - x.get(i - 1)) * (x.get(i) - x.get(i - 1))) + ((y.get(i) - y.get(i - 1)) * (y.get(i) - y.get(i - 1))));
		}
		
		return length;
	}
	
	public ArrayList<Point> resample(ArrayList<Float> x_points, ArrayList<Float> y_points, float n)
	{
		if(x_points.size() == 0)
		{
			return null;
		}
		float I = calculate_path_length(x_points, y_points) / (n - 1);
		float D = 0;
		ArrayList<Float> x = new ArrayList<Float>();
		ArrayList<Float> y = new ArrayList<Float>();
		for(int j = 0; j < x_points.size(); j++)
		{
			boolean flag = true;
			for(int k = 0; k < x.size(); k++)
			{
				if(x.get(k) == x_points.get(j))
				{
					flag = false;
					break;
				}
			}
			if(flag == true)
			{
				x.add(x_points.get(j));
				y.add(y_points.get(j));
			}
		}
		ArrayList<Point> new_points = new ArrayList<Point>();
		Point p = new Point(x.get(0), y.get(0));
		new_points.add(p);
		int i = 1;
		if(x.size() >= 2)
		{
			boolean flag = true;
			int iii = 1;
			while(i < x.size())
			{
				float d =  (float) Math.sqrt(((x.get(i) - x.get(i - 1)) * (x.get(i) - x.get(i - 1))) + ((y.get(i) - y.get(i - 1)) * (y.get(i) - y.get(i - 1))));
				if(D + d >= I && d!= 0)
				{
					float qx = x.get(i - 1) + (((I - D) / d) * (x.get(i) - x.get(i - 1)));
					float qy = y.get(i - 1) + (((I - D) / d) * (y.get(i) - y.get(i - 1)));
					
					Point q = new Point(qx, qy);
					
					new_points.add(q);
					x.add(i, qx);
					y.add(i, qy);
					D = 0;
					i++;
				}
				else
				{
					i++;
					D += d;
				}
			}
			
			return new_points;
		}
		else
		{
			return null;
		}
		
	}

}
