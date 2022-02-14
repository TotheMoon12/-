import java.util.ArrayList;

class Solution {
    static int answer;

    public int solution(String name) {
        ArrayList<Integer> fixIndexList = new ArrayList<>();
        int length = name.length();

        for (int i = 0; i < length; ++i) {
            if (name.charAt(i) != 'A') {
                fixIndexList.add(i);
            }
        }

        answer = name.length() * 27;
        int controlCount = 0;

        if (!fixIndexList.isEmpty() && fixIndexList.get(0) == 0) {
            fixIndexList.remove(0);
        }

        greedy(fixIndexList, 0, controlCount, name);

        return answer;
    }

    public static void greedy(ArrayList<Integer> fixIndexList, int cur, int controlCount, String name) {
        char c = name.charAt(cur);
        controlCount += Math.min(c - 'A', 'Z' - c + 1);

        if (fixIndexList.isEmpty()) {
            answer = Math.min(answer, controlCount);
            return;
        }

        int length = name.length();
        int firstIndex = fixIndexList.get(0);
        int lastIndex = fixIndexList.get(fixIndexList.size() - 1);

        int firstMove = Math.abs(firstIndex - cur);
        if (firstIndex > cur) {
            firstMove = Math.min(firstMove, length - firstIndex + cur);
        } else {
            firstMove = Math.min(firstMove, length - cur + firstIndex);
        }

        int lastMove = Math.abs(lastIndex - cur);
        if (lastIndex > cur) {
            lastMove = Math.min(lastMove, length - lastIndex + cur);
        } else {
            lastMove = Math.min(lastMove, length - cur + lastIndex);
        }

        if (fixIndexList.size() != 1) {
            fixIndexList.remove(0);
            greedy(fixIndexList, firstIndex, controlCount + firstMove, name);
            fixIndexList.add(0, firstIndex);
        }

        fixIndexList.remove(fixIndexList.size() - 1);
        greedy(fixIndexList, lastIndex, controlCount + lastMove, name);
        fixIndexList.add(lastIndex);
    }
}
