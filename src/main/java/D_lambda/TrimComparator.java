package D_lambda;
import java.util.Comparator;

class TrimComparator implements Comparator<String> {
	@Override
	public int compare(String s1, String s2) {
		return s1.trim().compareTo(s2.trim());
	}
}