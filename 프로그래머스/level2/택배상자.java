import java.util.Stack;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();

        int currentBox = 1;
        int orderIndex = 0;
        while (currentBox <= order.length) {
            int boxNumber = order[orderIndex];

            if (currentBox == boxNumber) {
                ++currentBox;
                ++orderIndex;
                ++answer;
            } else {
                if (!stack.isEmpty() && stack.peek() == boxNumber) {
                    ++orderIndex;
                    ++answer;
                    stack.pop();
                } else {
                    stack.push(currentBox++);
                }
            }
        }

        while (!stack.isEmpty() && stack.peek() == order[orderIndex]) {
            ++orderIndex;
            stack.pop();
            ++answer;
        }

        return answer;
    }
}
