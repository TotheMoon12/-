import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static class Player {
        int white;
        int black;

        public Player(int white, int black) {
            this.white = white;
            this.black = black;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Player> list = new ArrayList<>();

        String input = br.readLine();
        while (input != null) {
            StringTokenizer st = new StringTokenizer(input);
            int white = Integer.parseInt(st.nextToken());
            int black = Integer.parseInt(st.nextToken());

            Player player = new Player(white, black);
            list.add(player);
            input = br.readLine();
        }

        final int TOTAL_PLAYER_NUM = list.size();
        final int MAX_PLAYER_NUM = 15;
        int[][] dp = new int[MAX_PLAYER_NUM + 1][MAX_PLAYER_NUM + 1];


        for (int idx = 0; idx < TOTAL_PLAYER_NUM; ++idx) {
            Player player = list.get(idx);

            for (int white = MAX_PLAYER_NUM; white >= 0; --white) {
                for (int black = MAX_PLAYER_NUM; black >= 0; --black) {
                    if (white + 1 <= MAX_PLAYER_NUM) {
                        dp[white + 1][black] = Math.max(dp[white + 1][black], dp[white][black] + player.white);
                    }

                    if (black + 1 <= MAX_PLAYER_NUM) {
                        dp[white][black + 1] = Math.max(dp[white][black + 1], dp[white][black] + player.black);
                    }
                }
            }
        }

        System.out.print(dp[MAX_PLAYER_NUM][MAX_PLAYER_NUM]);
        br.close();
    }
}
