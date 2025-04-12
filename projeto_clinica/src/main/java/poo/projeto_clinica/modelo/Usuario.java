/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.projeto_clinica.modelo;

/**
 *
 * @author iuryp
 */
public abstract class Usuario {
    private String nome;
    private String senha;
    private String email;
    private String login;

    public Usuario(String nome, String senha, String email, String login) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.login = login;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    
}
