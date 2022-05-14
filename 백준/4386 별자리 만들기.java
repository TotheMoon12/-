import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Star {
        double x;
        double y;

        public Star(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class StarEdge {
        int star1;
        int star2;
        double cost;

        public StarEdge(int star1, int star2, double cost) {
            this.star1 = star1;
            this.star2 = star2;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Star[] stars = new Star[n];
        for (int index = 0; index < n; ++index) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            stars[index] = new Star(x, y);
        }

        ArrayList<StarEdge>[] map = new ArrayList[n * n];
        for (int index = 0; index < n; ++index) {
            map[index] = new ArrayList<>();
        }

        for (int index = 0; index < n - 1; ++index) {
            Star star1 = stars[index];
            for (int subIndex = index + 1; subIndex < n; ++subIndex) {
                Star star2 = stars[subIndex];
                double cost = Math.sqrt(Math.pow(star1.x - star2.x, 2) + Math.pow(star1.y - star2.y, 2));

                map[index].add(new StarEdge(index, subIndex, cost));
                map[subIndex].add(new StarEdge(subIndex, index, cost));
            }
        }

        PriorityQueue<StarEdge> pq = new PriorityQueue<>(new Comparator<StarEdge>() {
            @Override
            public int compare(StarEdge o1, StarEdge o2) {
                if (o1.cost < o2.cost) {
                    return -1;
                } else if (o1.cost == o2.cost) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });

        Queue<Integer> q = new LinkedList<>();
        int start = 0;
        q.offer(start);
        boolean[] visited = new boolean[n];
        visited[start] = true;

        double cost = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();

            for (StarEdge edge : map[cur]) {
                if (!visited[edge.star2]) {
                    pq.offer(edge);
                }
            }

            while (!pq.isEmpty()) {
                StarEdge edge = pq.poll();

                if (!visited[edge.star2]) {
                    visited[edge.star2] = true;
                    cost += edge.cost;
                    q.offer(edge.star2);
                    break;
                }
            }
        }

        System.out.printf("%.2f", cost);
    }
}
