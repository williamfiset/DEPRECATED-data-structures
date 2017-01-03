
/**
 * A min priority queue implementation using a binary heap.
 * Please contact me if you find a bug in this software.
 * @author William Fiset, william.alexandre.fiset@gmail.com
 **/

import java.util.*;

class PQueue <T extends Comparable<T>> {

  private int heap_size = 0;
  private int heap_capacity = 0;
  private List <T> heap = null;

  // This map keeps track of the possible indices a particular 
  // node value is found in the heap. Having this mapping lets
  // us have O(log(n)) removals and O(1) a element containment check
  // at the cost of some additional space and minor overhead
  private Map <T, TreeSet<Integer>> map = new HashMap<>();

  public PQueue() {
    this(1);
  }

  public PQueue(int sz) {
    heap = new ArrayList<>(sz);
  }

  // Heapify is O(n) a great explanation can be found at:
  // http://www.cs.umd.edu/~meesh/351/mount/lectures/lect14-heapsort-analysis-part.pdf
  public PQueue (T[] elems) {

    heap_size = heap_capacity = elems.length;
    heap = new ArrayList<T>( heap_capacity );
    
    // Place all element in heap
    for(int i = 0; i < heap_size; i++) {
      mapAdd(elems[i], i);
      heap.add(elems[i]);
    }

    // Heapify, takes O(n) time
    for (int i = Math.max(0, (heap_size/2)-1); i >= 0; i-- )
      sink(i);

  }

  // Priority queue construction, O(nlog(n))
  public PQueue (Collection <T> elems) {
    this(elems.size());
    for( T elem : elems) add(elem);
  }
  
  public boolean isEmpty() {
    return heap_size == 0;
  }

  // Clears everything inside the heap
  public void clear() {
    for (int i = 0; i < heap_capacity; i++)
      heap.set(i, null);
    heap_size = 0;
    map.clear();
  }

  public int size() {
    return heap_size;
  }

  // Returns the value of the element with the lowest
  // priority in this priority queue. If the priority 
  // queue is empty null is returned.
  public T peek() {
    if (isEmpty()) return null;
    return heap.get(0);
  }

  // Removes the root of the heap, O(log(n))
  public T poll() {
    return removeAt(0);
  }

  // Removes a particular element in the heap, O(log(n))
  public boolean remove(T element) {
    
    if (element == null) return false;
    
    // Linear removal via search, O(n)
    // for (int i = 0; i < heap_size; i++) {
    //   if (element.equals(heap.get(i))) {
    //     removeAt(i);
    //     return true;
    //   }
    // }

    // Logarithmic removal with map, O(log(n))
    Integer index = mapGet(element);
    if (index != null) removeAt(index);
    return index != null;

  }

  // Removes a node at particular index, O(log(n))
  private T removeAt(int i) {
    
    if (isEmpty()) return null;

    heap_size--;
    T removed_data = heap.get(i);

    // Obliterate the value
    swap(i, heap_size);
    heap.set(heap_size, null);
    mapRemove(removed_data, heap_size);

    // Removed last element
    if (i == heap_size) return removed_data;

    T elem = heap.get(i);

    // Try sinking element
    sink(i);

    // If sinking did not work try swimming
    if ( heap.get(i).equals(elem) )
      swim(i);

    return removed_data;

  } 

  // Test if an element is in heap, O(1)
  public boolean contains(T elem) {

    // Map lookup to check containment, O(1)
    if (elem == null) return false;
    return map.containsKey(elem);

    // Linear scan to check containment, O(n)
    // for(int i = 0; i < heap_size; i++)
    //   if (heap.get(i).equals(elem))
    //     return true;
    // return false;

  }

  // Adds an element to the priority queue, the 
  // element must not be null, O(log(n))
  public void add(T elem) {
    
    if (elem == null) throw new IllegalArgumentException();

    if (heap_size < heap_capacity) {
      heap.set(heap_size, elem);
    } else {
      heap.add(elem);
      heap_capacity++;
    }

    mapAdd(elem, heap_size);

    swim(heap_size); 
    heap_size++;

  }

  // Swap two nodes. Assumes i & j are valid, O(1)
  private void swap(int i, int j) {
    
    T i_elem = heap.get(i);
    T j_elem = heap.get(j);

    heap.set(i, j_elem);
    heap.set(j, i_elem);

    mapSwap(i_elem, j_elem, i, j);

  }

  // Tests if the value of node i <= node j
  // This method assumes i & j are valid indices, O(1)
  private boolean less(int i, int j) {

    T node1 = heap.get(i);
    T node2 = heap.get(j);
    return node1.compareTo(node2) <= 0;
    
  }

  // Bottom up node swim, O(log(n))
  private void swim(int k) {
    
    // Grab the index of the next parent node WRT to k
    int parent = (k-1) / 2;

    // Keep swimming while we have not reached the
    // root and while we're less than our parent.
    while(k > 0 && less(k, parent)) {

      // Exchange k with the parent
      swap( parent, k);
      k = parent;

      // Grab the index of the next parent node WRT to k
      parent = (k-1) / 2;

    }
  }

  // Top down node sink, O(log(n))
  private void sink(int k) {

    while ( true ) {
      
      int left  = 2 * k + 1; // Left  node
      int right = 2 * k + 2; // Right node
      int smallest = left;   // Assume left is the smallest node of the two children

      // Find which is smaller left or right
      // If right is smaller set smallest to be right
      if ( right < heap_size && less(right, left) )
        smallest = right;

      // Stop if we're outside the bounds of the tree
      // or stop early if we cannot sink k anymore
      if ( left >= heap_size || less(k, smallest)) break;
      
      // Move down the tree following the smallest node
      swap(smallest, k);
      k = smallest;

    }

  }

  // Recursively checks if this heap is a min heap
  // This method is just for testing purposes to make
  // sure the heap invariant is still being maintained
  // Called this method with k=0 to start at the root
  public boolean isMinHeap(int k) {
    if (k >= heap_size) return true;
    int left  = 2 * k + 1;
    int right = 2 * k + 2;
    if (left  < heap_size  && !less(k, left))  return false;
    if (right < heap_size  && !less(k, right)) return false;
    return isMinHeap(left) && isMinHeap(right);
  }  

  // Add a node value and its index to the map
  private void mapAdd(T value, int index) {
    
    TreeSet <Integer> set = map.get(value);

    // New value being inserted in map
    if (set == null) {

      set = new TreeSet<>();
      set.add(index);
      map.put(value, set);

    // Value already exists in map
    } else set.add(index);

  }

  // Removes the index at a given value
  private void mapRemove(T value, int index) {
    Set <Integer> set = map.get(value);
    set.remove(index); // Tree sets take O(log(n)) removal time
    if (set.size() == 0) map.remove(value);
  }

  // Extract an index position for the given value
  // NOTE: If a value exists multiple times in the heap the highest 
  // index is returned (this has arbitrarily been chosen)
  private Integer mapGet(T value) {
    TreeSet <Integer> set = map.get(value);
    if (set != null) return set.last();
    return null;
  }

  // Exchange the index of two nodes internally within the map
  private void mapSwap(T val1, T val2, int val1Index, int val2Index) {

    Set <Integer> set1 = map.get(val1);
    Set <Integer> set2 = map.get(val2);

    set1.remove(val1Index);
    set2.remove(val2Index);

    set1.add(val2Index);
    set2.add(val1Index);

  }

  @Override public String toString() {
    return heap.toString();
  }

}















