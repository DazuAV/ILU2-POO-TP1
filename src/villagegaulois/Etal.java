package villagegaulois;

import personnages.Gaulois;

public class Etal {
	private Gaulois vendeur;
	private String produit;
	private int quantiteDebutMarche;
	private int quantite;
	private boolean etalOccupe = false;

	public boolean isEtalOccupe() {
		return etalOccupe;
	}

	public Gaulois getVendeur() {
		return vendeur;
	}

	public void occuperEtal(Gaulois vendeur, String produit, int quantite) {
		this.vendeur = vendeur;
		this.produit = produit;
		this.quantite = quantite;
		quantiteDebutMarche = quantite;
		etalOccupe = true;
	}

	public String libererEtal() {
	    if (!etalOccupe) {
	        return "L'étal n'a pas été occupé par un vendeur.";}
	    etalOccupe = false;
	    StringBuilder chaine = new StringBuilder("Le marchant" + vendeur.getNom());
	    int produitVendu = quantiteDebutMarche - quantite;
	    if (produitVendu >0) {
	        chaine.append("Vend " + produitVendu + " et aussi " + produit + "  \n");
	    } else {
	 chaine.append("rien");
	    }return chaine.toString();}


	public String afficherEtal() {
		if (etalOccupe) {
			return "L'étal de " + vendeur.getNom() + " est garni de " + quantite
					+ " " + produit + "\n";
		}
		return "L'étal est libre";
	}

	public String acheterProduit(int quantiteAcheter, Gaulois acheteur) {
		 if (quantiteAcheter < 1) {
		 throw new IllegalArgumentException("non positive");}
		
		if (acheteur == null) {
	        return ""; }
		if (!etalOccupe) {
	        return "";
	    }
		return "";
	}

	public boolean contientProduit(String produit) {
		return this.produit.equals(produit);
	}

}
