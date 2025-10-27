package D_lambda;
import java.util.Arrays;
import java.util.Comparator;

public class Lambda_Trim3 {
	public static void main(String[] args) {		
		String[] words = {"M", "\n\tSkyfall", " Q\t", "\t\tAdele\t"};
		
		Arrays.sort(words, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.trim().compareTo(o2.trim());
			}
		});
		
		System.out.println(Arrays.toString(words));
	}
}
