package br.com.osa.stander.dto;

//import jakarta.validation.constraints.NotNull;
import org.antlr.v4.runtime.misc.NotNull;

public class AgenciaRequest {
    @NotNull
    private Integer posX;
    @NotNull
    private Integer posY;

    public Integer getPosX() { return posX; }
    public void setPosX(Integer posX) { this.posX = posX; }
    public Integer getPosY() { return posY; }
    public void setPosY(Integer posY) { this.posY = posY; }
}
