import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int SHORTCUT_COUNT = Integer.parseInt(st.nextToken());
        final int DISTANCE = Integer.parseInt(st.nextToken());

        HashMap<Integer, HashMap<Integer, Integer>> shortcutMap = new HashMap<>();
        for (int count = 0; count < SHORTCUT_COUNT; ++count) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            if (shortcutMap.containsKey(end)) {
                HashMap<Integer, Integer> startMap = shortcutMap.get(end);
                if (startMap.containsKey(start)) {
                    startMap.put(start, Math.min(startMap.get(start), distance));
                } else {
                    startMap.put(start, distance);
                }
            } else {
                HashMap<Integer, Integer> startMap = new HashMap<>();
                startMap.put(start, distance);
                shortcutMap.put(end, startMap);
            }
        }

        int[] dp = new int[DISTANCE + 1];
        for (int end = 1; end <= DISTANCE; ++end) {
            int min = dp[end - 1] + 1;

            if (shortcutMap.containsKey(end)) {
                HashMap<Integer, Integer> startMap = shortcutMap.get(end);
                for (int start : startMap.keySet()) {
                    min = Math.min(min, dp[start] + startMap.get(start));
                }
            }

            dp[end] = min;
        }

        System.out.print(dp[DISTANCE]);
    }
}
