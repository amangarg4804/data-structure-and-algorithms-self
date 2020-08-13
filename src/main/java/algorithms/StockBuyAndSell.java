package algorithms;

import java.util.ArrayList;
import java.util.List;

public class StockBuyAndSell {

    // The cost of stock on each day is given in an array A[] of size N. Find all the days on which you buy and sell the stock
    // so that in between those days your profit is maximum.
    // Valley Peak Approach - Buy on valleys and sell on peak
    // Buy on local minima and sell on local maxima
    // https://www.youtube.com/watch?v=JaosdXkUWTs
    //

    private static List<Interval> findBuyAndSellPoints(int[] arr) {
        if (arr.length == 1) {
            return null;
        }
        List<Interval> intervals = new ArrayList<>();
        int i = 0;
        while (i < arr.length) {
            int lastIndex = arr.length - 1;
            // Find a valley, Idea is to keep increasing i
            // if the current element is greater than next element
            // Meaning graph is decreasing
            while (i < lastIndex && arr[i] > arr[i + 1]) {
                i++;
            }
            if (i == lastIndex) {
                break;
            }
            Interval interval = new Interval();
            interval.buy = i; // or arr[i] if we want to print the element itself
            i++;

            // Let's try to find peak now. Idea is to keep increasing i
            // if the current element is less than it's next element
            // Meaning graph is increasing
            while (i < lastIndex && arr[i] < arr[i + 1]) {
                i++;
            }
            interval.sell = i; // or arr[i] if we want to print the element itself
            intervals.add(interval);
            i++;
        }
        return intervals;
    }

    public static void main(String[] args) {
        int[] arr = {100, 180, 260, 310, 40, 535, 695};
        List<Interval> intervals = findBuyAndSellPoints(arr);
        if (intervals == null || intervals.isEmpty()) {
            System.out.println("No Profit");
        } else {
            for (Interval interval : intervals) {
                System.out.print("(" + interval.buy + " " + interval.sell + ") ");
            }
            System.out.println();
        }

    }

    private static class Interval {

        private int buy;
        private int sell;

        public int getBuy() {
            return buy;
        }

        public void setBuy(int buy) {
            this.buy = buy;
        }

        public int getSell() {
            return sell;
        }

        public void setSell(int sell) {
            this.sell = sell;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "buy=" + buy +
                    ", sell=" + sell +
                    '}';
        }
    }

}
