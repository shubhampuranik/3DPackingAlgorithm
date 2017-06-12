package mylogic;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by LG-2 on 6/12/2017.
 */
public class ThreeDPacking1 {
    public ArrayList<Point> topList;
    public ArrayList<Cuboid> inputCuboids,fitCuboids;
    public ArrayList<Rectangle> fragileRectangles;
    public Cuboid bin;
    public ThreeDPacking1(){
        bin=new Cuboid(200,500,200);
        bin.setBottomLeftRear(new Point(0,0,0));
        inputCuboids=new ArrayList<>();
        Random r=new Random();
        for(int i=0;i<60;i++)
            inputCuboids.add(new Cuboid((r.nextInt(5)+1)*20,(r.nextInt(5)+1)*20,(r.nextInt(5)+1)*20));
        topList=new ArrayList<>();
        for(int x=0;x<=bin.length;x++)
            for(int z=0;z<=bin.breadth;z++)
                topList.add(new Point(x,0,z));

    }
}
