package mike.businesscards.view;


import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import mike.businesscards.view.abstracts.AbstractITextPdfView;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Map;

class PdfBuilder extends AbstractITextPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document,
        PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String image = (String) model.get("image");
        byte[] decodedBytes = DatatypeConverter.parseBase64Binary(image.replaceAll("data:image/.+;base64,", ""));
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(decodedBytes));

        File imageFile = File.createTempFile("BusinessCardHolder", ".png");
        ImageIO.write(bufferedImage , "png", imageFile);
        bufferedImage.flush();
        String str = imageFile.getAbsolutePath();
        Image imagePdf = Image.getInstance(str);
        document.add(imagePdf);
    }
}
