import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int[] button = {300, 60, 10};

        StringBuilder answer = new StringBuilder();
        for (int idx = 0; idx < button.length; ++idx) {
            int buttonPushCount = T / button[idx];
            T -= button[idx] * buttonPushCount;

            answer.append(buttonPushCount);
            answer.append(" ");
        }

        if (T == 0) {
            System.out.print(answer);
        } else {
            System.out.print(-1);
        }
    }
}
