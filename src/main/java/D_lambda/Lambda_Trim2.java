package D_lambda;
import java.util.Arrays;
import java.util.Comparator;

public class Lambda_Trim2 {
	public static void main(String[] args) {
		class TrimComparator implements Comparator<String> {
			@Override
			public int compare(String s1, String s2) {
				return s1.trim().compareTo(s2.trim());
			}
		}
		
		String[] words = {"M", "\n\tSkyfall", " Q\t", "\t\tAdele\t"};
		Arrays.sort(words, new TrimComparator());
		System.out.println(Arrays.toString(words));
	}
}
