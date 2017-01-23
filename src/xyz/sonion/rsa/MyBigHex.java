package xyz.sonion.rsa;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
//258
//16...2
//1...0
//1


/**
 * 大的十六进制非负数
 */
public class MyBigHex implements MyBigInteger, Comparable<MyBigHex> {
	/**
	 * hexDigits.get(0)为最低位
	 */
	private List<Integer> hexDigits;
	private static int BASE = 16;

	public MyBigHex() {
//		new MyBigHex(0);
		hexDigits = new ArrayList<Integer>();
	}

	public MyBigHex(Integer i) {
		fromInteger(i);
	}

	public MyBigHex(String s) {
		fromString(s);
	}

	public MyBigHex(MyBigHex h) {
		this.hexDigits = new ArrayList<Integer>(h.hexDigits);
	}

	/**
	 * @param i
	 * @return 0, 1..., 9 if i \in [0,9]<br>
	 * A,...,Z if i \in [10,35]<br>
	 * null otherwise.
	 */
	private Character i2c(int i) {
		if(i >= 0 && i <= 9) {
			return (char) ('0' + i);
		} else if(i >= 10 && i <= 35) {
			return (char) (i - 10 + 'A');
		} else {
			return null;
		}
	}

	/**
	 * Treat 'a' and 'A' equally.
	 *
	 * @param c
	 * @return null if c \not\in {0,1,...,9,a,b,...,z,A,B,...,Z}
	 */
	private Integer c2i(char c) {
		if('0' <= c && c <= '9') {
			return c - '0';
		} else if('a' <= c && c <= 'z') {
			return c - 'a' + 10;
		} else if('A' <= c && c <= 'Z') {
			return c - 'A' + 10;
		} else {
			return null;
		}
	}

	@Override
	public MyBigInteger fromInteger(Integer i) {
		this.hexDigits = new ArrayList<Integer>();
		do {
			Integer mod = i % BASE;
			hexDigits.add(mod);
			i /= BASE;
		} while(i > 0);
		return this;
	}

	@Override
	public Long toLong() {
		// not too big // TODO
		Long result = 0L;
		Long baseNow = 1L;
		for(Integer digit : hexDigits) {
			result += digit * baseNow;
			baseNow *= getBase();
		}
		return result;
	}

	// static method cannot be place in interface QAQ. Java 8 "Extensive method"?
	@Override
	public MyBigInteger fromString(String s) {
		this.hexDigits = new ArrayList<Integer>();
		// s.charAt[s.length()-1] is the least significant digit...
		for(int i = s.length() - 1; i >= 0; i--) {
			Integer maybeI = c2i(s.charAt(i));
			if(maybeI != null) {
				hexDigits.add(maybeI);
			} else {
				return null;
			}
		}

		this.normalize();
		return this;
	}

