import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MenuScene extends Scene {
  public KL keyListener;
  public ML mouseListener;
  public BufferedImage title, play, playPressed, exit, exitPressed;
  public Rect playRect, exitRect, titleRect;

  public BufferedImage playCurrentImage, exitCurrentImage;

  public MenuScene(KL keyListener, ML mouseListener) {
    this.keyListener = keyListener;
    this.mouseListener = mouseListener;

    try {
      BufferedImage spreadSheet = ImageIO.read(new File("assets/menuSprite.png"));
      title = spreadSheet.getSubimage(0, 242, 960, 240);
      play = spreadSheet.getSubimage(0, 121, 261, 121);
      playPressed = spreadSheet.getSubimage(264, 121, 261, 121);
      exit = spreadSheet.getSubimage(0, 0, 233, 93);
      exitPressed = spreadSheet.getSubimage(264, 0, 233, 93);

    } catch (Exception e) {
      e.printStackTrace();
    }
    playCurrentImage = play;
    exitCurrentImage = exit;

    titleRect = new Rect(240, 40, 300, 100);
    playRect = new Rect(310, 180, 150, 70);
    exitRect = new Rect(310, 280, 130, 54);
  }


  @Override
  public void update(double dt) {
    if (mouseListener.getX() >= playRect.x && mouseListener.getX() <= playRect.x + playRect.width && mouseListener.getY() >= playRect.y && mouseListener.getY() <= playRect.y + playRect.height) {
      playCurrentImage = playPressed;
      if (mouseListener.isPressed()) {
        Window.getWindow().changeState(3);
      }
    } else {
      playCurrentImage = play;
    }

    if (mouseListener.getX() >= exitRect.x && mouseListener.getX() <= exitRect.x + exitRect.width && mouseListener.getY() >= exitRect.y && mouseListener.getY() <= exitRect.y + exitRect.height) {
      exitCurrentImage = exitPressed;
      if (mouseListener.isPressed()) {
        Window.getWindow().close();
      }
    } else {
      exitCurrentImage = exit;
    }
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(Color.CYAN);
    g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

    g.drawImage(title, (int) titleRect.x, (int) titleRect.y, (int) titleRect.width, (int) titleRect.height, null);
    g.drawImage(playCurrentImage, (int) playRect.x, (int) playRect.y, (int) playRect.width, (int) playRect.height, null);
    g.drawImage(exitCurrentImage, (int) exitRect.x, (int) exitRect.y, (int) exitRect.width, (int) exitRect.height, null);
  }
}
