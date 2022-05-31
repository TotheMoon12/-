import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Pair implements Comparable<Pair> {
        int number;
        int weight;

        public Pair(int number, int weight) {
            this.number = number;
            this.weight = weight;
        }

        @Override
        public int compareTo(Pair other) {
            return other.weight - this.weight;
        }
    }

    public static class Edge implements Comparable<Edge>{
        int h1;
        int h2;
        int weight;

        public Edge(int h1, int h2, int weight) {
            this.h1 = h1;
            this.h2 = h2;
            this.weight = weight;
        }


        @Override
        public int compareTo(Edge other) {
            return other.weight - this.weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int houseNum = Integer.parseInt(st.nextToken());
        int totalEdgeCount = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        ArrayList<Pair>[] map = new ArrayList[houseNum + 1];
        for (int idx = 1; idx <= houseNum; ++idx) {
            map[idx] = new ArrayList<>();
        }

        for (int count = 0; count < totalEdgeCount; ++count) {
            st = new StringTokenizer(br.readLine());

            int h1 = Integer.parseInt(st.nextToken());
            int h2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            map[h1].add(new Pair(h2, weight));
            map[h2].add(new Pair(h1, weight));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[houseNum + 1];
        visited[end] = true;
        q.offer(end);

        int count = 1000000;
        while(!q.isEmpty() && !visited[start]) {
            int cur = q.poll();

            for (Pair next : map[cur]) {
                if (!visited[next.number]) {
                    pq.offer(next);
                }
            }

            while(!pq.isEmpty()) {
                Pair next = pq.poll();

                if (!visited[next.number]) {
                    visited[next.number] = true;
                    count = Math.min(count, next.weight);
                    q.offer(next.number);
                    break;
                }
            }
        }

        if (visited[start]) {
            System.out.println(count);
        } else {
            System.out.println(0);
        }
    }
}
