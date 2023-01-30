// 우선순위큐 말고 스택을 사용해서 뒤에서부터 확인을 하면 o(n)에 해결가능
import java.util.PriorityQueue;

class Solution {
    public class Node implements Comparable<Node> {
        int number;
        int idx;

        public Node(int number, int idx) {
            this.number = number;
            this.idx = idx;
        }


        @Override
        public int compareTo(Node o) {
            return this.number - o.number;
        }
    }

    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(numbers[0], 0));
        answer[0] = -1;
        for (int idx = 1; idx < numbers.length; ++idx) {
            answer[idx] = -1;

            int number = numbers[idx];
            while (!pq.isEmpty()) {
                if (number > pq.peek().number) {
                    Node node = pq.poll();
                    answer[node.idx] = number;
                } else {
                    break;
                }
            }

            pq.add(new Node(number, idx));
        }

        return answer;
    }
}
