import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; ++t) {
            int N = Integer.parseInt(br.readLine());

            HashMap<Integer, Integer> rankMap = new HashMap<>();
            for (int n = 0; n < N; ++n) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int documentRank = Integer.parseInt(st.nextToken());
                int interViewRank = Integer.parseInt(st.nextToken());
                rankMap.put(documentRank, interViewRank);
            }

            int answer = N;
            int lastRank = N;
            for (int documentRank = 1; documentRank <= N; ++documentRank) {
                int interViewRank = rankMap.get(documentRank);

                if (interViewRank <= lastRank) {
                    answer -= (lastRank - interViewRank);
                    lastRank = interViewRank - 1;
                }
            }

            sb.append(answer);
            sb.append(System.lineSeparator());
        }

        System.out.print(sb);
    }
}
