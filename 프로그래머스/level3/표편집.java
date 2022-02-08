/*
 * 배열에 이중 연결리스트의 특성을 사용하여 효율성을 높였다
 * 문제에서 표의 길이가 주어지고 새롭게 데이터가 추가되지 않기 때문에
 * 실제 노드 클래스를 만들필요 없이 배열을 사용해서 연결리스트구조를 만들고 상태를 저장하였다
 * 다른 분들의 풀이를 보니 스택만 사용하여 제거된 인덱스를 저장하여 결과를 만드는 것이 가능하였다
 * 내 코드는 메모리를 많이 사용하지만 성능이 좋았고
 * 스택만 사용한 코드는 메모리는 적게 먹지만 결과 사이사이에 삽입을 해줘야해서 느렸다
 * 단, 연결리스트와 스택을 모두 제대로 사용한 코드는 내 코드보다 성능이 2배 이상 좋았다
*/

import java.util.StringTokenizer;
import java.util.Stack;

class Solution {
    static final int PREV = 0;
    static final int NEXT = 1;
    static final int STATUS = 2;
    static final int REMOVE = 1;
    static final int NORMAL = 0;

    public String solution(int n, int k, String[] cmd) {
        int[][] table = new int[n][3];
        for (int i = 0; i < n; ++i) {
            if (i == 0) {
                table[i][PREV] = -1;
                table[i][NEXT] = i + 1;
            } else if (i == n - 1) {
                table[i][PREV] = i - 1;
                table[i][NEXT] = -1;
            } else {
                table[i][PREV] = i - 1;
                table[i][NEXT] = i + 1;
            }
        }

        Stack<Integer> removedRowList = new Stack<>();
        int index = k;

        for (String command : cmd) {
            StringTokenizer st = new StringTokenizer(command);
            String commandType = st.nextToken();

            if (commandType.equals("U")) {
                int moveCount = Integer.parseInt(st.nextToken());

                int count = 0;
                while (count++ < moveCount) {
                    index = table[index][PREV];
                }
            } else if (commandType.equals("D")) {
                int moveCount = Integer.parseInt(st.nextToken());

                int count = 0;
                while (count++ < moveCount) {
                    index = table[index][NEXT];
                }
            } else if (commandType.equals("C")) {
                removedRowList.add(index);
                removedRowList.add(table[index][PREV]);
                removedRowList.add(table[index][NEXT]);
                table[index][STATUS] = REMOVE;

                int prev = table[index][PREV];
                int next = table[index][NEXT];

                if (prev != -1) {
                    table[prev][NEXT] = next;
                    index = prev;
                }

                if (next != -1) {
                    table[next][PREV] = prev;
                    index = next;
                }
            } else {
                if (!removedRowList.isEmpty()) {
                    int next = removedRowList.pop();
                    int prev = removedRowList.pop();
                    int recoveryIndex = removedRowList.pop();

                    if (next != -1) {
                        table[next][PREV] = recoveryIndex;
                    }

                    if (prev != -1) {
                        table[prev][NEXT] = recoveryIndex;
                    }
                    table[recoveryIndex][STATUS] = NORMAL;
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            if (table[i][STATUS] == NORMAL) {
                answer.append('O');
            } else {
                answer.append('X');
            }
        }

        return answer.toString();
    }
}
