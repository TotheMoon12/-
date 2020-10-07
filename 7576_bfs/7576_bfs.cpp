#include <iostream>
#include <fstream>
#include <queue>
#include "Point.h"
#include "queue.h"
#include "queue.cpp"

using namespace std;

enum EState
{
	NOT = -1,
	HALF_TOMATO,
	TOMATO
};

int main(void)
{
	ifstream readFile;
	readFile.open("input.txt");

	if (!readFile.is_open())
	{
		return 0;
	}

	int row, col;

	readFile >> col;
	readFile >> row;

	int** map = new int* [row];
	for (int i = 0; i < row; ++i)
	{
		map[i] = new int[col];
	}

	for (int i = 0; i < row; ++i)
	{
		for (int j = 0; j < col; ++j)
		{
			readFile >> map[i][j];
		}
	}

	readFile.close();

	Point** pointArr = new Point * [row];
	for (int i = 0; i < row; ++i)
	{
		pointArr[i] = new Point[col];
	}

	for (int i = 0; i < row; ++i)
	{
		for (int j = 0; j < col; ++j)
		{
			pointArr[i][j].Row = i;
			pointArr[i][j].Col = j;
		}
	}

	int max = 0;
	Queue<Point> que;
	for (int i = 0; i < row; ++i)
	{
		for (int j = 0; j < col; ++j)
		{
			if (map[i][j] == TOMATO)
			{	
				//que.push(pointArr[i][j]);
				que.Push(pointArr[i][j]);
			}
		}
	}

	while (!que.empty())
	{
		Point current = que.GetFront();
		que.Pop();
		//Point current = que.front();
		//que.pop();

		if (current.Row + 1 < row && map[current.Row + 1][current.Col] == HALF_TOMATO)
		{
			map[current.Row + 1][current.Col] = TOMATO;
			pointArr[current.Row + 1][current.Col].Depth = current.Depth + 1;
			que.Push(pointArr[current.Row + 1][current.Col]);
			//que.push(pointArr[current.Row + 1][current.Col]);
			//que.push(Point(current.Row + 1, current.Col, current.Depth + 1));
		}

		if (current.Row - 1 >= 0 && map[current.Row - 1][current.Col] == HALF_TOMATO)
		{
			map[current.Row - 1][current.Col] = TOMATO;
			pointArr[current.Row - 1][current.Col].Depth = current.Depth + 1;;
			que.Push(pointArr[current.Row - 1][current.Col]);
			//que.push(pointArr[current.Row - 1][current.Col]);
			//que.push(Point(current.Row - 1, current.Col, current.Depth + 1));
		}

		if (current.Col + 1 < col && map[current.Row][current.Col + 1] == HALF_TOMATO)
		{
			map[current.Row][current.Col + 1] = TOMATO;
			pointArr[current.Row][current.Col + 1].Depth = current.Depth + 1;;
			que.Push(pointArr[current.Row][current.Col + 1]);
			//que.push(pointArr[current.Row][current.Col + 1]);
			//que.push(Point(current.Row, current.Col + 1, current.Depth + 1));
		}

		if (current.Col - 1 >= 0 && map[current.Row][current.Col - 1] == HALF_TOMATO)
		{
			map[current.Row][current.Col - 1] = TOMATO;
			pointArr[current.Row][current.Col - 1].Depth = current.Depth + 1;;
			que.Push(pointArr[current.Row][current.Col - 1]);
			//que.push(pointArr[current.Row][current.Col - 1]);
			//que.push(Point(current.Row, current.Col - 1, current.Depth + 1));
		}
	}

	for (int i = 0; i < row; ++i)
	{
		for (int j = 0; j < col; ++j)
		{
			if (map[i][j] == HALF_TOMATO)
			{
				max = -1;
				break;
			}

			if (map[i][j] == TOMATO && max < pointArr[i][j].Depth)
			{
				max = pointArr[i][j].Depth;
			}
		}

		if (max == -1)
		{
			break;
		}
	}

	cout << max;

	return 0;
}
