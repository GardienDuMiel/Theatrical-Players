// Package pour les représentations théâtrales
package theatricalplays;

// Classe représentant une représentation théâtrale
public class Performance {

  // Identifiant de la pièce de théâtre
  public String playID;
  
  // Nombre de spectateurs présents à la représentation
  public int audience;

  // Constructeur de la classe Performance
  public Performance(String playID, int audience) {
    this.playID = playID;
    this.audience = audience;
  }
}
