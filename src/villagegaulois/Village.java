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

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
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
            return vendeur.getNom() + " vend " + produit;
        } else 
     {
        	
        	
        	
            return "Aucun étal est libre pour " + vendeur.getNom() + ".";
      }
    }

    public String rechercherVendeursProduit(String produit) {
        Etal[] etals = marche.trouverEtals(produit);

        if (etals.length > 0) {
            StringBuilder chaine = new StringBuilder("Ces vendeurs propose le produit " + produit + " :\n");

            
            for (int i = 0; i < etals.length; i++) {
            chaine.append(etals[i].getVendeur().getNom() + " est dans l'étali " + i + " \n");
            }

            return chaine.toString();
        } else {
            return "Le produit n'est pas disponible " + produit + ".";
        }
    }

    public Etal rechercherEtal(Gaulois vendeur) {
        return marche.trouverVendeur(vendeur);
    }public String partirVendeur(Gaulois vendeur) {
        Etal etal = marche.trouverVendeur(vendeur);

        if (etal != null) {
            etal.libererEtal();
            return vendeur.getNom() + "  est partie de son étal";
        } else {
            return vendeur.getNom() + " travaille plus dans son étal.";
        }
    }

    public String afficherMarche() {
        return marche.afficherMarche();
    }
	
}