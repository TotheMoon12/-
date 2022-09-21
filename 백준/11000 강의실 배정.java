import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static class Course {
        int start;
        int end;

        public Course(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

        final int COURSE_COUNT = Integer.parseInt(br.readLine());
        PriorityQueue<Course> startPQ = new PriorityQueue<>(new Comparator<Course>() {
            @Override
            public int compare(Course c1, Course c2) {
                return c1.start - c2.start;
            }
        });
        for (int count = 0; count < COURSE_COUNT; ++count) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            startPQ.offer(new Course(start, end));
        }

        PriorityQueue<Course> endPQ = new PriorityQueue<>(new Comparator<Course>() {
            @Override
            public int compare(Course c1, Course c2) {
                return c1.end - c2.end;
            }
        });

        endPQ.offer(startPQ.poll());
        while (!startPQ.isEmpty()) {
            Course course = startPQ.poll();

            Course prev = endPQ.poll();
            if (course.start >= prev.end) {
                endPQ.offer(course);
            } else {
                endPQ.offer(prev);
                endPQ.offer(course);
            }
        }

        System.out.print(endPQ.size());
    }
}
