import java.util.StringTokenizer;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        int temp = n;
        while (true) {
            int remain = temp % k;

             sb.insert(0, remain);

            if (temp / k == 0) {
                break;
            }

            temp /= k;
        }

        String strNumber = sb.toString();
        StringTokenizer st = new StringTokenizer(strNumber, "0");

        while (st.hasMoreElements()) {
            String sNumber = st.nextToken();
            long number = Long.parseLong(sNumber);

            if (number == 1) {
                continue;
            }

            boolean isPrime = true;
            for (long i = 2; i <= Math.sqrt(number); ++i) {
                if (number % i == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                ++answer;
            }
        }

        return answer;
    }
}
