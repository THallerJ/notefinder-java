/**
 * Starts the program.
 *
 * @author Thomas Haller
 */
public class Main {
  private static final int WINDOW_WIDTH = 1200;
  private static final int WINDOW_HEIGHT = 800;

  /**
   * Starts the program.
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    NoteFinderLogic logic = new NoteFinderLogic();
    Fretboard fb = new Fretboard(WINDOW_WIDTH, 150, logic, false);
    NoteFinderGui gui = new NoteFinderGui(WINDOW_WIDTH, WINDOW_HEIGHT, fb, logic);
    gui.repaint();
  }
}
