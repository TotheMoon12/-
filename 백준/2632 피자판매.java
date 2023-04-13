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

        final int SIZE = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int M = Integer.parseInt(st.nextToken());
        final int N = Integer.parseInt(st.nextToken());

        int[] pizzaA = new int[2 * M];
        int total = 0;
        for (int m = 0; m < M; ++m) {
            pizzaA[m] = Integer.parseInt(br.readLine());
            pizzaA[m + M] = pizzaA[m];
            total += pizzaA[m];
        }

        HashMap<Integer, Integer> aSumMap = new HashMap<>();
        aSumMap.put(total, 1);
        aSumMap.put(0, 1);
        for (int start = 0; start < M; ++start) {
            int end = start + M - 2;
            int sum = 0;
            for (int idx = start; idx <= end; ++idx) {
                sum += pizzaA[idx];
                aSumMap.put(sum, aSumMap.getOrDefault(sum, 0) + 1);
            }
        }

        int[] pizzaB = new int[2 * N];
        int pizzaBTotal = 0;
        for (int n = 0; n < N; ++n) {
            pizzaB[n] = Integer.parseInt(br.readLine());
            pizzaB[n + N] = pizzaB[n];
            pizzaBTotal += pizzaB[n];
        }

        int answer = 0;
        for (int start = 0; start < N; ++start) {
            int end = start + N - 2;
            int sum = 0;
            for (int idx = start; idx <= end; ++idx) {
                sum += pizzaB[idx];
                int remain = SIZE - sum;

                answer += aSumMap.getOrDefault(remain, 0);
            }
        }

        answer += aSumMap.getOrDefault(SIZE - pizzaBTotal, 0);
        answer += aSumMap.getOrDefault(SIZE, 0); // a피자로만 만든 경우

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
