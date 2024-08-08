package ui;

import javax.swing.*;

import model.Habit;
import model.HabitList;
import model.EventLog;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// A class representing the graphical user interface of the application

// Some methods in this class have been built with the help of the following sources: 
// https://youtu.be/FwpREs5ogQE?si=mVTJwKpNNOf2XXXy
// https://youtu.be/ffVY6HGtxH8?si=49KZkEHjFMywSrN1
// https://youtu.be/hBe2eBorQuw?si=ErMWFQmlwljZq_Jg
// https://youtu.be/PD6pd6AMoOI?si=mN8q4hNR9gRy1Dup
// https://youtu.be/5o3fMLPY7qY?si=27bHlGh28PZwxL0y
// https://stackoverflow.com/questions/13963392/add-image-to-joptionpane
// https://stackoverflow.com/questions/16390503/java-swing-getting-input-from-a-jtextfield

public class HabitTrackerGUI implements ActionListener {

    private JFrame frame;

    private JPanel panel;
    private JButton addButton;
    private JButton removeButton;
    private JButton loadButton;
    private JButton quitButton;
    private JPanel content;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private HabitList hl;
    private ImageIcon save; 
    private ArrayList<HabitPanel> habitPanels; 


    public static final Dimension frameSize = new Dimension(700, 700);
    private static final String JSON_STORE = "./data/habitList.json";


    //MODIFIES: this
    //EFFECTS: Creates a new habit tracker gui
    public HabitTrackerGUI() {
        hl = new HabitList();
        habitPanels = new ArrayList<>(); 
        save = new ImageIcon("data/saveicon.png"); 
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
        HabitPanel habitPanel = new HabitPanel(this, null, 0);
        habitPanels.add(habitPanel); 
        hl.addHabit(new Habit(null));
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
    // EFFECTS: Adds the habits that are currently on the tracker to the habit list
    private void addHabitsToHabitList() {
        for (int i = 0; i < hl.getHabits().size(); i++) {
            hl.getHabits().get(i).setName(habitPanels.get(i).getName());
        }
    }

    // MODIFIES: this
    // EFFECTS: Removes habit from the habit list
    public void removeHabitFromHabitPanels(HabitPanel hp) {
        addHabitsToHabitList();
        habitPanels.remove(hp); 
        hl.removeHabit(hl.findHabit(hp.getName()));
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
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, save);
        if (answer == JOptionPane.NO_OPTION) {
            printLog(EventLog.getInstance());
            System.exit(0);

        } else if (answer == JOptionPane.YES_OPTION) {
            addHabitsToHabitList(); 
            saveHabits();
            printLog(EventLog.getInstance());
            System.exit(0);
        }

    }

    // MODIFIES: this
    // EFFECTS: Loads habits from file
    private void loadHabits() {
        try {
            hl = jsonReader.readHabits();
            for (Habit h: hl.getHabits()) {
                HabitPanel hp = new HabitPanel(this, h.getHabitName(), h.getDaysCompleted()); 
                habitPanels.add(hp); 
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

    public void printLog(EventLog el) {
        for (Event next: el) {
            System.out.println(next.toString());
        }
    }


}
