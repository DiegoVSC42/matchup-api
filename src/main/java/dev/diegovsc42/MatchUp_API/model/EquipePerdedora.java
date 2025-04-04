package dev.diegovsc42.MatchUp_API.model;

public enum EquipePerdedora {
    A, B;

    public static EquipePerdedora fromChar(char c) {
        return switch (Character.toUpperCase(c)) {
            case 'A' -> A;
            case 'B' -> B;
            default -> throw new IllegalArgumentException("Equipe inválida: " + c);
        };
    }
}
