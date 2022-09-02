import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int minPackageCost = 1000;
        int minOneCost = 1000;

        for (int m = 0; m < M; ++m) {
            st = new StringTokenizer(br.readLine());

            int packageCost = Integer.parseInt(st.nextToken());
            int oneCost = Integer.parseInt(st.nextToken());

            minPackageCost = Math.min(minPackageCost, packageCost);
            minOneCost = Math.min(minOneCost, oneCost);
        }

        int answer = 0;
        if (minOneCost * 6 < minPackageCost) {
            answer = minOneCost * N;
        } else {
            answer = N / 6 * minPackageCost;

            int remain = N % 6;
            if (remain * minOneCost < minPackageCost) {
                answer += remain * minOneCost;
            } else {
                answer += minPackageCost;
            }
        }

        System.out.print(answer);
    }
}
