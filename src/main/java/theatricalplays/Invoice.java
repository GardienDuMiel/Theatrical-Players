// Package pour les représentations théâtrales
package theatricalplays;

// Importation de la liste
import java.util.List;

// Classe représentant une facture
public class Invoice {

  // Nom du client
  public String customer;
  
  // Liste des représentations associées à la facture
  public List<Performance> performances;

  // Constructeur de la classe Invoice
  public Invoice(String customer, List<Performance> performances) {
    this.customer = customer;
    this.performances = performances;
  }
}
