import java.io.*;
import java.util.Scanner;

public class Main
{
    static char[][] maze;

    static int linha, coluna;
    
    public static void main(String[] args)
    {
        try
        {

         /**
         Solicita ao usuário para passar um labirinto escolhido por ele, a ser lido
         posteriormente.
         @param modelo a instância da classe AgendaConsultavel a ser usada como
         modelo.
         */
            @SuppressWarnings("resource")
            Scanner teclado = new Scanner(System.in);

            System.out.println("Digite qual o labirinto a ser usado:");
            String nomeArquivo = teclado.nextLine();

            /**
             Faz a leitura do arquivo, inicialmente buscando a localização do arquivo.
             @param nomeArquivo o nome do arquivo.
             @throws IOException se o nome estiver incorreto ou não for encontrado.
             */
           BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\maths\\Desktop\\Maze escaping\\project\\src\\labyrynths for testing\\" + nomeArquivo));
           try
           {
                linha = Integer.parseInt(in.readLine());
                coluna = Integer.parseInt(in.readLine());
           }
           catch (Exception e) //QuantidadeLiinhasErrada.txt
           {
               System.err.println("Sem quantidade de linhas ou colunas!");

           }


        /**
         Pula as duas primeiras linhas do labirinto(números que definem as linhas e colunas),
         afim de iniciar a criação do labirinto.
         */
        try
        {
            int n = 0;
            String str;

            maze = new char[linha][coluna];

            while ((str = in.readLine()) != null)
            {
                for(int i = 0; i < str.length(); i++)
                {
                    maze[n][i] = str.charAt(i);
                }
                n++;
            }
            in.close();

        }
        catch (Exception e)
        {
            System.err.println("Labirinto inválido.");

        }

            /**
             Lendo linhas por linhas do labirinto do arquivo .txt e armazenando em uma matriz
             com tamanho definido por n linhas e n colunas, passado anteriormente pelas duas
             primeiras linhas armazenadas. Depois, fecha o leitor de arquivo após o 'while'.
             
             Manda o labirinto para a classe "Labirinto" para realizar os movimentos e outros
             métodos pré-definidos.
             @param maze labirinto criado.
             @param linha número de linhas.
             @param coluna número de colunas.
             */
                Labirinto labirinto = new Labirinto(maze, linha, coluna);


            try
            {
                labirinto.quantidadeLinhasErrada();
            }
            catch (Exception a)
            {
                System.err.println("Linhas erradas com valor nulo!, labirinto incorreto");
                return;
            }


            try
            {
                labirinto.entradaForadeLugar();
            }
            catch (Exception a)
            {
                System.err.println("Entrada fora de lugar!, labirinto incorreto");
                return;
            }

            try
            {
                labirinto.verifica2Entradas();
            }
            catch (Exception a)
            {
                System.err.println("Duas ou mais entradas encontradas, labirinto incorreto");
                return;
            }


            try
            {
                labirinto.verifica2Saidas();
            }


            catch (Exception a)
            {
                System.err.println("Duas ou mais saídas encontradas, labirinto incorreto");
                return;
            }


            try
            {
                labirinto.caracteresEstranhos();
            }
            catch (Exception a)
            {
                System.err.println("Caractere(s) estranho(s) encontrado(s)!, labirinto incorreto");
                return;
            }

            try
            {
                labirinto.saidaForadeLugar();
            }
            catch (Exception a)
            {
                System.err.println("Duas ou mais saídas encontradas, labirinto incorreto");
                return;
            }


            try
            {
                labirinto.semEntrada();
            }
            catch (Exception a)
            {
                System.err.println("Não foi encontrado entrada, labirinto incorreto");
                return;
            }

            try
            {
                labirinto.semParede();
            }
            catch (Exception a)
            {
                System.err.println("Não foi encontrado paredes, labirinto incorreto");
                return;
            }


            try
            {
                labirinto.semSaida();
            }
            catch (Exception a)
            {
                System.err.println("Não foi encontrado a saida, labirinto incorreto");
                return;
            }


            try
            {
                labirinto.tamanhodeLinhasErrados();
            }
            catch (Exception a)
            {
                System.err.println("Tamanho de linhas errados, labirinto incorreto");
                return;
            }

            try
            {
                labirinto.caracteresEstranhos();
            }
            catch (Exception a)
            {
                System.err.println("Caracteres incorretos! Execute outro labirinto!");
                return;
            }


                System.out.println("Antes: \n");


                labirinto.mostra();
                labirinto.encontreEntrada();
                labirinto.ondeEstaSaida();
                labirinto.FiladeCoordenadas(Labirinto.e1, Labirinto.e2);

                System.out.println();

                labirinto.removedFromQueue();
                labirinto.move();
                labirinto.empilhaCoordernada();
                labirinto.empilhaFilaemPossibilidades();


            labirinto.findExit();

        }
        catch (Exception e)
        {}

        }
    }

