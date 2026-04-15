/**
* AlphabetSoup Kernel component with primary methods.
*
* modeled by a string of uppercase letters (A-Z)
*/
public interface AlphabetSoup extends AlphabetSoupKernel {

    /**
    * Adds 50 random letters to {@code this}.
    *
    * @updates this
    *
    * @ensures {@code this = #this * (50 random letters)}
    */
    void addCan();

    /**
    * Removes 10 random letters from this.
    *
    * @updates this
    *
    * @requires |this| >= 10
    * @ensures {@code #this = this * (10 removed letters)}
    */
    void eatSpoon();

    /**
    * Returns whether this is empty.
    *
    * @return true if this is empty, false otherwise
    *
    * @ensures isEmpty = (|this| == 0)
    */
    boolean isEmpty();

    /**
    * Returns the number of times {@code c} is in {@code this}.
    *
    * @param c
    *               letter to search for
    * @return a the number of times c is in this
    *
    * @requires {@code c} is a letter (A-Z)
    * @ensures numberOf = (number of times c is in this)
    */
    int numberOf(char c);

    /**
     * Removes {@code len} letters from the soup and returns a string
     * consisting of the removed letters.
     *
     * @param len
     *              length of word to create
     * @updates this
     *
     * @return String containing {@code len} letters removed from {@code this}
     * @requires len <= |this|
     * @ensures makeWord = (String of letters removed from this)
     * and |this| = |#this| - len
     * and #this = this * makeWord
     */
    String makeWord(int len);

}
