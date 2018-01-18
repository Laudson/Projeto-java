package br.com.ltjAdm.dao;

import java.util.ArrayList;

/**
 *
 * @author Junior
 */
public class FiltroPesquisa {

    public String Filtrar(String nomeTabela, String campoTabela, String ordem, ArrayList valores, ArrayList coluna) {

        String comandoSQL = "select * from " + nomeTabela + " where 1 " + this.condicional(valores, coluna)
                + " order by " + campoTabela + " " + ordem;
        return comandoSQL;
    }

    private String condicional(ArrayList valores, ArrayList coluna) {

        String clausulaWhere = "";

        if (valores.isEmpty()) {
            return clausulaWhere;
        } else {

            int contador = 0;

            while (valores.size() > contador) {

                clausulaWhere = clausulaWhere + " and " + coluna.get(contador) + " like '%" + valores.get(contador) + "%'";

                contador += 1;
            }
        }
        return clausulaWhere;
    }
}
