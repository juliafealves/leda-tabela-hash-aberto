package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
        extends AbstractHashtableOpenAddress<T> {

    public HashtableOpenAddressQuadraticProbingImpl(int size, HashFunctionClosedAddressMethod method, int c1, int c2) {
        super(size);
        hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
        this.initiateInternalTable(size);
    }

    /**
     * Inserts a non-null object into the hash table. The hashtable does not work with duplicated elements. Every time
     * that the insert is called, if there is a collision, then the attribute COLLISION of this hashtable is incremented.
     */
    @Override
    public void insert(T element) {
        if (this.isFull()) {
            throw new HashtableOverflowException();
        }

        if (element != null) {
            int probe = 0;
            boolean notInserted = true;

            while (probe != this.capacity() && notInserted) {
                int index = this.getIndexHash(element, probe);

                if (this.table[index] == null || this.table[index].equals(new DELETED())) {
                    this.table[index] = element;
                    this.elements++;
                    notInserted = false;
                } else if (this.table[index].equals(element)) {
                    notInserted = false;
                } else {
                    probe++;
                    this.COLLISIONS++;
                }
            }
        }
    }

    /**
     * Removes an element from the hash table.
     */
    @Override
    public void remove(T element) {
        if (!this.isEmpty() && element != null) {
            int index = this.indexOf(element);

            if (index != -1) {
                this.table[index] = new DELETED();
                this.elements--;
            }
        }
    }

    /**
     * Searches a given element in the hash table. If it is not in the table, the hash table returns null.
     */
    @Override
    public T search(T element) {
        T elementFound = null;

        if (!this.isEmpty() && element != null) {
            int index = this.indexOf(element);

            if (index != -1) {
                elementFound = ((T) this.table[index]);
            }
        }

        return elementFound;
    }

    /**
     * Searches the index of an element in the hashtable. It returns -1 if the element is not in the hashtable.
     */
    @Override
    public int indexOf(T element) {
        int indexOf = -1;

        if (!this.isEmpty() && element != null) {
            int probe = 0;
            int index = this.getIndexHash(element, probe);
            boolean notFound = true;

            while (probe != this.capacity() && this.table[index] != null && notFound) {
                if (this.table[index].equals(element)) {
                    indexOf = index;
                    notFound = false;
                } else {
                    probe++;
                    index = this.getIndexHash(element, probe);
                }
            }
        }

        return indexOf;
    }

    /**
     * Return index through the hash code.
     * @param element
     * @param probe
     * @return
     */
    private int getIndexHash(T element, int probe) {
        return ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, probe);
    }
}
