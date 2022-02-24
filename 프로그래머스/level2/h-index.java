class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        int hIndex = 0;
        int paperCount = citations.length;
        int[] paperCountByCitation = new int [1001];
        for (int i = 0; i < paperCount; ++i) {
            if (citations[i] >= 1000) {
                ++paperCountByCitation[1000];
            } else {
                ++paperCountByCitation[citations[i]];
            }
        }
        
        int count = 0;
        for (int i = 1000; i >= 0; --i) {
            count += paperCountByCitation[i];
            
            if (count >= i) {
                answer = i;
                break;
            }
        }        
        
        return answer;
    }
}
