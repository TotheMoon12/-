class Solution {
    static int answer = 0;
    static final int AT_LEAST_FATIGUE = 0;
    static final int FATIGUE = 1;

    public int solution(int k, int[][] dungeons) {
        dfs(k, dungeons, 0, 0);
        return answer;
    }

    public void dfs(int k, int[][] dungeons, int bitMask, int count) {
        int remain = 0;
        for (int i = 0; i < dungeons.length; ++i) {
            if ((bitMask >> i & 1) == 0 && k >= dungeons[i][AT_LEAST_FATIGUE]) {
                ++remain;
                dfs(k - dungeons[i][FATIGUE], dungeons, bitMask | (1 << i) , count + 1);
            }
        }

        if (remain == 0) {
            answer = Math.max(answer, count);
        }
    }
}
