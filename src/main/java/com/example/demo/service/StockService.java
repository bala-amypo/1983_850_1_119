public interface StockService {
    Stock create(Stock stock);
    Stock update(Long id, Stock stock);
    Stock get(Long id);
    List<Stock> getAll();
    void deactivate(Long id);
}