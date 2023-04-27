import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final int X = 0;
    static final int Y = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());
        final int SIZE = 1000001;
        int[][] markArr = new int[2][SIZE];
        final int MAX = 500000;

        StringTokenizer st = new StringTokenizer(br.readLine());
        int startX = Integer.parseInt(st.nextToken()) + MAX;
        int startY = Integer.parseInt(st.nextToken()) + MAX;
        int prevX = startX;
        int prevY = startY;
        for (int n = 1; n < N; ++n) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) + MAX;
            int y = Integer.parseInt(st.nextToken()) + MAX;

            mark(markArr, prevX, prevY, x, y);
            prevX = x;
            prevY = y;
        }
        mark(markArr, prevX, prevY, startX, startY);

        int answer = Math.max(markArr[X][0], markArr[Y][0]);
        for (int point = 1; point < SIZE; ++point) {
            int prev = point - 1;
            markArr[X][point] += markArr[X][prev];
            markArr[Y][point] += markArr[Y][prev];

            answer = Math.max(answer, markArr[X][point]);
            answer = Math.max(answer, markArr[Y][point]);
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }

    private static void mark(int[][] markArr, int prevX, int prevY, int x, int y) {
        // 수직
        if (x == prevX) {
            if (prevY < y) { // 위
                ++markArr[Y][prevY];
                --markArr[Y][y];
            } else { // 아래
                --markArr[Y][prevY];
                ++markArr[Y][y];
            }
        } else { // 수평
            if (prevX < x) { // 오른쪽
                ++markArr[X][prevX];
                --markArr[X][x];
            } else { // 왼쪽
                --markArr[X][prevX];
                ++markArr[X][x];
            }
        }
    }
}
