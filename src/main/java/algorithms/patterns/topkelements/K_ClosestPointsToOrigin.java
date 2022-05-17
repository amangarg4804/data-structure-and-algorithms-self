package algorithms.patterns.topkelements;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class K_ClosestPointsToOrigin {

  public static void main(String[] args) {
    System.out.println(closestPoints(new Point[]{new Point(1, 2), new Point(2, 3)}, 1));
    System.out.println(closestPoints(new Point[]{new Point(1, 3), new Point(3, 4), new Point(2, -1)}, 2));
  }

  private static List<Point> closestPoints(Point[] arr, int k) {
    PriorityQueue<Point> pq = new PriorityQueue<>((p1, p2) -> p2.distanceFromOrigin() - p1
        .distanceFromOrigin());

    for (int i =0 ; i< k; i++) {
      pq.offer(arr[i]);
    }

    for (int i = k; i< arr.length ; i++) {
      if(pq.peek().distanceFromOrigin() > arr[i].distanceFromOrigin()) {
        pq.poll();
        pq.offer(arr[i]);
      }
    }
    return new ArrayList<>(pq);
    // time N*LogK
  }
}

class Point {
  int x;
  int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int distanceFromOrigin() {
    return (x *x) + (y*y);
  }

  @Override
  public String toString() {
    return "Point{" +
        "x=" + x +
        ", y=" + y +
        '}';
  }
}