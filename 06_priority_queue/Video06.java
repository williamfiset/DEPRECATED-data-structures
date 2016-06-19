
/*
Remember that you can always turn a max heap into a min heap by inserting negated values 
and re-negating the values after they are removed from the heap
*/

interface IPQueue <T> {
  boolean contains(T elem);
  boolean remove(E elem);
  void clear();
  void add(T elem);
  int size();
  E peek();
  E poll();
}


