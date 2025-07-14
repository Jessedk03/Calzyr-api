package com.calzyr.entities.company;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "company_id")
    private Integer Id;

    @Column(name = "company_name")
    private String CompanyName;

    @Column(name = "coc_number")
    private String CocNumber;

    @Column(name = "vat_number")
    private String VatNumber;

    @Column(name = "street_name")
    private String StreetName;

    @Column(name = "street_number")
    private Integer StreetNumber;

    @Column(name = "street_number_suffix")
    private String StreetNumberSuffix;

    @Column(name = "office_number")
    private Integer OfficeNumber;

    @Column(name = "office_floor")
    private Integer OfficeFloor;

    @Column(name = "city")
    private String City;

    @Column(name = "province_state")
    private String ProvinceState;

    @Column(name = "country")
    private String Country;

    @Column(name = "created_at")
    private Timestamp CreatedAt;

    @Column(name = "updated_at")
    private Timestamp UpdatedAt;

    @Column(name = "deleted_at")
    private Timestamp DeletedAt;

}
