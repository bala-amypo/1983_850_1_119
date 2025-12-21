@Entity
public class Stock {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String ticker;

    private String companyName;
    private String sector;
    private Boolean active = true;
}
