// 전체 카펫의 크기가 아닌 테투리 면적인 brown과 그 안의 면적인 yellow가 주어지기 때문에
// 전체 카펫의 면적은 brown + yellow가 되고 
// yellow가 무조건 1이상이기 때문에 전체 카펫의 가로, 세로 길이는 무조건 최소 3이상이여야 한다
// 그래서 카펫의 세로를 3으로 두고 전체면적을 세로변수로 나눈 값이 3이상이 될때 그리고 나누어 떨어질 때
// yellow 전체를 이루는 사각형의 가로, 세로는 전체 카펫의 가로, 세로보다 각각 2씩 작기 때문에
// 내가 지금 지정한 가로 세로에서 각각 2를 뺀 값을 곱한 것이 yellow와 같을 때의 가로, 세로가 카페의 가로, 세로가 된다
// 완전탐색, 즉 브루트포스를 통해서 푼다

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int totalArea = brown + yellow;
        for (int bx = 3; totalArea / bx >= 3; ++bx) {
            if (totalArea % bx == 0) {
                int by = totalArea / bx;
                
                if ((bx - 2) * (by - 2) == yellow) {
                    answer[0] = by;
                    answer[1] = bx;
                    break;
                }
            }
        }
        return answer;
    }
}
