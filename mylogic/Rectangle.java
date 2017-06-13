package mylogic;



import java.util.ArrayList;

/**
 * Created by LG-2 on 6/1/2017.
 */
public class Rectangle {
    public int length,breadth,id;
    public static int counter;
    public Point2D bottomLeft,bottomRight,topLeft,topRight;
    public Rectangle(int length, int breadth){
        this.id=counter++;
        this.length=length;
        this.breadth=breadth;
    }
    public Rectangle(Point2D topLeft, int length, int breadth){
        this.length=length;
        this.breadth=breadth;
        this.topLeft=topLeft;
        this.topRight=new Point2D(topLeft.x+length,topLeft.y);
        this.bottomLeft=new Point2D(topLeft.x,topLeft.y+breadth);
        this.bottomRight=new Point2D(topLeft.x+length,topLeft.y+breadth);
    }
    public void setTopLeft(Point2D topLeft){
        this.topLeft=topLeft;
        this.topRight=new Point2D(topLeft.x+length,topLeft.y);
        this.bottomLeft=new Point2D(topLeft.x,topLeft.y+breadth);
        this.bottomRight=new Point2D(topLeft.x+length,topLeft.y+breadth);
    }
    public void setTopRight(Point2D topRight){
        this.topRight=topRight;
        this.topLeft=new Point2D(this.topRight.x-length,this.topRight.y);
        this.bottomLeft=new Point2D(this.topLeft.x,this.topLeft.y+breadth);
        this.bottomRight=new Point2D(this.bottomLeft.x+breadth,this.bottomLeft.y);
    }
    public void setBottomLeft(Point2D bottomLeft){
        this.bottomLeft=bottomLeft;
        this.bottomRight=new Point2D(this.bottomLeft.x+length,this.bottomLeft.y);
        this.topLeft=new Point2D(this.bottomLeft.x,this.bottomLeft.y-breadth);
        this.topRight=new Point2D(this.topLeft.x+length,this.topLeft.y);
    }
    public void setBottomRight(Point2D bottomRight){
        this.bottomRight=bottomRight;
        this.bottomLeft=new Point2D(this.bottomRight.x-length,this.bottomRight.y);
        this.topLeft=new Point2D(this.bottomLeft.x,this.bottomLeft.y-breadth);
        this.topRight=new Point2D(this.topLeft.x+length,this.topLeft.y);
    }
    public void reset(){
        this.topLeft=new Point2D(0,0);
        this.topRight=new Point2D(0,0);
        this.bottomLeft=new Point2D(0,0);
        this.bottomRight=new Point2D(0,0);
    }
    public void rotate(){
        int temp=this.length;
        this.length=this.breadth;
        this.breadth=temp;
    }
    public boolean intersects(Rectangle r){
        java.awt.Rectangle r1=new java.awt.Rectangle(r.topLeft.x,r.topLeft.y,r.length,r.breadth);
        java.awt.Rectangle r2=new java.awt.Rectangle(this.topLeft.x,this.topLeft.y,this.length,this.breadth);
        java.awt.Rectangle inter=r1.intersection(r2);
        return !inter.isEmpty();
    }
    public boolean intersects(ArrayList<Rectangle> rect){
        boolean intersects=false;
        for(Rectangle r:rect){
            if(this.intersects(r)){
                intersects=true;
                break;
            }
        }
        return intersects;
    }
    public boolean contains(Point p){
        java.awt.Rectangle rectangle=new java.awt.Rectangle(this.topLeft.x,this.topLeft.y,this.length,this.breadth);
        return rectangle.contains(p.x,p.z);
    }
    public boolean contains(Point2D p){
        java.awt.Rectangle rectangle=new java.awt.Rectangle(this.topLeft.x,this.topLeft.y,this.length,this.breadth);
        return rectangle.contains(p.x,p.y);
    }
    public boolean contains1(Point2D p){
        if(p.x >= this.topLeft.x && p.x <= this.topRight.x && p.y >= this.topLeft.y && p.y <= this.bottomLeft.y)
            return true;
        return false;
    }
    public boolean contains(ArrayList<Point> points){
        boolean contains=false;
        int c=0;
        for(Point p:points){
            if(this.contains(p) && p.z>=this.breadth/2){
                contains=true;
                break;
            }
        }
        return contains;
    }
    public boolean containsAllCornerPoints(Rectangle rectangle){
        return this.contains(rectangle.topLeft) && this.contains(rectangle.topRight) && this.contains(bottomLeft) && this.contains(bottomRight);
    }
    public boolean containsAllCornerPoints(ArrayList<Rectangle> topFaces){
        boolean contains=false;
        int c=0;
        for(Rectangle topFace:topFaces){
            if(topFace.contains(this.topLeft))
                c++;
            if(topFace.contains(this.topRight))
                c++;
            if(topFace.contains(this.bottomLeft))
                c++;
            if(topFace.contains(this.bottomRight))
                c++;
            if(c==4) {
                contains = true;
                break;
            }
        }
        return contains;
    }
    public boolean isOutOfBin(Rectangle bin){
        java.awt.Rectangle r1=new java.awt.Rectangle(this.topLeft.x,this.topLeft.y,this.length,this.breadth);
        java.awt.Rectangle r2=new java.awt.Rectangle(bin.topLeft.x,bin.topLeft.y,bin.length,bin.breadth);
//        java.awt.Rectangle inter=r1.intersection(r2);
//        int area=new Double(inter.getWidth()).intValue()*new Double(inter.getHeight()).intValue();
//        if(area<=(r1.width*r1.height))
        return !r2.contains(r1);
    }
    public String toString(){
        return "("+id+" L="+length+",B="+breadth+",TL="+topLeft+",TR="+topRight+",BL="+bottomLeft+",BR="+bottomRight+")";
    }
    public static void main(String[] args) {
        Rectangle r1=new Rectangle(new Point2D(0,0),300,300);
        Rectangle r2=new Rectangle(new Point2D(0,0),300,300);
        r1.setTopLeft(new Point2D(0,0));
        Point2D d=new Point2D(0,299);
        System.out.println(r1.contains(d));

    }
}
