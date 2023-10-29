package theatricalplays;

// Classe représentant une pièce de théâtre
public class Play {

  // Types de pièces de théâtre en tant que variables statiques
  public static final String TRAGEDY = "tragedy";
  public static final String COMEDY = "comedy";
  public static final String HISTORY = "history";
  public static final String PASTORAL = "pastoral";

  // Nom de la pièce de théâtre
  public String name;
  
  // Type de la pièce de théâtre
  public String type;

  // Constructeur de la classe Play avec contrôle sur le type de pièce
  public Play(String name, String type) {
    // Vérification du type de pièce lors de la création de l'objet
    if (!type.equals(TRAGEDY) && !type.equals(COMEDY) && !type.equals(HISTORY) && !type.equals(PASTORAL)) {
      throw new IllegalArgumentException("Invalid play type");
    }

    this.name = name;
    this.type = type;
  }
}
