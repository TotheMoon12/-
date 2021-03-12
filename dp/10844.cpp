#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <string.h>

int main(void)
{
	/* 문제 : 백준 10844 */
	/* 풀이 : dp 알고리즘으로 푼다 */
	/*        마지막 자리의 수의 개수를 계속 저장해나가면서 계단 수의 개수를 구한다. */
	size_t i, j;
	int num;
	long long dp[2][10] = {
		{0,1,1,1,1,1,1,1,1,1},
		{0,0,0,0,0,0,0,0,0,0}
	};

	int count = 0;
	long long answer = 0;

	scanf("%d", &num);
	for (i = 1; i < num; ++i)
	{

		count = (count + 1) % 2;
		memset(dp[count], 0, sizeof(long long) * 10);

		/* 내 방식 */
		for (j = 0; j < 10; ++j)
		{
			if (j == 0)
			{
				dp[count][1] += dp[(count + 1) % 2][0];
			}
			else if (j == 9)
			{
				dp[count][8] += dp[(count + 1) % 2][9];
			}
			else
			{
				dp[count][j - 1] += dp[(count + 1) % 2][j];
				dp[count][j + 1] += dp[(count + 1) % 2][j];
			}
		}
		/* 다른 분의 방식으로 위 for문 코드는 1에서 8까지 돌게하고 0과 9는 for문 바깥으로 빼내서 조건문을 없애서 개선할 수 있다.*/
	}

	for (i = 0; i < 10; ++i)
	{
		answer += dp[count][i];
		answer %= 1000000000;

	}
	printf("%ld", answer);

	return 0;
}
