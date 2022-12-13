package com.retro.visionarycrofting.entities;

import javax.persistence.*;
import java.util.*;

@Entity
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Temporal(TemporalType.TIMESTAMP)
  private Date date;

  @Transient
  private Double totalPrice;

  @Transient
  private int itemsNumber;

  @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
  private List<CartItem> items = new ArrayList<>();

  private String sessionToken;

  public Double getTotalPrice() {
    double sum = 0.0;
    for(CartItem item : this.items) {
      sum = sum + item.getProduct().getPrix()*item.getQuantity();
    }
    return sum;
  }
  public int getItemsNumber() {
    return this.items.size();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public List<CartItem> getItems() {
    return items;
  }

  public void setItems(List<CartItem> items) {
    this.items = items;
  }

  public String getSessionToken() {
    return sessionToken;
  }

  public void setSessionToken(String sessionToken) {
    this.sessionToken = sessionToken;
  }

  @Override
  public String toString() {
    return "Cart{" +
      "id=" + id +
      ", date=" + date +
      ", totalPrice=" + totalPrice +
      ", itemsNumber=" + itemsNumber +
      ", items=" + items +
      ", sessionToken='" + sessionToken + '\'' +
      '}';
  }
}
