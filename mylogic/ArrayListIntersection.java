package mylogic;

import java.util.ArrayList;

/**
 * Created by LG-2 on 6/12/2017.
 */
public class ArrayListIntersection {
    public static void main(String[] args) {
        ArrayList<String> list1=new ArrayList<>();
        list1.add("A");
        list1.add("B");
        list1.add("C");
        list1.add("D");
        list1.add("E");
        System.out.println("LIST1="+list1);
        ArrayList<String> list2=new ArrayList<>();
        list2.add("C");
        list2.add("D");
        list2.add("E");
        list2.add("D");
        list2.add("E");
        list2.add("F");
        System.out.println("LIST2="+list2);
        ArrayList<String> list3=new ArrayList<>(list1);
        list3.retainAll(list2);
        System.out.println("LIST3="+list3);
    }
}
