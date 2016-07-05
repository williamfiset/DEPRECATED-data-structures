
public class PQueueMain {
  public static void main(String[] args) {

    PQueue<Integer> q = new PQueue<>();
    q.add(1);
    q.add(2);
    q.add(3);
    q.add(4);
    q.add(5);

    System.out.println( q.size() );
    System.out.println( q.poll() );
    System.out.println();
    System.out.println( q.size() );
    System.out.println( q.poll() );
    System.out.println();
    System.out.println( q.size() );
    System.out.println( q.poll() );
    System.out.println();    
    System.out.println( q.size() );
    System.out.println( q.poll() );
    System.out.println();
    System.out.println( q.size() );
    System.out.println( q.poll() );
    System.out.println();
    System.out.println( q.size() );
    System.out.println( q.poll() );
    System.out.println();
    System.out.println( q.size() );
    System.out.println( q.poll() );
    System.out.println();
    System.out.println( q.size() );
    System.out.println( q.poll() );
    System.out.println();                
  }
}
