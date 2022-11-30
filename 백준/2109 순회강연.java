import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Course {
        int day;
        int price;

        public Course(int day, int price) {
            this.day = day;
            this.price = price;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());

        int maxD = 0;
        ArrayList<Course> list = new ArrayList<>();
        for (int n = 0; n < N; ++n) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int price = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());

            list.add(new Course(day, price));
            maxD = Math.max(maxD, day);
        }

        Collections.sort(list, new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                return o1.day - o2.day;
            }
        });

        PriorityQueue<Course> pq = new PriorityQueue<>(new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                return o2.price - o1.price;
            }
        });

        int answer = 0;
        int listIndex = N - 1;
        for (int day = maxD; day >= 1; --day) {
            for (; listIndex >= 0; --listIndex) {
                Course course = list.get(listIndex);

                if (course.day >= day) {
                    pq.offer(course);
                } else {
                    break;
                }
            }

            if (!pq.isEmpty()) {
                answer += pq.poll().price;
            }
        }

        bw.write(Integer.toString(answer));
        br.close();
        bw.close();
    }
}
