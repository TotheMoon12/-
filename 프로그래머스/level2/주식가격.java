import java.util.PriorityQueue;

class Solution {
    public static class Pair implements Comparable<Pair> {
        int price;
        int time;

        public Pair(final int price, final int time) {
            this.price = price;
            this.time = time;
        }

        @Override
        public int compareTo(Pair other) {
            return other.price - this.price;
        }
    }

    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        pq.add(new Pair(prices[0], 0));
        for (int i = 1; i < prices.length; ++i) {
            int curPrice = prices[i];

            while (!pq.isEmpty() && pq.peek().price > curPrice) {
                Pair pair = pq.poll();
                answer[pair.time] = i - pair.time;
            }

            pq.add(new Pair(curPrice, i));
        }

        while (!pq.isEmpty()) {
            Pair pair = pq.poll();
            answer[pair.time] = prices.length -1 - pair.time;
        }

        return answer;
    }
}
