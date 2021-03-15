/* 문제 : 백준 2193 N자리 이친수 구하기
          이친수는 이진수중에서 특징을 가지는 이진수로
          1로 시작하며 1이 연속으로 올 수 없다.
   풀이 : dp문제로 배열에 각 N자리의 이진수의 마지막 자리수가 
		  될 수 있는 0과 1의 개수를 저장하여 문제룰 푼다.
		  점화식은 마지막 자리가 0이면
		  dp[i][0] = dp[i - 1][0] + dp[i - 1][1]
		  1이면
		  dp[i][1] = dp[i - 1][0]
 */

#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int main(void)
{
	size_t N, i;
	long long dp[91][2] = { 0, };
	dp[1][1] = 1;

	scanf("%u", &N);

	for (i = 2; i <= N; ++i)
	{
		dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
		dp[i][1] = dp[i - 1][0];
	}

	printf("%lld\n", dp[N][0] + dp[N][1]);

	return 0;
}
