#include <iostream>
using namespace std;

int main(void)
{
	int deliveryWeight;
	int max_3;
	int max_5;
	cin >> deliveryWeight;

	max_3 = deliveryWeight / 3 + 1;
	max_5 = deliveryWeight / 5 + 1;

	int answer = -1;


	for (int bag3 = 0; bag3 <= max_3; ++bag3)
	{
		for (int bag5 = 0; bag5 <= max_5; ++bag5)
		{
			if ((3 * bag3 + 5 * bag5) == deliveryWeight && (answer == -1 || bag3 + bag5 < answer))
			{
				answer = bag3 + bag5;
			}
		}
	}

	cout << answer;

	return 0;
}
