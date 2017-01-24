package xyz.sonion.rsa;

import javafx.util.Pair;

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

//	public static String encrypt(RsaPublic pub, String m) {
//
//	}

	public static MyBigInteger encrypt(RsaPublic pub, MyBigInteger m) {
		return m.powerMod(pub.getE(),pub.getN());
	}

//	public static String decrypt(RsaPrivate pri, String m) {
//
//	}

	public static MyBigInteger decrypt(RsaSecret sec, MyBigInteger c) {
		return c.powerMod(sec.getD(),sec.getN());
	}

}
