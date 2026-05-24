import javax.swing.*;
import java.awt.*;

public class PrevTemp {

    //RESULTADOS DAS PESQUISAS
    static JLabel condicao = new JLabel("Condição: -");
    static JLabel temperatura = new JLabel("Temp: -");
    static JLabel tempMax = new JLabel("Max: -");
    static JLabel tempMin = new JLabel("Min: -");
    static JLabel Umidade = new JLabel("Umidade: -");
    static JLabel Vento = new JLabel("Vento: -");

    public static void main(String[] args) {
        //CONFIGURAÇÃO DA JANELA
        JFrame janela = new JFrame("Meu App Clima");
        
        janela.setSize(900, 600);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //DENTRO DA JANELA
        janela.setLayout(new GridLayout(9, 1, 5, 5));
        JLabel instrucao = new JLabel("Digite a cidade alvo:");
        JTextField campoCidede = new JTextField(15);//campo de digitação
        JButton botaoBuscar = new JButton("🔍");//botao para buscar

        janela.add(instrucao);
        janela.add(campoCidede);//adiciona dentro da janela
        janela.add(botaoBuscar);

        janela.add(condicao);
        janela.add(temperatura);
        janela.add(tempMax);
        janela.add(tempMin);//adiciona dentro da janela
        janela.add(Umidade);
        janela.add(Vento);

        //FAZ A BUSCA
        botaoBuscar.addActionListener(e -> {
            String texto = campoCidede.getText();
            buscaUrl(texto);
        });
        campoCidede.addActionListener(e -> {
            String texto = campoCidede.getText();
            buscaUrl(texto);
        });

         

        janela.setVisible(true);
    }

    public static void buscaUrl(String cidadeDigitada){
            
            String cidadeForm = cidadeDigitada.replace(" ", "%20");//Transforma o espaço em %20

            //System.out.println("cidade: " + cidadeForm); // para testes 
            //MONTA A URL
            String chaveAPI = "S6A6FU5YM8PHC6XTZH5J9Y3Y5";
            String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                        + cidadeForm
                        + "?unitGroup=metric&key=" + chaveAPI
                        + "&contentType=json&lang=pt"; 

            //System.out.println("LINK: " + url);// para testes

            //NAVEGAÇÃO
            try{
                java.net.URL urlConexao = new java.net.URL(url);

                java.net.HttpURLConnection conexao = (java.net.HttpURLConnection) urlConexao.openConnection();
                conexao.setRequestMethod("GET"); 

                java.io.BufferedReader leitor = new java.io.BufferedReader(new java.io.InputStreamReader(conexao.getInputStream(), "UTF-8"));
                StringBuilder resposta = new StringBuilder();
                String linha;

                while ((linha = leitor.readLine()) != null) {
                    resposta.append(linha);
                }
                leitor.close();

                //System.out.println("\n--- DADOS DA INTERNET ---");//para testes
                //System.out.println(resposta.toString());//para testes

                String json = resposta.toString();//transforma o textão em uma variavel menorzinha
                
                //BUSCA PELA TEMPERATURA
                int posicaoTemp = json.indexOf("\"temp\":");
                //System.out.println("A palavra temp está na posição: " + posicaoTemp);//para testes
                int inicioTemp = posicaoTemp + 7;
                int fimTemp = json.indexOf(",", inicioTemp);
                String valorTemp = json.substring(inicioTemp, fimTemp);
                //System.out.println("A temperatura limpinha é: " + valorTemp);//para testes
                temperatura.setText("Temp: " + valorTemp + " °C");

                //BUSCA PELA MAX
                int posMax = json.indexOf("\"tempmax\":");
                int inicioMax = posMax + 10;
                int fimMax = json.indexOf(",", inicioMax);
                String valorMax = json.substring(inicioMax, fimMax);
                tempMax.setText("Max: " + valorMax + " °C");

                //BUSCA PELA MIN
                int posMin = json.indexOf("\"tempmin\":");
                int inicioMin = posMin + 10;
                int fimMin = json.indexOf(",", inicioMin);
                String valorMin = json.substring(inicioMin, fimMin);
                tempMin.setText("Min: " + valorMin + " °C");

                //BUSCA PELA UMIDADE
                int posUmid = json.indexOf("\"humidity\":");
                int inicioUmid = posUmid + 11;
                int fimUmid = json.indexOf(",", inicioUmid);
                String valorUmid = json.substring(inicioUmid, fimUmid);
                Umidade.setText("Umidade: " + valorUmid + "%");

                //BUSCA PELO VENTO
                int posVento = json.indexOf("\"windspeed\":");
                int inicioVento = posVento + 12;
                int fimVento = json.indexOf(",", inicioVento);
                String valorVento = json.substring(inicioVento, fimVento);
                Vento.setText("Vento: " + valorVento + " km/h");


                //BUSCA A CONDIÇÃO
                int posCond = json.indexOf("\"conditions\":\"");
                int inicioCond = posCond + 14; 
                int fimCond = json.indexOf("\"", inicioCond); // Procura a aspa fechando em vez da vírgula
                String valorCond = json.substring(inicioCond, fimCond);
                condicao.setText("Condição: " + valorCond);
            }
            catch(Exception erro){
                System.out.println("Temos um erro de conexão: "+ erro.getMessage());
            }
    }
}
