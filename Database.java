import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Manages sorted owner, pet, and visit records in one flat list.
 *
 * @author Rohith Balijepalli Fall 2026 CS18000-BLK
 *
 * @version Oct 18, 2026
 */
public class Database implements Useable {
    private ArrayList<Entry> entries;
    private String filename;

    public Database(String fileName) {
        entries = new ArrayList<>();
        filename = fileName;
    }

    /** Returns the index of a matching owner, or -1 if absent. */
    private int ownerIndex(Entry owner) {
        if (!(owner instanceof OwnerEntry)) {
            return -1;
        }
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i) instanceof OwnerEntry
                    && entries.get(i).equals(owner)) {
                return i;
            }
        }
        return -1;
    }

    /** Returns the index immediately after an owner's entire block. */
    private int ownerEnd(int index) {
        int i = index + 1;
        while (i < entries.size() && !(entries.get(i) instanceof OwnerEntry)) {
            i++;
        }
        return i;
    }

    /** Returns the index immediately after a pet's visits. */
    private int petEnd(int index) {
        int i = index + 1;
        while (i < entries.size() && entries.get(i) instanceof VisitEntry) {
            i++;
        }
        return i;
    }

    /** Finds a pet within the specified owner's block. */
    private int petIndex(int ownerPosition, Entry pet) {
        if (!(pet instanceof PetEntry)) {
            return -1;
        }
        int end = ownerEnd(ownerPosition);
        for (int i = ownerPosition + 1; i < end; i++) {
            if (entries.get(i) instanceof PetEntry
                    && entries.get(i).equals(pet)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean validPhone(int number) {
        return number >= 100000000;
    }

    /** Finds a unique owner using any requested combination of fields. */
    private Entry findOwner(String name, String address, Integer phone,
                            boolean checkName, boolean checkAddress,
                            boolean checkPhone) throws EntryException {
        if ((checkName && name == null)
                || (checkAddress && address == null)
                || (checkPhone && !validPhone(phone))) {
            throw new EntryException();
        }
        Entry result = null;
        for (Entry entry : entries) {
            if (entry instanceof OwnerEntry
                    && (!checkName || entry.getName().equals(name))
                    && (!checkAddress || entry.getAddress().equals(address))
                    && (!checkPhone || entry.getPhoneNumber() == phone)) {
                if (result != null) {
                    throw new EntryException("Duplicate Entry");
                }
                result = entry;
            }
        }
        return result;
    }

    @Override
    public Entry getOwner(String name) throws EntryException {
        return findOwner(name, null, null, true, false, false);
    }

    @Override
    public Entry getOwner(int number) throws EntryException {
        return findOwner(null, null, number, false, false, true);
    }

    @Override
    public Entry getOwner(String name, String address) throws EntryException {
        return findOwner(name, address, null, true, true, false);
    }

    @Override
    public Entry getOwner(String name, String address, int number)
            throws EntryException {
        return findOwner(name, address, number, true, true, true);
    }

    @Override
    public Entry getPet(Entry owner, String name) throws EntryException {
        int position = ownerIndex(owner);
        if (position < 0) {
            throw new EntryException("No Such Owner");
        }
        if (name == null) {
            throw new EntryException();
        }
        int end = ownerEnd(position);
        for (int i = position + 1; i < end; i++) {
            Entry entry = entries.get(i);
            if (entry instanceof PetEntry && entry.getName().equals(name)) {
                return entry;
            }
        }
        throw new EntryException("No Such Pet");
    }

    @Override
    public Entry[] getPets(Entry owner) throws EntryException {
        int position = ownerIndex(owner);
        if (position < 0) {
            throw new EntryException("No Such Owner");
        }
        ArrayList<Entry> pets = new ArrayList<>();
        int end = ownerEnd(position);
        for (int i = position + 1; i < end; i++) {
            if (entries.get(i) instanceof PetEntry) {
                pets.add(entries.get(i));
            }
        }
        if (pets.isEmpty()) {
            throw new EntryException("No Pets");
        }
        return pets.toArray(new Entry[0]);
    }

    @Override
    public Entry[] getVisits(Entry pet) throws EntryException {
        int position = -1;
        if (pet instanceof PetEntry) {
            for (int i = 0; i < entries.size(); i++) {
                if (entries.get(i) instanceof PetEntry
                        && entries.get(i) == pet) {
                    position = i;
                    break;
                }
            }
            if (position == -1) {
                for (int i = 0; i < entries.size(); i++) {
                    if (entries.get(i) instanceof PetEntry
                            && entries.get(i).equals(pet)) {
                        if (position != -1) {
                            throw new EntryException("No Such Pet");
                        }
                        position = i;
                    }
                }
            }
        }
        if (position == -1) {
            throw new EntryException("No Such Pet");
        }
        int end = petEnd(position);
        if (end == position + 1) {
            throw new EntryException("No Visits");
        }
        return entries.subList(position + 1, end).toArray(new Entry[0]);
    }

    @Override
    public void addOwner(Entry owner) throws EntryException {
        if (!(owner instanceof OwnerEntry)) {
            throw new EntryException("No Such Entry");
        }
        int position = entries.size();
        for (int i = 0; i < entries.size(); i++) {
            Entry current = entries.get(i);
            if (current instanceof OwnerEntry) {
                int comparison = owner.compareTo(current);
                if (comparison == 0) {
                    throw new EntryException("Duplicate Entry");
                }
                if (comparison < 0) {
                    position = i;
                    break;
                }
            }
        }
        owner.setPetCount(0);
        entries.add(position, owner);
    }

    @Override
    public void addPet(Entry owner, Entry pet) throws EntryException {
        int position = ownerIndex(owner);
        if (position == -1) {
            throw new EntryException("No Such Owner");
        }
        if (!(pet instanceof PetEntry)) {
            throw new EntryException("No Such Pet");
        }
        int insertion = ownerEnd(position);
        for (int i = position + 1; i < insertion; i++) {
            Entry current = entries.get(i);
            if (current instanceof PetEntry) {
                int comparison = pet.compareTo(current);
                if (comparison == 0) {
                    throw new EntryException("Duplicate Entry");
                }
                if (comparison < 0) {
                    insertion = i;
                    break;
                }
            }
        }
        pet.setNumberOfVisits(0);
        entries.add(insertion, pet);
        Entry storedOwner = entries.get(position);
        storedOwner.setPetCount(storedOwner.getPetCount() + 1);
    }

    @Override
    public void addVisit(Entry owner, Entry pet, Entry visit)
            throws EntryException {
        int ownerPosition = ownerIndex(owner);
        if (ownerPosition == -1) {
            throw new EntryException("No Such Owner");
        }
        int petPosition = petIndex(ownerPosition, pet);
        if (petPosition == -1) {
            throw new EntryException("No Such Pet");
        }
        if (!(visit instanceof VisitEntry)) {
            throw new EntryException("No Such Entry");
        }
        int insertion = petEnd(petPosition);
        for (int i = petPosition + 1; i < insertion; i++) {
            Entry current = entries.get(i);
            if (visit.equals(current)) {
                throw new EntryException("Duplicate Entry");
            }
            if (visit.compareTo(current) < 0) {
                insertion = i;
                break;
            }
        }
        entries.add(insertion, visit);
        Entry storedPet = entries.get(petPosition);
        storedPet.setNumberOfVisits(storedPet.getNumberOfVisits() + 1);
    }

    @Override
    public Entry[] checkBalances() throws EntryException {
        ArrayList<Entry> owners = new ArrayList<>();
        Entry owner = null;
        boolean hasBalance = false;
        for (Entry entry : entries) {
            if (entry instanceof OwnerEntry) {
                if (owner != null && hasBalance) {
                    owners.add(owner);
                }
                owner = entry;
                hasBalance = false;
            } else if (entry instanceof VisitEntry && entry.getBalance() > 0) {
                hasBalance = true;
            }
        }
        if (owner != null && hasBalance) {
            owners.add(owner);
        }
        if (owners.isEmpty()) {
            throw new EntryException();
        }
        return owners.toArray(new Entry[0]);
    }

    @Override
    public double payBalance(Entry entry, double payment) throws EntryException {
        if (!(payment > 0)) {
            throw new EntryException();
        }
        if (entry instanceof OwnerEntry) {
            int position = ownerIndex(entry);
            if (position == -1) {
                throw new EntryException("No Such Entry");
            }
            double remaining = payment;
            double result = -payment;
            boolean paidAnyVisit = false;
            int end = ownerEnd(position);
            for (int i = position + 1; i < end && remaining > 0; i++) {
                Entry current = entries.get(i);
                if (current instanceof VisitEntry) {
                    double balance = current.getBalance();
                    if (balance > 0) {
                        result = current.payBalance(remaining);
                        paidAnyVisit = true;
                        remaining = Math.max(0.0, -result);
                    }
                }
            }
            if (remaining > 0) {
                return -remaining;
            }
            return paidAnyVisit ? result : -payment;
        }
        if (entry instanceof VisitEntry) {
            for (Entry current : entries) {
                if (current == entry) {
                    return current.payBalance(payment);
                }
            }
        }
        throw new EntryException("No Such Entry");
    }

    @Override
    public void shutdown() {
        try (ObjectOutputStream output = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            for (Entry entry : entries) {
                output.writeObject(entry);
            }
        } catch (IOException exception) {
        }
    }

    @Override
    public void startup() {
        ArrayList<Entry> loaded = new ArrayList<>();
        try (ObjectInputStream input = new ObjectInputStream(
                new FileInputStream(filename))) {
            while (true) {
                OwnerEntry owner = (OwnerEntry) input.readObject();
                loaded.add(owner);
                for (int p = 0; p < owner.getPetCount(); p++) {
                    PetEntry pet = (PetEntry) input.readObject();
                    loaded.add(pet);
                    for (int v = 0; v < pet.getNumberOfVisits(); v++) {
                        VisitEntry visit = (VisitEntry) input.readObject();
                        loaded.add(visit);
                    }
                }
            }
        } catch (EOFException exception) {
            entries = loaded;
        } catch (IOException | ClassNotFoundException | ClassCastException exception) {
        }
    }
}