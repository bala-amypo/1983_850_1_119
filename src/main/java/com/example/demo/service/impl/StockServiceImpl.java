@Service
public class StockServiceImpl implements StockService {

    private final StockRepository repo;

    public StockServiceImpl(StockRepository repo) {
        this.repo = repo;
    }

    public Stock create(Stock stock) {
        return repo.save(stock);
    }

    public Stock update(Long id, Stock stock) {
        Stock s = repo.findById(id).orElseThrow();
        s.setCompanyName(stock.getCompanyName());
        s.setSector(stock.getSector());
        return repo.save(s);
    }

    public Stock get(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public List<Stock> getAll() {
        return repo.findAll();
    }

    public void deactivate(Long id) {
        Stock s = repo.findById(id).orElseThrow();
        s.setActive(false);
        repo.save(s);
    }
}