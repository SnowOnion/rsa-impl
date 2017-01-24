package xyz.sonion.rsa.test;

import javafx.util.Pair;
import org.junit.Test;
import xyz.sonion.rsa.*;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class RSATest {
	@Test (timeout = 1000)
	public void generateKey1() throws Exception {
		RsaUtil.generateKey(768);
	}

	@Test (timeout = 1000)
	public void generateKey2() throws Exception {
		Pair<RsaPublic, RsaSecret> rsaPublicRsaSecretPair = RsaUtil.generateKey(1024);
		System.out.println(rsaPublicRsaSecretPair.getKey().getN());
		System.out.println(rsaPublicRsaSecretPair.getKey().getE());
		System.out.println(rsaPublicRsaSecretPair.getValue().getD());
	}

	@Test
	public void generateKey3() throws Exception {
		RsaUtil.generateKey(2048);
	}

	@Test
	public void endec1() throws Exception {
		Pair<RsaPublic, RsaSecret> rsaPublicRsaSecretPair = RsaUtil.generateKey(768);
		RsaPublic pub = rsaPublicRsaSecretPair.getKey();
		RsaSecret sec = rsaPublicRsaSecretPair.getValue();

		MyBigHex m = new MyBigHex(2015311962);
		System.out.println(m);

		MyBigInteger c = RsaUtil.encrypt(pub, m);
		System.out.println(c);
//		System.out.println(c.toLong());

		MyBigInteger mm = RsaUtil.decrypt(sec, c);
		System.out.println(mm);
//		System.out.println(mm.toLong());

		assertEquals(m, mm);
	}

	@Test
	public void endec2() throws Exception {
		Pair<RsaPublic, RsaSecret> rsaPublicRsaSecretPair = RsaUtil.generateKey(1024);
		RsaPublic pub = rsaPublicRsaSecretPair.getKey();
		RsaSecret sec = rsaPublicRsaSecretPair.getValue();

		MyBigHex m = new MyBigHex(2015311962);
		System.out.println(m);

		MyBigInteger c = RsaUtil.encrypt(pub, m);
		System.out.println(c);
//		System.out.println(c.toLong());

		MyBigInteger mm = RsaUtil.decrypt(sec, c);
		System.out.println(mm);
//		System.out.println(mm.toLong());

		assertEquals(m, mm);
	}

	/**
	 * http://stackoverflow.com/questions/13747730/implementing-rsa-algorithm-with-java
	 * http://stackoverflow.com/questions/228987/why-does-bytearray-have-a-length-of-22-instead-of-20
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public void endes1() throws UnsupportedEncodingException {
		String source = "文艺复兴以降";
		byte[] byteArray = source.getBytes("UTF-8");
		//		for(Byte b:byteArray){
//			System.out.println(b);
//		}
		System.out.println(new String(byteArray));
		BigInteger bigInteger = new BigInteger(byteArray);
		System.out.println(bigInteger);

		Pair<RsaPublic, RsaSecret> rsaPublicRsaSecretPair = RsaUtil.generateKey(1024);
		RsaPublic pub = rsaPublicRsaSecretPair.getKey();
		RsaSecret sec = rsaPublicRsaSecretPair.getValue();

		MyBigHex m = new MyBigHex(bigInteger.negate().toString(MyBigHex.BASE));
		System.out.println(m);

		MyBigInteger c = RsaUtil.encrypt(pub, m);
		System.out.println(c);
//		System.out.println(c.toLong());

		MyBigInteger mm = RsaUtil.decrypt(sec, c);
		System.out.println(mm);
//		System.out.println(mm.toLong());

		assertEquals(m, mm);


		byte[] bytes = new BigInteger(mm.toString(),16).negate().toByteArray();
		System.out.println(bytes.length);
		System.out.println(new String(bytes));
	}

	@Test
	public void endes3() throws UnsupportedEncodingException {
		String source = "文艺复兴以降";
		byte[] byteArray = source.getBytes("UTF-8");

		Pair<RsaPublic, RsaSecret> rsaPublicRsaSecretPair = RsaUtil.generateKey(512);
		RsaPublic pub = rsaPublicRsaSecretPair.getKey();
		RsaSecret sec = rsaPublicRsaSecretPair.getValue();

		MyBigHex m = new MyBigHex(byteArray);
		System.out.println(m);

		MyBigInteger c = RsaUtil.encrypt(pub, m);
		System.out.println(c);
//		System.out.println(c.toLong());

		MyBigHex mm = (MyBigHex) RsaUtil.decrypt(sec, c);
		System.out.println(mm);
//		System.out.println(mm.toLong());

		assertEquals(m, mm);

		byte[] bytes = mm.toByteArray();
		System.out.println(bytes.length);
		System.out.println(new String(bytes));
	}

	@Test
	public void endes4() throws Exception {
		String source = "文艺复兴以降";

		Pair<RsaPublic, RsaSecret> rsaPublicRsaSecretPair = RsaUtil.generateKey(512);
		RsaPublic pub = rsaPublicRsaSecretPair.getKey();
		RsaSecret sec = rsaPublicRsaSecretPair.getValue();

		String c = RsaUtil.encrypt(pub, source);
		System.out.println(c);
//		System.out.println(c.toLong());

		String mm = RsaUtil.decrypt(sec, c);
		System.out.println(mm);
//		System.out.println(mm.toLong());

		assertEquals(source, mm);
		System.out.println(mm);
	}

	/**
	 * http://stackoverflow.com/questions/13747730/implementing-rsa-algorithm-with-java
	 * http://stackoverflow.com/questions/228987/why-does-bytearray-have-a-length-of-22-instead-of-20
	 * @throws UnsupportedEncodingException
	 */
//	@Test
//	public void endes2() throws UnsupportedEncodingException {
//		String source = "文艺复兴以降";
//		byte[] byteArray = source.getBytes("UTF-8");
//		System.out.println(new String(byteArray));
//
//		ArrayList<byte[]> bss=new ArrayList<byte[]>();
//
//
//		int i = RsaUtil.fromByteArray(byteArray);
//		System.out.println(i);
//		byte[] bytes = RsaUtil.toByteArray(i);
//		System.out.println(bytes.length);
//		System.out.println(new String(bytes));
//
//
//	}

}