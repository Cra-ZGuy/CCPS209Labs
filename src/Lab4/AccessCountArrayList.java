package Lab4;

import java.util.ArrayList;

public class AccessCountArrayList<E> extends ArrayList<E> {
    private int count = 0;

    @Override
    public E get(int index) {
        count++;
        return super.get(index);
    }

    @Override
    public E set(int index, E element) {
        count++;
        return super.set(index, element);
    }

    public int getAccessCount() {
        return count;
    }

    public void resetCount() {
        count = 0;
    }
}
