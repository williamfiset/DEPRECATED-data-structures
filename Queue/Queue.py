#!/usr/bin/env python3

# Node class to represent data contained 
# within a singly linked list
class Node():
  
  data, next_ = None, None
  
  def __init__(self, data, next_):
    self.data = data
    self.next_ = next_
  
  def __str__(self):
    return str(self.data)

class Queue():
  
  head, tail, size = None, None, 0
  
  # Checks if the queue is empty
  def is_empty(self):
    return self.size == 0
  
  # Add an element to the back of the queue
  def enqueue(self, elem):
    if self.is_empty():
      self.head = self.tail = Node(elem, None)
    else:
      self.tail.next_ = Node(elem, self.tail)
      self.tail = self.tail.next_
    self.size += 1
  
  # Remove an element from the front of the queue
  def dequeue(self):
    if self.is_empty(): raise RuntimeError("Queue empty")
    data = self.head.data
    self.head.data = None
    self.head = self.head.next_
    self.size -= 1
    if self.size == 0: self.tail = None
    return data
  
  # Peek the element at the front of the queue
  def peek(self):
    if self.is_empty(): raise RuntimeError("Queue empty")
    return self.head.data
  
  def __len__(self):
    return self.size



  