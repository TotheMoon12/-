import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        final int LENGTH = s.length();
        StringBuilder number = new StringBuilder();
        int answer = 0;
        int idx = 0;
        boolean isMinus = false;
        for (; idx < LENGTH; ++idx) {
            char c = s.charAt(idx);

            if (c == '+') {
                answer += Integer.parseInt(number.toString());
                number.setLength(0);
            } else if (c == '-') {
                ++idx;
                isMinus = true;
                answer += Integer.parseInt(number.toString());
                number.setLength(0);
                break;
            } else {
                number.append(c);
            }
        }

        for (; idx < LENGTH; ++idx) {
            char c = s.charAt(idx);
            if (c == '+' || c == '-') {
                answer -= Integer.parseInt(number.toString());
                number.setLength(0);
            } else {
                number.append(c);
            }
        }

        if (isMinus) {
            answer -= Integer.parseInt(number.toString());
        } else {
            answer += Integer.parseInt(number.toString());
        }

        System.out.println(answer);
    }
}
