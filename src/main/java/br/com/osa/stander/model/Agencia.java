package br.com.osa.stander.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "agencia")
public class Agencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int posX;
    private int posY;

    private Instant createdAt = Instant.now();

    public Agencia() {}
    public Agencia(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public Long getId() { return id; }
    public int getPosX() { return posX; }
    public void setPosX(int posX) { this.posX = posX; }
    public int getPosY() { return posY; }
    public void setPosY(int posY) { this.posY = posY; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public String nomeAgencia() {
        return "AGENCIA_" + id;
    }
}
