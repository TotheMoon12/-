import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < B.length; ++i) {
            pq.add(B[i]);
        }

        for (int i = 0; i < A.length; ++i) {
            int aNumber = A[i];

            while (!pq.isEmpty()) {
                int bNumber = pq.poll();

                if (bNumber > aNumber) {
                    ++answer;
                    break;
                }
            }

            if (pq.isEmpty()) {
                break;
            }
        }

        return answer;
    }
}
