package D_lambda;
import java.util.Arrays;

public class Lambda_Trim1 {
	public static void main(String[] args) {
		String[] words = {"M", "\n\tSkyfall", " Q\t", "\t\tAdele\t"};
		Arrays.sort(words, new TrimComparator());
		System.out.println(Arrays.toString(words));
	}
}
