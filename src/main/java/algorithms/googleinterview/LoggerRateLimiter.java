package algorithms.googleinterview;

import java.util.HashMap;
import java.util.Map;
//Design a logger system that receive a stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.
//Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.
//It is possible that several messages arrive roughly at the same time.
public class LoggerRateLimiter {
    Map<String, Integer> map;

    public LoggerRateLimiter() {
        this.map = new HashMap<>();
    }

    public boolean shouldPrintMessage1(int timestamp, String message) {
        boolean shouldPrint = false;
        if(map.containsKey(message) ) {
            int previous = map.get(message);
            if(timestamp -previous >10) {
                shouldPrint=true;
                map.put(message, timestamp);
            }
        } else {
            shouldPrint = true;
            map.put(message, timestamp);
        }
        return shouldPrint;
    }
    public boolean shouldPrintMessage2(int timestamp, String message) {
        if(!map.containsKey(message) || map.get(message) - timestamp > 10) {
            map.put(message, timestamp);
            return true;
        }
        return false;
    }
}
