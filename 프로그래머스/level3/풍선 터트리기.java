// priorityQueue를 이용하여 처음에 뒤에서부터 값을 집어넣고 현재 pq의 peek값보다 큰 값은 집어넣지 않으면 오른쪽에 작은 값들을 순서대로 담을 수 있다
// 그리고 처음에 left에는 최솟값만을 유지하고 a배열을 1인덱스부터 시작해서 순차적으로 가져오고 만약 가져온 값이 right의 peek값(오른쪽에 있는 최솟값)과 같으면
// pq에서 빼고 다음 최솟값을 가져와서 비교하면 된다
// 비교는 left값과 right이 peek값 둘 중 하나라도 현재 가져온 a배열의 값보다 크면 그 풍선은 무조건 마지막에 남아있게 할 수 있다.
// 기본적인 원리는 어느 순으로 제거를 하든 찬스를 사용하지 않으면 내가 지정한 풍선의 왼쪽 풍선들중에서 가장 작은 값을 가진 풍선, 오른쪽에서 가장 작은 값을 가진 풍선만 남기 때문에
// 그 때 둘 중 하나라도 현재 기준보다 큰 값을 가진 풍선이 있으면 해당 풍선은 무조건 마지막에 남길 수 있다
// 그렇게 하면 TreeSet보다 빠르게 할 수 있다. TreeSet이 많이 느리다고 한다

import java.util.TreeSet;

class Solution {
    public int solution(int[] a) {
        int answer;
        if (a.length < 3) {
            return a.length;
        } else {
            answer = 1;
            TreeSet<Integer> left = new TreeSet<>();
            TreeSet<Integer> right = new TreeSet<>();
            left.add(a[0]);
            for (int i = 1; i < a.length; ++i) {
                right.add(a[i]);
            }

            for (int i = 1; i < a.length; ++i) {
                int num = a[i];

                boolean isLeftSmall = false;
                boolean isRightSmall = false;
                if (left.size() > 0) {
                    int prevNum = left.first();
                    if (prevNum < num) {
                        isLeftSmall = true;
                    }
                }

                if (right.size() > 0) {
                    int nextNum = right.first();

                    if (nextNum < num) {
                        isRightSmall = true;
                    }
                }

                if (!isLeftSmall || !isRightSmall) {
                    ++answer;
                }

                left.add(num);
                right.remove(num);
            }
        }

        return answer;
    }
}
