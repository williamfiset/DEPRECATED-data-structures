#!/usr/bin/env python3

# Min priority queue implementation using a binary heap
class PQueue():
  
  # The number of elements currently inside the heap
  size = 0
  
  # A dynamic list to track the elements inside the heap
  heap = []
  
  def __init__(self, elements = None):
    if elements != None:
      self.heapify(elements)
  
  def heapify(self, elements):
    
    self.size = len(elements)
    self.heap = list(elements)

    # Heapify process, O(n)
    for i in range(max(0, (self.size//2)-1), -1, -1):
      self._sink(i)
  
  def clear(self):
    self.heap = []
    self.size = 0
  
  def is_empty(self):
    return self.size == 0
  
  # Return the size of the heap
  def __len__(self):
    return self.size

  # Returns the value of the element with the lowest
  # priority in this priority queue. If the priority 
  # queue is empty null is returned
  def peek(self):
    if self.is_empty(): return None
    return self.heap[0]
  
  # Removes the root of the heap, O(log(n))
  def poll(self):
    return self._remove_at(0)
  
  # Test if an element is in heap, O(n)
  def __contains__(self, elem):
    
    # Linear scan to check containment, O(n)
    for i in range(self.size):
      if self.heap[i] == elem:
        return True
    
    return False
  
  # Adds an element to the priority queue, the 
  # element must not be null, O(log(n))  
  def add(self, elem):
    
    if elem == None: raise ValueError("Element cannot be None")
    
    self.heap.append(elem)
    self._swim(self.size)
    self.size += 1

  # Removes a particular element in the heap, O(n)
  def remove(self, elem):
    
    if elem == None: return False
    
    # Linear removal via search, O(n)
    for i in range(self.size):
      if elem == self.heap[i]:
        self._remove_at(i)
        return True
    
    return False

  # Tests if the value of node i <= node j
  # This method assumes i & j are valid indices, O(1)
  def _less(self, i, j):
    return self.heap[i] <= self.heap[j]

  # Swap two nodes. Assumes i & j are valid, O(1)
  def _swap(self, i, j):
    self.heap[i], self.heap[j] = self.heap[j], self.heap[i]

  # Perform bottom up node swim, O(log(n))
  def _swim(self, k):
    
    # Grab the index of the next parent node WRT to k
    parent = (k-1) // 2
    
    # Keep swimming while we have not reached the
    # root and while we're less than our parent.
    while k > 0 and self._less(k, parent):
      
      self._swap(parent, k)
      k = parent
      
      # Grab the index of the next parent node WRT to k
      parent = (k-1) // 2
  
  # Perform top down node sink, O(log(n))
  def _sink(self, k):
    
    while True:
      
      left  = 2*k + 1 # left node
      right = 2*k + 2 # right node
      smallest = left # Assume left is the smallest node of the two children
      
      # Find which is smaller left or right node
      # If right is smaller set smallest to be right
      if right < self.size and self._less(right, left):
        smallest = right
      
      # Stop if we're outside the bounds of the tree
      # or stop early if we cannot sink k anymore
      if left >= self.size or self._less(k, smallest): break
    
      # Move down the tree following the smallest node
      self._swap(smallest, k)
      k = smallest
  
  # Removes a node at particular index, O(log(n))
  def _remove_at(self, i):
    
    if self.is_empty(): return None
    
    self.size -= 1
    removed_data = self.heap[i]
    self._swap(i, self.size)
    
    # Obliterate the value
    del self.heap[self.size]
    
    # Removed last element
    if i == self.size: return removed_data
    elem = self.heap[i]
    
    # Try sinking element, and if sinking did not work try swimming
    self._sink(i)
    if self.heap[i] == elem: self._swim(i)
    
    return removed_data








