import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        int[] rowData = {-1, 1, 0, 0};
        int[] colData = {0, 0, -1, 1};

        StringBuilder builder = new StringBuilder();
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            final int H = Integer.parseInt(st.nextToken());
            final int W = Integer.parseInt(st.nextToken());

            Queue<Point> queue = new LinkedList<>();
            char[][] map = new char[H][W];
            boolean[][] visited = new boolean[H][W];
            HashMap<Character, ArrayList<Point>> blockedMap = new HashMap<>();
            for (int i = 0; i < 26; ++i) {
                char door = (char) ('A' + i);
                blockedMap.put(door, new ArrayList<>());
            }

            boolean[] keys = new boolean[26];
            for (int h = 0; h < H; ++h) {
                String s = br.readLine();
                for (int w = 0; w < W; ++w) {
                    char type = s.charAt(w);
                    map[h][w] = type;
                    if (h == 0 || h == H - 1 || w == 0 || w == W - 1) {
                        visited[h][w] = true;
                        if (type == '.' || type == '$') {
                            queue.offer(new Point(h, w));
                        } else if (type >= 'A' && type <= 'Z') {
                            blockedMap.get(type).add(new Point(h, w));
                        } else if (type >= 'a' && type <= 'z') {
                            keys[type - 'a'] = true;
                            queue.offer(new Point(h, w));
                        }
                    }

                    if (type == '*') {
                        visited[h][w] = true;
                    }
                }
            }

            String s = br.readLine();
            if (!s.equals("0")) {
                for (int i = 0; i < s.length(); ++i) {
                    keys[s.charAt(i) - 'a'] = true;
                }
            }

            for (int i = 0; i < 26; ++i) {
                if (keys[i]) {
                    char door = (char)('A' + i);
                    ArrayList<Point> list = blockedMap.get(door);
                    for (Point p : list) {
                        queue.offer(p);
                    }

                    list.clear();
                }
            }

            int paper = 0;
            while (!queue.isEmpty()) {
                Point current = queue.poll();
                if (map[current.row][current.col] == '$') {
                    ++paper;
                }

                for (int i = 0; i < rowData.length; ++i) {
                    int nextRow = current.row + rowData[i];
                    int nextCol = current.col + colData[i];

                    if (nextRow < 0 || nextRow == H || nextCol < 0 || nextCol == W || visited[nextRow][nextCol]) {
                        continue;
                    }

                    char type = map[nextRow][nextCol];
                    visited[nextRow][nextCol] = true;
                    if (type >= 'A' && type <= 'Z') {
                        if (keys[type - 'A']) {
                            queue.offer(new Point(nextRow, nextCol));
                        } else {
                            blockedMap.get(type).add(new Point(nextRow, nextCol));
                        }
                    } else if (type >= 'a' && type <= 'z') {
                        keys[type - 'a'] = true;
                        ArrayList<Point> list = blockedMap.get((char)(type & ~0x20));
                        for (Point p : list) {
                            queue.offer(p);
                        }

                        list.clear();
                        queue.offer(new Point(nextRow, nextCol));
                    } else {
                        queue.offer(new Point(nextRow, nextCol));
                    }
                }
            }

            builder.append(paper);
            builder.append(System.lineSeparator());
        }

        bw.write(builder.toString());
        br.close();
        bw.close();
    }
}
