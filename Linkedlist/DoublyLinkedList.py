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
  
  # Remove an arbitrary node from the linked list, O(1)
  def _remove(self, node):

    # If the node to remove is somewhere either at the
    # head or the tail handle those independently
    if node.prev  == None: return self.remove_first()
    if node.next_ == None: return self.remove_last()
  
    # Make the pointers of adjacent nodes skip over 'node'
    node.next_.prev = node.prev
    node.prev.next_ = node.next_
    
    # Temporarily store the data we want to return
    data = node.data
    
    # Memory cleanup
    node.data = None
    node = node.prev = node.next_ = None
    
    self.size -= 1
    
    # Return the data in the node we just removed
    return data
  
  # Remove a node at a particular index, O(n)
  def remove_at(self, index):
    
    if index < 0 or index >= self.size: raise ValueError("Illegal index")
    
    trav = self.head
    
    for i in range(index+1):
      if i == index: break
      trav = trav.next_  
    
    return self._remove(trav)
  
  # Remove a particular value in the linked list, O(n)
  def remove(self, elem):
    
    trav = self.head
    
    while trav != None:
      
      # Support searching for None elements
      if elem == None:
        if trav.data == elem:
          self._remove(trav)
          return True
      
      # Support searching for none None elements
      else:
        if elem.__eq__(trav.data):
          self._remove(trav)
          return True
      
      trav = trav.next_
    
    return False
  
  def index_of(self, obj):
    
    trav, i = self.head, 0
    
    if obj == None:
      while trav != None:
        if trav.data == None:
          return i
        trav = trav.next_
        i += 1
    else:
      while trav != None:
        if obj.__eq__(trav.data):
          return i
        trav = trav.next_
        i += 1
    
    return -1
  
  def __contains__(self, elem):
    return self.index_of(elem) != -1
  
  def __len__(self):
    return self.size
  
  def __str__(self):
    s = "["
    trav = self.head
    while trav != None:
      s += trav.data + ", "
      trav = trav.next_
    s += "]"
    return s










