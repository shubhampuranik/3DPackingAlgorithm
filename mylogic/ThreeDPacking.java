package mylogic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.Random;

/**
 * Created by LG-2 on 6/6/2017.
 */
public class ThreeDPacking {
    public ArrayList<Point> list;
    public ArrayList<Double[][]> draw;
    public ArrayList<Cuboid> cuboidArrayList;
    public void addAllPoints(Cuboid c){
        //The new cuboids will be placed from any of these 3 points
//
        for(int z=c.bottomRightRear.z;z<=c.bottomRightFront.z;z++)
            list.add(new Point(c.bottomRightRear.x,c.bottomRightRear.y,z));

        for(int x=c.bottomLeftFront.x;x<c.bottomRightFront.x;x++)
            list.add(new Point(x,c.bottomLeftFront.y,c.bottomLeftFront.z));

        for(int x=c.topLeftRear.x;x<=c.topRightRear.x/*+c.length/2*/;x++)
            list.add(new Point(x,c.topLeftRear.y,c.topLeftRear.z));

        for(int z=c.topLeftRear.z;z<c.topRightRear.z/*+c.breadth/2*/;z++)
            list.add(new Point(c.topLeftRear.x,c.topLeftRear.y,z));

        ListIterator<Point> pointListIterator=list.listIterator();

//        while(pointListIterator.hasNext()){
//
//            Point curr=pointListIterator.next();
//
//            if(curr.y<c.topLeftRear.y && curr.y>c.bottomLeftRear.y){
//
//                if(curr.x<c.bottomRightRear.x && curr.x>c.bottomLeftRear.x){
//
//                    if(curr.z<c.bottomLeftFront.z){
//
//                        pointListIterator.remove();
//
//                    }
//
//                }
//
//            }
//
//        }




//        list.add(c.topLeftRear);
//        list.add(c.bottomLeftFront);
//
//        list.add(c.bottomRightRear);
        Collections.sort(list,new YZXComparator());
//        Collections.sort(list,new ZYXComparator());
    }
    public ThreeDPacking(){
        list=new ArrayList<>();
        ArrayList<Cuboid> cuboids=new ArrayList<>();
        Random r=new Random();//This is for random generation of cuboids
        for(int i=0;i<100;i++)
            cuboids.add(new Cuboid((r.nextInt(5)+1)*20,(r.nextInt(5)+1)*20,(r.nextInt(5)+1)*20));

//        cuboids.add(new Cuboid(100,100,100));
//        cuboids.add(new Cuboid(100,100,100));
//        cuboids.add(new Cuboid(150,150,150));
//        cuboids.add(new Cuboid(150,150,150));
//        cuboids.add(new Cuboid(150,150,150));
//        cuboids.add(new Cuboid(150,150,150));
//        cuboids.add(new Cuboid(50,50,50));
//        cuboids.add(new Cuboid(50,50,50));
//        cuboids.add(new Cuboid(50,50,50));
//        cuboids.add(new Cuboid(50,50,50));
//        cuboids.add(new Cuboid(50,50,50));

        int length=300,breadth=300,height=300;

        for(int x=0;x<=length;x++)
            list.add(new Point(x,0,0));

        for(int z=0;z<=breadth;z++)
            list.add(new Point(0,0,z));

//        Collections.sort(cuboids,new VolumeComparator());
//        Collections.sort(cuboids,new HeightComparator());
        Collections.sort(cuboids,new BottomSurfaceAreaComparator());

        ArrayList<Cuboid> fitCuboids=new ArrayList<>();
        Cuboid c=cuboids.get(0);
        c.setBottomLeftRear(new Point(0, 0, 0));
        addAllPoints(c);
        fitCuboids.add(c);

        for(int i=1;i<cuboids.size();i++){
            //This checks the intersection of cuboids after it has bee placed at appropriate position and orientation
            Cuboid cuboid=cuboids.get(i);
            for(int j=0;j<list.size();j++) {
                Point p = list.get(j);
                cuboid.setBottomLeftRear(p);
                if (!cuboid.intersects(fitCuboids) && p.x+cuboid.length<=length && p.x+cuboid.length >= 0 && p.y+cuboid.height<=height && p.y+cuboid.height >= 0 && p.z+cuboid.breadth<=breadth && p.z+cuboid.breadth>= 0) {
                    fitCuboids.add(cuboid);
                    addAllPoints(cuboid);
                    break;
                }else{
                    cuboid.reset();
                    cuboid.rotate();
                    cuboid.setBottomLeftRear(p);
                    if (!cuboid.intersects(fitCuboids) && p.x+cuboid.length<=length && p.x+cuboid.length >= 0 && p.y+cuboid.height<=height && p.y+cuboid.height >= 0 && p.z+cuboid.breadth<=breadth && p.z+cuboid.breadth>= 0) {
                        fitCuboids.add(cuboid);
                        addAllPoints(cuboid);
                        break;
                    }
                }
            }

        }

        cuboidArrayList=new ArrayList<>();
        cuboidArrayList.add(new Cuboid(length,breadth,height));
        cuboidArrayList.addAll(fitCuboids);

    }
    public static void main(String[] args) {
        new ThreeDPacking();
    }
}
