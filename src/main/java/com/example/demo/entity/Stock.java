@Entity
@Table(name = "stocks", uniqueConstraints = @UniqueConstraint(columnNames = "ticker"))
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticker;
    private String companyName;
    private String sector;
    private Boolean active = true;

    // getters & setters
}