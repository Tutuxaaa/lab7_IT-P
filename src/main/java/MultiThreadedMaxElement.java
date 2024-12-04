public class MultiThreadedMaxElement {
    public static void main(String[] args){
        int[][] matrix = {{1, 2}, {3, 4, 8}, {5, 6, 7}};

        ThreadMaxElement thread1 = new ThreadMaxElement(matrix[0], matrix[0].length);
        ThreadMaxElement thread2 = new ThreadMaxElement(matrix[1], matrix[1].length);
        ThreadMaxElement thread3 = new ThreadMaxElement(matrix[2], matrix[2].length);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int totalMax = Math.max(Math.max(thread1.getMax(), thread2.getMax()), thread3.getMax());

        System.out.println("Максимальный элемент матрицы: " + totalMax);


    }
}