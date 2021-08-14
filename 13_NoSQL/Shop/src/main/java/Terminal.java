import com.mongodb.client.MongoCollection;
import model.BaseRepository;
import model.product.Product;
import model.product.ProductRepository;
import model.shop.Shop;
import model.shop.ShopRepository;
import org.bson.Document;

import java.util.Scanner;

public class Terminal {
    private String host;
    private int port;
    private BaseRepository mongoDB;
    private ShopRepository shopRepository;
    private ProductRepository productRepository;
    private MongoCollection<Document> shops;
    private MongoCollection<Document> goods;
    private ParseRequest parseRequest;

    public Terminal(String host, int port, String nameBase) {
        this.host = host;
        this.port = port;
        mongoDB = new BaseRepository(host, port, nameBase);
        shopRepository = new ShopRepository(mongoDB);
        productRepository = new ProductRepository(mongoDB);
    }

    public Terminal() {
        parseRequest = new ParseRequest();
    }

    public void startTerminal() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String request = scanner.nextLine();
            System.out.println(parseCommand(request));
        }
    }

    public String parseCommand(String request) {
        String[] command = request.split("\\s+");
        if (ParseRequest.isAddShop(command)) {
            Shop newShop = new Shop(command[1]);
            shopRepository.addShop(newShop);
            return "Магазин " + newShop.getName() + " добавлен. Сумма магазинов: " + shopRepository.getCountShop();
        } else if (ParseRequest.isAddProduct(command)) {
            Product newProduct = new Product(command[1], Integer.parseInt(command[2]));
            productRepository.addProduct(newProduct);
            return "Товар " + newProduct.getName() + " (цена: " + newProduct.getPrice() + ") добавлен. Сумма товаров: " + productRepository.getCountShop();
        } else if (ParseRequest.isGetProductInShop(command)) {
            if (shopRepository.putProduct(command[1], command[2])) {
                return "Товар " + command[2] + " выставлен в магазине " + command[1] + ". Список товара в магазине: " + shopRepository.getAllProducts(command[1]);
            }
            return "Товар выложить невозможно";
        } else if (ParseRequest.isStatisticsProduct(command)) {
            mongoDB.statisticProducts();
            return "";
        } else {
            return "Bad request";
        }
    }
}
