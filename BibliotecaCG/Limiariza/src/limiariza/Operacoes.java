package limiariza;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Operacoes {
    private BufferedImage imagem;
    private int tmax = 0;
    private int tmin = 255;
    
    public Operacoes() {
    }

    public Operacoes(BufferedImage imagem) {
        this.imagem = imagem;
    }

    public BufferedImage getImagem() {
        return imagem;
    }

    public void setImagem(BufferedImage imagem) {
        this.imagem = imagem;
    }

    public int getTmax() {
        return tmax;
    }

    public void setTmax(int tmax) {
        this.tmax = tmax;
    }

    public int getTmin() {
        return tmin;
    }

    public void setTmin(int tmin) {
        this.tmin = tmin;
    }
    
    
    
    public BufferedImage and(BufferedImage imagem, BufferedImage imagem2){
        try{
            int largura = imagem.getWidth();
            int altura = imagem.getHeight();
            
            for (int coluna = 0; coluna < largura; coluna = coluna + 1) {
                for (int linha = 0; linha < altura; linha = linha + 1) {
                    Color cor = new Color(imagem.getRGB(coluna, linha));
                    Color cor2 = new Color(imagem2.getRGB(coluna, linha));
                    
                    Color novaCor;
                    if(positivo(cor) && positivo(cor2)){
                        novaCor = new Color(255, 255, 255);
                    }
                    else{
                        novaCor = new Color(0, 0, 0);
                    }
                    imagem.setRGB(coluna, linha, novaCor.getRGB());
                }
            }
            return imagem;
        }
        catch(Exception ex){
            System.out.println("Erro: "+ex);
            return new BufferedImage(0, 0, 0);
        }
    }
    
    public BufferedImage or(BufferedImage imagem, BufferedImage imagem2){
        try{
            int largura = imagem.getWidth();
            int altura = imagem.getHeight();
            
            for (int coluna = 0; coluna < largura; coluna = coluna + 1) {
                for (int linha = 0; linha < altura; linha = linha + 1) {
                    Color cor = new Color(imagem.getRGB(coluna, linha));
                    Color cor2 = new Color(imagem2.getRGB(coluna, linha));
                    
                    Color novaCor;
                    if(positivo(cor) || positivo(cor2)){
                        novaCor = new Color(255, 255, 255);
                    }
                    else{
                        novaCor = new Color(0, 0, 0);
                    }
                    imagem.setRGB(coluna, linha, novaCor.getRGB());
                }
            }
            return imagem;
        }
        catch(Exception ex){
            System.out.println("Erro: "+ex);
            return new BufferedImage(0, 0, 0);
        }
    }
    
    public BufferedImage sub(File img, File img2){
        try{
            BufferedImage imagem = ImageIO.read(img);
            BufferedImage imagem2 = ImageIO.read(img2);
            
            int largura = imagem.getWidth();
            int altura = imagem.getHeight();
            
            int matrizr[][] = new int[largura][altura];
            int matrizg[][] = new int[largura][altura];
            int matrizb[][] = new int[largura][altura];
            
            for (int coluna = 0; coluna < largura; coluna = coluna + 1) {
                for (int linha = 0; linha < altura; linha = linha + 1) {
                    Color cor = new Color(imagem.getRGB(coluna, linha));
                    Color cor2 = new Color(imagem2.getRGB(coluna, linha));
                    
                    matrizr[coluna][linha] = cor.getRed() - cor2.getRed();
                        if(matrizr[coluna][linha] < 0){
                            matrizr[coluna][linha] = matrizr[coluna][linha] * -1;
                        }
                        
                    matrizg[coluna][linha] = cor.getGreen() - cor2.getGreen();
                        if(matrizg[coluna][linha] < 0){
                            matrizg[coluna][linha] = matrizg[coluna][linha] * -1;
                        }
                        
                    matrizb[coluna][linha] = cor.getBlue() - cor2.getBlue();
                        if(matrizb[coluna][linha] < 0){
                            matrizb[coluna][linha] = matrizb[coluna][linha] * -1;
                        }
                }
            }
            
            int Rmin = 255;
            int Gmin = 255;
            int Bmin = 255;
            int Rmax = 0;
            int Gmax = 0;
            int Bmax = 0;
            
            for (int coluna = 0; coluna < largura; coluna = coluna + 1) {
                for (int linha = 0; linha < altura; linha = linha + 1) {
                    if(matrizr[coluna][linha] > Rmax){
                        Rmax = matrizr[coluna][linha];
                    }
                    if(matrizr[coluna][linha] < Rmin){
                        Rmin = matrizr[coluna][linha];
                    }
                    
                    if(matrizg[coluna][linha] > Gmax){
                        Gmax = matrizg[coluna][linha];
                    }
                    if(matrizg[coluna][linha] < Gmin){
                        Gmin = matrizg[coluna][linha];
                    }
                    
                    if(matrizb[coluna][linha] > Bmax){
                        Bmax = matrizb[coluna][linha];
                    }
                    if(matrizb[coluna][linha] < Bmin){
                        Bmin = matrizb[coluna][linha];
                    }
                }
            }
            
            for (int coluna = 0; coluna < largura; coluna = coluna + 1) {
                for (int linha = 0; linha < altura; linha = linha + 1) {
                    double passo1R = Rmax - Rmin;
                    double passo1G = Gmax - Gmin;
                    double passo1B = Bmax - Bmin;
                    
                    double fator1R = 255 / passo1R;
                    double fator1G = 255 / passo1G;
                    double fator1B = 255 / passo1B;
                    
                    double fator2R = matrizr[coluna][linha] - Rmin;
                    double fator2G = matrizg[coluna][linha] - Gmin;
                    double fator2B = matrizb[coluna][linha] - Bmin;
                    
                    int resultR = (int) ((int) fator1R * fator2R);
                    int resultG = (int) ((int) fator1G * fator2G);
                    int resultB = (int) ((int) fator1B * fator2B);
                    
                    Color novaCor = new Color(resultR, resultG, resultB);
                    imagem.setRGB(coluna, linha, novaCor.getRGB());
                }
            }
            return imagem;
        }
        catch(Exception ex){
            System.out.println("Erro: "+ex);
            return new BufferedImage(0, 0, 0);
        }
    }
    
    public BufferedImage soma(File img, File img2){
        try{
            BufferedImage imagem = ImageIO.read(img);
            BufferedImage imagem2 = ImageIO.read(img2);
            
            int largura = imagem.getWidth();
            int altura = imagem.getHeight();
            
            int matrizr[][] = new int[largura][altura];
            int matrizg[][] = new int[largura][altura];
            int matrizb[][] = new int[largura][altura];
            
            for (int coluna = 0; coluna < largura; coluna = coluna + 1) {
                for (int linha = 0; linha < altura; linha = linha + 1) {
                    Color cor = new Color(imagem.getRGB(coluna, linha));
                    Color cor2 = new Color(imagem2.getRGB(coluna, linha));
                    
                    matrizr[coluna][linha] = cor.getRed() + cor2.getRed();
                    matrizg[coluna][linha] = cor.getGreen() + cor2.getGreen();
                    matrizb[coluna][linha] = cor.getBlue() + cor2.getBlue();
                }
            }
            
            int Rmin = 255;
            int Gmin = 255;
            int Bmin = 255;
            int Rmax = 0;
            int Gmax = 0;
            int Bmax = 0;
            
            for (int coluna = 0; coluna < largura; coluna = coluna + 1) {
                for (int linha = 0; linha < altura; linha = linha + 1) {
                    if(matrizr[coluna][linha] > Rmax){
                        Rmax = matrizr[coluna][linha];
                    }
                    if(matrizr[coluna][linha] < Rmin){
                        Rmin = matrizr[coluna][linha];
                    }
                    
                    if(matrizg[coluna][linha] > Gmax){
                        Gmax = matrizg[coluna][linha];
                    }
                    if(matrizg[coluna][linha] < Gmin){
                        Gmin = matrizg[coluna][linha];
                    }
                    
                    if(matrizb[coluna][linha] > Bmax){
                        Bmax = matrizb[coluna][linha];
                    }
                    if(matrizb[coluna][linha] < Bmin){
                        Bmin = matrizb[coluna][linha];
                    }
                }
            }

            for (int coluna = 0; coluna < largura; coluna = coluna + 1) {
                for (int linha = 0; linha < altura; linha = linha + 1) {
                    double passo1R = Rmax - Rmin;
                    double passo1G = Gmax - Gmin;
                    double passo1B = Bmax - Bmin;
                    
                    double fator1R = 255 / passo1R;
                    double fator1G = 255 / passo1G;
                    double fator1B = 255 / passo1B;
                    
                    
                    
                    double fator2R = matrizr[coluna][linha] - Rmin;
                    double fator2G = matrizg[coluna][linha] - Gmin;
                    double fator2B = matrizb[coluna][linha] - Bmin;
                    
                    int resultR = (int) (fator1R * fator2R);
                    int resultG = (int) (fator1G * fator2G);
                    int resultB = (int) (fator1B * fator2B);
                    
                    Color novaCor = new Color(resultR, resultG, resultB);
                    imagem.setRGB(coluna, linha, novaCor.getRGB());
                }
            }
            return imagem;
        }
        catch(Exception ex){
            System.out.println("Erro: "+ex);
            return new BufferedImage(0, 0, 0);
        }
    }
    
    public BufferedImage multi(File img, File img2){
        try{
            BufferedImage imagem = ImageIO.read(img);
            BufferedImage imagem2 = ImageIO.read(img2);
            
            int largura = imagem.getWidth();
            int altura = imagem.getHeight();
            
            int matrizr[][] = new int[largura][altura];
            int matrizg[][] = new int[largura][altura];
            int matrizb[][] = new int[largura][altura];
            
            for (int coluna = 0; coluna < largura; coluna = coluna + 1) {
                for (int linha = 0; linha < altura; linha = linha + 1) {
                    Color cor = new Color(imagem.getRGB(coluna, linha));
                    Color cor2 = new Color(imagem2.getRGB(coluna, linha));
                    
                    matrizr[coluna][linha] = cor.getRed() * cor2.getRed();
                    matrizg[coluna][linha] = cor.getGreen() * cor2.getGreen();
                    matrizb[coluna][linha] = cor.getBlue() * cor2.getBlue();
                }
            }
            
            int Rmin = 255;
            int Gmin = 255;
            int Bmin = 255;
            int Rmax = 0;
            int Gmax = 0;
            int Bmax = 0;
            
            for (int coluna = 0; coluna < largura; coluna = coluna + 1) {
                for (int linha = 0; linha < altura; linha = linha + 1) {
                    if(matrizr[coluna][linha] > Rmax){
                        Rmax = matrizr[coluna][linha];
                    }
                    if(matrizr[coluna][linha] < Rmin){
                        Rmin = matrizr[coluna][linha];
                    }
                    
                    if(matrizg[coluna][linha] > Gmax){
                        Gmax = matrizg[coluna][linha];
                    }
                    if(matrizg[coluna][linha] < Gmin){
                        Gmin = matrizg[coluna][linha];
                    }
                    
                    if(matrizb[coluna][linha] > Bmax){
                        Bmax = matrizb[coluna][linha];
                    }
                    if(matrizb[coluna][linha] < Bmin){
                        Bmin = matrizb[coluna][linha];
                    }
                }
            }

            for (int coluna = 0; coluna < largura; coluna = coluna + 1) {
                for (int linha = 0; linha < altura; linha = linha + 1) {
                    double passo1R = Rmax - Rmin;
                    double passo1G = Gmax - Gmin;
                    double passo1B = Bmax - Bmin;
                    
                    double fator1R = 255 / passo1R;
                    double fator1G = 255 / passo1G;
                    double fator1B = 255 / passo1B;
                    
                    
                    
                    double fator2R = matrizr[coluna][linha] - Rmin;
                    double fator2G = matrizg[coluna][linha] - Gmin;
                    double fator2B = matrizb[coluna][linha] - Bmin;
                    
                    int resultR = (int) (fator1R * fator2R);
                    int resultG = (int) (fator1G * fator2G);
                    int resultB = (int) (fator1B * fator2B);
                    
                    Color novaCor = new Color(resultR, resultG, resultB);
                    imagem.setRGB(coluna, linha, novaCor.getRGB());
                }
            }
            return imagem;
        }
        catch(Exception ex){
            System.out.println("Erro: "+ex);
            return new BufferedImage(0, 0, 0);
        }
    }
    
    public BufferedImage divide(File img, File img2){
        try{
            BufferedImage imagem = ImageIO.read(img);
            BufferedImage imagem2 = ImageIO.read(img2);
            
            int largura = imagem.getWidth();
            int altura = imagem.getHeight();
            
            for (int coluna = 0; coluna < largura; coluna = coluna + 1) {
                for (int linha = 0; linha < altura; linha = linha + 1) {
                    Color cor = new Color(imagem.getRGB(coluna, linha));
                    Color cor2 = new Color(imagem2.getRGB(coluna, linha));
                    
                    double R = cor.getRed();
                    double R2 = cor2.getRed();
                    int RF = 0;
                    if(R2 > 0){
                        RF = (int) (R/R2);
                    }
                    
                    double G = cor.getGreen();
                    double G2 = cor2.getGreen();
                    int GF = 0;
                    if(G2 > 0){
                        GF = (int) (G/G2);
                    }
                    
                    double B = cor.getBlue();
                    double B2 = cor2.getBlue();
                    int BF = 0;
                    if(B2 > 0){
                        BF = (int) (B/B2);
                    }
                    
                    Color novaCor = new Color(RF, GF, BF);
                    imagem.setRGB(coluna, linha, novaCor.getRGB());
                }
            }
            return imagem;
        }
        catch(Exception ex){
            System.out.println("Erro: "+ex);
            return new BufferedImage(0, 0, 0);
        }
    }
    
    private boolean positivo(Color cor){
        int blue = cor.getBlue();
        int red = cor.getRed();
        int green = cor.getGreen();
        int sobreposto = (blue + red + green) / 3;
        
        return sobreposto > 128;
    }
}
