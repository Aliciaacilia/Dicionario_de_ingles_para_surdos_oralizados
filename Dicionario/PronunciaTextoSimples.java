package Dicionario;

public class PronunciaTextoSimples implements PronunciacaoStrategy {

     public PronunciaTextoSimples() {
        
     }

        @Override
        public void pronunciar(String termo) {
        try {
            String pronuncia = OxfordAPI.buscarPronuncia(termo);
            if (pronuncia == null || pronuncia.isEmpty()) {
                System.out.println("Pronúncia não encontrada para " + termo);
            } else {
                System.out.println("Pronúncia Oxford de " + termo + ": " + pronuncia);
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar pronúncia Oxford para " + termo + ": " + e.getMessage());
        }
    }
}