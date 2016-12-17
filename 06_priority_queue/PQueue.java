
/*
Remember that you can always turn a max heap into a min heap by inserting negated values 
and re-negating the values after they are removed from the heap.

Some of this software was inspired by the works of Robert Sedgewick and Kevin Wayne

Should we support adding null elements inside the heap?

*/


// Eventually replace with custom Map
import java.util.Map;
import java.util.HashMap;

interface IPQueue <T> {
  
  public boolean contains(T elem);
  public boolean remove(T elem);
  public boolean isEmpty();
  public void add(T ... elem);
  public void clear();
  public int size();
  public T peek();
  public T poll();

}

class PQueue <T extends Comparable<T>> implements IPQueue <T> {

  int heap_size = 0;
  int heap_capacity = 0;
  Array <T> heap = null;

  // Eventually replace with custom Map (but ok for video series)
  // What if elements are not unique?
  Map <Integer, LinkedList<T>> index_mapping = new HashMap<>();

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

  // Wrong. Needs work.
  public boolean remove(T element) {
    for (int i = 0; i < heap_size; i++) {
      if (element.equals(heap.get(i))) {
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

  public void add(T ... elems) {
    if (elems == null) throw new NullPointerException();
    for ( T elem : elems ) {
      if (elem == null) throw new NullPointerException();
      if (heap_size < heap_capacity) {
        heap.set(heap_size, elem);
      } else {
        heap.add(elem);
        heap_capacity++;
      }
      // index_mapping.put(heap_size, elem);
      swim(heap_size); 
      heap_size++;
    }
  }

  public T peek() {
    if (isEmpty()) return null;
    return heap.get(0);
  }

  public T poll() {
    
    // replace with return removeAt(0);

    if (!isEmpty()) {
      T root = heap.get(0);
      heap_size--;
      swap(0, heap_size);
      heap.set(heap_size, null); // Memory cleanup
      sink(0); // Restore heap property
      return root;
    } else return null;

  }

  private T removeAt(int i) {
    
    // Just for debugging
    assert i >= 0 && i < heap_size;
    
    if (!isEmpty()) {
      T data = heap.get(i);
      heap_size--;
      swap(i, heap_size);
      heap.set(heap_size, null); // Memory cleanup
      sink(i); // Restore heap property
      return data;
    } else return null;

  }

  // Swap Two nodes 
  private void swap(int i, int j) {
    T i_elem = heap.get(i);
    T j_elem = heap.get(j);
    // index_mapping.put(i, j_elem);
    // index_mapping.put(j, i_elem);
    heap.set(i, j_elem);
    heap.set(j, i_elem);
  }

  // Test if node i < node j
  // Assumes i and j are valid
  private boolean less(int i, int j) {

    T child1 = heap.get(i);
    T child2 = heap.get(j);

    if (child1 == null || child2 == null) return false;
    return child1.compareTo(child2) <= 0;
    
  }

  // Bottom up re-heapify 
  private void swim(int k) {
    int parent = (k-1) / 2;
    while(k > 0 && less(k, parent)) {
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
  // This method is just for testing purposes 
  public boolean isMinHeap(int k) {
    if (k >= heap_size) return true;
    int left  = 2 * k + 1;
    int right = 2 * k + 2;
    if (left  < heap_size  && !less(k, left))  return false;
    if (right < heap_size  && !less(k, right)) return false;
    return isMinHeap(left) && isMinHeap(right);
  }  

}















