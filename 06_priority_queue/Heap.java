import java.util.*;
class Heap {
  int size = 0;
  List<Integer> ar = new ArrayList<>();
  public void add(int num) {
    ar.add(num);  
    if (size != 0) swim(size);
    size++;
  }
  public int getSize(){
    return size;
  }
  public Integer getTop() {
    if (size == 0)
      return null;
    return ar.get(0);
  }
  private void swim(int i) {
      while(i != 0) {
        int p_i = i % 2 == 0 ? (i/2)-1 : (i/2);
        int cur_val = ar.get(i);
        int par_val = ar.get(p_i);
        if (par_val > cur_val) {
          swap(i, p_i);
        } else break;
        i = p_i;
      }
  }
  private void swap(int i, int j) {
    int tmp = ar.get(i);
    ar.set(i, ar.get(j));
    ar.set(j, tmp);
  }
  public void remove(int num) {
    if (size == 1) {
      ar.set(0, null);
      size = 0;
      return;
    }
    for(int i = 0; i < size; i++) {
      if (ar.get(i) == num) {
        int last_elem = ar.get(size-1);
        ar.set(i, last_elem);
        --size;
        sink(i);
        ar.set(size, null);
      }
    }
  }
  

private void sink(int i) {
 while(i*2+1 < size || i*2+2 < size) {
     Integer left = null, right = null, cur = ar.get(i);
     if (i*2+1 < size) left = ar.get(i*2+1);
     if (i*2+2 < size) right = ar.get(i*2+2);
     // System.out.println("i = " + i + " left: " + left + " Right: " + right + " Cur: " + cur);
     if (left != null && right != null) {
       if (left < cur) {
         swap(i, i*2+1);
         i = i*2+1;
       } else if (right < cur) {
         swap(i, i*2+2);
         i = i*2+2;
       } else break;
     } else if (left != null && left < cur) {
      swap(i, i*2+1);
      i = i*2+1;
     } else if (right != null && right < cur) {
      swap(i, i*2+2);
      i = i*2+2;
     } else break;
  }
}

}

public class H {
  public static void main(String[] args) {
    Heap h = new Heap();
    h.add(6);
    h.add(4);
    h.add(2);
    h.add(3);
    h.add(8);
    h.add(9);
    h.add(10);
    h.add(11);

    System.out.println(h.ar);
    System.out.println(h.getTop());
    h.remove(2);
    System.out.println(h.ar);
    h.remove(3);
    System.out.println(h.ar);
    h.remove(4);
    System.out.println(h.ar);
    h.remove(6);
    System.out.println(h.ar);
    h.remove(11);
    System.out.println(h.ar);
    h.remove(10);
    System.out.println(h.ar);
    h.remove(8);
    System.out.println(h.ar);
    h.remove(9);
    System.out.println(h.ar);
    // h.remove(8);
    // System.out.println(h.ar);

    // System.out.println(h.getTop());
    // h.remove(3);
    // System.out.println(h.getTop());
    // h.remove(4);
    // System.out.println(h.getTop());
    // h.remove(6);
    // System.out.println(h.getTop());
    // h.remove(8);
    // System.out.println(h.getTop());
    // h.remove(9);
    // System.out.println(h.getTop());


  }
}



