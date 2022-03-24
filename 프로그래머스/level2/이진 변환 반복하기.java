class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        
        int count = 0;
        int removedZeroCount = 0;
        while (!s.equals("1")) {
            ++count;
            
            int length = 0;
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == '1') {
                    ++length;
                } else {
                    ++removedZeroCount;
                }
            }

            StringBuilder sb = new StringBuilder();
            while (true) {
                int remain = length % 2;

                sb.insert(0, remain);

                length /= 2;

                if (length == 0) {
                    break;
                }
            }
            
            s = sb.toString();
        }
        
        answer[0] = count;
        answer[1] = removedZeroCount;
        return answer;
    }
}
