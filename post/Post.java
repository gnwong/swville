/*
 *  `Post.java'
 *    A simple GUI Java program that speeds up the creation of
 *    post.php files on somewhereville.com/gnw and constrains 
 *    the style of the files.
 *
 *  Copyright (C)  2013-2014  George Wong
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see http://www.gnu.org/licenses/.
 */

import java.io.Writer;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Post {
  
  private static JFrame frame;
  
  static JTextArea mainField;
  static InfoBox infoBox;

  public JMenuBar MainMenu() {
    // Declaring in one place for ease-of-read
    JMenuBar menuBar;
    JMenuItem menuItem;
    JMenu menu;
    
    menuBar = new JMenuBar();
  
    // File menu
    menu = new JMenu("File");
    menu.setMnemonic('F');
    JMenuItem mFileNew = new JMenuItem("New");
    mFileNew.setMnemonic('n');
    mFileNew.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          if (newFile()) {
            infoBox.setStatus("  New file created");
          } else {
            infoBox.setStatus("  Unable to make new file");
          }
    } } );
    menu.add(mFileNew);
    JMenuItem mFileOpen = new JMenuItem("Open");
    mFileOpen.setMnemonic('o');
    mFileOpen.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          openFile();
    } } );
    menu.add(mFileOpen);
    JMenuItem mFileSave = new JMenuItem("Save");
    mFileSave.setMnemonic('s');
    mFileSave.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          if (saveFile()) {
            infoBox.setStatus("  File saved!");
          } else {
            infoBox.setStatus("  Unable to save file...");
          }
    } } );
    menu.add(mFileSave);
    menu.addSeparator();
    JMenuItem mFileExit = new JMenuItem("Exit");
    mFileExit.setMnemonic('x');
    mFileExit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          frame.dispose();
          System.exit(0);
    } } );
    menu.add(mFileExit);
    menuBar.add(menu);
    
    // Help menu
    menu = new JMenu("Help");
    menu.setMnemonic('H');
    JMenuItem mHelpAbout = new JMenuItem("About");
    mHelpAbout.setMnemonic('A');
    mHelpAbout.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          AboutBox aboutBox = new AboutBox();
    } } );
    menu.add(mHelpAbout);
    menuBar.add(menu);
  
    return menuBar; // `menu' is rendered a funny word, if
                    // you look at it long enough
  }
  
  // Check existing file
  public boolean fileExists() {
    if (mainField.getText().length()!=0) return true;
    if (infoBox.title.length()!=0) return true;
    return false;
  }
  
  // Check for existing file [save], then clear
  public boolean newFile() {
    if (fileExists()) {
      Object[] options = {"Yes", "No", "Cancel"};
      int ans = JOptionPane.showOptionDialog(frame,
                "It appears you have a file open already. Save?",
                "File already open",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                "Cancel");
      if (ans==0) {
        saveFile();
      } else if (ans==1) {
      } else {
        return false;
      }
    }
    
    mainField.setText("");
    infoBox.title = "";
    infoBox.titleField.setText("");
    infoBox.pid = "";
    infoBox.pidField.setText("");
    infoBox.update = "";
    infoBox.updateField.setText("");
    infoBox.length = "";
    infoBox.lengthField.setText("");
    
    return true;
  }
  
  // Check for existing file [save], then load
  public boolean openFile() {
    if (fileExists()) {
      Object[] options = {"Yes", "No", "Cancel"};
      int ans = JOptionPane.showOptionDialog(frame,
                "It appears you have a file open already. Save?",
                "File already open",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                "Cancel");
      if (ans==0) {
        saveFile();
      } else if (ans==1) {
      } else {
        return false;
      }
    }
    
    // TODO 
    
    return true;
  }
  
  // Save everything in the right format
  public boolean saveFile() {
    String saveString = "";
    
    /*
    saveString += "<! BEGIN >\n<?php\n";
    saveString += "$update='" + infoBox.updateField.getText() + "';\n";
    saveString += "$postid='" + infoBox.pidField.getText() + "';\n";
    saveString += "$pTitle='" + infoBox.titleField.getText() + "';\n";
    saveString += "$length=" + infoBox.lengthField.getText() + ";\n";
    saveString += "?>\n<! END >\n\n";
    saveString += "<font face=\"Helvetica\" size=\"3\">\n<br>\n";
    saveString += "<font size=\"5\">" + infoBox.titleField.getText() + "</font>\n";
    saveString += "<br><br>\n<hr width=\"80%\" align=\"left\">\n\n";
    saveString += mainField.getText();
    saveString += "\n\n</font>\n";*/
    
    saveString += "<! BEGIN >\n<?php\n";
    saveString += "$update='" + infoBox.updateField.getText() + "';\n";
    saveString += "$postid='" + infoBox.pidField.getText() + "';\n";
    saveString += "$pTitle='" + infoBox.titleField.getText() + "';\n";
    saveString += "$length=" + infoBox.lengthField.getText() + ";\n";
    saveString += "?>\n<! END >\n\n";
    saveString += "<div class=\"texttitle\">" + infoBox.titleField.getText() + "</div>\n";
    saveString += "<div class=\"textdate\"><?php echo $update; ?></div><br>\n\n";
    saveString += mainField.getText();
    saveString += "\n";
    
    JFileChooser saveChooser = new JFileChooser(System.getProperty("user.dir"));
    FileNameExtensionFilter filter = 
      new FileNameExtensionFilter("php/html", "php", "html");
    saveChooser.setFileFilter(filter);
    int retVal = saveChooser.showSaveDialog(null);
    if (retVal != JFileChooser.APPROVE_OPTION) {
      return false;
    }
    
    Writer fwriter = null;
    try {
      fwriter = new BufferedWriter(new OutputStreamWriter(
        new FileOutputStream(saveChooser.getSelectedFile().getAbsolutePath()), "utf-8"));
      fwriter.write(saveString);
    } catch (IOException e){
      // TODO
    } finally {
      try {
        fwriter.close();
      } catch (Exception e) {}
    }
    
    return true;
  }
  
  // A nice graphics function that sets everything in place
  private static void createGUI() {
    Post me = new Post();
    infoBox = new InfoBox();
  
    frame = new JFrame();
    frame.setTitle("Post Generator | wong1275/swville");
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setMinimumSize(new Dimension(500,650));
    frame.setJMenuBar(me.MainMenu());
    frame.setResizable(false);
    frame.validate();
    frame.setLocationRelativeTo(null);
    frame.setLayout(new BorderLayout());
    frame.add(infoBox, BorderLayout.PAGE_END);
    
    mainField = new JTextArea();
    mainField.setLineWrap(true);
    mainField.setWrapStyleWord(true);
    mainField.setMargin(new Insets(6,6,6,6));
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setViewportView(mainField);
    frame.add(scrollPane, BorderLayout.CENTER);
    
    frame.setVisible(true);
  }
  
  // Because I've decided the entry point should be low-profile
  public static void main(String[] args) {
    createGUI(); 
  }

  // Functions and graphics for the info box at the bottom of the screen
  static class InfoBox extends JPanel {

    JLabel messageBar = new JLabel("  waiting...");
    
    JTextField titleField;
    JTextField pidField;
    JTextField updateField;
    JTextField lengthField;
    
    String title = "";
    String pid = "";
    String update = "";
    String length = "";
    
    public InfoBox() {
      // Housekeeping
      super();
      this.setBorder(BorderFactory.createLoweredBevelBorder());
      this.setLayout(new GridLayout(5,3));
      
      // First row
      this.add(new JLabel("  Title:"));
      titleField = new JTextField();
      this.add(titleField);
      this.add(new JPanel());
      
      // Second row
      this.add(new JLabel("  Post ID:"));
      pidField = new JTextField();
      this.add(pidField);
      JButton pidGen = new JButton("Generate");
      pidGen.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          generatePid();
      } } );
      this.add(pidGen);
      
      // Third row
      this.add(new JLabel("  Update time:"));
      updateField = new JTextField();
      this.add(updateField);
      JButton updateTimeBtn = new JButton("Update");
      updateTimeBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          updateTime();
      } } );
      this.add(updateTimeBtn);
      
      // Fourth row
      this.add(new JLabel("  Length:"));
      lengthField = new JTextField();
      this.add(lengthField);
      JButton updateLengthBtn = new JButton("Update");
      updateLengthBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          genLength();
      } } );
      this.add(updateLengthBtn);
      
      // Guess the row
      this.add(messageBar);
      this.add(new JPanel());
      JButton generateBtn = new JButton("Generate All");
      generateBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          generatePid();
          updateTime();
          genLength();
      } } );
      this.add(generateBtn);
    }
    
    // Sets statusbar with new text
    public void setStatus(String s) {
      messageBar.setText(s);
    }
    
    // Generate post id
    public void generatePid() {
      Calendar now = Calendar.getInstance();
      this.pid = "j";
      this.pid += Integer.toString(now.get(Calendar.YEAR)-1961);
      if (now.get(Calendar.MONTH) < 9) this.pid += "0";
      this.pid += Integer.toString(now.get(Calendar.MONTH)+1);
      if (now.get(Calendar.DAY_OF_MONTH) < 10) this.pid += "0";
      this.pid += Integer.toString(now.get(Calendar.DAY_OF_MONTH));
      pidField.setText(this.pid);
    }
    
    // Sets Post ID internally and displays in field
    public void setPid(String s) {
      this.pid = s;
      pidField.setText(this.pid);
    }
    
    // Updates time internally and displays in field
    public void updateTime() {
      Calendar now = Calendar.getInstance();
      // Formatting
      if (now.get(Calendar.HOUR_OF_DAY) < 10) this.update = "0";
      else this.update = "";
      this.update += Integer.toString(now.get(Calendar.HOUR_OF_DAY)) + ":";
      if (now.get(Calendar.MINUTE) < 10) this.update += "0";
      this.update += Integer.toString(now.get(Calendar.MINUTE)) + " ";
      if (now.get(Calendar.DAY_OF_MONTH) < 10) this.update += "0";
      this.update += Integer.toString(now.get(Calendar.DAY_OF_MONTH)) + "-";
      this.update += getMon(now.get(Calendar.MONTH)) + "-";
      this.update += Integer.toString(now.get(Calendar.YEAR)%100);
      updateField.setText(this.update);
    }
    
    // Finds length, updates internally and displays
    public void genLength() {
      String text = mainField.getText();
      
      // Take care of the null-length case
      if (text.length()==0) {
        this.length = "0";
        lengthField.setText(this.length);
        return;
      }
      
      // We have to strip twice because the first might introduce double spaces surrounding ?
      text = stripString(text);
      text = stripString(text);
      
      // Remove trailing and leading whitespace
      if (text.charAt(0) == ' ') text = text.substring(1);
      if (text.charAt(text.length()-1) == ' ') text = text.substring(0,text.length()-1);
      
      // Count spaces
      int length = 1;
      for (int i=0; i<text.length(); i++) {
        if (text.charAt(i)==' ') length++;
      }
      
      // Update as appropriate
      this.length = Integer.toString(length);
      lengthField.setText(this.length);
    }
    
    // Strip all html tags & repeated spaces
    String stripString(String s) {
      int i=-1;
      char flag = 0b0;
      String stripped = "";
      while (i < s.length()-1) {
        i++;
        // Set flag if we're entering a tag
        if (s.charAt(i)=='<') {
          flag |= 0b1;
          continue;
        }
        // Check for removal of flags
        if (flag>0) {
          if (s.charAt(i)=='>') {
            flag &= ~0b1;
            continue;
          } else if (! (Character.isWhitespace(s.charAt(i)))) {
            flag &= ~0b10;
          }
          if (flag>0) { continue; }
        }
        // Set flag but continue if whitespace
        if (Character.isWhitespace(s.charAt(i))) {
          flag |= 0b10;
          stripped += ' ';
          continue;
        }
        
        stripped += s.charAt(i);
      }
      return stripped;
    }
    
    // Returns 3-char-long month string
    public String getMon(int n) {
      if (n==0) return "Jan";
      else if (n==1) return "Feb";
      else if (n==2) return "Mar";
      else if (n==3) return "Apr";
      else if (n==4) return "May";
      else if (n==5) return "Jun";
      else if (n==6) return "Jul";
      else if (n==7) return "Aug";
      else if (n==8) return "Sep";
      else if (n==9) return "Oct";
      else if (n==10) return "Nov";
      else if (n==11) return "Dec";
      return "NULL";
    }
  }

}

/*
 
 We'll need to get the following from the user:
 
  $update='';  // in format| hh:mm DD-MMM-YY
  $postid='';  // in format| j510120 |unconventional using 2-char month always
  $pTitle='';  // ... however you like
  $length=n;   // where n is the number of words of the BODY (not as string)

 */
