package ui;

import java.util.ArrayList;
import static javax.swing.ScrollPaneConstants.*; 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Habit;
import model.HabitList;

public class HabitTrackerGUI implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JButton addButton;
    private JButton removeButton;
    private JButton loadButton;
    private JButton quitButton;
    private JPanel content;
    private static final Dimension frameSize = new Dimension(700, 700); 

    public HabitTrackerGUI() {
        createGUI();
    }

    // MODIFIES: this
    // EFFECTS: Creates the GUI window with buttons and added habits
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


    private void newHabit() {
        JPanel habitPanel = new JPanel(); 
        JTextField habit = new JTextField(); 
        Dimension habitPanelDim = new Dimension((int)frameSize.getWidth() - 120, 50); 
        Dimension habitDim = new Dimension((int)frameSize.getWidth() - 150, 50);
        habit.setMaximumSize(habitDim);
        habit.setPreferredSize(habitDim);
        habit.setMinimumSize(habitDim);
        habitPanel.setMaximumSize(habitPanelDim);
        habitPanel.setPreferredSize(habitPanelDim);
        habitPanel.setMinimumSize(habitPanelDim);
        habitPanel.setBackground(Color.BLUE); 
        habit.setBackground(Color.GREEN); 
        habitPanel.add(habit); 
        habit.setAlignmentX(Component.LEFT_ALIGNMENT);

        removeButton(habitPanel); 

        content.add(habitPanel); 


        habit.requestFocus(); 
        frame.repaint();
        frame.revalidate();
    }

    private void removeButton(JPanel habitPanel) {
        removeButton = new JButton("X");
        removeButton.setPreferredSize(new Dimension(20, 20)); 
        removeButton.setActionCommand("Delete");
        removeButton.addActionListener(this);
        habitPanel.add(removeButton);
    }

    private void deleteHabit(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof Component) {
            Component comp = (Component)source; 
            content.remove(comp.getParent());
            frame.repaint();
            frame.revalidate();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add")) {
            newHabit(); 
        } 
        if (e.getActionCommand().equals("Delete")) {
            deleteHabit(e);
        }  
    }



}
