import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Pair implements Comparable<Pair>{
        int number;
        int cost;

        public Pair(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pair other) {
            return this.cost - other.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int cityCount = Integer.parseInt(st.nextToken());
        int cableCount = Integer.parseInt(st.nextToken());
        int stationCount = Integer.parseInt(st.nextToken());

        HashSet<Integer> stations = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[cityCount + 1];
        int[] parent = new int[cityCount + 1];
        ArrayList<Pair>[] map = new ArrayList[cityCount + 1];
        for (int i = 1; i <= cityCount; ++i) {
            parent[i] = i;
            map[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < stationCount; ++i) {
            int station = Integer.parseInt(st.nextToken());
            stations.add(station);
            visited[station] = true;
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int i = 0; i < cableCount; ++i) {
            st = new StringTokenizer(br.readLine());

            int city1 = Integer.parseInt(st.nextToken());
            int city2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if (stations.contains(city1) && stations.contains(city2)) {
                continue;
            }

            map[city1].add(new Pair(city2, cost));
            map[city2].add(new Pair(city1, cost));

            if (stations.contains(city1)) {
                pq.offer(new Pair(city2, cost));
            } else if (stations.contains(city2)) {
                pq.offer(new Pair(city1, cost));
            }
        }


        int cost = 0;
        if (!pq.isEmpty()) {
            Pair next = pq.poll();

            if (!visited[next.number]) {
                visited[next.number] = true;
                cost += next.cost;
                q.offer(next.number);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Pair next : map[cur]) {
                if (!visited[next.number]) {
                    pq.offer(next);
                }
            }

            while (!pq.isEmpty()) {
                Pair next = pq.poll();

                if (!visited[next.number]) {
                    visited[next.number] = true;
                    cost += next.cost;
                    q.offer(next.number);
                    break;
                }
            }
        }
        
        System.out.println(cost);
    }
}
