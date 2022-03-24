import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    static int answer = -1;
    static final int BLANK = 0;
    static final int BOARD_SIZE = 4;
    static int caseCount = 0;

    public static class Pair {
        final int row;
        final int col;

        public Pair(final int row, final int col) {
            this.row = row;
            this.col = col;
        }
    }

    public int solution(int[][] board, int r, int c) {
        HashMap<Integer, ArrayList<Pair>> sameCardMap = new HashMap<>();
        for (int i = 0; i < BOARD_SIZE; ++i) {
            for (int j = 0; j < BOARD_SIZE; ++j) {
                int type = board[i][j];
                if (type != BLANK) {
                    if (!sameCardMap.containsKey(type)) {
                        ArrayList<Pair> cards = new ArrayList<>();
                        cards.add(new Pair(i, j));
                        sameCardMap.put(type, cards);
                    } else {
                        ArrayList<Pair> cards = sameCardMap.get(type);
                        cards.add(new Pair(i, j));
                    }
                }
            }
        }

        ArrayList<Pair> cardOrder = new ArrayList<>();
        boolean[] visited = new boolean[7];
        dfs(board, sameCardMap, cardOrder, visited, r, c);

        return answer;
    }

    public int moveRow(int[][] board, int srcRow, int srcCol, int targetRow) {
        if (srcRow == targetRow) {
            return 0;
        }

        int count = 0;
        int blankCount = 0;

        if (srcRow < targetRow) {
            for (int row = srcRow + 1; row <= targetRow; ++row) {
                if (board[row][srcCol] != BLANK) {
                    ++count;
                } else {
                    ++blankCount;

                    if (row == targetRow) {
                        ++count;
                    }
                }
            }
        } else {
            for (int row = srcRow - 1; row >= targetRow; --row) {
                if (board[row][srcCol] != BLANK) {
                    ++count;
                } else {
                    ++blankCount;

                    if (row == targetRow) {
                        ++count;
                    }
                }
            }
        }

        if (((srcRow == 0 && targetRow == 2) || (srcRow == 3 && targetRow == 1)) && blankCount == 2) {
            count = 2;
        }

        return count;
    }

    public int moveCol(int[][] board, int srcRow, int srcCol, int targetCol) {
        if (srcCol == targetCol) {
            return 0;
        }

        int count = 0;
        int blankCount = 0;
        if (srcCol < targetCol) {
            for (int col = srcCol + 1; col <= targetCol; ++col) {
                if (board[srcRow][col] != BLANK) {
                    ++count;
                } else {
                    ++blankCount;

                    if (col == targetCol) {
                        ++count;
                    }
                }
            }
        } else {
            for (int col = srcCol - 1; col >= targetCol; --col) {
                if (board[srcRow][col] != BLANK) {
                    ++count;
                } else {
                    ++blankCount;
                    if (col == targetCol) {
                        ++count;
                    }
                }
            }
        }

        if (((srcCol == 0 && targetCol == 2) || (srcCol == 3 && targetCol == 1)) && blankCount == 2) {
            count = 2;
        }

        return count;
    }

    public void dfs(int[][] board, HashMap<Integer, ArrayList<Pair>> sameCardMap, ArrayList<Pair> cardOrder, boolean[] visited, int r, int c) {
        if (cardOrder.size() == sameCardMap.keySet().size() * 2) {
            ++caseCount;

            int currentRow = r;
            int currentCol = c;

            int count = 0;
            int[][] boardCloned = new int[BOARD_SIZE][BOARD_SIZE];
            for (int i = 0; i < BOARD_SIZE; ++i) {
                for (int j = 0; j < BOARD_SIZE; ++j) {
                    boardCloned[i][j] = board[i][j];
                }
            }

            for (int i = 0; i < cardOrder.size(); ++i) {
                Pair card = cardOrder.get(i);
                int nextRow = card.row;
                int nextCol = card.col;

                int rowFirst = moveRow(boardCloned, currentRow, currentCol, nextRow);
                rowFirst += moveCol(boardCloned, nextRow, currentCol, nextCol);

                int colFirst = moveCol(boardCloned, currentRow, currentCol, nextCol);
                colFirst += moveRow(boardCloned, currentRow, nextCol, nextRow);

                count += Math.min(rowFirst, colFirst);

                ++count; // ENTER
                boardCloned[nextRow][nextCol] = BLANK;
                currentRow = nextRow;
                currentCol = nextCol;
            }

            if (answer == -1) {
                answer = count;
            } else {
                answer = Math.min(answer, count);
            }

            return;
        }

        for (int type : sameCardMap.keySet()) {
            if (!visited[type]) {
                ArrayList<Pair> cardList = sameCardMap.get(type);
                Pair card1 = cardList.get(0);
                Pair card2 = cardList.get(1);

                cardOrder.add(card1);
                cardOrder.add(card2);

                visited[type] = true;
                dfs(board, sameCardMap, cardOrder, visited, r, c);
                visited[type] = false;
                cardOrder.remove(cardOrder.size() - 1);
                cardOrder.remove(cardOrder.size() - 1);

                cardOrder.add(card2);
                cardOrder.add(card1);
                visited[type] = true;
                dfs(board, sameCardMap, cardOrder, visited, r, c);
                visited[type] = false;
                cardOrder.remove(cardOrder.size() - 1);
                cardOrder.remove(cardOrder.size() - 1);
            }
        }

    }
}
