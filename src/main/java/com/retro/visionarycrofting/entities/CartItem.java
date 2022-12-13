package com.retro.visionarycrofting.entities;


import javax.persistence.*;
import java.util.Date;

@Entity(name = "cartItem")
public class CartItem {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private int quantity;

  @Temporal(TemporalType.TIMESTAMP)
  private Date date;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "product_id",nullable=false, updatable=false)
  private Product product;




  @Override
  public String toString() {
    return "CartItem{" +
      "id=" + id +
      ", quantity=" + quantity +
      ", date=" + date +
      ", product=" + product +
      '}';
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }
}
