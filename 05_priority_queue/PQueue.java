
/*
Remember that you can always turn a max heap into a min heap by inserting
negated values and re-negating the values after they are removed from the heap.

Some of this software was inspired by the works of Robert Sedgewick and Kevin Wayne.

Should we support adding null elements inside the heap? Currently no.

In the future we can try making removing O(log(n)) using a Map to track
element indexes. This makes the code a bit more messy and adds a bit more
space but is probably worth it overall. This could be added for the fastjavadss.

*/

class PQueue <T extends Comparable<T>> {

  private int heap_size = 0;
  private int heap_capacity = 0;
  private java.util.List <T> heap = null;

  public PQueue() {
    this(0);
  }

  public PQueue(int sz) {
    heap = new java.util.ArrayList<>(sz);
  }

  // NOTE: You're doing Heapify wrong!
  // Heapify is O(n) a great explanation can be found at:
  // http://www.cs.umd.edu/~meesh/351/mount/lectures/lect14-heapsort-analysis-part.pdf
  // https://en.wikipedia.org/wiki/Binary_heap#Building_a_heap
  public PQueue (T[] elems) {

    heap_size = heap_capacity = elems.length;
    heap = new java.util.ArrayList<T>( heap_capacity );
    
    // Place all element in heap
    for(int i = 0; i < heap_size; i++)
      heap.add(elems[i]);

    // System.out.println(toString());

    // This is actually linear
    for (int i = Math.max(0, (heap_size/2)-1); i >= 0; i-- )
      sink(i);

  }

  // O(nlogn)
  public PQueue (java.util.Collection <T> elems) {
    this(elems.size());
    for( T elem : elems) add(elem);
  }

  public boolean isEmpty() {
    return heap_size == 0;
  }

  public void clear() {
    for (int i = 0; i < heap_capacity; i++)
      heap.set(i, null);
    heap_size = 0;
  }

  public int size() {
    return heap_size;
  }

  public T peek() {
    if (isEmpty()) return null;
    return heap.get(0);
  }

  public T poll() {
    return removeAt(0);
  } 

  public boolean remove(T element) {
    if (element == null) return false;
    for (int i = 0; i < heap_size; i++) {
      if (element.equals(heap.get(i))) {
        removeAt(i);
        return true;
      }
    }
    return false;
  }

  private T removeAt(int i) {
    
    if (isEmpty()) return null;

    heap_size--;
    T removed_data = heap.get(i);

    // Removed last element
    if (i == heap_size) return removed_data;

    swap(i, heap_size);
    heap.set(heap_size, null);
    T elem = heap.get(i);

    // Try sinking element
    sink(i);

    // If sinking did not work try swimming
    if ( heap.get(i).equals(elem) )
      swim(i);

    return removed_data;

  } 

  // O(n) linear scan to test if element is in heap
  public boolean contains(T elem) {
    if (elem == null) return false;
    for(int i = 0; i < heap_size; i++)
      if (heap.get(i).equals(elem))
        return true;
    return false;
  }

  // Adds an element to the priority queue, the element
  // must not be null. Takes O(log(n)) time
  public void add(T elem) {
    
    if (elem == null) throw new IllegalArgumentException();

    if (heap_size < heap_capacity) {
      heap.set(heap_size, elem);
    } else {
      heap.add(elem);
      heap_capacity++;
    }

    swim(heap_size); 
    heap_size++;

  }

  // Swap two nodes. Assumes i & j are valid
  private void swap(int i, int j) {
    T i_elem = heap.get(i);
    T j_elem = heap.get(j);
    heap.set(i, j_elem);
    heap.set(j, i_elem);
  }

  // Tests if node i <= node j. Assumes i & j are valid
  private boolean less(int i, int j) {

    T node1 = heap.get(i);
    T node2 = heap.get(j);
    return node1.compareTo(node2) <= 0;
    
  }

  // Bottom up node swim
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

  // Top down node sink
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
  // This method is just for testing purposes 
  // Called this method with k=0 to start at the root
  public boolean isMinHeap(int k) {
    if (k >= heap_size) return true;
    int left  = 2 * k + 1;
    int right = 2 * k + 2;
    if (left  < heap_size  && !less(k, left))  return false;
    if (right < heap_size  && !less(k, right)) return false;
    return isMinHeap(left) && isMinHeap(right);
  }  

}















