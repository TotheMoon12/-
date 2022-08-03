import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        final int LENGTH = s.length();
        int[] partCount = new int[2];
        ++partCount[s.charAt(0) - '0'];
        for (int idx = 1; idx < LENGTH; ++idx) {
            if (s.charAt(idx) != s.charAt(idx - 1)) {
                ++partCount[s.charAt(idx) - '0'];
            }
        }

        System.out.println(partCount[0] < partCount[1] ? partCount[0] : partCount[1]);
    }
}
