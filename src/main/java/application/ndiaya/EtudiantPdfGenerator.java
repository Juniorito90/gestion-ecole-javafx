package application.ndiaya;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.List;

public class EtudiantPdfGenerator {

    public static void generatePdf(List<Etudiant> etudiants, String outputPath) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();

            // Générer le contenu du PDF avec les informations des étudiants
            for (Etudiant etudiant : etudiants) {
                Paragraph paragraph = new Paragraph("ID : " + etudiant.getId() +
                        "\nNom : " + etudiant.getNom() +
                        "\nPrénom : " + etudiant.getPrenom() +
                        "\nAdresse : " + etudiant.getAdresse());


// Vérifier si la classe de l'étudiant est null
                if (etudiant.getClasse() != null) {
                    // Ajouter le libellé de la classe dans le paragraphe
                    paragraph.add("\nClasse : " + etudiant.getClasse().getLibelle());
                }

// Ajouter un saut de ligne après chaque paragraphe
                paragraph.add("\n\n");
                document.add(paragraph);
            }

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
