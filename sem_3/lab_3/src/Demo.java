public class Demo {
  public static void main(String[] args) {
    HashTable<String, Product> productTable = new HashTable<>();

    productTable.put("1234567890123", new Product("Молоко", 85.50, 100));
    productTable.put("9876543210987", new Product("Хлеб", 45.00, 50));
    productTable.put("4567891234567", new Product("Сыр", 320.75, 25));
    productTable.put("1112223334445", new Product("Яблоки", 120.00, 75));

    System.out.println("Размер таблицы: " + productTable.size());
    System.out.println("Таблица пуста: " + productTable.isEmpty());
    System.out.println();

    // Получаем информацию о продукте
    String barcode = "1234567890123";
    Product milk = productTable.get(barcode);
    System.out.println("Продукт с штрихкодом " + barcode + ": " + milk);

    // Обновляем информацию о продукте
    productTable.put("1234567890123", new Product("Молоко", 90.00, 80));
    milk = productTable.get("1234567890123");
    System.out.println("Обновленная информация: " + milk);
    System.out.println();

    // Удаляем продукт
    Product removed = productTable.remove("4567891234567");
    System.out.println("Удаленный продукт: " + removed);
    System.out.println("Размер таблицы после удаления: " + productTable.size());
    System.out.println();

    // Пытаемся получить несуществующий продукт
    Product unknown = productTable.get("0000000000000");
    System.out.println("Несуществующий продукт: " + unknown);
    System.out.println();

    // Выводим содержимое таблицы
    System.out.println("Содержимое хэш-таблицы:");
    productTable.printTable();
  }
}
