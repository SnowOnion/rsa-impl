package xyz.sonion.rsa.test;

import org.junit.Test;
import xyz.sonion.rsa.MyBigHex;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


/**
 * Created by SnowOnion
 * on 2017-01-23 08:56.
 * To change this template, use Preferences | Editor | File and Code Templates | Includes | File Header.
 */
public class MyBigHexTest {


	MyBigHex x;
	MyBigHex y;
	MyBigHex z;
	MyBigHex w;
//
//	@Before
//	public void setup(){
//		x = new MyBigHex("0010AC"); // 4268
//	}

	@Test
	public void leftShift() throws Exception {
		x = new MyBigHex("AC");
		y = (MyBigHex) x.leftShift(2);
		assertEquals("AC00", y.toString());
		assertEquals("AC", x.toString());
	}

	@Test
	public void cut() throws Exception {
		x = (MyBigHex) new MyBigHex().fromString("FF00C"); //
		y = (MyBigHex) new MyBigHex().fromString("CCA0"); //
		z = (MyBigHex) new MyBigHex().fromString("FF12C"); //
//		System.out.println(x.cut(0,x.getDigits()/2));
//		System.out.println(y.cut(0,y.getDigits()/2));
		assertEquals("C", x.cut(0, x.getDigits() / 2).toString());
		assertEquals("FF0", x.cut(x.getDigits() / 2, x.getDigits()).toString());
		assertEquals("A0", y.cut(0, y.getDigits() / 2).toString());
		assertEquals("2C", z.cut(0, z.getDigits() / 2).toString());
	}

	@Test
	public void add1() throws Exception {
		x = new MyBigHex("AC");
		y = new MyBigHex("1096");
		z = (MyBigHex) x.add(y);
		assertEquals("1142", z.toString());
		assertEquals("AC", x.toString());
		assertEquals("1096", y.toString());
	}

	@Test
	public void add2() throws Exception {
		x = new MyBigHex("AC");
		y = new MyBigHex("FF96");
		z = (MyBigHex) x.add(y);
		assertEquals("10042", z.toString());
		assertEquals("AC", x.toString());
		assertEquals("FF96", y.toString());
	}

	@Test
	public void add12() throws Exception {
		y = new MyBigHex("AC");
		x = new MyBigHex("1096");
		z = (MyBigHex) x.add(y);
		assertEquals("1142", z.toString());
		assertEquals("AC", y.toString());
		assertEquals("1096", x.toString());
	}

	@Test
	public void add22() throws Exception {
		y = new MyBigHex("AC");
		x = new MyBigHex("FF96");
		z = (MyBigHex) x.add(y);
		assertEquals("10042", z.toString());
		assertEquals("AC", y.toString());
		assertEquals("FF96", x.toString());
	}

	@Test
	public void minus0() throws Exception {
		x = new MyBigHex("AC");
		y = new MyBigHex("1096");
		z = (MyBigHex) x.minus(y);
		assertEquals(null, z);
		assertEquals("AC", x.toString());
		assertEquals("1096", y.toString());
	}

	@Test
	public void minus11() throws Exception {
		x = new MyBigHex("AC");
		y = new MyBigHex("1096");
		z = (MyBigHex) y.minus(x);
		assertEquals(new MyBigHex("FEA"), z);
		assertEquals("AC", x.toString());
		assertEquals("1096", y.toString());
	}

	@Test
	public void minus21() throws Exception {
		x = new MyBigHex("FF96");
		y = new MyBigHex("FF96");
		z = (MyBigHex) x.minus(y);
		assertEquals("0", z.toString());
		assertEquals("FF96", x.toString());
		assertEquals("FF96", y.toString());
	}

	@Test
	public void minus31() throws Exception {
		x = new MyBigHex("FF96");
		y = new MyBigHex("EE85");
		z = (MyBigHex) x.minus(y);
		assertEquals("1111", z.toString());
		assertEquals("FF96", x.toString());
		assertEquals("EE85", y.toString());
	}

	@Test
	public void module1() throws Exception {
		x = new MyBigHex("FF96");
		y = new MyBigHex("EE85");
		z = (MyBigHex) x.module(y);
		assertEquals("1111", z.toString());
		assertEquals("FF96", x.toString());
		assertEquals("EE85", y.toString());
	}

	@Test
	public void module2() throws Exception {
		x = new MyBigHex("FF96");
		y = new MyBigHex("100");
		z = (MyBigHex) x.module(y);
		assertEquals("96", z.toString());
		assertEquals("FF96", x.toString());
		assertEquals("100", y.toString());
	}

