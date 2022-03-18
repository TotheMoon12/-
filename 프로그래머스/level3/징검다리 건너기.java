import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public static class Pair implements Comparable<Pair> {
        int number;
        int index;

        public Pair (int number, int index) {
            this.number = number;
            this.index = index;
        }

        @Override
        public int compareTo(Pair o) {
            return this.number - o.number;
        }
    }

    public int solution(int[] stones, int k) {
        int answer = 0;
        final int NUMBER = 0;
        final int PREV = 1;
        final int NEXT = 2;

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int[][] info = new int[stones.length][3];

        for (int i = 0; i < stones.length; ++i) {
            info[i][NUMBER] = stones[i];
            info[i][PREV] = i - 1;
            info[i][NEXT] = i + 1;

            pq.add(new Pair(stones[i], i));
        }

        while (!pq.isEmpty()) {
            Pair pair = pq.poll();

            int num = pair.number;
            int index = pair.index;

            int prev = info[index][PREV];
            int next = info[index][NEXT];

            if (prev != -1) {
                info[prev][NEXT] = next;
            }

            if (next != info.length) {
                info[next][PREV] = prev;
            }

            if (next - prev > k) {
                return num;
            }
        }

        return answer;
    }
}
