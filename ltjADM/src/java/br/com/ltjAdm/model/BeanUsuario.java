package br.com.ltjAdm.model;

/**
 *
 * @author laudson
 */
public class BeanUsuario {

    private String id_usuario;
    private String nome_completo;
    private String nome_usuario;
    private String permissao;
    private String senha;

    /**
     * @return the id_usuario
     */
    public String getId_usuario() {
        return id_usuario;
    }

    /**
     * @param id_usuario the id_usuario to set
     */
    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * @return the nome_completo
     */
    public String getNome_completo() {
        return nome_completo;
    }

    /**
     * @param nome_completo the nome_completo to set
     */
    public void setNome_completo(String nome_completo) {
        this.nome_completo = nome_completo;
    }

    /**
     * @return the nome_usuario
     */
    public String getNome_usuario() {
        return nome_usuario;
    }

    /**
     * @param nome_usuario the nome_usuario to set
     */
    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    /**
     * @return the permissao
     */
    public String getPermissao() {
        return permissao;
    }

    /**
     * @param permissao the permissao to set
     */
    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
