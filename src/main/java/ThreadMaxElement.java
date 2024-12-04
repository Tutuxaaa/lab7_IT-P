class ThreadMaxElement extends Thread{
    private int[] array;
    private int end;
    private int max;

    public ThreadMaxElement(int[] array, int end) {
        this.array = array;
        this.end = end;
        this.max = 0;
    }

    @Override
    public void run() {
        for (int i = 0; i < end; i++) {
            max = Math.max(array[i], max);
        }
    }

    public int getMax(){return max;}
}
