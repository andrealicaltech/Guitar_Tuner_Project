package edu.caltech.cs2.datastructures;

import edu.caltech.cs2.interfaces.IFixedSizeQueue;

import java.util.Iterator;

public class CircularArrayFixedSizeQueue<E> implements IFixedSizeQueue<E> {
  private E[] data;
  private int idx;
  private int back; //essentially size

  public CircularArrayFixedSizeQueue(int capacity) {
    this.data = (E[]) new Object[capacity];
    this.idx = 0;
    this.back = 0;
  }

  @Override
  public boolean isFull() {
    return (capacity() == this.back);
  }

  @Override
  public int capacity() {
    return this.data.length;
  }

  @Override
  public boolean enqueue(E e) {//addBack
    if (!isFull()) {
      add(e);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public E dequeue() {//removeFront
    if (!this.isEmpty()) {
      int start = (capacity() + this.idx - this.back) % capacity();
      E front = this.data[start];
      this.data[start] = null;
      this.back--;
      return front;
    } else {
      return null;
    }
  }


  @Override
  public E peek() {
    if (!this.isEmpty()) {
      int start = (capacity() + this.idx - this.back) % capacity();
      return this.data[start];
    } else {
      return null;
    }
  }

  @Override
  public int size() {
    return this.back;
  }

  @Override
  public void add(E e) {
    if (!isFull()) {
      this.data[this.idx] = e;
      this.back++;
      this.idx = (this.idx+1) % capacity();
    }
  }

  @Override
  public void clear() {
    int start = (capacity() + this.idx - this.back) % capacity();

    for (int i = 0; i< this.back; i++) {
      this.data[start] = null;
      start = (start+1) % capacity();
    }
    this.back = 0;
  }

  public String toString() {
    if (this.isEmpty()) {
      return "[]";
    }
    String result = "[";
    int start = (capacity() + this.idx - this.back) % capacity();
    for (int i = 0; i < this.back; i++) {
      result += this.data[start] +", ";
      start = (start+1) % capacity();
    }
    result = result.substring(0, result.length() -2);
    return result + "]";
  }

  @Override
  public Iterator<E> iterator() {

    return new CircleDequeIterator();
  }

  private class CircleDequeIterator implements Iterator<E> {
    private int curr;
    private int counter;

    public CircleDequeIterator() {
      this.counter = 0;
      this.curr = (capacity() + idx - back) % capacity();
    }

    @Override
    public boolean hasNext() {
      return this.counter < back;
    }

    @Override
    public E next() {
      E toReturn = data[this.curr];
      this.curr = (this.curr +1) % capacity();
      this.counter++;
      return toReturn;
    }
  }//private class
}