	@Test
	public void module3() throws Exception {
		x = new MyBigHex("FF96");
		y = new MyBigHex("1");
		z = (MyBigHex) x.module(y);
		assertEquals("0", z.toString());
		assertEquals("FF96", x.toString());
		assertEquals("1", y.toString());
	}

	@Test
	public void module4() throws Exception {
		x = new MyBigHex("FF96");
		y = new MyBigHex("EE85");
		z = (MyBigHex) y.module(x);
		assertEquals(y, z);
		assertEquals("FF96", x.toString());
		assertEquals("EE85", y.toString());
	}

	@Test
	public void module5() throws Exception {
		x = new MyBigHex("FFFF");
		y = new MyBigHex("F");
		z = (MyBigHex) x.module(y);
		assertEquals(new MyBigHex(0), z);
		assertEquals("FFFF", x.toString());
		assertEquals("F", y.toString());
	}

	@Test
	public void module6() throws Exception {
		x = new MyBigHex("800");
		y = new MyBigHex("4D");
		z = (MyBigHex) x.module(y);
		assertEquals(new MyBigHex(46), z);
	}

	@Test
	public void equals() throws Exception {

	}

	@Test
	public void setDigitAt() throws Exception {

	}

	@Test
	public void addDigit() throws Exception {

	}


	@org.junit.Test
	public void fromInteger() throws Exception {

	}

	@org.junit.Test
	public void toLong() throws Exception {
		x = (MyBigHex) new MyBigHex().fromString("0010AC");
		assertEquals(new Long(4268), x.toLong());
	}

	@org.junit.Test
	public void fromString() throws Exception {
		x = (MyBigHex) new MyBigHex().fromString("0010AC");
		y = new MyBigHex();
		y.addDigit(12);
		y.addDigit(10);
		y.addDigit(0);
		y.addDigit(1);
		assertEquals(y, x);
	}

	@org.junit.Test
	public void toStringTest() throws Exception {
		x = (MyBigHex) new MyBigHex().fromString("0010AC");
		assertEquals("10AC", x.toString());
	}

	@org.junit.Test
	public void multiplyDigit1() throws Exception {
		x = (MyBigHex) new MyBigHex().fromString("F9AC"); // 63916
//		System.out.println(x.toLong());
		z = (MyBigHex) x.multiply(12);
		assertEquals("F9AC", x.toString());
		assertEquals(new Long(766992), z.toLong());
	}

	@org.junit.Test
	public void multiplyDigit2() throws Exception {
		x = (MyBigHex) new MyBigHex().fromString("F9AC"); // 63916
//		System.out.println(x.toLong());
		z = (MyBigHex) x.multiply(0);
		assertEquals("F9AC", x.toString());
		assertEquals("0", z.toString());
		assertEquals(new Long(0), z.toLong());
	}

	@org.junit.Test
	public void multiply1() throws Exception {
		x = (MyBigHex) new MyBigHex().fromString("FF9AC"); //
		y = (MyBigHex) new MyBigHex().fromString("CCA0"); //
		System.out.println(x.toLong());
		System.out.println(y.toLong());
		z = (MyBigHex) x.multiply(y);
		assertEquals("FF9AC", x.toString());
		assertEquals("CCA0", y.toString());
		assertEquals(new Long(54843743104L), z.toLong());
	}

	@org.junit.Test
	public void multiply2() throws Exception {
		x = (MyBigHex) new MyBigHex().fromString("FF9AC"); //
		y = (MyBigHex) new MyBigHex().fromString("CC"); //
		System.out.println(x.toLong());
		System.out.println(y.toLong());
		z = (MyBigHex) x.multiply(y);
		assertEquals("FF9AC", x.toString());
		assertEquals("CC", y.toString());
		assertEquals(new Long(213579024), z.toLong());
	}

	@org.junit.Test
	public void multiply3() throws Exception {
		x = (MyBigHex) new MyBigHex().fromString("FF9AC"); //
		y = (MyBigHex) new MyBigHex().fromString("0"); //
		System.out.println(x.toLong());
		System.out.println(y.toLong());
		z = (MyBigHex) x.multiply(y);
		assertEquals("FF9AC", x.toString());
		assertEquals("0", y.toString());
		assertEquals(new Long(0), z.toLong());
	}

