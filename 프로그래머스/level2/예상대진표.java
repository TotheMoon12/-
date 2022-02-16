class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 1;

        int min = Math.min(a,b);
        int max = Math.max(a,b);
        while (max - min != 1 || max % 2 != 0) {
            if (max % 2 == 0) {
                max /= 2;
            } else {
                max = (max + 1) / 2;
            }
            
            if (min % 2 == 0) {
                min /= 2;
            } else {
                min = (min + 1) / 2;
            }
            
            ++answer;
        }

        return answer;
    }
}
