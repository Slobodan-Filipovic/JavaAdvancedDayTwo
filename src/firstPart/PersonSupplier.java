package firstPart;

@FunctionalInterface
public interface PersonSupplier <I, J, K>{

	K  createPerson(I name, J  lastName);
}
