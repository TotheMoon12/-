import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String N = br.readLine();
        final int LENGTH = N.length();
        int[] numberCount = new int[10];
        int sum = 0;
        for (int idx = 0; idx < LENGTH; ++idx) {
            int digit = N.charAt(idx) - '0';
            ++numberCount[digit];
            sum += digit;
        }

        if (numberCount[0] == 0) {
            System.out.print(-1);
        } else {
            StringBuilder answer = new StringBuilder();
            if (sum % 3 == 0) {
                for (int idx = 9; idx >= 0; --idx) {
                    int count = numberCount[idx];
                    while (count-- > 0) {
                        answer.append(idx);
                    }
                }

                System.out.print(answer);
            } else {
                System.out.print(-1);
            }
        }
    }
}
