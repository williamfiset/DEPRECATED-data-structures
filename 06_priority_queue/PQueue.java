
/*
Remember that you can always turn a max heap into a min heap by inserting negated values 
and re-negating the values after they are removed from the heap.

Some of this software was inspired by the works of Robert Sedgewick and Kevin Wayne

*/


// Eventually replace with custom Map
import java.util.HashMap;

interface IPQueue <T> {
  
  public boolean contains(T elem);
  public boolean remove(T elem);
  public boolean isEmpty();
  public void add(T elem);
  public void clear();
  public int size();
  public T peek();
  public T poll();

}

class PQueue <T extends Comparable<T>> implements IPQueue <T> {

  int heap_size = 0;
  int heap_capacity = 0;
  Array <T> heap = null;
  HashMap <Integer, T> index_mapping = new HashMap<>();

  public PQueue () {
    this(0);
  }

  public PQueue(int sz) {
    heap = new Array<>(sz);
  }

  // Heapify 
  public PQueue (T[] elems) {
    this(elems.length);
    for (int i = 0; i < elems.length; i++ )
      add(elems[i]);
  }

  // Heapify
  public PQueue (Array <T> elems) {
    this(elems.size());
    for (int i = 0; i < elems.size(); i++ )
      add(elems.get(i));
  }

  public boolean isEmpty() {
    return heap_size == 0;
  }

  // Wrong. Needs work
  public boolean remove(T elem) {

    for (int i = 0; i < heap_size; i++) {
      if (elem.equals(heap.get(i))) {

        removeAt(i);

        return true;
      }
    }
    return false;

  }

  public void clear() {
    heap.clear();
    heap_size = 0;
  }

  public int size() {
    return heap_size;
  }

  // O(n) linear scan to test if element is in heap
  // You can optionally use a Set<T> if element is hashable
  // to test if the element is in the heap
  public boolean contains(T elem) {
    for(int i = 0; i < heap_size; i++)
      if (heap.get(i).equals(elem))
        return true;
    return false;
  }

  public void add(T elem) {
    if (elem == null) throw new NullPointerException();
    if (heap_size < heap_capacity) {
      heap.set(heap_size, elem);
    } else {
      heap.add(elem);
      heap_capacity++;
    }
    index_mapping.put(heap_size, elem);
    swim(heap_size); 
    heap_size++;
  }

  public T peek() {
    if (isEmpty()) return null;
    return heap.get(0);
  }

  public T poll() {
    if (!isEmpty()) {
      T root = heap.get(0);
      heap_size--;
      swap(0, heap_size);
      heap.set(heap_size, null); // Memory cleanup
      sink(0); // Restore heap property
      return root;
    } else return null;
  }

  private void removeAt(int i) {

    assert i >= 0 && i < heap_size;
    heap_size--;
    
    // Still need to implement remove at

  }

  // Swap Two nodes 
  private void swap(int i, int j) {
    T i_elem = heap.get(i);
    T j_elem = heap.get(j);
    index_mapping.put(i, j_elem);
    index_mapping.put(j, i_elem);
    heap.set(i, j_elem);
    heap.set(j, i_elem);
  }

  // Test if node i < node j
  private boolean less(int i, int j) {

    // Check if in bounds
    // if (i > heap_size || j > heap_size) return false;

    T child1 = heap.get(i);
    T child2 = heap.get(j);

    if (child1 == null || child2 == null) return false;
    return child1.compareTo(child2) < 0;
    
  }

  // Bottom up re-heapify 
  private void swim(int k) {
    int parent = (k-1) / 2;
    while(k > 0 && less(k, parent)) {
      // System.out.println("Swapping: " + (k) + " and "+ ( (k-1)/2));
      swap( parent, k);
      k = parent;
      parent = (k-1) / 2;
    }
  }

  // Top down re-heapify
  private void sink(int k) {
    while ( 2*k < heap_size ) {
      int i = 2*k + 1;
      int j = 2*k + 2;
      // Find which is smaller i or j
      if ( j < heap_size && less(j, i) ) i++;
      if (!less(i, k)) break;
      swap(i, k);
      k = i;
    }
  }

  // Recursively checks if this heap is a min heap
  public boolean isMinHeap(int k) {
    if (k >= heap_size) return true;
    int left  = 2 * k + 1;
    int right = 2 * k + 2;
    if (left  < heap_size  && less(left, k))  return false;
    if (right < heap_size  && less(right, k)) return false;
    return isMinHeap(left) && isMinHeap(right);
  }  

}















