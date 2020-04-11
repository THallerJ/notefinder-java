/**
 * Represents a guitar string.
 *
 * @author Thomas Haller
 */
public enum GuitarString {
  HIGH_E(0, "E"),
  B(1, "B"),
  G(2, "G"),
  D(3, "D"),
  A(4, "A"),
  LOW_E(5, "E");

  private final int stringNum;
  private final String stringName;

  GuitarString(int stringNum, String stringName) {
    this.stringNum = stringNum;
    this.stringName = stringName;
  }

  public int getStringNum() {
    return stringNum;
  }

  public String getStringName() {
    return stringName;
  }
}
