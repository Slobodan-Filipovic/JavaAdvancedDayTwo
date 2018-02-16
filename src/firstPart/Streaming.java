package firstPart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.omg.Messaging.SyncScopeHelper;

 

public class Streaming {
	
	public static int staticInt = 10;
	public static void main(String[] args) {
		 
		/**
		 * Jer interface ne mozemo da instanciramo ukucamo.
		 * Dodamo anonimnu kjlasu i impelemtiramo. Multiply koji je abstraktna metoda u calculate
		 */
		Calculate calculate = new Calculate() {

			/**
			 * Anonimna klasa
			 */
			@Override
			public int multiply(int a, int b) {
				/*Can be accessed here because it is visible inside*/
				 printing();
				 return a * b;
			}
			/**
			 * Overriding default method we made in Calculate interface
			 */
			@Override
			public int add(int a, int b) {
				return a / b;
			}
			
			/**
			 * Added a new method without it being in the interface
			 */
			public void printing() {
				System.out.println("Hello");
			}
			
		};
		System.out.println();
		System.out.println(calculate.add(2, 3));
		System.out.println(calculate.substract(5,4));
		System.out.println(calculate.multiply(2, 3));
		// the printing method can not be accessed like the previous ones because it is not part of the calculate interface
		//System.out.println(calculate.);
		
		
		List<String> names = Arrays.asList("marko", "nikola", "pera", "djura");
		//names.sort(arg0); moze i ovako da se sortira
		Collections.sort(names, new Comparator<String>() {/*alternative with lambda
		(String a1, String a2) -> {return s1.compareTo(a1);}*/
			/*Jos jedna alternative 
			 * names.sort((a1,a2) -> a1.compareTo(a2));*/

			@Override
			public int compare(String o1, String o2) {				 
				return -o1.compareTo(o2); //MINUS MENJA REDOSLED ASC/DESC FAZON
			}
			
		} );//ajde da napisemo koomparator
		
		/*for(String name: names) {
			System.out.println(name);
		}*/
		/*Alternativa od for loop*/
		names.forEach(System.out::println);
		/*Mora da ude wrapper, ne moze primitives
		 * U ovom trenutku smo napravili 
		 * from je parametar, moze biti bleja, inParameter w/e
		 * Ovo je aninimna fukcnija*/
		//Converter<String, Integer> converter = from -> Integer.parseInt(from); duplicate with the one below, so commented out because I lost track of things
		/*Alternativa za ono gore tako da nemamo tri linije koda, sad imamo samo jednu liniju koda.
		public int convertFromString(String s){
			return Integer.parseInt(s)
		}*/
		Converter <String, Integer> converter = x -> Integer.parseInt(x);
		/*Easier for debugging, block method
		 * Converter <String, Integer> converter = x ->{ 
			System.out.println("first line");
			System.out.println("second line");
			return Integer.parseInt(x);
			};*/
		Integer convertedInteger = converter.convert("123");
		System.out.println(convertedInteger);
		/*Praktican primer u upotrebi*/
		//Function <String, Integer> func = x -> Integer.parseInt(x);
		
		/*Ovo nece moci nego cemo morati da pravimo novi funkcionalni interface
		Person p = Person::new;*/
		PersonSupplier<String, String, Person> personSupplier = Person::new;// pokazuje na new Person(x, y) konstruktr
		/*Nismo mogli drugacije da napisemo Person::new jer smo ovde dobili referencu na taj konstruktor ili method. Jer moze i na method*/
		Person p = personSupplier.createPerson("Mika", "Peric");
		
		System.out.println(p);
		
		
		
		/*Vidljivost u okviru lambdi. */
		final int nekiBroj = 3;
		Converter<Integer, String> stringConverter = x -> String.valueOf( x + nekiBroj);
		System.out.println(stringConverter.convert(4));/*x ce biti neka cetvorka a nekiBroj je van ovog lambda izraza. 
		Vidimo da je scope ne samo u okviru ove funkcije nego su lambda svesne i promenljvih van sebe. Dobra praska je staviti
		final int jer moze da se desi sledece i pukne neki broj = 4. Final ili efektivni final, tj da se nece menjati vrednost*/
		
		Converter<Integer, String> stringConverter2 = x -> {
			
			staticInt = 55;
			return String.valueOf(staticInt);
		};
		/*Mozemo da pristupimo staticnim i non staticnim fieldovima jednog objekta.*/
		System.out.println("First :  " + stringConverter.convert(3));
		System.out.println(stringConverter2.convert(5));
		
		
		Predicate<String> stringNotNull = x -> x != null;
		Predicate<String> stringVeciOd10 = x -> x != null && x.length() > 10; //x != null dodato da bi se ovo ispod izbeglo
		System.out.println("String veci od 10: " + stringVeciOd10.test(null) );// exception je bio java.langNullPointerException
		System.out.println("String veci od 10: " + stringVeciOd10.test(""));
		System.out.println("String veci od 10: " + stringVeciOd10.test("123432423wefwef4"));
		System.out.println("String veci od 10: " + stringVeciOd10.test("test"));
		System.out.println(stringVeciOd10.and(stringNotNull).test("234234234234234"));//Example of two Predicates with add		
		//stringVeciOd10.and(other)//da vezujemo predicate, negative da negiramo i da vezujemo sa or-om
		/*kako pozivam filter nad listom*/
		//names.stream().filter(insert predicate here) .collect.Collectors.toList da bi ih pohvatali filtrirane
		
		
		
		Function<String, Integer> toInteger = Integer::parseInt;
		System.out.println(toInteger.apply("123"));//123
		
		Function<Integer, String> toString = String::valueOf;
		System.out.println(toString.apply(123));//123 of string type
		
		/*Bunio se jer default konstruktor nije bio u Person*/
		/*Suplier gets an empty object for us*/
		Supplier<Person> personSupplier1 = Person::new;
		Person novaPersona = personSupplier1.get();//ovo je kao da hoce da naprave Factory pattern, ali u svemu se svede na funkcijalno programiranje
		/* ovo get je tipicna funkcija ove novePersone, tj mozemo nad novom personom da setujemo propertije ali je sada null null*
		 * Suplier prima samo jedan*/
		novaPersona.setName("Deneris");
		novaPersona.setLastName("Targerijan");
		/*mi implementiramo logiku sta consumer treba da uradi*/
		Consumer<Person> greetPerson = x -> System.out.println(x);
		greetPerson.accept(novaPersona);
		
		/*final class that cant be changed. It is used mainly to avoid null values*/
		Optional<String> optional = Optional.of("something");//null "nekaVrednost" baca error
		//Optional<String> optional = null;
		//Optional<String> optional = Optional.ofNullable(null); 
		optional.orElse("somethingelse");/*nece biti ikad accessed, jer nema opcija da sam setujemo u false predhodni tako da nece doci do ovog*/
		System.out.println(optional);
		System.out.println("Optional: " + optional.isPresent());	
		
		
		List<String> stringCollection = new ArrayList<>();
		stringCollection.add("aaa2");
		stringCollection.add("bbb3");
		stringCollection.add("ccc4");
		stringCollection.add("ddd5");
		stringCollection.add("qwe6");
		stringCollection.add("abc333");
		stringCollection.add("DJURA");
		
		/*Predicate stringVeciOd10 above*/
		/*FILTER here, its intuitive, sluzi za filtriranje*/
		List<String> stringoviSaSlovomA = stringCollection.stream().filter(x -> x.startsWith("a")).sorted((x,y) -> -x.compareTo(y)).collect(Collectors.toList());
		
		stringoviSaSlovomA.forEach(x -> System.out.println(x));
		
		/*Where am I going to use stream?
		 * One line of code above vs this below.*/
		System.out.println("Where am I going to use stream");
		for(String tempString : stringoviSaSlovomA) {
			if(!tempString.startsWith("a")) {
				stringoviSaSlovomA.remove(tempString);
			}
		}
		for(String tempString : stringoviSaSlovomA) {
			System.out.println(tempString);
		}
		
		List<Person> ljudi = new ArrayList<>();
		ljudi.add(new Person("Aca","Acic"));
		ljudi.add(new Person("Mika","Mikic"));
		ljudi.add(new Person("Djura"," Djuric"));
		ljudi.add(new Person("Maca","Macic"));
		ljudi.add(new Person("Kuca","Kucic"));
		
		/*Person names in map*/
		ljudi.stream().map(x -> x.getName()).forEach(System.out::println);
		
		System.out.println("booleans");
		boolean ImaNekoKoPocinjeSaD = ljudi.stream().anyMatch(x -> x.getName().startsWith("d"));
		boolean sviSaD = ljudi.stream().anyMatch(x -> x.getName().startsWith("d"));
		System.out.println("Ljudi ima ovoliko: " + ljudi.stream().count());
		
		String adjucentNames = ljudi.stream().map(x -> x.getName()).reduce((x, y) -> x + "|" + y).orElse("nema");
		System.out.println(adjucentNames);
		
		List<Integer> lista = new ArrayList<Integer>();
		//lista.forEach(x -> lista.add()); cant
		
		System.out.println("Check output to see stream parallel: ");
		for(int i = 0; i < 10; i++) {
			lista.add(i);
		}
		/*does it in parallel, thats why output is as it is*/
		lista.parallelStream().forEach(System.out::println);
		System.out.println("End of stream parallel");
		
		Map<String, String> mapaLjudi = ljudi.stream().collect(Collectors.toMap(x -> x.getName(), x -> x.getLastName()));
		mapaLjudi.keySet().forEach(System.out::println);
	}

}
