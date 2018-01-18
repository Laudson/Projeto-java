/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ltjAdm.upload;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Junior
 */
public class Redimensiona {
    
    public static void novoTamanho(String caminho) throws IOException {
        BufferedImage imagem;
        imagem = ImageIO.read(new File(caminho));
        int new_w = 150, new_h = 150;
        BufferedImage new_img = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = new_img.createGraphics();
        g.drawImage(imagem, 0, 0, new_w, new_h, null);
        ImageIO.write(new_img, "JPG", new File(caminho));
    }
    
}
