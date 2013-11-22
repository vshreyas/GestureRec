package com.example.gesturerec;

import java.util.ArrayList;
import java.util.Vector;

import android.util.Log;

public class WordRecognizer {
	Vector<String[]> words;	
	public WordRecognizer() {
		String[] family = {"pa","r","i1","va","r"};
		String[] brother = {"bha","aa1", "ee"};
		String[] tea = {"cha", "ya"};
		String[] milk = {"dh", "oo", "dhha"};
		String[] father = {"pa", "i1", "tha", "aa1"};
		String[] morning = {"sa", "u", "ba", "ha"};
		String[] sister = {"ba", "ha", "n4"};
		String[] nephew = {"bha", "tha", "i1", "ja"};
		String[] today = {"aa", "ja"};
		String[] aunt = {"cha", "cha", "i1"};
		words = new Vector<String[]>();
		words.add(family);
		words.add(brother);
		words.add(tea);
		words.add(milk);
		words.add(father);
		words.add(morning);
		words.add(sister);
		words.add(nephew);
		words.add(today);
		words.add(aunt);
	}

	public Vector<String> recognize(ArrayList<ArrayList<Point2>> stroke_data) {
		String[] input = recognize_letters(stroke_data);
		Vector<String> diffChars = new Vector<String>();
		recognize(input, diffChars);
		return diffChars;
	}
	
	public String[] recognize_letters(ArrayList<ArrayList<Point2>> stroke_data) {
		String[] code = new String[stroke_data.size()];
		Recognizer r = new Recognizer(5,Math.PI/4, Math.PI /90, 40);
		for(int i = 0;i < stroke_data.size();i++) {
			String result = r.main(stroke_data.get(i));
			code[i] = result;
			System.out.println(code[i]);
		}
		return code;
	}
	
	
	private int recognize(String[] input, Vector<String> diffChars) {
		int match = -1;
		int score = 0;
		int max = -1;
		for(int j = 0;j < words.size();j++) {
			String[] candidate = words.get(8);
			if(candidate.length < input.length) continue;
			score = 0;
			for(int i = 0;i < input.length;i++) {
				//Log.v("candidate[i]", candidate[i]);
				//Log.v("input[i]", input[i]);
				if(candidate[i].compareTo(input[i]) == 0)score += 1;
			}
			if(max < score) {
				max = score;
				match = j;
				diffChars.clear();
				for(int i = 0;i < input.length;i++) {
					if(candidate[i].compareTo(input[i]) == 0)diffChars.add(candidate[i]);
				}
			}
		}
		
		return score;
	}
	
	
	/**
	 * @param args
	 */
	public static void main() {
		/*
		WordRecognizer w = new WordRecognizer();
		String[] input = {"pa", "l", "i1", "va", "sa"};
		Vector<String> diffChars = new Vector<String>();
		int i = w.recognize(input, diffChars);
		for(int j = 0;j < w.words.get(i).length;j++)System.out.println(w.words.get(i)[j]);
		System.out.println("You got all except" + diffChars.size() + " characters right:");
		for(int j = 0;j < diffChars.size();j++) System.out.println(diffChars.get(j) + ",");
		*/
	}
}
