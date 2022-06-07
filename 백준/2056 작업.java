import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] inDegrees = new int[n + 1];
        int[] times = new int[n + 1];
        ArrayList<Integer>[] parents = new ArrayList[n + 1];
        ArrayList<Integer>[] children = new ArrayList[n + 1];

        for (int idx = 1; idx <= n; ++idx) {
            parents[idx] = new ArrayList<>();
            children[idx] = new ArrayList<>();
        }

        for (int idx = 1; idx <= n; ++idx) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            times[idx] = Integer.parseInt(st.nextToken());
            int inDegree = Integer.parseInt(st.nextToken());
            inDegrees[idx] = inDegree;
            for (int count = 0; count < inDegree; ++count) {
                int parent = Integer.parseInt(st.nextToken());
                parents[idx].add(parent);
                children[parent].add(idx);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int idx = 1; idx <= n; ++idx) {
            if (inDegrees[idx] == 0) {
                q.offer(idx);
            }
        }

        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        int minTime = 0;
        for (int count = 0; count < n; ++count) {
            int cur = q.poll();
            if (parents[cur].size() == 0) {
                dp[cur] = times[cur];
            } else {
                int parentTime = 0;
                for (int parent : parents[cur]) {
                    parentTime = Math.max(parentTime, dp[parent]);
                }

                dp[cur] = parentTime + times[cur];
            }

            for (int child : children[cur]) {
                --inDegrees[child];

                if (inDegrees[child] == 0) {
                    q.offer(child);
                }
            }

            minTime = Math.max(minTime, dp[cur]);
        }

        System.out.println(minTime);
    }
}
