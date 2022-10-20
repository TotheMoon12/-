import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        String T = br.readLine();
        StringBuilder builder = new StringBuilder(T);

        while (builder.length() != S.length()) {
            char lastChar = builder.charAt(builder.length() - 1);
            builder.setLength(builder.length() - 1);

            if (lastChar == 'B') {
                builder = builder.reverse();
            }
        }

        if (builder.toString().equals(S)) {
            System.out.print(1);
        } else {
            System.out.print(0);
        }

        br.close();
    }
}
