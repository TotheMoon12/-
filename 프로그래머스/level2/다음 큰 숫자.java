class Solution {
    public int solution(int n) {
        int oneCount = 0;
        int nOneCount = getOneCountFromBinary(n);
        
        int biggerNum = n + 1;
        while (true) {
            int bigOneCount = getOneCountFromBinary(biggerNum);
            
            if (bigOneCount == nOneCount) {
                return biggerNum;
            }
            
            ++biggerNum;
        }
    }
    
    public int getOneCountFromBinary(int num) {
        int oneCount = 0;
        while (num > 0) {
            int remain = num % 2;
            if (remain == 1) {
                ++oneCount;
            }
            
            num /= 2;
        }
        
        return oneCount;
    }
}
