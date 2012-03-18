/*
 * File: LauncherTestApplet.java
 * 
 * Copyright (C) 2011 JDotSoft. All Rights Reserved.
 * 
 * $Id: LauncherTestApplet.java,v 1.2 2011/11/02 14:39:29 mg Exp $
 */
package com.jdotsoft.jarloader.test;

import javax.swing.JApplet;

import com.jdotsoft.jarloader.JarClassLoader;

@SuppressWarnings("serial")
public class LauncherTestApplet extends JApplet {

    private JarClassLoader jcl;
    
    public static void main(String[] args) {
        JarClassLoader jcl = new JarClassLoader();
        try {
            jcl.invokeMain("com.jdotsoft.jarloader.test.TestApplet", args);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    } // main()
    
    @Override
    public void init() {
        System.setProperty("JarClassLoader.logger.level", "INFO");
        System.setProperty("JarClassLoader.logger.area", "JAR,NATIVE,RESOURCE");
        jcl = new JarClassLoader();
        try {
            jcl.initApplet("com.jdotsoft.jarloader.test.TestApplet", this);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void start() {
        jcl.startApplet();
    }
    
    @Override
    public void stop() {
        jcl.stopApplet();
    }
    
    @Override
    public void destroy() {
        jcl.destroyApplet();
    }
} // class LauncherTestApplet
