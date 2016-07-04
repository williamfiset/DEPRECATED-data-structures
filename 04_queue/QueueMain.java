public class QueueMain {

  public static void main(String[] args) {

    Queue <Integer> q = new Queue<>();
    q.offer(1);
    q.offer(2);
    q.offer(3);
    q.offer(4);

    System.out.println(q.peek());
    System.out.println(q.poll());
    System.out.println(q.peek());
    System.out.println(q.poll());

    // for (int num : q) {
    //   System.out.println(num);
    // }
    
    // Demo doing a BFS?

  }
}
