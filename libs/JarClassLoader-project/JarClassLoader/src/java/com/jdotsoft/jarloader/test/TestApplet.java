/*
 * File: TestApplet.java
 * 
 * Copyright (C) 2011 JDotSoft. All Rights Reserved.
 * 
 * $Id: TestApplet.java,v 1.2 2011/11/02 14:40:10 mg Exp $
 */
package com.jdotsoft.jarloader.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TestApplet extends JApplet {
   
    private TestGUI appTestGUI;
    
    public static void main(String[] args) {
        System.out.println(TestApplet.class.getName() + " - main()");
        
        // Prepare content pane:
        TestGUI app = new TestGUI();
        app.test(args);
        
        // Put the content into the JFrame:
        JFrame frameApplet = new JFrame("JarClassLoader - TestApplet");
        frameApplet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameApplet.setLayout(new BorderLayout());
        frameApplet.add(app.getContentPane(), "Center");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frameApplet.setSize(600, 500);
        frameApplet.setLocation(
                (dimension.width - frameApplet.getSize().width) / 2, 
                (dimension.height - frameApplet.getSize().height) / 2);
        frameApplet.setVisible(true);
    }

    @Override
    public void init() {
        System.out.println(getClass().getName() + " - init()");
        // Prepare content pane:
        appTestGUI = new TestGUI();
        appTestGUI.disableCloseButton();
        Component contentPane = appTestGUI.getContentPane();
        
        // Prepare parameters and run test:
        final String KEY1 = "key1";
        final String KEY2 = "key2";
        appTestGUI.test(new String[] {
                KEY1 + "=" + this.getParameter(KEY1),
                KEY2 + "=" + this.getParameter(KEY2),
        });
        
        // Add content pane into the panel with border and header (class loader name): 
        JPanel panelMain = new JPanel(); 
        panelMain.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
        panelMain.setLayout(new BorderLayout());
        panelMain.add(contentPane, BorderLayout.CENTER);
        this.add(panelMain);
        
    }
    
    @Override
    public void start() {
        System.out.println(getClass().getName() + " - start()");
    }
    
    @Override
    public void stop() {
        System.out.println(getClass().getName() + " - stop()");
    }
    
    @Override
    public void destroy() {
        System.out.println(getClass().getName() + " - destroy()");
    }
	
} // class TestApplet
