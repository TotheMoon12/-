// 위상 정렬을 통해서 각 건물을 짓는데 걸리는 시간을 계산한다

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        boolean[] visited = new boolean[N];
        ArrayList<Integer>[] map = new ArrayList[N];
        for (int i = 0; i < N; ++i) {
            map[i] = new ArrayList<>();
        }

        int[] times = new int[N];
        int[] entryCounts = new int[N];
        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            times[i] = time;

            while (st.hasMoreTokens()) {
                int building = Integer.parseInt(st.nextToken());

                if (building == -1) {
                    break;
                }

                map[building - 1].add(i);
                ++entryCounts[i];
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < N; ++i) {
            if (entryCounts[i] == 0) {
                visited[i] = true;
                queue.add(i);
                queue.add(times[i]);
            }
        }

        int[] answer = new int[N];
        while (!queue.isEmpty()) {
            int building = queue.poll();
            int time = queue.poll();
            answer[building] = time;

            for (int nextBuilding : map[building]) {
                --entryCounts[nextBuilding];
                answer[nextBuilding] = Math.max(answer[nextBuilding], time);
            }

            for (int i = 0; i < N; ++i) {
                if (!visited[i] && entryCounts[i] == 0) {
                    visited[i] = true;
                    queue.add(i);
                    queue.add(answer[i] + times[i]);
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int time : answer) {
            bw.write(time + "");
            bw.write(System.lineSeparator());
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
