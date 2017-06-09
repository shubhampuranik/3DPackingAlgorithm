package mylogic;

import javafx.scene.shape.Box;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by LG-2 on 6/6/2017.
 */
public class Cuboid {
    public int length,breadth,height,id;
    public Point topLeftFront,topRightFront,topLeftRear,topRightRear,bottomLeftFront,bottomRightFront,bottomLeftRear,bottomRightRear;
    public float[] color;
    public static Random r;
    public static int idCounter;
    static{
        r=new Random();
        idCounter=0;
    }
    public Cuboid(int length,int breadth,int height){
        this.length=length;
        this.breadth=breadth;
        this.height=height;
        color=new float[]{(new Float(r.nextInt(255)+1)/255.0f),(new Float(r.nextInt(255)+1)/255.0f),(new Float(r.nextInt(255)+1)/255.0f)};
        id=idCounter++;
    }
    public String toString(){
        return "(L="+length+",B="+breadth+",H="+height+",BLR="+bottomLeftRear+",BRR="+bottomRightRear+",BRF="+bottomRightFront+",BLF="+bottomLeftFront+",TLF="+topLeftFront+",TLR="+topLeftRear+",TRR="+topRightRear+",TRF="+topRightFront+" COLOR=("+color[0]+","+color[1]+","+color[2]+"))";
    }
    public void rotate(){
        int temp=this.length;
        this.length=this.breadth;
        this.breadth=temp;
    }
    public int getLeft(){ return this.topLeftFront.x; }
    public int getRight(){
        return this.topRightFront.x;
    }
    public int getTop(){
        return this.topLeftFront.y;
    }
    public int getBottom(){
        return this.bottomLeftFront.y;
    }
    public int getFront(){
        return this.topLeftFront.z;
    }
    public int getRear(){
        return this.topLeftRear.z;
    }


