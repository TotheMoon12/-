class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 1;
        int[][] map = new int[N + 1][N + 1];
        final int INF = 600000;

        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= N; ++j) {
                if (i != j) {
                    map[i][j] = INF;
                }
            }
        }

        for (int i = 0; i < road.length; ++i) {
            int v1 = road[i][0];
            int v2 = road[i][1];
            int time = road[i][2];

            if (time < map[v1][v2]) {
                map[v1][v2] = time;
                map[v2][v1] = time;
            }
        }

        int[] times = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        visited[1] = true;

        times[0] = INF;
        for (int i = 2; i <= N; ++i) {
            times[i] = map[1][i];
        }

        for (int count = 2; count <= N; ++count) {
            int minIndex = 0;

            for (int i = 2; i <= N; ++i) {
                if (!visited[i] && times[i] <= times[minIndex]) {
                    minIndex = i;
                }
            }

            visited[minIndex] = true;
            for (int i = 2; i <= N; ++i) {
                int time = times[minIndex] + map[minIndex][i];

                if (time < times[i]) {
                    times[i] = time;
                }
            }
        }

        for (int i = 2; i <= N; ++i) {
            if (times[i] <= K) {
                ++answer;
            }
        }

        return answer;
    }


}
