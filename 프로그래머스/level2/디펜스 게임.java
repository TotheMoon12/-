import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;

        int totalEnemy = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int round = 0; round < enemy.length; ++round) {
            answer = round + 1;
            totalEnemy += enemy[round];

            pq.offer(enemy[round]);
            if (totalEnemy > n) {
                if (k > 0) {
                    totalEnemy -= pq.poll();
                    --k;
                } else {
                    answer = round;
                    break;
                }
            }
        }

        return answer;
    }
}
