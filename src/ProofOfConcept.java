import components.sequence.Sequence1L;
import components.sequence.Sequence;
import components.random.Random;
import components.random.Random1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class ProofOfConcept {
    private static Sequence<Character> rep;
    private static Random rand;

    private static void createNewRep() {
        rep = new Sequence1L<>();
        rand = new Random1L();
    }
    private static char randomLetter() {
        int letterDistance = (int) (rand.nextDouble() * 26);
        return (char) ('a' + letterDistance);
    }
    //kernel
    public static void add(char letter) {
        rep.add(rep.length(), letter);
    }
    public static char removeAny() {
        int removed = (int) (rand.nextDouble() * rep.length());
        return rep.remove(removed);

    }
    public static int size() {
        return rep.length();
    }
    public static void addAny() {
        char letter = randomLetter();
        rep.add(rep.length(), letter);
    }
    //secondary
    public static void addCan() {
        for (int i = 0; i < 50; i++) {
            addAny();
        }
    }
    public static void eatSpoon() {
        for (int i = 0; i < 20; i++) {
            removeAny();
        }
    }
    public static boolean isEmpty() {
        return size() == 0;
    }
    public static String makeWord(int len) {
        
        String word = "";
        for (int i = 0; i < len; i++) {
            char removed = removeAny();
            word += removed;
        }
        for (int i = 0; i < len; i++) {
            add(word.charAt(i));
        }
        return word;
    }
    //not part of class
    public static void printSoup(SimpleWriter out) {
        String temp = "";
        int soupSize = size();
        for (int i = 0; i < soupSize; i++) {
            char letter = removeAny();
            out.print(letter);
            temp += letter;
        }
        for (int i = 0; i < soupSize; i++) {
            add(temp.charAt(i));
        }
    }
    public static void main(String [] args) {
        SimpleWriter out = new SimpleWriter1L();
        createNewRep();
        out.println(isEmpty());
        addAny();
        add('a');
        printSoup(out);
        out.println();
        addCan();
        out.println(size());
        printSoup(out);
        out.println();
        eatSpoon();
        out.println(size());
        printSoup(out);
        out.println();
        out.println(makeWord(10));
        out.println(makeWord(5));

    }

}
