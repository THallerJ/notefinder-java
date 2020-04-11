import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Class responsible for managing the guitar fretboard that appears in the GUI
 *
 * @author Thomas Haller
 */
public class Fretboard extends JPanel {
  private static final int STRING_GAUGE = 2;
  private static final int FRET_WIDTH = 4;

  private NoteFinderLogic logic;

  private int xSize;
  private int ySize;

  private int[] stringYPosition;
  private int[] fretXPosition;

  private int edgeSpacing;
  private int fretSpacing;
  private int stringSpacing;

  private boolean isFullSize;

  /**
   * The constructor for Fretboard.
   * @param xSize The horizontal size of the fretboard.
   * @param ySize The vertical size of the fretboard.
   * @param logic The logic of the fretboard.
   * @param fullSize Whether the fretboard is full-sized.
   */
  public Fretboard(int xSize, int ySize, NoteFinderLogic logic, boolean fullSize) {
    if (ySize % 10 != 0) {
      throw new IllegalArgumentException("ySize must be divisible by 10");
    }
    this.logic = logic;

    isFullSize = fullSize;

    toggleFullSize();

    edgeSpacing = (ySize - STRING_GAUGE - (ySize - STRING_GAUGE) / 10 * 10);
    stringSpacing = (ySize - edgeSpacing - (6 * STRING_GAUGE)) / 5;
    fretSpacing = xSize / logic.getMaxFrets();

    logic.setNote();

    this.xSize = xSize;
    this.ySize = ySize;
  }

  private void drawFretboard(Graphics g) {
    g.setColor(new Color(74, 65, 40));
    g.fillRect(0, 0, xSize, ySize);
  }

  private void drawStrings(Graphics g) {
    g.setColor(Color.GRAY);
    int nextString = edgeSpacing;

    for (int i = 0; i < 6; i++) {
      g.fillRect(0, nextString, xSize, STRING_GAUGE);
      stringYPosition[i] = nextString;
      nextString += stringSpacing;
    }
  }

  private void drawFrets(Graphics g) {
    g.setColor(Color.LIGHT_GRAY);
    int xFret = fretSpacing - FRET_WIDTH;

    for (int i = 0; i < logic.getMaxFrets(); i++) {
      g.fillRect(xFret, 0, FRET_WIDTH, ySize);

      if (i < 9) {
        g.drawString(Integer.toString(i + 1), (xFret - (fretSpacing / 2)), ySize + 20);
      } else {
        g.drawString(Integer.toString(i + 1), (xFret - (fretSpacing / 2) - 4), ySize + 20);
      }

      fretXPosition[i] = xFret;

      xFret = xFret + fretSpacing;
    }
  }

  private void drawInlays(Graphics g) {
    int inlaySize = stringSpacing - 10;
    g.setColor(Color.LIGHT_GRAY);

    for (int i = 0; i < logic.getMaxFrets(); i++) {
      if ((i % 2 == 0) && (i != 0 && i != 10 && i != 12)) {
        g.fillOval(fretXPosition[i] - (fretSpacing / 2) - (inlaySize / 2),
                stringYPosition[2] + 6, inlaySize, inlaySize);
      } else if (i == 11) {
        g.fillOval(fretXPosition[i] - (fretSpacing / 2) - (inlaySize / 2),
                stringYPosition[0] + 6, inlaySize, inlaySize);
        g.fillOval(fretXPosition[i] - (fretSpacing / 2) - (inlaySize / 2),
                stringYPosition[4] + 6, inlaySize, inlaySize);
      }
    }
  }

  /**
   * Draws a note on the fretboard.
   * @param g Graphics object.
   */
  public void drawNote(Graphics g) {
    int noteSize = stringSpacing - 10;

    g.setColor(new Color(60, 213, 96));
    g.fillOval(fretXPosition[logic.getInterval()] - (fretSpacing / 2) - (noteSize / 2),
            stringYPosition[logic.getGuitarString().getStringNum()]
                    - (noteSize / 2), noteSize, noteSize);
  }

  /**
   * Toggles whether the fretboard is full-sized,
   */
  public void toggleFullSize() {
    if (isFullSize) {
      logic.setMaxFrets(24);
    } else {
      logic.setMaxFrets(12);
    }

    stringYPosition = new int[6];
    fretXPosition = new int[logic.getMaxFrets()];

    edgeSpacing = (ySize - STRING_GAUGE - (ySize - STRING_GAUGE) / 10 * 10);
    stringSpacing = (ySize - edgeSpacing - (6 * STRING_GAUGE)) / 5;
    fretSpacing = xSize / logic.getMaxFrets();

    if (logic.getInterval() > 12) {
      logic.setNote();
    }

    repaint();

    isFullSize = !isFullSize;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.setColor(Color.BLACK);
    drawFretboard(g);
    drawFrets(g);
    drawStrings(g);
    drawInlays(g);
    drawNote(g);
  }
}
