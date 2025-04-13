/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package poo.projeto_clinica;
import poo.projeto_clinica.modelo.Paciente;
import poo.projeto_clinica.modelo.Medico;
import poo.projeto_clinica.modelo.Consulta;
import poo.projeto_clinica.modelo.Medicamento;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author iuryp
 */
public class ProjetoClinica {

    private List<Medico> medicos;
    private List<Paciente> pacientes;
    private List<Medicamento> medicamentos;

    public ProjetoClinica() {
        medicos = new ArrayList<>();
        pacientes = new ArrayList<>();
        medicamentos = new ArrayList<>();
    }

    public void cadastrarMedico(String nome, String senha, String email, String login, String crm, String especialidade) {
        Medico medico = new Medico(nome, senha, email, login, crm, especialidade);
        medicos.add(medico);
        System.out.println("Médico cadastrado com sucesso!");
    }

    public void cadastrarPaciente(String nome, String senha, String email, String login, String cpf) {
        Paciente paciente = new Paciente(nome, senha, email, login, cpf);
        pacientes.add(paciente);
        System.out.println("Paciente cadastrado com sucesso!");
    }

    public void cadastrarMedicamento(String nome, String dosagem) {
        Medicamento medicamento = new Medicamento(nome, dosagem);
        medicamentos.add(medicamento);
        System.out.println("Medicamento cadastrado com sucesso!");
    }

    public void agendarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        Consulta consulta = new Consulta(medico, paciente, data);
        medico.adicionarConsulta(consulta);
        paciente.getProntuario().adicionarConsulta(consulta);
        medico.getAgenda().reservarHorario(data);
        System.out.println("Consulta agendada com sucesso!");
    }

    public static void main(String[] args) {
        ProjetoClinica sistema = new ProjetoClinica();
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        while (true) {
            System.out.println("\n1. Cadastrar Médico");
            System.out.println("2. Cadastrar Paciente");
            System.out.println("3. Cadastrar Medicamento");
            System.out.println("4. Adicionar Horário na Agenda");
            System.out.println("5. Agendar Consulta");
            System.out.println("6. Visualizar Agenda do Médico");
            System.out.println("7. Gerar Relatório de Consultas do Médico");
            System.out.println("8. Gerar Relatório do Prontuário do Paciente");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 9) break;

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nomeMedico = scanner.nextLine();
                    System.out.print("CRM: ");
                    String crm = scanner.nextLine();
                    System.out.print("Especialidade: ");
                    String especialidade = scanner.nextLine();
                    sistema.cadastrarMedico(nomeMedico, "senha", "email", "login", crm, especialidade);
                    break;
                case 2:
                    System.out.print("Nome: ");
                    String nomePaciente = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    sistema.cadastrarPaciente(nomePaciente, "senha", "email", "login", cpf);
                    break;
                case 3:
                    System.out.print("Nome do Medicamento: ");
                    String nomeMedicamento = scanner.nextLine();
                    System.out.print("Dosagem: ");
                    String dosagem = scanner.nextLine();
                    sistema.cadastrarMedicamento(nomeMedicamento, dosagem);
                    break;
                case 4:
                    if (sistema.medicos.isEmpty() || sistema.pacientes.isEmpty()) {
                        System.out.println("Cadastre médicos e pacientes primeiro!");
                    } else {
                        Medico medico = sistema.medicos.get(0); // Simplificação: primeiro médico
                        System.out.print("Horário (dd/MM/yyyy HH:mm): ");
                        String dataStr = scanner.nextLine();
                        LocalDateTime data = LocalDateTime.parse(dataStr, formatter);
                        medico.getAgenda().adicionarHorarioDisponivel(data);
                        System.out.println("Horário adicionado com sucesso!");
                    }
                    break;
                case 5:
                    if (sistema.medicos.isEmpty() || sistema.pacientes.isEmpty()) {
                        System.out.println("Cadastre médicos e pacientes primeiro!");
                    } else {
                        Medico medico = sistema.medicos.get(0);
                        Paciente paciente = sistema.pacientes.get(0);
                        System.out.print("Horário da consulta (dd/MM/yyyy HH:mm): ");
                        String dataStr = scanner.nextLine();
                        LocalDateTime data = LocalDateTime.parse(dataStr, formatter);
                        if (medico.getAgenda().isHorarioDisponivel(data)) {
                            sistema.agendarConsulta(medico, paciente, data);
                        } else {
                            System.out.println("Horário não disponível!");
                        }
                    }
                    break;
                case 6:
                    if (sistema.medicos.isEmpty()) {
                        System.out.println("Nenhum médico cadastrado!");
                    } else {
                        Medico medico = sistema.medicos.get(0);
                        System.out.println(medico.getAgenda().visualizarAgenda());
                    }
                    break;
                case 7:
                    if (sistema.medicos.isEmpty()) {
                        System.out.println("Nenhum médico cadastrado!");
                    } else {
                        Medico medico = sistema.medicos.get(0);
                        System.out.println(medico.relatorioConsultas());
                    }
                    break;
                case 8:
                    if (sistema.pacientes.isEmpty()) {
                        System.out.println("Nenhum paciente cadastrado!");
                    } else {
                        Paciente paciente = sistema.pacientes.get(0);
                        System.out.println(paciente.getProntuario().gerarRelatorio());
                    }
                    break;
            }
        }
        scanner.close();
    }
}
