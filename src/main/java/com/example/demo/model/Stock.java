// package com.example.demo.model;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "stocks")
// public class Stock {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(unique = true, nullable = false)
//     private String ticker;

//     @Column(name = "company_name")
//     private String companyName;

//     private String sector;

//     @Column(name = "is_active")
//     private Boolean isActive;

//     public Stock() {
//     }

//     public Stock(String ticker, String companyName, String sector, Boolean isActive) {
//         this.ticker = ticker;
//         this.companyName = companyName;
//         this.sector = sector;
//         this.isActive = isActive;
//     }

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getTicker() {
//         return ticker;
//     }

//     public void setTicker(String ticker) {
//         this.ticker = ticker;
//     }

//     public String getCompanyName() {
//         return companyName;
//     }

//     public void setCompanyName(String companyName) {
//         this.companyName = companyName;
//     }

//     public String getSector() {
//         return sector;
//     }

//     public void setSector(String sector) {
//         this.sector = sector;
//     }

//     public Boolean getIsActive() {
//         return isActive;
//     }

//     public void setIsActive(Boolean active) {
//         isActive = active;
//     }
// }


package com.example.demo.model;

public class Stock {
    private Long id;
    private String ticker;
    private String companyName;
    private String sector;

    public String getTicker() { return ticker; }
    public void setTicker(String ticker) { this.ticker = ticker; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getSector() { return sector; }
    public void setSector(String sector) { this.sector = sector; }
}