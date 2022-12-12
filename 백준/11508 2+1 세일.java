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
        int[] product = new int[N + 1];
        for (int n = 1; n <= N; ++n) {
            product[n] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(product);
        int answer = 0;
        int count = 0;
        for (int n = N; n >= 1; --n) {
            ++count;
            if (count == 3) {
                count = 0;
            } else {
                answer += product[n];
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
