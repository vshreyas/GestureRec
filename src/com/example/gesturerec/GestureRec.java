package com.example.gesturerec;

import java.io.Console;
import java.util.ArrayList;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.gesture.Gesture;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GestureRec extends Activity implements OnTouchListener{

	public ArrayList<Float> points_x = new ArrayList<Float>();
	public ArrayList<Float> points_y = new ArrayList<Float>();
	public Draw_Surface drawing_surface;
	public static boolean flag = false;
	public static ArrayList<Integer> word_image_lookup = new ArrayList<Integer>();
	public static TextView tv;
	public static int image_index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_rec);
        LinearLayout l = (LinearLayout) findViewById(R.id.main_layout);
        drawing_surface = new Draw_Surface(getApplicationContext());
        //View inflate = getLayoutInflater().inflate(R.layout.activity_gesture_rec, null);
     //   this.addContentView(drawing_surface, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
       // 		ViewGroup.LayoutParams.WRAP_CONTENT));
      //  View view = (View) findViewById(R.id.gesture_area);
        l.addView(drawing_surface);
        l.requestFocus();
//        Draw_Surface.initialize();
        Button button = (Button) findViewById(R.id.recognize_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Point.flag = true;
                Log.v("Button", "Clicked");
                drawing_surface.invalidate();
            }
        });
        word_image_lookup.add(R.drawable.aaj);
        word_image_lookup.add(R.drawable.behen);
        word_image_lookup.add(R.drawable.bhai);
        word_image_lookup.add(R.drawable.chachi);
        word_image_lookup.add(R.drawable.chai);
        word_image_lookup.add(R.drawable.dada);
        word_image_lookup.add(R.drawable.dudhh);
        word_image_lookup.add(R.drawable.nephew);
        word_image_lookup.add(R.drawable.parivar);
        word_image_lookup.add(R.drawable.pitha);
        word_image_lookup.add(R.drawable.subah);
        Random generator = new Random();
        image_index = 0 * generator.nextInt(11);
        ImageView imv = (ImageView) findViewById(R.id.word);
        imv.setBackgroundResource(word_image_lookup.get(image_index));
        
        tv = (TextView) findViewById(R.id.result);
    }
    
    public void letter_done(View view)
    {
    	drawing_surface.letter_completed();
    	Log.v("Letter", "Done");
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.gesture_rec, menu);
        return true;
    }
    
    @Override
	public boolean onTouch(View arg0, MotionEvent event) {
		points_x.add(event.getX());
		points_y.add(event.getY());
		Log.v("point_x_y", event.getX() + "" + event.getY());
		// TODO Auto-generated method stub
		return false;
	}
    
   
    
}
