

interface IArray <T> {

  public int getSize();
  public T get(int index);
  public void set(int index, T val);
  public void add(T elem);
  public void removeAt(int rm_index);
  public boolean remove(T elem);
  public boolean isEmpty();

}

@SuppressWarnings("unchecked")
class Array <T> implements IArray <T> {

  private int capacity = 0; // Actual array size
  private int len = 0;      // length user thinks array is
  private T [] arr;  

  public Array() { this(16); }

  public Array(int capacity) {
    if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
    this.capacity = capacity;
    arr = (T[]) new Object[capacity];
  }

  public int getSize() { return len; }
  public boolean isEmpty() { return len == 0; }

  public T get(int index) { return arr[index]; }
  public void set(int index, T elem) { arr[index] = elem; }

  public void add(T elem) {
    if (len+1 >= capacity) {
      if (capacity == 0) capacity = 1;
      else capacity *= 2; // double the size
      T[] new_arr = (T[]) new Object[capacity];
      for (int i = 0; i < new_arr.length; i++)
        new_arr[i] = arr[i];
      arr = new_arr; // new_arr has extra nulls padded
    }
    arr[len++] = elem;
  }

  // Removes the element at the specified index in this list. 
  public void removeAt(int rm_index) {
    if (rm_index < 0 || rm_index >= len) throw new IndexOutOfBoundsException();
    T[] new_arr = (T[]) new Object[len-1];
    for (int i = 0, j = 0; i < len; i++, j++)
      if (i == rm_index) j--; // Skip over rm_index by fixing j temporarily
      else new_arr[j] = arr[i];
    arr = new_arr;
    --len; --capacity;
  }

  public boolean remove(T elem) {
    for (int i = 0; i < len; i++) {
      if (arr[i].equals(elem)) {
        removeAt(i); return true; } }
    return false;
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


