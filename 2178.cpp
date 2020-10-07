#include <iostream>
#include <vector>
#include <queue>
using namespace std;

enum
{
	WALL,
	NOT_VISITED,
	VISITED
};

struct Point
{
	int Row;
	int Col;
	int Depth;
	Point();
	Point(int row, int col, int depth);
};

Point::Point(int row, int col, int depth)
{
	Row = row;
	Col = col;
	Depth = depth;
}

bool Check(vector<vector<int>>& state, int row, int col);
int GetMovingMinCountByBFS(vector<vector<int>> map);

int main(void)
{
	int row, col;
	cin >> row;
	cin >> col;

	vector<vector<int> > map(row, vector <int>(col));

	for (int i = 0; i < row; ++i)
	{
		for (int j = 0; j < col; ++j)
		{
			char temp;
			cin >> temp;
			map[i][j] = temp - '0';
		}
	}
    
	int result = GetMovingMinCountByBFS(map);
	cout << result;

	return 0;
}

bool Check(vector<vector<int>>& state, int row, int col)
{
	if (state[row][col] == NOT_VISITED)
	{
		return true;
	}
	else
	{
		return false;
	}
}

int GetMovingMinCountByBFS(vector<vector<int>> map)
{
	queue<Point> que;
	que.push(Point(0, 0, 1));
	const int ROW_SIZE = map.size();
	const int COL_SIZE = map[0].size();

	while (!que.empty())
	{
		Point current = que.front();
		que.pop();
        
		if (current.Row == ROW_SIZE - 1 && current.Col == COL_SIZE - 1)
		{
			return current.Depth;
		}
        
		if (current.Row + 1 < ROW_SIZE && Check(map, current.Row + 1, current.Col))
		{
			map[current.Row + 1][current.Col] = VISITED;
			que.push(Point(current.Row + 1, current.Col, current.Depth + 1));
		}
        
		if (current.Row - 1 >= 0 && Check(map, current.Row - 1, current.Col))
		{
			map[current.Row - 1][current.Col] = VISITED;
			que.push(Point(current.Row - 1, current.Col, current.Depth + 1));
		}
        
		if(current.Col + 1 < COL_SIZE && Check(map, current.Row, current.Col + 1))
		{
			map[current.Row][current.Col + 1] = VISITED;
			que.push(Point(current.Row, current.Col + 1, current.Depth + 1));
		}
        
		if (current.Col - 1 >= 0 && Check(map, current.Row, current.Col - 1))
		{
			map[current.Row][current.Col - 1] = VISITED;
			que.push(Point(current.Row, current.Col - 1, current.Depth + 1));
		}		
	}
}
