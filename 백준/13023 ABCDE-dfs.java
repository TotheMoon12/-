import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int personNumber = Integer.parseInt(st.nextToken());
        int relationNumber = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] relations = new ArrayList[personNumber];
        for (int i = 0; i < personNumber; ++i) {
            relations[i] = new ArrayList<>();
        }

        for (int i = 0; i < relationNumber; ++i) {
            st = new StringTokenizer(br.readLine());
            int person1 = Integer.parseInt(st.nextToken());
            int person2 = Integer.parseInt(st.nextToken());

            relations[person1].add(person2);
            relations[person2].add(person1);
        }

        for (int i = 0; i < personNumber; ++i) {
            boolean[] visited = new boolean[2000];
            dfs(relations, i, 1, visited);

            if (answer == 1) {
                System.out.println(1);
                return;
            }
        }

        System.out.println(0);
    }

    private static void dfs(ArrayList<Integer>[] relations, int person, int depth, boolean[] visited) {
        visited[person] = true;
        if (depth == 5) {
            answer = 1;
            return;
        }

        for (int friend : relations[person]) {
            if (!visited[friend]) {
                dfs(relations, friend, depth + 1, visited);
            }
        }

        visited[person] = false; // 다른 경로를 통해서 친구관계가 이어질 수 있기 때문에 탐색이 끝나고 자기자신의 방문여부를 초기화 해준다
    }
}
