/* 라인해설사이트) https://engineering.linecorp.com/ko/blog/2019-firsthalf-line-internship-recruit-coding-test/ */
/* 2019년 상반기 LINE 인턴 채용 코딩테스트 문제 기출*/
/* 문제의 풀이방식은 bfs */
/* 이 문제의 풀이 알고리즘이 bfs라는 것을 보기전에는 알지 못했다.
   bfs라는 것을 보고 난 이후에 한참 고민하고 bfs 문제라는 것을 이해했다.
   brown이 이동할 수 있는 경로는 3가지로 정해져있으므로 이 3가지 경우를 큐에 넣으면서 제일 빨리 coni의 위치와 같게 되는 것을 구하면 됐었다.
   아직 이해가 가지 않는 것은 visited를 사용해야 하는 것이다.
   풀이에서는 짝수, 홀수로 나눠서 visited를 체크하는데 짝수, 홀수시간을 사용한 것은 이해가 가나
   어쨋든 중복은 일어날 수 있으니 굳이 짝수,홀수시간을 사용하지 않고 즉, visited를 체크하지 않으면 되지 않을까라는 생각
   => 홀수/짝수시간에 이미 방문했던 곳은 무슨 일이 있어도 나중에 이전에 해당 홀수/짝수시간에 방문했던 것보다 최종 cony에게 도달하는 것이 더 빠를 수가 없다는 뜻인데
      반드시 그럴수가 있는지 의문
*/

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
