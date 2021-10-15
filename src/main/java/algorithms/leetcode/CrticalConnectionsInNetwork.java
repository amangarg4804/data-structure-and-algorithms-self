package algorithms.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrticalConnectionsInNetwork {

  public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < connections.size(); i++) {
      int server1 = connections.get(i).get(0);
      int server2 = connections.get(i).get(1);

      if (map.containsKey(server1)) {
        int server1Count = map.get(server1);
        map.put(server1, ++server1Count);
      } else {
        map.put(server1, 1);
      }

      if (map.containsKey(server2)) {
        int server2Count = map.get(server2);
        map.put(server2, ++server2Count);
      } else {
        map.put(server2, 1);
      }
    }

    List<List<Integer>> criticalConnections = new ArrayList<>();
    for (int i = 0; i < connections.size(); i++) {
      int server1 = connections.get(i).get(0);
      int server2 = connections.get(i).get(1);

      if(map.get(server1) <2 || map.get(server2) < 2) {
        List<Integer> criticalConnection = new ArrayList<>();
        criticalConnection.add(server1);
        criticalConnection.add(server2);
        criticalConnections.add(criticalConnection);
      }
    }
    return criticalConnections;
  }
}
