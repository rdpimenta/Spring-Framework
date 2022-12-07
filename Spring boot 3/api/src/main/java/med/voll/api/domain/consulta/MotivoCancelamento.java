package med.voll.api.domain.consulta;

public enum MotivoCancelamento {
    PACIENTE_DESISTIU("Paciente desistiu"),
    MEDICO_CANCELOU("Médico cancelou"),
    OUTRO("OUTRO");

    public final String motivo;

    private MotivoCancelamento(String motivo) {
        this.motivo = motivo;
    }

    public static MotivoCancelamento valueOfLabel(String motivo) {
        for (MotivoCancelamento m : values()) {
            if (m.motivo.equals(motivo)) {
                return m;
            }
        }
        return null;
    }
}
