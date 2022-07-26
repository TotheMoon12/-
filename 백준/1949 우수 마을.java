import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] personNumber = new int[N + 1];
        int[][] dp = new int[N + 1][2];

        final int SELECT = 0;
        final int NOT_SELECT = 1;
        final int COUNT = 0;
        final int ADJACENT = 1;

        ArrayList<Integer>[] tree = new ArrayList[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; ++n) {
            tree[n] = new ArrayList<>();
            personNumber[n] = Integer.parseInt(st.nextToken());
            dp[n][SELECT] = personNumber[n];
        }

        for (int n = 1; n < N; ++n) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            tree[n1].add(n2);
            tree[n2].add(n1);
        }



        boolean[] isAdjacent = new boolean[N + 1];

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        for (int n = 1; n <= N; ++n) {
            if (tree[n].size() == 1) {
                q.offer(n);
            }
        }

        int last = 0;
        while (!q.isEmpty()) {
            int current = q.poll();
            last = current;

            int notVisitedCount = 0;
            int notVisitedNumber = -1;
            for (int next : tree[current]) {
                if (!visited[next]) {
                    ++notVisitedCount;
                    notVisitedNumber = next;
                }
            }

            if (!visited[current] && (notVisitedCount == 1 || notVisitedCount == 0)) {
                for (int next : tree[current]) {
                    if (next != notVisitedNumber) {
                        dp[current][SELECT] += dp[next][NOT_SELECT];

                        if (isAdjacent[next]) {
                            if (dp[next][SELECT] >= dp[next][NOT_SELECT]) {
                                dp[current][NOT_SELECT] += dp[next][SELECT];
                                isAdjacent[current] = true;
                            } else {
                                dp[current][NOT_SELECT] += dp[next][NOT_SELECT];
                            }

                        } else {
                            dp[current][NOT_SELECT] += dp[next][SELECT];
                            isAdjacent[current] = true;
                        }
                    }
                }

                if (notVisitedCount == 1 || notVisitedCount == 0) {
                    visited[current] = true;
                }

                if (notVisitedCount == 1) {
                    q.offer(notVisitedNumber);
                }
            }
        }

        System.out.println(Math.max(dp[last][SELECT], dp[last][NOT_SELECT]));
    }
}
