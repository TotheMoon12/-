import java.util.ArrayList;

class Solution {
    public int[] solution(int n) {
        int max = (n * (n + 1)) / 2;
        int[] answer = new int[max];

        ArrayList<ArrayList<Integer>> triangle = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; ++j) {
                list.add(0);
            }
            triangle.add(list);
        }

        int value = 1;
        int plus = 0;
        for (int i = 0; i < n; i += 2) {
            for (int j = i; j < n - plus; ++j) {
                triangle.get(j).set(plus, value++);
            }

            if (n - 1 - plus > i) {
                for (int j = plus + 1; j < triangle.get(n - 1 - plus).size() - plus; ++j) {
                    triangle.get(n - 1 - plus).set(j, value++);
                }

                for (int j = n - plus - 2; j > i; --j) {
                    triangle.get(j).set(j - plus, value++);
                }
            }

            // 어차피 범위를 통해서 딱 맞게 돌기 때문에 비교문이 필요 없음
//             if (value > max) {
//                 break;
//             }

            ++plus;
        }

        int index = 0;
        for (int i = 0; i < n; ++i) {
            ArrayList<Integer> list = triangle.get(i);

            for (int number : list) {
                answer[index++] = number;
            }
        }

        return answer;
    }
}
