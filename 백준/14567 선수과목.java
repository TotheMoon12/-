import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        int[] dp = new int[N + 1];
        int[] in = new int[N + 1];
        ArrayList<Integer>[] nextCourses = new ArrayList[N + 1];
        for (int n = 1; n <= N; ++n) {
            nextCourses[n] = new ArrayList<>();
        }

        for (int m = 0; m < M; ++m) {
            st = new StringTokenizer(br.readLine());

            int prevCourse = Integer.parseInt(st.nextToken());
            int course = Integer.parseInt(st.nextToken());

            nextCourses[prevCourse].add(course);
            ++in[course];
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int n = 1; n <= N; ++n) {
            if (in[n] == 0) {
                dp[n] = 1;
                queue.offer(n);
            }
        }

        while (!queue.isEmpty()) {
            int course = queue.poll();

            for (int nextCourse : nextCourses[course]) {
                --in[nextCourse];

                if (in[nextCourse] == 0) {
                    dp[nextCourse] = dp[course] + 1;
                    queue.offer(nextCourse);
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append(dp[1]);
        for (int n = 2; n <= N; ++n) {
            builder.append(' ');
            builder.append(dp[n]);
        }

        System.out.print(builder);
        br.close();
    }
}
