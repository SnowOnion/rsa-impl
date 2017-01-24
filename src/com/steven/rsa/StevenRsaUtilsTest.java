package com.steven.rsa;

import org.junit.Test;
import xyz.sonion.rsa.MyBigHex;
import xyz.sonion.rsa.MyBigInteger;

import java.math.BigInteger;

/**
 * Created by SnowOnion
 * on 2017-01-24 06:37.
 * To change this template, use Preferences | Editor | File and Code Templates | Includes | File Header.
 */
public class StevenRsaUtilsTest {
	@Test
	public void getPrime1() throws Exception {
		BigInteger p = StevenRsaUtils.getPrime(512);
		BigInteger q = StevenRsaUtils.getPrime(512);
		System.out.println(p.toString(16));
		System.out.println(q.toString(16));

		BigInteger n = p.multiply(q);
		System.out.println(n.toString(16));
	}

	@Test(timeout = 1000)
	public void getPrime11() throws Exception {
		BigInteger p = StevenRsaUtils.getPrime(512);
		BigInteger q = StevenRsaUtils.getPrime(512);
		MyBigHex ph=new MyBigHex(p.toString(16));
		MyBigHex qh=new MyBigHex(q.toString(16));
		System.out.println(ph);
		System.out.println(qh);

		MyBigInteger n = ph.multiply(qh);
		System.out.println(n);
	}

	@Test
	public void getPrime13() throws Exception {
		BigInteger p = StevenRsaUtils.getPrime(512);
		BigInteger q = StevenRsaUtils.getPrime(512);
		MyBigHex ph=new MyBigHex(p.toString(16));
		MyBigHex qh=new MyBigHex(q.toString(16));
		System.out.println(ph);
		System.out.println(qh);

		MyBigInteger n = ph.multiply(qh);
		System.out.println(n);

		MyBigInteger r = ph.minus(MyBigHex.ONE).multiply(qh.minus(MyBigHex.ONE));
		System.out.println(r);

		BigInteger d=new BigInteger("10001",16).modInverse(new BigInteger(r.toString(),16));

		System.out.println(d);
	}

	@Test(timeout = 1000)
	public void getPrime12() throws Exception {
		BigInteger p = StevenRsaUtils.getPrime(383);
		BigInteger q = StevenRsaUtils.getPrime(383);
		MyBigHex ph=new MyBigHex(p.toString(16));
		MyBigHex qh=new MyBigHex(q.toString(16));
		System.out.println(ph);
		System.out.println(qh);

		MyBigInteger n = ph.multiply(qh);
		System.out.println(n);
	}

	@Test
	public void getPrime2() throws Exception {
		BigInteger p = StevenRsaUtils.getPrime2(512);
		BigInteger q = StevenRsaUtils.getPrime2(512);

		System.out.println(p);
		System.out.println(q);
		BigInteger n = p.multiply(q);
		System.out.println(n);
	}

}