#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <string.h>

int main(void)
{
	/* 문제 : 백준 11057 오르막수 */
	/* 풀이 : dp문제이고 n자리의 오름차순 수의 개수를 구하는 것이므로
	          마지막 자리수의 경우가 0~9로 이 각각의 수의 경우는
			  이전 n-1자리수에서 마지막수가 자신보다 작은 수의 경우를 모두 더한것가 같다.
			  따라서 점화식은 dp[i][j] = sum(dp[i-1][0~j])이다.
			  다른 경우로는 dp[i][j] = dp[i-1][j] + dp[i][j-1]이다.(단, j==1의 경우는 모두 1이다. 0앞에는 0밖에 올 수 없으므로)
			  주의) 문제에서 정답에서 10007의 나머지를 제출하라고 했는데
			        (A + B) mod C  = ((A mod C) + (B mod C)) mod C이므로 마지막에 한번더 mod연산을 해줘야 한다.
		 개선) 어차피 dp[i][j] = dp[i][j] + dp[i][j-1]이여서 굳이 배열을 2차원으로 만들필요없이 1차원배열로 크기 10자리로만 만들어도 가능하다.
	 */

	long long dp[1001][10] = { 0, };
	size_t i, j, N;
	long long sum;
	int result = 0;
	
	for (i = 0; i < 10; ++i)
	{
		dp[1][i] = 1;
	}

	scanf("%u", &N);

	for (i = 2; i <= N; ++i)
	{
		sum = 0;

		for (j = 0; j < 10; ++j)
		{
			sum += dp[i - 1][j] % 10007;
			dp[i][j] = sum;
		}
	}

	for (i = 0; i < 10; ++i)
	{
		result += dp[N][i] % 10007;
	}

	printf("%d", result % 10007);
}
