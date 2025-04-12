/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.projeto_clinica.modelo;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author iuryp
 */
public class Agenda {
    private Medico medico;
    private Map<LocalDateTime, Boolean> horariosDisponiveis;

    public Agenda(Medico medico) {
        this.medico = medico;
        this.horariosDisponiveis = new HashMap<>();
    }

    public void adicionarHorarioDisponivel(LocalDateTime horario) {
        horariosDisponiveis.put(horario, true);
    }

    public boolean isHorarioDisponivel(LocalDateTime horario) {
        return horariosDisponiveis.getOrDefault(horario, false);
    }

    public void reservarHorario(LocalDateTime horario) {
        if (isHorarioDisponivel(horario)) {
            horariosDisponiveis.put(horario, false);
        } else {
            throw new IllegalStateException("Horário não disponível!");
        }
    }

    public String visualizarAgenda() {
        StringBuilder sb = new StringBuilder();
        sb.append("Agenda do Dr. ").append(medico.getNome()).append(":\n");
        for (Map.Entry<LocalDateTime, Boolean> entry : horariosDisponiveis.entrySet()) {
            sb.append("Horário: ").append(entry.getKey())
              .append(" - ").append(entry.getValue() ? "Disponível" : "Ocupado").append("\n");
        }
        return sb.toString();
    }
    
}
