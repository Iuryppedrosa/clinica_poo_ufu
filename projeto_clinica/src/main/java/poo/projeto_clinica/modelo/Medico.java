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
public class Medico extends Usuario{
    private String crm;
    private String especialidade;
    private List<String> convenios;
    private List<Consulta> consultas;
    private Agenda agenda;

    public Medico(String nome, String senha, String email, String login, String crm, String especialidade) {
        super(nome, senha, email, login);
        this.crm = crm;
        this.especialidade = especialidade;
        this.convenios = new ArrayList<>();
        this.consultas = new ArrayList<>();
        this.agenda = new Agenda(this);
    }

    public void adicionarConvenio(String convenio) {
        convenios.add(convenio);
    }

    public void adicionarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }
    
    public Agenda getAgenda() {
        return agenda;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }
    
    public String relatorioConsultas() {
        StringBuilder sb = new StringBuilder();
        sb.append("Relatório de Consultas do Dr. ").append(getNome()).append(":\n");
        for (Consulta consulta : consultas) {
            sb.append("Data: ").append(consulta.getData())
              .append(", Paciente: ").append(consulta.getPaciente().getNome())
              .append(", Diagnóstico: ").append(consulta.getDiagnostico()).append("\n");
        }
        return sb.toString();
    }

    public String getCrm() { return crm; }
    public String getEspecialidade() { return especialidade; }
    public List<String> getConvenios() { return convenios; }
    
}
