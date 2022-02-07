// sensor min length
// backjoon 2212
#include <iostream>

using namespace std;

int main(void)
{
	int N;
	int K;

	cin >> N;
	cin >> K;

	if (N <= K)
	{
		cout << "0";
		return 0;
	}

	int pos[10000];
	for (int i = 0; i < N; ++i)
	{
		cin >> pos[i];
	}

	int minIndex;
	for (int i = 0; i < N - 1; ++i)
	{
		minIndex = i;

		for (int j = i + 1; j < N; ++j)
		{
			if (pos[minIndex] > pos[j])
			{
				minIndex = j;
			}
		}

		int temp = pos[i];
		pos[i] = pos[minIndex];
		pos[minIndex] = temp;
	}

	int length[9999];
	int index = 0;
	int unduplicatedSensorCount = N;
	for (int i = 0; i < N - 1; ++i)
	{
		if (pos[i] == pos[i + 1])
		{
			--unduplicatedSensorCount;
			continue;
		}

		length[index++] = pos[i + 1] - pos[i];
	}

	int minLengthIndex;
	for (int i = 0; i < index - 1; ++i)
	{
		minLengthIndex = i;

		for (int j = i + 1; j < index; ++j)
		{
			if (length[minLengthIndex] > length[j])
			{
				minLengthIndex = j;
			}
		}

		int temp = length[i];
		length[i] = length[minLengthIndex];
		length[minLengthIndex] = temp;
	}

	if (unduplicatedSensorCount <= K)
	{
		cout << "0";
		return 0;
	}
	else
	{
		int count = unduplicatedSensorCount - K;
		int answer = 0;
		for (int i = 0; i < count; ++i)
		{
			answer += length[i];
		}

		cout << answer;
	}

	return 0;
}
