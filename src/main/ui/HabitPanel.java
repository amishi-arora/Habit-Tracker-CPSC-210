package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


// A class representing a habit added to the habit tracker GUI

// Some methods in this class have been built with the help of the following sources: 
// https://youtu.be/FwpREs5ogQE?si=mVTJwKpNNOf2XXXy
// https://youtu.be/ffVY6HGtxH8?si=49KZkEHjFMywSrN1
// https://youtu.be/hBe2eBorQuw?si=ErMWFQmlwljZq_Jg
// https://youtu.be/PD6pd6AMoOI?si=mN8q4hNR9gRy1Dup
// https://youtu.be/5o3fMLPY7qY?si=27bHlGh28PZwxL0y
// https://stackoverflow.com/questions/13963392/add-image-to-joptionpane
// https://stackoverflow.com/questions/16390503/java-swing-getting-input-from-a-jtextfield

public class HabitPanel implements ActionListener {
    private static final Dimension habitPanelDim = new Dimension((int) HabitTrackerGUI.frameSize.getWidth() - 120, 50);
    private static final Dimension habitDim = new Dimension((int) HabitTrackerGUI.frameSize.getWidth() - 225, 50);

    private JPanel habitPanel;
    private JTextField habit;
    private JButton removeButton;
    private ImageIcon exMark;
    private JPanel content;
    private HabitTrackerGUI htg; 
    private Counter counter; 

    // MODIFIES: this
    // REQUIRES: HabitTrackerGUI is not null
    // EFFECTS: Constructs a new habit panel instance
    public HabitPanel(HabitTrackerGUI htg, String name, int dc) {
        this.htg = htg; 
        exMark = new ImageIcon("data/exMark.png");
        content = htg.getContent();
        habitPanel = new JPanel();
        habit = new JTextField(name);
        habit.getDocument();
        habit.getDocument().putProperty("name", "Text Field");
        habit.setMaximumSize(habitDim);
        habit.setPreferredSize(habitDim);
        habit.setMinimumSize(habitDim);
        habitPanel.setMaximumSize(habitPanelDim);
        habitPanel.setPreferredSize(habitPanelDim);
        habitPanel.setMinimumSize(habitPanelDim);
        habit.setBackground(Color.WHITE);

        habitPanel.add(removeButton());
        habitPanel.add(habit);
        counter = new Counter(habitPanel, dc);
        content.add(habitPanel);

        habit.requestFocus();
        htg.getFrame().repaint();
        htg.getFrame().revalidate();

    }

    // EFFECTS: Returns the current text of the habit panel
    public String getName() {
        return habit.getText();
    }

    // EFFECTS: Creates the remove habit button
    public JButton removeButton() {
        removeButton = new JButton("X");
        removeButton.setPreferredSize(new Dimension(20, 20));
        removeButton.setActionCommand("Delete");
        removeButton.addActionListener(this);
        return removeButton;
    }

    // EFFECTS: Handles reponse when delete button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Delete")) {
            htg.removeHabitFromHabitPanels(this);
            content.remove(habitPanel);
            content.repaint();
            content.revalidate();
            displayRemoveImage();
        }
    }


    // EFFECTS: Creates pop up panel for when habit is removed
    public void displayRemoveImage() {
        new JOptionPane();

        JOptionPane.showMessageDialog(content, "Habit Deleted",
                "Habit Deleted", JOptionPane.INFORMATION_MESSAGE, exMark);
    }

    // EFFECTS: Returns the days completed of the habit panels corresponding counter
    public int getDaysCompleted() {
        return counter.getDaysCompleted(); 
    }

    public Counter getCounter() {
        return counter; 
    }

}
