package firstPart;

/**
 * First interface of the day, simple stuff
 * @author Slobodan
 *
 */
public interface Calculate {

	/**
	 * Learning default method in interface. Since java 8 they added this. Can have multiple default methods.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	default int add(int a, int b){
		return a + b;
	}
	/**
	 * Proof that there can be one or more default methods
	 * @param a
	 * @param b
	 * @return
	 */
	default int substract(int a, int b){
		return a - b;
	}
	/**
	 * Multiplying
	 * @param a
	 * @param b
	 * @return
	 */
	int multiply (int a, int b);
}
