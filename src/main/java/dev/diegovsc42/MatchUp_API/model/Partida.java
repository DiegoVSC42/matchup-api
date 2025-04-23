package dev.diegovsc42.MatchUp_API.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Schema(description = "Modelo que representa uma partida entre equipes")
public class Partida {
    @EqualsAndHashCode.Include
    private Equipe equipeA;

    @EqualsAndHashCode.Include
    private Equipe equipeB;

    @EqualsAndHashCode.Include
    private Equipe reserva;

    public Partida(Equipe equipeA, Equipe equipeB, Equipe reserva) {
        this.equipeA = equipeA;
        this.equipeB = equipeB;
        this.reserva = reserva;
    }
}