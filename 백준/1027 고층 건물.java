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
        final int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] buildings = new int[N];
        for (int n = 0; n < N; ++n) {
            buildings[n] = Integer.parseInt(st.nextToken());
        }


        int answer = 0;
        if (N == 1) {
            answer = 0;
        } else if (N == 2) {
            answer = 1;
        } else {
            int[] counts = new int[N];
            for (int n = 0; n < N - 1; ++n) {
                counts[n] += 1;
                if (n + 1 < N) {
                    counts[n + 1] += 1;
                }

                int startX = n;
                int startY = buildings[n];
                int secondX = n + 1;
                int secondY = buildings[n + 1];

                for (int next = n + 2; next < N; ++next) {
                    int thirdX = next;
                    int thirdY = buildings[next];

                    long result = getShoelaceFormula(startX, startY, secondX, secondY, thirdX, thirdY);
                    if (result > 0) {
                        counts[n] += 1;
                        counts[next] += 1;

                        secondX = thirdX;
                        secondY = thirdY;
                    }
                }
            }

            for (int count: counts) {
                answer = Math.max(answer, count);
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }

    private static long getShoelaceFormula(long x1, long y1, long x2, long y2, long x3, long y3) {
        return x1 * y2 + x2 * y3 + x3 * y1 - (x2 * y1 + x3 * y2 + x1 * y3);
    }
}
