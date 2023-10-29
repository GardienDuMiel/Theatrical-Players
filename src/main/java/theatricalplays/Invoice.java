package theatricalplays;

import java.util.List;
import java.util.Map;  // Ajout de l'importation nécessaire pour la classe Map

// Classe représentant une facture
public class Invoice {

  public String customer;
  public List<Performance> performances;

  // Constructeur de la classe Invoice
  public Invoice(String customer, List<Performance> performances) {
    this.customer = customer;
    this.performances = performances;
  }

  // Méthode pour générer la facture au format HTML
  public String toHTML(Map<String, Play> plays) {
    HtmlStatementPrinter htmlStatementPrinter = new HtmlStatementPrinter();
    return htmlStatementPrinter.generateHtml(this, plays);
  }

  // Méthode pour générer la facture au format texte
  public String toText(Map<String, Play> plays) {
    StatementPrinter statementPrinter = new StatementPrinter();
    return statementPrinter.print(this, plays);
  }
}
