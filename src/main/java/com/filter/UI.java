package com.filter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {

    private final JFrame frame;
    private final JLabel label;
    private final JButton blackAndWhiteBtn;
    private final ImageProccesor image;

    public UI(){
        //Initialize components
        frame = new JFrame("Image Filter");
        label = new JLabel("Welcome to ImageFilter");
        blackAndWhiteBtn = new JButton("Apply Black and White filter");
        image = new ImageProccesor();

        //Set component bounds
        label.setBounds(70,50,200,30);
        blackAndWhiteBtn.setBounds(45,100,200,30);

        // Add action listener to the Black and White button
        blackAndWhiteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                image.selectAndProcessImage();
            }
        });

        // Configure frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(300,200);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Add components to the frame
        frame.add(label);
        frame.add(blackAndWhiteBtn);


    }
    public void run(){

        frame.setVisible(true);
    }

}
