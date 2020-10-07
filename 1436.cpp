#include <iostream>

int main(void)
{
	int number;
	std::cin >> number;
	int answer = 666;

	for (int i = 2; i <= number; ++i)
	{
		answer += 1000;
		int maxTenUnit = 1;
		while (answer / maxTenUnit != 0)
		{
			maxTenUnit *= 10;
		}

		maxTenUnit = maxTenUnit / 10;

		int count = 0;
		int temp = answer;
		while (true)
		{
			if (temp / maxTenUnit == 6)
			{
				++count;
			}
			else
			{
				count = 0;
			}

			if (count == 3)
			{
				break;
			}

			temp = temp % maxTenUnit;
			maxTenUnit /= 10;
		}

		temp = answer;

		if (maxTenUnit > 1)
		{

			answer = (answer / maxTenUnit) * maxTenUnit;
			++i;
			int j = 1;
			for (; j < maxTenUnit; ++j)
			{
				if (i > number)
				{
					break;
				}
				++answer;
				++i;
			}

			if (i <= number)
			{
				answer = temp + 1000;
			}
		}
	}

	std::cout << answer << std::endl;
	return 0;
}
