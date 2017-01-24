package xyz.sonion.rsa;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 大的十六进制非负数
 */
public class MyBigHex implements MyBigInteger {
	/**
	 * hexDigits.get(0)为最低位
	 */
	private List<Integer> hexDigits;
	public static int BASE = 16;
	public static final MyBigHex ZERO = new MyBigHex(0);
	public static final MyBigHex ONE = new MyBigHex(1);

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

	public MyBigHex(byte[] bytes) {
		this.hexDigits=new MyBigHex(new BigInteger(bytes).negate().toString(BASE)).hexDigits;
	}

	public byte[] toByteArray() {
		return new BigInteger(this.toString(),BASE).toByteArray();
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
		that.normalize();
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
	public MyBigInteger minus(MyBigInteger that) {
		int cmp = this.compareTo(that);
		if(cmp < 0) {
			return null; // 不兹瓷负数……
		} else if(cmp == 0) {
			return new MyBigHex(0);
		} else {
			MyBigInteger shorter = that, longer = this;
			int lenShort = shorter.getDigits();
			int lenLong = longer.getDigits();
			MyBigHex result = new MyBigHex();
			int dec = 0;
			for(int i = 0; i < lenShort; i++) {
				int c = longer.digitAt(i) - shorter.digitAt(i) - dec;
				dec = c < 0 ? 1 : 0;
				int r = (c + getBase()) % getBase();
				result.addDigit(r);
			}
			for(int i = lenShort; i < lenLong; i++) {
				int c = longer.digitAt(i) - dec;
				dec = c < 0 ? 1 : 0;
				int r = (c + getBase()) % getBase();
				result.addDigit(r);
			}
			// dec should not >0 now.
			result.normalize();
			return result;
		}
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
		if(lenShort == 1) {
			return longer.multiply(shorter.digitAt(0));
		} else {

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
		if(thatDigit == 0) {
			return new MyBigHex(0);
		} else {
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
	}

	@Override
	public MyBigInteger powerMod(MyBigInteger power, MyBigInteger module) {
		return powerModInner(this, power, module);
	}

	/**
	 * this ^ power mod (p*q)
	 *
	 * @param power
	 * @param p
	 * @param q
	 * @return
	 */
	@Override
	public MyBigInteger powerMod(MyBigInteger power, MyBigInteger p, MyBigInteger q) {
		MyBigHex one = new MyBigHex(1);

		MyBigInteger r = p.minus(one).multiply(q.minus(one)); // Euler function of prime*prime
		MyBigInteger n = p.multiply(q);

		// power is very large
		if(power.compareTo(r) > 0) {
			// Euler's Theorem
			return powerModInner(this, power.module(r), n);
		}
		// power is small。// goto fastPower.
		else {
			return powerModInner(this, power, n);
		}
	}


	/***
	 * Test only
	 *
	 * @param baseOfPower
	 * @param power
	 * @param n
	 * @return
	 */
	public static MyBigInteger powerModInner(MyBigInteger baseOfPower, MyBigInteger power, MyBigInteger n) {
		final MyBigHex one = new MyBigHex(1);
		final MyBigHex zero = new MyBigHex(0);

		if(power.compareTo(new MyBigHex(one)) == 0) {
			return baseOfPower.module(n);
		} else if(power.compareTo(new MyBigHex(zero)) == 0) {
			return new MyBigHex(1);
		} else {
			MyBigInteger half = power.halve();
			MyBigInteger powerHalve = powerModInner(baseOfPower, half, n);
			if(power.isEven()) {
				return powerHalve.multiply(powerHalve).module(n);
			} else {
				return powerHalve.multiply(powerHalve).multiply(baseOfPower).module(n);
			}
		}
	}

	@Override
	public MyBigInteger module(MyBigInteger m) {
		int thisLen = this.getDigits();
		int mLen = m.getDigits();
		if(this.compareTo(m) < 0) {
			return new MyBigHex(this);
		} else if(this.compareTo(m) == 0) {
			return new MyBigHex(0);
		} else if(thisLen <= mLen + 1) {
			MyBigInteger that = new MyBigHex(this);
			while(that.compareTo(m) >= 0) {
				that = that.minus(m); // many (<=BASE) steps, objects...
			}
			return that;
		} else { // this has more digits than m
			MyBigInteger r = new MyBigHex(0);
			for(int i = thisLen - 1; i >= 0; i--) { // too redundant... TODO
				r = r.leftShift(1).add(new MyBigHex(this.digitAt(i))).module(m);
			}
			return r;
//			int splitPos = thisLen / 2;
//			MyBigInteger lowMod= this.cut(0,splitPos).module(m);
//			MyBigInteger highMod= this.cut(splitPos,thisLen).module(m);
//			MyBigInteger expMod = new MyBigHex(1).leftShift(splitPos).module(m);
//			return lowMod.add(highMod.multiply(expMod)).module(m);
		}
	}

//	public MyBigInteger module(Integer m) {
//
//	}

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
	public int compareTo(MyBigInteger that) {
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


	/**
	 * Hardly to be found to go back to MyBigInteger 23333
	 *
	 * @return
	 */
	@Override
	public boolean isEven() {
		return hexDigits.get(0) % 2 == 0;
	}

	@Override
	public boolean isZero() {
		return hexDigits.size() == 1 && hexDigits.get(0) == 0;
	}

	/**
	 * Then floor.
	 *
	 * @return
	 */
	@Override
	public MyBigInteger halve() {
		MyBigHex that = new MyBigHex();
		int r = 0;
		for(int i = this.getDigits() - 1; i >= 0; i--) {
			int c = (this.digitAt(i) + r * getBase()) / 2;
			r = (this.digitAt(i) + r * getBase()) % 2;
			that.hexDigits.add(0, c);
		}
		that.normalize();
		return that;
	}


//	public byte[] toByteArray() {
//		int byteLen = bitLength()/8 + 1;
//		byte[] byteArray = new byte[byteLen];
//
//		for (int i=byteLen-1, bytesCopied=4, nextInt=0, intIndex=0; i >= 0; i--) {
//			if (bytesCopied == 4) {
//				nextInt = getInt(intIndex++);
//				bytesCopied = 1;
//			} else {
//				nextInt >>>= 8;
//				bytesCopied++;
//			}
//			byteArray[i] = (byte)nextInt;
//		}
//		return byteArray;
//	}
//
//	public BigInteger(byte[] val) {
//		if (val.length == 0)
//			throw new NumberFormatException("Zero length BigInteger");
//
//		if (val[0] < 0) {
//			mag = makePositive(val);
//			signum = -1;
//		} else {
//			mag = stripLeadingZeroBytes(val);
//			signum = (mag.length == 0 ? 0 : 1);
//		}
//		if (mag.length >= MAX_MAG_LENGTH) {
//			checkRange();
//		}
//	}

}
