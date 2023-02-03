import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;

        Arrays.sort(book_time, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1[0].compareTo(o2[0]);
            }
        });

        PriorityQueue<Integer> endPQ = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        for (int idx = 0; idx < book_time.length; ++idx) {
            int startTime = getMinute(book_time[idx][0]);
            int endTime = getMinute(book_time[idx][1]) + 10;

            while (!endPQ.isEmpty()) {
                int prevEndTime = endPQ.peek();
                if (prevEndTime <= startTime) {
                    ++answer;
                    endPQ.poll();
                } else {
                    break;
                }
            }

            if (answer > 0) {
                --answer;
            }

            endPQ.offer(endTime);
        }

        answer += endPQ.size();
        return answer;
    }

    private int getMinute(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        int hour = Integer.parseInt(st.nextToken()) * 60;
        int minute = Integer.parseInt(st.nextToken());
        return hour + minute;
    }
}
