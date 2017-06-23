
class UnionFind():
  
  # The number of elements in this union find
  size = 0
  
  # Used to track the sizes of each of the components
  sz = []
  
  # id_[i] points to the parent of i, if id_[i] = i then i is a root node
  id_ = []
  
  # Tracks the number of components in the union find
  num_components = 0
  
  def __init__(self, size):
    
    if size <= 0: raise ValueError("Illegal size")
    
    self.num_components = self.size = size
    
    # Each component is originally of size one
    self.sz = [1] * size
    
    # Each node is a link to itself (self root)
    self.id_ = list(range(size))
  
  def find(self, p):
    
    # Find the root of the component/set
    root = p
    while root != self.id_[root]:
      root = self.id_[root]
      
    # Compress the path leading back to the root. 
    # This operation is called "path compression" and is 
    # what gives us amortized time complexity.    
    while p != root:
      next_ = self.id_[p]
      self.id_[p] = root
      p = next_
    
    return root

  # This is the recursive formulation for the find method
  # def find(self, p):
  #   if p == self.id_[p]: return p
  #   root = self.id_[p] = self.find(self.id_[p])
  #   return root

  # Return whether or not the elements 'p' and
  # 'q' are in the same components/set.
  def connected(self, p, q):
    return self.find(p) == self.find(q)
  
  # Return the size of the components/set 'p' belongs to
  def component_size(self, p):
    return self.sz[self.find(p)]
  
  # Returns the number of remaining components/sets 
  def components(self):
    return self.num_components

  # Unify the components/sets containing elements 'p' and 'q'
  def unify(self, p, q):
    
    root1 = self.find(p)
    root2 = self.find(q)
    
    # These elements are already in the same group!
    if root1 == root2: return None
    
    # Merge smaller component/set into the larger one.
    if self.sz[root1] < self.sz[root2]:
      self.sz[root2] += self.sz[root1]
      self.id_[root1] = root2
    else:
      self.sz[root1] += self.sz[root2]
      self.id_[root2] = root1
    
    # Since the roots found are different we know that the
    # number of components/sets has decreased by one
    self.num_components -= 1

  # Return the number of elements in this UnionFind/Disjoint set
  def __len__(self):
    return self.size

uf  = UnionFind(10)
uf.unify(1,2)
uf.unify(2,3)
print(uf.component_size(1))
print(uf.component_size(2))
print(uf.component_size(3))
print(uf.component_size(4))
print(uf.components())






