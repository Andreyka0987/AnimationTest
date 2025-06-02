package org.example;

public class Touch {

    public static boolean isTouching(int x1, int y1, int x2, int y2, int distanceThreshold) {
        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        return distance <= distanceThreshold;
    }


}
