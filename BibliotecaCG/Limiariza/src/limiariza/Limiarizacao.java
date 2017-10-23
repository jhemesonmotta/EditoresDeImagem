package limiariza;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Limiarizacao {
    private BufferedImage imagem;

    public Limiarizacao() {
    }

    public Limiarizacao(BufferedImage imagem) {
        this.imagem = imagem;
    }

    public BufferedImage getImagem() {
        return imagem;
    }

    public void setImagem(BufferedImage imagem) {
        this.imagem = imagem;
    }
    
    public BufferedImage limiariza(File img){
        try{
            BufferedImage imagem = ImageIO.read(img); // lê a imagem... caso não encontre, gerará uma ex
            
            int largura = imagem.getWidth();
            int altura = imagem.getHeight();
            
//            Color meuBranco = new Color(255, 255, 255); // Cor branca
//            int rgb = meuBranco.getRGB();
//            System.out.print(rgb); // esse é um bom macete pra descobrir como pôr uma cor


            for (int coluna = 0; coluna < largura; coluna = coluna + 1) {
                for (int linha = 0; linha < altura; linha = linha + 1) {
                      Color cor = new Color(imagem.getRGB(coluna, linha));
                      // o motivo de não pegar direto: cor.getRGB é que com esse método ele retorna números totalmente fora de lógica 
                      //(ex.: -116545987465478)
                      
                      int blue = cor.getBlue();
                      int red = cor.getRed();
                      int green = cor.getGreen();
                      // aqui, eu "sobreponho" as três placas de cores
                      
                      int sobreposto = (blue + red + green) / 3;
                      
                      // antes de processar, seria legal pôr em tons de cinza...
                      // == pixel atual = sobreposto
                      // no caso, eu mesmo defini que 128 seria meu ponto de limiarização (o mesmo do algoritmo de Otsu)
                      if(sobreposto > 128){
                          // a intenção desse 255 é que ficasse branco... mas deu errado
                          // obs: 256 fica preto (provavelmete por ser uma cor inválida)
                          imagem.setRGB(coluna, linha, -1);
                          // -1 é o código de branco nesse formato
                      }
                      else{
                          imagem.setRGB(coluna, linha, 0);
                      }
                }
            }
            return imagem;
//            ImageIO.write(imagem, "jpg", new File("imagem.jpg"));
        }
        catch(Exception ex){
            System.out.print("Erro: " + ex);
            return new BufferedImage(0, 0, 0);
        }
    }
    
    public static void montagem(File img1, File img2, File img3){
        try{
            BufferedImage imagem = ImageIO.read(img1); // lê a imagem... caso não encontre, gerará uma ex
            BufferedImage imagem2 = ImageIO.read(img2); // lê a imagem... caso não encontre, gerará uma ex
            BufferedImage imagem3 = ImageIO.read(img3); // lê a imagem... caso não encontre, gerará uma ex
            
            int largura = imagem.getWidth();
            int altura = imagem.getHeight();
            
            for (int coluna = 0; coluna < largura; coluna = coluna + 1) {
                for (int linha = 0; linha < altura; linha = linha + 1) {
                      Color cor = new Color(imagem.getRGB(coluna, linha));                      
                      Color cor2 = new Color(imagem2.getRGB(coluna, linha));     
                      Color cor3 = new Color(imagem3.getRGB(coluna, linha));     
                      
                      int blue = cor.getBlue();
                      int red = cor.getRed();
                      int green = cor.getGreen();
                      
                      int blue2 = cor2.getBlue();
                      int red2 = cor2.getRed();
                      int green2 = cor2.getGreen();
                      
                      int blue3 = cor3.getBlue();
                      int red3 = cor3.getRed();
                      int green3 = cor3.getGreen();
                      
                      int sobreposto = (blue + red + green) / 3;
                      
                      Color novo = new Color(red2, green2, blue2);
                      Color novo2 = new Color(red3, green3, blue3);
                      
                      if(sobreposto > 1){
                          imagem.setRGB(coluna, linha, novo2.getRGB());
                      }
                      else{
                          imagem.setRGB(coluna, linha, novo.getRGB());
                      }
                }
                ImageIO.write(imagem, "jpg", new File("saida.jpg"));
            }
        }
        catch(Exception ex){
            System.out.print("Erro: " + ex);
        }
    }
}
