import java.util.ArrayList;
import java.util.Random;

/**
 * Handles the logic of the program.
 * @author Thomas Haller
 */
public class NoteFinderLogic {
  private int currentInterval;
  private int maxFrets;
  private int[] note;

  private ArrayList<GuitarString> allowedStrings;
  private GuitarString currentGuitarString;
  private ChromaticScale chromaticScale;

  /**
   * Constructs a new NoteFinderLogic object.
   */
  public NoteFinderLogic() {
    note = new int[2];

    allowedStrings = new ArrayList<>();
    allowedStrings.add(GuitarString.HIGH_E);
    allowedStrings.add(GuitarString.B);
    allowedStrings.add(GuitarString.G);
    allowedStrings.add(GuitarString.D);
    allowedStrings.add(GuitarString.A);
    allowedStrings.add(GuitarString.LOW_E);

    chromaticScale = new ChromaticScale();
  }

  /**
   * Randomly generates the note for the user to find.
   */
  public void setNote() {
    Random rand = new Random();

    note[0] = rand.nextInt(allowedStrings.size());
    note[1] = rand.nextInt(getMaxFrets() - 1);

    currentGuitarString = allowedStrings.get(note[0]);

    currentInterval = note[1];

    System.out.println("Correct Note: " + getCorrectAnswer());
  }

  /**
   * Finds the correct note name of the current note.
   * @return The correct note name.
   */
  public String getCorrectAnswer() {
    return chromaticScale.findNote(getGuitarString(), getInterval());
  }

  /**
   * Limits the guitar strings on which to generate random notes.
   * @param guitarString The GuitarString to be added or removed from allowedStrings
   * @return A boolean value that indicates whether a guitar string was successfully
   * added or removed from allowedStrings
   */
  public boolean filterGuitarStrings(GuitarString guitarString) {
    boolean success = false;

    // If the guitar string is in the ArrayList, then remove it.
    if (allowedStrings.contains(guitarString)) {
      // if it is the only guitar string in the ArrayList, then don't remove it.
      if (allowedStrings.size() > 1) {
        allowedStrings.remove(guitarString);
        success = true;
      }
    } else {
      // If the guitar string is not in the ArrayList, then add it.
      allowedStrings.add(guitarString);
      success = true;
    }

    System.out.println("\nALLOWED STRINGS\n---------------------");
    for (GuitarString tempGuitarString : allowedStrings) {
      System.out.println(tempGuitarString.getStringName());
    }
    System.out.println("---------------------\n");

    return success;
  }

  /**
   * Retrieves the number of frets on guitar's fretboard.
   * Used for placing an upper limit on the interval of the randomly generated note.
   * @return The number of frets on the guitar's fretboard.
   */
  public int getMaxFrets() {
    return maxFrets;
  }

  /**
   * Sets the size of the guitar's fretboard.
   * @param fretNum The number of frets on the guitar's fretboard.
   */
  public void setMaxFrets(int fretNum) {
    this.maxFrets = fretNum;
  }

  /**
   * Retrieves the guitar string that has the current note.
   * @return The guitar string that has the current note.
   */
  public GuitarString getGuitarString() {
    return currentGuitarString;
  }

  /**
   * Retrieves the interval of the current note.
   * @return The interval of the current note.
   */
  public int getInterval() {
    return currentInterval;
  }
}
