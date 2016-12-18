public class StackMain {

  public static void main(String[] args) {
    
    Stack <String> stack = new Stack<>();

    stack.push("Hello");
    stack.push("World");
    stack.push("my");
    stack.push("name");
    stack.push("is");
    stack.push("Will");

    System.out.println(stack.size());
    System.out.println(stack.isEmpty());

    for (String w : stack) {
      System.out.println(w);
    }

  }

}
