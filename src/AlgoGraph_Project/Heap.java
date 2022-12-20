package AlgoGraph_Project;

import java.util.*;

// Basic Heap Functionality for backup
public class Heap {

    Map<Integer, Integer> position;
    List<int[]> heap;


    Heap(List<int[]> fringes) {
        heap = new ArrayList<>(fringes.size());
        position = new HashMap<>(fringes.size());
        for (int i = 0; i < fringes.size(); i++) {
            heap.add(fringes.get(i));
            position.put(fringes.get(i)[0], i);
        }
        for (int i = heap.size() / 2; i >= 0; i--) {
            heapify(i);
        }
    }

    public int[] getMaximum() {
        int[] maximum = heap.get(0);
        delete(maximum[0]);
        return maximum;
    }

    public void insert(int[] fringe) {
        heap.add(fringe);
        int t = heap.size() - 1;
        position.put(fringe[0], t);
        while (t > 0 && t < heap.size() && heap.get(t / 2)[1] - heap.get(t)[1] < 0) {
            swap(t / 2, t);
            t = t / 2;
        }
    }

    public void delete(int node) {
        int pos = position.get(node);
        swap(pos, heap.size() - 1);
        heap.remove(heap.size() - 1);
        position.remove(node);
        while (pos > 0 && pos < heap.size() && heap.get(pos / 2)[1] - heap.get(pos)[1] < 0) {
            swap(pos / 2, pos);
            pos = pos / 2;
        }
        heapify(pos);
    }

    //rearrange heap
    private void heapify(int t) {
        int leftch = 2 * t;
        int rightch = 2 * t + 1;
        int largest = t;
        if (leftch < heap.size() && heap.get(leftch)[1] - heap.get(t)[1] > 0) {
            largest = leftch;
        }
        if (rightch < heap.size() && heap.get(rightch)[1] - heap.get(largest)[1] > 0) {
            largest = rightch;
        }
        if (largest != t) {
            swap(t, largest);
            heapify(largest);
        }
    }

    private void swap(int node1, int node2) {
        int[] fringe1 = heap.get(node1);
        int[] fringe2 = heap.get(node2);
        heap.set(node1, fringe2);
        heap.set(node2, fringe1);
        position.put(fringe1[0], node2);
        position.put(fringe2[0], node1);
    }
}
