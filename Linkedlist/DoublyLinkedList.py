#!/usr/bin/env python3

# Node class to represent data contained 
# within a doubly linked list
class Node():
  
  data, prev, next_ = None, None, None
  
  def __init__(self, data, prev, next_):
    self.data = data
    self.prev = prev
    self.next_ = next_
  
  def __str__(self):
    return str(self.data)  

class DoublyLinkedList():
  
  size = 0
  head, tail = None, None
  
  def is_empty(self):
    return self.size == 0
  
  # Add a node to the tail of the linked list, O(1)
  def add(self, elem):
    self.add_last(elem)
  
  # Add a node to the tail of the linked list, O(1)
  def add_last(self, elem):
    if self.is_empty():
      self.head = self.tail = Node(elem, None, None)
    else:
      self.tail.next_ = Node(elem, self.tail, None)
      self.tail = self.tail.next_
    self.size += 1
  
  # Add an element to the beginning of this linked list, O(1)
  def add_first(self, elem):
    if self.is_empty():
      self.head = self.tail = Node(elem, None, None)
    else:
      self.head.prev = Node(elem, None, self.head)
      self.head = self.head.prev
    self.size += 1
  
  # Check the value of the first node if it exists, O(1)
  def peek_first(self):
    if self.is_empty(): raise RuntimeError("Empty linked list")
    return self.head.data
  
  # Check the value of the last node if it exists, O(1)
  def peek_last(self):
    if self.is_empty(): raise RuntimeError("Empty linked list")
    return self.tail.data
  
  # Remove the first value at the head of the linked list, O(1)
  def remove_first(self):
    
    # Can't remove data from an empty list -_-
    if self.is_empty(): raise RuntimeError("Empty linked list")
    
    # Extract the data at the head and move 
    # the head pointer forwards one node
    data = self.head.data
    self.head = self.head.next_
    self.size -= 1
    
    # If the list is empty set the tail to None
    if self.is_empty(): self.tail = None
  
    # Cleanup previous node
    else: self.head.prev = None
  
    # Return the data that was at the first node we just removed
    return data
  
  # Remove the last value at the tail of the linked list, O(1)
  def remove_last(self):
    
    # Can't remove data from an empty list -_-
    if self.is_empty(): raise RuntimeError("Empty linked list")
    
    # Extract the data at the tail and move 
    # the tail pointer backwards one node
    data = self.tail.data
    self.tail = self.tail.prev
    self.size -= 1
    
    # If the list is now empty set the head to None
    if self.is_empty(): self.head = None
    
    # Cleanup the node that was just removed
    else: self.tail.next_ = None
  
    # Return the data that was in the last node we just removed
    return data
  
  def __len__(self):
    return self.size
  



















