import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class GameScene extends Scene {
  private Rect background, foreground;
  Snake snake;
  KL keyListener;

  public GameScene(KL keyListener) {
    background = new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    foreground = new Rect(24, 48, 24 * 31, 24 * 22);
    snake = new Snake(5, 48, 48 + 24, 24, 24);
    this.keyListener = keyListener;
  }

  @Override
  public void update(double dt) {
    if (keyListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
      snake.changeDirection(Direction.RIGHT);
    } else if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
      snake.changeDirection(Direction.DOWN);
    } else if (keyListener.isKeyPressed(KeyEvent.VK_LEFT)) {
      snake.changeDirection(Direction.LEFT);
    } else if (keyListener.isKeyPressed(KeyEvent.VK_UP)) {
      snake.changeDirection(Direction.UP);
    }
    snake.update(dt);
  }

  @Override
  public void draw(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.BLACK);
    g2.fill(new Rectangle2D.Double((int) background.x, (int) background.y, (int) background.width, (int) background.height));

    g2.setColor(Color.WHITE);
    g2.fill(new Rectangle2D.Double((int) foreground.x, (int) foreground.y, (int) foreground.width, (int) foreground.height));
    snake.draw(g2);

  }
}
