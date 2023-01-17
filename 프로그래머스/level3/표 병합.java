import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

class Solution {
    public static class Cell {
        final private int r;
        final private int c;
        private String value;
        private HashSet<Cell> mergeList;

        public Cell(int r, int c) {
            this.r = r;
            this.c = c;
            this.value = "";
            this.mergeList = new HashSet<>();
        }

        public String getValue() {
            return this.value;
        }

        public boolean isSame(String value) {
            return this.value.equals(value);
        }

        public void update(String value) {
            this.value = value;
            for (Cell other : mergeList) {
                other.value = value;
            }
        }

        public void merge(Cell cell) {
            if (this.mergeList.contains(cell)) {
                return;
            }

            String value;
            if (this.value.equals("")) {
                value = cell.value;
            } else {
                value = this.value;
            }

            this.value = value;
            cell.value = value;
            for (Cell otherElement : cell.mergeList) {
                for (Cell thisElement : this.mergeList) {
                    if (otherElement.equals(thisElement)) {
                        continue;
                    }

                    otherElement.mergeList.add(thisElement);
                }

                otherElement.value = value;
                otherElement.mergeList.add(this);
                this.mergeList.add(otherElement);
            }

            for (Cell thisElement : this.mergeList) {
                for (Cell otherElement : cell.mergeList) {
                    if (thisElement.equals(otherElement)) {
                        continue;
                    }

                    thisElement.mergeList.add(otherElement);
                }

                thisElement.value = value;
                thisElement.mergeList.add(cell);
                cell.mergeList.add(thisElement);
            }

            this.mergeList.add(cell);
            cell.mergeList.add(this);
        }

        public void unmerge() {
            for (Cell other : this.mergeList) {
                other.value = "";
                other.mergeList.clear();
            }

            this.mergeList.clear();
        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash = hash * 31 + r;
            hash = hash * 31 + c;

            return hash;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }

            if (object == null || !(object instanceof Cell) || this.hashCode() != object.hashCode()) {
                return false;
            }

            Cell other = (Cell) object;
            return this.r == other.r && this.c == other.c;
        }
    }

    public String[] solution(String[] commands) {
        String[] answer;

        final int SIZE = 50;
        Cell[][] board = new Cell[SIZE + 1][SIZE + 1];
        for (int r = 1; r <= SIZE; ++r) {
            for (int c = 1; c <= SIZE; ++c) {
                board[r][c] = new Cell(r, c);
            }
        }

        ArrayList<String> printList = new ArrayList<>();
        for (String command : commands) {
            StringTokenizer st = new StringTokenizer(command);

            String type = st.nextToken();
            if (type.equals("UPDATE")) {
                if (st.countTokens() == 3) {
                    int r = Integer.parseInt(st.nextToken());
                    int c = Integer.parseInt(st.nextToken());
                    String value = st.nextToken();

                    board[r][c].update(value);
                } else {
                    String origin = st.nextToken();
                    String value = st.nextToken();
                    for (int r = 1; r <= SIZE; ++r) {
                        for (int c = 1; c <= SIZE; ++c) {
                            if (board[r][c].isSame(origin)) {
                                board[r][c].update(value);
                            }
                        }
                    }
                }
            } else if (type.equals("MERGE")) {
                final int r1 = Integer.parseInt(st.nextToken());
                final int c1 = Integer.parseInt(st.nextToken());
                final int r2 = Integer.parseInt(st.nextToken());
                final int c2 = Integer.parseInt(st.nextToken());

                if (r1 == r2 && c1 == c2) {
                    continue;
                }

                board[r1][c1].merge(board[r2][c2]);
            } else if (type.equals("UNMERGE")) {
                final int r = Integer.parseInt(st.nextToken());
                final int c = Integer.parseInt(st.nextToken());
                board[r][c].unmerge();
            } else { // PRINT
                final int r = Integer.parseInt(st.nextToken());
                final int c = Integer.parseInt(st.nextToken());
                String value = board[r][c].getValue();

                if (value.equals("")) {
                    printList.add("EMPTY");
                } else {
                    printList.add(value);
                }
            }
        }

        final int PRINT_LIST_SIZE = printList.size();
        answer = new String[PRINT_LIST_SIZE];
        for (int idx = 0; idx < PRINT_LIST_SIZE; ++idx) {
            answer[idx] = printList.get(idx);
        }

        return answer;
    }
}
