# Veterinary Clinic Database System

A Java object-oriented database project for maintaining veterinary clinic records, including owners, pets, visits, and outstanding balances.

## Features
- Manage relationships between owner, pet, and visit records in one `ArrayList<Entry>`
- Insert records in sorted order and detect duplicates
- Search for owners and pets using identifying information
- Track visit services, dates, and balances
- Apply payments to individual visits or distribute them across an owner's visits
- Save and load records using Java object serialization

## Technologies
Java, object-oriented programming, inheritance, interfaces, ArrayList, Comparable ordering, exception handling, LocalDate, object I/O.

## Project Structure
- `OwnerEntry.java` — owner contact information and pet counts
- `PetEntry.java` — pet records and visit counts
- `VisitEntry.java` — appointments, services, and payments
- `Database.java` — searching, sorted insertion, payment processing, and persistence

## Setup
The course-provided `Entry.java`, `Useable.java`, `Maintainable.java`, and `EntryException.java` files are included. To compile the Java classes:

```bash
javac *.java
```

These are library classes rather than a standalone application, so there is no `main` method. Create a separate driver class to test the functionality.

## Context
Java coursework project, Fall 2026.