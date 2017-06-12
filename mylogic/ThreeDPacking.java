package mylogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.Random;

/**
 * Created by LG-2 on 6/6/2017.
 */
public class ThreeDPacking {
    public ArrayList<Point> list,topList;
    public ArrayList<Double[][]> draw;
    public ArrayList<Cuboid> cuboidArrayList;
    public ArrayList<Rectangle> fragileRects;
    public void addAndRemovePoints(Cuboid c){
        ListIterator<Point> pointListIterator=topList.listIterator();
        while(pointListIterator.hasNext()){
            Point curr=pointListIterator.next();
            if(curr.x>=c.bottomLeftRear.x && curr.x<=c.bottomRightRear.x && curr.z>=c.bottomLeftRear.z && curr.z<=c.topLeftFront.z){
                pointListIterator.remove();
                pointListIterator.add(new Point(curr.x,c.topLeftFront.y,curr.z));
            }
        }
    }
    public void addAllPoints(Cuboid c){
        for(int z=c.bottomRightRear.z;z<=c.bottomRightFront.z;z++)
            list.add(new Point(c.bottomRightRear.x,c.bottomRightRear.y,z));
        for(int x=c.bottomLeftFront.x;x<c.bottomRightFront.x;x++)
            list.add(new Point(x,c.bottomLeftFront.y,c.bottomLeftFront.z));
        if(!c.isFragile){
            for(int x=c.topLeftRear.x;x<=c.topRightRear.x;x++)
                list.add(new Point(x,c.topLeftRear.y,c.topLeftRear.z));
            for(int z=c.topLeftRear.z;z<c.topRightFront.z;z++)
                list.add(new Point(c.topLeftRear.x,c.topLeftRear.y,z));
        }else {
            fragileRects.add(new Rectangle(new Point2D(c.topLeftRear.x,c.topLeftRear.z),c.length,c.breadth));
            c.color=new float[]{1f,0f,0f};
        }
        Collections.sort(list,new YZXComparator());
//        Collections.sort(list,new ZYXComparator());
    }
    public ThreeDPacking(){
        list=new ArrayList<>();

        ArrayList<Cuboid> cuboids=new ArrayList<>();
        fragileRects=new ArrayList<>();
        Random r=new Random();//This is for random generation of cuboids
        for(int i=0;i<50;i++)
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

        int length=200,breadth=500,height=200;

        topList=new ArrayList<>();
        for(int x=0;x<=length;x++)
            for(int z=0;z<=breadth;z++)
                topList.add(new Point(x,0,z));

        for(int x=0;x<=length;x++)
            list.add(new Point(x,0,0));

        for(int z=0;z<=breadth;z++)
            list.add(new Point(0,0,z));

//        Collections.sort(cuboids,new VolumeComparator());
//        Collections.sort(cuboids,new HeightComparator());
//        Collections.sort(cuboids,new BottomSurfaceAreaComparator());

        ArrayList<Cuboid> fitCuboids=new ArrayList<>();
        Cuboid c=cuboids.get(0);
        c.setBottomLeftRear(new Point(0, 0, 0));
        addAllPoints(c);
        fitCuboids.add(c);

        for(int i=1;i<cuboids.size();i++){
            boolean flag=false;
            //This checks the intersection of cuboids after it has bee placed at appropriate position and orientation
            Cuboid cuboid=cuboids.get(i);
//            System.out.println(cuboid);
            int area=(cuboid.length*cuboid.breadth)/2;
            FOR2: for(int j=0;j<list.size();j++) {
//                int intersection=0;
                Point p = list.get(j);
                cuboid.setBottomLeftRear(p);
                Rectangle bottomFace=new Rectangle(new Point2D(cuboid.bottomLeftRear.x,cuboid.bottomLeftRear.z),cuboid.length,cuboid.breadth);
                if(!bottomFace.intersects(fragileRects)){
//                    ArrayList<Point> bottomPoints=new ArrayList<>();
//                    for(int x=cuboid.bottomLeftRear.x;x<=cuboid.bottomRightRear.x;x++)
//                        for(int z=cuboid.bottomLeftRear.z;z<=cuboid.bottomLeftFront.z;z++)
//                            bottomPoints.add(new Point(x,cuboid.bottomLeftRear.y,z));

//                    addAndRemovePoints(cuboid);

//                    System.out.println("BOTTOM="+bottomPoints);
//                    System.out.println("TOP="+topList);

//                    ArrayList<Point> intersection=new ArrayList<>(topList);
//                    intersection.retainAll(bottomPoints);
//                    System.out.println(intersection);
////
//                    System.out.println("intersection="+intersection.size());

//                    FOR1: for(Point p1:topList){
//                        for(Point p2:bottomPoints){
//                            if(p1.equals(p2)){
//                                intersection++;
//                                if(intersection==area+1){
//                                    flag=true;
////                                    System.out.println(intersection);
//                                    break FOR1;
//                                }
//                            }
//                        }
//                    }
//                    if(flag) {
//                        System.out.println(cuboid);
//                        System.out.println(fitCuboids);
                        if (!cuboid.intersects(fitCuboids) && p.x + cuboid.length <= length && p.x + cuboid.length >= 0 && p.y + cuboid.height <= height && p.y + cuboid.height >= 0 && p.z + cuboid.breadth <= breadth && p.z + cuboid.breadth >= 0) {
                            fitCuboids.add(cuboid);
                            addAllPoints(cuboid);
                            addAndRemovePoints(cuboid);
                            break FOR2;
                        } else {
                            cuboid.reset();
                            cuboid.rotate();
                            cuboid.setBottomLeftRear(p);
//                            System.out.println(flag);
                            if (!cuboid.intersects(fitCuboids) && p.x + cuboid.length <= length && p.x + cuboid.length >= 0 && p.y + cuboid.height <= height && p.y + cuboid.height >= 0 && p.z + cuboid.breadth <= breadth && p.z + cuboid.breadth >= 0) {
                                fitCuboids.add(cuboid);
                                addAllPoints(cuboid);
                                addAndRemovePoints(cuboid);
                                break FOR2;
                            }
                        }
//                    }

                }


            }
//            System.out.println(fitCuboids);
        }

//        System.out.println(fragileRects);
            System.out.println(fitCuboids);
        System.out.println("FIT CUBOIDS:-"+fitCuboids.size());

        cuboidArrayList=new ArrayList<>();
        cuboidArrayList.add(new Cuboid(length,breadth,height));
        cuboidArrayList.addAll(fitCuboids);

    }
    public static void main(String[] args) {
        new ThreeDPacking();
    }
}
