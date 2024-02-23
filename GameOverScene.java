import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class GameOverScene extends Scene {
  private final KL keyListener;
  private final ML mouseListener;

  public GameOverScene(KL keyListener, ML mouseListener) {
    this.keyListener = keyListener;
    this.mouseListener = mouseListener;
    this.mouseListener.setButtonPressListener(this::onButtonPressed);
  }

  @Override
  public void update(double dt) {
    if (keyListener.isKeyPressed(KeyEvent.VK_ENTER)) {
      Window.getWindow().changeState(3); // Change state to menu scene
    }
  }

  @Override
  public void draw(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.BLACK);
    g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

    // Draw game over text
    g2.setColor(Color.WHITE);
    g2.setFont(new Font("Arial", Font.BOLD, 36));
    String gameOverText = "GAME OVER";
    FontMetrics fontMetrics = g2.getFontMetrics();
    int textWidth = fontMetrics.stringWidth(gameOverText);
    int x = (Constants.SCREEN_WIDTH - textWidth) / 2;
    int y = 200;
    g2.drawString(gameOverText, x, y);

    // Draw instructions to restart
    g2.setFont(new Font("Arial", Font.PLAIN, 18));
    String restartText = "Press ENTER to restart";
    fontMetrics = g2.getFontMetrics();
    textWidth = fontMetrics.stringWidth(restartText);
    x = (Constants.SCREEN_WIDTH - textWidth) / 2;
    y += 45;
    g2.drawString(restartText, x, y);

    // Drawing the exit button
    g2.setColor(Color.WHITE);
    RoundRectangle2D exitButton = new RoundRectangle2D.Double(320, 280, 150, 35, 10, 10);
    g2.fill(exitButton);

    g2.setColor(Color.BLACK);
    g2.setFont(new Font("Arial", Font.BOLD, 20));
    FontRenderContext frc = g2.getFontRenderContext();
    String buttonText = "Exit";
    Rectangle2D bounds = g2.getFont().getStringBounds(buttonText, frc);
    int textX = (int) ((320 + 150 / 2) - bounds.getWidth() / 2);
    int textY = (int) (280 + 35 / 2 + bounds.getHeight() / 2);
    g2.drawString(buttonText, textX, textY);
  }

  private void onButtonPressed(double x, double y) {
    RoundRectangle2D.Double exitButton = new RoundRectangle2D.Double(100 + 200, 280, 150, 35, 10, 10);
    if (exitButton.contains(x, y)) {
      Window.getWindow().close();
    }
  }
}
