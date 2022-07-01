import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static class Pair {
        int one;
        int half;

        public Pair(int one, int half) {
            this.one = one;
            this.half = half;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || !(o instanceof Pair) || this.hashCode() != o.hashCode()) {
                return false;
            }

            Pair other = (Pair) o;
            return this.one == other.one && this.half == other.half;
        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash = hash * 31 + this.one;
            hash = hash * 31 + this.half;

            return hash;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (N != 0) {
            final int TOTAL_DAY = N * 2;

            HashMap<Pair, Long>[] dp = new HashMap[TOTAL_DAY + 1];
            for (int day = 0; day <= TOTAL_DAY; ++day) {
                dp[day] = new HashMap<>();
            }

            dp[0].put(new Pair(N,0), 1l);
            for (int day = 0; day < TOTAL_DAY; ++day) {
                for (Pair pair : dp[day].keySet()) {
                    int nextDay = day + 1;

                    if (pair.one != 0 && pair.half != 0) {
                        Pair next = new Pair(pair.one - 1, pair.half + 1);
                        if (dp[nextDay].containsKey(next)) {
                            dp[nextDay].put(next, dp[nextDay].get(next) + dp[day].get(pair));
                        } else {
                            dp[nextDay].put(next, dp[day].get(pair));
                        }

                        next = new Pair(pair.one, pair.half - 1);
                        if (dp[nextDay].containsKey(next)) {
                            dp[nextDay].put(next, dp[nextDay].get(next) + dp[day].get(pair));
                        } else {
                            dp[nextDay].put(next, dp[day].get(pair));
                        }
                    } else if (pair.one == 0) {
                        Pair next = new Pair(pair.one, pair.half - 1);
                        if (dp[nextDay].containsKey(next)) {
                            dp[nextDay].put(next, dp[nextDay].get(next) + dp[day].get(pair));
                        } else {
                            dp[nextDay].put(next, dp[day].get(pair));
                        }
                    } else {
                        Pair next = new Pair(pair.one - 1, pair.half + 1);
                        if (dp[nextDay].containsKey(next)) {
                            dp[nextDay].put(next, dp[nextDay].get(next) + dp[day].get(pair));
                        } else {
                            dp[nextDay].put(next, dp[day].get(pair));
                        }
                    }
                }
            }

            long answer = 0;
            for (Pair pair : dp[TOTAL_DAY].keySet()) {
                answer += dp[TOTAL_DAY].get(pair);
            }

            sb.append(answer);
            sb.append(System.lineSeparator());

            N = Integer.parseInt(br.readLine());
        }

        System.out.println(sb.toString());
    }
}
