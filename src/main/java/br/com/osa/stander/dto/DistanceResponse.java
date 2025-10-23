package br.com.osa.stander.dto;

public class DistanceResponse {
    private String agencia;
    private double distancia;

    public DistanceResponse(String agencia, double distancia) {
        this.agencia = agencia;
        this.distancia = distancia;
    }
    public String getAgencia() { return agencia; }
    public double getDistancia() { return distancia; }
}
