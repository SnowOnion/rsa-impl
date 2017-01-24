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
		Pair<RsaPublic, RsaPrivate> rsaPublicRsaSecretPair = RsaUtil.generateKey(1024);
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
		Pair<RsaPublic, RsaPrivate> rsaPublicRsaSecretPair = RsaUtil.generateKey(768);
		RsaPublic pub = rsaPublicRsaSecretPair.getKey();
		RsaPrivate sec = rsaPublicRsaSecretPair.getValue();

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
		Pair<RsaPublic, RsaPrivate> rsaPublicRsaSecretPair = RsaUtil.generateKey(1024);
		RsaPublic pub = rsaPublicRsaSecretPair.getKey();
		RsaPrivate sec = rsaPublicRsaSecretPair.getValue();

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

		Pair<RsaPublic, RsaPrivate> rsaPublicRsaSecretPair = RsaUtil.generateKey(1024);
		RsaPublic pub = rsaPublicRsaSecretPair.getKey();
		RsaPrivate sec = rsaPublicRsaSecretPair.getValue();

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

		Pair<RsaPublic, RsaPrivate> rsaPublicRsaSecretPair = RsaUtil.generateKey(512);
		RsaPublic pub = rsaPublicRsaSecretPair.getKey();
		RsaPrivate sec = rsaPublicRsaSecretPair.getValue();

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

		Pair<RsaPublic, RsaPrivate> rsaPublicRsaSecretPair = RsaUtil.generateKey(512);
		RsaPublic pub = rsaPublicRsaSecretPair.getKey();
		RsaPrivate sec = rsaPublicRsaSecretPair.getValue();

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

	@Test
	public void enc111(){
		String m = "123";
		MyBigInteger n = new MyBigHex("1A19E6B45999A76264D76D8E6BFF757F741CACE1634CB66E853A413AC0EFDC681988DF54218192C0C81E4FCEBC404531009E5EDA94AF55FFAEF43250359EA9606216BCD25B8C4B07E6BE48F481BB6A6F4EF4A676F4394FD5896ED4669DC6C40E4FBCA1F119D5B60F9ACB7989946E6C4766E2E4EBDAF6EC70CD56A61CFE161D719");
		MyBigInteger e = new MyBigHex("10001");
		RsaPublic pub = new RsaPublic(n, e);
		assertEquals("E268E0B0867D90ED79BFF48BAA16CA54FAEF5F00671B30278764C5519A759E2258A80C03DD8810D2EF343D01763BC38F0517291D0FCDDF5E63C29A4980261079310D5532F63F8786B4416E0232B315912DB815BBB790734A84B32476157A043B295FFCF5D3DD549DEB26842896A50BDD170041330910C1442DD9595D6CA96303",RsaUtil.encrypt(pub, m));
	}

	@Test
	public void dec111(){
		String c = "E268E0B0867D90ED79BFF48BAA16CA54FAEF5F00671B30278764C5519A759E2258A80C03DD8810D2EF343D01763BC38F0517291D0FCDDF5E63C29A4980261079310D5532F63F8786B4416E0232B315912DB815BBB790734A84B32476157A043B295FFCF5D3DD549DEB26842896A50BDD170041330910C1442DD9595D6CA96303";
		MyBigInteger d = new MyBigHex("95F3B838E98A98D9EFFEB8AB424AA1FF5A5463E37D597BBDF82A7615D5DC31A6DF0C3A005D7ECC5E4155E3AB145B29281D25BE4C8769CBE8E2788D95C277851E02067CBE1237B4F9DC1075C1AD5699E8A5CB0DB4E69405A5A443FC9028D75D47B6BFC68CEDD0BE8977B820700697485D3CE3C7235B6BCE23080A9D94F3693C01");
		MyBigInteger p = new MyBigHex("F5F88FE4BF08166A6D95D6BCF6B33D3E2B0B4AA17CF8E9BE404BF449264A6C1DE3BF1C0EF547FCD4E32DC90CD0D91DC0C018B6E3D5AA8216CD7C25D9F03EA8B9");
		MyBigInteger q = new MyBigHex("1B2A5834382BF2D93DF8F7EC68D7D865AE8C250FAA30C367BE6846AEE0B6235E9E7E091FE6358D66F2155AD84A393E831C5924EB618C92B6E271CF3AFC8DCB161");
		RsaPrivate sec = new RsaPrivate(p, q, d);
		System.out.println("secret done");
		// want profiling...
		assertEquals("123",RsaUtil.decrypt(sec, c));
	}

	@Test
	public void enc112(){
		String m = "123";
		MyBigInteger n = new MyBigHex("32F7B9F145FF3570CDE4BF5C57789F6C70DCD3C14EE355166D8A8ABC3B79187D14D53503F97D7736FEAA7742569877A00BC12BA2C4181F4DF317DDF4F15AAD5D");
		MyBigInteger e = new MyBigHex("10001");
		RsaPublic pub = new RsaPublic(n, e);
		assertEquals("BE5C0E449E641EBECD17A97FD33277B2A724D09CAA267CDA031BC1BF0991B1B01A310F806E92295A76C211B68F70A82EFE67248E252ED245395BBDE97E6A156",RsaUtil.encrypt(pub, m));
	}

	@Test
	public void dec112(){
		String c = "BE5C0E449E641EBECD17A97FD33277B2A724D09CAA267CDA031BC1BF0991B1B01A310F806E92295A76C211B68F70A82EFE67248E252ED245395BBDE97E6A156";
		MyBigInteger d = new MyBigHex("30487831365367C7FE7536477F61F8FD5D0A05DCBA2428663FCDA05263B0FB5D91BB9245F854A2106DE897A873A62A7AB9B67A838053206EE76E06B7C9A5CFCD");
		MyBigInteger p = new MyBigHex("157B77B78CFC9DE0904BC8DBFCDDD9D88F726D9BD815C2756DD5550B00C7310DB");
		MyBigInteger q = new MyBigHex("25F5F0325F2682DD157D6B7EC06F278607071E53144D6AEDD20140570A261427");
		RsaPrivate sec = new RsaPrivate(p, q, d);
		System.out.println("secret done");
		// want profiling...
		assertEquals("123",RsaUtil.decrypt(sec, c));
	}

	@Test
	public void enc113(){
		String m = "123";
		MyBigInteger n = new MyBigHex("8F39F88DFA94DCA5");
		MyBigInteger e = new MyBigHex("10001");
		RsaPublic pub = new RsaPublic(n, e);
		assertEquals("215AE41698EB39FB",RsaUtil.encrypt(pub, m));
	}

	@Test
	public void dec113(){
		String c = "215AE41698EB39FB";
		MyBigInteger d = new MyBigHex("8A777219B0EDD281");
		MyBigInteger p = new MyBigHex("49B58DC5");
		MyBigInteger q = new MyBigHex("1F170E161");
		RsaPrivate sec = new RsaPrivate(p, q, d);

		MyBigInteger nnn = new MyBigHex("8F39F88DFA94DCA5");
		assertEquals(nnn,sec.getN());

		// want profiling...
		assertEquals("123",RsaUtil.decrypt(sec, c));
	}

	@Test
	public void dec114(){
		String c = "E6850FE32AEF127A";
		MyBigInteger d = new MyBigHex("B76C3119AE2B0191");
		MyBigInteger p = new MyBigHex("8D66F2E7");
		MyBigInteger q = new MyBigHex("1BB42D81D");
		RsaPrivate sec = new RsaPrivate(p, q, d);

//		MyBigInteger nnn = new MyBigHex("8F39F88DFA94DCA5");
//		assertEquals(nnn,sec.getN());

		// want profiling...
		assertEquals("19992019",RsaUtil.decrypt(sec, c));
	}
}