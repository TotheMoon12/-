// 경우의 수는 총 3가지만 가능하다
// 첫번째가 포함되는 경우
// 두번째가 포함되는 경우
// 첫번째, 두번째가 모두 포함되지 않는 경우
// 그래서 첫번째가 포함될 경우 두번째를 0으로 하고
// 두번째가 포함될 경우 첫 번째를 0으로
// 둘 다 포함되지 않을 경우 , 둘을 모두 0으로 둔 채 끝까지 동적계획법을 통해 누적시킨다
// 그러면 모든 dp에 저장된 값 중 최댓값이 정답이 된다

class Solution {
    public int solution(int[] money) {
        int max = 0;
        int[] dp = new int[money.length];
        dp[0] = money[0];
        dp[1] = 0;
        for (int i = 0; i < money.length; ++i) {
            if (i + 2 < money.length - 1) {
                dp[i + 2] = Math.max(dp[i + 2], dp[i] + money[i + 2]);
            }

            if (i + 3 < money.length - 1) {
                dp[i + 3] = Math.max(dp[i + 3], dp[i] + money[i + 3]);
            }
        }

        int[] dp2 = new int[money.length];
        dp2[0] = 0;
        dp2[1] = money[1];
        for (int i = 0; i < money.length; ++i) {
            if (i + 2 < money.length) {
                dp2[i + 2] = Math.max(dp2[i + 2], dp2[i] + money[i + 2]);
            }

            if (i + 3 < money.length) {
                dp2[i + 3] = Math.max(dp2[i + 3], dp2[i] + money[i + 3]);
            }
        }

        // 둘 다 포함되지 않는 경우는 두 번째가 포함되는 경우에서 체그되기 때문에 할 필요가 없다
//         int[] dp3 = new int[money.length];
//         dp3[0] = 0;
//         dp3[1] = 0;
//         for (int i = 0; i < money.length; ++i) {
//             if (i + 2 < money.length) {
//                 dp3[i + 2] = Math.max(dp3[i + 2], dp3[i] + money[i + 2]);
//             }

//             if (i + 3 < money.length) {
//                 dp3[i + 3] = Math.max(dp3[i + 3], dp3[i] + money[i + 3]);
//             }
//         }


        for (int i = 0; i < money.length; ++i) {
            max = Math.max(max, dp[i]);
            max = Math.max(max, dp2[i]);
//             max = Math.max(max, dp3[i]);
        }

        return max;
    }
}
