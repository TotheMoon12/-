import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        for (int n = 0; n < N; ++n) {
            String s = br.readLine();

            for (int m = 0; m < M; ++m) {
                map[n][m] = s.charAt(m);
            }
        }

        int[][] visited = new int[N][M];
        int answer = 0;
        int number = 0;
        for (int n = 0; n < N; ++n) {
            for (int m = 0; m < M; ++m) {
                if (visited[n][m] == 0) {
                    ++number;
                    int row = n;
                    int col = m;

                    while (visited[row][col] == 0) {
                        visited[row][col] = number;

                        char direction = map[row][col];
                        if (direction == 'U') {
                            --row;
                        } else if (direction == 'D') {
                            ++row;
                        } else if (direction == 'L') {
                            --col;
                        } else {
                            ++col;
                        }
                    }

                    if (visited[row][col] == number) {
                        ++answer;
                    }
                }
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
