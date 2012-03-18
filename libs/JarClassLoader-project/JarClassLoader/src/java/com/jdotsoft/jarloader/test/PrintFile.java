/*
 * File: PrintFile.java
 * 
 * Copyright (C) 2011 JDotSoft. All Rights Reserved.
 * 
 * $Id: PrintFile.java,v 1.4 2011/10/17 16:56:03 mg Exp $
 */
package com.jdotsoft.jarloader.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PrintFile {

    private String sFile;
    private String sExpectedContent;
    
    public PrintFile(String sFile) {
        this.sFile = sFile;
    }
    
    public boolean print() {
        final String PREFIX = "PrintFile: ";
        StringBuilder sb = new StringBuilder(); 
        System.out.println(PREFIX + "Reading file \"" + sFile + "\"");
        try {  
            InputStream is = getClass().getClassLoader().getResourceAsStream(sFile);
            if (is == null) {
                throw new FileNotFoundException(sFile);
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            for (int i = 1; ; i++) {
                String s = br.readLine();
                if (s == null) {
                    break;
                }
                if (sb.length() > 0) sb.append("\n");
                sb.append(s);
                System.out.println("    Line-" + i + ": " + s);
            }
            return sb.toString().equals(sExpectedContent);
        } catch(IOException e) {
            System.out.println(PREFIX + e);
            return false;
        }
    } // print()

    public void setsExpectedContent(String sExpectedContent) {
        this.sExpectedContent = sExpectedContent;
    }
    
} // class PrintFile
