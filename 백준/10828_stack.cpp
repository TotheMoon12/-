#include <iostream>
#include <cstring>
using namespace std;
#define STACK_MAX 10000

class Stack
{
public:
	Stack();
	~Stack() = default;
	void push(int data);
	void pop();
	void size() const;
	void empty() const;
	void top() const;

private:
	int mArr[STACK_MAX];
	int mTop;
};

int main(void)
{
	Stack stack;
	char cInput[6];
	int num;
	int count = 0;

	cin >> num;
	getchar();

	while (count < num)
	{
		cin >> cInput;

		if (strcmp(cInput, "push") == 0)
		{
			int data;
			cin >> data;

			stack.push(data);
		}
		else if (strcmp(cInput, "top") == 0)
		{
			stack.top();
		}
		else if (strcmp(cInput, "pop") == 0)
		{
			stack.pop();
		}
		else if (strcmp(cInput, "size") == 0)
		{
			stack.size();
		}
		else if (strcmp(cInput, "empty") == 0)
		{
			stack.empty();
		}
		count++;
	}
	return 0;
}

Stack::Stack() : mTop(-1)
{
	for (int i = 0; i < STACK_MAX; i++)
	{
		mArr[i] = -1;
	}
}
void Stack::push(int data)
{
	if (mTop == STACK_MAX)
	{
		cout << "stack is full" << endl;
	}
	else
	{
		mArr[++mTop] = data;		
	}	
}
void Stack::pop()
{
	if (mTop<0)
	{
		cout << "-1" << endl;
	}
	else
	{
		cout << mArr[mTop] << endl;
		mArr[mTop] = -1;
		mTop--;
	}
}
void Stack::size() const
{
	if (mTop < 0)
	{
		cout << 0 << endl;
	}
	else
	{
		cout << mTop+1 << endl;
	}	
}
void Stack::empty() const
{
	if (mTop < 0)
	{
		cout << "1" << endl;
	}
	else
	{
		cout << "0" << endl;
	}
}
void Stack::top() const
{
	if (mTop < 0)
	{
		cout << "-1" << endl;
	}
	else
	{
		cout << mArr[mTop] << endl;
	}
}
