import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static  void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int bottleCount = n;
        int answer = -1;
        while (true) {

            int emptyBottleCount = 0;
            int temp = bottleCount;
            while (temp > 0) {
                emptyBottleCount += temp % 2;
                temp /= 2;
            }

            if (emptyBottleCount <= k) {
                answer = bottleCount - n;
                break;
            }

            ++bottleCount;
        }

        System.out.println(answer);
    }
}
