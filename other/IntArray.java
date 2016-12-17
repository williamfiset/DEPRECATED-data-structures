

class IntArray implements Iterable <Integer> {

  private int capacity = 0;
  public  int len = 0;
  public  int [] arr;

  public IntArray() { this(16); }

  public IntArray(int capacity) {
    if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
    this.capacity = capacity;
    arr = new int[capacity];
  }

  public IntArray(int[] start_arr) {
    if (start_arr == null) throw new IllegalArgumentException("Array cannot be null");
    arr = java.util.Arrays.copyOf(start_arr, start_arr.length);
    capacity = len = start_arr.length;        
  }

  public int getSize() { return len; }
  public boolean isEmpty() { return len == 0; }

  // To get/set even faster do array_obj.arr[index] instead!
  // You gain about 10x the speed!
  public int get(int index) { return arr[index]; }
  public void set(int index, int elem) { arr[index] = elem; }

  public void add(int elem) {
    if (len+1 >= capacity) {
      if (capacity == 0) capacity = 1;
      else capacity *= 2; // double the size
      arr = java.util.Arrays.copyOf(arr, capacity); // pads with extra 0/null elements
    }
    arr[len++] = elem;
  }

  // Removes the element at the specified index in this list. 
  public void removeAt(int rm_index) {
    System.arraycopy(arr, rm_index+1, arr, rm_index, len - rm_index - 1 );
    --len; --capacity;
  }

  public boolean remove(int elem) {
    for (int i = 0; i < len; i++) {
      if (arr[i] == elem) {
        removeAt(i); return true; } }
    return false;
  }

  public void reverse() {
    for(int i = 0; i < len/2; i++) {
      int tmp = arr[i];
      arr[i] = arr[len - i - 1];
      arr[len - i - 1] = tmp;
    }
  }

  // Make sure this array is sorted! Returns a value < 0 if item is not found
  public int binarySearch(int key) {
    int index = java.util.Arrays.binarySearch(arr, 0, len, key);
    // if (index < 0) index = -index - 1; // If not found this will tell you where to insert
    return index;
  }

  public void sort() {
    java.util.Arrays.sort( arr, 0, len );
  }

  // Iterator is still fast but not as fast as iterative for loop
  @Override public java.util.Iterator <Integer> iterator () {
    return new java.util.Iterator <Integer> () {
      int index = 0;
      @Override public boolean hasNext() { return index < len; }
      @Override public Integer next() { return arr[index++]; }
    };
  }

  @Override public String toString() {
    if (len == 0) return "[]";
    else {
      StringBuilder sb = new StringBuilder( len ).append("[");
      for (int i = 0; i < len-1; i++ )
        sb.append(arr[i] + ", " );
      return sb.append(arr[len-1] + "]").toString();
    }
  }

}
  
