import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static class Group {
        int candy;
        int count;

        public Group(int candy, int count) {
            this.candy = candy;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());
        final int K = Integer.parseInt(st.nextToken());

        int[] candy = new int[N + 1];
        int[] parent = new int[N + 1];
        int[] friendCount = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; ++n) {
            int count = Integer.parseInt(st.nextToken());
            candy[n] = count;
            parent[n] = n;
            friendCount[n] = 1;
        }
        
        for (int m = 0; m < M; ++m) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            int parent1 = getParent(parent, n1);
            int parent2 = getParent(parent, n2);

            if (parent1 != parent2) {
                if (parent1 < parent2) {
                    parent[parent2] = parent1;
                    candy[parent1] += candy[parent2];
                    candy[parent2] = 0;
                    friendCount[parent1] += friendCount[parent2];
                    friendCount[parent2] = 0;
                } else {
                    parent[parent1] = parent2;
                    candy[parent2] += candy[parent1];
                    candy[parent1] = 0;
                    friendCount[parent2] += friendCount[parent1];
                    friendCount[parent1] = 0;
                }
            }
        }

        ArrayList<Group> list = new ArrayList<>();
        for (int n = 1; n <= N; ++n) {
            if (candy[n] > 0) {
                list.add(new Group(candy[n], friendCount[n]));
            }
        }

        final int GROUP_COUNT = list.size();
        int[][] dp = new int[GROUP_COUNT][K];
        Group firstGroup = list.get(0);
        if (firstGroup.count < K) {
            dp[0][firstGroup.count] = firstGroup.candy;
        }

        for (int i = 1; i < GROUP_COUNT; ++i) {
            Group group = list.get(i);
            if (group.count < K) {
                dp[i][group.count] = Math.max(dp[i - 1][group.count], group.candy);
            }

            for (int k = 1; k < K; ++k) {
                int count = k + group.count;
                if (count < K && dp[i - 1][k] > 0) {
                    dp[i][count] = Math.max(dp[i - 1][count], dp[i - 1][k] + group.candy);
                }

                dp[i][k] = Math.max(dp[i][k], dp[i - 1][k]);
            }
        }

        int answer = 0;
        for (int candyCount : dp[GROUP_COUNT - 1]) {
            answer = Math.max(answer, candyCount);
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }

    public static int getParent(int[] parent, int n) {
        if (parent[n] == n) {
            return n;
        }

        return getParent(parent, parent[n]);
    }
}
