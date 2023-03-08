class Solution {
    static int answer = Integer.MAX_VALUE;
    static int[] rowData = {-1, 1, 0, 0};
    static int[] colData = {0, 0, -1, 1};
    static int DIRECTION_NUM = 4;

    public int solution(int[][] clockHands) {
        int[] rotateCountsOfFirstRow = new int[clockHands.length];
        dfs(rotateCountsOfFirstRow, 0, clockHands);

        return answer;
    }

    public void dfs(int[] rotateCountsOfFirstRow, int index, int[][] clockHands) {
        if (index == rotateCountsOfFirstRow.length) {
            int[][] copy = new int[index][index];
            for (int row = 0; row < index; ++row) {
                for (int col = 0; col < index; ++col) {
                    copy[row][col] = clockHands[row][col];
                }
            }

            int total = 0;
            for (int col = 0; col < index; ++col) {
                total += rotateCountsOfFirstRow[col];
                copy[0][col] = (copy[0][col] + rotateCountsOfFirstRow[col]) % DIRECTION_NUM;
                for (int idx = 0; idx < rowData.length; ++idx) {
                    int nearRow = rowData[idx];
                    int nearCol = col + colData[idx];

                    if (nearRow < 0 || nearRow >= index || nearCol < 0 || nearCol >= index) {
                        continue;
                    }

                    copy[nearRow][nearCol] = (copy[nearRow][nearCol] + rotateCountsOfFirstRow[col]) % DIRECTION_NUM;
                }
            }

            for (int row = 1; row < index; ++row) {
                for (int col = 0; col < index; ++col) {
                    int count = DIRECTION_NUM - copy[row - 1][col];
                    if (count == DIRECTION_NUM) {
                        continue;
                    }

                    total += count;
                    copy[row][col] = (copy[row][col] + count) % DIRECTION_NUM;
                    for (int idx = 0; idx < rowData.length; ++idx) {
                        int nearRow = row + rowData[idx];
                        int nearCol = col + colData[idx];

                        if (nearRow < 0 || nearRow >= index || nearCol < 0 || nearCol >= index) {
                            continue;
                        }

                        copy[nearRow][nearCol] = (copy[nearRow][nearCol] + count) % DIRECTION_NUM;
                    }
                }
            }

            boolean isPossible = true;
            int lastRow = index - 1;
            for (int col = 0; col < index; ++col) {
                if (copy[lastRow][col] != 0) {
                    isPossible = false;
                    break;
                }
            }

            if (isPossible) {
                answer = Math.min(answer, total);
            }

            return;
        }

        for (int count = 0; count < 4; ++count) {
            rotateCountsOfFirstRow[index] = count;
            dfs(rotateCountsOfFirstRow, index + 1, clockHands);
        }
    }
}
