package Q_Optimizations;

import java.util.ArrayList;
import java.util.List;

public class C_WordDictionary {
    private List<String> words = new ArrayList<String>();

    public void add(String word) {
        this.words.add(word.toLowerCase());
    }

    public boolean contains(String word) {
        for (String s : this.words) {
            if (s.toLowerCase().equals(word)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        C_WordDictionary dict = new C_WordDictionary();
        dict.add("Hello");
        dict.add("World");

        System.out.println(dict.contains("hello")); // true
        System.out.println(dict.contains("world")); // true
        System.out.println(dict.contains("java"));  // false
    }
}
