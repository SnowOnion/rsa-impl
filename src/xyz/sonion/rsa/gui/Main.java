package xyz.sonion.rsa.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by SnowOnion
 * on 2017-01-24 10:09.
 * To change this template, use Preferences | Editor | File and Code Templates | Includes | File Header.
 */
public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("MainForm");
		frame.setContentPane(new RsaDemo().getPanel1());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,500);
		frame.setPreferredSize(new Dimension(1024,768));
		frame.pack();
		frame.setVisible(true);
	}
}
