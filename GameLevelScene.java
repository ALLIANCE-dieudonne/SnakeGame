import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class GameLevelScene extends Scene implements ML.ButtonPressListener {
  private final KL keyListener;
  private final ML mouseListener;
  double speed = 0;

  public GameLevelScene(KL keyListener, ML mouseListener) {
    this.keyListener = keyListener;
    this.mouseListener = mouseListener;
    this.mouseListener.setButtonPressListener(this);
  }

  @Override
  public void update(double dt) {
  }

  @Override
  public void draw(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.BLACK);
    g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

    // Draw the "CHOOSE LEVEL" text
    Util.drawText(g2, "CHOOSE LEVEL", Constants.SCREEN_WIDTH / 2, 150, Color.WHITE, "Arial", Font.BOLD, 35);

    // Draw the buttons
    for (int i = 0; i < 3; i++) {
      g2.setColor(Color.WHITE);
      g2.fill(new RoundRectangle2D.Double(100 + 200 * i, 250, 150, 35, 10, 10));

      // Draw text inside the rectangle
      String buttonText = "";
      if (i == 0)
        buttonText = "Low";
      else if (i == 1)
        buttonText = "Medium";
      else
        buttonText = "Difficult";

      Util.drawText(g2, buttonText, 100 + 200 * i + 75, 250 + 35 / 2, Color.BLACK, "Arial", Font.BOLD, 20);
    }
  }

  @Override
  public void onButtonPressed(double x, double y) {
    if (x >= 100 && x <= 250 && y >= 250 && y <= 285) {
      speed = 0.3; // Set speed for low level
    } else if (x >= 300 && x <= 450 && y >= 250 && y <= 285) {
      speed = 0.2; // Set speed for medium level
    } else if (x >= 500 && x <= 650 && y >= 250 && y <= 285) {
      speed = 0.1; // Set speed for difficult level
    }

    Window.getWindow().changeState(1); // Change state to GameScene with speed
  }

  public double getSpeed() {
    return speed;
  }
}
