package carlos.ifrs.com.testbeacons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Created by carlos on 08/01/18.
 */

public class BeaconList {
    private static ArrayList<Beacon> beacons;
    private static Random randomGenerator = new Random();

    private BeaconList() {


    }

    public static ArrayList<Beacon> getInstance() {
        if (beacons == null) {
            beacons = new ArrayList<Beacon>();
        }
        return beacons;
    }

    public static int getNextId(){

        return randomGenerator.nextInt();

    }
}
