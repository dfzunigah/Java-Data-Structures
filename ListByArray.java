public class ListByArray {

    // ----------------------------------------------------------------- ATTRIBUTES
    private int size;
    private int superTopIndex, bottomIndex;
    private int list[];

    // ----------------------------------------------------------------- CONSTRUCTORS
    public ListByArray(int maxCapacity) {
        this.setBottomIndex(0);
        this.setSuperTopIndex(0);
        this.setSize(0);
        this.list = new int[maxCapacity];
    }

    // ------------------------------------------------------------------ METHODS
    public void add(int newElement) {

        int[] newList;

        if (this.getSuperTopIndex() != this.list.length) {
            this.list[this.getSuperTopIndex()] = newElement;
            this.setSuperTopIndex(this.getSuperTopIndex() + 1);
            this.setSize(this.getSize() + 1);
        } else // I wonder if whats below will work!
        {
            newList = new int[this.list.length * 2];
            for (int index = 0; index < this.getSuperTopIndex(); index = index + 1) {
                newList[index] = this.list[index];
            }
            this.list = newList;

            this.list[this.getSuperTopIndex()] = newElement;
            this.setSuperTopIndex(this.getSuperTopIndex() + 1);
            this.setSize(this.getSize() + 1);
        }
    }

    public void eliminate(int eliminationIndex) throws Exception {
        if ((eliminationIndex >= this.getBottomIndex()) && (eliminationIndex < this.getSuperTopIndex())) {
            for (int index = eliminationIndex; index < this.getSuperTopIndex() - 1; index = index + 1) {
                this.list[index] = this.list[index + 1];
            }
            this.setSize(this.getSize() - 1);
            this.setSuperTopIndex(this.getSuperTopIndex() - 1);
        } else {
            throw new Exception("That index doesn't exist on the current list");
        }
    }

    public int getElement(int queryIndex) throws Exception {
        int returnValue;

        if ((queryIndex >= this.getBottomIndex()) && (queryIndex < this.getSuperTopIndex())) {
            returnValue = this.list[queryIndex];
        } else {
            throw new Exception("That index doesn't exist on the current list");
        }

        return returnValue;
    }

    public int getIndex(int element) throws Exception {
        int returnValue;
        boolean elementFound;

        returnValue = 0;
        elementFound = false;
        for (int index = this.getBottomIndex(); index < this.getSuperTopIndex(); index = index + 1) {
            if (this.list[index] == element) {
                elementFound = true;
                returnValue = index;
                break;
            }
        }

        if (!elementFound) {
            throw new Exception("The element wasn't found on the current list");
        }

        return returnValue;
    }

    public void print() {
        for (int index = this.getBottomIndex(); index < this.getSuperTopIndex(); index = index + 1) {
            System.out.print(this.list[index] + " ");
        }
    }

    public boolean isEmpty() {
        boolean returnValue;

        if (this.getSize() == 0) {
            returnValue = true;
        } else {
            returnValue = false;
        }

        return returnValue;
    }

    public void empty() {
        this.setBottomIndex(0);
        this.setSuperTopIndex(0);
        this.setSize(0);
    }

    public void modify(int index, int newElement) throws Exception {

        if ((index >= this.getBottomIndex()) && (index < this.getSuperTopIndex())) {
            this.list[index] = newElement;

        } else {
            throw new Exception("That index doesn't exist on the current list");
        }
    }

    // --------------------------------- GETTERS & SETTERS
    public void setSize(int newSize) {
        this.size = newSize;
    }

    public int getSize() {
        return this.size;
    }

    public void setSuperTopIndex(int newSuperTopIndex) {
        this.superTopIndex = newSuperTopIndex;
    }

    public int getSuperTopIndex() {
        return this.superTopIndex;
    }

    public void setBottomIndex(int newBottomIndex) {
        this.bottomIndex = newBottomIndex;
    }

    public int getBottomIndex() {
        return this.bottomIndex;
    }
}
