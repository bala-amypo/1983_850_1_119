@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService service;

    public StockController(StockService service) {
        this.service = service;
    }

    @PostMapping
    public Stock create(@RequestBody Stock stock) {
        return service.create(stock);
    }

    @GetMapping
    public List<Stock> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Stock get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public Stock update(@PathVariable Long id, @RequestBody Stock stock) {
        return service.update(id, stock);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivate(id);
    }
}