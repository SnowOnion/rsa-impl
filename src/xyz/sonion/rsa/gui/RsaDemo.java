package xyz.sonion.rsa.gui;

import javax.swing.*;

/**
 * Created by SnowOnion
 * on 2017-01-24 10:04.
 * To change this template, use Preferences | Editor | File and Code Templates | Includes | File Header.
 */
public class RsaDemo {
	private JTabbedPane tabbedPane1;
	private JPanel panel1;
	private JTextField textField1;
	private JTextField textField2;
	private JButton buttonEncGenKey;
	private JTextArea textAreaEncPlaintext;
	private JButton buttonEncSavePublicKey;
	private JComboBox comboBoxEncPubKey;
	private JTextArea textAreaEncN;
	private JTextArea textAreaEncE;
	private JButton buttonEncLoadPlaintext;
	private JButton buttonEncEncrypt;
	private JButton buttonEncSaveCiphertext;
	private JTextArea textAreaEncCiphertext;
	private JTextArea textAreaEncd;
	private JTextArea textAreaEncP;
	private JTextArea textAreaEncQ;
	private JButton buttonEncSavePrivateKey;
	private JTextArea textAreaDecD;
	private JTextArea textAreaDecQ;
	private JButton buttonDecLoadPrivateKey;
	private JButton buttonDecLoadCiphertext;
	private JTextArea textAreaDecCiphertext;
	private JButton buttonDecDecrypt;
	private JButton buttonDecSavePlaintext;
	private JTextArea textAreaDecPlaintext;
	private JTextArea textAreaDecP;


	public JTabbedPane getTabbedPane1() {
		return tabbedPane1;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public JTextField getTextField1() {
		return textField1;
	}

	public JTextField getTextField2() {
		return textField2;
	}
}
