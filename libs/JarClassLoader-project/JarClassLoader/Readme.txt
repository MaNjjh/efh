HowTo setup and test the project in Eclipse environment.
--------------------------------------------------------
1. Unzip JarClassLoader project.

2. Create new Eclipse project "JarClassLoader" from existing source in the folder 
with unzipped files.

3. The project contains ready to use launch configurations from Eclipse 
environment.

TestConsole launch configurations to run the demo application 
com.jdotsoft.jarloader.test.TestConsole:

   - TestConsole.launch to start the demo application using system classloader.    
   - LauncherTestConsole.launch to start the demo application using JarClassLoader.   

TestGUI launch configurations to run the demo application 
com.jdotsoft.jarloader.test.TestGUI:

   - TestGUI.launch to start the demo application using system classloader.    
   - LauncherTestGUI.launch to start the demo application using JarClassLoader.   

TestApplet launch configurations to run the demo application 
com.jdotsoft.jarloader.test.TestApplet:

   - TestApplet.launch to start the demo application using system classloader.    
   - LauncherTestApplet.launch to start the demo application using JarClassLoader.   

To execute launch configuration in Eclipse:
   - Right click file with extension ".launch" 
   - Select menu option "Run As" and select this launch.

Using JarClassLoader launchers is transparent in Eclipse environment
because all classes (and resources) are loaded from a file system and 
JarClassLoader delegates class loading to system class loader.

All launch configurations have an additional entry "project folder" in 
a classpath. This is required to mimic JAR access to demo "resources/*.txt" files.

All launch configurations have VM argument "-Djava.library.path=lib-native" 
to load demo native libraries.

4. The project contains "dist" folder to run console demo in Windows 
or Linux and GUI demo in Windows. The folder content is the following:
   JarClassLoaderDemo.jar
   start-test-console.cmd
   start-test-console.sh
   start-test-gui.cmd
   start-test-applet.cmd
   start-test-applet.html
This folder could be recreated by running Ant "build.xml" build.

5. Execute cmd-script on Windows platform or sh-script on Linux from "dist"
to start a demo or html to start demo applet directly from the file system. 
