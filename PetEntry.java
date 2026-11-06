/**
 * Stores a pet's name and the number of visits associated with it.
 * @author Rohith Balijepalli Fall 2026 CS18000-BLK
 * @version Oct 18, 2026
 */
public class PetEntry extends Entry {
    private String name;
    private int numberOfVisits;

    public PetEntry(String name) throws EntryException {
        if (name == null) {
            throw new EntryException("Name can not be null");
        }
        this.name = name;
        this.numberOfVisits = 0;
    }

    @Override
    public String getName() { return name; }

    @Override
    public int getNumberOfVisits() { return numberOfVisits; }

    @Override
    public void setNumberOfVisits(int count) { numberOfVisits = count; }

    @Override
    public String toString() {
        return name + " (" + numberOfVisits + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PetEntry)) { return false; }
        PetEntry other = (PetEntry) object;
        return name.equals(other.name);
    }

    @Override
    public int compareTo(Entry entry) {
        if (!(entry instanceof PetEntry)) {
            throw new IllegalArgumentException();
        }
        return name.compareTo(((PetEntry) entry).name);
    }
}
