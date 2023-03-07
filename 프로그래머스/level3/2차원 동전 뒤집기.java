import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    static int answer = -1;

    public int solution(int[][] beginning, int[][] target) {
        final int ROW_LENGTH = beginning.length;
        final int COL_LENGTH = beginning[0].length;

        ArrayList<Integer> list = new ArrayList<>();

        for (int idx = 1; idx <= ROW_LENGTH; ++idx) {
            list.add(idx);
        }

        for (int idx = 1; idx <= COL_LENGTH; ++idx) {
            list.add(idx * -1);
        }

        HashMap<Integer, Boolean> selected = new HashMap<>();
        dfs(list, selected, beginning, target);
        return answer;
    }

    public void dfs(ArrayList<Integer> list, HashMap<Integer, Boolean> selected, int[][] beginning, int[][] target) {
        final int ROW_LENGTH = beginning.length;
        final int COL_LENGTH = beginning[0].length;
        if (selected.size() == list.size()) {
            int[][] temp = new int[ROW_LENGTH][COL_LENGTH];

            // 복사
            for (int row = 0; row < ROW_LENGTH; ++row) {
                for (int col = 0; col < COL_LENGTH; ++col) {
                    temp[row][col] = beginning[row][col];
                }
            }

            // 뒤집기
            int count = 0;
            for (int idx = 0; idx < list.size(); ++idx) {
                int choice = list.get(idx);

                if (selected.get(choice)) {
                    ++count;
                    if (choice > 0) {
                        choice = Math.abs(choice) - 1;
                        for (int col = 0; col < COL_LENGTH; ++col) {
                            temp[choice][col] = (temp[choice][col] + 1) % 2;
                        }
                    } else {
                        choice = Math.abs(choice) - 1;
                        for (int row = 0; row < ROW_LENGTH; ++row) {
                            temp[row][choice] = (temp[row][choice] + 1) % 2;
                        }
                    }
                }
            }

            boolean isSame = true;
            for (int row = 0; row < ROW_LENGTH; ++row) {
                for (int col = 0; col < COL_LENGTH; ++col) {
                    if (temp[row][col] != target[row][col]) {
                        isSame = false;
                        break;
                    }
                }

                if (!isSame) {
                    break;
                }
            }

            if (isSame) {
                if (answer == -1) {
                    answer = count;
                } else {
                    answer = Math.min(answer, count);
                }
            }

            return;
        }

        int choice = list.get(selected.size());
        selected.put(choice, true);
        dfs(list, selected, beginning, target);

        selected.put(choice, false);
        dfs(list, selected, beginning, target);
        selected.remove(choice);
    }
}
