import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.*;

public class LinkedListTest {

  @Test
  public void testEmptyList() {
    LinkedList<Integer> list = new LinkedList<>();
    assertTrue(list.isEmpty());
  }

}

