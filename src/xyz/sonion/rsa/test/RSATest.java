package xyz.sonion.rsa.test;

import javafx.util.Pair;
import org.junit.Test;
import xyz.sonion.rsa.*;

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

}