#include <iostream>
#include "Queue.h"

//Queue::Queue()
//{
//	mQueue = new int[mCapacity];
//}
//Queue::~Queue()
//{
//	delete mQueue;
//}
//int Queue::GetSize() const
//{
//	return mSize;
//}
//
//void Queue::Push(int item)
//{
//	if (mSize < mCapacity)
//	{
//		mQueue[mSize++] = item;
//	}
//	else
//	{
//		int* newQueue = new int[mCapacity * 2];
//		mCapacity *= 2;
//		for (int i = 0; i < mSize; ++i)
//		{
//			newQueue[i] = mQueue[i];
//		}
//
//		newQueue[mSize++] = item;
//		delete mQueue;
//		mQueue = newQueue;
//	}
//}
//
//void Queue::Pop()
//{
//	if (mSize > 0)
//	{
//		--mSize;
//		int* newQueue = new int[mCapacity];
//		int* frontAddress = &mQueue[0];
//		memcpy(newQueue, mQueue + 1, mSize * sizeof(int));
//		delete mQueue;
//		mQueue = newQueue;
//	}
//}
//
//int Queue::GetFront() const
//{
//	if (mSize > 0)
//	{
//		return mQueue[0];
//	}
//	else
//	{
//		throw std::out_of_range("Queue Empty");
//	}
//}
//
//bool Queue::empty() const
//{
//	if (mSize == 0)
//	{
//		return true;
//	}
//	else
//	{
//		return false;
//	}
//}

template <class T>
Queue<T>::Queue()
{
	mQueue = new T[mCapacity];
}

template <class T>
Queue<T>::~Queue()
{
	delete mQueue;
}

template <class T>
int Queue<T>::GetSize() const
{
	return mSize;
}

template <class T>
void Queue<T>::Push(T item)
{
	if (mSize < mCapacity)
	{
		mQueue[mSize] = item;
		++mSize;
	}
	else
	{
		T* newQueue = new T[mCapacity * 2];
		mCapacity *= 2;
		for (int i = 0; i < mSize; ++i)
		{
			newQueue[i] = mQueue[i];
		}

		newQueue[mSize] = item;
		++mSize;
		delete mQueue;
		mQueue = newQueue;
	}
}


template <class T>
void Queue<T>::Pop()
{
	if (mSize > 0)
	{
		--mSize;
		T* newQueue = new T[mCapacity];
		//memcpy(newQueue, mQueue + 1, mSize * sizeof(T));
		for (int i = 0; i < mSize; ++i)
		{
			newQueue[i] = mQueue[i + 1];
		}

		delete mQueue;
		mQueue = newQueue;
	}
}

template <class T>
T Queue<T>::GetFront() const
{
	if (mSize > 0)
	{
		return mQueue[0];
	}
	else
	{
		throw std::out_of_range("Queue Empty");
	}
}

template <class T>
bool Queue<T>::empty() const
{
	if (mSize == 0)
	{
		return true;
	}
	else
	{
		return false;
	}
}
