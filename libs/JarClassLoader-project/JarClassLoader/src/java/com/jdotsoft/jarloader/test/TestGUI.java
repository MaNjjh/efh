/*
 * File: TestGUI.java
 * 
 * Copyright (C) 2011 JDotSoft. All Rights Reserved.
 * 
 * $Id: TestGUI.java,v 1.10 2011/10/26 19:46:20 mg Exp $
 */
package com.jdotsoft.jarloader.test;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jvnet.substance.SubstanceLookAndFeel;

@SuppressWarnings("serial")
public class TestGUI extends JFrame {
    
    static {
        try {
            UIManager.setLookAndFeel(new SubstanceLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("Cannot load LaF: " + e);
        }
    }
    
    private JTextArea ta;
    private JButton btnClose;
    
    public TestGUI() {
        super("JarClassLoader - TestGUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cont = this.getContentPane();
        cont.setLayout(new BorderLayout());
        
        ta = new JTextArea();
        ta.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane sp = new JScrollPane(ta,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        cont.add(sp, BorderLayout.CENTER);
        
        JButton btnClick = new JButton("Click Me");
        btnClick.addActionListener(new ActionListener() {
            // @Override - commented out for Java 1.5
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(null, "Hello!");
            }
        });
        btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            // @Override - commented out for Java 1.5
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JPanel panelBtn = new JPanel(); // FlowLayout
        panelBtn.add(btnClick);
        panelBtn.add(btnClose);
        cont.add(panelBtn, BorderLayout.SOUTH);
        
        // Place frame centered:
        Dimension dmScreen = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dmApp = new Dimension(600, 500);
        int nW = dmApp.width;
        int nH = dmApp.height;
        int nX = (dmScreen.width - nW) / 2;
        int nY = (dmScreen.height - nH) / 2;
        setBounds(nX, nY, nW, nH);
    }
   
    public void test(String[] args) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(500);
        System.setOut(new PrintStream(bos));
        TestConsole app = new TestConsole();
        app.test(args);
        ta.setText(
             "This is JarClassLoader demo (test application).\n\n" +
             "Verify external look-and-feel loading visually.\n" + 
             "Look-and-feel 'Substance' - http://java.net/projects/substance\n\n");
        ta.append(bos.toString());
    }
    
    /**
     * Disable "Close" button in applet.  
     */
    public void disableCloseButton() {
        btnClose.setEnabled(false);
    }
    
	public static void main(String[] args) {
	    TestGUI app = new TestGUI();
	    app.setVisible(true);
	    app.test(args);
	}
	
} // class TestGUI
