/*
 * File: LauncherTestConsole.java
 * 
 * Copyright (C) 2011 JDotSoft. All Rights Reserved.
 * 
 * $Id: LauncherTestConsole.java,v 1.3 2011/10/17 16:56:03 mg Exp $
 */
package com.jdotsoft.jarloader.test;

import com.jdotsoft.jarloader.JarClassLoader;

public class LauncherTestConsole {

    public static void main(String[] args) {
        JarClassLoader jcl = new JarClassLoader();
        System.out.println("Starting TestConsole...");
        try {
            jcl.invokeMain("com.jdotsoft.jarloader.test.TestConsole", args);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    } // main()
    
} // class LauncherTestConsole