    public void setBottomLeftRear(Point bottomLeftRear){
        this.bottomLeftRear=bottomLeftRear;
        this.bottomRightRear=new Point(this.bottomLeftRear.x+length,this.bottomLeftRear.y,this.bottomLeftRear.z);
        this.bottomRightFront=new Point(this.bottomRightRear.x,this.bottomRightRear.y,this.bottomRightRear.z+breadth);
        this.bottomLeftFront=new Point(this.bottomRightFront.x-length,this.bottomRightFront.y,this.bottomRightFront.z);
        this.topLeftFront=new Point(this.bottomLeftFront.x,this.bottomLeftFront.y+height,this.bottomLeftFront.z);
        this.topRightFront=new Point(this.topLeftFront.x+length,this.topLeftFront.y,this.topLeftFront.z);
        this.topRightRear=new Point(this.topRightFront.x,this.topRightFront.y,this.topRightFront.z-breadth);
        this.topLeftRear=new Point(this.topRightRear.x-length,this.topRightRear.y,this.topRightRear.z);
    }
    public void setBottomRightRear(Point bottomRightRear){
        this.bottomRightRear=bottomRightRear;
        this.bottomRightFront=new Point(this.bottomRightRear.x,this.bottomRightRear.y,this.bottomRightRear.z+breadth);
        this.bottomLeftFront=new Point(this.bottomRightFront.x-length,this.bottomRightFront.y,this.bottomRightFront.z);
        this.bottomLeftRear=new Point(this.bottomLeftFront.x,this.bottomLeftFront.y,this.bottomLeftFront.z-breadth);
        this.topLeftRear=new Point(this.bottomLeftRear.x,this.bottomLeftRear.y+height,this.bottomLeftRear.z);
        this.topRightRear=new Point(this.topLeftRear.x+length,this.topLeftRear.y,this.topLeftRear.z);
        this.topRightFront=new Point(this.topRightRear.x,this.topRightRear.y,this.topRightRear.z+breadth);
        this.topLeftFront=new Point(this.topRightFront.x-length,this.topRightFront.y,this.topRightFront.z);
    }
    public void setBottomRightFront(Point bottomRightFront){
        this.bottomRightFront=bottomRightFront;
        this.bottomLeftFront=new Point(this.bottomRightFront.x-length,this.bottomRightFront.y,this.bottomRightFront.z);
        this.bottomLeftRear=new Point(this.bottomLeftFront.x,this.bottomLeftFront.y,this.bottomLeftFront.z-breadth);
        this.bottomRightRear=new Point(this.bottomLeftRear.x+length,this.bottomLeftRear.y,this.bottomLeftRear.z);
        this.topRightRear=new Point(this.bottomRightRear.x,this.bottomRightRear.y+height,this.bottomRightRear.z);
        this.topRightFront=new Point(this.topRightRear.x,this.topRightRear.y,this.topRightRear.z+breadth);
        this.topLeftFront=new Point(this.topRightFront.x-length,this.topRightFront.y,this.topRightFront.z);
        this.topLeftRear=new Point(this.topLeftFront.x,this.topLeftFront.y,this.topLeftFront.z-breadth);
    }
    public void setBottomLeftFront(Point bottomLeftFront){
        this.bottomLeftFront=bottomLeftFront;
        this.bottomLeftRear=new Point(this.bottomLeftFront.x,this.bottomLeftFront.y,this.bottomLeftFront.z-breadth);
        this.bottomRightRear=new Point(this.bottomLeftRear.x+length,this.bottomLeftRear.y,this.bottomLeftRear.z);
        this.bottomRightFront=new Point(this.bottomRightRear.x,this.bottomRightRear.y,this.bottomRightRear.z+breadth);
        this.topRightFront=new Point(this.bottomRightFront.x,this.bottomRightFront.y+height,this.bottomRightFront.z);
        this.topLeftFront=new Point(this.topRightFront.x-length,this.topRightFront.y,this.topRightFront.z);
        this.topLeftRear=new Point(this.topLeftFront.x,this.topLeftFront.y,this.topLeftFront.z-breadth);
        this.topRightRear=new Point(this.topLeftRear.x+length,this.topLeftRear.y,this.topLeftRear.z);
    }
    public void setTopLeftFront(Point topLeftFront){
        this.topLeftFront=topLeftFront;
        this.topRightFront=new Point(this.topLeftFront.x+this.length,this.topLeftFront.y,this.topLeftFront.z);
        this.topRightRear=new Point(this.topRightFront.x,this.topRightFront.y,this.topRightFront.z-this.breadth);
        this.topLeftRear=new Point(this.topRightRear.x-this.length,this.topRightRear.y,this.topRightRear.z);
        this.bottomLeftFront=new Point(this.topLeftFront.x,this.topLeftFront.y-height,this.topLeftFront.z);
        this.bottomRightFront=new Point(this.bottomLeftFront.x+length,this.bottomLeftFront.y,this.bottomLeftFront.z);
        this.bottomRightRear=new Point(this.bottomRightFront.x,this.bottomRightFront.y,this.bottomRightFront.z-breadth);
        this.bottomLeftRear=new Point(this.bottomRightRear.x-length,this.bottomRightRear.y,this.bottomRightRear.z);
    }
    public void setTopLeftRear(Point topLeftRear){
        this.topLeftRear=topLeftRear;
        this.topRightRear=new Point(this.topLeftRear.x+length,this.topLeftRear.y,this.topLeftRear.z);
        this.topRightFront=new Point(this.topRightRear.x,this.topRightRear.y,this.topRightRear.z+breadth);
        this.topLeftFront=new Point(this.topRightFront.x-length,this.topRightFront.y,this.topRightFront.z);
        this.bottomLeftFront=new Point(this.topLeftFront.x,this.topLeftFront.y-height,this.topLeftFront.z);
        this.bottomLeftRear=new Point(this.bottomLeftFront.x,this.bottomLeftFront.y,this.bottomLeftFront.z-breadth);
        this.bottomRightRear=new Point(this.bottomLeftRear.x+length,this.bottomLeftRear.y,this.bottomLeftRear.z);
        this.bottomRightFront=new Point(this.bottomRightRear.x,this.bottomRightRear.y,this.bottomRightRear.z+breadth);
    }
    public void setTopRightRear(Point topRightRear){
        this.topRightRear=topRightRear;
        this.topRightFront=new Point(this.topRightRear.x,this.topRightRear.y,this.topRightRear.z+breadth);
        this.topLeftFront=new Point(this.topRightFront.x-length,this.topRightFront.y,this.topRightFront.z);
        this.topLeftRear=new Point(this.topLeftFront.x,this.topLeftFront.y,this.topLeftFront.z-breadth);
        this.bottomLeftRear=new Point(this.topLeftRear.x,this.topLeftRear.y-height,this.topLeftRear.z);
        this.bottomRightRear=new Point(this.bottomLeftRear.x+length,this.bottomLeftRear.y,this.bottomLeftRear.z);
        this.bottomRightFront=new Point(this.bottomRightRear.x,this.bottomRightRear.y,this.bottomRightRear.z+breadth);
        this.bottomLeftFront=new Point(this.bottomRightFront.x-length,this.bottomRightFront.y,this.bottomRightFront.z);
    }
    public void setTopRightFront(Point topRightFront){
        this.topRightFront=topRightFront;
        this.topLeftFront=new Point(this.topRightFront.x-length,this.topRightFront.y,this.topRightFront.z);
        this.topLeftRear=new Point(this.topLeftFront.x,this.topLeftFront.y,this.topLeftFront.z-breadth);
        this.topRightRear=new Point(this.topLeftRear.x+length,this.topLeftRear.y,this.topLeftRear.z);
        this.bottomRightRear=new Point(this.topRightRear.x,this.topRightRear.y-height,this.topRightRear.z);
        this.bottomRightFront=new Point(this.bottomRightRear.x,this.bottomRightRear.y,this.bottomRightRear.z+breadth);
        this.bottomLeftFront=new Point(this.bottomRightFront.x-length,this.bottomRightFront.y,this.bottomRightFront.z);
        this.bottomLeftRear=new Point(this.bottomLeftFront.x,this.bottomLeftFront.y,this.bottomLeftFront.z-breadth);
    }
    public void reset(){
        this.topRightFront=new Point(0,0,0);
        this.topLeftFront=new Point(0,0,0);
        this.topLeftRear=new Point(0,0,0);
        this.topRightRear=new Point(0,0,0);
        this.bottomRightRear=new Point(0,0,0);
        this.bottomRightFront=new Point(0,0,0);
        this.bottomLeftFront=new Point(0,0,0);
        this.bottomLeftRear=new Point(0,0,0);
    }
    public boolean intersects(Cuboid c){
        int left1=this.getLeft(),left2=c.getLeft(),right1=this.getRight(),right2=c.getRight(),front1=this.getFront(),front2=c.getFront(),rear1=this.getRear(),rear2=c.getRear(),top1=this.getTop(),top2=c.getTop(),bottom1=this.getBottom(),bottom2=c.getBottom();
        if(right1<=left2 || rear1>=front2 || left1>=right2 || front1<=rear2 || bottom1>=top2 || top1<=bottom2)
            return false;
        return true;
    }
    public boolean intersects(ArrayList<Cuboid> cuboids){
        boolean intersects=false;
        for(Cuboid c:cuboids){
            if(this.intersects(c)){
                intersects=true;
                break;
            }
        }
        return intersects;
    }
}
