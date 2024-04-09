import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static ArrayList<Point> houses;
    static ArrayList<Point> chickens;
    static int[][] distance;

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];

        houses = new ArrayList<>();
        chickens = new ArrayList<>();

        for (int n = 0; n < N; ++n) {
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; ++i) {
                int type = Integer.parseInt(st.nextToken());

                if (type == 1) {
                    houses.add(new Point(n, i));
                } else if (type == 2) {
                    chickens.add(new Point(n, i));
                }
            }
        }

        distance = new int[houses.size()][chickens.size()];
        for (int h = 0; h < houses.size(); ++h) {
            Point house = houses.get(h);
            for (int c = 0; c < chickens.size(); ++c) {
                Point chicken = chickens.get(c);
                distance[h][c] = Math.abs(house.r - chicken.r) + Math.abs(house.c - chicken.c);
            }
        }

        boolean[] selected = new boolean[chickens.size()];
        dfs(0, selected, 0, M);

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }

    public static void dfs(int index, boolean[] selected, int count, final int MAX) {
        if (count > 0) {
            int dis = 0;
            for (int h = 0; h < houses.size(); ++h) {
                int min = Integer.MAX_VALUE;
                for (int i = 0; i < selected.length; ++i) {
                    if (selected[i]) {
                        min = Math.min(min, distance[h][i]);
                    }
                }

                dis += min;
            }

            answer = Math.min(answer, dis);
        }

        if (index == selected.length || count == MAX) {
            return;
        }

        selected[index] = true;
        dfs(index + 1, selected, count + 1, MAX);
        selected[index] = false;
        dfs(index + 1, selected, count, MAX);

    }
}
