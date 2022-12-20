package AlgoGraph_Project;


import java.util.Arrays;
//Heap Implementation using HDP Array as mentioned in Project Description
public class Heap_HDP {
    private static int[] vertex_array;// H Array: get Vertex value
    private static int[] position_array;// P Array : Get Index to heap from vertex
    private static int[] heap_array; // D Array : store BW of vertex
    private static int size;

    public static void initalize(int size_max) {
        size = 0;
        vertex_array = new int[size_max];
        heap_array = new int[size_max];
        position_array = new int[size_max];

        Arrays.fill(vertex_array, -1);
        Arrays.fill(heap_array, -1);
        Arrays.fill(position_array, -1);
    }

    private static int leftChild(int position) { return 1 + (2 * position); }
    private static int rightChild(int position) { return 2 + (2 * position); }
    public static int getSize() { return size; }
    private static int parent(int position) { return (position - 1) / 2; }
    private static boolean leaf(int position) {
        return position <= size && position > (size / 2) ;
    }

    public static void delete(int vertex) {
        int position = position_array[vertex];
        extractmaximum(position);
    }
    public static int extractmaximum(int position) {
        int element = vertex_array[position];
        size--;
        vertex_array[position] = vertex_array[size];
        position_array[vertex_array[size]] = position_array[element];
        heap_array[position] = heap_array[size];

        heapify(position);

        vertex_array[size] = -1;
        position_array[element] = -1;
        heap_array[size] = -1;

        return element;
    }
    private static void swap(int position1, int position2) {
        position_array[vertex_array[position1]] = position2;
        position_array[vertex_array[position2]] = position1;

        int t_heap = heap_array[position1];
        heap_array[position1] = heap_array[position2];
        heap_array[position2] = t_heap;

        int temp = vertex_array[position1];
        vertex_array[position1] = vertex_array[position2];
        vertex_array[position2] = temp;

    }

    private static void heapify(int position) {
        if (leaf(position))
            return;

        if (heap_array[position] < heap_array[leftChild(position)]
                || heap_array[position] < heap_array[rightChild(position)]) {

            if (heap_array[leftChild(position)]
                    > heap_array[rightChild(position)]) {
                swap(position, leftChild(position));
                heapify(leftChild(position));
            } else {
                swap(position, rightChild(position));
                heapify(rightChild(position));
            }
        }
    }
    public static void insert(int vertex, int element) {

        vertex_array[size] = vertex;
        position_array[vertex] = size;
        heap_array[size] = element;

        int curr = size;
        while (heap_array[curr] > heap_array[parent(curr)]) {
            swap(curr, parent(curr));
            curr = parent(curr);
        }
        size++;
    }
}