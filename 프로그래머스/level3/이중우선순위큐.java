import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];

        StringTokenizer st;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < operations.length; ++i) {
            st = new StringTokenizer(operations[i]);

            String operation = st.nextToken();
            int number = Integer.parseInt(st.nextToken());

            if (operation.equals("I")) {
                pq.add(number);
            } else {
                if (number == 1) {
                    pq.poll();
                } else {
                    Queue<Integer> queue = new LinkedList<>();
                    while (!pq.isEmpty()) {
                        queue.add(pq.poll());
                    }

                    while (queue.size() > 1) {
                        pq.add(queue.poll());
                    }
                }
            }
        }

        if (pq.isEmpty()) {
            answer[0] = 0;
            answer[1] = 0;
        } else {
            answer[0] = pq.poll();

            if (pq.isEmpty()) {
                answer[1] = answer[0];
            } else {
                int number = pq.poll();
                while (!pq.isEmpty()) {
                    number = pq.poll();
                }

                answer[1] = number;
            }
        }
        return answer;
    }
}
