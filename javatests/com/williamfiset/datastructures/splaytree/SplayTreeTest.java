package javatests.com.williamfiset.datastructures.splaytree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SplayTreeTest {

    @Test
    void getRoot() {
        SplayTree<Integer> splayTree = new SplayTree<>();
        assertNull(splayTree.getRoot());
        splayTree.insert(2);
        assertEquals(2,splayTree.getRoot().getData());
        splayTree.insert(3);
        assertEquals(3,splayTree.getRoot().getData());
        splayTree.insert(10);
        assertEquals(10,splayTree.getRoot().getData());

    }

    @Test
    void testSplayInsertDeleteSearch() {
        SplayTree<Integer> splayTree = new SplayTree<>();
        int[] data = {2, 29, 26,-1,10,0,-2,11};
        for (int i :
                data) {
            splayTree.insert(i);
            assertEquals(i,splayTree.getRoot().getData());
        }
        for (int i :
                data) {
            splayTree.delete(i);
            assertNull(splayTree.search(i));
        }

    }

    @Test
    void findMax() {
        SplayTree<Integer> splayTree = new SplayTree<>();

        int[] data = {2, 3, 4,5,6,7,8,9};
        for (int i :
                data) {
            splayTree.insert(i);
            assertEquals(i,splayTree.findMax(splayTree.getRoot()));
        }

    }




}