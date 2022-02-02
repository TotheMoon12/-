import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        
        for (int i = 0 ; i < scoville.length; ++i) {
            priorityQueue.add(scoville[i]);
        }
        
        int count = 0;
        while (priorityQueue.peek() < K && priorityQueue.size() > 1) {
            int min = priorityQueue.poll();
            int nextMin = priorityQueue.poll();
            
            int newScovile = min + nextMin * 2;
            priorityQueue.offer(newScovile);
            ++count;
        }
        
        if (priorityQueue.peek() < K) {
            answer = -1;
        } else {
            answer = count;
        }
        
        
        return answer;
    }
}
