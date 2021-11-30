package ovh.devnote.ksiegarnia.document;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ovh.devnote.ksiegarnia.entity.Koszyk;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
public class PdfCreator {

    public static final String dest = "D:\\DOKUMENTY\\Spring\\src\\main\\java\\ovh\\devnote\\ksiegarnia\\PDF/faktura.pdf";
    Random rand = new Random();
    int x = rand.nextInt(10);
    int y;
    String code = "";



    public void pdf(List<Koszyk> koszyk) throws DocumentException, IOException {


        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        for(int i =0; i<14; i++)
        {
            y = rand.nextInt(10);
            code = code + y;
        }


        BaseFont helvetica;


        helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);

        com.itextpdf.text.Font polskieFonty=new com.itextpdf.text.Font(helvetica,10);
        Font helvetica16=new Font(helvetica,10);
        Font cellFont = new Font(helvetica, 12, Font.NORMAL);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/HH/mm");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        Rectangle small = new Rectangle(500,800);
        Font smallfont = new Font(Font.FontFamily.HELVETICA, 10);
        Document document = new Document(small, 5, 5, 5, 5);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Paragraph preface = new Paragraph("Faktura NR" + dtf.format(now) + x );
        preface.setAlignment(Element.ALIGN_CENTER);
        document.add(preface);
        PdfPTable table = new PdfPTable(2);

        table.setTotalWidth(new float[]{ 300, 120 });
        table.setLockedWidth(true);
        PdfContentByte cb = writer.getDirectContent();
        // first row
        PdfPCell cell = new PdfPCell(new Phrase("Dane sprzedającego: \n Księgarnia Euforia \n Bolesława Chrobrego 10 \n 50-254 Warszawa \n NIP: 0000 00 \n numer konta: \n 48965132478954", polskieFonty));
        cell.setFixedHeight(75);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table.addCell(cell);
        // second row
        cell = new PdfPCell(new Phrase("Dane kupującego: \n "+username, polskieFonty));
        cell.setFixedHeight(60);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);


        Barcode128 code128 = new Barcode128();
        code128.setCode(code);
        code128.setCodeType(Barcode128.CODE128);
        Image code128Image = code128.createImageWithBarcode(cb, null, null);
        cell = new PdfPCell(code128Image, true);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setFixedHeight(30);
        table.addCell(cell);
        // third row

        cell = new PdfPCell(new Phrase("Data wystawienia: \n" + dtf2.format(now), smallfont));
        cell.setFixedHeight(30);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);

        document.add(table);

        float [] pointColumnWidths = {150F, 150F, 150F, 150F};
        PdfPTable table2 = new PdfPTable(pointColumnWidths);

        // Adding cells to the table
        table2.addCell(new PdfPCell(new Phrase("Nazwa")));
        table2.addCell(new PdfPCell(new Phrase("Cena (zł)")));
        table2.addCell(new PdfPCell(new Phrase("Gatunek")));
        table2.addCell(new PdfPCell(new Phrase("ilosc (szt)")));

        for (Koszyk kosz: koszyk) {

            table2.addCell(new PdfPCell(new Phrase(kosz.getNazwa())));
            table2.addCell(new PdfPCell(new Phrase(Float.toString(kosz.getCena()))));
            table2.addCell(new PdfPCell(new Phrase(kosz.getKategoria())));
            table2.addCell(new PdfPCell(new Phrase("Jakas ilosc")));
        }

        double cenaRazem=0;
        for (Koszyk kosz: koszyk) {
            cenaRazem = cenaRazem + kosz.getCena();
        }
        System.out.println("TEST CENY "+  cenaRazem );
        table2.addCell(new PdfPCell(new Phrase("")));
        table2.addCell(new PdfPCell(new Phrase("Razem: " + (float) cenaRazem)));
        table2.addCell(new PdfPCell(new Phrase("")));
        table2.addCell(new PdfPCell(new Phrase("")));







        document.add(table2);
        document.close();

    }
}
