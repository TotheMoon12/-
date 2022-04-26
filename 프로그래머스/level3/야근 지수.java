import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < works.length; ++i) {
            pq.add(works[i]);
        }

        while (n > 0 && !pq.isEmpty()) {
            int work = pq.poll();

            if (work - 1 > 0) {
                pq.add(work - 1);
            }

            --n;
        }

        while (!pq.isEmpty()) {
            int work = pq.poll();
            answer += work * work;
        }

        return answer;
    }
}
