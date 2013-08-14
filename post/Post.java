/*
 *  `Post.java'
 *    A simple GUI Java program that speeds up the creation of
 *    post.php files on somewhereville.com/gnw and constrains 
 *    the style of the files.
 *
 *  Copyright (C)  2013  George Wong
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

import java.awt.Dimension;
import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
// Future use?
import java.awt.event.*;
import javax.swing.KeyStroke;

public class Post {
  
  private static JFrame frame;

  public JMenuBar MainMenu() {
    /* Declaring in one place for ease-of-read */
    JMenuBar menuBar;
    JMenuItem menuItem;
    JMenu menu;
    
    menuBar = new JMenuBar();
  
    /* File menu */
    menu = new JMenu("File");
    // More items here
    menu.addSeparator();
    menuItem = new JMenuItem("Exit");
    menu.add(menuItem);
    
    menuBar.add(menu);
    
    /* Help menu */
    menu = new JMenu("Help");
    // More to come
    menuItem = new JMenuItem("About");
    menu.add(menuItem);
    
    menuBar.add(menu);
  
    return menuBar; // `menu' is rendered a funny word, if
                    // you look at it long enough
  }
  
  private static void createGUI() {
  
    Post me = new Post();
  
    frame = new JFrame();
    frame.setTitle("Post Generator | wong1275/swville");
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setMinimumSize(new Dimension(500,650));
    frame.setJMenuBar(me.MainMenu());
    frame.setResizable(false);
    frame.setVisible(true);
    
  }
  
  public static void main(String[] args) {
    
    createGUI();
    
  }
}