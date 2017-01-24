package com.liang;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


/**
 * http://blog.csdn.net/liang5630/article/details/25651491
 */
public class FileChooser extends JFrame implements ActionListener {
	JButton open = null;
	JButton save = null;

	public static void main(String[] args) {
		new FileChooser();
	}

	public FileChooser() {
		open = new JButton("open");
		this.add(open);
		this.setLayout(new FlowLayout());

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		open.addActionListener(this);

		save = new JButton("save");
		this.add(save);
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser fc=new JFileChooser(".");
				int flag=0;
				File f;

				String fileName;

				//设置保存文件对话框的标题
				fc.setDialogTitle("Save File");

				//这里将显示保存文件的对话框
				try {
					flag = fc.showSaveDialog(new Label("保存"));
				} catch (HeadlessException he) {
					System.out.println("Save File Dialog ERROR!");
				}

				//如果按下确定按钮，则获得该文件。
				if (flag == JFileChooser.APPROVE_OPTION) {
					//获得你输入要保存的文件
					f = fc.getSelectedFile();

					//获得文件名
					fileName = fc.getName(f);
					System.out.println(f.getAbsoluteFile());
					//也可以使用fileName=f.getName();
					System.out.println(fileName);


				}
			}
		});

		this.setBounds(400, 200, 100, 100);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFileChooser jfc = new JFileChooser(".");
//		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfc.showDialog(new JLabel(), "选择");
		File file = jfc.getSelectedFile();
		if(file == null) {
			System.out.println("未选择文件");
		} else if(file.isDirectory()) {
			System.out.println("文件夹:" + file.getAbsolutePath());
			System.out.println(jfc.getSelectedFile().getName());
		} else if(file.isFile()) {
			System.out.println("文件:" + file.getAbsolutePath());
			System.out.println(jfc.getSelectedFile().getName());
		}

	}

}  