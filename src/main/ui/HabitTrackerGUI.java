package ui;

import javax.swing.*;

import model.Habit;
import model.HabitList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// A class representing the graphical user interface of the application

// Some methods have been built with the help of the following sources: 


public class HabitTrackerGUI implements ActionListener {

    private JFrame frame;

    private HabitPanel habitPanel;
    private JPanel panel;
    private JButton addButton;
    private JButton removeButton;
    private JButton loadButton;
    private JButton quitButton;
    private JPanel content;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private HabitList hl;
    private ImageIcon exMark; 
    private ArrayList<HabitPanel> habitPanels; 


    public static final Dimension frameSize = new Dimension(700, 700);
    private static final String JSON_STORE = "./data/habitList.json";


    //MODIFIES: this
    //EFFECTS: Creates a new habit tracker gui
    public HabitTrackerGUI() {
        hl = new HabitList();
        habitPanels = new ArrayList<>(); 
        exMark = new ImageIcon("data/exMark.png"); 
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        createGUI();
    }

    // MODIFIES: this
    // EFFECTS: Creates the GUI window with buttons
    private void createGUI() {
        frame = new JFrame("Habit Tracker");
        frame.setPreferredSize(frameSize);
        frame.setMaximumSize(frameSize);
        frame.setMinimumSize(new Dimension(300, 300));
        panel = new JPanel();
        panel.setLayout(new BorderLayout(100, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        panel.setBackground(Color.WHITE);

        content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JScrollPane scroll = new JScrollPane(content);

        panel.add(scroll);

        frame.add(panel);
        addButtons();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();

        frame.setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: Creates and adds buttons to the GUI window
    private void addButtons() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.pink);
        addButton = new JButton("Add");
        addButton.setActionCommand("Add");
        addButton.addActionListener(this);
        loadButton = new JButton("Load");
        loadButton.setActionCommand("Load");
        loadButton.addActionListener(this);
        quitButton = new JButton("Quit");
        quitButton.setActionCommand("Quit");
        quitButton.addActionListener(this);
        buttonsPanel.add(addButton);
        buttonsPanel.add(loadButton);
        buttonsPanel.add(quitButton);
        panel.add(buttonsPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: Creates a new habit for the tracker
    private void newHabit() {
        habitPanel = new HabitPanel(this, null);
        habitPanels.add(habitPanel); 
    }

    // REQUIRES: habitPanel can not be null
    // MODIFIES: this
    // EFFECTS: Creates remove button for the given habit panel 
    public void removeButton(JPanel habitPanel) {
        removeButton = new JButton("X");
        removeButton.setPreferredSize(new Dimension(20, 20));
        removeButton.setActionCommand("Delete");
        removeButton.addActionListener(this);
        habitPanel.add(removeButton);
    }

    // MODIFIES: this
    // EFFECTS: Deletes habit when remove button is pressed
    private void deleteHabit(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof Component) {
            Component comp = (Component) source;
            content.remove(comp.getParent());
            frame.repaint();
            frame.revalidate();
        }
    }

    private void addHabitsToHabitList() {
        for (HabitPanel hp : habitPanels) {
            Habit h = new Habit(hp.getName()); 
            hl.addHabit(h); 
        }
    }

    // EFFECTS: Saves the added habits to file 
    private void saveHabits() {
        try {
            jsonWriter.open();
            jsonWriter.write(hl);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            //
        }
    }

    // EFFECTS: Creates pop up asking user if they want to save their habits before quitting, 
    // and quits application
    private void quitApp() {
        int answer = JOptionPane.showConfirmDialog(frame, "Do you want to save your habits?", "Save",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (answer == JOptionPane.NO_OPTION) {
            System.exit(0);

        } else if (answer == JOptionPane.YES_OPTION) {
            addHabitsToHabitList(); 
            saveHabits();
            System.exit(0);
        }

    }

    // MODIFIES: this
    // EFFECTS: Loads habits from file
    private void loadHabits() {
        try {
            hl = jsonReader.readHabits();
            for (Habit h: hl.getHabits()) {
                new HabitPanel(this, h.getHabitName()); 
            }
        } catch (IOException e) {
            //
        }
    }


    // MODIFIES: this
    // EFFECTS: Handles response when any of the buttons are pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add")) {
            newHabit();
        }
        if (e.getActionCommand().equals("Delete")) {
            deleteHabit(e);
            displayRemoveImage();
            frame.repaint(); 
            frame.revalidate(); 
        }
        if (e.getActionCommand().equals("Quit")) {
            quitApp();
        }
        if (e.getActionCommand().equals("Load")) {
            loadHabits();
        }
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getContent() {
        return content;
    }


    // EFFECTS: Creates pop up panel for when habit is removed 
    public void displayRemoveImage() {
        new JOptionPane(); 

        JOptionPane.showMessageDialog(content, "Habit Deleted", 
                    "Habit Deleted", JOptionPane.INFORMATION_MESSAGE, exMark); 
    }

}
