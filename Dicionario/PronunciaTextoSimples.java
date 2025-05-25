package Dicionario;

import java.util.HashMap;
import java.util.Map;

public class PronunciaTextoSimples implements PronunciacaoStrategy {
    private static final Map<String, String> PRONUNCIAS_CONHECIDAS = new HashMap<>();
    
    static {
        
        PRONUNCIAS_CONHECIDAS.put("solid", "Sô-li-di");
        PRONUNCIAS_CONHECIDAS.put("factory", "Fác-to-ri");
        PRONUNCIAS_CONHECIDAS.put("strategy", "Es-tra-té-gi");
        PRONUNCIAS_CONHECIDAS.put("pattern", "Pá-tern");
        PRONUNCIAS_CONHECIDAS.put("interface", "In-ter-fei-si");
        PRONUNCIAS_CONHECIDAS.put("abstract", "Abs-tra-ti");
        PRONUNCIAS_CONHECIDAS.put("polymorphism", "Po-li-mor-fis-mo");
        PRONUNCIAS_CONHECIDAS.put("encapsulation", "En-cap-su-la-ção");
        PRONUNCIAS_CONHECIDAS.put("inheritance", "He-ran-ça");
        PRONUNCIAS_CONHECIDAS.put("java", "Já-va");
        PRONUNCIAS_CONHECIDAS.put("python", "Pai-ton");
        PRONUNCIAS_CONHECIDAS.put("javascript", "Já-vas-cript");
        PRONUNCIAS_CONHECIDAS.put("framework", "Freim-uórk");
        PRONUNCIAS_CONHECIDAS.put("database", "Dei-ta-bei-si");
        PRONUNCIAS_CONHECIDAS.put("algorithm", "Al-go-rit-mo");
    }
    
    @Override
    public void pronunciar(String termo) {
        String termoLower = termo.toLowerCase();
        String pronuncia = PRONUNCIAS_CONHECIDAS.get(termoLower);
        
        if (pronuncia != null) {
            System.out.println(" Pronúncia de '" + termo + "': " + pronuncia);
        } else {
            
            String pronunciaSilabica = gerarPronunciaSilabica(termo);
            System.out.println(" Pronúncia de '" + termo + "': " + pronunciaSilabica);
        }
        
        
        adicionarDicaPronuncia(termoLower);
    }
    
    private String gerarPronunciaSilabica(String termo) {
        
        StringBuilder resultado = new StringBuilder();
        String termoLower = termo.toLowerCase();
        
        for (int i = 0; i < termoLower.length(); i++) {
            char c = termoLower.charAt(i);
            
            if (i == 0 || isVogal(termoLower.charAt(i-1))) {
                resultado.append(Character.toUpperCase(c));
            } else {
                resultado.append(c);
            }
            
            
            if (i < termoLower.length() - 1 && 
                isVogal(c) && !isVogal(termoLower.charAt(i+1))) {
                resultado.append("-");
            }
        }
        
        return resultado.toString();
    }
    
    private boolean isVogal(char c) {
        return "aeiouAEIOU".indexOf(c) != -1;
    }
    
    private void adicionarDicaPronuncia(String termo) {
        switch (termo) {
            case "solid" -> System.out.println(" Dica: Acrônimo em inglês, pronuncie cada letra separadamente ou como palavra");
            case "factory" -> System.out.println(" Dica: Palavra inglesa, ênfase na primeira sílaba");
            case "strategy" -> System.out.println(" Dica: Palavra inglesa, ênfase na primeira sílaba");
            case "pattern" -> System.out.println(" Dica: Palavra inglesa, ênfase na primeira sílaba");
        }
    }
}
