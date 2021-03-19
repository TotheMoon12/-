#include <iostream>
#include <vector>
#include <queue>
using namespace std;

typedef struct Info
{
	int coniPos;
	int brownPos;
	int time;
	Info(int _coniPos, int _brownPos, int _time);
}Info;

Info::Info(int _coniPos, int _brownPos, int _time)
{
	coniPos = _coniPos;
	brownPos = _brownPos;
	time = _time;
}

int bfs(int coniPos, int brownPos);

int main(void)
{
	int coniPos, brownPos;
	cin >> coniPos;
	cin >> brownPos;

	cout << bfs(coniPos, brownPos) << endl;
	return 0;
}

int bfs(int coniPos, int brownPos)
{
	int result;
	queue<Info> que;
	que.push(Info(coniPos, brownPos, 0));

	while (!que.empty())
	{
		Info info = que.front();
		que.pop();

		if (info.coniPos == info.brownPos)
		{
			return info.time;
		}

		int nextTime = info.time + 1;
		int nextConiPos = info.coniPos + nextTime;

		if (nextConiPos > 200000)
		{
			return -1;
		}

		int nextBrownPos[3] = { info.brownPos - 1, info.brownPos + 1 , info.brownPos * 2 };

		for (int i = 0; i < 3; ++i)
		{
			if (nextBrownPos[i] >= 0 && nextBrownPos[i] <= 200000)
			{
				que.push(Info(nextConiPos, nextBrownPos[i], nextTime));
			}
		}
	}

	return -1;
}
