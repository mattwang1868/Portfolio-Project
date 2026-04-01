/**
* Layered implementations of secondary methods for {@code AlphabetSoup}.
*/
public abstract class AlphabetSoupSecondary implements AlphabetSoup {
    /*
    void add(char c);
    void addAny();
    int size();
    char removeAny();
    */

    //Common Methods
    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public String toString() {
        int size = this.size();
        StringBuilder str = new StringBuilder();
        AlphabetSoup temp = this.newInstance();
        for (int i = 0; i < size; i++) {
            char next = this.removeAny();
            str.append(next);
            temp.add(next);
        }
        this.transferFrom(temp);
        return str.toString();
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public boolean equals(Object obj) {
        boolean equal = false;
        if (obj instanceof AlphabetSoup) {
            AlphabetSoup a = (AlphabetSoup) obj;
            if (a.size() == this.size()) {
                int size = this.size();
                StringBuilder strThis = new StringBuilder();
                StringBuilder strA = new StringBuilder();
                AlphabetSoup tempThis = this.newInstance();
                AlphabetSoup tempA = this.newInstance();
                //copy a and this into strings
                for (int i = 0; i < size; i++) {
                    char nextThis = this.removeAny();
                    char nextA = a.removeAny();
                    strThis.append(nextThis);
                    strA.append(nextA);
                    tempThis.add(nextThis);
                    tempA.add(nextA);
                }
                this.transferFrom(tempThis);
                a.transferFrom(tempA);
                int current = 0;
                boolean different = false;
                //check to see if every character in this is also in a
                while (current < size && !different) {
                    char next = strThis.charAt(current);
                    int nextIndex = strA.indexOf("" + next);
                    if (nextIndex == -1) {
                        different = true;
                    } else {
                        strA.deleteCharAt(nextIndex);
                        current++;
                    }
                }
                equal = !different;
            }
        }
        return equal;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public int hashCode() {
        final int asciiA = 65;
        int size = this.size();
        int code = 0;
        AlphabetSoup temp = this.newInstance();
        for (int i = 0; i < size; i++) {
            char next = this.removeAny();
            code += next - asciiA + 1;
            temp.add(next);
        }
        this.transferFrom(temp);
        return code;
    }

    // Type-Specific Methods
    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public void addCan() {
        final int canSize = 50;
        for (int i = 0; i < canSize; i++) {
            this.addAny();
        }
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public void eatSpoon() {
        final int spoonSize = 10;
        for (int i = 0; i < spoonSize; i++) {
            this.removeAny();
        }
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public int numberOf(char c) {
        AlphabetSoup temp = this.newInstance();
        int size = this.size();
        int count = 0;
        for (int i = 0; i < size; i++) {
            char next = this.removeAny();
            if (next == c) {
                count++;
            }
            temp.add(next);
        }
        this.transferFrom(temp);
        return count;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public String makeWord(int len) {

        String word = "";
        for (int i = 0; i < len; i++) {
            char removed = this.removeAny();
            word += removed;
        }
        for (int i = 0; i < len; i++) {
            this.add(word.charAt(i));
        }
        return word;
    }
}
