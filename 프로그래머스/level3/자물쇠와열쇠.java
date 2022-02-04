class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int count = 4;
        int lockHoleCount = 0;
        for (int i = 0; i < lock.length; ++i) {
            for (int j = 0; j < lock[i].length; ++j) {
                if (lock[i][j] == 0) {
                    ++lockHoleCount;
                }
            }
        }

        int[][] rotatedKey = key;
        while (true) {
            for (int startKeyCol = rotatedKey[0].length - 1; startKeyCol >= 0; --startKeyCol) {
                // key의 왼쪽 위 모서리가 자물쇠의 왼쪽 위 모서리와 일치 할 때까지(왼쪽 위 모서리 포함) 확인
                for (int startKeyRow = rotatedKey.length - 1; startKeyRow >= 0; --startKeyRow) {
                    boolean result = isMatched(rotatedKey, startKeyRow, startKeyCol, lock, 0, 0, lockHoleCount);
                    if (result) {
                        return true;
                    }
                }

                // key의 왼쪽 위 모서리가 자물쇠의 왼쪽 아래 모서리와 대응될 때까지(왼쪽 아래 모서리 포함) 비교
                for (int startLockRow = 0; startLockRow < lock.length; ++startLockRow) {
                    boolean result = isMatched(rotatedKey, 0, startKeyCol, lock, startLockRow, 0, lockHoleCount);
                    if (result) {
                        return true;
                    }
                }
            }

            for (int startLockCol = 1; startLockCol < lock[0].length; ++startLockCol) {
                //  key의 마지막 행으로 행을 늘려나가 자물쇠와 일치하는지 확인
                for (int startKeyRow = rotatedKey.length - 1; startKeyRow >= 0; --startKeyRow) {
                    boolean result = isMatched(rotatedKey, startKeyRow, 0, lock, 0, startLockCol, lockHoleCount);
                    if (result) {
                        return true;
                    }
                }

                // 키는 항상 0행 0열부터 시작해서 자물쇠의 행이 변하므로 자물쇠를 이동
                for (int startLockRow = 1; startLockRow < lock.length; ++startLockRow) {
                    boolean result = isMatched(rotatedKey, 0, 0, lock, startLockRow, startLockCol, lockHoleCount);
                    if (result) {
                        return true;
                    }
                }
            }

            --count;
            if (count == 0) {
                break;
            }

            rotatedKey = rotate(rotatedKey);
        }


        return false;
    }

    public boolean isMatched(int[][] key, int startKeyRow, int startKeyCol, int[][] lock, int startLockRow, int startLockCol, int lockHoleCount) {
        boolean result = true;

        int count = 0;
        for (int keyRow = startKeyRow, lockRow = startLockRow; keyRow < key.length && lockRow < lock.length; ++keyRow, ++lockRow) {
            for (int keyCol = startKeyCol, lockCol = startLockCol; keyCol < key[0].length && lockCol < lock[0].length; ++keyCol, ++lockCol) {
                if (key[keyRow][keyCol] == 1 && lock[lockRow][lockCol] == 1) {
                    result = false;
                    break;
                } else if (key[keyRow][keyCol] == 1 && lock[lockRow][lockCol] == 0) {
                    ++count;
                }
            }

            if (!result) {
                break;
            }
        }

        if (result && count == lockHoleCount) {
            return true;
        } else {
            return false;
        }
    }

    public int[][] rotate(int[][] arr) {
        final int ROW_SIZE = arr.length;
        final int COL_SIZE = arr[0].length;

        int[][] rotatedArr = new int[COL_SIZE][ROW_SIZE];
        for (int row = 0; row < ROW_SIZE; ++row) {
            for (int col = 0; col < COL_SIZE; ++col) {
                rotatedArr[col][COL_SIZE - row - 1] = arr[row][col];
            }
        }

        return rotatedArr;
    }
}
