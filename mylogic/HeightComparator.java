package mylogic;

import java.util.Comparator;

/**
 * Created by LG-2 on 6/9/2017.
 */
public class HeightComparator implements Comparator<Cuboid> {
    @Override
    public int compare(Cuboid o1, Cuboid o2) {
        if(o1.height>o2.height)
            return 1;
        else if(o1.height<o2.height)
            return -1;
        else
            return 0;
    }
}
