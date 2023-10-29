// Package pour les représentations théâtrales
package theatricalplays;

// Importation des classes nécessaires
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

// Classe responsable de l'impression de l'état financier
public class StatementPrinter {

  // Méthode pour imprimer l'état financier à partir d'une facture et d'une liste de pièces de théâtre
  public String print(Invoice invoice, Map<String, Play> plays) {
    int totalAmount = 0; // Montant total de la facture
    int volumeCredits = 0; // Crédits de volume pour les spectateurs

    // Chaîne de résultat avec le nom du client
    String result = String.format("Statement for %s\n", invoice.customer);

    // Formatage de la monnaie en dollars américains
    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    // Parcours des représentations dans la facture
    for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);
      int thisAmount = 0;

      // Calcul du montant en fonction du type de la pièce de théâtre
      switch (play.type) {
        case "tragedy":
          thisAmount = 40000;
          if (perf.audience > 30) {
            thisAmount += 1000 * (perf.audience - 30);
          }
          break;
        case "comedy":
          thisAmount = 30000;
          if (perf.audience > 20) {
            thisAmount += 10000 + 500 * (perf.audience - 20);
          }
          thisAmount += 300 * perf.audience;
          break;
        default:
          throw new Error("unknown type: ${play.type}");
      }

      // Ajout des crédits de volume
      volumeCredits += Math.max(perf.audience - 30, 0);
      // Ajout de crédits supplémentaires pour chaque tranche de dix spectateurs pour les comédies
      if ("comedy".equals(play.type)) volumeCredits += Math.floor(perf.audience / 5);

      // Ajout d'une ligne pour cette représentation dans le résultat
      result += String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount / 100), perf.audience);
      totalAmount += thisAmount; // Mise à jour du montant total
    }
    result += String.format("Amount owed is %s\n", frmt.format(totalAmount / 100));
    result += String.format("You earned %s credits\n", volumeCredits);
    return result; // Renvoi de la chaîne résultat
  }

}
