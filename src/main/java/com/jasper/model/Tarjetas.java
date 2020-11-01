package com.jasper.model;

public class Tarjetas {

  private Integer numero;

  private String producto;

  private String folio;

  private Double importe;

  private String comprobante;

  public Tarjetas(Integer numero, String producto, String folio, Double importe,
      String comprobante) {
    super();
    this.numero = numero;
    this.producto = producto;
    this.folio = folio;
    this.importe = importe;
    this.comprobante = comprobante;
  }

  public Integer getNumero() {
    return numero;
  }

  public void setNumero(Integer numero) {
    this.numero = numero;
  }

  public String getProducto() {
    return producto;
  }

  public void setProducto(String producto) {
    this.producto = producto;
  }

  public String getFolio() {
    return folio;
  }

  public void setFolio(String folio) {
    this.folio = folio;
  }

  public Double getImporte() {
    return importe;
  }

  public void setImporte(Double importe) {
    this.importe = importe;
  }

  public String getComprobante() {
    return comprobante;
  }

  public void setComprobante(String comprobante) {
    this.comprobante = comprobante;
  }

  @Override
  public String toString() {
    return "Tarjetas [numero=" + numero + ", producto=" + producto + ", folio=" + folio
        + ", importe=" + importe + ", comprobante=" + comprobante + "]";
  }

}
