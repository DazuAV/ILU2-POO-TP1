package histoire;

import villagegaulois.Etal;

public class ScenarioCasDegrade {
    public static void main(String[] args) {
        Etal etal = new Etal();
        try {
      etal.acheterProduit(-45, null); 
        } catch (IllegalArgumentException e) {
          System.err.println("return 1 " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }}
}
