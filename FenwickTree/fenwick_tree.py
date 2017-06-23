
class FenwickTree():
  
  # This list contains the Fenwick tree ranges
  tree = []

  # Make sure the 'values' array is one based meaning 
  # values[0] does not get used, O(n) construction
  def __init__(self, values):
    
    if values == None: raise ValueError("Values cannot be None")

    # Make a clone of the values array since we manipulate 
    # the array in place destroying all its original content
    self.tree = list(values)
    self.n = len(self.tree)
    
    for i in range(1, self.n):
      j = i + self.lsb(i)
      if j < self.n: self.tree[j] += self.tree[i]

  # Returns the value of the least significant bit (LSB)
  # lsb(108) = lsb(0b1101100) =     0b100 = 4
  # lsb(104) = lsb(0b1101000) =    0b1000 = 8
  # lsb(96)  = lsb(0b1100000) =  0b100000 = 32
  # lsb(64)  = lsb(0b1000000) = 0b1000000 = 64
  def lsb(self, i):
    return i & -i
  
  # Computes the prefix sum from [1, i], O(log(n))
  def prefix_sum(self, i):
    sum_ = 0
    while i != 0:
      sum_ += self.tree[i]
      i &= ~self.lsb(i) # Equivalently, i -= self.lsb(i);
    return sum_
  
  # Returns the sum of the interval [i, j], O(log(n))
  def sum(self, i, j):
    if j < i: raise IndexError("Make sure j >= i")
    return self.prefix_sum(j) - self.prefix_sum(i-1)
  
  # Add 'v' to index 'i', O(log(n))
  def add(self, i, v):
    while i < self.n:
      self.tree[i] += v
      i += self.lsb(i)
  
  # Set index i to be equal to v, O(log(n))
  def set(self, i, v):
    self.add(i, v - self.sum(i, i))
  
  def __str__(self):
    return str(self.tree)
  
  
  