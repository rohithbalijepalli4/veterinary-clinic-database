import java.io.Serializable;

/**
 * Entry abstract class
 *
 * The parent class for all database entries.
 *
 * Every entry stored in the database must extend this class.
 * This includes OwnerEntry, PetEntry, and VisitEntry.
 *
 * Entry provides a common type that allows all database objects
 * to be stored together, compared, serialized, and manipulated
 * through the Database class.
 *
 * All subclasses must provide implementations for compareTo(),
 * equals(), and toString().
 *
 * @author Purdue CS
 * @version 1.0
 */
public abstract class Entry
        implements Serializable, Comparable<Entry>, Maintainable {

    /**
     * Construct a new Entry.
     *
     * Subclasses may perform any additional initialization
     * required by their specific implementation.
     */
    public Entry() {

    }

    /**
     * Compare this Entry to another Entry.
     *
     * The comparison rules are defined separately for each
     * subclass and are described in the project handout.
     *
     * This method is used to keep database entries in their
     * required sorted order.
     *
     * @param e the Entry being compared
     * @return a negative value if this Entry should come before e,
     *         zero if the Entries are considered equal,
     *         or a positive value if this Entry should come after e
     */
    @Override
    public abstract int compareTo(Entry e);

    /**
     * Determine whether this Entry is equal to another object.
     *
     * Equality rules are defined separately for each subclass
     * and are described in the project handout.
     *
     * @param o the object being compared
     * @return true if the objects are considered equal;
     *         false otherwise
     */
    @Override
    public abstract boolean equals(Object o);

    /**
     * Return a String representation of this Entry.
     *
     * The exact format for each subclass is specified
     * in the project handout.
     *
     * @return a String representation of this Entry
     */
    @Override
    public abstract String toString();
}
