#pragma once
struct Point
{
	int Row;
	int Col;
	int Depth;
	Point();
	Point(int row, int col, int depth);
};

Point::Point()
{
	Row = 0;
	Col = 0;
	Depth = 0;
}

Point::Point(int row, int col, int depth)
{
	Row = row;
	Col = col;
	Depth = depth;
}
