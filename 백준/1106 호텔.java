import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int targetNum = Integer.parseInt(st.nextToken());
        int hotelNum = Integer.parseInt(st.nextToken());

        final int COST = 0;
        final int PERSON_NUM = 1;
        int[][] hotelsInfo = new int[hotelNum + 1][2];
        for (int i = 1; i <= hotelNum; ++i) {
            st = new StringTokenizer(br.readLine());
            hotelsInfo[i][COST] = Integer.parseInt(st.nextToken());
            hotelsInfo[i][PERSON_NUM] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[targetNum + 1];
        for (int num  = 1; num <= targetNum; ++num) {
            int min = Integer.MAX_VALUE;
            int personByMin = 0;

            for (int j = 1; j <= hotelNum; ++j) {
                int cost = hotelsInfo[j][COST];
                int personNum = hotelsInfo[j][PERSON_NUM];

                if (num <= personNum) {
                    if (cost < min) {
                        min = cost;
                    }
                } else {
                    int newCost = dp[num - personNum] + cost;
                    if (newCost < min) {
                        min = newCost;
                    }
                }
            }

            dp[num] = min;
        }

        System.out.println(dp[targetNum]);
    }
}
