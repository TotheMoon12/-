import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int UP = 0;
    static int DOWN = 1;

    public static class Pair {
        int number;
        int type;

        public Pair(int number, int type) {
            this.number = number;
            this.type = type;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int buildingNum = Integer.parseInt(st.nextToken());
        int edgeCount = Integer.parseInt(st.nextToken());


        ArrayList<Pair>[] map = new ArrayList[buildingNum + 1];
        for (int idx = 0; idx <= buildingNum; ++idx) {
            map[idx] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int next = Integer.parseInt(st.nextToken());
        int startType = Integer.parseInt(st.nextToken());
        map[start].add(new Pair(next, startType));
        map[next].add(new Pair(start, startType));

        for (int count = 0; count < edgeCount; ++count) {
            st = new StringTokenizer(br.readLine());

            int number1 = Integer.parseInt(st.nextToken());
            int number2 = Integer.parseInt(st.nextToken());
            int type = Integer.parseInt(st.nextToken());

            map[number1].add(new Pair(number2, type));
            map[number2].add(new Pair(number1, type));
        }

        int best = prim(map, DOWN, buildingNum);
        int worst = prim(map, UP, buildingNum);

        System.out.println(worst - best);
    }

    public static int prim(ArrayList<Pair>[] map, final int priorityType, final int buildingNum) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.type == priorityType) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        final int start = 0;
        boolean[] visited = new boolean[buildingNum + 1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;

        int fatigue = 0;
        while(!q.isEmpty()) {
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
                    q.offer(next.number);

                    if (next.type == UP) {
                        ++fatigue;
                    }

                    break;
                }
            }
        }

        return fatigue * fatigue;
    }
}

