import java.util.*;

class Solution {
    static int NEW_NODE = 0;
    static int DONUT = 1;
    static int STICK = 2;
    static int EIGHT = 3;

    class Info {
        int nodeCount;
        int edgeCount;
    }


    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        Set<Integer> set = new HashSet<>();

        int maxNumber = 0;
        for (int[] edge : edges) {
            set.add(edge[0]);
            set.add(edge[1]);
            maxNumber = Math.max(maxNumber, edge[0]);
            maxNumber = Math.max(maxNumber, edge[1]);
        }

        ArrayList<Integer>[] in = new ArrayList[maxNumber + 1];
        ArrayList<Integer>[] out = new ArrayList[maxNumber + 1];
        for (int i = 1; i <= maxNumber; ++i) {
            in[i] = new ArrayList<>();
            out[i] = new ArrayList<>();
        }


        for (int[] edge : edges) {
            in[edge[1]].add(edge[0]);
            out[edge[0]].add(edge[1]);
        }

        int newNode = 0;
        int outCount = 0;

        for (int i = 1; i <= set.size(); ++i) {

            if (in[i].size() == 0 && out[i].size() > outCount) {
                newNode = i;
                outCount = out[i].size();
            }
        }

        answer[NEW_NODE] = newNode;

        boolean[] visited = new boolean[maxNumber + 1];
        for (int node : out[newNode]) {
            Info info = bfs(out, node, visited);
            if (info.edgeCount == info.nodeCount) {
                ++answer[DONUT];
            } else if (info.edgeCount < info.nodeCount) {
                ++answer[STICK];
            } else {
                ++answer[EIGHT];
            }
        }

        return answer;
    }

    public Info bfs(ArrayList<Integer>[] out, int node, boolean[] visited) {
        visited[node] = true;
        Info info = new Info();
        info.nodeCount = 1;
        for (int next : out[node]) {
            if (!visited[next]) {
                Info sum = bfs(out, next, visited);
                info.nodeCount += sum.nodeCount;
                info.edgeCount += sum.edgeCount + 1;
            } else {
                ++info.edgeCount;
            }
        }

        return info;
    }
}
