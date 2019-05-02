package com.Remigiusz.MacronutrientsApiREST.DAO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product {

    public Product() {

    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "nazwa")
    String name;
    @Column(name = "kcal")
    int calories;
    @Column(name = "bialko")
    float protein;
    @Column(name = "tluszcze")
    float fats;
    @Column(name = "weglowodany")
    float carbohydrates;

    @ManyToMany(fetch = FetchType.LAZY,cascade ={CascadeType.DETACH
            , CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "day_product"
            ,joinColumns = @JoinColumn(name = "produkt_id")
            ,inverseJoinColumns = @JoinColumn(name = "day_of_life_id"))
    @JsonIgnore
    List<Day> daysList=new ArrayList<>();

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    List<DayProductsConnection> connectionsList=new ArrayList<>();




    @Override
    public String toString() {
        return  name;
    }
}