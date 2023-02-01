import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;

        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[col - 1] == o2[col - 1]) {
                    return o2[0] - o1[0];
                }

                return o1[col - 1] - o2[col - 1];
            }
        });

        for (int idx = row_begin - 1; idx < row_end; ++idx) {
            int s_i = 0;
            for (int value : data[idx]) {
                s_i += value % (idx + 1);
            }

            answer ^= s_i;
        }

        return answer;
    }
}
