import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] sum = new int[N + 1];
        for (int n = 1; n <= N; ++n) {
            sum[n] = Integer.parseInt(st.nextToken()) + sum[n - 1];
        }

        long answer = 0;
        HashMap<Integer, Integer> leftMap = new HashMap<>();
        HashMap<Integer, Integer> rightMap = new HashMap<>();
        for (int n = 3; n < N; ++n) {
            int fourthSum = sum[N] - sum[n];
            rightMap.put(fourthSum, rightMap.getOrDefault(fourthSum, 0) + 1);
        }

        for (int halfEnd = 2; halfEnd <= N - 2; ++halfEnd) {
            int halfSum = sum[N] - sum[halfEnd];
            int firstSum = sum[halfEnd - 1];
            leftMap.put(firstSum, leftMap.getOrDefault(firstSum, 0) + 1);

            if (halfSum * 2 == sum[N] && halfSum % 2 == 0) {
                int target = halfSum / 2;
                long count1 = leftMap.getOrDefault(target, 0);
                long count2 = rightMap.getOrDefault(target, 0);

                answer += count1 * count2;
            }

            int fourthSum = sum[N] - sum[halfEnd + 1];
            rightMap.put(fourthSum, rightMap.get(fourthSum) - 1);
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
