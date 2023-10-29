// Package pour les représentations théâtrales
package theatricalplays;

// Importation des classes nécessaires pour les tests
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

// Importation des outils d'approbation des résultats de tests
import static org.approvaltests.Approvals.verify;

// Classe de tests pour la classe StatementPrinter
public class StatementPrinterTests {

    // Test pour un exemple d'état financier approuvé
    @Test
    void exampleStatement() {
        // Création d'une liste de pièces de théâtre
        Map<String, Play> plays = Map.of(
                "hamlet",  new Play("Hamlet", "tragedy"),
                "as-like", new Play("As You Like It", "comedy"),
                "othello", new Play("Othello", "tragedy"));

        // Création d'une facture avec des représentations
        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        // Création d'une instance de StatementPrinter
        StatementPrinter statementPrinter = new StatementPrinter();

        // Appel de la méthode print avec la facture et la liste de pièces de théâtre, et vérification du résultat
        var result = statementPrinter.print(invoice, plays);

        // Approbation du résultat
        verify(result);
    }

    // Test pour un état financier avec de nouveaux types de pièces de théâtre non pris en charge
    @Test
    void statementWithNewPlayTypes() {
        // Création d'une liste de pièces de théâtre avec de nouveaux types
        Map<String, Play> plays = Map.of(
                "henry-v",  new Play("Henry V", "history"),
                "as-like", new Play("As You Like It", "pastoral"));

        // Création d'une facture avec des représentations de pièces de théâtre non prises en charge
        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("henry-v", 53),
                new Performance("as-like", 55)));

        // Création d'une instance de StatementPrinter
        StatementPrinter statementPrinter = new StatementPrinter();

        // Vérification qu'une erreur est levée lors de l'impression de l'état financier
        Assertions.assertThrows(Error.class, () -> {
            statementPrinter.print(invoice, plays);
        });
    }

     // Test d'acceptation pour le format HTML
     @Test
     void htmlStatement() {
         Map<String, Play> plays = Map.of(
                 "hamlet",  new Play("Hamlet", "tragedy"),
                 "as-like", new Play("As You Like It", "comedy"),
                 "othello", new Play("Othello", "tragedy"));
 
         Invoice invoice = new Invoice("BigCo", List.of(
                 new Performance("hamlet", 55),
                 new Performance("as-like", 35),
                 new Performance("othello", 40)));
 
         // Génération de la facture au format HTML
         String htmlResult = invoice.toHTML(plays);
 
         // Approbation du résultat HTML
         verify(htmlResult);
     }
}
