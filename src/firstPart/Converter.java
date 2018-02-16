package firstPart;

@FunctionalInterface
/*Funkcional interface nas tera da ubacimo samo jedan method. 
 * An ERROR IS THROWN IF IT IS NOT DONE SO:
 * THE FOLLOWING ERROR: Invalid '@FunctionalInterface' annotation; Converter<I,O> is not a functional interface 
 * Can't hold a second abstract method together with this one
 * Same with functional interface, can only be one
 * Zgodno za poklapanje tipova*/
public interface Converter<I, O> {
	O convert(I from);
	//int someMethod(int a, int b);
	/* Ovo moze
	default void nesto() {
		System.out.println("Hello");
		
	}*/
	
	

}
