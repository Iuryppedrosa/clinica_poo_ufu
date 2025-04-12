/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.projeto_clinica.modelo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author iuryp
 */
public class Consulta {
    private Medico medico;
    private Paciente paciente;
    private LocalDateTime data;
    private String diagnostico;
    private List<Medicamento> prescricao;
    private int batimentos;
    private String pressao;
    private double temperatura;

    public Consulta(Medico medico, Paciente paciente, LocalDateTime data) {
        this.medico = medico;
        this.paciente = paciente;
        this.data = data;
        this.prescricao = new ArrayList<>();
    }

    public void registrarDadosClinicos(int batimentos, String pressao, double temperatura, String diagnostico) {
        this.batimentos = batimentos;
        this.pressao = pressao;
        this.temperatura = temperatura;
        this.diagnostico = diagnostico;
    }

    public void adicionarMedicamento(Medicamento medicamento) {
        prescricao.add(medicamento);
    }
    
    public Paciente getPaciente() {
        return paciente;
    }

    public LocalDateTime getData() { return data; }
    public String getDiagnostico() { return diagnostico; }
    public List<Medicamento> getPrescricao() { return prescricao; }
    
}
