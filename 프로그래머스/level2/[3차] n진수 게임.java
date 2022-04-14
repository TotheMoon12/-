class Solution {
    public String solution(int n, int t, int m, int p) {
        int turn = 0;
        int count = 0;
        int number = 1;

        StringBuilder answer = new StringBuilder();

        --p;
        if (turn == p) {
            answer.append(0);
            ++count;
        }
        
        turn = (turn + 1) % m;

        while (count < t) {
            StringBuilder systemNumber = new StringBuilder();
            int temp = number;
            while (temp > 0) {
                int quotient = temp / n;
                int remain = temp % n;

                temp = quotient;
                if (remain == 10) {
                    systemNumber.append('A');
                } else if (remain == 11) {
                    systemNumber.append('B');
                } else if (remain == 12) {
                    systemNumber.append('C');
                } else if (remain == 13) {
                    systemNumber.append('D');
                } else if (remain == 14) {
                    systemNumber.append('E');
                } else if (remain == 15) {
                    systemNumber.append('F');
                } else {
                    systemNumber.append(remain);
                }
            }

            for (int i = systemNumber.length() - 1; i >= 0; --i) {
                if (turn == p) {
                    answer.append(systemNumber.charAt(i));
                    ++count;

                    if (count == t) {
                        return answer.toString();
                    }
                }

                turn = (turn + 1) % m;
            }

            ++number;
        }
        return answer.toString();
    }
}
