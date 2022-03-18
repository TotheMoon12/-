import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = n;

        for (int i = 0; i < wires.length; ++i) {
            boolean[] visited = new boolean[n + 1];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(wires[i][0]);
            visited[wires[i][0]] = true;

            int count = 1;
            while (!queue.isEmpty()) {
                int src = queue.poll();

                for (int j = 0; j < wires.length; ++j) {
                    if (i == j) {
                        continue;
                    }

                    int v1 = wires[j][0];
                    int v2 = wires[j][1];

                    if (v1 == src) {
                        if (!visited[v2]) {
                            visited[v2] = true;
                            queue.add(v2);
                            ++count;
                        }
                    } else if (v2 == src) {
                        if (!visited[v1]) {
                            visited[v1] = true;
                            queue.add(v1);
                            ++count;
                        }
                    }
                }
            }

            answer = Math.min(answer, Math.abs(n - count - count));
        }

        return answer;
    }
}
