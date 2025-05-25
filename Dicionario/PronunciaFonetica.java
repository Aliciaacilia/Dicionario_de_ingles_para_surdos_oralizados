package Dicionario;

import java.util.HashMap;
import java.util.Map;

public class PronunciaFonetica implements PronunciacaoStrategy {
    private static final Map<String, String> MAPEAMENTO_FONETICO = new HashMap<>();

    static {
        MAPEAMENTO_FONETICO.put("a", "ɐ");
        MAPEAMENTO_FONETICO.put("e", "ɛ");
        MAPEAMENTO_FONETICO.put("i", "i");
        MAPEAMENTO_FONETICO.put("o", "ɔ");
        MAPEAMENTO_FONETICO.put("u", "u");

        MAPEAMENTO_FONETICO.put("ch", "ʃ");
        MAPEAMENTO_FONETICO.put("lh", "ʎ");
        MAPEAMENTO_FONETICO.put("nh", "ɲ");
        MAPEAMENTO_FONETICO.put("rr", "ʁ");
        MAPEAMENTO_FONETICO.put("ss", "s");

        MAPEAMENTO_FONETICO.put("solid", "ˈsɔ.li.dʒi");
        MAPEAMENTO_FONETICO.put("factory", "ˈfæk.tə.ri");
        MAPEAMENTO_FONETICO.put("strategy", "ˈstræt.ə.dʒi");
        MAPEAMENTO_FONETICO.put("pattern", "ˈpæt.ərn");
    }

    @Override
    public void pronunciar(String termo) {
        String termoLower = termo.toLowerCase();
        String pronunciaFonetica = MAPEAMENTO_FONETICO.get(termoLower);

        if (pronunciaFonetica != null) {
            System.out.println("Pronúncia fonética de '" + termo + "': [" + pronunciaFonetica + "]");
        } else {
            String pronunciaBasica = gerarPronunciaBasica(termoLower);
            System.out.println("Pronúncia fonética de '" + termo + "': [" + pronunciaBasica + "]");
        }
    }

    private String gerarPronunciaBasica(String termo) {
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < termo.length(); i++) {
            char c = termo.charAt(i);
            String letra = String.valueOf(c);

            if (i < termo.length() - 1) {
                String combinacao = termo.substring(i, i + 2);
                if (MAPEAMENTO_FONETICO.containsKey(combinacao)) {
                    resultado.append(MAPEAMENTO_FONETICO.get(combinacao));
                    i++; // pula o próximo caractere porque já foi usado na combinação
                    continue;
                }
            }

            String fonetica = MAPEAMENTO_FONETICO.getOrDefault(letra, letra);
            resultado.append(fonetica);
        }

        return resultado.toString();
    }
}
