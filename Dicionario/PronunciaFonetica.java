package Dicionario;

public class PronunciaFonetica implements PronunciacaoStrategy {
    @Override
    public void pronunciar(String termo) {
        System.out.println ("Pronúncia fonética de " + termo);
    }
}
