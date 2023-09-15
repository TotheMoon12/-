import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());
        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);

        int sqrt = (int) Math.sqrt(N);
        for (int n = 2; n <= sqrt; ++n) {
            for (int start = n * 2; start <= N; start += n) {
                isPrime[start] = false;
            }
        }

        ArrayList<Integer> primeList = new ArrayList<>();
        for (int n = 2; n <= N; ++n) {
            if (isPrime[n]) {
                primeList.add(n);
            }
        }

        final int SIZE = primeList.size();
        if (SIZE == 0) {
            bw.write("0");
            br.close();
            bw.close();
            return;
        }

        int answer = 0;
        int left = 0;
        int right = 0;
        int sum = 2;
        while (left <= right) {
            if (sum == N) {
                ++answer;
                sum -= primeList.get(left);
                ++left;
            } else if (sum > N) {
                sum -= primeList.get(left);
                ++left;
            } else {
                ++right;
                if (right == SIZE) {
                    break;
                }

                sum += primeList.get(right);
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
