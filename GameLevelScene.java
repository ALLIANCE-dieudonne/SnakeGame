import java.awt.*;
import java.awt.event.MouseEvent;
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

    //draw the choose level text
    g2.setColor(Color.WHITE);
    g2.setFont(new Font("Arial", Font.BOLD, 35));

    String chooseLevelText = "CHOOSE LEVEL";
    FontMetrics fontMetrics = g2.getFontMetrics();
    int textWidth = fontMetrics.stringWidth(chooseLevelText);
    int x = (Constants.SCREEN_WIDTH - textWidth) / 2;
    int y = 200;
    g2.drawString(chooseLevelText, x, y);

    //draw the buttons
    for (int i = 0; i < 3; i++) {
      g2.setColor(Color.WHITE);
      g2.fill(new RoundRectangle2D.Double(100 + 200 * i, 250, 150, 35, 10, 10));

      // Draw text inside the rectangle
      String buttonText = "";
      if (i == 0)
        buttonText = "Low";
      else if (i == 1)
        buttonText = "Medium";
      else buttonText = "Difficult";

      g2.setColor(Color.BLACK);
      g2.setFont(new Font("Arial", Font.BOLD, 20));
      FontRenderContext frc = g2.getFontRenderContext();
      Rectangle2D bounds = g2.getFont().getStringBounds(buttonText, frc);
      int textX = (int) ((100 + 200 * i + 150 / 2) - bounds.getWidth() / 2);
      int textY = (int) (250 + 35 / 2 + bounds.getHeight() / 2);
      g2.drawString(buttonText, textX, textY);
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
