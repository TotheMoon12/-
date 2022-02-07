#include <stdio.h>
#include <queue>

using namespace std;

#define INF 100000000

enum
{
	NOT_VISITED,
	WALL,
	VISITED
};

enum
{
	NOT_DRIL,
	DRIL
};

struct Point
{
	int Row;
	int Col;
	int Depth;
	bool Dril;
	Point(int row, int col, int  depth, bool dril);
};

Point::Point(int row, int col, int depth, bool dril)
{
	Row = row;
	Col = col;
	Depth = depth;
	Dril = dril;
}

int main(void)
{
	int n, m;
    	scanf("%d %d", &n, &m);
    
	int map[1000][1000][2];
	int depth[1000][1000][2];
	char temp;

	for (int i = 0; i < n; ++i)
	{
		for (int j = 0; j < m; ++j)
		{
			scanf(" %c", &temp);
			map[i][j][0] = temp - '0';
			map[i][j][1] = temp - '0';
		}
	}

	queue<Point> que;
	que.push(Point(0, 0, 1, false));
	map[0][0][0] = VISITED;
	map[0][0][1] = VISITED;
	depth[n - 1][m - 1][NOT_DRIL] = INF;
	depth[n - 1][m - 1][DRIL] = INF;

	while (!que.empty())
	{
		Point current = que.front();
		que.pop();
		int moveDepth = current.Depth + 1;
		int drilDepth = current.Depth + 2;
		if (current.Row - 1 >= 0)
		{
			if (map[current.Row - 1][current.Col][current.Dril] == NOT_VISITED)
			{
				que.push(Point(current.Row - 1, current.Col, moveDepth, current.Dril));
				map[current.Row - 1][current.Col][current.Dril] = VISITED;
				depth[current.Row - 1][current.Col][current.Dril] = moveDepth;
			}
			else if (map[current.Row - 1][current.Col][current.Dril] == WALL && current.Dril == NOT_DRIL)
			{
				if (current.Row - 2 >= 0)
				{
					if (map[current.Row - 2][current.Col][DRIL] == NOT_VISITED)
					{
						que.push(Point(current.Row - 2, current.Col, drilDepth, DRIL));
						map[current.Row - 2][current.Col][DRIL] = VISITED;
						depth[current.Row - 2][current.Col][DRIL] = drilDepth;
					}
					else if (map[current.Row - 2][current.Col][DRIL] == VISITED && drilDepth < depth[current.Row - 2][current.Col][DRIL])
					{
						que.push(Point(current.Row - 2, current.Col, drilDepth, DRIL));
						depth[current.Row - 2][current.Col][DRIL] = drilDepth;
					}
				}

				if (current.Col - 1 >= 0)
				{
					if (map[current.Row - 1][current.Col - 1][DRIL] == NOT_VISITED)
					{
						que.push(Point(current.Row - 1, current.Col - 1, drilDepth, DRIL));
						map[current.Row - 1][current.Col - 1][DRIL] = VISITED;
						depth[current.Row - 1][current.Col - 1][DRIL] = drilDepth;
					}
					else if (map[current.Row - 1][current.Col - 1][DRIL] == VISITED && drilDepth < depth[current.Row - 1][current.Col - 1][DRIL])
					{
						que.push(Point(current.Row - 1, current.Col - 1, drilDepth, DRIL));
						depth[current.Row - 1][current.Col - 1][DRIL] = drilDepth;
					}
				}

				if (current.Col + 1 < m)
				{
					if (map[current.Row - 1][current.Col + 1][DRIL] == NOT_VISITED)
					{
						que.push(Point(current.Row - 1, current.Col + 1, drilDepth, DRIL));
						map[current.Row - 1][current.Col + 1][DRIL] = VISITED;
						depth[current.Row - 1][current.Col + 1][DRIL] = drilDepth;
					}
					else if (map[current.Row - 1][current.Col + 1][DRIL] == VISITED && drilDepth < depth[current.Row - 1][current.Col + 1][DRIL])
					{
						que.push(Point(current.Row - 1, current.Col + 1, drilDepth, DRIL));
						depth[current.Row - 1][current.Col + 1][DRIL] = drilDepth;
					}
				}
			}
		}

		if (current.Row + 1 < n)
		{
			if (map[current.Row + 1][current.Col][current.Dril] == NOT_VISITED)
			{
				que.push(Point(current.Row + 1, current.Col, moveDepth, current.Dril));
				map[current.Row + 1][current.Col][current.Dril] = VISITED;
				depth[current.Row + 1][current.Col][current.Dril] = moveDepth;
			}
			else if (map[current.Row + 1][current.Col][current.Dril] == WALL && current.Dril == NOT_DRIL)
			{
				if (current.Row + 2 < n)
				{
					if (map[current.Row + 2][current.Col][DRIL] == NOT_VISITED)
					{
						que.push(Point(current.Row + 2, current.Col, drilDepth, DRIL));
						map[current.Row + 2][current.Col][DRIL] = VISITED;
						depth[current.Row + 2][current.Col][DRIL] = drilDepth;
					}
					else if (map[current.Row + 2][current.Col][DRIL] == VISITED && drilDepth < depth[current.Row + 2][current.Col][DRIL])
					{
						que.push(Point(current.Row + 2, current.Col, drilDepth, DRIL));
						depth[current.Row + 2][current.Col][DRIL] = drilDepth;
					}
				}

				if (current.Col - 1 >= 0)
				{
					if (map[current.Row + 1][current.Col - 1][DRIL] == NOT_VISITED)
					{
						que.push(Point(current.Row + 1, current.Col - 1, drilDepth, DRIL));
						map[current.Row + 1][current.Col - 1][DRIL] = VISITED;
						depth[current.Row + 1][current.Col - 1][DRIL] = drilDepth;
					}
					else if (map[current.Row + 1][current.Col - 1][DRIL] == VISITED && drilDepth < depth[current.Row + 1][current.Col - 1][DRIL])
					{
						que.push(Point(current.Row + 1, current.Col - 1, drilDepth, DRIL));
						depth[current.Row + 1][current.Col - 1][DRIL] = drilDepth;
					}
				}

				if (current.Col + 1 < m)
				{
					if (map[current.Row + 1][current.Col + 1][DRIL] == NOT_VISITED)
					{
						que.push(Point(current.Row + 1, current.Col + 1, drilDepth, DRIL));
						map[current.Row + 1][current.Col + 1][DRIL] = VISITED;
						depth[current.Row + 1][current.Col + 1][DRIL] = drilDepth;
					}
					else if (map[current.Row + 1][current.Col + 1][DRIL] == VISITED && drilDepth < depth[current.Row + 1][current.Col + 1][DRIL])
					{
						que.push(Point(current.Row + 1, current.Col + 1, drilDepth, DRIL));
						depth[current.Row + 1][current.Col + 1][DRIL] = drilDepth;
					}
				}
			}
		}

		if (current.Col - 1 >= 0)
		{
			if (map[current.Row][current.Col - 1][current.Dril] == NOT_VISITED)
			{
				que.push(Point(current.Row, current.Col - 1, moveDepth, current.Dril));
				map[current.Row][current.Col - 1][current.Dril] = VISITED;
				depth[current.Row][current.Col - 1][current.Dril] = moveDepth;
			}
			else if (map[current.Row][current.Col - 1][current.Dril] == WALL && current.Dril == NOT_DRIL)
			{
				if (current.Col - 2 >= 0)
				{
					if (map[current.Row][current.Col - 2][DRIL] == NOT_VISITED)
					{
						que.push(Point(current.Row, current.Col - 2, drilDepth, DRIL));
						map[current.Row][current.Col - 2][DRIL] = VISITED;
						depth[current.Row][current.Col - 2][DRIL] = drilDepth;
					}
					else if (map[current.Row][current.Col - 2][DRIL] == VISITED && drilDepth < depth[current.Row][current.Col - 2][DRIL])
					{
						que.push(Point(current.Row, current.Col - 2, drilDepth, DRIL));
						depth[current.Row][current.Col - 2][DRIL] = drilDepth;
					}
				}

				if (current.Row - 1 >= 0)
				{
					if (map[current.Row - 1][current.Col - 1][DRIL] == NOT_VISITED)
					{
						que.push(Point(current.Row - 1, current.Col - 1, drilDepth, DRIL));
						map[current.Row - 1][current.Col - 1][DRIL] = VISITED;
						depth[current.Row - 1][current.Col - 1][DRIL] = drilDepth;
					}
					else if (map[current.Row - 1][current.Col - 1][DRIL] == VISITED && drilDepth < depth[current.Row - 1][current.Col - 1][DRIL])
					{
						que.push(Point(current.Row - 1, current.Col - 1, drilDepth, DRIL));
						depth[current.Row - 1][current.Col - 1][DRIL] = drilDepth;
					}
				}

				if (current.Row + 1 < n)
				{
					if (map[current.Row + 1][current.Col - 1][DRIL] == NOT_VISITED)
					{
						que.push(Point(current.Row + 1, current.Col - 1, drilDepth, DRIL));
						map[current.Row + 1][current.Col - 1][DRIL] = VISITED;
						depth[current.Row + 1][current.Col - 1][DRIL] = drilDepth;
					}
					else if (map[current.Row + 1][current.Col - 1][DRIL] == VISITED && drilDepth < depth[current.Row + 1][current.Col - 1][DRIL])
					{
						que.push(Point(current.Row + 1, current.Col - 1, drilDepth, DRIL));
						depth[current.Row + 1][current.Col - 1][DRIL] = drilDepth;
					}
				}
			}
		}

		if (current.Col + 1 < m)
		{
			if (map[current.Row][current.Col + 1][current.Dril] == NOT_VISITED)
			{
				que.push(Point(current.Row, current.Col + 1, moveDepth, current.Dril));
				map[current.Row][current.Col + 1][current.Dril] = VISITED;
				depth[current.Row][current.Col + 1][current.Dril] = moveDepth;
			}
			else if (map[current.Row][current.Col + 1][current.Dril] == WALL && current.Dril == NOT_DRIL)
			{
				if (current.Col + 2 < m)
				{
					if (map[current.Row][current.Col + 2][DRIL] == NOT_VISITED)
					{
						que.push(Point(current.Row, current.Col + 2, drilDepth, DRIL));
						map[current.Row][current.Col + 2][DRIL] = VISITED;
						depth[current.Row][current.Col + 2][DRIL] = drilDepth;
					}
					else if (map[current.Row][current.Col + 2][DRIL] == VISITED && drilDepth < depth[current.Row][current.Col + 2][DRIL])
					{
						que.push(Point(current.Row, current.Col + 2, drilDepth, DRIL));
						depth[current.Row][current.Col + 2][DRIL] = drilDepth;
					}
				}

				if (current.Row - 1 >= 0)
				{
					if (map[current.Row - 1][current.Col + 1][DRIL] == NOT_VISITED)
					{
						que.push(Point(current.Row - 1, current.Col + 1, drilDepth, DRIL));
						map[current.Row - 1][current.Col + 1][DRIL] = VISITED;
						depth[current.Row - 1][current.Col + 1][DRIL] = drilDepth;
					}
					else if (map[current.Row - 1][current.Col + 1][DRIL] == VISITED && drilDepth < depth[current.Row - 1][current.Col + 1][DRIL])
					{
						que.push(Point(current.Row - 1, current.Col + 1, drilDepth, DRIL));
						depth[current.Row - 1][current.Col + 1][DRIL] = drilDepth;
					}
				}

				if (current.Row + 1 < n)
				{
					if (map[current.Row + 1][current.Col + 1][DRIL] == NOT_VISITED)
					{
						que.push(Point(current.Row + 1, current.Col + 1, drilDepth, DRIL));
						map[current.Row + 1][current.Col + 1][DRIL] = VISITED;
						depth[current.Row + 1][current.Col + 1][DRIL] = drilDepth;
					}
					else if (map[current.Row + 1][current.Col + 1][DRIL] == VISITED && drilDepth < depth[current.Row + 1][current.Col + 1][DRIL])
					{
						que.push(Point(current.Row + 1, current.Col + 1, drilDepth, DRIL));
						depth[current.Row + 1][current.Col + 1][DRIL] = drilDepth;
					}
				}
			}
		}

	}

	if (n == 1 && m == 1)
	{
		printf("1");
	}
	else if (depth[n - 1][m - 1][NOT_DRIL] == INF && depth[n - 1][m - 1][DRIL] == INF)
	{
		printf("-1");
	}
	else
	{
		if (depth[n - 1][m - 1][NOT_DRIL] < depth[n - 1][m - 1][DRIL])
		{
			printf("%d", depth[n - 1][m - 1][NOT_DRIL]);
		}
		else
		{
			printf("%d", depth[n - 1][m - 1][DRIL]);
		}
	}

	return 0;
}
