import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] weights = new int[N];
        for (int n = 0; n < N; ++n) {
            weights[n] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(weights);

        int max = 0;
        boolean pass = true;
        for (int n = 0; n < N; ++n) {
            int weight = weights[n];

            if (weight - max <= 1) {
                max = max + weight;
            } else {
                ++max;
                pass = false;
                break;
            }
        }

        if (pass) {
            ++max;
        }
        System.out.print(max);
    }
}

