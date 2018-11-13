package controle;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Sacola;
import util.Datas;

/**
 *
 * @author William Rodrigues
 */
public class TermoDeCiencia {

    SacolaControle controleSacola = new SacolaControle();

    private final Sacola sacola;
    private final String nomeCOnsultora;
    private final String cpfConsultora;
    private final Integer quantidadeProdutos;
    private final String dataAtual;
    private final Double valorTotal;
    private final String dataAcerto;

    public TermoDeCiencia(Sacola sacola) {
        this.sacola = sacola;
        nomeCOnsultora = sacola.getConsultora().getNome();
        cpfConsultora = sacola.getConsultora().getCpf();
        quantidadeProdutos = controleSacola.calculaQuantidadeProdutosSacola(sacola);
        dataAtual = Datas.dateToString(sacola.getDataCriacao());
        valorTotal = controleSacola.calculaValorTotalSacola(sacola);
        dataAcerto = Datas.dateToString(sacola.getDataAcerto());
    }

    public void gerar() {
        Document doc = new Document();
        String arquivoPdf = "Termo_de_Ciencia.pdf";

        try {

            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf));
            doc.open();

            Font fonteTitulo = new Font(FontFamily.TIMES_ROMAN, 20);

            Paragraph paragrafo = new Paragraph("Ateliê Doce Beleza", fonteTitulo);
            paragrafo.setAlignment(1);
            doc.add(paragrafo);

            paragrafo = new Paragraph("\n");
            doc.add(paragrafo);

            paragrafo = new Paragraph("Termo de Ciência", fonteTitulo);

            paragrafo.setAlignment(1);
            doc.add(paragrafo);

            paragrafo = new Paragraph("\n");
            doc.add(paragrafo);

            String texto = "Eu, " + nomeCOnsultora + ", portador(a) do CPF de número " + cpfConsultora
                    + ", consultor(a) do Ateliê Doce Beleza, declaro estar ciente da responsabilidade de realizar vendas dos "
                    + quantidadeProdutos + " produtos pegos de forma consignada na data de "
                    + dataAtual + ", que compreendem um valor total de R$ " + valorTotal
                    + " e realizar o acerto e/ou devolução dos mesmos até a data de " + dataAcerto + ".";

            paragrafo = new Paragraph(texto);
            doc.add(paragrafo);

            paragrafo = new Paragraph("\n");
            doc.add(paragrafo);

            paragrafo = new Paragraph("Assinatura do(a) consultor(a):  _____________________________________________________");
            doc.add(paragrafo);

            paragrafo = new Paragraph("\n");
            doc.add(paragrafo);

            paragrafo = new Paragraph("Maringá, " + dataAtual + ".");
            paragrafo.setAlignment(2);
            doc.add(paragrafo);

            doc.close();
            Desktop.getDesktop().open(new File(arquivoPdf));

        } catch (FileNotFoundException | DocumentException ex) {
            System.err.print("Erro: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.print("Erro: " + ex.getMessage());
        }
    }

}
