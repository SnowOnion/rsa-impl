package xyz.sonion.rsa;

import com.steven.rsa.StevenRsaUtils;

import java.math.BigInteger;


public class MyMathUtil {

	public static MyBigHex generateHexPrime(int binaryBit) {
		return new MyBigHex(StevenRsaUtils.getPrime(binaryBit).toString(16));
	}

	/***
	 * invoke BigInteger#modInverse........... not mine.
	 * @return a^(-1) (mod m)
	 * @param a
	 * @param m
	 */
	public static MyBigHex modInverse(MyBigInteger a, MyBigInteger m) {
		BigInteger bi = new BigInteger(a.toString(), a.getBase()).modInverse(new BigInteger(m.toString(), m.getBase()));
		return new MyBigHex(bi.toString(16));
	}

//
//	public static MyBigInteger multiplicationInverse(MyBigInteger a, MyBigInteger m) {
//		return cal(a, m, 1);
//	}
//
//	/**
//	 * http://blog.csdn.net/stray_lambs/article/details/52133141
//	 *
//	 * @param a
//	 * @param b
//	 * @param c
//	 * @return
//	 */
//	MyBigInteger cal(MyBigInteger a, MyBigInteger b, MyBigInteger c) {
//		MyBigInteger x, y;
//		MyBigInteger gcd = e_gcd(a, b, x, y);
//		if(!c.module(gcd).isZero()) return null;
//		x=x.multiply(c)
//		x *= c / gcd;
//		b /= gcd;
//		if(b < 0) b = -b;
//		MyBigInteger ans = x % b;
//		if(ans <= 0) ans += b;
//		return ans;
//	}
//
//	MyBigInteger e_gcd(MyBigInteger a, MyBigInteger b, MyBigInteger&x, MyBigInteger&y) {
//		if(b == 0) {
//			x = 1;
//			y = 0;
//			return a;
//		}
//		MyBigInteger ans = e_gcd(b, a % b, x, y);
//		MyBigInteger temp = x;
//		x = y;
//		y = temp - a / b * y;
//		return ans;
//	}
}
