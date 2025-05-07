package poo.projeto_clinica.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Prontuario {
    private Paciente paciente;
    private List<Consulta> consultas;
    private String numeroProntuario;

    public Prontuario(Paciente paciente) {
        this.paciente = paciente;
        this.consultas = new ArrayList<>();
        this.numeroProntuario = "PR" + System.currentTimeMillis();
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void adicionarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }
    // Dentro da classe Prontuario
    public String gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        for (Consulta consulta : consultas) {
            relatorio.append("Data: ").append(consulta.getData().format(formatter))
                     .append("\n");
        }

        if (relatorio.length() == 0) {
            return "Nenhuma consulta registrada.";
        }

        return relatorio.toString();
    }

  
    
}
    
        

        

       

