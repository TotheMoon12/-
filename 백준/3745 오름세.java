import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder builder = new StringBuilder();
        String input = "";
        while ((input = br.readLine()) != null) {
            final int N = Integer.parseInt(input.trim());

            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[N];
            for (int n = 0; n < N; ++n) {
                arr[n] = Integer.parseInt(st.nextToken());
            }

            int[] uptrend = new int[N];
            uptrend[0] = arr[0];
            int count = 1;

            for (int n = 1; n < N; ++n) {
                int cost = arr[n];

                if (cost > uptrend[count - 1]) {
                    uptrend[count++] = cost;
                } else {
                    int left = 0;
                    int right = count;

                    while (left < right) {
                        int mid = (left + right) / 2;
                        int midCost = uptrend[mid];

                        if (midCost >= cost) {
                            right = mid;
                        } else {
                            left = mid + 1;
                        }
                    }

                    uptrend[right] = cost;
                }
            }

            builder.append(count);
            builder.append(System.lineSeparator());
        }

        bw.write(builder.toString());
        br.close();
        bw.close();
    }
}
