package edu.bsu.cs222.dndcharactergenerator;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class PdfTest {
    // personal api reference

    //RandomAccessFile (stream type to put PDF file into)
    //PDFParser (used to get PDDocument from PDF file)
    //PDDocument (in memory pdf document)
    //PDDocument.getDocument() (gets low level document, whatever that means)
    //COSDocument (low level used for copying a document)

    @Test
    public void testReadPdf() {
        try {
            // todo: find fix for pathing
            File pdfFile = new File("C:\\dev\\cs222\\" +
                    "fp-jwright-sanaugler-cthomas-jkennedy\\src\\" +
                    "test\\resources\\" +
                    "blank_character.pdf");
            RandomAccessFile randomAccessPdf = new RandomAccessFile(pdfFile, "r");
            PDFParser pdfParser = new PDFParser(randomAccessPdf);
            pdfParser.parse();
            PDDocument document = pdfParser.getPDDocument();
            Assertions.assertNotNull(document);

        }
        catch( IOException e) {
            e.printStackTrace();
        }
        // ideas for refactoring
            // Class PdfFactory takes in a string
            // methods for editing each part of the pdf (stats etc)
            // single method that writes new character guide
    }
}
