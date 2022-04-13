// long을 사용하면 오버플로우가 발생하기 때문에 BigIntegr 클래스를 사용하였는데 
// BigInteger클래스가 문자를 가지는 것으로 구현되어 있다고 해서 구현해본 방식

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static class BigNumber {
        StringBuilder number;

        public BigNumber(String number) {
            this.number = new StringBuilder(number);
        }

        public BigNumber add(BigNumber other) {
            StringBuilder sum = new StringBuilder();
            int numberIndex = this.number.length() - 1;
            int otherIndex = other.number.length() - 1;
            StringBuilder otherNumber = other.number;

            int carry = 0;
            while (numberIndex >= 0 && otherIndex >= 0) {
                int digit1 = this.number.charAt(numberIndex) - '0';
                int digit2 = otherNumber.charAt(otherIndex) - '0';

                int newDigit = digit1 + digit2 + carry;

                carry = newDigit / 10;
                sum.insert(0, newDigit % 10);
                --numberIndex;
                --otherIndex;
            }

            if (numberIndex >= 0 || otherIndex >= 0) {
                while (numberIndex >= 0) {
                    int digit = this.number.charAt(numberIndex) - '0';
                    digit += carry;

                    sum.insert(0, digit % 10);
                    carry = digit / 10;
                    --numberIndex;
                }

                while (otherIndex >= 0) {
                    int digit = otherNumber.charAt(otherIndex) - '0';
                    digit += carry;
                    sum.insert(0, digit % 10);
                    carry = digit / 10;
                    --otherIndex;
                }
            }

            if (carry > 0) {
                sum.insert(0, carry);
            }

            return new BigNumber(sum.toString());
        }

        @Override
        public String toString() {
            return this.number.toString();
        }
    }

    static BigNumber[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        dp = new BigNumber[n + 1][m + 1];
        BigNumber answer = getCombination(n, m);
        System.out.println(answer);
    }
    public static BigNumber getCombination(int n, int m) {
        if (n == m) {
            dp[n][m] = new BigNumber("1");
            return dp[n][m];
        }

        if (m == 1) {
            dp[n][m] = new BigNumber(Integer.toString(n));
            return dp[n][m];
        }

        BigNumber value1;

        if (dp[n - 1][m - 1] != null) {
            value1 = dp[n - 1][m - 1];
        } else {
            value1 = getCombination(n - 1, m - 1);
            dp[n - 1][m - 1] = value1;
        }

        BigNumber value2;
        if (dp[n - 1][m] != null) {
            value2 = dp[n - 1][m];
        } else {
            value2 = getCombination(n - 1, m);
            dp[n - 1][m] = value2;
        }

        return value1.add(value2);
    }
}
