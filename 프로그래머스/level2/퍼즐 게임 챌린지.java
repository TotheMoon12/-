class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        int maxLevel = 0;
        for (int diff : diffs) {
            maxLevel = Math.max(maxLevel, diff);
        }
        
        int left = 1;
        int right = maxLevel;
        
        int min = maxLevel;
        while (left <= right) {
            int mid = (left + right) / 2;
            
            long time = 0;
            for (int i = 0; i < diffs.length; ++i) {
                int diff = diffs[i];
                int puzzleTime = times[i];
                
                if (diff <= mid) {
                    time += puzzleTime;
                } else {
                    long wrongCount = diff - mid;
                    long prevTime = times[i - 1];
                    time += wrongCount * (puzzleTime + prevTime) + puzzleTime;                    
                }
            }
            
            if (time <= limit) {
                right = mid - 1;
                min = Math.min(min, mid);
            } else {
                left = mid + 1;
            }
        }
        
        answer = min;
        return answer;
    }
}
