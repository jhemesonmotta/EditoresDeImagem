package limiariza;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class FiltragemEspacial {
    public BufferedImage grayScale(File img){
        try{
            BufferedImage imagem = ImageIO.read(img); // lê a imagem... caso não encontre, gerará uma ex
            
            int largura = imagem.getWidth();
            int altura = imagem.getHeight();
            
            for (int coluna = 0; coluna < largura; coluna = coluna + 1) {
                for (int linha = 0; linha < altura; linha = linha + 1) {
                      Color cor = new Color(imagem.getRGB(coluna, linha));
                      
                      int blue = cor.getBlue();
                      int red = cor.getRed();
                      int green = cor.getGreen();
                      
                      int gray = (blue + red + green) / 3;
                      
                      Color novaCor = new Color(gray, gray, gray);
                      
                      imagem.setRGB(coluna, linha, novaCor.getRGB());
                }
            }
            return imagem;
        }
        catch(Exception ex){
            System.out.println("Erro: " + ex);
            return new BufferedImage(0, 0, 0);
        }
    }
    
    public BufferedImage suavizar(BufferedImage imagem, int x, int y, int nivelSuavizacao){
        try{
            for (int coluna = 0; coluna < nivelSuavizacao; coluna = coluna + 1) {
                for (int linha = 0; linha < nivelSuavizacao; linha = linha + 1) {
                    int media = media(pegaVizinhos(imagem, x, y));
                    Color novaCor = new Color(media, media, media);
                    imagem.setRGB(coluna, linha, novaCor.getRGB());
                    x = x + 1;
                }
                y = y + 1;
            }
            return imagem;
        }
        catch(Exception ex){
            return new BufferedImage(0, 0, 0);
        }
    }
    
    public BufferedImage realceDiagonal(File img, int x, int y, int nivel){
        try{
            BufferedImage imagem = this.grayScale(img);
            for (int coluna = 0; coluna < nivel; coluna = coluna + 1) {
                for (int linha = 0; linha < nivel; linha = linha + 1) {
                    try{
                      int media = mediaDiagonal(pegaVizinhos(imagem, x, y));
                      Color novaCor = new Color(media, media, media);
                      imagem.setRGB(coluna, linha, novaCor.getRGB());  
                    }
                    catch(Exception ex){}
                    x = x + 1;
                }
                y = y + 1;
            }
            return imagem;
        }
        catch(Exception ex){
            return new BufferedImage(0, 0, 0);
        }
    }
    
    public int[][] pegaVizinhos(BufferedImage imagem, int x0, int y0){
        int vizinhanca[][] = new int[3][3];
        
        try{vizinhanca[0][0] = (new Color(imagem.getRGB(x0-1, y0-1))).getBlue();}
        catch(Exception ex){}
        
        try{vizinhanca[0][1] = (new Color(imagem.getRGB(x0-1, y0))).getBlue();}
        catch(Exception ex){}
        
        try{vizinhanca[0][2] = (new Color(imagem.getRGB(x0-1, y0+1))).getBlue();}
        catch(Exception ex){}
        
        try{vizinhanca[1][0] = (new Color(imagem.getRGB(x0, y0-1))).getBlue();}
        catch(Exception ex){}
        
        try{vizinhanca[1][1] = (new Color(imagem.getRGB(x0, y0))).getBlue();}
        catch(Exception ex){}
        
        try{vizinhanca[1][2] = (new Color(imagem.getRGB(x0, y0+1))).getBlue();}
        catch(Exception ex){}
        
        try{vizinhanca[2][0] = (new Color(imagem.getRGB(x0+1, y0-1))).getBlue();}
        catch(Exception ex){}
        
        try{vizinhanca[2][1] = (new Color(imagem.getRGB(x0+1, y0))).getBlue();}
        catch(Exception ex){}
        
        try{vizinhanca[2][2] = (new Color(imagem.getRGB(x0+1, y0+1))).getBlue();}
        catch(Exception ex){}

        return vizinhanca;
    }
    
    public int media(int matriz[][]){
        int soma = 0;
        for (int coluna = 0; coluna < 3; coluna = coluna + 1) {
            for (int linha = 0; linha < 3; linha = linha + 1) {
                soma = soma + matriz[coluna][linha];
            }
        }
        return Math.round((soma/9));
    }
    
    public int mediaDiagonal(int matriz[][]){
        int soma = 0;
        
        soma = soma + matriz[0][0];
        soma = soma + matriz[0][2];
        soma = soma + matriz[1][1];
        soma = soma + matriz[2][2];
        soma = soma + matriz[2][0];
        
        soma = soma - matriz[0][1];
        soma = soma - matriz[1][0];
        soma = soma - matriz[1][2];
        soma = soma - matriz[2][1];
        
        for (int coluna = 0; coluna < 3; coluna = coluna + 1) {
            for (int linha = 0; linha < 3; linha = linha + 1) {
                soma = soma + matriz[coluna][linha];
            }
        }
        return Math.round((soma/9));
    }
    
    
}
