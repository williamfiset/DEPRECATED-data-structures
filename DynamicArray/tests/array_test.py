#!/usr/bin/env python3

from inspect import getsourcefile
import os.path
import sys

# Allow importing from parent directory
current_path = os.path.abspath(getsourcefile(lambda:0))
current_dir = os.path.dirname(current_path)
parent_dir = current_dir[:current_dir.rfind(os.path.sep)]
sys.path.insert(0, parent_dir)

import unittest
from dynamic_array import Array

class ArrayTest(unittest.TestCase):
  
  def setUp(self): pass
  def tearDown(self): pass
  
  def test_empty_list(self):
    self.assertTrue(Array().is_empty())
  
  def test_removing_empty(self):
    ar = Array()
    self.assertRaises(IndexError, ar.remove_at, 0)

  def test_index_out_of_bounds(self):
    ar = Array()
    ar.add(None)
    ar.add(1)
    ar.add("")
    self.assertRaises(IndexError, ar.remove_at, 3)

  def test_index_out_of_bounds2(self):
    ar = Array()
    for _ in range(1000):
      ar.add("x")
    self.assertRaises(IndexError, ar.remove_at, 1000)

  def test_index_out_of_bounds3(self):
    ar = Array()
    for _ in range(1000):
      ar.add("x")
    self.assertRaises(IndexError, ar.__getitem__, 1000)

  def test_index_out_of_bounds4(self):
    ar = Array()
    for _ in range(1000):
      ar.add("x")
    self.assertRaises(IndexError, ar.__getitem__, -1)

  def test_filling(self):
    ar = Array(10)
    s = 0
    for i in range(10): ar.add(i)
    for i in range(10): ar[i] = i
    for i in range(10): s += ar[i]
    self.assertEqual(10, len(ar))
    self.assertEqual(45, s)
    

if __name__ == '__main__':
  unittest.main()
