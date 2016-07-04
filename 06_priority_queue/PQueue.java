
/*
Remember that you can always turn a max heap into a min heap by inserting negated values 
and re-negating the values after they are removed from the heap
*/

interface IPQueue <T> {
  
  public boolean contains(T elem);
  public boolean remove(T elem);
  public void clear();
  public void add(T elem);
  public int size();
  public T peek();
  public T poll();

}

class PQueue <T> implements IPQueue <T> {

  int heap_size = 0;
  Array <T> heap = null;

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

  // Tests if an element is in the heap in O(log(n)) time
  public boolean contains(T elem) {

    return false;
  }


  public boolean remove(T elem) {

    heap_size--;
    return false;
  }

  public void clear() {
    this(0);
  }

  public void add(T elem) {
    heap_size++;
  }

  public int size() {
    return heap_size;
  }

  public T peek() {
    if (sz > 0)
      return heap.get(0);
    throw new Exception("No elements, heap empty");
  }

  public T poll() {
    if (sz > 0) {
      T root = heap.get(0);
      heap.set(0, heap.get(heap_size-1));
      heap_size--;
      for (int i = 0; i < heap_size; i++) {
        Boolean left_is_smaller = less(i*2, i*2+1);
        if (left_is_smaller) {
          swap( i, i*2 );
        } else if (left_is_smaller == false) {
          swap( i, i*2 + 1 );
        } // else { } // already at bottom
      }
      return root;
    } else throw new Exception("No elements, heap empty")
  }

  // Swap Two nodes 
  private void swap(int i, int j) {
    T tmp = heap.get(i);
    heap.set(i, heap.get(j));
    heap.set(j, tmp);
  }

  // Test if node i < node j. Return null if i or j are out of bounds
  private Boolean less(int i, int j) {

    // Indecisive 
    if (i >= heap_size || j >= heap_size)
      return null;

    // Assume child1 & child2 are not null
    T child1 = heap.get(i);
    T child2 = heap.get(j);

    return child1.compareTo(child2) < 0;
    
  }

  // Bottom up re-heapify 
  private void swim(int k) {

  }

  // Top down re-heapify
  private void sink(int k) {

  }


}















