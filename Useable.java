/**
 * Useable interface
 *
 * Defines the required methods for a Database implementation.
 * These methods are responsible for locating, adding, managing,
 * and saving Entry objects.
 *
 * @author Purdue CS
 * @version 1.0
 */
public interface Useable {

    /**
     * Find and return the Owner Entry with the specified name.
     *
     * If multiple Owner Entries share the same name,
     * throw an EntryException with the message:
     * "Duplicate Entry"
     *
     * If name is null,
     * throw an EntryException with no message.
     */
    default Entry getOwner(String name) throws EntryException {
        throw new RuntimeException("getOwner(String name) does not exist");
    }

    /**
     * Find and return the Owner Entry with the specified phone number.
     *
     * If multiple Owner Entries share the same phone number,
     * throw an EntryException with the message:
     * "Duplicate Entry"
     *
     * If the phone number is invalid,
     * throw an EntryException with no message.
     *
     * See Maintainable for the definition of a valid phone number.
     */
    default Entry getOwner(int number) throws EntryException {
        throw new RuntimeException("getOwner(int number) does not exist");
    }

    /**
     * Find and return the Owner Entry with the specified name and address.
     *
     * If multiple Owner Entries share the same name and address,
     * throw an EntryException with the message:
     * "Duplicate Entry"
     *
     * If name is null,
     * throw an EntryException with no message.
     *
     * If address is null,
     * throw an EntryException with no message.
     */
    default Entry getOwner(String name, String address) throws EntryException {
        throw new RuntimeException("getOwner(String name, String address) does not exist");
    }

    /**
     * Find and return the Owner Entry matching the specified
     * name, address, and phone number.
     *
     * If multiple Owner Entries share the same name, address,
     * and phone number, throw an EntryException with the message:
     * "Duplicate Entry"
     *
     * If name is null,
     * throw an EntryException with no message.
     *
     * If address is null,
     * throw an EntryException with no message.
     *
     * If the phone number is invalid,
     * throw an EntryException with no message.
     */
    default Entry getOwner(String name, String address, int number)
            throws EntryException {
        throw new RuntimeException(
                "getOwner(String name, String address, int number) does not exist");
    }

    /**
     * Find and return the Pet Entry with the specified name
     * belonging to the specified owner.
     *
     * If the owner does not exist,
     * throw an EntryException with the message:
     * "No Such Owner"
     *
     * If the pet does not exist,
     * throw an EntryException with the message:
     * "No Such Pet"
     *
     * If name is null,
     * throw an EntryException with no message.
     */
    default Entry getPet(Entry owner, String name) throws EntryException {
        throw new RuntimeException("getPet does not exist");
    }

    /**
     * Find and return all Pet Entries belonging to the specified owner.
     *
     * If the owner does not exist,
     * throw an EntryException with the message:
     * "No Such Owner"
     *
     * If the owner has no pets,
     * do not return an empty array. Instead, throw an
     * EntryException with the message:
     * "No Pets"
     */
    default Entry[] getPets(Entry owner) throws EntryException {
        throw new RuntimeException("getPets does not exist");
    }

    /**
     * Find and return all Visit Entries belonging to the specified pet.
     *
     * If the pet does not exist,
     * throw an EntryException with the message:
     * "No Such Pet"
     *
     * If the pet has no visits,
     * do not return an empty array. Instead, throw an
     * EntryException with the message:
     * "No Visits"
     */
    default Entry[] getVisits(Entry pet) throws EntryException {
        throw new RuntimeException("getVisits(Entry pet) does not exist");
    }

    /**
     * Add the specified Owner Entry to the database.
     *
     * Owner Entries must be stored in Comparable order as
     * specified by the project handout. Do not simply add
     * the entry to the end of the list.
     *
     * If the entry already exists,
     * throw an EntryException with the message:
     * "Duplicate Entry"
     */
    default void addOwner(Entry owner) throws EntryException {
        throw new RuntimeException("addOwner does not exist");
    }

    /**
     * Add the specified Pet Entry to the specified owner.
     *
     * Pet Entries must be stored in Comparable order as
     * specified by the project handout. Do not simply add
     * the entry to the end of the list.
     *
     * Be sure to update the owner's pet count.
     *
     * If the pet already exists for that owner,
     * throw an EntryException with the message:
     * "Duplicate Entry"
     *
     * If the owner does not exist,
     * throw an EntryException with the message:
     * "No Such Owner"
     */
    default void addPet(Entry owner, Entry pet) throws EntryException {
        throw new RuntimeException("addPet does not exist");
    }

    /**
     * Add the specified Visit Entry to the specified pet
     * belonging to the specified owner.
     *
     * Visit Entries must be stored in Comparable order as
     * specified by the project handout. Do not simply add
     * the entry to the end of the list.
     *
     * Be sure to update the pet's visit count.
     *
     * If the visit already exists for that pet,
     * throw an EntryException with the message:
     * "Duplicate Entry"
     *
     * If the owner does not exist,
     * throw an EntryException with the message:
     * "No Such Owner"
     *
     * If the pet does not exist,
     * throw an EntryException with the message:
     * "No Such Pet"
     */
    default void addVisit(Entry owner, Entry pet, Entry visit)
            throws EntryException {
        throw new RuntimeException("addVisit does not exist");
    }

    /**
     * Check all visits for all pets belonging to each owner
     * and determine whether the owner has an outstanding balance.
     *
     * Return an array containing all Owner Entries with a balance greater
     * than zero.
     *
     * If no owners have an outstanding balance,
     * throw an EntryException with no message.
     */
    default Entry[] checkBalances() throws EntryException {
        throw new RuntimeException("checkBalances does not exist");
    }

    /**
     * Apply a payment to the specified entry.
     *
     * If the entry is an Owner Entry, apply the payment to that
     * owner's visits in order until either:
     * - the entire payment has been applied, or
     * - all visit balances reach 0
     *
     * Additional payment details are provided in the project handout.
     *
     * If the entry is a Visit Entry, apply the payment only to
     * that visit.
     *
     * Return the remaining balance of the last modified visit
     * as a positive value.
     *
     * Return a negative value if the payment exceeds the balance,
     * where the absolute value represents the amount overpaid.
     *
     * If payment is zero or negative,
     * throw an EntryException with no message.
     *
     * If the entry does not exist,
     * throw an EntryException with the message:
     * "No Such Entry"
     */
    default double payBalance(Entry entry, double payment)
            throws EntryException {
        throw new RuntimeException("payBalance does not exist");
    }

    /**
     * Write all database entries to the filename specified
     * by the Database class using object I/O.
     *
     * This method must throw zero exceptions.
     *
     * Catch and handle all checked exceptions internally.
     */
    default void shutdown() {
        throw new RuntimeException("shutdown does not exist");
    }

    /**
     * Read all database entries from the filename specified
     * by the Database class using object I/O.
     *
     * This method must throw zero exceptions.
     *
     * Catch and handle all checked exceptions internally.
     *
     * Be sure to correctly cast each object as it is read.
     */
    default void startup() {
        throw new RuntimeException("startup does not exist");
    }
}
