/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ltjAdm.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author laudson
 */
public class Criptografia {

    public static String verificaCriptografia(String InfSenha) throws NoSuchAlgorithmException {

        String resultado = "";
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(InfSenha.getBytes(), 0, InfSenha.length());
        resultado = ("MD5: " + new BigInteger(1, m.digest()).toString(16));
        return resultado.substring(resultado.indexOf(" ") + 1, resultado.length());
    }
}
