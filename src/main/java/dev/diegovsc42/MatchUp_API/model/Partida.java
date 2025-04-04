package dev.diegovsc42.MatchUp_API.model;

public class Partida {
    Equipe equipeA;
    Equipe equipeB;
    Equipe reserva;

    public Partida(Equipe equipeA, Equipe equipeB, Equipe reserva) {
        this.equipeA = equipeA;
        this.equipeB = equipeB;
        this.reserva = reserva;
    }

    public Equipe getEquipeA() {
        return equipeA;
    }

    public Equipe getEquipeB() {
        return equipeB;
    }

    public void setEquipeA(Equipe equipeA) {
        this.equipeA = equipeA;
    }

    public void setEquipeB(Equipe equipeB) {
        this.equipeB = equipeB;
    }

    public Equipe getReserva() {
        return reserva;
    }
}
