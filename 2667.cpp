#include <iostream>
#include <queue>
using namespace std;

enum EState {
	WALL,
	NOT_VISITED,
	VISITED
};

struct Point {
	int Row;
	int Col;
	Point(int row, int col);
};

Point::Point(int row, int col)
{
	Row = row;
	Col = col;
}

int main(void)
{
	int countArr[313] = { 0 };
	int arrIndex = 0;
	int map[25][25];
	int n;

	cin >> n;
    
	for (int i = 0; i < n; ++i)
	{
		for (int j = 0; j < n; ++j)
		{
			char temp;
			cin >> temp;
			map[i][j] = temp - '0';
		}
	}

	for (int i = 0; i < n; ++i)
	{
		for (int j = 0; j < n; ++j)
		{
			if (map[i][j] == NOT_VISITED)
			{
				queue<Point> que;
				que.push(Point(i, j));
				map[i][j] = VISITED;
				++countArr[arrIndex];

				while (!que.empty())
				{
					Point current = que.front();
					que.pop();

					if (current.Row + 1 < n && map[current.Row + 1][current.Col] == NOT_VISITED)
					{
						map[current.Row + 1][current.Col] = VISITED;
						que.push(Point(current.Row + 1, current.Col));
						++countArr[arrIndex];
					}

					if (current.Row - 1 >= 0 && map[current.Row - 1][current.Col] == NOT_VISITED)
					{
						map[current.Row - 1][current.Col] = VISITED;
						que.push(Point(current.Row - 1, current.Col));
						++countArr[arrIndex];
					}

					if (current.Col + 1 < n && map[current.Row][current.Col + 1] == NOT_VISITED)
					{
						map[current.Row][current.Col + 1] = VISITED;
						que.push(Point(current.Row, current.Col + 1));
						++countArr[arrIndex];
					}

					if (current.Col - 1 >= 0 && map[current.Row][current.Col - 1] == NOT_VISITED)
					{
						map[current.Row][current.Col - 1] = VISITED;
						que.push(Point(current.Row, current.Col - 1));
						++countArr[arrIndex];
					}
				}

				++arrIndex;
			}
		}
	}

	int min;

	for (int i = 0; i < arrIndex - 1; ++i)
	{
        min = countArr[i];
        
		for (int j = i; j < arrIndex; ++j)
		{
			if (min > countArr[j])
			{
				min = countArr[j];
				countArr[j] = countArr[i];
				countArr[i] = min;
			}
		}
	}

	cout << arrIndex << endl;
	for (int i = 0; i < arrIndex; ++i)
	{
        if(i == arrIndex - 1)
        {
            cout << countArr[i];
        }
        else
        {
            cout << countArr[i] << endl;    
        }		
	}

	return 0;
}
