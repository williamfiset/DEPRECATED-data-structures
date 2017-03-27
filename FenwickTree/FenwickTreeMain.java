
class FenwickTreeMain {
  public static void main(String[] args) {
    
    long DUMMY_VALUE = 0;
    long[] ar = {DUMMY_VALUE,1,2,3,4,5,6};
    //            ^^^^ first entry is does not get used and can be any value

    FenwickTree ft = new FenwickTree(ar);
    
    // Range queries should start at an index of 1
    System.out.println(ft.sum(1, 6)); // 21
    System.out.println(ft.sum(1, 5)); // 15
    System.out.println(ft.sum(1, 4)); // 10
    System.out.println(ft.sum(1, 3)); // 6
    System.out.println(ft.sum(1, 2)); // 3
    System.out.println(ft.sum(1, 1)); // 1
    // System.out.println(ft.sum(1, 0)); // <-- invalid bounds!

    System.out.println(ft.sum(3, 5)); // 12
    System.out.println(ft.sum(2, 6)); // 20
    System.out.println(ft.sum(4, 4)); // 4

  }
}
