import java.io.*;
import java.util.Scanner;

public class Arena {

    public static void main(String[] args) {

        Scanner dadosDoTeclado = new Scanner(System.in);
        String nome;

        System.out.println("Digite o nome do Inimigo: ");
        nome = dadosDoTeclado.next();

        checaArquivo(nome);
    }

    public static void salvaNoArquivo(String caminhoArquivo, String nomeInimigo) {

        try( FileWriter  arq = new FileWriter (caminhoArquivo, true);
             BufferedWriter in = new BufferedWriter(arq);
             PrintWriter out = new PrintWriter(in); ) {

            out.println(nomeInimigo);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public static void checaArquivo(String nomeInimigo) {

        boolean checaArquivo = false;
        Scanner dadosDoTeclado = new Scanner(System.in);
        String resposta;
        String resultado;
        String[] caminhoArquivo = {"C:/Users/Thiago/Documents/git/RaidShadowLegends-ARENA-/arquivos/RSL Vitoria.txt", "C:/Users/Thiago/Documents/git/RaidShadowLegends-ARENA-/arquivos/RSL Derrota.txt"};

        for (int i = 0; i < 2; i++) {
            try {
                FileReader arq = new FileReader(caminhoArquivo[i]);
                BufferedReader lerArq = new BufferedReader(arq);
                String[] linhas;
                String linha = null;
                while (true) {
                    try {
                        if (((linha = lerArq.readLine()) != null)) break;
                        assert false;
                        linhas = linha.split("\n");
                        for(String l: linhas){
                            if (l.equals(nomeInimigo)) {
                                checaArquivo = true;
                                break;
                            }
                        }
                        if(checaArquivo) {
                            break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (!checaArquivo) {
            System.out.println("Esse inimigo não existe nos arquivos, deseja salva ?!");
            resposta = dadosDoTeclado.nextLine();
            if (resposta.endsWith("sim")) {
                System.out.println("A Batatlha na ARENA foi uma Vitoria(v) ou uma Derrota(d) ?");
                resultado = dadosDoTeclado.nextLine();

                if (resultado.equals("v")) {
                    salvaNoArquivo(caminhoArquivo[0], nomeInimigo);
                } else {
                    salvaNoArquivo(caminhoArquivo[1], nomeInimigo);
                }
            }
        }
    }
}
