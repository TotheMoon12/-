import java.io.BufferedReader;
import java.io.InputStreamReader;
// bfs로 시작지점과 키들 사이의 간선을 얻고 프림알고리즘을 통하여 최소 이동횟수를 구한다

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int WALL = 1;
    static final int START = 2;
    static final int KEY = 3;

    public static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || !(obj instanceof Point) || this.hashCode() != obj.hashCode()) {
                return false;
            }

            Point other = (Point) obj;
            return this.row == other.row && this.col == other.col;
        }

        @Override
        public int hashCode() {
            int hash = 31;
            hash = hash ^ 17 + this.row;
            hash = hash ^ 17 + this.col;

            return hash;
        }
    }

    public static class Data implements Comparable<Data> {
        int row;
        int col;
        int length;

        public Data(int row, int col, int length) {
            this.row = row;
            this.col = col;
            this.length = length;
        }


        @Override
        public int compareTo(Data other) {
            return this.length - other.length;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


        int[][] maze = new int[n][n];
        Point start = null;
        ArrayList<Point> dataList = new ArrayList<>();
        HashMap<Point, ArrayList<Data>> map = new HashMap<>();
        for (int row = 0; row < n; ++row) {
            String s = br.readLine();

            for (int col = 0; col < n; ++col) {
                char type = s.charAt(col);
                if (type == 'S') {
                    maze[row][col] = START;
                    start = new Point(row, col);
                    dataList.add(start);
                } else if (type == 'K') {
                    maze[row][col] = KEY;
                    Point key = new Point(row, col);
                    dataList.add(key);
                } else {
                    maze[row][col] = type - '0';
                }
            }
        }

        for (int idx = 0; idx < dataList.size(); ++idx) {
            makeMap(maze, dataList.get(idx), map);
        }

        boolean[][] visited = new boolean[n][n];
        Queue<Point> q = new LinkedList();
        q.offer(start);
        visited[start.row][start.col] = true;

        int move = 0;
        int count = 0;
        PriorityQueue<Data> pq = new PriorityQueue<>();
        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (Data next : map.get(cur)) {
                if (!visited[next.row][next.col]) {
                    pq.offer(next);
                }
            }

            while (!pq.isEmpty()) {
                Data next = pq.poll();

                if (!visited[next.row][next.col]) {
                    visited[next.row][next.col] = true;
                    move += next.length;
                    ++count;
                    q.offer(new Point(next.row, next.col));
                    break;
                }
            }
        }

        if (count == m) {
            System.out.println(move);
        } else {
            System.out.println(-1);
        }
    }

    public static void makeMap(int[][] maze, Point start, HashMap<Point, ArrayList<Data>> map) {
        int n = maze.length;
        int[] rowData = {-1, 1, 0, 0};
        int[] colData = {0, 0, -1, 1};

        ArrayList<Data> list = new ArrayList<>();
        Queue<Data> q = new LinkedList<>();
        Data dataStart = new Data(start.row, start.col, 0);
        q.offer(dataStart);
        boolean[][] visited = new boolean[n][n];
        visited[start.row][start.col] = true;

        while (!q.isEmpty()) {
            Data data = q.poll();

            for (int idx = 0; idx < rowData.length; ++idx) {
                int nextRow = data.row + rowData[idx];
                int nextCol = data.col + colData[idx];

                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) {
                    continue;
                }

                if (!visited[nextRow][nextCol] && maze[nextRow][nextCol] != WALL) {
                    visited[nextRow][nextCol] = true;
                    Data next = new Data(nextRow, nextCol, data.length + 1);
                    if ((nextRow != start.row || nextCol != start.col) && maze[nextRow][nextCol] == KEY) {
                        list.add(next);
                    }

                    q.offer(next);
                }
            }
        }

        map.put(start, list);
    }
}
