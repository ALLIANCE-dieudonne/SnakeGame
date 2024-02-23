import javax.swing.JFrame;
import java.awt.*;
import java.time.Duration;
import java.time.Instant;

public class Window extends JFrame implements Runnable {
  public static Window window = null;
  public boolean isRunning;

  public int currentState;
  public Scene currentScene;

  public KL keyListener = new KL();
  public ML mouseListener = new ML();

  public Window(int width, int height, String title) {
    setSize(width, height);
    setTitle(title);
    setResizable(false);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    addKeyListener(keyListener);
    addMouseListener(mouseListener);
    addMouseMotionListener(mouseListener);

    isRunning = true;
    changeState(0);
  }

  public static Window getWindow() {
    if (Window.window == null) {
      Window.window = new Window(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, Constants.SCREEN_TITLE);
    }

    return Window.window;
  }

  public void close() {
    isRunning = false;
  }

  public void changeState(int newState) {
    currentState = newState;
    double speed = 0; // Initialize speed variable
    switch (currentState) {
      case 0 -> currentScene = new MenuScene(keyListener, mouseListener);
      case 1 -> {
        // Retrieve speed from GameLevelScene if the current scene is GameScene
        if (currentScene instanceof GameLevelScene) {
          speed = ((GameLevelScene) currentScene).getSpeed();
        }
        currentScene = new GameScene(keyListener, speed);
      }
      case 2 -> currentScene = new GameOverScene(keyListener, mouseListener);

      case 3 -> currentScene = new GameLevelScene(keyListener, mouseListener);
      default -> {
        System.out.println("Unknown scene.");
        currentScene = null;
      }
    }
    // Revalidate and repaint the window to reflect the changes
    revalidate();
    repaint();
  }

  public void update(double dt) {
    Image dbImage = createImage(getWidth(), getHeight());
    Graphics dbg = dbImage.getGraphics();
    this.draw(dbg);
    getGraphics().drawImage(dbImage, 0, 0, this);

    currentScene.update(dt);
  }

  public void draw(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    currentScene.draw(g);
  }

  @Override
  public void run() {
    Instant lastFrameTime = Instant.now();
    try {
      while (isRunning) {
        Instant time = Instant.now();
        double deltaTime = Duration.between(lastFrameTime, time).toNanos() * 10E-10;
        lastFrameTime = Instant.now();

        double deltaWanted = 0.0167;
        update(deltaWanted);
        long msToSleep = (long) ((deltaWanted - deltaTime) * 1000);
        if (msToSleep > 0) {
          Thread.sleep(msToSleep);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    this.dispose();
  }
}
