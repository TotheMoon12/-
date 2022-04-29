// 앞에서 차례대로 순서를 고정시켜나가면서 경우의 수를 세가며 고정되는 수를 구한다

import java.util.ArrayList;

class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        final int FACTORIAL_MAX = 20;

        ArrayList<Integer> persons = new ArrayList<>();
        for (int i = 1; i <= n; ++i) {
            persons.add(i);
        }

        long[] factorials = new long[FACTORIAL_MAX];
        factorials[0] = 1;
        factorials[1] = 1;


        for (int i = 2; i < FACTORIAL_MAX; ++i) {
            factorials[i] = factorials[i - 1] * i;
        }

        final int PERSON = n;
        --n;
        int index = 0;
        while (index < PERSON - 1) {
            int order = (int) (k / factorials[n]);
            if (k % factorials[n] == 0) {
                --order;
                k -= Math.max(0, order) * factorials[n];
            } else {
                k %= factorials[n];
            }

            answer[index++] = persons.get(order);
            persons.remove(order);
            --n;
        }

        answer[index] = persons.get(0);
        return answer;
    }
}
