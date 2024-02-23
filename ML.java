import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class ML extends MouseAdapter implements MouseMotionListener {
  private boolean isPressed = false;
  private double x = 0.0, y = 0.0;
  private ButtonPressListener listener;

  public void setButtonPressListener(ButtonPressListener listener) {
    this.listener = listener;
  }

  @Override
  public void mousePressed(MouseEvent event) {
    isPressed = true;
    if (listener != null) {
      listener.onButtonPressed(event.getX(), event.getY());
    }
  }

  @Override
  public void mouseReleased(MouseEvent event) {
    isPressed = false;
  }

  @Override
  public void mouseMoved(MouseEvent event) {
    this.x = event.getX();
    this.y = event.getY();
  }

  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }

  public boolean isPressed() {
    return this.isPressed;
  }

  public interface ButtonPressListener {
    void onButtonPressed(double x, double y);
  }
}
