// A가 아닌 곳의 모든 인덱스를 리스트에 집어넣는다
// 어차피 현재 위치에서 제일 가까운 오른쪽 아니면 왼쪽으로 갔을 때 제일 가까운 왼쪽 둘 중 하나를 먼저 조작하기 때문에
// 제일 가까운 왼쪽 , 오른쪽 두 경우를 전부 재귀를 통해서 각 경우의 조작 횟수를 구하고 리스트에 든 것이 모두 없어졌을 때의 값으로 최솟값을 구한다
// 왼쪽을 지운 경우 오른쪽을 먼저 지우는 것이 좋을 수도 있기 때문에 백트래킹을 통해서 리스트에 다시 집어넣어서 재귀를 통해서 구현

// 개선점 : 문자열에서 , A가 연속으로 나타나는 구간들의 시작과 끝을 기록해서
//          왼쪽으로 왕복한 경우와 오른쪽으로 갔다가 돌아온 길이의 최솟값을 구하면 재귀를 할 필요없이 빠르게 구현가능
//          어차피 되돌아가는 것은 한 번 밖에 일어나지 않기 때문에 A구간을 통해서만 구할 수 있다!!!
           

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
