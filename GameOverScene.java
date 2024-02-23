import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class GameOverScene extends Scene implements ML.ButtonPressListener {
  private final KL keyListener;
  private final ML mouseListener;

  public GameOverScene(KL keyListener, ML mouseListener) {
    this.keyListener = keyListener;
    this.mouseListener = mouseListener;
    this.mouseListener.setButtonPressListener(this::onButtonPressed);
  }

  @Override
  public void update(double dt) {
    // Check if the "Restart" button is pressed
    if (keyListener.isKeyPressed(KeyEvent.VK_ENTER)) {
      restartGame();
    }
  }

  @Override
  public void draw(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.BLACK);
    g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

    // Draw "GAME OVER" text
    Util.drawText(g2, "GAME OVER", Constants.SCREEN_WIDTH / 2, 150, Color.WHITE, "Arial", Font.BOLD, 36);

    // Draw the "Restart" button
    g2.setColor(Color.WHITE);
    RoundRectangle2D restartButton = new RoundRectangle2D.Double(320, 200, 150, 35, 10, 10);
    g2.fill(restartButton);

    g2.setColor(Color.BLACK);
    g2.setFont(new Font("Arial", Font.BOLD, 20));
    FontRenderContext frc = g2.getFontRenderContext();
    String restartText = "Restart";
    Rectangle2D bounds = g2.getFont().getStringBounds(restartText, frc);
    int textX = (int) (Constants.SCREEN_WIDTH / 2 - bounds.getWidth() / 2);
    int textY = (int) (200 + 35 / 2 + bounds.getHeight() / 2);
    g2.drawString(restartText, textX, textY);

    // Draw the "Exit" button
    g2.setColor(Color.WHITE);
    RoundRectangle2D exitButton = new RoundRectangle2D.Double(320, 280, 150, 35, 10, 10);
    g2.fill(exitButton);

    g2.setColor(Color.BLACK);
    String exitText = "Exit";
    bounds = g2.getFont().getStringBounds(exitText, frc);
    textX = (int) ((320 + 150 / 2) - bounds.getWidth() / 2);
    textY = (int) (280 + 35 / 2 + bounds.getHeight() / 2);
    g2.drawString(exitText, textX, textY);
  }

  public void onButtonPressed(double x, double y) {
    // Check if the "Restart" button is pressed
    if (x >= 320 && x <= 470 &&
      y >= 200 && y <= 235) {
      restartGame();
    }

    // Check if the "Exit" button is pressed
    if (x >= 320 && x <= 470 && y >= 280 && y <= 315) {
      Window.getWindow().close();
    }
  }

  // Method to restart the game
  private void restartGame() {
    // Change state to the menu scene
    Window.getWindow().changeState(3);
  }
}
