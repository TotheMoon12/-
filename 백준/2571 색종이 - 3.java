import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());
        final int SIZE = 100;
        final int PAPER_SIZE = 10;
        int[][] sum = new int[SIZE + 1][SIZE + 1];

        boolean[] xArr = new boolean[SIZE + 1];
        boolean[] yArr = new boolean[SIZE + 1];

        for (int n = 0; n < N; ++n) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int endX = x + PAPER_SIZE;
            int endY = y + PAPER_SIZE;

            ++x;
            ++y;
            xArr[x] = true;
            xArr[endX] = true;
            yArr[y] = true;
            yArr[endY] = true;
            for (int startX = x; startX <= endX; ++startX) {
                for (int startY = y; startY <= endY; ++startY) {
                    sum[startX][startY] = 1;
                }
            }
        }

        for (int x = 1; x <= SIZE; ++x) {
            for (int y = 1; y <= SIZE; ++y) {
                sum[x][y] = sum[x][y] +  sum[x - 1][y] + sum[x][y - 1] - sum[x - 1][y - 1];
            }
        }

        int answer = 100;
        for (int startX = 1; startX < SIZE; ++startX) {
            if (!xArr[startX]) {
                continue;
            }

            for (int startY = 1; startY < SIZE; ++startY) {
                if (!yArr[startY]) {
                    continue;
                }

                for (int endX = startX; endX <= SIZE; ++endX) {
                    if (!xArr[endX]) {
                        continue;
                    }

                    for (int endY = startY; endY <= SIZE; ++endY) {
                        if (!yArr[endY]) {
                            continue;
                        }
                        
                        int result = sum[endX][endY] - sum[endX][startY - 1]
                                - sum[startX - 1][endY] + sum[startX - 1][startY - 1];
                        int expected = (endX - startX + 1) * (endY - startY + 1);

                        if (result == expected) {
                            answer = Math.max(answer, result);
                        }
                    }
                }
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
