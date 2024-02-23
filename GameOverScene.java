import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverScene extends Scene {
  private final KL keyListener;

  public GameOverScene(KL keyListener) {
    this.keyListener = keyListener;
  }

  @Override
  public void update(double dt) {
    if (keyListener.isKeyPressed(KeyEvent.VK_ENTER)) {
      Window.getWindow().changeState(0); // Change state to menu scene
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
    int y = Constants.SCREEN_HEIGHT / 2;
    g2.drawString(gameOverText, x, y);

    // Draw instructions to restart
    g2.setFont(new Font("Arial", Font.PLAIN, 18));
    String restartText = "Press ENTER to restart";
    fontMetrics = g2.getFontMetrics();
    textWidth = fontMetrics.stringWidth(restartText);
    x = (Constants.SCREEN_WIDTH - textWidth) / 2;
    y += 40;
    g2.drawString(restartText, x, y);
  }
}
