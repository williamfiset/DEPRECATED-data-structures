import static java.lang.Math.*;
import java.util.*;
import java.io.*;

class Array implements Iterable <PRIMATIVE_WRAPPER_CLASS> {

  private int capacity = 0;
  public  int len = 0;
  public  PRIMATIVE_TYPE [] arr;  // arr is public for speed :)

  public Array() { this(16); }

  public Array(int capacity) {
    if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
    this.capacity = capacity;
    arr = new int[capacity];
  }

  public Array(PRIMATIVE_TYPE[] start_arr) {
    if (start_arr == null) throw new IllegalArgumentException("Array cannot be null");
    arr = Arrays.copyOf(start_arr, start_arr.length);
    capacity = len = start_arr.length;        
  }

  public int getSize() { return len; }
  public boolean isEmpty() { return len == 0; }

  // To get/set even faster do array_obj.arr[index] instead!
  // You gain about 10x the speed!
  public PRIMATIVE_TYPE get(int index) { return arr[index]; }
  public void set(int index, PRIMATIVE_TYPE elem) { arr[index] = elem; }

  public void add(PRIMATIVE_TYPE elem) {
    if (len+1 >= capacity) {
      if (capacity == 0) capacity = 1;
      else capacity *= 2; // double the size
      arr = Arrays.copyOf(arr, capacity); // pads with extra 0 elements
    }
    arr[len++] = elem;
  }

  // Removes the element at the specified index in this list. 
  public void removeAt(int rm_index) {
    System.arraycopy(arr, rm_index+1, arr, rm_index, len - rm_index - 1 );
    --len; --capacity;
  }

  public boolean remove(PRIMATIVE_TYPE elem) {
    for (int i = 0; i < len; i++) {
      if (arr[i] == elem) {
        removeAt(i); return true; } }
    return false;
  }

  public void reverse() {
    for(int i = 0; i < len/2; i++) {
      PRIMATIVE_TYPE tmp = arr[i];
      arr[i] = arr[len - i - 1];
      arr[len - i - 1] = tmp;
    }
  }

  public void sort() {
    Arrays.sort( arr, 0, len );
  }

  // Iterator is still fast but not as fast as iterative for loop
  @Override public Iterator <PRIMATIVE_WRAPPER_CLASS> iterator () {
    return new Iterator <PRIMATIVE_WRAPPER_CLASS> () {
      int index = 0;
      public boolean hasNext() { return index < len; }
      public PRIMATIVE_WRAPPER_CLASS next() { return arr[index++]; }
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



public class IntArrayTest {

    final static int SZ = 5000000;

    static void addingSpeed(IntArray int_arr, ArrayList<Integer> java_arraylist, Array<Integer> gen_arr ) {

      long intArr_start = System.nanoTime();
      for (int i = 0; i < SZ; i++)
        int_arr.add(i);
      long intArr_end = System.nanoTime();
      
      long arraylist_start = System.nanoTime();
      for (int i = 0; i < SZ; i++)
        java_arraylist.add(i);
      long arraylist_end = System.nanoTime();

      long gen_array_start = System.nanoTime();
      for (int i = 0; i < SZ; i++)
        gen_arr.add(i);
      long gen_array_end = System.nanoTime();

      double intArrTime = (intArr_end - intArr_start) / 1e9;
      double arraylistTime = (arraylist_end-arraylist_start) / 1e9;
      double genArrayTime = (gen_array_end-gen_array_start) / 1e9;

      System.out.println("Adding speed: ");
      System.out.println( "IntArray : " + intArrTime );
      System.out.println( "ArrayList: " + arraylistTime );
      System.out.println( "Array    : " + genArrayTime );
      // System.out.printf("%.3f/%.3f = %.3f times faster\n", arraylistTime, intArrTime, (arraylistTime/intArrTime) );

    }


    static void getSpeed(IntArray int_arr, ArrayList<Integer> java_arraylist, Array<Integer> gen_arr ) {

      long foreach_start = System.nanoTime();
      for (int num : int_arr) { }
      long foreach_end = System.nanoTime();

      long intArr_start = System.nanoTime();
      for (int i = 0; i < SZ; i++)
        int_arr.add(i);
      long intArr_end = System.nanoTime();
      
      long intArr_start2 = System.nanoTime();
      for (int i = 0; i < SZ; i++) {
        int v = int_arr.arr[i];
      }
      long intArr_end2 = System.nanoTime();

      long arraylist_start = System.nanoTime();
      for (int i = 0; i < SZ; i++)
        java_arraylist.get(i);
      long arraylist_end = System.nanoTime();

      long gen_array_start = System.nanoTime();
      for (int i = 0; i < SZ; i++)
        gen_arr.get(i);
      long gen_array_end = System.nanoTime();

      double foreachTime = (foreach_end - foreach_start) / 1e9;
      double intArrTime = (intArr_end - intArr_start) / 1e9;
      double intArrTime2 = (intArr_end2 - intArr_start2) / 1e9;
      double arraylistTime = (arraylist_end-arraylist_start) / 1e9;
      double genArrayTime = (gen_array_end-gen_array_start) / 1e9;

      System.out.println("\nGet speed: ");
      System.out.println( "IntArray foreach time: " + foreachTime );
      System.out.println( "IntArray     . get(i): " + intArrTime );
      System.out.println( "IntArray   obj.arr[i]: " + intArrTime2 );
      System.out.println( "ArrayList     .get(i): " + arraylistTime );
      System.out.println( "Array         .get(i): " + genArrayTime );
      // System.out.printf("%.3f/%.3f = %.3f times faster\n", arraylistTime, intArrTime2, (arraylistTime/intArrTime2) );

    }

    static void removeSpeed(IntArray int_arr, ArrayList<Integer> java_arraylist ) {

      // Select random number
      int num_randoms = 1000;
      int randomNums [] = new int[num_randoms];
      for (int i = 0; i < num_randoms; i++) randomNums[i] = (((int)(Math.random() * SZ)) % SZ);

      long intArr_start = System.nanoTime();
      for (int i = 0; i < num_randoms; i++)
        int_arr.remove( randomNums[i] );
      long intArr_end = System.nanoTime();
      
      long arraylist_start = System.nanoTime();
      for (int i = 0; i < num_randoms; i++)
        java_arraylist.remove( randomNums[i] );
      long arraylist_end = System.nanoTime();

      double intArrTime = (intArr_end - intArr_start) / 1e9;
      double arraylistTime = (arraylist_end-arraylist_start) / 1e9;

      System.out.println("\nRemove speed: ");
      System.out.println( "IntArray: " + intArrTime );
      System.out.println( "ArrayList: " + arraylistTime );
      System.out.printf("Ratio %.3f/%.3f = %.3f times faster\n", arraylistTime, intArrTime, (arraylistTime/intArrTime) );

    }


    public static void main(String[] args) throws IOException {
  
      IntArray int_arr = new IntArray(SZ);
      Array<Integer> gen_arr = new Array<Integer>(SZ);
      ArrayList <Integer> java_arraylist = new ArrayList<> (SZ);
     
      // addingSpeed(int_arr, java_arraylist, gen_arr);
      // getSpeed(int_arr, java_arraylist, gen_arr);
      // removeSpeed(int_arr, java_arraylist, gen_arr);

      int_arr.add(1);
      int_arr.add(3);
      int_arr.add(2);
      int_arr.add(5);
      int_arr.add(5);
      int_arr.add(4);
      int_arr.sort();
      System.out.println(int_arr);
      int_arr.add(4);
      System.out.println(int_arr);
    }

}













