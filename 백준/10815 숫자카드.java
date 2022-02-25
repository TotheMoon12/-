import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] cards = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards);

        int M = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; ++i) {
            int value = Integer.parseInt(st.nextToken());

            int left = 0;
            int right = N - 1;
            boolean isExisted = false;
            while (left <= right) {
                int mid = (left + right) / 2;
                int midValue = cards[mid];

                if (midValue == value) {
                    isExisted = true;
                    break;
                } else if (midValue < value) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            if (isExisted) {
                answer.append(1);
            } else {
                answer.append(0);
            }
            answer.append(System.lineSeparator());
        }

        System.out.println(answer.toString());
    }
}
