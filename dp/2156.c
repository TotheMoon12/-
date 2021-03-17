/* 문제 :백준 2156 */
/* 스스로 못 푼 문제 */
/* 풀이 : 본 블로그 : https://blog.naver.com/PostView.nhn?blogId=occidere&logNo=220791788953 */
/*  dp문제이고 너무 어렵게 생각하지 않는 것이 중요하지만 논리적으로 생각해야 한다 */
/*  3가지 경우가 있다
	1) 바로 이전의 포도주를 마시는 경우 => 2연속이 되기 때문에 전전 포도주를 마시지 않고 3개전까지의 최대값을 더한다. dp[i] = arr[i] + arr[i - 1] + dp[i - 3]
	2) 바로 이전의 포도주를 마시지 않는 경우 => 이전 포도주를 마시지 않으므로 전전까지의 최대값을 더한다. dp[i] = arr[i] + dp[i - 2]
	3) 현재를 마시지 않는 경우 => 오히려 이전 두개의 포도주를 마시는 것이 현재보다 더 큰 경우이다. dp[i] = dp[i - 1]

	max(1번,2번,3번)을 하는 것
*/

#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#define NUM 10001

int main(void)
{
	size_t i, N;
	int arr[NUM];
	int dp[NUM];
	
	scanf("%d", &N);

	for (i = 1; i <= N; ++i)
	{
		scanf("%d", &arr[i]);
	}

	dp[1] = arr[1];
	dp[2] = arr[1] + arr[2];

	for (i = 3; i <= N; ++i)
	{
		if (arr[i] + dp[i - 2] > arr[i] + arr[i - 1] + dp[i - 3])
		{
			dp[i] = arr[i] + dp[i - 2];
		}
		else
		{
			dp[i] = arr[i] + arr[i - 1] + dp[i - 3];
		}

		if (dp[i] < dp[i - 1])
		{
			dp[i] = dp[i - 1];
		}
	}

	printf("%d", dp[N]);
	return 0;
}
