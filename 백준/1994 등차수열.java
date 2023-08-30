import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int n = 0; n < N; ++n) {
            arr[n] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int[][] dp = new int[N][2];
        final int LENGTH = 0;
        final int DIFF = 1;
        int answer = 1;
        for (int n = 0; n < N; ++n) {
            dp[n][LENGTH] = 1;

            for (int prev = n - 1; prev >= 0; --prev) {
                int diff = arr[n] - arr[prev];

                if (dp[prev][DIFF] == diff) {
                    if (dp[prev][LENGTH] + 1 > dp[n][LENGTH]) {
                        dp[n][LENGTH] = dp[prev][LENGTH] + 1;
                        dp[n][DIFF] = diff;
                    }
                } else {
                    int target = arr[prev] - diff;
                    int length = 2;
                    int end = prev - 1;

                    while (end >= 0) {
                        int left = 0;
                        int right = end;
                        int find = -1;
                        while (left <= right) {
                            int mid = (left + right) / 2;
                            int value = arr[mid];

                            if (value <= target) {
                                find = mid;
                                left = mid + 1;
                            } else {
                                right = mid - 1;
                            }
                        }

                        if (find != -1 && arr[find] == target) {
                            end = find - 1;
                            target -= diff;
                            ++length;
                        } else {
                            break;
                        }
                    }

                    if (length >= dp[n][LENGTH]) {
                        dp[n][LENGTH] = length;
                        dp[n][DIFF] = diff;
                    }
                }

                answer = Math.max(answer, dp[n][LENGTH]);
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
