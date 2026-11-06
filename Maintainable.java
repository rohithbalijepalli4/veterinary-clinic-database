import java.time.LocalDate;

/**
 * Maintainable interface
 *
 * Defines the required methods that may be implemented by
 * Entry subclasses. Not every Entry type requires every
 * method in this interface.
 *
 * OwnerEntry, PetEntry, and VisitEntry should implement only
 * the methods that apply to their specific responsibilities.
 *
 * @author Purdue CS
 * @version 1.0
 */
public interface Maintainable {

    /**
     * Return the name associated with this entry.
     *
     * This method is used by Owner Entries and Pet Entries.
     *
     * @return the name stored in this entry
     */
    default String getName() {
        throw new RuntimeException(
                this.getClass().getSimpleName() + " getName does not exist");
    }

    /**
     * Return the date associated with this visit.
     *
     * This method is used by Visit Entries.
     *
     * @return the visit date
     */
    default LocalDate getDate() {
        throw new RuntimeException(
                this.getClass().getSimpleName() + " getDate does not exist");
    }

    /**
     * Return the address associated with this owner.
     *
     * This method is used by Owner Entries.
     *
     * @return the owner's address
     */
    default String getAddress() {
        throw new RuntimeException(
                this.getClass().getSimpleName() + " getAddress does not exist");
    }

    /**
     * Update the address associated with this owner.
     *
     * If address is null,
     * throw an EntryException with no message.
     *
     * This method is used by Owner Entries.
     *
     * @param address the new address
     * @throws EntryException if address is null
     */
    default void updateAddress(String address) throws EntryException {
        throw new RuntimeException(
                this.getClass().getSimpleName() + " updateAddress does not exist");
    }

    /**
     * Return the phone number associated with this owner.
     *
     * This method is used by Owner Entries.
     *
     * @return the owner's phone number
     */
    default int getPhoneNumber() {
        throw new RuntimeException(
                this.getClass().getSimpleName() + " getPhoneNumber does not exist");
    }

    /**
     * Update the phone number associated with this owner.
     *
     * A phone number is invalid if it contains fewer than
     * 9 digits or begins with 0.
     *
     * This is 1 digit shorter than a normal phone number.
     *
     * If the phone number is invalid,
     * throw an EntryException with no message.
     *
     * This method is used by Owner Entries.
     *
     * @param number the new phone number
     * @throws EntryException if the phone number is invalid
     */
    default void updatePhoneNumber(int number) throws EntryException {
        throw new RuntimeException(
                this.getClass().getSimpleName() + " updatePhoneNumber does not exist");
    }

    /**
     * Return the current balance associated with this visit.
     *
     * This method is used by Visit Entries.
     *
     * @return the outstanding balance
     */
    default double getBalance() {
        throw new RuntimeException(
                this.getClass().getSimpleName() + " getBalance does not exist");
    }

    /**
     * Apply a payment to this visit's balance.
     *
     * Return the remaining balance as a positive value.
     *
     * If the payment exceeds the balance, return a negative
     * value whose absolute value represents the amount overpaid.
     *
     * A resulting balance within +/- 0.0001 should be treated
     * as exactly 0.00.
     *
     * This method is used by Visit Entries.
     *
     * @param payment the payment amount to apply
     * @return the remaining balance or overpayment amount
     */
    default double payBalance(double payment) {
        throw new RuntimeException(
                this.getClass().getSimpleName() + " payBalance does not exist");
    }

    /**
     * Return the number of pets belonging to this owner.
     *
     * This method is used by Owner Entries.
     *
     * @return the number of pets owned
     */
    default int getPetCount() {
        throw new RuntimeException(
                this.getClass().getSimpleName() + " getPetCount does not exist");
    }

    /**
     * Update the number of pets belonging to this owner.
     *
     * This method is used by Owner Entries.
     *
     * @param count the new pet count
     */
    default void setPetCount(int count) {
        throw new RuntimeException(
                this.getClass().getSimpleName() + " setPetCount does not exist");
    }

    /**
     * Return the number of visits associated with this pet.
     *
     * This method is used by Pet Entries.
     *
     * @return the number of visits
     */
    default int getNumberOfVisits() {
        throw new RuntimeException(
                this.getClass().getSimpleName() + " getNumberOfVisits does not exist");
    }

    /**
     * Update the number of visits associated with this pet.
     *
     * This method is used by Pet Entries.
     *
     * @param count the new visit count
     */
    default void setNumberOfVisits(int count) {
        throw new RuntimeException(
                this.getClass().getSimpleName() + " setNumberOfVisits does not exist");
    }

    /**
     * Return all services performed during this visit.
     *
     * This method is used by Visit Entries.
     *
     * @return an array containing all services performed
     *         during the visit
     */
    default String[] getServices() {
        throw new RuntimeException(
                this.getClass().getSimpleName() + " getServices does not exist");
    }
}
