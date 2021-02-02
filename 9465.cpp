#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>

int main(void)
{
	int sticker[2][100000];
	int testCaseNum;
	scanf("%d", &testCaseNum);

	for (int i = 0; i < testCaseNum; ++i)
	{
		int n; // 총 스티커 개수는 2n개 2행  n열
		scanf("%d", &n);
		for (int row = 0; row < 2; ++row)
		{
			for (int col = 0; col < n; ++col)
			{
				scanf("%d", &sticker[row][col]);
			}
		}

		if (n > 1)
		{
			sticker[0][1] += sticker[1][0];
			sticker[1][1] += sticker[0][0];
		}

		for (int col = 2; col < n; ++col)
		{
			for (int row = 0; row < 2; ++row)
			{
				if (row == 0)
				{
					sticker[row][col] += sticker[1][col - 2] > sticker[1][col - 1] ? sticker[1][col - 2] : sticker[1][col - 1];
				}
				else
				{
					sticker[row][col] += sticker[0][col - 2] > sticker[0][col - 1] ? sticker[0][col - 2] : sticker[0][col - 1];
				}
			}
		}

		if (sticker[0][n - 1] > sticker[1][n - 1])
		{
			printf("%d\n", sticker[0][n - 1]);
		}
		else
		{
			printf("%d\n", sticker[1][n - 1]);
		}		
	}

	return 0;
}
