import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int[] solution(String[][] places) {
        final int LENGTH = 5;
        int[] answer = new int[LENGTH];
        int[] rowData = {-1, 1, 0, 0};
        int[] colData = {0, 0, -1, 1};


        for (int i = 0; i < LENGTH; ++i) {
            boolean isGood = true;

            boolean[][] visited = new boolean[LENGTH][LENGTH];
            for (int j = 0; j < LENGTH && isGood; ++j) {
                for (int k = 0; k < LENGTH && isGood; ++k) {
                    char type = places[i][j].charAt(k);

                    if (type == 'P') {
                        Queue<Integer> queue = new LinkedList<>();
                        

                        visited[j][k] = true;
                        queue.add(j);
                        queue.add(k);
                        queue.add(0);
                        while (!queue.isEmpty() && isGood) {
                            int row = queue.poll();
                            int col = queue.poll();
                            int distance = queue.poll();

                            for (int m = 0; m < rowData.length; ++m) {
                                int newRow = row + rowData[m];
                                int newCol = col + colData[m];

                                if (newRow < 0 || newRow >= LENGTH || newCol < 0 || newCol >= LENGTH) {
                                    continue;
                                }

                                if (!visited[newRow][newCol] && distance + 1 <= 2) {
                                    char next = places[i][newRow].charAt(newCol);

                                    if (next == 'P') {
                                        isGood = false;
                                        break;
                                    } else if (next == 'O') {
                                        queue.add(newRow);
                                        queue.add(newCol);
                                        queue.add(distance + 1);
                                        visited[newRow][newCol] = true;
                                    }
                                }
                            }
                        }
                    }

                }
            }

            if (isGood) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }

        return answer;
    }
}
