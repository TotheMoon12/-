// 주어지는 간선 중 두 학교의 성별이 다른 경우에만 도로를 추가한 후
// 프림 알고리즘을 통하여 최소 소패닝 트리를 구하고
// 간선의 개수가 학교 수 보다 1작은 경우에만 길이를 출력하고 아닌 경우에는
// 끊어진 경우로 -1을 출력

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static class Pair {
        int number;
        int length;

        public Pair(int number, int length) {
            this.number = number;
            this.length = length;
        }
    }

    public static class Edge implements Comparable<Edge> {
        int school1;
        int school2;
        int length;

        public Edge(int school1, int school2, int length) {
            this.school1 = school1;
            this.school2 = school2;
            this.length = length;
        }

        @Override
        public int compareTo(Edge other) {
            return this.length - other.length;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int schoolNum = Integer.parseInt(st.nextToken());
        int edgeCount = Integer.parseInt(st.nextToken());

        char[] schoolInfo = new char[schoolNum + 1];
        final int MAN = 0;
        final int WOMAN = 1;

        ArrayList<Pair>[] map = new ArrayList[schoolNum + 1];
        st = new StringTokenizer(br.readLine());
        for (int idx = 1; idx <= schoolNum; ++idx) {
            schoolInfo[idx] = st.nextToken().charAt(0);
            map[idx] = new ArrayList<>();
        }

        for (int idx = 1; idx <= edgeCount; ++idx) {
            st = new StringTokenizer(br.readLine());
            int school1 = Integer.parseInt(st.nextToken());
            int school2 = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());

            if (schoolInfo[school1] != schoolInfo[school2]) {
                map[school1].add(new Pair(school2, length));
                map[school2].add(new Pair(school1, length));
            }
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        Queue<Integer> q = new LinkedList();
        boolean[] visited = new boolean[schoolNum + 1];
        int start = 1;
        visited[start] = true;

        q.offer(start);
        int connectedEdgeCount = 0;
        int totalLength = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Pair next : map[cur]) {
                if (!visited[next.number]) {
                    pq.offer(new Edge(cur, next.number, next.length));
                }
            }

            while(!pq.isEmpty()) {
                Edge edge = pq.poll();

                if (!visited[edge.school2]) {
                    visited[edge.school2] = true;
                    ++connectedEdgeCount;
                    totalLength += edge.length;
                    q.offer(edge.school2);
                    break;
                }
            }
        }

        if (connectedEdgeCount == schoolNum - 1) {
            System.out.println(totalLength);
        } else {
            System.out.println(-1);
        }
    }
}
