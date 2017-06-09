package mylogic;

/**
 * Created by LG-2 on 6/6/2017.
 */
public class Point {
    public int x,y,z;
    public Point(int x,int y,int z){
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public String toString(){
        return "("+x+","+y+","+z+")";
    }
}
