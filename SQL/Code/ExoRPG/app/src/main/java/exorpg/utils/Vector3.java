package exorpg.utils;

public class Vector3 extends Vector2 {
    double z;
    
    public Vector3(){
        super();
        this.z = 0;
    }

    public Vector3(double x, double y){
        super(x, y);
        this.z = 0;
    }
    
    public Vector3(double x, double y, double z){
        super(x, y);
        this.z = z;
    }

    @Override
    public double magnitude(){
        return Math.sqrt(this.x*this.x + this.y*this.y +this.z*this.z);
    }

    @Override
    public String toString(){
        return "V("+this.x+","+this.y+","+this.z+")";
    }

    public void methodeSpeciale(){
        
    }
}
