import java.util.ArrayList;
import java.util.List;

class Loader implements Runnable {
    private List<Product> products;
    private static final int MAX_LOAD = 150;

    public Loader(List<Product> products) {
        this.products = products;
    }

    @Override
    public void run() {
        int currentLoad = 0;
        List<Product> loadedProducts = new ArrayList<>();

        for (Product product : products) {
            if (currentLoad + product.getWeight() <= MAX_LOAD) {
                loadedProducts.add(product);
                currentLoad += product.getWeight();
            }
            if (currentLoad >= MAX_LOAD) {
                break;
            }
        }

        if (!loadedProducts.isEmpty()) {
            System.out.println(Thread.currentThread().getName() + " грузит: " + loadedProducts);
            // Симуляция времени на доставку
            try {
                Thread.sleep(1000); // 1 секунда
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(Thread.currentThread().getName() + " разгружает: " + loadedProducts);
        }
    }
}
