import com.williamfiset.datastructures.utils.TreePrinter;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Scanner;

import java.util.Collections;
import java.util.List;


class BinaryTree<T extends Comparable<T>> implements TreePrinter.PrintableNode {
    private BinaryTree<T> left, right;
    private T data;

    public BinaryTree(T data) {
        this.data = data;
    }

    @Override
    public BinaryTree<T> getLeft() {
        return left;
    }

    @Override
    public BinaryTree<T> getRight() {
        return right;
    }

    @Override
    public String getText() {
        return data.toString();
    }
    public void setRight(BinaryTree<T> right) {
        this.right = right;
        //   right.parent = this;
    }
    public void setLeft(BinaryTree<T> left) {
        this.left = left;
        // left.parent = this;
    }
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {

        return TreePrinter.getTreeDisplay(this);
    }


}

/**Note : Each Operation of a SplayTree tries returns the new splayed root after the operation **/
public class SplayTree<T extends Comparable<T>> {

    private BinaryTree<T> root;

    public SplayTree() {
        this.root = null;
    }

    public SplayTree(BinaryTree<T> root) {
        this.root = root;
    }

    public BinaryTree<T> getRoot() {
        return root;
    }

    private BinaryTree<T> rightRotate(BinaryTree<T> x) {
        BinaryTree<T> p = x.getLeft();
        x.setLeft(p.getRight());
        p.setRight(x);
        return p;
    }

    private BinaryTree<T> leftRotate(BinaryTree<T> x) {
        BinaryTree<T> p = x.getRight();
        x.setRight(p.getLeft());
        p.setLeft(x);
        return p;
    }

    private BinaryTree<T> splayUtil(BinaryTree<T> root, T key) {
        if (root == null || root.getData() == key)
            return root;

        if (root.getData().compareTo(key) > 0) {
            if (root.getLeft() == null) return root;

            if (root.getLeft().getData().compareTo(key) > 0) {

                root.getLeft().setLeft(splayUtil(root.getLeft().getLeft(), key));

                root = rightRotate(root);
            }
            else if (root.getLeft().getData().compareTo(key) < 0) {

                root.getLeft().setRight(splayUtil(root.getLeft().getRight(), key));

                if (root.getLeft().getRight() != null)
                    root.setLeft(leftRotate(root.getLeft()));

            }
            return (root.getLeft() == null) ? root : rightRotate(root);
        } else {
            if (root.getRight() == null) return root;

            if (root.getRight().getData().compareTo(key) > 0) {
                root.getRight().setLeft(splayUtil(root.getRight().getLeft(), key));
        if (root.getRight().getLeft() != null)
                    root.setRight(rightRotate(root.getRight()));
            } else if (root.getRight().getData().compareTo(key) < 0)// Zag-Zag (Right Right)
            {
                root.getRight().setRight(splayUtil(root.getRight().getRight(), key));
                root = leftRotate(root);
            }

            return (root.getRight() == null) ? root : leftRotate(root);
        }

    }

    public BinaryTree<T> splay(T x) {
        if (root == null) return null;

        this.root = splayUtil(root, x);

        return this.root;
    }
    public BinaryTree<T> search(T x) {
        if (root == null) return null;

        this.root = splayUtil(root, x);

        return this.root.getData().compareTo(x)==0 ? this.root:null;
    }

    private ArrayList<BinaryTree<T>> split(T x) {
        BinaryTree<T> right;
        BinaryTree<T> left;

        if (x.compareTo(root.getData()) > 0) {
            right = root.getRight();
            left = root;
            left.setRight(null);
        } else {
            left = root.getLeft();
            right = root;
            right.setLeft(null);
        }
        ArrayList<BinaryTree<T>> l_r = new ArrayList<>();
        l_r.add(left);
        l_r.add(right);

        return l_r;
    }

    public BinaryTree<T> insert(T x) {
        if (root == null) {
            root = new BinaryTree<>(x);
            return root;
        }
        splay(x);

        ArrayList<BinaryTree<T>> l_r = split(x);

        BinaryTree<T> left = l_r.get(0);
        BinaryTree<T> right = l_r.get(1);

        root = new BinaryTree<>(x);
        root.setLeft(left);
        root.setRight(right);


        return root;
    }

    public BinaryTree<T> delete(T x) {
        if (root == null) return null;

        BinaryTree<T> searchResult = splay(x);

        if (searchResult.getData().compareTo(x) != 0)
            return null;

        BinaryTree<T> leftSubtree = root.getLeft();
        BinaryTree<T> rightSubtree = root.getRight();

        // Set the 'to be deleted' key ready for garbage collection
        root.setLeft(null);
        root.setRight(null);
        root.setData(null);

        root = join(leftSubtree, rightSubtree);

        return root;
    }

    private BinaryTree<T> join(BinaryTree<T> L, BinaryTree<T> R) {

        if (L == null) {
            root = R;
            return R;
        }
        root = splayUtil(L, findMax(L));
        root.setRight(R);
        return root;
    }
    public T findMax(BinaryTree<T> root){
        BinaryTree<T> temp = root;
        while (temp.getRight()!=null)
            temp=temp.getRight();
        return temp.getData();
    }

    @Override
    public String toString() {

        System.out.println("Elements:"+inorder(root, new ArrayList<>()));
        return (root != null) ? root.toString() : null;
    }

    public ArrayList<T> inorder(BinaryTree<T> root, ArrayList<T> sorted) {

        if (root == null) {
            return sorted;
        }
        inorder(root.getLeft(), sorted);
        sorted.add(root.getData());
        inorder(root.getRight(), sorted);
        return sorted;
    }
}

class SplayTreeRun {
    public static void main(String[] args) {

        SplayTree<Integer> splayTree = new SplayTree<>();
        int[] data = {2, 29, 26,-1,10,0,2,11};
        Scanner sc = new Scanner(System.in);
        for (int i :
                data) {
            splayTree.insert(i);
        }

        while (true) {
            System.out.println("1. Insert 2. Delete 3. Search 4. PrintTree");
            int c = sc.nextInt();
            switch (c) {
                case 1:
                    System.out.println("Enter Data :");
                    splayTree.insert(sc.nextInt());
                    break;
                case 2:
                    System.out.println("Enter Element to be Deleted:");
                    splayTree.delete(sc.nextInt());
                    break;
                case 3:
                    System.out.println("Enter Element to be Searched and Splayed:");
                    splayTree.search(sc.nextInt());
                    break;
                case 4:
                    System.out.println(splayTree);
                    break;
            }


        }

    }
}

