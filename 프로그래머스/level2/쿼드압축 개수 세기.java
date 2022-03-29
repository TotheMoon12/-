class Solution {
    static int[] answer = new int[2];
    
    public int[] solution(int[][] arr) {
        compress(arr, 0,0, arr.length);
        return answer;
    }
    
    public void compress(int[][] arr, int startRow, int startCol, int length) {
        if (length == 1) {
            ++answer[arr[startRow][startCol]];
            return;
        }
        
        boolean isCompressed = true;
        int value = arr[startRow][startCol];
        for (int row = startRow; row < startRow + length; ++row) {
            for (int col = startCol; col < startCol + length; ++col) {
                if (arr[row][col] != value) {
                    isCompressed = false;
                    break;
                }
            }
        }
        
        if (isCompressed) {
            ++answer[value];
        } else {
            int nextLength = length / 2;
            compress(arr, startRow, startCol, nextLength);
            compress(arr, startRow + nextLength, startCol + nextLength, nextLength);
            compress(arr, startRow + nextLength, startCol, nextLength);
            compress(arr, startRow, startCol + nextLength, nextLength);
        }
    }
}
