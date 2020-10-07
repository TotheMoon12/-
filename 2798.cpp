// black jack
#include <iostream>
#include <string>
#include <sstream>
using namespace std;

int main(void)
{
	int cardCount,dealerNumber,result = 0;
	cin >> cardCount;cin >> dealerNumber;
	int* cards = new int[cardCount];
	for (int i = 0; i < cardCount; ++i)
	{
		cin >> cards[i];
	}

	for (int first = 0; first < cardCount-2; ++first)
		for (int second = first + 1; second < cardCount-1; ++second)
			for (int third = second + 1; third < cardCount; ++third)
            {
				int temp = cards[first] + cards[second] + cards[third];
				if (temp <= dealerNumber && temp > result)
					result = temp;
            }
	cout << result;
	return 0;
}
