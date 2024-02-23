import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class GameScene extends Scene {
  Rect background, foreground;
  Snake snake;
  KL keyListener;
  private int score = 0;

  double speed;
  public Food food;

  public GameScene(KL keyListener, double speed) {
    background = new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    foreground = new Rect(24, 48, Constants.TILE_WIDTH * 31, Constants.TILE_WIDTH * 22);
    snake = new Snake(3, 48, 48 + 24, 24, 24, foreground, speed);
    this.keyListener = keyListener;
    food = new Food(foreground, snake, 12, 12, Color.GREEN);
    food.spawn();
    this.speed = speed;
  }

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  @Override
  public void update(double dt) {
    if (keyListener.isKeyPressed(KeyEvent.VK_UP)) {
      snake.changeDirection(Direction.UP);
    } else if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
      snake.changeDirection(Direction.DOWN);
    } else if (keyListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
      snake.changeDirection(Direction.RIGHT);
    } else if (keyListener.isKeyPressed(KeyEvent.VK_LEFT)) {
      snake.changeDirection(Direction.LEFT);
    }

    if (!food.isSpawned) food.spawn();

    if (snake.intersectingWithRect(food.rect)) {
      score += 1;
      System.out.println(score);
    }

    food.update(dt);
    if (!snake.isGameOver()) { // Only update the snake if the game is not over
      snake.update(dt);
    }

    if (keyListener.isKeyPressedOnce(KeyEvent.VK_SPACE)) {
      snake.togglePause(); // Toggle the pause state of the snake when space is pressed
    }
  }

  @Override
  public void draw(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;

    g2.setColor(Color.BLACK);
    g2.fill(new Rectangle2D.Double(background.x, background.y, background.width, background.height));

    g2.setColor(Color.WHITE);
    g2.fill(new Rectangle2D.Double(foreground.x, foreground.y, foreground.width, foreground.height));

    snake.draw(g2);
    food.draw(g2);
  }
}
