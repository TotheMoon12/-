import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int R = Integer.parseInt(st.nextToken());
        final int C = Integer.parseInt(st.nextToken());

        char[][] table = new char[R][C];
        for (int r = 0; r < R; ++r) {
            String s = br.readLine();

            for (int c = 0; c < C; ++c) {
                table[r][c] = s.charAt(c);
            }
        }

        int left = 0;
        int right = R - 1;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            boolean same = false;
            for (int c = 0; c < C; ++c) {
                for (int compareC = c + 1; compareC < C; ++compareC) {
                    int r = mid;
                    while (r < R && table[r][c] == table[r][compareC]) {
                        ++r;
                    }

                    if (r == R) {
                        same = true;
                        break;
                    }
                }

                if (same) {
                    break;
                }
            }

            if (!same) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
