package xyz.sonion.rsa.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by SnowOnion
 * on 2017-01-24 10:09.
 * To change this template, use Preferences | Editor | File and Code Templates | Includes | File Header.
 */
public class Main {
	public static void main(String[] args) {
		RsaDemo rsaDemo=new RsaDemo();

		JFrame frame = new JFrame("RsaDemo-LiTianchi-2015311962");
		frame.setContentPane(rsaDemo.getPanel1());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,500);
		frame.setPreferredSize(new Dimension(1024,768));
		frame.pack();
//
//		rsaDemo.getButtonEncGenKey().addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println(111);
//			}
//		});

		frame.setVisible(true);


	}
}
