package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

  // Méthode pour imprimer l'état financier à partir d'une facture et d'une liste de pièces de théâtre
  public String print(Invoice invoice, Map<String, Play> plays) {
    // Utilisation de double pour les sommes
    double totalAmount = 0;
    int volumeCredits = 0;

    // Utilisation de StringBuffer pour concaténer les résultats
    StringBuffer result = new StringBuffer(String.format("Statement for %s\n", invoice.customer));

    // Formatage de la monnaie en dollars américains
    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    // Parcours des représentations dans la facture
    for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);
      double thisAmount = 0; // Utilisation de double pour le montant

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

      // Utilisation de StringBuffer pour concaténer la ligne résultat
      result.append(String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount), perf.audience));
      totalAmount += thisAmount;
    }

    // Utilisation de StringBuffer pour concaténer les lignes du résultat
    result.append(String.format("Amount owed is %s\n", frmt.format(totalAmount)));
    result.append(String.format("You earned %s credits\n", volumeCredits));

    // Conversion de StringBuffer en String
    return result.toString();
  }
}
