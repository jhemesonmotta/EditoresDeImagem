/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package limiariza;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author renato
 */
public class Histograma {

    private BufferedImage imagem;

    public Histograma() {
    }

    public Histograma(BufferedImage imagem) {
        this.imagem = imagem;
    }

    public BufferedImage getImagem() {
        return imagem;
    }

    public void setImagem(BufferedImage imagem) {
        this.imagem = imagem;
    }

    public Rgb histogramaBasico(File img) {
        try {
            BufferedImage imagem = ImageIO.read(img); // lê a imagem... caso não encontre, gerará uma ex

            Rgb histograma = new Rgb();

            int largura = imagem.getWidth();
            int altura = imagem.getHeight();

            for (int coluna = 0; coluna < largura; coluna = coluna + 1) {
                for (int linha = 0; linha < altura; linha = linha + 1) {
                    Color cor = new Color(imagem.getRGB(coluna, linha));

                    int blue = cor.getBlue();
                    int red = cor.getRed();
                    int green = cor.getGreen();

                    histograma.r[red] += 1;
                    histograma.g[green] += 1;
                    histograma.b[blue] += 1;

                }
            }

            /**
             * Criando Gráfico
             */
            DefaultCategoryDataset hBasicoRGB = new DefaultCategoryDataset();
            
            for(int i=0;i<256;i++){
                hBasicoRGB.addValue(histograma.r[i],"Red",Integer.toString(i));
                hBasicoRGB.addValue(histograma.g[i],"Green",Integer.toString(i));
                hBasicoRGB.addValue(histograma.b[i],"Blue",Integer.toString(i));
            }

            JFreeChart hBrgb = ChartFactory.createAreaChart("Histograma Basico - Vermelho, Verde e Azul", "Basico", "Quantidade por cor", hBasicoRGB, PlotOrientation.VERTICAL, true, true, false);
            try {
                OutputStream graficoR = new FileOutputStream("graficos/Histograma-basico/histogramaBasico.jpg");
                ChartUtilities.writeChartAsJPEG(graficoR, hBrgb, 1366, 720);
                graficoR.close();
            } 
            catch (IOException io) {
                System.out.println("Erro: " + io.getMessage());
            }

            return histograma;
            
        } 
        catch (Exception ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }

    public Rgb histogramaNormalizado(File img) {
        try {
            BufferedImage imagem = ImageIO.read(img); // lê a imagem... caso não encontre, gerará uma ex

            Rgb histogramaBasico = new Histograma().histogramaBasico(img);
            Rgb histogramaNormalizado = new Rgb();

            int largura = imagem.getWidth();
            int altura = imagem.getHeight();
            double MxN = altura * largura;

            for(int i=0;i<256;i++){                
                histogramaNormalizado.r[i] = (histogramaBasico.r[i] / MxN);
                histogramaNormalizado.g[i] = (histogramaBasico.g[i] / MxN);
                histogramaNormalizado.b[i] = (histogramaBasico.b[i] / MxN);
            }
            
            /**
             * Criando Gráfico
             */
            DefaultCategoryDataset hBasicoRGB = new DefaultCategoryDataset();
            
            for(int i=0;i<256;i++){
                hBasicoRGB.addValue(histogramaNormalizado.r[i],"Red",Integer.toString(i));
                hBasicoRGB.addValue(histogramaNormalizado.g[i],"Green",Integer.toString(i));
                hBasicoRGB.addValue(histogramaNormalizado.b[i],"Blue",Integer.toString(i));
            }

            JFreeChart hBrgb = ChartFactory.createAreaChart("Histograma Normalizado - Vermelho, Verde e Azul", "Normalizado", "Quantidade por cor", hBasicoRGB, PlotOrientation.VERTICAL, true, true, false);
            try {
                OutputStream graficoR = new FileOutputStream("graficos/Histograma-normalizado/histogramaNormalizado.jpg");
                ChartUtilities.writeChartAsJPEG(graficoR, hBrgb, 1366, 720);
                graficoR.close();
            } 
            catch (IOException io) {
                System.out.println("Erro: " + io.getMessage());
            }
            
            return histogramaNormalizado;
        } 
        catch (Exception ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }

    public Rgb histogramaAcumulado(File img) {
        try {
            BufferedImage imagem = ImageIO.read(img); // lê a imagem... caso não encontre, gerará uma ex

            Rgb histogramaBasico = new Histograma().histogramaBasico(img);

            Rgb histogramaAcumulado = new Rgb();
            
            double auxR = 0;
            double auxG = 0;
            double auxB = 0;


            for(int i=0;i<256;i++){
                auxR = auxR + histogramaBasico.r[i];
                histogramaAcumulado.r[i] = auxR; 
                
                auxG = auxG + histogramaBasico.g[i];
                histogramaAcumulado.g[i] = auxG; 
                
                auxB = auxB + histogramaBasico.b[i];
                histogramaAcumulado.b[i] = auxB; 
            }
            
            /**
             * Criando Gráfico
             */
            DefaultCategoryDataset hBasicoRGB = new DefaultCategoryDataset();
            
            for(int i=0;i<256;i++){
                hBasicoRGB.addValue(histogramaAcumulado.r[i],"Red",Integer.toString(i));
                hBasicoRGB.addValue(histogramaAcumulado.g[i],"Green",Integer.toString(i));
                hBasicoRGB.addValue(histogramaAcumulado.b[i],"Blue",Integer.toString(i));
            }

            JFreeChart hBrgb = ChartFactory.createAreaChart("Histograma Acumulado - Vermelho, Verde e Azul", "Acumulado", "Quantidade por cor", hBasicoRGB, PlotOrientation.VERTICAL, true, true, false);
            try {
                OutputStream graficoR = new FileOutputStream("graficos/Histograma-acumulado/histogramaAcumulado.jpg");
                ChartUtilities.writeChartAsJPEG(graficoR, hBrgb, 1366, 720);
                graficoR.close();
            } 
            catch (IOException io) {
                System.out.println("Erro: " + io.getMessage());
            }

            return histogramaAcumulado;
        } 
        catch (Exception ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }

    public Rgb histogramaAcumuladoNormalizado(File img) {
        try {
            BufferedImage imagem = ImageIO.read(img); // lê a imagem... caso não encontre, gerará uma ex

            Rgb histogramaNormalizado = new Histograma().histogramaNormalizado(img);

            int largura = imagem.getWidth();
            int altura = imagem.getHeight();
            
            double auxR = 0;
            double auxG = 0;
            double auxB = 0;

            Rgb histogramaAcumuladoNormalizado = new Rgb();

            for(int i=0;i<256;i++){
                auxR = auxR + histogramaNormalizado.r[i];
                histogramaAcumuladoNormalizado.r[i] = auxR; 
                
                auxG = auxG + histogramaNormalizado.g[i];
                histogramaAcumuladoNormalizado.g[i] = auxG; 
                
                auxB = auxB + histogramaNormalizado.b[i];
                histogramaAcumuladoNormalizado.b[i] = auxB; 
            }

            /**
             * Criando Gráfico
             */
            DefaultCategoryDataset hBasicoRGB = new DefaultCategoryDataset();
            
            for(int i=0;i<256;i++){
                hBasicoRGB.addValue(histogramaAcumuladoNormalizado.r[i],"Red",Integer.toString(i));
                hBasicoRGB.addValue(histogramaAcumuladoNormalizado.g[i],"Green",Integer.toString(i));
                hBasicoRGB.addValue(histogramaAcumuladoNormalizado.b[i],"Blue",Integer.toString(i));
            }

            JFreeChart hBrgb = ChartFactory.createAreaChart("Histograma Acumulado Normalizado - Vermelho, Verde e Azul", "Normalizado e Acumulado", "Quantidade por cor", hBasicoRGB, PlotOrientation.VERTICAL, true, true, false);
            try {
                OutputStream graficoR = new FileOutputStream("graficos/Histograma-normalizado-acumulado/histogramaAcumuladoNormalizado.jpg");
                ChartUtilities.writeChartAsJPEG(graficoR, hBrgb, 1366, 720);
                graficoR.close();
            } 
            catch (IOException io) {
                System.out.println("Erro: " + io.getMessage());
            }
            
            return histogramaAcumuladoNormalizado;
        } 
        catch (Exception ex) {
            System.out.println("Erro: " + ex);
            return null;
        }
    }

    public BufferedImage equalizarHistograma(File img) {
        try {
            BufferedImage imagem = ImageIO.read(img);

            Rgb histogramaAcumuladoNormalizado = new Histograma().histogramaAcumuladoNormalizado(img);

            int largura = imagem.getWidth();
            int altura = imagem.getHeight();
            
            for (int coluna = 0; coluna < largura; coluna = coluna + 1) {
                for (int linha = 0; linha < altura; linha = linha + 1) {
                    Color cor = new Color(imagem.getRGB(coluna, linha));
                    int blue = cor.getBlue();
                    int red = cor.getRed();
                    int green = cor.getGreen();
                    
                    int hred = (int)(histogramaAcumuladoNormalizado.r[red]*255);
                    int hgreen = (int)(histogramaAcumuladoNormalizado.g[green]*255);
                    int hblue = (int)(histogramaAcumuladoNormalizado.b[blue]*255);
                    
                    Color novaCor = new Color(hred,hgreen,hblue);

                    imagem.setRGB(coluna, linha, novaCor.getRGB());
                }
            }
            
            return imagem;
            
        } catch (Exception ex) {
            System.out.println("Erro: " + ex);
            return new BufferedImage(0, 0, 0);
        }
    }
}
