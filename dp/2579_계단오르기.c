/* 문제: 백준 2579 계단오르기
*  풀이: 저번 포도주 시식문제와 같이 dp문제
*  단) 포도주와 달리 계단 오르기는 반드시 1칸 또는 두칸 건너서 올라가야하기 때문에
*  현재계단을 올라가지 않는 것을 고려할 필요가 없다.
*  저번과 동일하게 하면 세 칸을 건너띄는 경우가 생겨버린다.
*/

#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#define NUM 301

int main(void)
{
	size_t i, N;
	int stairs[NUM] = { 0, };
	int dp[NUM] = { 0, };

	scanf("%d", &N);
	for (i = 1; i <= N; ++i)
	{
		scanf("%d", &stairs[i]);
	}

	dp[0] = 0;
	dp[1] = stairs[1];
	dp[2] = stairs[1] + stairs[2];

	for (i = 3; i <= N; ++i)
	{
		if (stairs[i] + dp[i - 2] > stairs[i] + stairs[i - 1] + dp[i - 3])
		{
			dp[i] = stairs[i] + dp[i - 2];
		}
		else
		{
			dp[i] = stairs[i] + stairs[i - 1] + dp[i - 3];
		}
	}

	printf("%d", dp[N]);
	return 0;
}
