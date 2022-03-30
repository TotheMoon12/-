import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }

                return o1[0] - o2[0];
            }
        });

        boolean[] visited = new boolean[routes.length];
        for (int i = 0; i < routes.length; ++i) {
            if (!visited[i]) {
                visited[i] = true;
                int prevCount = 0;
                for (int j = i; j < routes.length; ++j) {
                    int start = routes[j][0];
                    int count = 0;
                    for (int k = i; k < routes.length; ++k) {
                        if (start >= routes[k][0] && start <= routes[k][1]) {
                            ++count;
                            visited[k] = true;
                        } else {
                            break;
                        }
                    }

                    if (count < prevCount) {
                        break;
                    }
                }

                ++answer;
            }
        }

        return answer;
    }
}
