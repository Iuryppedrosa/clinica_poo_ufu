/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.projeto_clinica.modelo;

/**
 *
 * @author iuryp
 */
public class Paciente extends Usuario {
    private String cpf;
    private Prontuario prontuario;

    public Paciente(String nome, String senha, String email, String login, String cpf) {
        super(nome, senha, email, login);
        this.cpf = cpf;
        this.prontuario = new Prontuario(this);
    }

    public Prontuario getProntuario() {
        return prontuario;
    }

    public String getCpf() { return cpf; }
    
}
