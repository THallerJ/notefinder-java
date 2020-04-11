import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Responsible for creating the GUI.
 *
 * @author Thomas Haller
 */
public class NoteFinderGui extends JFrame {
  private Timer timer;
  private int width;
  private int height;
  private Fretboard fretboard;
  private NoteFinderLogic logic;
  private JLabel feedbackLabel;
  private Color backgroundColor;

  private ImageIcon selectedIcon;
  private ImageIcon blankIcon;

  /**
   * Constructor for NoteFinderGUI.
   * @param width  The horizontal size of the program's window.
   * @param height The vertical size of the program's window.
   * @param fb     The Fretboard that is displayed in the GUI.
   * @param logic  The logic of the program.
   */
  public NoteFinderGui(int width, int height, Fretboard fb, NoteFinderLogic logic) {
    this.width = width;
    this.height = height;
    this.fretboard = fb;
    this.logic = logic;

    timer = new javax.swing.Timer(800, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        feedbackLabel.setVisible(false);
      }
    });

    selectedIcon = new ImageIcon(getClass().getResource("resources/checked.png"));
    blankIcon = new ImageIcon(getClass().getResource("resources/unchecked.png"));

    backgroundColor = new Color(35, 35, 35);
    createUI();
  }

  private void createUI() {
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setSize(width, height);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setTitle("NoteFinder");
    createComponents();
    this.setVisible(true);
  }

  private void createComponents() {
    JPanel rootPanel = new JPanel();
    rootPanel.setLayout(new BorderLayout(50, 25));
    rootPanel.setBackground(backgroundColor);
    this.add(rootPanel);

    JPanel headerPanel = new JPanel();
    headerPanel.setLayout(new BorderLayout());
    headerPanel.setBackground(backgroundColor);
    rootPanel.add(headerPanel, BorderLayout.NORTH);

    JPanel stringCheckBoxPanel = new JPanel();
    stringCheckBoxPanel.setBackground(backgroundColor);
    headerPanel.add(stringCheckBoxPanel, BorderLayout.WEST);

    createStringCheckboxes(stringCheckBoxPanel);

    JCheckBox toggleSize = new JCheckBox("Full Fretboard", blankIcon, false);
    toggleSize.setHorizontalAlignment(SwingConstants.RIGHT);
    toggleSize.setSelectedIcon(selectedIcon);
    toggleSize.setDisabledIcon(blankIcon);
    toggleSize.setFocusPainted(false);
    toggleSize.setBackground(backgroundColor);
    toggleSize.setForeground(Color.WHITE);
    toggleSize.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        fretboard.toggleFullSize();
      }
    });
    headerPanel.add(toggleSize, BorderLayout.EAST);

    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new GridLayout(2, 1));
    rootPanel.add(bottomPanel, BorderLayout.SOUTH);

    JLabel titleLabel = new JLabel("NoteFinder");
    titleLabel.setFont(new Font("Serif", Font.BOLD, 38));
    titleLabel.setForeground(Color.WHITE);
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    headerPanel.add(titleLabel, BorderLayout.SOUTH);
    titleLabel.setBorder(new EmptyBorder(20, 0, 0, 0));

    rootPanel.add(fretboard, BorderLayout.CENTER);
    fretboard.setBackground(backgroundColor);

    feedbackLabel = new JLabel("0");
    feedbackLabel.setVisible(false);
    feedbackLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
    feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
    feedbackLabel.setBorder(new EmptyBorder(0, 0, 50, 0));
    bottomPanel.add(feedbackLabel);
    bottomPanel.setBackground(backgroundColor);
    bottomPanel.setBorder(new EmptyBorder(0, 0, 200, 0));

    JPanel btnPanel = new JPanel();
    btnPanel.setBackground(backgroundColor);
    createButtons(btnPanel);
    bottomPanel.add(btnPanel);
  }

  private void createButtons(JPanel panel) {
    JButton cBtn = new JButton("C");
    setupButton(cBtn, panel, false);

    JButton cdBtn = new JButton("C#/D♭");
    setupButton(cdBtn, panel, true);

    JButton dBtn = new JButton("D");
    setupButton(dBtn, panel, false);

    JButton deBtn = new JButton("D#/E♭");
    setupButton(deBtn, panel, true);

    JButton eBtn = new JButton("E");
    setupButton(eBtn, panel, false);

    JButton fBtn = new JButton("F");
    setupButton(fBtn, panel, false);

    JButton fgBtn = new JButton("F#/G♭");
    setupButton(fgBtn, panel, true);

    JButton gBtn = new JButton("G");
    setupButton(gBtn, panel, false);

    JButton gaBtn = new JButton("G#/A♭");
    setupButton(gaBtn, panel, true);

    JButton aBtn = new JButton("A");
    setupButton(aBtn, panel, false);

    JButton abBtn = new JButton("A#/B♭");
    setupButton(abBtn, panel, true);

    JButton bBtn = new JButton("B");
    setupButton(bBtn, panel, false);
  }

  private void setupButton(JButton btn, JPanel panel, boolean accidental) {
    if (accidental) {
      btn.setBackground(Color.BLACK);
      btn.setForeground(Color.WHITE);
    } else {
      btn.setBackground(Color.WHITE);
      btn.setForeground(Color.BLACK);
    }

    btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
    btn.setFocusPainted(false);
    btn.addActionListener(new ButtonListener(btn.getText()));
    panel.add(btn);
  }

  private void createStringCheckboxes(JPanel panel) {
    JCheckBox toggleHighE = new JCheckBox(GuitarString.HIGH_E.getStringName(), blankIcon, true);
    setupFilterCheckBoxes(toggleHighE, GuitarString.HIGH_E, panel);

    JCheckBox toggleB = new JCheckBox(GuitarString.B.getStringName(), blankIcon, true);
    setupFilterCheckBoxes(toggleB, GuitarString.B, panel);

    JCheckBox toggleG = new JCheckBox(GuitarString.G.getStringName(), blankIcon, true);
    setupFilterCheckBoxes(toggleG, GuitarString.G, panel);

    JCheckBox toggleD = new JCheckBox(GuitarString.D.getStringName(), blankIcon, true);
    setupFilterCheckBoxes(toggleD, GuitarString.D, panel);

    JCheckBox toggleA = new JCheckBox(GuitarString.A.getStringName(), blankIcon, true);
    setupFilterCheckBoxes(toggleA, GuitarString.A, panel);

    JCheckBox toggleLowE = new JCheckBox(GuitarString.LOW_E.getStringName(), blankIcon, true);
    setupFilterCheckBoxes(toggleLowE, GuitarString.LOW_E, panel);
  }

  private void setupFilterCheckBoxes(JCheckBox checkBox, GuitarString guitarString, JPanel panel) {
    checkBox.setHorizontalAlignment(SwingConstants.LEFT);
    checkBox.setSelectedIcon(selectedIcon);
    checkBox.setDisabledIcon(blankIcon);
    checkBox.setBackground(backgroundColor);
    checkBox.setFocusPainted(false);
    checkBox.setForeground(Color.WHITE);
    checkBox.addActionListener(new FilterCheckBoxListener(guitarString, checkBox));
    panel.add(checkBox);
  }

  /**
   * Determines the text feedback that is displayed after the user selects a note.
   * @param correct Whether the user selected the correct note.
   */
  public void setFeedBack(boolean correct) {
    if (timer.isRunning()) {
      timer.stop();
    }

    if (correct) {
      feedbackLabel.setText("Correct");
      feedbackLabel.setForeground(Color.GREEN);
    } else {
      feedbackLabel.setText("Incorrect");
      feedbackLabel.setForeground(Color.PINK);
    }

    feedbackLabel.setVisible(true);

    timer.start();
  }

  private class FilterCheckBoxListener implements ActionListener {
    private GuitarString guitarString;
    private JCheckBox checkBox;

    public FilterCheckBoxListener(GuitarString guitarString, JCheckBox checkBox) {
      this.guitarString = guitarString;
      this.checkBox = checkBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      boolean success = logic.filterGuitarStrings(guitarString);

      // If a string was successfully added or removed to the list of allowable guitar strings...
      if (success) {
        // if the guitar string that has the current note was selected to be filtered...
        if (guitarString == logic.getGuitarString()) {
          logic.setNote();
          fretboard.repaint();
        }
      } else {
        // The string was not successfully added or removed from the list of allowable strings.
        // Revert the checkbox back to its previous state.
        if (checkBox.isSelected()) {
          checkBox.setSelected(false);
        } else {
          checkBox.setSelected(true);
        }
      }
    }
  }

  private class ButtonListener implements ActionListener {
    private String btnText;

    private ButtonListener(String btnText) {
      this.btnText = btnText;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      // If the user selected the correct note...
      if (btnText.equals(logic.getCorrectAnswer())) {
        logic.setNote();
        fretboard.repaint();
        setFeedBack(true);
      } else {
        setFeedBack(false);
      }
    }
  }
}
