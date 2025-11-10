public class LowerToUpperFinder {
  public static void main(String[] args) {
    String text = "helloWorld thisIsJava";
    String result = text.replaceAll("([a-z])([A-Z])", "$1!$2!");
    System.out.println(result);
  }
}
