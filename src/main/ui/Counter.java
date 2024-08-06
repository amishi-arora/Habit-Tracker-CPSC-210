package ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

// A class representing each habits days-completed counter on the GUI

// Some methods in this class have been built with the help of the following sources: 
// https://youtu.be/FwpREs5ogQE?si=mVTJwKpNNOf2XXXy
// https://youtu.be/ffVY6HGtxH8?si=49KZkEHjFMywSrN1
// https://youtu.be/hBe2eBorQuw?si=ErMWFQmlwljZq_Jg
// https://youtu.be/PD6pd6AMoOI?si=mN8q4hNR9gRy1Dup
// https://youtu.be/5o3fMLPY7qY?si=27bHlGh28PZwxL0y
// https://stackoverflow.com/questions/13963392/add-image-to-joptionpane
// https://stackoverflow.com/questions/16390503/java-swing-getting-input-from-a-jtextfield

public class Counter implements ActionListener {
    private int daysCompleted; 
    private JButton counter; 
    private JPanel panel;
    private String day; 
    private ImageIcon checkMark; 

    //MODIFES: this
    //EFFECTS: Creates a new counter instance
    public Counter(JPanel panel, int daysCompleted) {
        this.daysCompleted = daysCompleted;
        this.panel = panel; 
        counterButton(daysCompleted); 
        checkMark = new ImageIcon("data/checkmark.png"); 

    }

    //REQUIRES: daysCompleted is not negative
    //MODIFIES: this
    //EFFECTS: Creates the counter button displaying the number of days completed
    private void counterButton(int daysCompleted) {
        if (daysCompleted == 1) {
            day = " day";
        } else {
            day = " days";
        }
        counter = new JButton(String.valueOf(daysCompleted) + day); 
        counter.setPreferredSize(new Dimension(70, 20)); 
        counter.setActionCommand("Count");
        counter.addActionListener(this);
        panel.add(counter);
    }

    //MODIFIES: this
    //EFFECTS: When count button is clicked, incrememts number of days completed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Count")) {
            daysCompleted++; 
            panel.remove(counter);
            counterButton(daysCompleted);
            panel.repaint();
            panel.revalidate();
            displayImage(); 
        } 
    }
    
    //EFFECTS: Creates pop up window for when counter button is clicked
    public void displayImage() {
        new JOptionPane(); 

        JOptionPane.showMessageDialog(panel, "Habit Completed", 
                    "Habit Completed", JOptionPane.INFORMATION_MESSAGE, checkMark); 

    }

    public int getDaysCompleted() {
        return daysCompleted; 
    }

    // EFFECTS: Sets days completed to the given int
    public void setDaysCompleted(int n) {
        daysCompleted = n; 
        counterButton(daysCompleted);
    }




}
