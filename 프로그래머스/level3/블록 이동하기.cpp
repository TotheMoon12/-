#include <string>
#include <vector>
#include <queue>

using namespace std;

enum state { NOT_VISITED, VISITED, WALL };
typedef struct Points
{
	int x1;
	int y1;
	int x2;
	int y2;
	int depth;
	Points(int x1, int y1, int x2, int y2, int depth);
}Points;
Points::Points(int x1, int y1, int x2, int y2, int depth = 0)
{
	Points::x1 = x1;
	Points::y1 = y1;
	Points::x2 = x2;
	Points::y2 = y2;
	Points::depth = depth;
}
bool Check(Points points,int size)
{
    if ((points.x1 == size - 1 && points.y1 == size - 1) || (points.x2 == size - 1 && points.y2 == size - 1))
	{
		return true;
	}

	return false;
}

int solution(vector<vector<int>> map) {
    int answer = 0;
    queue<Points> q;
	q.push(Points(0, 0, 0, 1));
	vector<int> result;
	const int SIZE = map.size();
	vector< vector<state> > horizonState;
	vector< vector<state> > verticalState;
	int count = 0;
	horizonState.assign(SIZE, vector<state>(SIZE, NOT_VISITED));
	verticalState.assign(SIZE, vector<state>(SIZE, NOT_VISITED));
	
	for (int i = 0; i < SIZE; ++i)
	{
		for (int j = 0; j < SIZE; ++j)
		{
			if (map[i][j] == 1)
			{
				horizonState[i][j] = WALL;
				verticalState[i][j] = WALL;
			}
		}
	}

	horizonState[0][0] = VISITED;
	horizonState[0][1] = VISITED;

	while (q.size() != 0)
	{
		Points currentPoints = q.front();
		q.pop();

		int x1 = currentPoints.x1;
		int y1 = currentPoints.y1;
		int x2 = currentPoints.x2;
		int y2 = currentPoints.y2;
		int depth = currentPoints.depth;

		/* 로봇 세로 방향 */
		if (currentPoints.y1 == currentPoints.y2)
		{
			/* 상 */
			if (x1 - 1 >= 0 && (verticalState[x1 - 1][y1]) != WALL && verticalState[x1 - 1][y1] == NOT_VISITED)
			{
				q.push(Points(x1 - 1, y1, x2 - 1, y2, depth + 1));
				verticalState[x1 - 1][y1] = VISITED;
				if (Check(Points(x1 - 1, y1, x2 - 1, y2, depth + 1), SIZE))
				{
					result.push_back(depth + 1);
				}
			}
			/* 하 */
			if (x2 + 1 < SIZE && verticalState[x2 + 1][y2] != WALL && verticalState[x2 + 1][y2] == NOT_VISITED)
			{
				q.push(Points(x1 + 1, y1, x2 + 1, y2, depth + 1));
				verticalState[x2 + 1][y2] = VISITED;
				if (Check(Points(x1 + 1, y1, x2 + 1, y2, depth + 1), SIZE))
				{
					result.push_back(depth + 1);
				}
			}
			/* 좌 */
			if (y1 - 1 >= 0 && (verticalState[x1][y1 - 1] != WALL && verticalState[x2][y2 - 1] != WALL)
				&& (verticalState[x1][y1 - 1] == NOT_VISITED && verticalState[x2][y2 - 1] == NOT_VISITED))
			{
				q.push(Points(x1, y1 - 1, x2, y2 - 1, depth + 1));
				verticalState[x1][y1 - 1] = VISITED;
				verticalState[x2][y2 - 1] = VISITED;
				if (Check(Points(x1, y1 - 1, x2, y2 - 1, depth + 1), SIZE))
				{
					result.push_back(depth + 1);
				}
			}
			/* 우 */
			if (y1 + 1 < SIZE && (verticalState[x1][y1 + 1] != WALL && verticalState[x2][y2 + 1] != WALL) &&
				(verticalState[x1][y1 + 1] == NOT_VISITED && verticalState[x2][y2 + 1] == NOT_VISITED))
			{
				q.push(Points(x1, y1 + 1, x2, y2 + 1, depth + 1));
				verticalState[x1][y1 + 1] = VISITED;
				verticalState[x2][y2 + 1] = VISITED;
				if (Check(Points(x1, y1 + 1, x2, y2 + 1, depth + 1), SIZE))
				{
					result.push_back(depth + 1);
				}
			}

			/* 왼쪽 회전 */
			if (y1 - 1 >= 0 && horizonState[x1][y1 - 1] != WALL && horizonState[x1 + 1][y1 - 1] != WALL)
			{
				if (horizonState[x1][y1 - 1] == NOT_VISITED)
				{
					q.push(Points(x1, y1 - 1, x1, y1, depth + 1));
					horizonState[x1][y1 - 1] = VISITED;
					horizonState[x1][y1] = VISITED;
					if (Check(Points(x1, y1 - 1, x1, y1, depth + 1), SIZE))
					{
						result.push_back(depth + 1);
					}
				}
				if (horizonState[x1 + 1][y1 - 1] == NOT_VISITED)
				{
					q.push(Points(x1 + 1, y1 - 1, x2, y2, depth + 1));
					horizonState[x1 + 1][y1 - 1] = VISITED;
					horizonState[x2][y2] = VISITED;
					if (Check(Points(x1 + 1, y1 - 1, x2, y2, depth + 1), SIZE))
					{
						result.push_back(depth + 1);
					}
				}
			}
			/* 오른쪽 회전 */
			if (y1 + 1 < SIZE && horizonState[x1][y1 + 1] != WALL && horizonState[x1 + 1][y1 + 1] != WALL)
			{
				if (horizonState[x1][y1 + 1] == NOT_VISITED)
				{
					q.push(Points(x1, y1, x1, y1 + 1, depth + 1));
					horizonState[x1][y1] = VISITED;
					horizonState[x1][y1 + 1] = VISITED;
					if (Check(Points(x1, y1, x1, y1 + 1, depth + 1), SIZE))
					{
						result.push_back(depth + 1);
					}
				}
				if (horizonState[x1 + 1][y1 + 1] == NOT_VISITED)
				{
					q.push(Points(x2, y2, x1 + 1, y1 + 1, depth + 1));
					horizonState[x1 + 1][y1 + 1] = VISITED;
					horizonState[x2][y2] = VISITED;
					if (Check(Points(x2, y2, x1 + 1, y1 + 1, depth + 1), SIZE))
					{
						result.push_back(depth + 1);
					}
				}
			}
		}
		else // 로봇 가로 모양
		{
			/* 상 */
			if (x1 - 1 >= 0 && (horizonState[x1 - 1][y1] != WALL && horizonState[x2 - 1][y2] != WALL) &&
				(horizonState[x1 - 1][y1] == NOT_VISITED || horizonState[x2 - 1][y2] == NOT_VISITED))
			{
				q.push(Points(x1 - 1, y1, x2 - 1, y2, depth + 1));
				horizonState[x1 - 1][y1] = VISITED;
				horizonState[x2 - 1][y2] = VISITED;
				if (Check(Points(x1 - 1, y1, x2 - 1, y2, depth + 1), SIZE))
				{
					result.push_back(depth + 1);
				}
			}
			/* 하 */
			if (x1 + 1 < SIZE && (horizonState[x1 + 1][y1] != WALL && horizonState[x2 + 1][y2] != WALL) &&
				(horizonState[x1 + 1][y1] == NOT_VISITED || horizonState[x2 + 1][y2] == NOT_VISITED))
			{
				q.push(Points(x1 + 1, y1, x2 + 1, y2, depth + 1));
				horizonState[x1 + 1][y1] = VISITED;
				horizonState[x2 + 1][y2] = VISITED;
				if (Check(Points(x1 + 1, y1, x2 + 1, y2, depth + 1), SIZE))
				{
					result.push_back(depth + 1);
				}
			}
			/* 좌 */
			if (y1 - 1 >= 0 && horizonState[x1][y1 - 1] != WALL && horizonState[x1][y1 - 1] == NOT_VISITED)
			{
				q.push(Points(x1, y1 - 1, x2, y2 - 1, depth + 1));
				horizonState[x1][y1 - 1] = VISITED;
				horizonState[x2][y2 - 1] = VISITED;
				if (Check(Points(x1, y1 - 1, x2, y2 - 1, depth + 1), SIZE))
				{
					result.push_back(depth + 1);
				}
			}
			/* 우 */
			if (y2 + 1 < SIZE && horizonState[x2][y2 + 1] != WALL && horizonState[x2][y2 + 1] == NOT_VISITED)
			{
				q.push(Points(x1, y1 + 1, x2, y2 + 1, depth + 1));
				horizonState[x1][y1 + 1] = VISITED;
				horizonState[x2][y2 + 1] = VISITED;
				if (Check(Points(x1, y1 + 1, x2, y2 + 1, depth + 1), SIZE))
				{
					result.push_back(depth + 1);
				}
			}

			/* 왼쪽 회전 */
			if (x1 - 1 >= 0 && verticalState[x1 - 1][y1] != WALL && verticalState[x2 - 1][y2] != WALL)
			{
				if (verticalState[x1 - 1][y1] == NOT_VISITED)
				{
					q.push(Points(x1 - 1, y1, x1, y1, depth + 1));
					verticalState[x1 - 1][y1] = VISITED;
					verticalState[x1][y1] = VISITED;
					if (Check(Points(x1 - 1, y1, x1, y1, depth + 1), SIZE))
					{
						result.push_back(depth + 1);
					}
				}
				if (verticalState[x2 - 1][y2] == NOT_VISITED)
				{
					q.push(Points(x2 - 1, y2, x2, y2, depth + 1));
					verticalState[x2 - 1][y2] = VISITED;
					verticalState[x2][y2] = VISITED;
					if (Check(Points(x2 - 1, y2, x2, y2, depth + 1), SIZE))
					{
						result.push_back(depth + 1);
					}
				}
			}
			/* 오른쪽 회전 */
			if (x1 + 1 < SIZE && verticalState[x1 + 1][y1] != WALL && verticalState[x2 + 1][y2] != WALL)
			{
				if (verticalState[x1 + 1][y1] == NOT_VISITED)
				{
					q.push(Points(x1, y1, x1 + 1, y1, depth + 1));
					verticalState[x1][y1] = VISITED;
					verticalState[x1 + 1][y1] = VISITED;
					if (Check(Points(x1, y1, x1 + 1, y1, depth + 1), SIZE))
					{
						result.push_back(depth + 1);
					}
				}
				if (verticalState[x2 + 1][y2] == NOT_VISITED)
				{
					q.push(Points(x2, y2, x2 + 1, y2, depth + 1));
					verticalState[x2][y2] = VISITED;
					verticalState[x2 + 1][y2] = VISITED;
					if (Check(Points(x2, y2, x2 + 1, y2, depth + 1), SIZE))
					{
						result.push_back(depth + 1);
					}
				}
			}
		}
		++count;
	}

	if (result.size() != 0)
	{
		answer = result.front();
		for (int i = 0; i < result.size(); ++i)
		{
			if (answer > result.at(i))
			{
				answer = result.at(i);
			}
		}
		return answer;
	}

	return -1;
}
