import static java.lang.Math.*;
import java.util.*;
import java.io.*;


interface IArray {

  public int getSize();
  public int get(int index);
  public void set(int index, int val);
  public void add(int elem);
  public void removeAt(int rm_index);
  public boolean remove(int elem);
  public boolean isEmpty();

}

class Array implements IArray {

  private int capacity = 0; // Actual array size
  private int len = 0;      // length user thinks array is
  private int [] arr;  

  public Array() { this(16); }

  public Array(int capacity) {
    if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
    this.capacity = capacity;
    arr = new int[capacity];
  }

  public Array(int[] start_arr) {
    if (start_arr == null) throw new IllegalArgumentException("Array cannot be null");
    arr = Arrays.copyOf(start_arr, start_arr.length);
    capacity = len = start_arr.length;        
  }

  public int getSize() { return len; }
  public boolean isEmpty() { return len == 0; }

  public int get(int index) { return arr[index]; }
  public void set(int index, int elem) { arr[index] = elem; }

  public void add(int elem) {
    if (len+1 >= capacity) {
      if (capacity == 0) capacity = 1;
      else capacity *= 2; // double the size
      arr = Arrays.copyOf(arr, capacity); // pads with extra 0/null elements
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


public class Video01 {

    public static void main(String[] args) {

      

    }

}