	/**
	 * // normalize. Remove the heading 0s.
	 * Will change this!
	 */
	public void normalize() {
		// i >= 1: Remain one '0' for number 0.
		for(int i = hexDigits.size() - 1; i >= 1; i--) {
			if(hexDigits.get(i) == 0) {
				hexDigits.remove(i);
			} else {
				break;
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = hexDigits.size() - 1; i >= 0; i--) {
			sb.append(i2c(hexDigits.get(i)));
		}
		return sb.toString();
	}

	@Override
	public MyBigInteger leftShift(int digits) {
		MyBigHex that = new MyBigHex(this);
		for(int i = 0; i < digits; i++) {
			that.hexDigits.add(0, 0);
		}
		return that;
	}

	@Override
	public MyBigInteger cut(int leastDigit, int mostDigit) {
		MyBigHex that = new MyBigHex(this);
		that.hexDigits = that.hexDigits.subList(leastDigit, mostDigit);
		that.normalize();
		return that;
	}

	@Override
	public MyBigInteger add(MyBigInteger that) {
		MyBigInteger shorter, longer;
		if(this.getDigits() < that.getDigits()) {
			shorter = this;
			longer = that;
		} else {
			shorter = that;
			longer = this;
		}
		int lenShort = shorter.getDigits();
		int lenLong = longer.getDigits();

		MyBigHex result = new MyBigHex();
		int inc = 0;
		for(int i = 0; i < lenShort; i++) {
			int c = shorter.digitAt(i) + longer.digitAt(i) + inc;
			int r = c % getBase();
			inc = c / getBase();
			result.addDigit(r);
		}
		for(int i = lenShort; i < lenLong; i++) {
			int c = longer.digitAt(i) + inc;
			int r = c % getBase();
			inc = c / getBase();
			result.addDigit(r);
		}
		if(inc > 0) {
			result.addDigit(inc);
		}
		return result;
	}

	@Override
	public BigInteger minus(MyBigInteger that) {
return null;
	}

	@Override
	public boolean equals(Object that) {
		// TODO not generally correct! Same-base integer only.
		if(that instanceof MyBigHex) {
			if(this.toString().equals(that.toString())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Karatsuba
	 *
	 * @param that
	 * @return
	 */
	@Override
	public MyBigInteger multiply(MyBigInteger that) {
		if(this.getDigits() == 1) {
			return that.multiply(this.digitAt(0));
		} else if(that.getDigits() == 1) {
			return this.multiply(that.digitAt(0));
		} else {
			MyBigInteger shorter, longer;
			if(this.getDigits() < that.getDigits()) {
				shorter = this;
				longer = that;
			} else {
				shorter = that;
				longer = this;
			}
			int lenShort = shorter.getDigits();
			int lenLong = longer.getDigits();
			int splitPos = lenLong / 2;

			if(splitPos >= lenShort) {
				MyBigInteger l1 = longer.cut(0, splitPos);
				MyBigInteger h1 = longer.cut(splitPos, lenLong);
				return h1.multiply(shorter).leftShift(splitPos).add(l1.multiply(shorter));
			} else {
				MyBigInteger l1 = longer.cut(0, splitPos);
				MyBigInteger h1 = longer.cut(splitPos, lenLong);
				MyBigInteger l2 = shorter.cut(0, splitPos);
				MyBigInteger h2 = shorter.cut(splitPos, lenShort);
				MyBigInteger h1h2 = h1.multiply(h2).leftShift(splitPos * 2);
				MyBigInteger h1l2 = h1.multiply(l2).leftShift(splitPos);
				MyBigInteger h2l1 = h2.multiply(l1).leftShift(splitPos);
				MyBigInteger l1l2 = l1.multiply(l2);
				return h1h2.add(h1l2).add(h2l1).add(l1l2);
			}
//			return null;
		}
	}

	/**
	 * Public for Test only.
	 *
	 * @param thatDigit
	 * @return
	 */
	public MyBigInteger multiply(int thatDigit) {
		int len = this.getDigits();
		MyBigHex result = new MyBigHex();
		int inc = 0;
		for(int i = 0; i < len; i++) {
			int c = this.digitAt(i) * thatDigit + inc;
			int r = c % getBase();
			inc = c / getBase();
			result.addDigit(r);
		}
		if(inc > 0) {
			result.addDigit(inc);
		}
		result.normalize();
		return result;
	}

	@Override
	public MyBigInteger powerMod(MyBigInteger power, MyBigInteger module) {

		return null; // TODO
	}

	@Override
	public MyBigInteger powerMod(MyBigInteger power, MyBigInteger p, MyBigInteger q) {
//		MyBigInteger r =
		return null;
	}

	@Override
	public int getBase() {
		return BASE;
	}

	@Override
	public int digitAt(int index) {
		return hexDigits.get(index);
	}

	/***
	 * Debug/Test Only
	 */
	public void setDigitAt(int index, Integer val) {
		hexDigits.set(index, val);
	}

	public void addDigit(Integer val) {
		hexDigits.add(val);
	}

	@Override
	public int getDigits() {
		return hexDigits.size();
	}


	@Override
	public int compareTo(MyBigHex that) {
		// awesome. total order.
		if(this.getDigits() > that.getDigits()) {
			return 1;
		} else if(this.getDigits() < that.getDigits()) {
			return -1;
		} else {
			for(int i = this.getDigits() - 1; i >= 0; i--) {
				if(this.digitAt(i) > that.digitAt(i)) {
					return 1;
				} else if(this.digitAt(i) < that.digitAt(i)) {
					return -1;
				} else {
					continue;
				}
			}
			return 0;
		}
	}
}
