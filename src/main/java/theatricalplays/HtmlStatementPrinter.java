package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

// Classe pour générer la facture au format HTML
public class HtmlStatementPrinter {

  // Méthode pour générer la facture au format HTML
  public String generateHtml(Invoice invoice, Map<String, Play> plays) {
    double totalAmount = 0;
    int volumeCredits = 0;

    // Utilisation de StringBuilder pour concaténer les résultats HTML
    StringBuilder result = new StringBuilder("<html><body>\n");

    // Ajout du titre de la facture
    result.append(String.format("<h1>Statement for %s</h1>\n", invoice.customer));

    // Formatage de la monnaie en dollars américains
    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    // Parcours des représentations dans la facture
    for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);
      double thisAmount = 0;

      // Calcul du montant en fonction du type de la pièce de théâtre
      switch (play.type) {
        case "tragedy":
          thisAmount = 400.0;
          if (perf.audience > 30) {
            thisAmount += 10.0 * (perf.audience - 30);
          }
          break;
        case "comedy":
          thisAmount = 300.0;
          if (perf.audience > 20) {
            thisAmount += 100.0 + 5.0 * (perf.audience - 20);
          }
          thisAmount += 3.0 * perf.audience;
          break;
        default:
          throw new Error("unknown type: ${play.type}");
      }

      // Ajout des crédits de volume
      volumeCredits += Math.max(perf.audience - 30, 0);
      // Ajout de crédits supplémentaires pour chaque tranche de dix spectateurs pour les comédies
      if ("comedy".equals(play.type)) volumeCredits += Math.floor(perf.audience / 5);

      // Utilisation de StringBuilder pour concaténer la ligne résultat HTML
      result.append(String.format("<p>%s: %s (%s seats)</p>\n", play.name, frmt.format(thisAmount), perf.audience));
      totalAmount += thisAmount;
    }

    // Utilisation de StringBuilder pour concaténer les lignes du résultat HTML
    result.append(String.format("<p>Amount owed is %s</p>\n", frmt.format(totalAmount)));
    result.append(String.format("<p>You earned %s credits</p>\n", volumeCredits));
    result.append("</body></html>");

    // Conversion de StringBuilder en String
    return result.toString();
  }
}
