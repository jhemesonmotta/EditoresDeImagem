package limiariza;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Negativo {
    private BufferedImage imagem;

    public Negativo() {
    }

    public BufferedImage getImagem() {
        return imagem;
    }

    public void setImagem(BufferedImage imagem) {
        this.imagem = imagem;
    }
    
    public BufferedImage negativar(File img){
        try{
            BufferedImage imagem = ImageIO.read(img); // lê a imagem... caso não encontre, gerará uma ex
            
            int largura = imagem.getWidth();
            int altura = imagem.getHeight();
            
            for (int coluna = 0; coluna < largura; coluna = coluna + 1) {
                for (int linha = 0; linha < altura; linha = linha + 1) {
                      Color cor = new Color(imagem.getRGB(coluna, linha));
                      // o motivo de não pegar direto: cor.getRGB é que com esse método ele retorna números totalmente fora de lógica 
                      //(ex.: -116545987465478)
                      
                      int blue = cor.getBlue();
                      int red = cor.getRed();
                      int green = cor.getGreen();
                      
                      int blue2 = 255 - blue;
                      int red2 = 255 - red;
                      int green2 = 255 - green;
                      
                      Color novaCor = new Color(red2, green2, blue2);
                      
                      int sobreposto = (blue + red + green) / 3;
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
}
