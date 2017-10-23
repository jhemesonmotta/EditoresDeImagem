package limiariza;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class Limiariza {
    public static void main(String[] args) throws IOException {
        File img = new File("img/img.jpg");
        File img2 = new File("img/china.jpg");
        File img3 = new File("img/inputFiltragem.jpg");
        File img4 = new File("img/baixocontraste.jpg");
        BufferedImage imagem = ImageIO.read(img3);
//OK        ImageIO.write(new Operacoes().and(new Limiarizacao().limiariza(img), new Limiarizacao().limiariza(img2)), "jpg", new File("img/and.jpg"));
//OK        ImageIO.write(new Operacoes().or(new Limiarizacao().limiariza(img), new Limiarizacao().limiariza(img2)), "jpg", new File("img/or.jpg"));
//OK        ImageIO.write(new Operacoes().soma(img, img2), "jpg", new File("img/som.jpg"));
//OK        ImageIO.write(new Operacoes().multi(img, img2), "jpg", new File("img/multi.jpg"));
//OK        ImageIO.write(new Operacoes().divide(img, img2), "jpg", new File("img/div.jpg"));
//OK        ImageIO.write(new Operacoes().sub(img, img2), "jpg", new File("img/sub.jpg"));
//OK        ImageIO.write(new Limiarizacao().limiariza(img), "jpg", new File("img/limiariza.jpg"));
//OK        ImageIO.write(new Negativo().negativar(img), "jpg", new File("img/nega.jpg"));
//OK        ImageIO.write(new FiltragemEspacial().realceDiagonal(img, 1, 1, 5), "jpg", new File("img/realce.jpg"));
//OK        ImageIO.write(new FiltragemEspacial().suavizar(imagem, 1, 1, 5), "jpg", new File("img/filtragemOutput.jpg"));
//OK        ImageIO.write(new Histograma().equalizarHistograma(img4), "jpg", new File("img/equalizada.jpg"));
    }
}
