#!/usr/bin/env python3

# Node class to represent data contained 
# within a doubly linked list
class Node():
  
  data, prev = None, None
  
  def __init__(self, data, prev):
    self.data = data
    self.prev = prev
  
  def __str__(self):
    return str(self.data)

class Stack():
  
  head, trav, size = None, None, 0
  
  def is_empty(self):
    return self.size == 0
  
  # Push an element on the stack
  def push(self, elem):
    if self.is_empty():
      self.head = Node(elem, None)
    else:
      new_head = Node(elem, self.head)
      self.head = new_head
    self.size += 1
  
  # Pop an element off the stack
  def pop(self):
    if self.is_empty(): raise RuntimeError("Empty stack!")
    data = self.head.data
    self.head.data = None
    self.head = self.head.prev
    self.size -= 1
    return data
  
  # Peek the top of the stack without removing an element
  def peek(self):
    if self.is_empty(): raise RuntimeError("Empty stack!")
    return self.head.data
  
  def __len__(self):
    return self.size
  
  def __iter__(self):
    self.trav = self.head
    return self
  
  def __next__(self):
    if self.trav == None: raise StopIteration()
    data = self.trav.data
    self.trav = self.trav.prev
    return data

  def __str__(self):
    s = ""
    trav = self.head
    while trav != None:
      if trav == self.head:
        s = str(trav.data) 
      else:
        s = str(trav.data) + ", " + s 
      trav = trav.prev
    return s

