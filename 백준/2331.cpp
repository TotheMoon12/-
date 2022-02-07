#include <stdio.h>

int pow(int number, int count)
{
	int result = number;
	for (int i = 1; i < count; ++i)
	{
		result *= number;
	}
	return result;
}

int main(void) {
	int* state = new int[1000000]();
	int A, P, answer;
	int count = 0;
	scanf("%d %d", &A, &P);

	state[A] = -1;
	while (true) {
		int temp = 0;
		int divider = 10;
		while (true)
		{
			int remainder = A % divider;
			if (A / divider == 0 && remainder == 0)
			{
				break;
			}
			temp += pow(remainder, P);
			A = (A - remainder) / 10;
		}
		A = temp;

		if (state[A] == -1)
		{
			answer = 0;
			break;
		}

		if (state[A] != 0 )
		{
			answer = state[A];
			break;
		}

		state[A] = ++count;
	}
	printf("%d",answer);
	return 0;
}
