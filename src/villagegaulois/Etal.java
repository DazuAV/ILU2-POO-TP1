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

	public String getProduit() {
        return produit;
    }

    public int getQuantiteDebutMarche() {
        return quantiteDebutMarche;
    }

    public int getQuantite() {
        return quantite;
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
	        throw new IllegalStateException("L'étal n'a pas été occupé par un vendeur.");
	    }

	    etalOccupe = false;
	    StringBuilder chaine = new StringBuilder("Le marchand " + vendeur.getNom() + " ");
	    int produitVendu = quantiteDebutMarche - quantite;

	    if (produitVendu > 0) {
	        chaine.append("vend " + produitVendu + " " + produit + ".\n");
	    } else {
	        chaine.append("ne vend rien.\n");
	    }

	    return chaine.toString();
	}



	public String afficherEtal() {
		if (etalOccupe) {
			return "L'étal de " + vendeur.getNom() + " est garni de " + quantite
					+ " " + produit + "\n";
		}
		return "L'étal est libre";
	}

	public String acheterProduit(int quantiteAcheter, Gaulois acheteur) {
	    try {
	        if (quantiteAcheter < 1) {
	            throw new IllegalArgumentException("La quantité à acheter doit être positive. \n");
	        }

	        if (acheteur == null) {
	            throw new NullPointerException("L'acheteur ne doit pas être null. \n");
	        }

	        if (!etalOccupe) {
	            throw new IllegalStateException("L'étal n'est pas occupé par un vendeur. \n");
	        }

	        int quantiteRestante = quantite - quantiteAcheter;
	        if (quantiteRestante >= 0) {
	            quantite = quantiteRestante;
	            StringBuilder message = new StringBuilder(acheteur.getNom() + " veut acheter " + quantiteAcheter
	                    + " " + produit + " à " + vendeur.getNom() + ". " + acheteur.getNom() + " est ravi de tout trouver sur l'étal de "
	                    + vendeur.getNom());
	            return message.toString();
	        } else {
	            int quantiteAchetable = quantite;
	            quantite = 0;
	            return acheteur.getNom() + " veut acheter " + quantiteAcheter + " " + produit + " à " + vendeur.getNom()
	                    + ", comme il n'y en a plus que " + quantiteAchetable + ", " + acheteur.getNom() + " vide l'étal de "
	                    + vendeur.getNom() + ". \n";
	        }
	    } catch (IllegalArgumentException e) {
	        System.err.println("Erreur : " + e.getMessage());
	        e.printStackTrace();
	        return "";
	    } catch (NullPointerException e) {
	        System.err.println("Erreur :  " + e.getMessage());
	        e.printStackTrace();
	        return "";
	    } catch (IllegalStateException e) {
	        System.err.println("Erreur : " + e.getMessage());
	        e.printStackTrace();
	        return "";
	    } catch (Exception e) {
	        System.err.println("Erreur :  " + e.getMessage());
	        e.printStackTrace();
	        return "";
	    }
	}





	public boolean contientProduit(String produitRecherche) {
	    if (this.produit == null || produitRecherche == null) {
	        return false;
	    }
	    return this.produit.equals(produitRecherche);
	}

	public static void main(String[] args) {
	    Etal etal = new Etal();
	    Gaulois acheteur = new Gaulois("AcheteurTest", 5);

	    // Test avec l'acheteur null
	    String resultat1 = etal.acheterProduit(10, null);
	    System.out.println(resultat1);  // Vous devriez voir "Acheteur invalide."

	    // Test avec une quantité négative
	    try {
	        String resultat2 = etal.acheterProduit(-5, acheteur);
	        System.out.println(resultat2);  // Cette ligne ne devrait pas être atteinte
	    } catch (IllegalArgumentException e) {
	        System.out.println("Exception attrapée : " + e.getMessage());
	    }

	    // Test avec un étal non occupé
	    etal.occuperEtal(new Gaulois("VendeurTest", 10), "ProduitTest", 20);
	    etal.libererEtal();  // Libérer l'étal
	    String resultat3 = etal.acheterProduit(5, acheteur);
	    System.out.println(resultat3);  // Vous devriez voir "L'étal n'est pas occupé par un vendeur."
	}

	
}
