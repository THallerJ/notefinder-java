/**
 * Responsible for managing the chromatic scale.
 *
 * @author Thomas Haller
 */
public class ChromaticScale {

  public String findNote(GuitarString guitarString, int interval) {
    NoteNode currentNode = makeChromaticScale();
    int fretNum = interval;

    while (fretNum > 12) {
      fretNum -= 12;
    }

    while (!guitarString.getStringName().equals(currentNode.getNote())) {
      currentNode = currentNode.getNextNode();
    }

    while (fretNum >= 0) {
      currentNode = currentNode.getNextNode();
      fretNum--;
    }

    return currentNode.getNote();
  }

  private NoteNode makeChromaticScale() {
    NoteNode rootNode = new NoteNode("C");
    NoteNode cd = new NoteNode("C#/D♭");
    NoteNode d = new NoteNode("D");
    NoteNode de = new NoteNode("D#/E♭");
    NoteNode e = new NoteNode("E");
    NoteNode f = new NoteNode("F");
    NoteNode fg = new NoteNode("F#/G♭");
    NoteNode g = new NoteNode("G");
    NoteNode ga = new NoteNode("G#/A♭");
    NoteNode a = new NoteNode("A");
    NoteNode ab = new NoteNode("A#/B♭");
    NoteNode b = new NoteNode("B");

    rootNode.setNextNode(cd);
    cd.setNextNode(d);
    d.setNextNode(de);
    de.setNextNode(e);
    e.setNextNode(f);
    f.setNextNode(fg);
    fg.setNextNode(g);
    g.setNextNode(ga);
    ga.setNextNode(a);
    a.setNextNode(ab);
    ab.setNextNode(b);
    b.setNextNode(rootNode);

    return rootNode;
  }

  private static class NoteNode {
    private String note;
    private NoteNode nextNode;

    public NoteNode(String note) {
      this.note = note;
      this.nextNode = null;
    }

    public String getNote() {
      return note;
    }

    public NoteNode getNextNode() {
      return nextNode;
    }

    public void setNextNode(NoteNode next) {
      this.nextNode = next;
    }

    @Override
    public String toString() {
      return getNote();
    }
  }
}
