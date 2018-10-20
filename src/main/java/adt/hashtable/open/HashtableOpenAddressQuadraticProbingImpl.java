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

    @Override
    public void insert(T element) {
        if (this.isFull()) {
            throw new HashtableOverflowException();
        }

        int probe = 0;
        boolean notInserted = true;

        while (probe != this.capacity() && notInserted) {
            int index = this.getIndexHash(element, probe);

            if (this.table[index] == null || this.table[index].equals(new DELETED())) {
                this.table[index] = element;
                this.elements++;
                notInserted = false;
            } else {
                probe++;
                this.COLLISIONS++;
            }
        }
    }

    @Override
    public void remove(T element) {
        if (!this.isEmpty()) {
            int index = this.indexOf(element);

            if (index != -1) {
                this.table[index] = new DELETED();
                this.elements--;
            }
        }
    }

    @Override
    public T search(T element) {
        T elementFound = null;

        if (!this.isEmpty()) {
            int index = this.indexOf(element);

            if (index != -1) {
                elementFound = ((T) this.table[index]);
            }
        }

        return elementFound;
    }

    @Override
    public int indexOf(T element) {
        int indexOf = -1;

        if (!this.isEmpty()) {
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

    private int getIndexHash(T element, int probe) {
        return ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, probe);
    }
}
