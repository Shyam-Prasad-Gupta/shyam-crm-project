package com.luv2code.springdemo.config;

import java.util.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.lang.*;
import java.net.MalformedURLException;
import java.io.*;

class Practive {
	public static void main(String[] args) {

		Document document = new Document();

		try {
			File file = new File("helloWorld.pdf");
			PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file.getPath()));
			// we can write file attribute before document is open
			// Set attributes here
			document.addAuthor("Shyam Gupta");
			document.addCreationDate();
			document.addCreator("spg.com");
			document.addTitle("Set Attribute Example");
			document.addSubject("An example to show how attributes can be added to pdf files.");
			////////////////
			document.open();
			// adding paragraph
			document.add(new Paragraph("A hello world PDF document"));

			// adding image from file system
			Image img1 = Image.getInstance("C:\\Users\\shyam\\Desktop\\testing.jpg");
			img1.setAbsolutePosition(100f, 150f);
			img1.scaleAbsolute(100, 100);
			document.add(img1);
			// adding image from the url
			Image img2 = Image.getInstance("http://www.eclipse.org/xtend/images/java8_logo.png");
			document.add(img2);

			document.close();
			pdfWriter.close();
		} catch (DocumentException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
