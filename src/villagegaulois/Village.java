package villagegaulois;

import personnages.Gaulois;
import personnages.Chef;


public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
    private Marche marche; // le marché

	
	public static class Marche {
        private Etal[] etals;

        public Marche(int nombreEtals) {
            etals = new Etal[nombreEtals];
            
            
            for (int i = 0; i < nombreEtals; i++) {
                etals[i] = new Etal();
            }
        }

        public Etal[] getEtals() {
            return etals;
        }
        
        public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
            if (indiceEtal >= 0 && indiceEtal < etals.length) {
                Etal etal = etals[indiceEtal];
                etal.occuperEtal(vendeur, produit, nbProduit);
            } else {
                System.out.println("Indice d'étal invalide.");
            }
        }
        
        public int trouverEtalLibre() {
            for (int i = 0; i < etals.length; i++) {
                if (!etals[i].isEtalOccupe()) {
                    return i; 
                }
            }
            return -1; 
        }
        
        public Etal[] trouverEtals(String produit) {
            int nombreEtalsVendantProduit = 0;     
            for (Etal etal : etals) {
                if (etal.contientProduit(produit)) {
                    nombreEtalsVendantProduit++;
                }
            }

            //Produit un tableau qui va stocker seulement étal qui vendent produit
            Etal[] etalsVendantProduit = new Etal[nombreEtalsVendantProduit];

            //Créer un tableau avec des étals qui vende les produits
            int index = 0;
            
            for (Etal etal : etals) {
                if (etal.contientProduit(produit)) {
                    etalsVendantProduit[index] = etal;
                    index++;
                }
            }

            return etalsVendantProduit;
        }
        
        public Etal trouverVendeur(Gaulois gaulois) {
            for (Etal etal : etals) {
                if (etal.isEtalOccupe() && etal.getVendeur() == gaulois) {
                    return etal; 
                }
            }
            return null; 
        }
        
        public String afficherMarche() {
            StringBuilder chaine = new StringBuilder();
            int nbEtalVide = 0;
            for (Etal etal : etals) {
                if (etal.isEtalOccupe()) {
                    chaine.append(etal.afficherEtal()).append("\n");
                } else {
                    nbEtalVide++;
                }
            }

            if ( 0<nbEtalVide ) {
                chaine.append("Il reste ").append(nbEtalVide).append(" étals non utilisés dans le marché.\n");
            }

            return chaine.toString();
        }

        
	
	}
	
	public class VillageSansChefException extends Exception {
        public VillageSansChefException(String message) {
            super(message);
        }
    }
	
	

	public Village(String nom, int nbVillageoisMaximum, int NbEtalsduMarche) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
        marche = new Marche(NbEtalsduMarche); 

	}

	
	
	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
		
		
		
		
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	 public String afficherVillageois() throws VillageSansChefException {
	        StringBuilder chaine = new StringBuilder();
	        if (chef == null) {
	            // Lancer l'exception si le chef est null
	            throw new VillageSansChefException("Le village ne peut exister sans chef.");
	        }

	        if (nbVillageois < 1) {
	            chaine.append("Il n'y a encore aucun habitant au village du chef "
	                    + chef.getNom() + ".\n");
	        } else {
	            chaine.append("Au village du chef " + chef.getNom()
	                    + " vivent les légendaires gaulois :\n");
	            for (int i = 0; i < nbVillageois; i++) {
	                chaine.append("- " + villageois[i].getNom() + "\n");
	            }
	        }
	        return chaine.toString();
	    }
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
	    int indiceEtal = marche.trouverEtalLibre();
	    
	    if (indiceEtal != -1) {
	        marche.utiliserEtal(indiceEtal, vendeur, produit, nbProduit);
	        return vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit
	                + ".\nLe vendeur " + vendeur.getNom() + " vend des " + produit
	                + " à l'étal n°" + (indiceEtal + 1) + ".\n";
	    } else {
	        return "Aucun étal est libre pour " + vendeur.getNom() + ". ";
	    }
	}

    

    public String rechercherVendeursProduit(String produit) {
        Etal[] etals = marche.trouverEtals(produit);

        if (etals.length > 0) {
            StringBuilder chaine = new StringBuilder("Seul le vendeur Bonemine propose des " + produit + "au marché   :\n");

            
            for (int i = 0; i < etals.length; i++) {
            chaine.append(etals[i].getVendeur().getNom() + " est dans l'étali " + i + " \n");
            }

            return chaine.toString();
        } else {
            return "Il n'y a pas de vendeur qui propose des " + produit + "au marché.";
        }
    }

    public Etal rechercherEtal(Gaulois vendeur) {
        return marche.trouverVendeur(vendeur);}
    
        public String partirVendeur(Gaulois vendeur) {
            Etal etal = marche.trouverVendeur(vendeur);

            if (etal != null && etal.isEtalOccupe()) {
                String nomProduit = etal.getProduit();
                int quantiteVendue = etal.getQuantiteDebutMarche() - etal.getQuantite();
                etal.libererEtal();

                StringBuilder message = new StringBuilder("Le vendeur " + vendeur.getNom() + " quitte son étal, il a vendu "
                        + quantiteVendue + " " + nomProduit + " parmi les " + etal.getQuantiteDebutMarche() + " qu'il voulait vendre.");
                return message.toString();
            } else {
                return vendeur.getNom() + " ne travaille plus dans son étal.";
            }
        }


    public String afficherMarche() {
        return marche.afficherMarche();
    }
	
}