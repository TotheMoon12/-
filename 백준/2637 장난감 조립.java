import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        boolean[] isAssemble = new boolean[N + 1];
        int[][] dp = new int[N + 1][N + 1];
        ArrayList<Integer>[] childList = new ArrayList[N + 1];
        HashSet<Integer>[] parentSet = new HashSet[N + 1];
        for (int n = 1; n <= N; ++n) {
            childList[n] = new ArrayList<>();
            parentSet[n] = new HashSet<>();
        }

        for (int m = 0; m < M; ++m) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int child = Integer.parseInt(st.nextToken());
            int parent = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());

            childList[parent].add(child);
            parentSet[child].add(parent);

            dp[child][parent] = count;
            isAssemble[child] = true;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int n = 1; n < N; ++n) {
            if (parentSet[n].isEmpty()) {
                queue.add(n);
                dp[n][n] = 1;
            }
        }

        while (!queue.isEmpty()) {
            int parent = queue.poll();

            for (int child : childList[parent]) {
                parentSet[child].remove(parent);

                int count = dp[child][parent];
                dp[child][parent] = 0;
                for (int n = 1; n < N; ++n) {
                    dp[child][n] += dp[parent][n] * count;
                }

                if (parentSet[child].isEmpty()) {
                    queue.offer(child);
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int n = 1; n < N; ++n) {
            if (!isAssemble[n]) {
                answer.append(n);
                answer.append(' ');
                answer.append(dp[N][n]);
                answer.append(System.lineSeparator());
            }
        }

        System.out.print(answer);
        br.close();
    }
}
