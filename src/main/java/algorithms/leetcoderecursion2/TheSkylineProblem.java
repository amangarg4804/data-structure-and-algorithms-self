package algorithms.leetcoderecursion2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class TheSkylineProblem {

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        List<Point> points = new ArrayList<>();
        for(int i=0; i< buildings.length; i++) {
            int start = buildings[i][0];
            int end = buildings[i][1];
            int height = buildings[i][2];
            points.add(new Point(start, -height)); // use -height in case of start of building
            points.add(new Point(end, height));
        }
        points.sort((p1,p2) -> {
            if(p1.x ==p2.x) {
            return p1.height-p2.height;
        }
            return p1.x-p2.x;
        });

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.offer(0);
        int currentHeight = 0;
        for (int i=0; i< points.size(); i++) {
            Point point = points.get(i);
            if(point.height <0) { //meaning start point of building
                maxHeap.offer(-point.height);  // - because we used -height while creating start points
            } else {
                maxHeap.remove(point.height);
            }
            if(maxHeap.peek() != currentHeight) {
                // x axis value equal to current point's x value. Height equals max in heap
                List<Integer> resultPoint = new ArrayList<>();
                resultPoint.add(point.x);
                resultPoint.add(maxHeap.peek()); // For the ending building, heap will only contain 0 as we would have removed all heights at line 35
                result.add(resultPoint);
                currentHeight = maxHeap.peek();
            }
        }
        return result;
    }

    class Point {
        int x;
        int height;
        public Point(int x, int height) {
            this.x = x;
            this.height = height;
        }
    }

}