import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static class Node implements Comparable<Node> {
        int v;
        int distance;

        public Node(int v, int distance) {
            this.v = v;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int V = Integer.parseInt(st.nextToken());
        final int E = Integer.parseInt(st.nextToken());
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        final int INF = 100000000;
        int[] distances = new int[V + 1];
        for (int v = 1; v <= V; ++v) {
            map.put(v, new HashMap<Integer, Integer>());
            distances[v] = INF;
        }

        int start = Integer.parseInt(br.readLine());
        for (int e = 0; e < E; ++e) {
            StringTokenizer edgeSt = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(edgeSt.nextToken());
            int dest = Integer.parseInt(edgeSt.nextToken());
            int distance = Integer.parseInt(edgeSt.nextToken());

            HashMap<Integer, Integer> startMap = map.get(src);
            if (startMap.containsKey(dest)) {
                startMap.put(dest, Math.min(startMap.get(dest), distance));
            } else {
                startMap.put(dest, distance);
            }
        }


        boolean[] visited = new boolean[V + 1];
        distances[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int next : map.get(start).keySet()) {
            int distance = map.get(start).get(next);
            distances[next] = distance;
            pq.offer(new Node(next, distance));
        }

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (visited[node.v]) {
                continue;
            }

            visited[node.v] = true;
            for (int next : map.get(node.v).keySet()) {
                int nextDistance = distances[node.v] + map.get(node.v).getOrDefault(next, INF);
                if (nextDistance < distances[next]) {
                    distances[next] = nextDistance;
                    pq.offer(new Node(next, nextDistance));
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int v = 1; v <= V; ++v) {
            int distance = distances[v];

            if (distance == INF) {
                builder.append("INF");
            } else {
                builder.append(distance);
            }

            builder.append(System.lineSeparator());
        }

        bw.write(builder.toString());
        br.close();
        bw.close();
    }
}
