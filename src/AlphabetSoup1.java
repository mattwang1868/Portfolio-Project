import java.util.Iterator;
import java.util.NoSuchElementException;

import components.random.Random;
import components.random.Random1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * {@code AlphabetSoup} represented as a {@code Sequence} of Characters with
 * implementations of primary methods.
 *
 * @convention |$this.elements| = |entries($this.elements)| this.elements can
 * only contain capital letters ('A' to 'Z')
 * @correspondence this = entries($this.elements)
 * Order of this.elements does not matter
 */
public class AlphabetSoup1 extends AlphabetSoupSecondary {

    /*
     * Private members
     */

    /**
     * Elements included in {@code this}.
     */
    private Sequence<Character> elements;

    /**
     * Random number generator to access random elements.
     */
    private Random randomizer;

    /**
     * Number of letters in the alphabet.
     */
    private static final int NUMBER_OF_LETTERS = 26;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.elements = new Sequence1L<>();
        this.randomizer = new Random1L();

    }

    /**
     * Returns a random capital letter using a random double from
     * {@code random}.
     *
     * @param random
     *            random generator
     * @return a random capital letter ('A' to 'Z')
     * @ensures randomLetter = (Random capital letter)
     *
     */
    private static char randomLetter(Random random) {

        int letterDistance = (int) (random.nextDouble() * NUMBER_OF_LETTERS);
        return (char) ('A' + letterDistance);
    }

    /*
     * Constructors
     */

    /**
     * No-argument constructor.
     */
    public AlphabetSoup1() {

        this.createNewRep();
    }

    /*
     * Standard methods
     */

    @Override
    public final AlphabetSoup newInstance() {

        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(AlphabetSoup source) {

        AlphabetSoup1 localSource = (AlphabetSoup1) source;
        this.elements = localSource.elements;
        this.randomizer = localSource.randomizer;
        localSource.createNewRep();
    }

    @Override
    public final void add(char c) {
        this.elements.add(this.elements.length(), Character.toUpperCase(c));
    }

    @Override
    public final char removeAny() {
        int removed = (int) (this.randomizer.nextDouble()
                * this.elements.length());
        return this.elements.remove(removed);

    }

    @Override
    public final int size() {
        return this.elements.length();
    }

    @Override
    public final void addAny() {
        char letter = randomLetter(this.randomizer);
        this.elements.add(this.elements.length(), letter);
    }

    @Override
    public final Iterator<Character> iterator() {
        return new AlphabetSoup1Iterator();
    }


    /**
     * Implementation of {@code Iterator} interface for {@code AlphabetSoup1}.
     */
    private final class AlphabetSoup1Iterator implements Iterator<Character> {

        /**
         * Indices that were not seen by iterator already.
         */
        private Sequence<Integer> unseen;

        /**
         * Random number generator.
         */
        private Random random;

        /**
         * Returns a random number from 0 (inclusive) to range (exclusive).
         * @param random
         *                 random generator
         * @param bound
         *              upper bound of random generation
         * @return a number from 0 to (bound-1) inclusive
         * @ensures randomInRange = random integer from [0, bound)
         */
        private static int randomInRange(Random random, int bound) {
            return (int) (random.nextDouble()
                * bound);
        }
        /**
         * No-argument constructor.
         */
        private AlphabetSoup1Iterator() {
            this.unseen = new Sequence1L<>();
            this.random = new Random1L();
            int soupSize = AlphabetSoup1.this.size();
            for (int i = 0; i < soupSize; i++) {
                this.unseen.add(this.unseen.length(), i);
            }
        }

        @Override
        public boolean hasNext() {

            return this.unseen.length() != 0;
        }

        @Override
        public Character next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            int nextIndex = randomInRange(this.random, this.unseen.length());
            char x = AlphabetSoup1.this.elements.entry(this.unseen.entry(nextIndex));
            this.unseen.remove(nextIndex);
            return x;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException(
                    "remove operation not supported");
        }

    }

}
