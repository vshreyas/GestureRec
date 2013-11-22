package com.example.gesturerec;

class Point
{
  public float X;
  public float Y;
  public static boolean flag = false;
  Point( float x, float y)
  {
    X = x;
    Y = y;
  }

  float distance( Point other)
  {
    return (float) Math.sqrt(((X - other.X) * (X - other.X)) + ((Y - other.Y) * (Y - other.Y)));
  }
}