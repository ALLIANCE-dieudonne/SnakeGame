public class Main {
  public static void main(String[] args) {
    Window window = Window.getWindow();
    Thread t = new Thread(window);
    t.start();
  }
}
