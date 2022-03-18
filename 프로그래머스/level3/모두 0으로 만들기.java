import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Solution {
    public long solution(int[] a, int[][] edges) {
        long[] weight = new long[a.length];
        for (int i = 0; i < a.length; ++i) {
            weight[i] = a[i];
            sum += a[i];
        }


        HashMap<Integer, Boolean>[] map = new HashMap[a.length];
        for (int i = 0; i < a.length; ++i) {
            map[i] = new HashMap<>();
        }

        for (int i = 0; i < edges.length; ++i) {
            int node1 = edges[i][0];
            int node2 = edges[i][1];

            map[node1].put(node2, false);
            map[node2].put(node1, false);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < map.length; ++i) {
            if (map[i].size() == 1) {
                queue.add(i);
            }
        }

        long count = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();

            Set<Integer> set = map[node].keySet();
            if (set.size() == 1) {
                Iterator<Integer> it = set.iterator();
                int next = it.next();
                if (weight[node] != 0) {
                    count += Math.abs(weight[node]);
                    weight[next] += weight[node];
                    weight[node] = 0;
                }

                map[node].remove(next);
                map[next].remove(node);
                queue.add(next);
            }
        }

        boolean isZero = true;
        for (int i = 0; i < weight.length; ++i) {
            if (weight[i] != 0) {
                isZero = false;
                break;
            }
        }

        if (isZero) {
            return count;
        } else {
            return -1;
        }
    }
}
