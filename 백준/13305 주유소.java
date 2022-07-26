import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer stDistance = new StringTokenizer(br.readLine());
        StringTokenizer stCost = new StringTokenizer(br.readLine());

        int[] distances = new int[N - 1];
        int[] costsByCity = new int[N];
        for (int idx = 0; idx < N - 1; ++idx) {
            distances[idx] = Integer.parseInt(stDistance.nextToken());
            costsByCity[idx] = Integer.parseInt(stCost.nextToken());
        }

        int cost = costsByCity[0];
        long answer = 0;

        for (int n = 1; n < N; ++n) {
            answer += (long)(distances[n - 1]) * cost;
            if (costsByCity[n] < cost) {
                cost = costsByCity[n];
            }
        }

        System.out.print(answer);
    }
}
