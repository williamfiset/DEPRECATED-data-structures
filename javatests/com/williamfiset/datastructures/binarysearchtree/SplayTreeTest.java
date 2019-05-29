import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class SplayTreeTest {
    public static final int MAX = Integer.MAX_VALUE/4,MIN=Integer.MIN_VALUE/4;

    @Test
    void getRoot() {
        SplayTree<Integer> splayTree = new SplayTree<>();
        List<Integer> data = com.williamfiset.datastructures.utils.TestUtils.randomIntegerList(100,MIN,MAX);
        for (int i :
                data) {
            splayTree.insert(i);
            assertEquals(i,splayTree.getRoot().getData());
        }


    }

    @Test
    void splayInsertDeleteSearch() {
        SplayTree<Integer> splayTree = new SplayTree<>();
        List<Integer> data = com.williamfiset.datastructures.utils.TestUtils.
                randomUniformUniqueIntegerList(100);//Note : we dont want duplicate values here to test "search" after "delete" should assertNull
        for (int i :
                data) {
            splayTree.insert(i);
            assertEquals(i,splayTree.getRoot().getData());
        }
        for (int i :
                data) {
            assertNotNull(splayTree.search(i));
        }
        for (int i :
                data) {
            splayTree.delete(i);
            assertNull(splayTree.search(i));
        }

    }
    @Test
    void insertSearch(){
        SplayTree<Integer> splayTree = new SplayTree<>();
        List<Integer> data = com.williamfiset.datastructures.utils.TestUtils.randomIntegerList(100,MIN,MAX);
        for (int i :
                data) {
            splayTree.insert(i);
            assertEquals(i,splayTree.getRoot().getData());
        }
    }

    @Test
    void findMax() {
        SplayTree<Integer> splayTree = new SplayTree<>();
        List<Integer> data = com.williamfiset.datastructures.utils.TestUtils.sortedIntegerList(-50,50);
        for (int i :
                data) {
            splayTree.insert(i);
            assertEquals(i,splayTree.findMax(splayTree.getRoot()));
        }

    }

    /*Comparison With Built In Priority Queue*/

    @Test
    void splayTreePriorityQueueConsistencyTest() {
        SplayTree<Integer> splayTree = new SplayTree<>();
        Queue<Integer> pq = new PriorityQueue<>();
        List<Integer> data = com.williamfiset.datastructures.utils.TestUtils.randomUniformUniqueIntegerList(100);
        for (int i :
                data) {
            assertEquals(pq.add(i),splayTree.insert(i)!=null);
        }
        for (int i :
                data) {
            assertEquals(splayTree.search(i).getData().equals(i),pq.contains(i));
        }
        for (int i :
                data) {
            splayTree.delete(i);
            assertNull(splayTree.search(i));
            pq.remove(i);
            assertFalse(pq.contains(i));
        }

    }







}