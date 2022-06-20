import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int mod = 1000000000;

        HashMap<Integer, Integer>[][] dp = new HashMap[N][10];
        for (int idx = 0; idx < N; ++idx) {
            for (int number = 0; number < 10; ++number) {
                dp[idx][number] = new HashMap<>();
            }
        }

        for (int idx = 1; idx < 10; ++idx) {
            int bitflag = (int) Math.pow(2, idx);
            dp[0][idx].put(bitflag, 1);
        }

        for (int idx = 0; idx < N - 1; ++idx) {
            for (int last = 0; last < 10; ++last) {
                HashMap<Integer, Integer> current = dp[idx][last];
                for (int bitflag : current.keySet()) {
                    int count = current.get(bitflag);

                    if (last == 0) {
                        bitflag |= (1 << 1);
                        HashMap<Integer, Integer> next = dp[idx + 1][1];
                        if (next.containsKey(bitflag)) {
                            count = (count + next.get(bitflag)) % mod;
                            next.put(bitflag, count);
                        } else {
                            next.put(bitflag, count);
                        }
                    } else if (last == 9) {
                        bitflag |= (1 << 8);
                        HashMap<Integer, Integer> prev = dp[idx + 1][8];
                        if (prev.containsKey(bitflag)) {
                            count = (count + prev.get(bitflag)) % mod;
                            prev.put(bitflag, count);
                        } else {
                            prev.put(bitflag, count);
                        }
                    } else {
                        int temp = bitflag;
                        temp |= (1 << (last + 1));
                        HashMap<Integer, Integer> next = dp[idx + 1][last + 1];
                        int tempCount = count;

                        if (next.containsKey(temp)) {
                            tempCount = (count + next.get(temp)) % mod;
                            next.put(temp, tempCount);
                        } else {
                            next.put(temp, tempCount);
                        }

                        temp = bitflag | (1 << (last - 1));
                        HashMap<Integer, Integer> prev = dp[idx + 1][last - 1];

                        tempCount = count;
                        if (prev.containsKey(temp)) {
                            tempCount = (count + prev.get(temp)) % mod;
                            prev.put(temp, tempCount);
                        } else {
                            prev.put(temp, tempCount);
                        }
                    }
                }
            }
        }

        int total = 0;
        int bit = (int) (Math.pow(2, 10) - 1);
        for (int idx = 0; idx < 10; ++idx) {
            if (dp[N - 1][idx].containsKey(bit)) {
                total = (total + dp[N - 1][idx].get(bit)) % mod;
            }
        }
        
        System.out.println(total);
    }
}
