import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());

        int[][] rectangles = new int[N][2];
        final int WIDTH = 0;
        final int HEIGHT = 1;
        for (int n = 0; n < N; ++n) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int width = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());

            if (height > width) {
                int temp = height;
                height = width;
                width = temp;
            }

            rectangles[n][WIDTH] = width;
            rectangles[n][HEIGHT] = height;
        }

        Arrays.sort(rectangles, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[WIDTH] == o2[WIDTH]) {
                    return o2[HEIGHT] - o1[HEIGHT];
                }

                return o2[WIDTH] - o1[WIDTH];
            }
        });

        int[] dp = new int[N];
        int answer = 0;
        for (int n = 0; n < N; ++n) {
            dp[n] = 1;

            int count = 0;
            int width = rectangles[n][WIDTH];
            int height = rectangles[n][HEIGHT];
            for (int prev = n - 1; prev >= 0; --prev) {
                if (rectangles[prev][WIDTH] >= width && rectangles[prev][HEIGHT] >= height && dp[prev] > count) {
                    count = dp[prev];
                }
            }

            dp[n] += count;
            answer = Math.max(answer, dp[n]);
        }

        bw.write(Integer.toString(answer));
        br.close();
        bw.close();
    }
}
