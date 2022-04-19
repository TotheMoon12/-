class Solution {
    public int solution(int n) {
        int answer = 0;
        for (int i = 1 ; i <= n; ++i) {
            int num = 0;
            for (int j = i; j <= n; ++j) {
                num += j;
                    
                if (num == n) {
                    ++answer;
                    break;
                } else if (num > n) {
                    break;
                }
            }
        }
        return answer;
    }
}
