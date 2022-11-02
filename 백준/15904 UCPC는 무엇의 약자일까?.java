import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        final int LENGTH = s.length();
        char[] abbreviation = {'U', 'C', 'P', 'C'};

        int abbreviationIndex = 0;
        for (int idx = 0; idx < LENGTH; ++idx) {
            if (s.charAt(idx) == abbreviation[abbreviationIndex]) {
                ++abbreviationIndex;

                if (abbreviationIndex == abbreviation.length) {
                    break;
                }
            }
        }

        if (abbreviationIndex == abbreviation.length) {
            System.out.print("I love UCPC");
        } else {
            System.out.print("I hate UCPC");
        }
        br.close();
    }
}
