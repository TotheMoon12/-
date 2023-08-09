import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int T = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        for (int t = 0; t < T; ++t) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            final int K = Integer.parseInt(st.nextToken());
            final int N = Integer.parseInt(st.nextToken());

            final int CLASS_NUM = 4;
            int[][] weightArr = new int[CLASS_NUM][N];

            for (int i = 0; i < CLASS_NUM; ++i) {
                st = new StringTokenizer(br.readLine());

                for (int n = 0; n < N; ++n) {
                    weightArr[i][n] = Integer.parseInt(st.nextToken());
                }
            }

            int[] sumArr = new int[N * N];
            int idx = 0;
            for (int n1 = 0; n1 < N; ++n1) {
                int weight1 = weightArr[0][n1];
                for (int n2 = 0; n2 < N; ++n2) {

                    sumArr[idx++] = weight1 + weightArr[1][n2];
                }
            }

            Arrays.sort(sumArr);

            int answer = 0;
            int minError = Integer.MAX_VALUE;
            for (int n1 = 0; n1 < N; ++n1) {
                int weight1 = weightArr[2][n1];
                for (int n2 = 0; n2 < N; ++n2) {
                    int sum = weight1 + weightArr[3][n2];

                    int left = 0;
                    int right = N * N - 1;

                    while (left <= right) {
                        int mid = (left + right) / 2;

                        int result = sum + sumArr[mid];
                        int error = Math.abs(result - K);
                        if (error < minError) {
                            answer = result;
                            minError = error;
                        } else if (error == minError) {
                            answer = Math.min(answer, result);
                        }

                        if (result < K) {
                            left = mid + 1;
                        } else if (result > K) {
                            right = mid - 1;
                        } else {
                            break;
                        }
                    }
                }
            }

            builder.append(answer);
            builder.append(System.lineSeparator());
        }

        bw.write(builder.toString());
        br.close();
        bw.close();
    }
}
