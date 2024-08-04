package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Counter implements ActionListener{
    private int daysCompleted; 
    private JButton counter; 
    private JPanel panel;
    private String day; 

    public Counter(JPanel panel) {
        daysCompleted = 0;
        this.panel = panel; 
        counterButton(daysCompleted); 
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Count")) {
            daysCompleted ++; 
            panel.remove(counter);
            counterButton(daysCompleted);
            panel.repaint();
            panel.revalidate();
        } 
    }

}
