#pragma once
template <class T>
class Queue
{
private:
	int mSize = 0;
	int mCapacity = 1000;
	//int* mQueue;
	T* mQueue;
public:
	Queue();
	~Queue();
	T GetFront() const;
	int GetSize() const;
	//int GetFront() const;
	//void Push(int item);
	void Push(T item);
	void Pop();
	bool empty() const;
};
