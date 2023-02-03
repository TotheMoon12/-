import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution {
    public class Book {
        int start;
        int end;
        
        public Book(String start, String end) {
            this.start = getMinute(start);
            this.end = getMinute(end) + 10;
        }
    }
    
    public int solution(String[][] book_time) {
        int answer = 0;

        PriorityQueue<Book> startPQ = new PriorityQueue<>(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.start - o2.start;
            }
        });

        PriorityQueue<Integer> endPQ = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        for (int idx = 0; idx < book_time.length; ++idx) {
            startPQ.add(new Book(book_time[idx][0], book_time[idx][1]));
        }
        
        while (!startPQ.isEmpty()) {
            Book book = startPQ.poll();
            int startTime = book.start;
            int endTime = book.end;

            if (!endPQ.isEmpty()) {
                int prevEndTime = endPQ.peek();
                if (prevEndTime <= startTime) {
                    endPQ.poll();
                }
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
