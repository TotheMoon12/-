import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final String MAGIC = br.readLine();
        final String DEVIL_BRIDGE = br.readLine();
        final String ANGEL_BRIDGE = br.readLine();

        final int BRIDGE_LENGTH = DEVIL_BRIDGE.length();
        final int MAGIC_LENGTH = MAGIC.length();
        final int KIND = 2; // DEVIL OR ANGEL
        final int BUFFER_SIZE = 2;
        int[][][] dp = new int[BUFFER_SIZE][KIND][MAGIC_LENGTH + 1];

        final int DEVIL = 0;
        final int ANGEL = 1;

        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        map.put('R', new ArrayList<>());
        map.put('I', new ArrayList<>());
        map.put('N', new ArrayList<>());
        map.put('G', new ArrayList<>());
        map.put('S', new ArrayList<>());

        for (int idx = 0; idx < MAGIC_LENGTH; ++idx) {
            char c = MAGIC.charAt(idx);
            map.get(c).add(idx + 1);
        }

        final char FIRST_CHAR = MAGIC.charAt(0);
        int current = 0;
        if (DEVIL_BRIDGE.charAt(0) == FIRST_CHAR) {
            dp[current][DEVIL][1] = 1;
        }

        if (ANGEL_BRIDGE.charAt(0) == FIRST_CHAR) {
            dp[current][ANGEL][1] = 1;
        }

        for (int idx = 1; idx < BRIDGE_LENGTH; ++idx) {
            char devilChar = DEVIL_BRIDGE.charAt(idx);
            char angelChar = ANGEL_BRIDGE.charAt(idx);

            current = (current + 1) % BUFFER_SIZE;

            final int PREV = (current + 1) % BUFFER_SIZE;
            for (int length = 1; length <= MAGIC_LENGTH; ++length) {
                dp[current][DEVIL][length] = dp[PREV][DEVIL][length];
                dp[current][ANGEL][length] = dp[PREV][ANGEL][length];
            }

            for (int length : map.get(devilChar)) {
                dp[current][DEVIL][length] += dp[PREV][ANGEL][length - 1];
            }

            for (int length : map.get(angelChar)) {
                dp[current][ANGEL][length] += dp[PREV][DEVIL][length - 1];
            }

            if (devilChar == FIRST_CHAR) {
                dp[current][DEVIL][1] += 1;
            }

            if (angelChar == FIRST_CHAR) {
                dp[current][ANGEL][1] += 1;
            }
        }

        int answer = dp[current][DEVIL][MAGIC_LENGTH] + dp[current][ANGEL][MAGIC_LENGTH];
        System.out.print(answer);
    }
}
