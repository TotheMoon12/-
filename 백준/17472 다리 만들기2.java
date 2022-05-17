import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Point {
        int islandNumber;
        int row;
        int col;

        public Point(int islandNumber, int row, int col) {
            this.islandNumber = islandNumber;
            this.row = row;
            this.col = col;
        }
    }

    public static class Edge implements Comparable<Edge> {
        int number1;
        int number2;
        int length;

        public Edge(int number1, int number2, int length) {
            this.number1 = number1;
            this.number2 = number2;
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

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int row = 0; row < N; ++row) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; ++col) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] visited = new boolean[N][M];
        ArrayList<ArrayList<Point>> islands = new ArrayList<>();
        int[] rowData = {-1,1,0,0};
        int[] colData = {0,0,-1,1};
        int count = 0;
        for (int row = 0; row < N; ++row) {
            for (int col = 0; col < M; ++col) {
                if (map[row][col] == 1 && !visited[row][col]) {
                    ArrayList<Point> island = new ArrayList<>();
                    island.add(new Point(count, row, col));
                    visited[row][col] = true;

                    Queue<Integer> q = new LinkedList<>();
                    q.offer(row);
                    q.offer(col);

                    while (!q.isEmpty()) {
                        int curRow = q.poll();
                        int curCol = q.poll();

                        for (int index = 0; index < rowData.length; ++index) {
                            int nextRow = curRow + rowData[index];
                            int nextCol = curCol + colData[index];

                            if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) {
                                continue;
                            }

                            if (!visited[nextRow][nextCol] && map[nextRow][nextCol] == 1) {
                                island.add(new Point(count, nextRow, nextCol));
                                visited[nextRow][nextCol] = true;
                                q.offer(nextRow);
                                q.offer(nextCol);
                            }
                        }
                    }

                    islands.add(island);
                    ++count;
                }
            }
        }

        ArrayList<Point>[] adjacentSeaRowPoint = new ArrayList[M];
        for (int index = 0; index < M; ++index) {
            adjacentSeaRowPoint[index] = new ArrayList<>();
        }

        ArrayList<Point>[] adjacentSeaColPoint = new ArrayList[N];
        for (int index = 0; index < N; ++index) {
            adjacentSeaColPoint[index] = new ArrayList<>();
        }

        for (int index = 0; index < islands.size(); ++index) {
            ArrayList<Point> island = islands.get(index);
            for (Point point : island) {
                boolean isRowDuplicate = false;
                boolean isColDuplicate = false;
                for (int subIndex = 0; subIndex < rowData.length; ++subIndex) {
                    int adjacentRow = point.row + rowData[subIndex];
                    int adjacentCol = point.col + colData[subIndex];

                    if (adjacentRow < 0 || adjacentRow >= N || adjacentCol < 0 || adjacentCol >= M) {
                        continue;
                    }

                    if (map[adjacentRow][adjacentCol] == 0) {
                        if (rowData[subIndex] != 0 && !isRowDuplicate) {
                            isRowDuplicate = true;
                            adjacentSeaRowPoint[point.col].add(point);
                        } else if (colData[subIndex] != 0 && !isColDuplicate) {
                            isColDuplicate = true;
                            adjacentSeaColPoint[point.row].add(point);
                        }
                    }
                }
            }
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int index = 0; index < M; ++index) {
            ArrayList<Point> rowDirPoints = adjacentSeaRowPoint[index];
            Collections.sort(rowDirPoints, new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    return o1.row - o2.row;
                }
            });

            for (int subIndex = 0; subIndex < rowDirPoints.size() - 1; ++subIndex) {
                Point p1 = rowDirPoints.get(subIndex);
                Point p2 = rowDirPoints.get(subIndex + 1);

                int length = p2.row - p1.row - 1;
                if (length > 1) {
                    pq.add(new Edge(p1.islandNumber, p2.islandNumber, length));
                }
            }
        }

        for (int index = 0; index < N; ++index) {
            ArrayList<Point> colDirPoints = adjacentSeaColPoint[index];
            Collections.sort(adjacentSeaColPoint[index], new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    return o1.col - o2.col;
                }
            });

            for (int subIndex = 0; subIndex < colDirPoints.size() - 1; ++subIndex) {
                Point p1 = colDirPoints.get(subIndex);
                Point p2 = colDirPoints.get(subIndex + 1);

                int length = p2.col - p1.col - 1;
                if (length > 1) {
                    pq.add(new Edge(p1.islandNumber, p2.islandNumber, length));
                }
            }
        }

        int[] parent = new int[count];
        for (int index = 0; index < count; ++index) {
            parent[index] = index;
        }

        int minLength = 0;
        int edgeCount = 0;
        while(!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (!find(parent, edge.number1, edge.number2)) {
                minLength += edge.length;
                union(parent, edge.number1, edge.number2);
                ++edgeCount;
            }
        }

        if (edgeCount == count - 1) {
            System.out.println(minLength);
        } else {
            System.out.println(-1);
        }
    }

    public static int getParent(int[] parent, int number) {
        if (parent[number] == number) {
            return number;
        }

        return getParent(parent, parent[number]);
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

