import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class WarehouseTransfer {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Товар 1", 50));
        products.add(new Product("Товар 2", 30));
        products.add(new Product("Товар 3", 20));
        products.add(new Product("Товар 4", 40));
        products.add(new Product("Товар 5", 60));
        products.add(new Product("Товар 6", 10));
        products.add(new Product("Товар 7", 25));
        products.add(new Product("Товар 8", 15));

        int numLoaders = 3;
        List<List<Product>> dividedProducts = new ArrayList<>();

        for (int i = 0; i < numLoaders; i++) {
            dividedProducts.add(new ArrayList<>());
        }

        for (int i = 0; i < products.size(); i++) {
            dividedProducts.get(i % numLoaders).add(products.get(i));
        }


        List<CompletableFuture<Void>> loaders = new ArrayList<>();

        for (List<Product> loaderProducts : dividedProducts) {
            loaders.add(CompletableFuture.runAsync(new Loader(loaderProducts)));
        }

        // Ожидаем завершения всех грузчиков
        CompletableFuture<Void> allLoaders = CompletableFuture.allOf(loaders.toArray(new CompletableFuture[0]));

        // Ждем, пока все грузчики закончат свою работу
        allLoaders.join();

        System.out.println("Все товары перенесены.");
    }
}