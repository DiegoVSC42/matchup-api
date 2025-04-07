package dev.diegovsc42.MatchUp_API.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Partida {
    Equipe equipeA;
    Equipe equipeB;
    Equipe reserva;

    public Partida(Equipe equipeA, Equipe equipeB, Equipe reserva) {
        this.equipeA = equipeA;
        this.equipeB = equipeB;
        this.reserva = reserva;
    }
}
