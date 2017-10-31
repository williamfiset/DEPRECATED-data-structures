#!/usr/bin/env python3

class BinarySearchTree():
  
  # Tracks the number of nodes in this BST
  size = 0
  
  # This BST is a rooted tree so we maintain a handle on the root node
  root = None
  
  # Internal node containing node references and the actual node data
  class Node():
    data, left, right = None, None, None
    def __init__(self, data, left, right):
      self.data = data
      self.left = left
      self.right = right
  
  def is_empty(self):
    return self.size == 0

  # Add an element to this binary tree. Returns true
  # if the element was successfully inserted, O(n)
  def add(self, elem):
    
    # Check if the value already exists in this
    # binary tree, if it does ignore adding it
    if elem in self: return False
    
    self.root = self._add(self.root, elem)
    self.size += 1
    
    return True
  
  # Method to recursively add a value in the binary tree
  def _add(self, node, elem):
    
    # Base case: found a leaf node
    if node == None:
      return self.Node(elem, None, None)
    
    # Pick a subtree to insert element
    if elem < node.data:
      node.left  = self._add(node.left, elem)
    else:
      node.right = self._add(node.right, elem)
    
    return node
  
  # Remove a value from this binary tree if it exists, O(n)
  def remove(self, elem):
    
    # Make sure the node we want to remove 
    # actually exists before we remove it
    if elem in self:
      self.root = self._remove(self.root, elem)
      self.size -= 1
      return True
    return False
  
  def _remove(self, node, elem):
    
    if node == None: return None
    
    # Dig into left subtree, the value we're looking
    # for is smaller than the current value
    if elem < node.data:
      node.left = self._remove(node.left, elem)
      
    # Dig into right subtree, the value we're looking
    # for is greater than the current value
    elif elem > node.data:
      node.right = self._remove(node.right, elem)
    
    # Found the node we wish to remove
    else:
      
      # This is the case with only a right subtree or 
      # no subtree at all. In this situation just
      # swap the node we wish to remove with its right child.      
      if node.left == None:
        
        right_child = node.right
        del node.data, node
        return right_child
      
      # This is the case with only a left subtree or 
      # no subtree at all. In this situation just
      # swap the node we wish to remove with its left child.
      elif node.right == None:
        
        left_child = node.left
        del node.data, node
        return left_child

      # When removing a node from a binary tree with two links the
      # successor of the node being removed can either be the largest
      # value in the left subtree or the smallest value in the right 
      # subtree. In this implementation I have decided to find the 
      # smallest value in the right subtree which can be found by 
      # traversing as far left as possible in the right subtree.
      else:
        
        # Find the leftmost node in the right subtree
        tmp = self._dig_left(node.right)
        
        # Swap the data
        node.data = tmp.data
        
        # Go into the right subtree and remove the leftmost node we
        # found and swapped data with. This prevents us from having
        # two nodes in our tree with the same value.        
        node.right = self._remove(node.right, tmp.data)

        # If instead we wanted to find the largest node in the left
        # subtree as opposed to smallest node in the right subtree 
        # here is what we would do:
        # tmp = self._dig_right(node.left)
        # node.data = tmp.data
        # node.left = self._remove(node.left, tmp.data)
    
    return node
  
  # Helper method to find the leftmost node
  def _dig_left(self, node):
    while node.left != None:
      node = node.left
    return node
  
  # Helper method to find the rightmost node
  def _dig_right(self, node):
    while node.right != None:
      node = node.right
    return node  
  
  # Computes the height of the tree, O(n)
  def height(self):
    return self._height(self.root)
  
  # Recursive helper method to compute the height of the tree
  def _height(self, node):
    if node == None: return 0
    return max(self._height(node.left), self._height(node.right)) + 1
  
  def __len__(self):
    return self.size
  
  def __contains__(self, elem):
    if elem == None: return False
    return self._contains(self.root, elem)

  # Recursive method to find an element in the tree (if it exists)
  def _contains(self, node, elem):
    
    if node == None: return False
    
    if elem == node.data:
      return True
    elif elem < node.data:
      return self._contains(node.left, elem)
    else:
      return self._contains(node.right, elem)


