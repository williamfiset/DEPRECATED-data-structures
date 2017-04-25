
## Fenwick Tree

The Fenwick Tree (FT), also called the Binary Indexed Tree (BIT) is and efficient data structure for performing range queries and point queries or range queries and range updates. The current Fenwick tree implementation supports sum queries but in general can be modified to support any invertible function. More specific operations such as min/max queries can be done with a FT but require you to maintain additional information.

### Fenwick Tree example usage

```java

  long[] values = {0,1,2,3,4,5,6};
  //               ^ first element does not get used

  FenwickTree ft = new FenwickTree(values);
  
  // Examples queries
  ft.sum(1, 6); // 21
  ft.sum(1, 5); // 15
  ft.sum(1, 4); // 10
  ft.sum(1, 3); // 6
  ft.sum(1, 2); // 3
  ft.sum(1, 1); // 1

```
