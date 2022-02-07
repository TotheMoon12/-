#pragma warning(disable:4996)
#include <stdio.h>

using namespace std;

int main(void)
{
	int arr[1001];
	int count; // test case 수
	int size;  // 순열 길이
	const int VISITED = 0;
	scanf("%d", &count);

	for (int j = 0; j < count; ++j)
	{
		int answer = 0;
		scanf("%d", &size);

		for (int i = 1; i <= size; ++i)
		{
			scanf("%d", &arr[i]);
		}

		for (int i = 1; i <= size; ++i)
		{
			if (arr[i] != VISITED)
			{
				int next = i;
				while (arr[next] != i)
				{
					int temp = arr[next];
					arr[next] = VISITED;
					next = temp;
				}
				arr[next] = VISITED;
				++answer;
			}
		}
		printf("%d\n", answer);
	}

	return 0;
}
