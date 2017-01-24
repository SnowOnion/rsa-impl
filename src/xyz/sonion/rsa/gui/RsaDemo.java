package xyz.sonion.rsa.gui;

import javafx.util.Pair;
import xyz.sonion.rsa.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JTextArea textAreaEncD;
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


	public RsaDemo() {
		buttonEncEncrypt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String m = textAreaEncPlaintext.getText();
				MyBigInteger n = new MyBigHex(textAreaEncN.getText());
				MyBigInteger e = new MyBigHex(textAreaEncE.getText());
				RsaPublic pub = new RsaPublic(n, e);
				textAreaEncCiphertext.setText(RsaUtil.encrypt(pub, m));
			}
		});
		buttonEncGenKey.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Pair<RsaPublic, RsaSecret> rsaPublicRsaSecretPair = RsaUtil.generateKey(1024);
				RsaPublic pub = rsaPublicRsaSecretPair.getKey();
				RsaSecret sec = rsaPublicRsaSecretPair.getValue();

				textAreaEncN.setText(pub.getN().toString());
				textAreaEncE.setText(pub.getE().toString());
				textAreaEncD.setText(sec.getD().toString());
				textAreaEncP.setText(sec.getP().toString());
				textAreaEncQ.setText(sec.getQ().toString());
			}
		});
		buttonDecDecrypt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String c = textAreaDecCiphertext.getText();
				MyBigInteger d = new MyBigHex(textAreaDecD.getText());
				MyBigInteger p = new MyBigHex(textAreaDecP.getText());
				MyBigInteger q = new MyBigHex(textAreaDecQ.getText());
				RsaSecret sec = new RsaSecret(p, q, d);
				textAreaEncCiphertext.setText(RsaUtil.decrypt(sec, c));
			}
		});
	}

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

	public JButton getButtonEncGenKey() {
		return buttonEncGenKey;
	}

	public JTextArea getTextAreaEncPlaintext() {
		return textAreaEncPlaintext;
	}

	public JButton getButtonEncSavePublicKey() {
		return buttonEncSavePublicKey;
	}

	public JComboBox getComboBoxEncPubKey() {
		return comboBoxEncPubKey;
	}

	public JTextArea getTextAreaEncN() {
		return textAreaEncN;
	}

	public JTextArea getTextAreaEncE() {
		return textAreaEncE;
	}

	public JButton getButtonEncLoadPlaintext() {
		return buttonEncLoadPlaintext;
	}

	public JButton getButtonEncEncrypt() {
		return buttonEncEncrypt;
	}

	public JButton getButtonEncSaveCiphertext() {
		return buttonEncSaveCiphertext;
	}

	public JTextArea getTextAreaEncCiphertext() {
		return textAreaEncCiphertext;
	}

	public JTextArea getTextAreaEncD() {
		return textAreaEncD;
	}

	public JTextArea getTextAreaEncP() {
		return textAreaEncP;
	}

	public JTextArea getTextAreaEncQ() {
		return textAreaEncQ;
	}

	public JButton getButtonEncSavePrivateKey() {
		return buttonEncSavePrivateKey;
	}

	public JTextArea getTextAreaDecD() {
		return textAreaDecD;
	}

	public JTextArea getTextAreaDecQ() {
		return textAreaDecQ;
	}

	public JButton getButtonDecLoadPrivateKey() {
		return buttonDecLoadPrivateKey;
	}

	public JButton getButtonDecLoadCiphertext() {
		return buttonDecLoadCiphertext;
	}

	public JTextArea getTextAreaDecCiphertext() {
		return textAreaDecCiphertext;
	}

	public JButton getButtonDecDecrypt() {
		return buttonDecDecrypt;
	}

	public JButton getButtonDecSavePlaintext() {
		return buttonDecSavePlaintext;
	}

	public JTextArea getTextAreaDecPlaintext() {
		return textAreaDecPlaintext;
	}

	public JTextArea getTextAreaDecP() {
		return textAreaDecP;
	}
}
