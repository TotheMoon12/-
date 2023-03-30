import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int n, int[][] data) {
        int answer = 0;

        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for (int one = 0; one < data.length - 1; ++one) {
            int x1 = data[one][0];
            int y1 = data[one][1];

            for (int two = one + 1; two < data.length; ++two) {
                int x2 = data[two][0];
                int y2 = data[two][1];

                if (x1 != x2 && y1 != y2) {
                    int smallX = Math.min(x1, x2);
                    int bigX = Math.max(x1, x2);
                    int smallY = Math.min(y1, y2);
                    int bigY = Math.max(y1, y2);

                    boolean isPossible = true;
                    for (int idx = one + 1; idx < two; ++idx) {
                        int x = data[idx][0];
                        int y = data[idx][1];

                        if (x > smallX && x < bigX && y > smallY && y < bigY) {
                            isPossible = false;
                            break;
                        }
                    }

                    if (isPossible) {
                        ++answer;
                    }
                }
            }
        }

        return answer;
    }
}
