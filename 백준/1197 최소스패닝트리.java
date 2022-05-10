import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Pair {
         int number;
         int value;

         public Pair(int number, int value) {
             this.number = number;
             this.value = value;
         }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        ArrayList<Pair>[] map = new ArrayList[V + 1];
        for (int index = 1; index <= V; ++index) {
            map[index] = new ArrayList<>();
        }

        for (int index = 0; index < E; ++index) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            map[v1].add(new Pair(v2, value));
            map[v2].add(new Pair(v1, value));
        }
    }

    public void kruskal(ArrayList<Pair>[] map) {

    }

    public void prim(ArrayList<Pair>[] map, int V) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.value - o2.value;
            }
        });

        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        boolean[] visited = new boolean[V + 1];
        visited[1] = true;
        int cost = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Pair next : map[cur]) {
                if (!visited[next.number]) {
                    pq.offer(next);
                }
            }

            while(!pq.isEmpty()) {
                Pair next = pq.poll();

                if (!visited[next.number]) {
                    cost += next.value;
                    q.offer(next.number);
                    visited[next.number] = true;
                    break;
                }
            }
        }

        System.out.println(cost);
    }
}
