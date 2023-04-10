import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static class Ball implements Comparable<Ball> {
        int color;
        int size;
        int index;

        public Ball(int color, int size, int index) {
            this.color = color;
            this.size = size;
            this.index = index;
        }

        @Override
        public int compareTo(Ball o) {
            return this.size - o.size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());
        final int MAX_SIZE = 2000;
        HashMap<Integer, Integer>[] scoreMapBySizeAndColor = new HashMap[MAX_SIZE + 1];
        for (int size = 1; size <= MAX_SIZE; ++size) {
            scoreMapBySizeAndColor[size] = new HashMap<>();
        }

        int[] totalScoreBySize = new int[MAX_SIZE + 1];
        ArrayList<Ball> ballList = new ArrayList<>();
        for (int n = 0; n < N; ++n) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());

            Ball ball = new Ball(color, size, n);
            ballList.add(ball);
            scoreMapBySizeAndColor[size].put(color, scoreMapBySizeAndColor[size].getOrDefault(color, 0) + size);
            totalScoreBySize[size] += size;
        }

        Collections.sort(ballList);
        int[] sumByColor = new int[N + 1];
        int[] scores = new int[N + 1];
        int ballIndex = 0;
        for (int size = ballList.get(0).size; size <= MAX_SIZE; ++size) {
            while (ballIndex < N) {
                Ball ball = ballList.get(ballIndex);
                if (ball.size == size) {
                    scores[ball.index] = totalScoreBySize[size - 1] - sumByColor[ball.color];
                    ++ballIndex;
                } else {
                    break;
                }
            }

            for (int color : scoreMapBySizeAndColor[size].keySet()) {
                sumByColor[color] += scoreMapBySizeAndColor[size].get(color);
            }

            totalScoreBySize[size] += totalScoreBySize[size - 1];
        }

        StringBuilder builder = new StringBuilder(N);
        for (int n = 0; n < N; ++n) {
            builder.append(scores[n]);
            builder.append(System.lineSeparator());
        }

        bw.write(builder.toString());
        br.close();
        bw.close();
    }
}
