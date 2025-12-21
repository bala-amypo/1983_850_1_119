@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    private String fullName;
    private String password;
    private String role;
    private LocalDateTime createdAt = LocalDateTime.now();
}
