package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import data.*;

public class UndoButton extends JFrame implements ActionListener{


    private static final long serialVersionUID = 1L;
    
    private JButton blue = new JButton("Blue");
    private JButton red = new JButton("Red");
    private JButton green = new JButton("Green");
    private JButton undo = new JButton("Undo");

    private transient StackGeneric<String> undoStack = new StackRefGeneric<>();

    public UndoButton() {
        Container contentPane = getContentPane();
        setSize(300,200);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);

        blue.setBounds(100,10,100,30);
        contentPane.add(blue);
        red.setBounds(100,50,100,30);
        getContentPane().add(red);
        green.setBounds(100,90,100,30);
        getContentPane().add(green);
        undo.setBounds(100,140,100,30);
        getContentPane().add(undo);

        green.addActionListener(this);
        red.addActionListener(this);
        blue.addActionListener(this);
        undo.addActionListener(this);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setBackgroundColor(String color) {
        if (color.equals("Blue")) {
            getContentPane().setBackground(Color.BLUE);
            undoStack.push("Blue");
        } else if (color.equals("Red")) {
            getContentPane().setBackground(Color.RED);
            undoStack.push("Red");
        } else if (color.equals("Green")){
            getContentPane().setBackground(Color.GREEN);
            undoStack.push("Green");
        } else {
            getContentPane().setBackground(Color.WHITE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String color;
        JButton clickedButton = (JButton) event.getSource();
        String buttonText = clickedButton.getText();
        if (!buttonText.equals("Undo")) {
            setBackgroundColor(buttonText);
        } else {
            if (undoStack.empty()) {
                setBackgroundColor("Undo");
            } else {
                undoStack.pop();
                if (undoStack.empty()) {
                    setBackgroundColor("Undo");
                } else {
                    color = undoStack.pop();
                    setBackgroundColor(color);
                }
            }
        }
    }


    
}
