/**
 * Stores the contact information and pet count for one owner.
 * @author Rohith Balijepalli Fall 2026 CS18000-BLK
 * @version Oct 18, 2026
 */
public class OwnerEntry extends Entry {
    private String name;
    private String address;
    private int phoneNumber;
    private int petCount;

    public OwnerEntry(String name, String address, int phoneNumber)
            throws EntryException {
        if (name == null) {
            throw new EntryException("Name can not be null");
        }
        if (address == null) {
            throw new EntryException("Address can not be null");
        }
        if (phoneNumber < 100000000) {
            throw new EntryException("Phone number invalid");
        }
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.petCount = 0;
    }

    @Override
    public String getName() { return name; }

    @Override
    public String getAddress() { return address; }

    @Override
    public void updateAddress(String address) throws EntryException {
        if (address == null) { throw new EntryException(); }
        this.address = address;
    }

    @Override
    public int getPhoneNumber() { return phoneNumber; }

    @Override
    public void updatePhoneNumber(int number) throws EntryException {
        if (number < 100000000) { throw new EntryException(); }
        phoneNumber = number;
    }

    @Override
    public int getPetCount() { return petCount; }

    @Override
    public void setPetCount(int count) { petCount = count; }

    @Override
    public String toString() {
        return name + " - " + address + " - " + phoneNumber
                + " (" + petCount + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OwnerEntry)) { return false; }
        OwnerEntry other = (OwnerEntry) object;
        return name.equals(other.name)
                && address.equals(other.address)
                && phoneNumber == other.phoneNumber;
    }

    @Override
    public int compareTo(Entry entry) {
        if (!(entry instanceof OwnerEntry)) {
            throw new IllegalArgumentException();
        }
        OwnerEntry other = (OwnerEntry) entry;
        int result = name.compareTo(other.name);
        if (result == 0) { result = address.compareTo(other.address); }
        if (result == 0) {
            result = Integer.compare(phoneNumber, other.phoneNumber);
        }
        return result;
    }
}
