import java.util.HashMap;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] topping = {1, 2, 1, 3, 1, 4, 1, 2};
        int answer = solution.solution(topping);
        System.out.println(answer);
    }

    public int solution(int[] topping) {
        int answer = 0;
        HashMap<Integer, Integer> brother = new HashMap<>();
        HashMap<Integer, Integer> youngBrother = new HashMap<>();

        brother.put(topping[0], 1);
        for (int idx = 1; idx < topping.length; ++idx) {
            int type = topping[idx];
            youngBrother.put(type, youngBrother.getOrDefault(type, 0) + 1);
        }

        if (brother.keySet().size() == youngBrother.keySet().size()) {
            ++answer;
        }

        for (int idx = 1; idx < topping.length; ++idx) {
            int type = topping[idx];
            int count = youngBrother.get(type);
            if (count == 1) {
                youngBrother.remove(type);
            } else {
                youngBrother.put(type, count - 1);
            }

            brother.put(type, 1);

            if (brother.keySet().size() == youngBrother.keySet().size()) {
                ++answer;
            }
        }

        return answer;
    }
}
