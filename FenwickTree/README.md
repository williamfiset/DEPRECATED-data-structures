
## Fenwick Tree

The Fenwick Tree (FT), also called the Binary Indexed Tree (BIT) is an efficient data structure for performing range/point queries/updates. We currently have two flavors of Fenwick trees which support summation queries/updates. In general, you can modify FTs to support [any invertible function](https://www.quora.com/What-are-the-advantage-of-binary-indexed-tree-BIT-or-fenwick-tree-over-segment-tree) not just summation. More specific operations such as min/max queries can be done with a FT but require you to maintain additional information.

### Fenwick Tree - Range updates and point queries

```java

// The values array must be one based
long[] values = {0,+1,-2,+3,-4,+5,-6};
//               ^ first element does not get used
  
FenwickTreeRangeUpdatePointQuery ft = new FenwickTreeRangeUpdatePointQuery(values);

// // All indexes used are one based
ft.updateRange(1, 4, 10);
ft.getPoint(1); // 11
ft.getPoint(4); // 6
ft.getPoint(5); // 5

ft.updateRange(3, 6, -20);
ft.getPoint(3); // -7
ft.getPoint(4); // -14
ft.getPoint(5); // -15
```

### Fenwick Tree - Range queries and point updates

```java

// The values array must be one based
long[] values = {0,1,2,2,4};
//               ^ first element does not get used
  
FenwickTreeRangeQueryPointUpdate ft = new FenwickTreeRangeQueryPointUpdate(values);

// All indexes used are one based
ft.sum(1, 4); // 9
ft.add(3, 1);
ft.sum(1, 4); // 10
ft.set(4, 0);
ft.sum(1, 4); // 6
ft.sum(2, 2); // 2
```
