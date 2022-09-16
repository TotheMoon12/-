import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] values = new int[N + 1];
        ArrayList<Integer>[] tree = new ArrayList[N + 1];
        ArrayList<Integer>[][] list = new ArrayList[N + 1][2];
        int[][] dp = new int[N + 1][2];
        final int YES = 0;
        final int NO = 1;

        for (int n = 1; n <= N; ++n) {
            tree[n] = new ArrayList<>();

            list[n][0] = new ArrayList<>();
            list[n][1] = new ArrayList<>();
            list[n][YES].add(n);

            values[n] = Integer.parseInt(st.nextToken());
            dp[n][YES] = values[n];
        }

        for (int n = 1; n < N; ++n) {
            st = new StringTokenizer(br.readLine());

            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            tree[n1].add(n2);
            tree[n2].add(n1);
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        for (int n = 1; n <= N; ++n) {
            if (tree[n].size() == 1) {
                queue.add(n);
            }
        }

        ArrayList<Integer> answer = new ArrayList<>();
        int max = 0;

        if (queue.isEmpty()) {
            max = values[1];
            answer.add(1);
        } else {
            while (!queue.isEmpty()) {
                int node = queue.poll();

                if (visited[node]) {
                    continue;
                }

                int notVisitedAdjacentCount = 0;
                int notVisitedAdjacent = -1;
                for (int adjacent : tree[node]) {
                    if (!visited[adjacent]) {
                        ++notVisitedAdjacentCount;
                        notVisitedAdjacent = adjacent;
                    }
                }

                if (notVisitedAdjacentCount == 1) {
                    visited[node] = true;

                    for (int element : list[node][NO]) {
                        list[notVisitedAdjacent][YES].add(element);
                    }
                    dp[notVisitedAdjacent][YES] += dp[node][NO];

                    if (dp[node][YES] > dp[node][NO]) {
                        for (int element : list[node][YES]) {
                            list[notVisitedAdjacent][NO].add(element);
                        }

                        dp[notVisitedAdjacent][NO] += dp[node][YES];
                    } else {
                        for (int element : list[node][NO]) {
                            list[notVisitedAdjacent][NO].add(element);
                        }

                        dp[notVisitedAdjacent][NO] += dp[node][NO];
                    }

                    queue.offer(notVisitedAdjacent);
                } else if (notVisitedAdjacentCount == 0) {
                    if (dp[node][YES] > dp[node][NO]) {
                        answer = list[node][YES];
                        max = dp[node][YES];
                    } else {
                        max = dp[node][NO];
                        answer = list[node][NO];
                    }

                    Collections.sort(answer);
                    break;
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append(max);
        builder.append(System.lineSeparator());

        for (int node : answer) {
            builder.append(node);
            builder.append(' ');
        }

        System.out.print(builder);
    }
}
