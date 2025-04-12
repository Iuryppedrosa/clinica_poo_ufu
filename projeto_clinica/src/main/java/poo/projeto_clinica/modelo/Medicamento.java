/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.projeto_clinica.modelo;

/**
 *
 * @author iuryp
 */
public class Medicamento {
    private String nome;
    private String dosagem;

    public Medicamento(String nome, String dosagem) {
        this.nome = nome;
        this.dosagem = dosagem;
    }

    public String getNome() { return nome; }
    public String getDosagem() { return dosagem; }
}
