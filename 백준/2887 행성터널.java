// 각각 x,y,z를 오름차순으로 정렬하고 다음 좌표의 행성과만의 간선을 가지면 그것이 최소 간선들이여서 그 간선들만 가지고 크루스칼 알고리즘을
//사용한다.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Pair implements Comparable<Pair> {
        int number;
        int coordinate;

        public Pair(int number, int coordinate) {
            this.number = number;
            this.coordinate = coordinate;
        }

        @Override
        public int compareTo(Pair other) {
            return this.coordinate - other.coordinate;
        }
    }

    public static class Edge implements Comparable<Edge> {
        int number1;
        int number2;
        int cost;

        public Edge(int number1, int number2, int cost) {
            this.number1 = number1;
            this.number2 = number2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return this.cost - other.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] parent = new int[N];

        ArrayList<Pair> xList = new ArrayList<>();
        ArrayList<Pair> yList = new ArrayList<>();
        ArrayList<Pair> zList = new ArrayList<>();

        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            xList.add(new Pair(i, x));
            yList.add(new Pair(i, y));
            zList.add(new Pair(i, z));
            parent[i] = i;
        }

        Collections.sort(xList);
        Collections.sort(yList);
        Collections.sort(zList);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < N - 1; ++i) {
            pq.offer(new Edge(xList.get(i).number, xList.get(i + 1).number, Math.abs(xList.get(i).coordinate - xList.get(i + 1).coordinate)));
            pq.offer(new Edge(yList.get(i).number, yList.get(i + 1).number, Math.abs(yList.get(i).coordinate - yList.get(i + 1).coordinate)));
            pq.offer(new Edge(zList.get(i).number, zList.get(i + 1).number, Math.abs(zList.get(i).coordinate - zList.get(i + 1).coordinate)));
        }

        long cost = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (!find(parent, edge.number1, edge.number2)) {
                cost += edge.cost;
                union(parent, edge.number1, edge.number2);
            }
        }

        System.out.println(cost);
    }

    public static int getParent(int[] parent, int n) {
        if (parent[n] == n) {
            return n;
        }

        return getParent(parent, parent[n]);
    }

    public static boolean find(int[] parent, int n1, int n2) {
        int parent1 = getParent(parent, n1);
        int parent2 = getParent(parent, n2);

        return parent1 == parent2;
    }

    public static void union(int[] parent, int n1, int n2) {
        int parent1 = getParent(parent, n1);
        int parent2 = getParent(parent, n2);

        if (parent1 < parent2) {
            parent[parent2] = parent1;
        } else {
            parent[parent1] = parent2;
        }
    }
}
