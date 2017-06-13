package mylogic;

import javax.xml.transform.sax.SAXSource;
import java.util.*;

/**
 * Created by LG-2 on 6/6/2017.
 */
public class ThreeDPacking {
    public ArrayList<Point> list,midPoints;
    public ArrayList<Rectangle> fragileRects,topFaces;
    public Cuboid bin;
    public void addAllPoints(Cuboid c){
        Point midPoint=new Point((c.topLeftRear.x+c.topRightRear.x)/2,c.topLeftRear.y,(c.topLeftRear.z+c.topLeftFront.z)/2);
        midPoints.add(midPoint);
        Rectangle rectangle=new Rectangle(c.length,c.breadth);
        rectangle.setTopLeft(new Point2D(c.topLeftRear.x,c.topLeftRear.z));
        topFaces.add(rectangle);
        for(int z=c.bottomLeftRear.z;z<=c.bottomLeftFront.z;z++)
            list.add(new Point(c.bottomRightRear.x,c.bottomRightRear.y,z));
        for(int x=c.bottomLeftRear.x;x<c.bottomRightRear.x;x++)
            list.add(new Point(x,c.bottomLeftFront.y,c.bottomLeftFront.z));
        if(!c.isFragile){
            for(int x=c.topLeftRear.x;x<=c.topRightRear.x;x++)
                list.add(new Point(x,c.topLeftRear.y,c.topLeftRear.z));
            for(int z=c.topLeftRear.z;z<c.topRightFront.z;z++)
                list.add(new Point(c.topLeftRear.x,c.topLeftRear.y,z));
        }else {
            fragileRects.add(new Rectangle(new Point2D(c.topLeftRear.x,c.topLeftRear.z),c.length,c.breadth));
            c.color=new float[]{0f,1f,0f};
        }
//        Collections.sort(list,new YZXComparator());
//        Collections.sort(list,new XZYComparator());
        Collections.sort(list,new ZYXComparator());
        System.out.println(list);
    }
    public ArrayList<Cuboid> fitCuboids(int length,int breadth,int height,ArrayList<Cuboid> cuboids){
        midPoints=new ArrayList<>();
        fragileRects=new ArrayList<>();
        topFaces=new ArrayList<>();
        ArrayList<Cuboid> fitCuboids=new ArrayList<>();
        Cuboid c=cuboids.get(0);
        c.setBottomLeftRear(new Point(0, 0, 0));
        fitCuboids.add(c);
        list=new ArrayList<>();
        for(int x=c.bottomRightRear.x;x<=length;x++)
            list.add(new Point(x,0,0));
        for(int z=c.bottomLeftFront.z;z<=breadth;z++)
            list.add(new Point(0,0,z));
        addAllPoints(c);                                
        fitCuboids.add(c);
        for(int i=1;i<cuboids.size();i++){
            c=cuboids.get(i);
//            System.out.println(c);
            for(int j=0;j<list.size();j++) {
                Point p = list.get(j);
                c.setBottomLeftRear(p);
                Rectangle bottomFace=new Rectangle(new Point2D(c.bottomLeftRear.x,c.bottomLeftRear.z),c.length,c.breadth);
                if(!bottomFace.intersects(fragileRects)){
                    if(c.bottomLeftRear.y>0){
                        System.out.println(p+" "+c.contains1(fitCuboids)+" "+ c.containsAllCornerPoints(fitCuboids)+" "+ !c.intersects(fitCuboids));
                        if (c.contains1(fitCuboids) &&  c.containsAllCornerPoints(fitCuboids) && !c.intersects(fitCuboids) && p.x + c.length <= length && p.x + c.length >= 0 && p.y + c.height <= height && p.y + c.height >= 0 && p.z + c.breadth <= breadth && p.z + c.breadth >= 0) {
                            fitCuboids.add(c);
                            addAllPoints(c);
                            break;
                        } else {
                            c.reset();
                            c.rotate();
                            c.setBottomLeftRear(p);
                            if ( c.contains1(fitCuboids) &&  c.containsAllCornerPoints(fitCuboids) &&  !c.intersects(fitCuboids) && p.x + c.length <= length && p.x + c.length >= 0 && p.y + c.height <= height && p.y + c.height >= 0 && p.z + c.breadth <= breadth && p.z + c.breadth >= 0) {
                                fitCuboids.add(c);
                                addAllPoints(c);
                                break;
                            }
                        }
                    }else {
                        if (!c.intersects(fitCuboids) && p.x + c.length <= length && p.x + c.length >= 0 && p.y + c.height <= height && p.y + c.height >= 0 && p.z + c.breadth <= breadth && p.z + c.breadth >= 0) {
                            fitCuboids.add(c);
                            addAllPoints(c);
                            break;
                        } else {
                            c.reset();
                            c.rotate();
                            c.setBottomLeftRear(p);
                            if (!c.intersects(fitCuboids) && p.x + c.length <= length && p.x + c.length >= 0 && p.y + c.height <= height && p.y + c.height >= 0 && p.z + c.breadth <= breadth && p.z + c.breadth >= 0) {
                                fitCuboids.add(c);
                                addAllPoints(c);
                                break;
                            }
                        }
                    }
                }
            }
        }
        ArrayList<Cuboid> fitCuboids1=new ArrayList<>();
        c=new Cuboid(length,breadth,height);
        c.setBottomLeftRear(new Point(0,0,0));
        fitCuboids1.add(c);
        fitCuboids1.addAll(fitCuboids);
        return fitCuboids1;
    }
    public static void main(String[] args) {
        Random r=new Random();//This is for random generation of cuboids
        ArrayList<Cuboid> cuboids=new ArrayList<>();
        for(int i=0;i<50;i++)
            cuboids.add(new Cuboid((r.nextInt(5)+1)*20,(r.nextInt(5)+1)*20,(r.nextInt(5)+1)*20));
        ThreeDPacking packing=new ThreeDPacking();
        System.out.println(packing.fitCuboids(500, 500, 500, cuboids));
    }
}
