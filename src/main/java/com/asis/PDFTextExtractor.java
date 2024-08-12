package com.asis;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.asis.util.BaseClass;

import java.io.File;
import java.io.IOException;

public class PDFTextExtractor extends BaseClass{
    public static void main(String[] args) {
        // Specify the PDF file path
        String pdfFilePath = FILE1;

        try {
            // Load the PDF document
            PDDocument document = PDDocument.load(new File(pdfFilePath));
            
            // Instantiate PDFTextStripper class
            PDFTextStripper pdfStripper = new PDFTextStripper();

            // Extract text from PDF
            String text = pdfStripper.getText(document);

            // Print the extracted text
            System.out.println(text);

            // Close the document
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

