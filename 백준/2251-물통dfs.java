// 못 풋 문제
// 풀이참조 : 알고리즘 스터티원의 풀이참조
// 평소에 dfs를 사용하는 문제에서는 보통 2차원 배열에 탐색을 했는데 이 문제의 경우에는
// 3가지 물병이 있고 각 물병에 생길 수 있는 물의 양이 visited로 사용되어 생각을 달리해야 했다
// 물병의 최대 용량이 200으로 제한되어 있고 두 물병의 물 양을 안다면 다른 하나의 물병의 현재 물양은
// 알 수 있기 때문에 visited로 두 물병의 양을 인덱스로 사용하게 2차원 boolean배열을 사용한다
// 그리고 물을 옮기는 경우의 수는
// A -> B, A -> C
// B -> A, B -> C
// C -> A, C -> B
// 로 총 6가지의 경우가 있기 때문에 6가지 경우를 모두 방문하게 하고
// visited로 이미 방문을 했다면 종료를 하게 하는 방식으로 물병 A가 0일 때 C의 물양을 알아낸다
// 알아낸 C의 물양은 총 가능한 용량이 200이므로 201크기의 boolean 배열을 만들어 해당 인덱스를 true로 만들어 기록한다
// 그리고 최종적으로 이 boolean배열에 값이 true인 인덱스를 출력하여  C의 물양을 출력한다


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] capacity;
    static final int A = 0;
    static final int B = 1;
    static final int C = 2;
    static final int BOTTLE_NUM = 3;
    static final int MAX_CAPACITY = 200;
    static boolean[][] visited = new boolean[MAX_CAPACITY + 1][MAX_CAPACITY + 1];
    static boolean[] answer = new boolean[MAX_CAPACITY + 1];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        capacity = new int[3];
        capacity[A] = Integer.parseInt(st.nextToken());
        capacity[B] = Integer.parseInt(st.nextToken());
        capacity[C] = Integer.parseInt(st.nextToken());

        dfs(0, 0, capacity[C]);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= MAX_CAPACITY; ++i) {
            if (answer[i]) {
                sb.append(i);
                sb.append(' ');
            }
        }

        System.out.println(sb.toString());
    }

    public static void dfs(int amountA, int amountB, int amountC) {
        if (visited[amountA][amountB]) {
            return;
        }

        visited[amountA][amountB] = true;

        if (amountA == 0) {
            answer[amountC] = true;
        }

        // A -> B
        int movedWater = Math.min(capacity[B] - amountB, amountA);
        dfs(amountA - movedWater, amountB + movedWater, amountC);

        // A -> C
        movedWater = Math.min(capacity[C] - amountC, amountA);
        dfs(amountA - movedWater, amountB, amountC + movedWater);

        // B -> A
        movedWater = Math.min(capacity[A] - amountA, amountB);
        dfs(amountA + movedWater, amountB - movedWater, amountC);

        // B -> C
        movedWater = Math.min(capacity[C] - amountC, amountB);
        dfs(amountA, amountB - movedWater, amountC + movedWater);

        // C -> A
        movedWater = Math.min(capacity[A] - amountA, amountC);
        dfs(amountA + movedWater, amountB, amountC - movedWater);

        // C -> B
        movedWater = Math.min(capacity[B] - amountB, amountC);
        dfs(amountA, amountB + movedWater, amountC - movedWater);
    }
}
