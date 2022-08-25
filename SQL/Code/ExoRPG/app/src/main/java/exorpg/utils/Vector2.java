package exorpg.utils;

public class Vector2 {
    public double x;
    public double y;

    public double magnitude(){
        return Math.sqrt(x*x+ y*y);
    }

    public Vector2(){
        this.x = 0;
        this.y = 0;
    }

    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "V("+x+","+y+")";
    }

    public boolean equals(Vector2 other){
        return (this.x == other.x && this.y == other.y);
    }
}
