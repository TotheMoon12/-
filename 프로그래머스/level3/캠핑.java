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
    
    // O(N^2) 구간합 사용풀이
    public int solution2(int n, int[][] data) {
        int answer = 0;

        HashSet<Integer> xSet = new HashSet<>();
        HashSet<Integer> ySet = new HashSet<>();

        for (int idx = 0; idx < n; ++idx) {
            xSet.add(data[idx][0]);
            ySet.add(data[idx][1]);
        }

        ArrayList<Integer> uniqueXList = new ArrayList<>(xSet);
        ArrayList<Integer> uniqueYList = new ArrayList<>(ySet);

        Collections.sort(uniqueXList);
        Collections.sort(uniqueYList);

        // 구간 합
        int[][] sum = new int[n][n];
        for (int idx = 0; idx < n; ++idx) {
            int x = uniqueXList.indexOf(data[idx][0]);
            int y = uniqueYList.indexOf(data[idx][1]);

            data[idx][0] = x;
            data[idx][1] = y;

            sum[x][y] = 1;
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i - 1 >= 0) {
                    sum[i][j] += sum[i - 1][j];
                }

                if (j - 1 >= 0) {
                    sum[i][j] += sum[i][j - 1];
                }

                if (i - 1 >= 0 && j - 1 >= 0) {
                    sum[i][j] -= sum[i - 1][j - 1];
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            int x1 = data[i][0];
            int y1 = data[i][1];

            for (int j = i + 1; j < n; ++j) {
                int x2 = data[j][0];
                int y2 = data[j][1];

                if (x1 == x2 || y1 == y2) {
                    continue;
                }

                int minX = Math.min(x1, x2);
                int maxX = Math.max(x1, x2);
                int minY = Math.min(y1, y2);
                int maxY = Math.max(y1, y2);


                if (minX + 1 == maxX || minY + 1 == maxY) {
                    ++answer;
                } else {
                    if (sum[maxX - 1][maxY - 1] - sum[minX][maxY - 1] - sum[maxX - 1][minY] + sum[minX][minY] == 0) {
                        ++answer;
                    }
                }
            }
        }

        return answer;
    }
}
