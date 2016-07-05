import java.util.Iterator;

public class BinarySearchTreeMain {
  public static void main(String[] args) {
    
    BinaryTree<Integer> tree = new BinaryTree<>();
    tree.add(3);
    tree.add(5);
    tree.add(1);
    tree.add(4);
    tree.add(2);
    tree.add(6);

    tree.remove(6);
    tree.remove(3);
    tree.remove(1);
    tree.remove(5);
    tree.remove(5);
    tree.remove(2);
    tree.remove(4);

    Iterator <Integer> iter = tree.traverse(TreeTraversalOrder.LEVEL_ORDER);
    
    while (iter.hasNext()) {
      Integer n = iter.next();
      System.out.println(n);
    }

  }
}


