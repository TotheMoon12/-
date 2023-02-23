import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public class Move {
        int src;
        int time;

        public Move(int src, int time) {
            this.src = src;
            this.time = time;
        }
    }

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];

        ArrayList<Integer>[] map = new ArrayList[n + 1];
        for (int idx = 1; idx <= n; ++idx) {
            map[idx] = new ArrayList<>();
        }

        for (int[] road : roads) {
            int a = road[0];
            int b = road[1];

            map[a].add(b);
            map[b].add(a);
        }

        boolean[] visited = new boolean[n + 1];
        visited[destination] = true;

        Queue<Move> queue = new LinkedList<>();
        queue.offer(new Move(destination, 0));
        int[] distances = new int[n + 1];
        final int IMPOSSIBLE = Integer.MAX_VALUE;
        Arrays.fill(distances, IMPOSSIBLE);
        distances[destination] = 0;

        while (!queue.isEmpty()) {
            Move move = queue.poll();

            for (int next : map[move.src]) {
                if (!visited[next]) {
                    visited[next] = true;
                    distances[next] = move.time + 1;
                    queue.offer(new Move(next, move.time + 1));
                }
            }
        }

        for (int idx = 0; idx < sources.length; ++idx) {
            int source = sources[idx];

            int distance = distances[source];
            if (distance != IMPOSSIBLE) {
                answer[idx] = distance;
            } else {
                answer[idx] = -1;
            }
        }

        return answer;
    }
}
