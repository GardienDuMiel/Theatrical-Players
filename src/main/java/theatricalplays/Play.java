package theatricalplays;

// Classe représentant une pièce de théâtre
public class Play {

  public String name;
  public PlayType type;  // Utilisation de l'énumération PlayType

  // Constructeur de la classe Play
  public Play(String name, PlayType type) {
    this.name = name;
    this.type = type;
  }
}
