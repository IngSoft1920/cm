package ingsoft1920.cm.dao;

import java.util.List;

public class Test {
	
	public static void main(String[] args) {
		
		List<Integer> list = List.of(1,2,3,4,5,6);
		
		list.stream()
			.map( i -> "Num"+i )
			.forEach( elem -> System.out.println(elem) );
		
		
	}

}
