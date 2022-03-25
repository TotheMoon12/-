// 해설을 봄
// 완전탐색이라는 접근은 맞았지만 순서를 만들어내는 과정에서 하나할 때마다 dfs를 들어가는 방식으로 해서 시간 초과가 난 거 같음
// 해설은 시작을 취약 지점 한 지점 마다 해서 순서를 다 만들고 친구를 배정하는 순서를 다 만들어서 모든 취약지점을 방문할 수 있는 지를 비교한다

import java.util.ArrayList;

class Solution {
    static int answer = -1;
    ArrayList<int[]> map = new ArrayList<>();

    public int solution(int n, int[] weak, int[] dist) {
        for (int i = 0; i < weak.length; ++i) {
            int startWeak = weak[i];
            int index = i;
            int[] order = new int[weak.length];
            for (int j = 0; j < order.length; ++j) {
                order[j] = weak[index];

                if (weak[index] < startWeak) {
                    order[j] += n;
                }

                index = (index + 1) % weak.length;
            }

            map.add(order);
        }

        boolean[] visited = new boolean[dist.length];
        dfs(weak, dist, new ArrayList<>(), visited);
        return answer;
    }

    public void dfs(int[] weak, int[] dist, ArrayList<Integer> friends, boolean[] visited) {
        if (friends.size() > 0) {
            for (int i = 0; i < map.size(); ++i) {
                int[] order = map.get(i);
                int index = 0;
                 int count = 0;
                 for (int j = 0; j < friends.size(); ++j) {
                     int friend = friends.get(j);
                    int end = order[index] + dist[friend];
                    for (int k = index; k < order.length; ++k) {
                        if (order[k] <= end) {
                            ++index;
                        } else {
                            break;
                        }
                    }

                    if (index == order.length) {
                        count = j + 1;
                        break;
                    }
                }

                if (index == order.length) {
                    if (answer == -1) {
                        answer = count;
                    } else {
                        answer = Math.min(answer, count);
                    }
                }
            }
        }

        if (friends.size() == dist.length) {
            return;
        }

        for (int i = 0; i < visited.length; ++i) {
            if (!visited[i]) {
                visited[i] = true;
                friends.add(i);
                dfs(weak, dist, friends, visited);
                friends.remove(friends.size() - 1);
                visited[i] = false;
            }
        }
    }
}
