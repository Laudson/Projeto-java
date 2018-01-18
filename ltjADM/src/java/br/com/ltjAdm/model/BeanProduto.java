package br.com.ltjAdm.model;

/**
 *
 * @author Junior
 */
public class BeanProduto {
    
    private String id_produto;
    private String nome_produto;
    private String nome_grupo;
    private String nome_categoria;
    private String id_grupo;
    private String id_categoria;
    private String caminho_imagem;

    /**
     * @return the id_produto
     */
    public String getId_produto() {
        return id_produto;
    }

    /**
     * @param id_produto the id_produto to set
     */
    public void setId_produto(String id_produto) {
        this.id_produto = id_produto;
    }

    /**
     * @return the nome_produto
     */
    public String getNome_produto() {
        return nome_produto;
    }

    /**
     * @param nome_produto the nome_produto to set
     */
    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    /**
     * @return the nome_grupo
     */
    public String getNome_grupo() {
        return nome_grupo;
    }

    /**
     * @param nome_grupo the nome_grupo to set
     */
    public void setNome_grupo(String nome_grupo) {
        this.nome_grupo = nome_grupo;
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

    /**
     * @return the id_grupo
     */
    public String getId_grupo() {
        return id_grupo;
    }

    /**
     * @param id_grupo the id_grupo to set
     */
    public void setId_grupo(String id_grupo) {
        this.id_grupo = id_grupo;
    }

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
     * @return the caminho_imagem
     */
    public String getCaminho_imagem() {
        return caminho_imagem;
    }

    /**
     * @param caminho_imagem the caminho_imagem to set
     */
    public void setCaminho_imagem(String caminho_imagem) {
        this.caminho_imagem = caminho_imagem;
    }
    
}
