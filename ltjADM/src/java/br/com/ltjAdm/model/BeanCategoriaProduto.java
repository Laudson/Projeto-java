/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ltjAdm.model;

/**
 *
 * @author Junior
 */
public class BeanCategoriaProduto {
    
    private String id_categoria;
    private String nome_categoria;

    /**
     * @return the id_categoria
     */
    public String getId_categoria() {
        return id_categoria;
    }

    /**
     * @param id_categoria the id_categoria to set
     */
    public void setId_categoria(String id_categoria) {
        this.id_categoria = id_categoria;
    }

    /**
     * @return the nome_categoria
     */
    public String getNome_categoria() {
        return nome_categoria;
    }

    /**
     * @param nome_categoria the nome_categoria to set
     */
    public void setNome_categoria(String nome_categoria) {
        this.nome_categoria = nome_categoria;
    }
    
}
