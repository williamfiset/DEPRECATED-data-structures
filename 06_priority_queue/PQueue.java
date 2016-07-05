
/*
Remember that you can always turn a max heap into a min heap by inserting negated values 
and re-negating the values after they are removed from the heap
*/


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
  Array <T> heap = null;
  HashMap<Integer, T> index_mapping = new HashMap<>();

  public PQueue () {
    heap = new Array<>();
  }

  public PQueue(int sz) {
    heap_size = sz;
    heap = new Array<>(sz);
  }

  // Heapify 
  public PQueue (T[] elems) {
    this(elems.length);
    for (int i = 0; i < elems.length; i++ )
      add(elems[i]);
  }

  public boolean isEmpty() {
    return heap_size == 0;
  }

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
    heap_size = 0;
    heap = new Array<>();
  }
  public int size() {
    return heap_size;
  }

  // Tests if an element is in the heap in O(log(n)) time
  public boolean contains(T elem) {
    int k = 0;
    while(2*k < heap_size) {
      
      // Found element
      if ( heap.get(k).equals(elem) )
        return true;
      
      // Dig into heap
      int j = 2*k;
      if (j < heap_size && less(j, j+1)) j++;
      swap(k, j);
      k = j;

    }
    return heap.get(k).equals(elem);
  }

  public void add(T elem) {
    if (elem == null) throw new NullPointerException();
    heap.add(elem);
    index_mapping.put(heap_size, elem);
    swim(heap_size); // bubble up element
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
      heap.set(heap_size, null);
      sink(0); // Restore heap property
      return root;
    } else return null;
  }

  private void removeAt(int i) {
    assert i >= 0 && i < heap_size;
    
    // Still need to implement remove at

    // heap_size--;
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
    // Assume child1 & child2 are not null
    T child1 = heap.get(i);
    T child2 = heap.get(j);

    return child1.compareTo(child2) < 0;
    
  }

  // Bottom up re-heapify 
  private void swim(int k) {
    while(k > 0 && less(k/2, k)) {
      swap(k/2, k);
      k /= 2;
    }
  }

  // Top down re-heapify
  private void sink(int k) {
    while(2*k < heap_size) {
      int j = 2*k;
      if (j+1 < heap_size && less(j, j+1)) j++;
      if (!less(k, j)) break;
      swap(k, j);
      k = j;
    }
  }

}















