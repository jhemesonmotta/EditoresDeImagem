/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package limiariza;

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
public class GraficoBasico {
    public static void main(String [] args){
        /**
             * Criando Gráfico
             */
            DefaultCategoryDataset hBasicoR = new DefaultCategoryDataset();
            DefaultCategoryDataset hBasicoG = new DefaultCategoryDataset();
            DefaultCategoryDataset hBasicoB = new DefaultCategoryDataset();
            
            for(int i=0;i<256;i++){
                hBasicoR.addValue(histograma.r[i],"0 - 255",Integer.toString(i));
                hBasicoG.addValue(histograma.g[i],"0 - 255",Integer.toString(i));
                hBasicoB.addValue(histograma.b[i],"0 - 255",Integer.toString(i));
            }

            JFreeChart hBr = ChartFactory.createBarChart("Histograma Básico - Vermelho", "Red", "cor", hBasicoR, PlotOrientation.VERTICAL, false, false, false);
            JFreeChart hBg = ChartFactory.createBarChart("Histograma Básico - Verde", "Green", "cor", hBasicoG, PlotOrientation.VERTICAL, false, false, false);
            JFreeChart hBb = ChartFactory.createBarChart("Histograma Básico - Azul", "Blue", "cor", hBasicoB, PlotOrientation.VERTICAL, false, false, false);

            try {
                System.out.println("Criando Grafico...");
                OutputStream graficoR = new FileOutputStream("histogramaBasicoRed.jpg");
                OutputStream graficoG = new FileOutputStream("histogramaBasicoGreen.jpg");
                OutputStream graficoB = new FileOutputStream("histogramaBasicoBlue.jpg");
                ChartUtilities.writeChartAsJPEG(graficoR, hBr, 1920, 1080);
                ChartUtilities.writeChartAsJPEG(graficoG, hBg, 1920, 1080);
                ChartUtilities.writeChartAsJPEG(graficoB, hBb, 1920, 1080);
                graficoR.close();
                graficoG.close();
                graficoB.close();
            } 
            catch (IOException io) {
                System.out.println("Erro: " + io.getMessage());
            }
            System.out.println("Grafico criado");

    }
}
