#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>

int main(void)
{
	/*
	* 백준 1463번
	* 문제 : 어떤 정수가 주어졌을 때 다음의 세가지 연산을 사용하여 1로 만들 때 연산을 사용하는 횟수의 최솟값 구하기
	*        정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.
			1. X가 3으로 나누어 떨어지면, 3으로 나눈다.
			2. X가 2로 나누어 떨어지면, 2로 나눈다.
			3. 1을 뺀다.
	* 해결방안 : 동적계획법을 통하여 bottom-up 방식으로 이전의 연산 횟수를 저장하여 최솟값을 구한다.
	*/

	int number;
	scanf("%d", &number);
	int* memoization = new int[number + 1];

	memoization[1] = 0;
	memoization[2] = 1;
	memoization[3] = 1;

	for (int i = 4; i <= number; ++i)
	{
		if (i % 3 == 0 && i % 2 == 0) // 3과 2로 모두 나누어떨어지는 경우 => 세가지 연산 중에 어느 것이 더 최소의 길인지 찾는다.
		{
			int min;
			if (memoization[i / 3] <= memoization[i / 2]) 
			{
				min = memoization[i / 3];
			}
			else
			{
				min = memoization[i / 2];
			}

			if (memoization[i - 1] < min)
			{
				min = memoization[i - 1];
			}

			memoization[i] = min + 1;
		}
		else if (i % 3 == 0)
		{
			if (memoization[i / 3] <= memoization[i - 1])
			{
				memoization[i] = memoization[i / 3] + 1;
			}
			else
			{
				memoization[i] = memoization[i - 1] + 1;
			}
		}
		else if (i % 2 == 0)
		{
			if (memoization[i / 2] <= memoization[i - 1])
			{
				memoization[i] = memoization[i / 2] + 1;
			}
			else
			{
				memoization[i] = memoization[i - 1] + 1;
			}
		}
		else
		{
			memoization[i] = memoization[i - 1] + 1;
		}
	}

	printf("%d", memoization[number]);

	return 0;
}
