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

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int M = Integer.parseInt(st.nextToken());
        final int N = Integer.parseInt(st.nextToken());
        final int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] guns = new int[M];
        for (int m = 0; m < M; ++m) {
            guns[m] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(guns);

        int answer = 0;
        for (int n = 0; n < N; ++n) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int left = 0;
            int right = guns.length - 1;

            while (left <= right) {
                int mid = (left + right) / 2;
                int distance = Math.abs(x - guns[mid]) + y;

                if (distance <= L) {
                    ++answer;
                    break;
                } else {
                    if (guns[mid] > x) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