	@org.junit.Test
	public void multiply11() throws Exception {
		x = (MyBigHex) new MyBigHex().fromString("FF9AC"); //
		y = (MyBigHex) new MyBigHex().fromString("CCA0"); //
		System.out.println(x.toLong());
		System.out.println(y.toLong());
		z = (MyBigHex) y.multiply(x);
		assertEquals("FF9AC", x.toString());
		assertEquals("CCA0", y.toString());
		assertEquals(new Long(54843743104L), z.toLong());
	}

	@org.junit.Test
	public void multiply21() throws Exception {
		x = (MyBigHex) new MyBigHex().fromString("FF9AC"); //
		y = (MyBigHex) new MyBigHex().fromString("CC"); //
		System.out.println(x.toLong());
		System.out.println(y.toLong());
		z = (MyBigHex) y.multiply(x);
		assertEquals("FF9AC", x.toString());
		assertEquals("CC", y.toString());
		assertEquals(new Long(213579024), z.toLong());
	}

	@org.junit.Test
	public void multiply31() throws Exception {
		x = (MyBigHex) new MyBigHex().fromString("FF9AC"); //
		y = (MyBigHex) new MyBigHex().fromString("0"); //
		System.out.println(x.toLong());
		System.out.println(y.toLong());
		z = (MyBigHex) y.multiply(x);
		assertEquals("FF9AC", x.toString());
		assertEquals("0", y.toString());
		assertEquals(new Long(0), z.toLong());
	}

	@org.junit.Test
	public void powerMod() throws Exception {

	}

	@org.junit.Test
	public void getBase() throws Exception {

	}

	@org.junit.Test
	public void digitAt() throws Exception {

	}

	@Test
	public void compareTo11() throws Exception {
		x = (MyBigHex) new MyBigHex().fromString("FF9AC"); //
		y = (MyBigHex) new MyBigHex().fromString("FF9AB"); //
		assertEquals(1, x.compareTo(y));
	}

	@Test
	public void compareTo12() throws Exception {
		x = (MyBigHex) new MyBigHex().fromString("FF9AC"); //
		y = (MyBigHex) new MyBigHex().fromString("FF9AB"); //
		assertEquals(-1, y.compareTo(x));
	}

	@Test
	public void compareTo13() throws Exception {
		x = (MyBigHex) new MyBigHex().fromString("FF9AC"); //
		y = (MyBigHex) new MyBigHex().fromString("0FF9AB"); //
		assertEquals(-1, y.compareTo(x));
	}

	@Test
	public void compareTo21() throws Exception {
		x = (MyBigHex) new MyBigHex().fromString("EF9AC"); //
		y = (MyBigHex) new MyBigHex().fromString("FF9A"); //
		assertEquals(1, x.compareTo(y));
	}

	@Test
	public void compareTo22() throws Exception {
		x = (MyBigHex) new MyBigHex().fromString("EF9AC"); //
		y = (MyBigHex) new MyBigHex().fromString("FF9A"); //
		assertEquals(-1, y.compareTo(x));
	}

	@Test
	public void compareTo31() throws Exception {
		x = (MyBigHex) new MyBigHex().fromString("FF9AC"); //
		y = (MyBigHex) new MyBigHex().fromString("FF9AC"); //
		assertEquals(0, x.compareTo(y));
	}


	@Test
	public void test() throws Exception {
		ArrayList<Integer> ali = new ArrayList<Integer>();
		ali.add(3);
		ali.add(0, 2);
		for(Integer i : ali) {
			System.out.println(i);
		}
	}


	@Test
	public void halve1() {
		x = (MyBigHex) new MyBigHex().fromString("2");
		y= (MyBigHex) x.halve();
		assertEquals(new MyBigHex(1),y);
	}

	@Test
	public void halve2() {
		x = (MyBigHex) new MyBigHex().fromString("33");
		y= (MyBigHex) x.halve();
		assertEquals(new MyBigHex(25),y);
	}

	@Test
	public void halve3() {
		x = (MyBigHex) new MyBigHex().fromString("0");
		y= (MyBigHex) x.halve();
		assertEquals(new MyBigHex(0),y);
	}

	@Test
	public void halve4() {
		x = (MyBigHex) new MyBigHex().fromString("10");
		y= (MyBigHex) x.halve();
		assertEquals(new MyBigHex(8),y);
	}

	@Test
	public void powerModInner1() {
		x = new MyBigHex(2);//.fromString("2");
		y = new MyBigHex(22);//.fromString("22");
		z = new MyBigHex(77);//.fromString("77");
		w = (MyBigHex) MyBigHex.powerModInner(x, y, z);
		assertEquals(new MyBigHex(37),w); // "25"
	}

}