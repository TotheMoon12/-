class Solution {
    public int solution(int storey) {
        if (storey == 0) {
            return 0;
        }

        int answer = 0;
        int greater = 1;
        while (greater < storey) {
            greater *= 10;
        }

        int smaller = greater / 10;
        if (storey - smaller <= greater - storey) {
            answer = 1 + solution(storey - smaller);
        } else {
            answer = 1 + solution(greater - storey);
        }

        return answer;
    }
}
