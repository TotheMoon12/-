import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] time = new int[N];
        for (int n = 0; n < N; ++n) {
            time[n] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(time);

        int answer = time[0];
        int sum = time[0];
        for (int n = 1; n < N; ++n) {
            sum += time[n];
            answer += sum;
        }

        System.out.println(answer);
    }
}
