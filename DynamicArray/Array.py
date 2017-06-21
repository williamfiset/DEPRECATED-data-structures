#!/usr/bin/env python3

class Array():
  
  arr = []     # Pretend this is actually a static array
  size = 0     # length user thinks array is
  capacity = 0 # Actual array size
  
  def __init__(self, capacity = 16):
    if capacity <= 0: raise ValueError("Illegal size.")
    self.arr = [None for _ in range(capacity)]
    self.capacity = capacity
    self.iter_index = 0
  
  def clear(self):
    for i in range(self.size):
      self[i] = None
    self.size = 0
  
  def add(self, elem):
    
    # Time to resize!
    if self.size + 1 == self.capacity:
      
      self.capacity *= 2
      
      new_arr = [None for _ in range(self.capacity)]
      new_arr[:self.size] = self[:self.size]
      self.arr = new_arr
    
    self.arr[self.size] = elem
    self.size = self.size + 1
  
  # Removes an element at a specified index
  def removeAt(self, i):
    
    if i < 0 or i >= self.capacity: raise IndexError("removeAt index out of bounds")
    data = self[i]

    new_arr = [None for _ in range(self.size-1)]
    new_arr[:i] = self[:i]
    new_arr[i:] = self[i+1:]
    
    self.capacity = self.size = self.size - 1
    return data
  
  def remove(self, obj):
    
    index = self.indexOf(obj)
    if index == -1: return False
    
    self.removeAt(index)
    return True
  
  def indexOf(self, obj):
    
    for i in range(self.size):
      if self[i] == obj:
        return i
    return -1
  
  # Allows you to treat this class as an array and set values
  # via the square bracket notation like: myarray[index] = someobject
  def __setitem__(self, index, item):
    if index < 0 or index >= self.size:
      raise IndexError("Remove index out of bounds")
    self[index] = item

  # Allows you to treat this class as an array and get values
  # via the square bracket notation like: someobject = myarray[index]
  def __getitem__(self, index):
    if index < 0 or index >= self.size:
      raise IndexError("Get index out of bounds")
    return self.arr[index]

  # Overrides the 'in' keyword to easily check if
  # an element is contained within this array
  def __contains__(self, obj):
    return self.indexOf(obj) != -1
  
  # Overrides the 'len' builtin to return the size of this array
  def __len__(self):
    return self.size
  
  # Returns an iterator for this array. Allows for-in loop.
  def __iter__(self):
    self.iter_index = 0
    return self
  
  def __next__(self):
    
    if self.iter_index == self.size:
      raise StopIteration()
      
    data = self[self.iter_index]
    self.iter_index = self.iter_index + 1
    return data
  
  # Returns a string representation of this array
  def __str__(self):
    return "[" + ', '.join(map(str, self.arr[:self.size])) + "]"


