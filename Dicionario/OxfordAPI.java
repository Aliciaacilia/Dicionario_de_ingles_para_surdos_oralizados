package Dicionario;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

public class OxfordAPI {
    private static final String APP_ID = Env.getAppId();
    private static final String APP_KEY = Env.getAppKey();
    private static final String LANGUAGE = "en-gb"; 
    private static final String BASE_URL = "https://od-api-sandbox.oxforddictionaries.com/api/v2";

    public static String buscarPronuncia(String palavra) {
        try {
            String termoFormatado = URLEncoder.encode(palavra.toLowerCase(), "UTF-8");
            String urlString = BASE_URL + "/entries/" + LANGUAGE + "/" + termoFormatado;

            URL url = new URL(urlString);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestProperty("app_id", APP_ID);
            conexao.setRequestProperty("app_key", APP_KEY);

            int respostaCodigo = conexao.getResponseCode();
            if (respostaCodigo != 200) {
                System.out.println("Erro na API: Código " + respostaCodigo);
                return "Pronúncia não encontrada";
            }

            BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            StringBuilder resposta = new StringBuilder();
            String linha;
            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha);
            }
            leitor.close();

            JSONObject json = new JSONObject(resposta.toString());

            JSONArray results = json.getJSONArray("results");
            JSONObject lexicalEntry = results.getJSONObject(0).getJSONArray("lexicalEntries").getJSONObject(0);

            JSONArray pronunciations = lexicalEntry.getJSONArray("pronunciations");
            if (pronunciations.length() > 0) {
                JSONObject primeiraPronuncia = pronunciations.getJSONObject(0);
                return primeiraPronuncia.optString("phoneticSpelling", "Pronúncia não encontrada");
            } else {
                return "Pronúncia não encontrada";
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar pronúncia: " + e.getMessage());
            return "Pronúncia não encontrada";
        }
    }
}
