import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] tree = new ArrayList[N + 1];
        for (int n = 1; n <= N; ++n) {
            tree[n] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        st.nextToken();
        for (int n = 2; n <= N; ++n) {
            int superior = Integer.parseInt(st.nextToken());
            tree[superior].add(n);
        }

        int[] dp = new int[N + 1];
        for (int m = 0; m < M; ++m) {
            st = new StringTokenizer(br.readLine());
            int subordinate = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            dp[subordinate] += value;
        }

        dfs(tree, 1, dp);
        StringBuilder builder = new StringBuilder();
        for (int n = 1; n < N; ++n) {
            builder.append(dp[n]);
            builder.append(' ');
        }
        builder.append(dp[N]);

        bw.write(builder.toString());
        br.close();
        bw.close();
    }

    static void dfs(ArrayList<Integer>[] tree, int start, int[] dp) {
        for (int next : tree[start]) {
            dp[next] += dp[start];

            if (!tree[next].isEmpty()) {
                dfs(tree, next, dp);
            }
        }
    }
}
1
