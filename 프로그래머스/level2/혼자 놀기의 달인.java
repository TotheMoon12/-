import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[] cards) {
        int answer = 0;

        boolean[] open = new boolean[cards.length];
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        
        pq.offer(0);
        for (int idx = 0; idx < cards.length; ++idx) {
            int point = idx;
            int count = 0;
            while (!open[point]) {
                ++count;
                open[point] = true;
                point = cards[point] - 1;
            }

            pq.offer(count);
        }

        answer = pq.poll() * pq.poll();
        return answer;
    }
}
