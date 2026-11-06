import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Locale;

/**
 * Stores a dated visit, its services, and its unpaid balance.
 * @author Rohith Balijepalli Fall 2026 CS18000-BLK
 * @version Oct 18, 2026
 */
public class VisitEntry extends Entry {
    private LocalDate date;
    private String[] services;
    private double balance;

    public VisitEntry(String date, String[] services, double balance)
            throws EntryException {
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException | NullPointerException exception) {
            throw new EntryException("Invalid Date");
        }
        if (services == null || services.length == 0) {
            throw new EntryException("Invalid Services");
        }
        if (balance < 0 || Double.isNaN(balance)) {
            throw new EntryException("Invalid Balance");
        }
        this.services = services.clone();
        this.balance = balance;
    }

    @Override
    public LocalDate getDate() { return date; }

    @Override
    public String[] getServices() { return services.clone(); }

    @Override
    public double getBalance() { return balance; }

    @Override
    public double payBalance(double payment) {
        double remaining = balance - payment;
        if (Math.abs(remaining) <= 0.0001) {
            remaining = 0.0;
        }
        balance = Math.max(0.0, remaining);
        return remaining;
    }

    @Override
    public String toString() {
        return date + " - " + String.join(", ", services) + " - $"
                + String.format(Locale.US, "%.2f", balance);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof VisitEntry)) { return false; }
        VisitEntry other = (VisitEntry) object;
        return date.equals(other.date)
                && Arrays.equals(services, other.services);
    }

    @Override
    public int compareTo(Entry entry) {
        if (!(entry instanceof VisitEntry)) {
            throw new IllegalArgumentException();
        }
        return date.compareTo(((VisitEntry) entry).date);
    }
}
