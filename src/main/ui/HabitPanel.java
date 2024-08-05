package ui;


import java.awt.Color;
import java.awt.Dimension;


import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

// A class representing the habits that are added to the habit tracker

// Some methods have been built with the help of the following sources: 

public class HabitPanel {
    private static final Dimension habitPanelDim = new Dimension((int) HabitTrackerGUI.frameSize.getWidth() - 120, 50);
    private static final Dimension habitDim = new Dimension((int) HabitTrackerGUI.frameSize.getWidth() - 225, 50);

    private JPanel habitPanel;
    private JTextField habit;


    //MODIFIES: this
    //REQUIRES: HabitTrackerGUI is not null
    //EFFECTS: Constructs a new habit panel instance
    public HabitPanel(HabitTrackerGUI htg, String name) {

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

        htg.removeButton(habitPanel);
        habitPanel.add(habit);
        new Counter(habitPanel);
        htg.getContent().add(habitPanel);

        habit.requestFocus();
        htg.getFrame().repaint();
        htg.getFrame().revalidate();

    }

    // EFFECTS: Returns the current text of the habit panel
    public String getName() {
        return habit.getText(); 
    }


}
