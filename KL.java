import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KL extends KeyAdapter implements KeyListener {
  private boolean[] keyPressed = new boolean[128];

  @Override
  public void keyPressed(KeyEvent e) {
    keyPressed[e.getKeyCode()] = true;
  }

  @Override
  public void keyReleased(KeyEvent e) {
    keyPressed[e.getKeyCode()] = false;
  }

  public boolean isKeyPressed(int keyCode) {
    return keyPressed[keyCode];
  }
  public boolean isKeyPressedOnce(int keyCode) {
    if (keyPressed[keyCode]) {
      keyPressed[keyCode] = false; // Reset the flag after key is processed
      return true;
    }
    return false;
  }

}
