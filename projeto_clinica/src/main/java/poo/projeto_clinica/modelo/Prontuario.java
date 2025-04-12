/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.projeto_clinica.modelo;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author iuryp
 */
public class Prontuario {
    private Paciente paciente;
    private List<Consulta> consultas;
    private String numeroProntuario;

    public Prontuario(Paciente paciente) {
        this.paciente = paciente;
        this.consultas = new ArrayList<>();
        this.numeroProntuario = "PR" + System.currentTimeMillis(); // Exemplo de número único
    }

    public void adicionarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }

    public String gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Prontuário de ").append(paciente.getNome()).append("\n");
        for (Consulta consulta : consultas) {
            relatorio.append("Data: ").append(consulta.getData())
                     .append(", Diagnóstico: ").append(consulta.getDiagnostico()).append("\n");
        }
        return relatorio.toString();
    }
    
}
