package xyz.sonion.rsa.test;

import org.junit.Test;
import xyz.sonion.rsa.MyBigHex;
import xyz.sonion.rsa.MyMathUtil;

import static org.junit.Assert.assertEquals;

/**
 * Created by SnowOnion
 * on 2017-01-24 07:24.
 * To change this template, use Preferences | Editor | File and Code Templates | Includes | File Header.
 */
public class MyMathUtilTest {
	@Test
	public void modInverse() throws Exception {
		MyBigHex a = new MyBigHex(3);
		MyBigHex r = new MyBigHex(160);
		MyBigHex x = MyMathUtil.modInverse(a, r);
		assertEquals(new Long(107), x.toLong());
	}

}