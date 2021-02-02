#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>

int main(void)
{
	/*
	* 백준 9465번
	* 풀이 : 동적계획법을 통하여 해당 스티커를 기준으로 최대점수값으로 만드는 이전 열의 값을 비교하여 구한다.
	*        두 번째 열의 점수는 무조건 대각방향의 첫 번째 열의 스티커 점수를 누적하면 된다.(대각방향외에 없으므로)
	*        그 이후로는 점수들이 누적되어 있기 때문에 현재위치를 row, col이라고하면 row-1(row가 1인 경우, row가 0이면 row +1)에 col-1과 col-2에
	*        기록된 값을 비교하면 된다. 
	*        => 무조건 대각으로 누적하는 것이 최대값이 아닐 수 있기 때문에 두 개전의 열의 값을 비교하여 구한다.
	*/
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

		if (n > 1) // 두 번째 열의 스티커는 무조건 대각방향의 첫 번째 열의 점수를 누적하면 된다
		{
			sticker[0][1] += sticker[1][0];
			sticker[1][1] += sticker[0][0];
		}

		for (int col = 2; col < n; ++col)
		{
			for (int row = 0; row < 2; ++row)
			{
				// 누적해놓은 대각방향의 2개전까지의 열의 점수값을 비교하여 큰 값을 누적한다.
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
