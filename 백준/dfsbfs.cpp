#include <iostream>
#include <vector>
#include <queue>
using namespace std;

enum eState
{
	UNVISITED,
	VISITED
};

void PrintDfsPath(vector<vector<int>> map, vector<int>& state, int startingNode);
void PrintDfsPathByStack(vector<vector<int>> map, vector<int>& state, int startingNode);
void PrintBfsPath(vector<vector<int>> map, vector<int>& state, int startingNode);

int main(void)
{
	int nodeCount;
	int edgeCount;
	int startingNode;

	cin >> nodeCount;
	cin >> edgeCount;
	cin >> startingNode;
	vector<vector <int>> map(nodeCount, vector<int>(nodeCount));
	vector<int> dfsState(nodeCount);
	vector<int> dfsStackState(nodeCount);
	vector<int> bfsState(nodeCount);

	int node1;
	int node2;
	for (int i = 0; i < edgeCount; ++i)
	{
		cin >> node1;
		cin >> node2;
		map[node1 - 1][node2 - 1] = 1;
		map[node2 - 1][node1 - 1] = 1;
	}
	readFile.close();

	PrintDfsPath(map, dfsState, startingNode - 1);
	cout << endl;
	PrintDfsPathByStack(map, dfsStackState, startingNode - 1);
	cout << endl;
	PrintBfsPath(map, bfsState, startingNode - 1);
	//cout << "result " << state[0];
	return 0;
}

void PrintDfsPath(vector<vector<int>> map, vector<int>& state, int startingNode)
{
	if (state[startingNode] == UNVISITED)
	{
		state[startingNode] = VISITED;
		cout << startingNode + 1 << " ";
	}

	const int SIZE = state.size();
	for (int i = 0; i < SIZE; ++i)
	{
		if (map[startingNode][i] == 1 && state[i] == UNVISITED)
		{
			PrintDfsPath(map, state, i);
		}
	}
}

void PrintDfsPathByStack(vector<vector<int>> map, vector<int>& state, int startingNode)
{
	vector<int> stack;
	stack.push_back(startingNode);
	state[startingNode] = VISITED;
	cout << startingNode + 1 << " ";
	while (!stack.empty())
	{
		int last = stack.back();
		int i = 0;
		for (; i < state.size(); ++i)
		{
			if (map[last][i] == 1 && state[i] == UNVISITED)
			{
				cout << i + 1 << " ";
				state[i] = VISITED;
				stack.push_back(i);
				break;
			}
		}

		if (i == state.size())
		{
			stack.pop_back();
		}
	}
}

void PrintBfsPath(vector<vector<int>> map, vector<int>& state, int startingNode)
{
	queue<int> que;
	que.push(startingNode);
	state[startingNode] = VISITED;

	while (!que.empty())
	{
		int current = que.front();
		que.pop();
		cout << current + 1 << " ";

		for (int i = 0; i < state.size(); ++i)
		{
			if (map[current][i] == 1 && state[i] == UNVISITED)
			{
				state[i] = VISITED;
				que.push(i);
			}
		}
	}
}
