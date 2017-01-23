package xyz.sonion.rsa;

/**
 * Non negative.
 */
public interface MyBigInteger extends Comparable<MyBigInteger> {

	public MyBigInteger fromInteger(Integer i);

	public Long toLong();

	/**
	 * 0123456789ABC...XYZ. Support most base-36.
	 * The base used for converting should == this.getBase()
	 *
	 * @param s
	 * @return null If s does not represent a valid integer in this base.
	 */
	public MyBigInteger fromString(String s);

	@Override
	public String toString();

	public MyBigInteger leftShift(int digits);

	public MyBigInteger cut(int leastDigit, int mostDigit);

	/**
	 * @param that
	 * @return this + that
	 */
	public MyBigInteger add(MyBigInteger that);

	public MyBigInteger minus(MyBigInteger that);

	/**
	 * @param that
	 * @return this * that
	 */
	public MyBigInteger multiply(MyBigInteger that);

	public MyBigInteger multiply(int i);

	public MyBigInteger powerMod(MyBigInteger power, MyBigInteger module);

	MyBigInteger powerMod(MyBigInteger power, MyBigInteger p, MyBigInteger q);


	MyBigInteger module(MyBigInteger m);

	public int getBase();

	/**
	 * digitAt(0) is the least significant digit
	 *
	 * @param index
	 * @return
	 */
	public int digitAt(int index);


	int getDigits();


}
