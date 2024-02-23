import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class Util {
  public static void drawText(Graphics2D g2, String text, int x, int y, Color color, String fontName, int fontStyle, int fontSize) {
    g2.setColor(color);
    g2.setFont(new Font(fontName, fontStyle, fontSize));
    FontRenderContext frc = g2.getFontRenderContext();
    Rectangle2D bounds = g2.getFont().getStringBounds(text, frc);
    int textX = (int) (x - bounds.getWidth() / 2);
    int textY = (int) (y + bounds.getHeight() / 2);
    g2.drawString(text, textX, textY);
  }
}
