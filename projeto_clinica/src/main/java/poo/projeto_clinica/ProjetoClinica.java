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
 import java.time.format.DateTimeParseException;
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
             System.out.println("9. Registrar dados da consulta" );

             System.out.println("10. Sair");
             System.out.print("Escolha uma opção: ");
             int opcao = scanner.nextInt();
             scanner.nextLine();
 
             if (opcao == 10) break;
 
             switch (opcao) {
                 case 1:
                    try {
                        System.out.print("Nome: ");
                        String nomeMedico = scanner.nextLine();
                        if (nomeMedico.isEmpty()) throw new IllegalArgumentException("Nome não pode ser vazio.");

                        System.out.print("CRM: ");
                        String crm = scanner.nextLine();
                        if (crm.isEmpty()) throw new IllegalArgumentException("CRM não pode ser vazio.");

                        System.out.print("Especialidade: ");
                        String especialidade = scanner.nextLine();
                        if (especialidade.isEmpty()) throw new IllegalArgumentException("Especialidade não pode ser vazia.");

                        sistema.cadastrarMedico(nomeMedico, "senha", "email", "login", crm, especialidade);
                        System.out.println("Médico cadastrado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao cadastrar médico: " + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Nome: ");
                        String nomePaciente = scanner.nextLine();
                        if (nomePaciente.isEmpty()) throw new IllegalArgumentException("Nome não pode ser vazio.");

                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        if (cpf.isEmpty()) throw new IllegalArgumentException("CPF não pode ser vazio.");

                        sistema.cadastrarPaciente(nomePaciente, "senha", "email", "login", cpf);
                        System.out.println("Paciente cadastrado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao cadastrar paciente: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Nome do Medicamento: ");
                        String nomeMedicamento = scanner.nextLine();
                        if (nomeMedicamento.isEmpty()) throw new IllegalArgumentException("Nome do medicamento não pode ser vazio.");

                        System.out.print("Dosagem: ");
                        String dosagem = scanner.nextLine();
                        if (dosagem.isEmpty()) throw new IllegalArgumentException("Dosagem não pode ser vazia.");

                        sistema.cadastrarMedicamento(nomeMedicamento, dosagem);
                        System.out.println("Medicamento cadastrado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao cadastrar medicamento: " + e.getMessage());
                    }
                    break;

                case 4:
                    if (sistema.medicos.isEmpty() || sistema.pacientes.isEmpty()) {
                        System.out.println("Cadastre médicos e pacientes primeiro!");
                    } else {
                        try {
                            Medico medico = sistema.medicos.get(0);
                            System.out.print("Horário (dd/MM/yyyy HH:mm): ");
                            String dataStr = scanner.nextLine();
                            LocalDateTime data = LocalDateTime.parse(dataStr, formatter);
                            medico.getAgenda().adicionarHorarioDisponivel(data);
                            System.out.println("Horário adicionado com sucesso!");
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de data inválido! Use o formato dd/MM/yyyy HH:mm.");
                        } catch (Exception e) {
                            System.out.println("Erro ao adicionar horário: " + e.getMessage());
                        }
                    }
                    break;

                case 5:
                    if (sistema.medicos.isEmpty() || sistema.pacientes.isEmpty()) {
                        System.out.println("Cadastre médicos e pacientes primeiro!");
                    } else {
                        try {
                            // Listar médicos
                            System.out.println("Escolha o médico:");
                            for (int i = 0; i < sistema.medicos.size(); i++) {
                                System.out.println((i + 1) + " - " + sistema.medicos.get(i).getNome());
                            }
                            System.out.print("Número do médico: ");
                            int medicoIndex = Integer.parseInt(scanner.nextLine()) - 1;

                            if (medicoIndex < 0 || medicoIndex >= sistema.medicos.size()) {
                                System.out.println("Médico inválido!");
                                break;
                            }
                            Medico medico = sistema.medicos.get(medicoIndex);

                            // Listar pacientes
                            System.out.println("Escolha o paciente:");
                            for (int i = 0; i < sistema.pacientes.size(); i++) {
                                System.out.println((i + 1) + " - " + sistema.pacientes.get(i).getNome());
                            }
                            System.out.print("Número do paciente: ");
                            int pacienteIndex = Integer.parseInt(scanner.nextLine()) - 1;

                            if (pacienteIndex < 0 || pacienteIndex >= sistema.pacientes.size()) {
                                System.out.println("Paciente inválido!");
                                break;
                            }
                            Paciente paciente = sistema.pacientes.get(pacienteIndex);

                            // Informar horário
                            System.out.print("Horário da consulta (dd/MM/yyyy HH:mm): ");
                            String dataStr = scanner.nextLine();
                            LocalDateTime data = LocalDateTime.parse(dataStr, formatter);

                            if (medico.getAgenda().isHorarioDisponivel(data)) {
                                sistema.agendarConsulta(medico, paciente, data);
                                System.out.println("Consulta agendada com sucesso!");
                            } else {
                                System.out.println("Horário não disponível para este médico!");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida! Digite apenas números para selecionar médico e paciente.");
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de data inválido! Use o formato dd/MM/yyyy HH:mm.");
                        } catch (Exception e) {
                            System.out.println("Erro ao agendar consulta: " + e.getMessage());
                        }
                    }
                    break;

                case 6:
                    if (sistema.medicos.isEmpty()) {
                        System.out.println("Nenhum médico cadastrado!");
                    } else {
                        try {
                            Medico medico = sistema.medicos.get(0);
                            System.out.println(medico.getAgenda().visualizarAgenda());
                        } catch (Exception e) {
                            System.out.println("Erro ao visualizar agenda: " + e.getMessage());
                        }
                    }
                    break;

                case 7:
                    if (sistema.medicos.isEmpty()) {
                        System.out.println("Nenhum médico cadastrado!");
                    } else {
                        try {
                            // Listar médicos para escolha
                            System.out.println("Escolha o médico para gerar o relatório de consultas:");
                            for (int i = 0; i < sistema.medicos.size(); i++) {
                                System.out.println((i + 1) + " - " + sistema.medicos.get(i).getNome());
                            }
                            System.out.print("Número do médico: ");
                            int medicoIndex = Integer.parseInt(scanner.nextLine()) - 1;

                            if (medicoIndex < 0 || medicoIndex >= sistema.medicos.size()) {
                                System.out.println("Médico inválido!");
                                break;
                            }
                            Medico medico = sistema.medicos.get(medicoIndex);
                            System.out.println(medico.relatorioConsultas());
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida! Digite apenas números.");
                        } catch (Exception e) {
                            System.out.println("Erro ao gerar relatório de consultas: " + e.getMessage());
                        }
                    }
                    break;


                case 8:
                    if (sistema.pacientes.isEmpty()) {
                        System.out.println("Nenhum paciente cadastrado!");
                    } else {
                        try {
                            // Listar pacientes para escolha
                            System.out.println("Escolha o paciente para gerar o relatório do prontuário:");
                            for (int i = 0; i < sistema.pacientes.size(); i++) {
                                System.out.println((i + 1) + " - " + sistema.pacientes.get(i).getNome());
                            }
                            System.out.print("Número do paciente: ");
                            int pacienteIndex = Integer.parseInt(scanner.nextLine()) - 1;

                            if (pacienteIndex < 0 || pacienteIndex >= sistema.pacientes.size()) {
                                System.out.println("Paciente inválido!");
                                break;
                            }
                            Paciente paciente = sistema.pacientes.get(pacienteIndex);
                            System.out.println(paciente.getProntuario().gerarRelatorio());
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida! Digite apenas números.");
                        } catch (Exception e) {
                            System.out.println("Erro ao gerar relatório do prontuário: " + e.getMessage());
                        }
                    }
                   break;
                case 9:
                    if (sistema.pacientes.isEmpty()) {
                        System.out.println("Nenhum paciente cadastrado!");
                    } else {
                        Paciente paciente = sistema.pacientes.get(0); // ou escolha com scanner
                        List<Consulta> consultas = paciente.getProntuario().getConsultas();

                        if (consultas.isEmpty()) {
                            System.out.println("Esse paciente não possui consultas.");
                        } else {
                            System.out.println("Consultas disponíveis:");
                            for (int i = 0; i < consultas.size(); i++) {
                                Consulta c = consultas.get(i);
                                System.out.println(i + " - " + c.getData().format(formatter));
                            }

                            System.out.print("Escolha a consulta (número): ");
                            int indiceConsulta = scanner.nextInt();
                            scanner.nextLine(); // consumir quebra de linha

                            Consulta consulta = consultas.get(indiceConsulta);

                            System.out.print("Batimentos cardíacos: ");
                            int batimentos = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Pressão arterial: ");
                            String pressao = scanner.nextLine();

                            System.out.print("Temperatura corporal: ");
                            double temperatura = scanner.nextDouble();
                            scanner.nextLine();

                            System.out.print("Diagnóstico: ");
                            String diagnostico = scanner.nextLine();

                            consulta.registrarDadosClinicos(batimentos, pressao, temperatura, diagnostico);
                            System.out.println("Dados clínicos registrados com sucesso!");
                        }
                    }
                    break;
                }
            }
            scanner.close();
        }
 }
 