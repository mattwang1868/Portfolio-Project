import components.standard.Standard;
/**
* AlphabetSoup Kernel component with primary methods.
*
* modeled by a string of uppercase letters (A-Z)
*/
public interface AlphabetSoupKernel extends Standard<AlphabetSoup> {

    /**
    * Adds {@code c} to {@code this}.
    *
    * @param c
    *            the letter to be added
    * @updates this
    * @requires c is a letter (A-Z)
    * @ensures {@code this = #this * c}
    */
    void add(char c);

    /**
    * Adds a random letter to {@code this}.
    *
    * @updates this
    * @ensures {@code this = #this * (random letter)}
    */
    void addAny();

    /**
    * Returns the size of {@code this}.
    *
    * @return number of entries in this
    *
    * @ensures size = |this|
    */
    int size();

    /**
    * Removes and returns a random letter in this.
    *
    * @return a letter contained in this
    * @updates this
    *
    * @requires |this| > 0
    * @ensures {@code #this = this * (removeAny)}
    */
    char removeAny();


}
