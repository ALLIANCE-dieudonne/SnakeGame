import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Snake {
  public Rect[] body = new Rect[100];
  public double bodyWidth, bodyHeight;

  private boolean gameOver = false; // Flag to track game over state
  private boolean paused = false; // Flag to track whether the snake movement is paused

  public int size;
  public int tail = 0;
  public int head = 0;

  public Direction direction = Direction.RIGHT;

  public Rect background;

  public double ogWaitBetweenUpdate;
  public double waitTimeLeft = ogWaitBetweenUpdate;

  public Snake(int size, double startX, double startY, double bodyWidth, double bodyHeight, Rect background, double speed) {
    this.size = size;
    this.bodyWidth = bodyWidth;
    this.bodyHeight = bodyHeight;
    this.background = background;
    this.ogWaitBetweenUpdate = speed;

    for (int i = 0; i <= size; i++) {
      Rect bodyPiece = new Rect(startX + i * bodyWidth, startY, bodyWidth, bodyHeight);
      body[i] = bodyPiece;
      head++;
    }
    head--;
  }

  public void changeDirection(Direction newDirection) {
    if (!paused) { // Only change direction if not paused
      if (newDirection == Direction.RIGHT && direction != Direction.LEFT) {
        direction = newDirection;
      } else if (newDirection == Direction.UP && direction != Direction.DOWN) {
        direction = newDirection;
      } else if (newDirection == Direction.LEFT && direction != Direction.RIGHT) {
        direction = newDirection;
      } else if (newDirection == Direction.DOWN && direction != Direction.UP) {
        direction = newDirection;
      }
    }
  }

  public boolean intersectingWithSelf() {
    // Check all segments, including the head
    for (int i = 0; i < body.length; i++) {
      if (i == head || body[i] == null) {
        continue; // Skip head and null segments
      }
      if (detectIntersection(body[head], body[i]) || intersectingWithBoundaries(body[head])) {
        return true;
      }
    }
    return false;
  }

  public boolean intersectingWithBoundaries(Rect head) {
    return (head.x < background.x || (head.x + head.width) > background.x + background.width ||
      head.y < background.y || (head.y + head.height) > background.y + background.height);
  }

  public boolean detectIntersection(Rect r1, Rect r2) {
    return (r1.x >= r2.x && r1.x + r1.width <= r2.x + r2.width &&
      r1.y >= r2.y && r1.y + r1.height <= r2.y + r2.height);
  }

  public void update(double dt) {
    if (gameOver || paused) {
      return; // If game over or paused, don't update the snake
    }

    if (waitTimeLeft > 0) {
      waitTimeLeft -= dt;
      return;
    }
    waitTimeLeft = ogWaitBetweenUpdate;
    double newX = 0;
    double newY = 0;
    if (direction == Direction.RIGHT) {
      newX = body[head].x + bodyWidth;
      newY = body[head].y;
    } else if (direction == Direction.LEFT) {
      newX = body[head].x - bodyWidth;
      newY = body[head].y;
    } else if (direction == Direction.UP) {
      newX = body[head].x;
      newY = body[head].y - bodyHeight;
    } else if (direction == Direction.DOWN) {
      newX = body[head].x;
      newY = body[head].y + bodyHeight;
    }
    body[(head + 1) % body.length] = body[tail];
    body[tail] = null;
    head = (head + 1) % body.length;
    tail = (tail + 1) % body.length;

    body[head].x = newX;
    body[head].y = newY;

    if (intersectingWithSelf()) {
      gameOver = true;
      Window.getWindow().changeState(2);
    }
  }

  public boolean intersectingWithRect(Rect rect) {
    for (int i = tail; i != head; i = (i + 1) % body.length) {
      if (detectIntersection(rect, body[i])) {
        return true;
      }
    }
    return false;
  }

  public void grow() {
    double newX = 0;
    double newY = 0;

    if (direction == Direction.RIGHT) {
      newX = body[tail].x - bodyWidth;
      newY = body[tail].y;
    } else if (direction == Direction.LEFT) {
      newX = body[tail].x + bodyWidth;
      newY = body[tail].y;
    } else if (direction == Direction.UP) {
      newX = body[tail].x;
      newY = body[tail].y + bodyHeight;
    } else if (direction == Direction.DOWN) {
      newX = body[tail].x;
      newY = body[tail].y - bodyHeight;
    }

    Rect newBodyPiece = new Rect(newX, newY, bodyWidth, bodyHeight);

    tail--; // Decrement tail index
    if (tail < 0) {
      tail = body.length - 1; // Wrap around to the end of the array
    }
    body[tail] = newBodyPiece;
  }

  public void draw(Graphics2D g2) {
    for (int i = tail; i != head; i = (i + 1) % body.length) {
      Rect piece = body[i];
      double subWidth = (piece.width - 6.0) / 2.0;
      double subHeight = (piece.height - 6.0) / 2.0;

      g2.setColor(Color.BLACK);
      g2.fill(new Rectangle2D.Double(piece.x + 2.0, piece.y + 2.0, subWidth, subHeight));
      g2.fill(new Rectangle2D.Double(piece.x + 4.0 + subWidth, piece.y + 2.0, subWidth, subHeight));
      g2.fill(new Rectangle2D.Double(piece.x + 2.0, piece.y + 4.0 + subHeight, subWidth, subHeight));
      g2.fill(new Rectangle2D.Double(piece.x + 4.0 + subWidth, piece.y + 4.0 + subHeight, subWidth, subHeight));
    }
  }
  public boolean isGameOver() {
    return gameOver;
  }

  public void togglePause() {
    paused = !paused; // Toggle the paused state
  }
}
