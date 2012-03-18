/*
 * File: NativeCode.java
 * 
 * Copyright (C) 2011 JDotSoft. All Rights Reserved.
 * 
 * $Id: NativeCode.java,v 1.5 2011/10/17 16:56:03 mg Exp $
 */
package com.jdotsoft.jarloader.test;

public class NativeCode {

    private final static String PREFIX = "NativeCode: ";
    private final static String LIBRARY_NAME = "Native";
    private static boolean bLoaded = false;
    
    public native String getString();
    
    public static boolean loadLibrary() {
        if (!bLoaded) {
            try {
                //Runtime.getRuntime().loadLibrary(sLib); // #1 
                System.loadLibrary(LIBRARY_NAME);  // #2
                // #1 and #2 are identical: making a call to 
                //   ClassLoader.loadLibrary0(Class fromClass, final File file)            
                System.out.println(PREFIX + "Library \"" + LIBRARY_NAME + "\" is loaded.");
                bLoaded = true;
                return true;
            } catch (UnsatisfiedLinkError e) {
                System.out.println(PREFIX + "Library \"" + LIBRARY_NAME + "\": " + e);
                return false;
            }
        }
        return false;
    }
    
    public boolean print() {
        if (bLoaded) {
            System.out.println(PREFIX + "Received: \"" + getString() + "\"");
            return true;
        }
        return false;
    } // print()
    
} // class NativeCode
