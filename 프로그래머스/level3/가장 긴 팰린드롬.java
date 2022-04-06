class Solution
{
    public int solution(String s)
    {
        int answer = 1;
        final int LENGTH = s.length();
        boolean[][] dp = new boolean[LENGTH + 1][LENGTH];
        
        dp[1][0] = true;
        for (int i = 1; i < LENGTH; ++i) {
            dp[1][i] = true;
            if (s.charAt(i) == s.charAt(i - 1)) {
                dp[2][i - 1] = true;
                answer = 2;
            }
        }
        
        for (int count = 3; count <= LENGTH; ++count) {
            for (int i = 0; i + count <= LENGTH; ++i) {
                char c = s.charAt(i);
                
                if (dp[count - 2][i + 1] && c == s.charAt(i + count - 1)) {
                    dp[count][i] = true;
                    answer = count;
                }
            }            
        }        

        return answer;
    }
}
