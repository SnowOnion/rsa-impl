package xyz.sonion.rsa;

import javafx.util.Pair;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class RsaUtil {

	public static final MyBigInteger E = new MyBigHex("10001"); // 2^16 + 1

	public static Pair<RsaPublic, RsaSecret> generateKey(int nBitLength) {
		MyBigInteger p = MyMathUtil.generateHexPrime(nBitLength / 2 + 1);
		MyBigInteger q = MyMathUtil.generateHexPrime(nBitLength / 2 + 1);
		MyBigInteger e = E;
		MyBigInteger n = p.multiply(q);
		MyBigInteger r = p.minus(MyBigHex.ONE).multiply(q.minus(MyBigHex.ONE));
		MyBigInteger d = MyMathUtil.modInverse(e, r);

		return new Pair<RsaPublic, RsaSecret>(new RsaPublic(n, e), new RsaSecret(p, q, d));
	}

	public static String encrypt(RsaPublic pub, String m) {
		byte[] byteArray = new byte[0];
		try {
			byteArray = m.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		for(byte b:byteArray){
			System.out.println(b);
		}

		MyBigHex mm = new MyBigHex(byteArray);
		System.out.println("Constructed plain MyBigHex: "+mm);

		MyBigInteger c = RsaUtil.encrypt(pub, mm);
//		System.out.println(c);
//		System.out.println(c.toLong());
		return c.toString();
	}

	public static MyBigInteger encrypt(RsaPublic pub, MyBigInteger m) {
		return m.powerMod(pub.getE(), pub.getN());
	}

	public static String decrypt(RsaSecret sec, String cInHex) {

		MyBigHex cInHexNum = new MyBigHex(cInHex);
		System.out.println("cInHexNum "+ cInHexNum);

		MyBigHex m = (MyBigHex) RsaUtil.decrypt(sec, cInHexNum);

		System.out.println("Constructed decrypt text: "+m);

		byte[] bytes = m.toByteArray();
		for(byte b:bytes){
			System.out.println(b);
		}
//		System.out.println(bytes.length);
//		System.out.println(new String(bytes));
		try {
			return new String(bytes,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static MyBigInteger decrypt(RsaSecret sec, MyBigInteger c) {
		return c.powerMod(sec.getD(), sec.getP(), sec.getQ());
	}

	/**
	 * @param value
	 * @return
	 * @Reference http://stackoverflow.com/questions/7619058/convert-a-byte-array-to-integer-in-java-and-vise-versa
	 */
//	public static byte[] toByteArray(int value) {
//		return  ByteBuffer.allocate(4).putInt(value).array();
//	}
	public static byte[] toByteArray(long value) {
		return ByteBuffer.allocate(8).putLong(value).array();
	}

//	public static byte[] toByteArray(int value) {
//		return new byte[] {
//				(byte)(value >> 24),
//				(byte)(value >> 16),
//				(byte)(value >> 8),
//				(byte)value };
//	}

	public static long fromByteArray(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getLong();
	}

//	public static int fromByteArray(byte[] bytes) {
//		return ByteBuffer.wrap(bytes).getInt();
//	}
//	// packing an array of 4 bytes to an int, big endian
//	public static int fromByteArray(byte[] bytes) {
//		return bytes[0] << 24 | (bytes[1] & 0xFF) << 16 | (bytes[2] & 0xFF) << 8 | (bytes[3] & 0xFF);
//	}

}
