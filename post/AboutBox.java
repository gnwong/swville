/*
 *  Copyright (C)  2013-2014 George Wong
 *  GNU General Public License
 */

import java.awt.Insets;
import java.awt.Dimension; 
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;


// Self-explanatory
public class AboutBox {
  private static JFrame ab = new JFrame();
  
  public AboutBox() {
    ab.setTitle("About");
    ab.setMinimumSize(new Dimension(300,200));
    ab.setResizable(false);
    ab.setLayout(new BorderLayout());
    
    JTextArea info = new JTextArea("\"Post.java\"\nCopyright (C) 2013-2014 George Wong\n\nThis project is released under version 3 of the GNU General Public License, which can be found at http://www.gnu.org/licenses/.");
    info.setLineWrap(true);
    info.setWrapStyleWord(true);
    info.setMargin(new Insets(10,10,10,10));
    info.setEditable(false);
    info.setHighlighter(null);
    
    JButton b_OK = new JButton("OK");
    b_OK.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ab.setVisible(false);
        ab.dispose();
    } });
    
    ab.add(info, BorderLayout.CENTER);
    ab.add(b_OK, BorderLayout.PAGE_END);
    ab.validate();
    ab.setLocationRelativeTo(null);
    ab.setVisible(true);
  }
}
