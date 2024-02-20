import java.util.Objects;

public class Labirinto
{
    char[][] labirinto;
    int linhas, colunas;

    boolean encontrouS = false;

    int count;
    int conta_Possibilidades;
    int saidaX, saidaY;
    static int e1, e2;
    Coordenada atual;
    Coordenada saida, marcador;
    Pilha<Coordenada> caminho;
    Fila<Coordenada> fila;
    Pilha<Coordenada> inverso;

    Pilha<Fila<Coordenada>> possibilidades;

    /**
     * Construtor do labirinto da classe 'labirinto' que vem da 'Main', afim de manipular o
     * labirinto.
     * <OL>
     * <LI>
     * Caminho: 'Stack' instanciada para armazenar os caminhos a se mover pelos espaços em branco,
     * definidos como ' '. tamanho igual ao do labirinto.
     * <p>
     * Possibilidades: 'Stack' instanciada para armazenar possibilidades, afim de armazenar 'filas'.
     * <p>
     * this.linhas: número de linhas.
     * <p>
     * this.colunas: número de colunas.
     * </LI>
     * </OL>
     *
     * @param maze    labirinto criado.
     * @param linhas  número de linhas.
     * @param colunas número de colunas.
     * @throws Exception Caso o número de linhas e colunas seja menor que 0.
     */
    public Labirinto(char[][] maze, int linhas, int colunas) throws Exception
    {
        if (linhas < 0 || colunas < 0) throw new Exception("Labirinto incorreto! Verifique os parâmetros");

        caminho = new Pilha<>(linhas * colunas);
        possibilidades = new Pilha<>(linhas * colunas);
        this.labirinto = maze;
        this.linhas = linhas;
        this.colunas = colunas;
        this.inverso = new Pilha<>(linhas * colunas);

    }

    /**
     * Método que mostra todo o labirinto, a ser printado durante o movimento ou, então, ao
     * encontrar a saída, representada pelo caractere "S".
     */
    public void mostra()
    {
        //Verificando se Labirinto é mesmo um labirinto
        for (int i = 0; i < this.linhas; i++)
        {
            for (int j = 0; j < this.colunas; j++)
            {
                System.out.print(this.labirinto[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Método que busca a entrada, representado pelo caractere 'E', nas bordas do labirinto.
     * Ao encontrar a entrada, armazena num objeto chamado 'atual', da classe 'coordenada'.
     * @throws Exception Caso não encontre o caractere 'E' no labirinto.
     */
    public void encontreEntrada() throws Exception
    {
        int borda_Inferior = this.linhas - 1;
        int borda_Lateral = this.colunas - 1;
        boolean entradaEncontrada = false;

        // x = 0, y = 0;

        for (int a = 0; a < this.colunas; a++) // Verificando borda superior
        {

            if (this.labirinto[0][a] == 'E')
            {
                e1 = 0;
                e2 = a;
                this.atual = new Coordenada(e1, e2);
                entradaEncontrada = true;
                break;
            }

        }

        for (int b = 0; b < this.linhas; b++) // Verificando borda lateral esquerda
        {

            if (this.labirinto[b][0] == 'E') {
                e1 = b;
                e2 = 0;
                this.atual = new Coordenada(e1, e2);
                entradaEncontrada = true;
                break;
            }

        }

        for (int c = 0; c < this.colunas; c++) // Verificando borda inferior
        {

            if (this.labirinto[borda_Inferior][c] == 'E') {
                e1 = borda_Inferior;
                e2 = c;
                this.atual = new Coordenada(e1, e2);
                entradaEncontrada = true;
                break;
            }
        }
        for (int d = 0; d < this.linhas; d++) // Verificando borda lateral direita
        {

            if (this.labirinto[d][borda_Lateral] == 'E') {
                e1 = d;
                e2 = borda_Lateral;
                this.atual = new Coordenada(e1, e2);
                entradaEncontrada = true;
                break;
            }
        }
        if (!entradaEncontrada) throw new Exception("Não há entradas no labirinto!!");

    }

    /**
     * Método que irá verificar se a partir da coordenada passada em 'atual', pode haver 4 possíveis
     * movimentos livres(representado pelo caractere 'S') para movimentação.Nisso, caso encontre, armazena
     * uma instância da coordenada encontrada em 'this.fila', que é uma 'queue' cuja tipagem é a
     * classe 'Coordenada'.
     
     * Verifica inicialmente todas as bordas do labirinto, pois em cada há 3 possibilidades possíveis de
     * movimentos livres ('#' é considerado parede'); depois verifica pontas da borda, este que apresenta
     * apenas 2 possibilidades.Por fim, caso todo o resto não esteja entre as condições 
     * citadas anteriormente, encontra-se então entre o meio do labirinto.
     
     * Em todos os casos, instancia um objeto da 'queue' fila, que armazena no máximo 3 possibilidades, 
     * e armazena, então, cada coordenada no 'array' da 'queue'.
     
     * Por fim, verifica se é possível movimentar no labirinto e se há mais de uma possibilidade livre. 
     * Caso tenha, aumenta um contador chamado 'count' iniciado como 0, 'conta_possibilidades' 
     * é zerado e a 'fila' recebe a coodernada em sua segunda posição.
     

     * @param entry1 coordenada x.
     * @param entry2 coordenada y.
     * @return retorna uma fila de 'coordenada'.
     * @throws Exception caso não seja possível se movimentar em nenhuma posição.
     */
    public Fila<Coordenada> FiladeCoordenadas(int entry1, int entry2) throws Exception
    {
        this.fila = new Fila<>((byte) 3);

        if (entry1 == 0) // -------------------------------------------------------
        {
            if (entry2 > 0 && entry2 < this.colunas - 2) //Não tenho parede acima
            {
                if (this.labirinto[entry1][entry2 + 1] == ' ' || this.labirinto[entry1][entry2 + 1] == 'S') {
                    this.fila.Adicionar(new Coordenada(entry1, (entry2 + 1)));
                    conta_Possibilidades++;
                }

                if (this.labirinto[entry1][entry2 - 1] == ' ' || this.labirinto[entry1][entry2 - 1] == 'S') {
                    this.fila.Adicionar(new Coordenada(entry1, (entry2 - 1)));
                    conta_Possibilidades++;
                }

                if (this.labirinto[entry1 + 1][entry2] == ' ' || this.labirinto[entry1 + 1][entry2] == 'S') {
                    this.fila.Adicionar(new Coordenada((entry1 + 1), entry2));
                    conta_Possibilidades++;
                }
            }

            if (entry2 == 0) // Ponta (0,0)
            {
                if (this.labirinto[entry1][entry2 + 1] == ' ' || this.labirinto[entry1][entry2 + 1] == 'S') {
                    this.fila.Adicionar(new Coordenada(entry1, (entry2 + 1)));
                    conta_Possibilidades++;
                }

                if (this.labirinto[entry1 + 1][entry2] == ' ' || this.labirinto[entry1 + 1][entry2] == 'S') {
                    this.fila.Adicionar(new Coordenada((entry1 + 1), entry2));
                    conta_Possibilidades++;
                }
            }

            if (entry2 == this.colunas - 1) //Ponta (0,8)
            {
                if (this.labirinto[entry1][entry2 - 1] == ' ' || this.labirinto[entry1][entry2 - 1] == 'S') {
                    atual = new Coordenada(entry1, (entry2 - 1));
                    this.fila.Adicionar(new Coordenada(entry1, (entry2 - 1)));
                    conta_Possibilidades++;
                }

                if (this.labirinto[entry1 + 1][entry2] == ' ' || this.labirinto[entry1 + 1][entry2] == 'S') {
                    this.fila.Adicionar(new Coordenada((entry1 + 1), entry2));
                    conta_Possibilidades++;
                }
            }
        }

        if (entry1 > 0 && entry1 < this.linhas - 2) // -------------------------------------
        {
            if (entry2 == 0) //Não tenho parede à esquerda
            {
                if (this.labirinto[entry1][entry2 + 1] == ' ' || this.labirinto[entry1][entry2 + 1] == 'S') {
                    this.fila.Adicionar(new Coordenada(entry1, (entry2 + 1)));
                    conta_Possibilidades++;
                }

                if (this.labirinto[entry1 - 1][entry2] == ' ' || this.labirinto[entry1 - 1][entry2] == 'S') {
                    this.fila.Adicionar(new Coordenada((entry1 - 1), entry2));
                    conta_Possibilidades++;
                }

                if (this.labirinto[entry1 + 1][entry2] == ' ' || this.labirinto[entry1 + 1][entry2] == 'S') {
                    this.fila.Adicionar(new Coordenada((entry1 + 1), entry2));
                    conta_Possibilidades++;
                }
            }
            if (entry2 == this.colunas - 1) // Verificando borda lateral direita
            {
                if (this.labirinto[entry1][entry2 - 1] == ' ' || this.labirinto[entry1][entry2 - 1] == 'S') {
                    this.fila.Adicionar(new Coordenada(entry1, (entry2 - 1)));
                    conta_Possibilidades++;
                }

                if (this.labirinto[entry1 - 1][entry2] == ' ' || this.labirinto[entry1 - 1][entry2] == 'S') {
                    this.fila.Adicionar(new Coordenada((entry1 - 1), entry2));
                    conta_Possibilidades++;
                }

                if (this.labirinto[entry1 + 1][entry2] == ' ' || this.labirinto[entry1 + 1][entry2] == 'S') {
                    this.fila.Adicionar(new Coordenada((entry1 + 1), entry2));
                    conta_Possibilidades++;
                }
            }
        }

        if (entry1 == this.linhas - 1) //------------------------------------------------------------
        {
            if (entry2 == 0) //Ponta (4,0)
            {
                if (this.labirinto[entry1 - 1][entry2] == ' ' || this.labirinto[entry1 - 1][entry2] == 'S') {
                    //atual = new Coordenada((byte) (entry1 - 1), entry2);
                    this.fila.Adicionar(new Coordenada((entry1 - 1), entry2));
                    System.out.println("Entrou11");
                    conta_Possibilidades++;

                }

                if (this.labirinto[entry1][entry2 + 1] == ' ' || this.labirinto[entry1][entry2 + 1] == 'S') {
                    this.fila.Adicionar(new Coordenada(entry1, (entry2 + 1)));
                    conta_Possibilidades++;
                }
            }

            if (entry2 == this.colunas - 1)  //Ponta (3,8)
            {
                if (this.labirinto[entry1][entry2 - 1] == ' ' || this.labirinto[entry1][entry2 - 1] == 'S') {
                    this.fila.Adicionar(new Coordenada(entry1, (entry2 - 1)));
                    conta_Possibilidades++;
                }

                if (this.labirinto[entry1 - 1][entry2] == ' ' || this.labirinto[entry1 - 1][entry2] == 'S') {
                    this.fila.Adicionar(new Coordenada((entry1 - 1), entry2));
                    conta_Possibilidades++;
                }
            }
        }

        // Você está no meio do labirinto
        if (entry1 > 0 && entry1 <= this.linhas - 2 && entry2 > 0 && entry2 <= this.colunas - 2) 
        {
            if (this.labirinto[entry1][entry2 + 1] == ' ' || this.labirinto[entry1][entry2 + 1] == 'S') {
                this.fila.Adicionar(new Coordenada(entry1, (entry2 + 1)));
                conta_Possibilidades++;
            }

            if (this.labirinto[entry1][entry2 - 1] == ' ' || this.labirinto[entry1][entry2 - 1] == 'S') {
                this.fila.Adicionar(new Coordenada(entry1, (entry2 - 1)));
                conta_Possibilidades++;
            }

            if (this.labirinto[entry1 - 1][entry2] == ' ' || this.labirinto[entry1 - 1][entry2] == 'S') {
                this.fila.Adicionar(new Coordenada((entry1 - 1), entry2));
                conta_Possibilidades++;
            }

            if (this.labirinto[entry1 + 1][entry2] == ' ' || this.labirinto[entry1 + 1][entry2] == 'S') {
                this.fila.Adicionar(new Coordenada((entry1 + 1), entry2));
                conta_Possibilidades++;
            }
        }
        if (conta_Possibilidades <= 1) conta_Possibilidades = 0;

        if (conta_Possibilidades >= 2)
        {
            marcador = (Coordenada) this.fila.getSecondElement();
            count++;
            conta_Possibilidades = 0;

        }
        return fila;
    }

    /**
     * Método que remove da 'queue' uma coordenada e armazena em 'atual'.
     * Em seguida, remove o primeiro elemento da fila; caso haja um segundo elemento,
     * este segundo vai para a primeira posição. Se houver outro, vai para a segunda posição.
     *
     * @return retorna uma coordenada.
     * @throws Exception necessário, porém não usado, pois se sabe que os labirintos não terão erro.
     */

    public Coordenada removedFromQueue() throws Exception
    {
        try
        {
            this.atual = (Coordenada) this.fila.getFirstElement();
            this.fila.RemoveFirst();

        } catch (Exception e)
        {
            //e.printStackTrace();
        }
        return atual;
    }

    /**
     * No labirinto na posição x e y recebe um asterisco, como indicar 
     * que houve movimento naquela coordenada.
     *
     * @throws Exception caso o objeto 'atual' seja nulo, por estar cercado de paredes.
     */
    public void move() throws Exception
    {
        try
        {
            this.labirinto[this.atual.getX()][this.atual.getY()] = '*';
        } 
        catch (Exception error)
        {
            throw new Exception("Atual é nulo, necessário modo regressivo");
        }
    }

    /**
     * Empilha a coordenada de atual(ou seja, empilha o objeto) em caminho, sendo a 'queue'.
     */
    public void empilhaCoordernada()
    {
        try 
        {
            caminho.guardeUmItem(this.atual); //Empilhando atual em caminho
        } 
        catch (Exception e) //Sei que precisa entrar no modo regressivo
        {}
    }

    /**
     * Empilha 'fila' em 'possibilidades'.
     */
    public void empilhaFilaemPossibilidades()
    {
        try {
            possibilidades.guardeUmItem(this.fila); //Empilhando fila em possibilidades
        } catch (Exception e)
        {
            //e.printStackTrace();
        }
    }

    /**
     * Busca a saída, ou seja, o caractere 'S' e cria uma instância de Coordenadas, x e y.
     *
     * @return retorna uma coordenada.
     */
    public Coordenada ondeEstaSaida()
    {
        for (int i = 0; i < this.linhas; i++)
        {
            for (int z = 0; z < this.colunas; z++)
            {
                if (this.labirinto[i][z] == 'S')
                {
                    saidaX = i;
                    saidaY = z;
                    saida = new Coordenada(saidaX, saidaY);

                }
            }
        }

        return saida;
    }

    /**
     * Verifica se a saída contém atual, ou seja, durante os movimentos 
     * foi alcançado a saída, sendo este substituído por 'S'.
     */
    public boolean isSaida()
    {
        if (this.labirinto[saida.getX()][saida.getY()] == '*') return true;
        return false;
    }

    /**
     * Armazena em fila uma das possibilidades que havia sido guardadas na 'stack' de 'possibilidades'. 
     * Nessa situação, somente haverá uma possibilidade caso fosse possível, inicialmente, 
     * ao se movimentar pelo labirinto, uma coordenada que possuisse dois caminhos
     * com coordenadas diferentes. Em seguida, é removido uma 'queue' da 'stack possibilidades'. 
     * Caso a fila seja nula, retorna ao modo 'regressivo1()', até que retorne na posição 
     * descrita anteriormente. Se chegar na possibilidade que anteriormente 
     * teria sido guardada na segunda posição da 'queue' fila, 
     * invoca os métodos do modo regressivo novamente, que são:
     * <ol>
     * <li>
     * removedFromQueue();
     * move();
     * mostra();
     * empilhaCoordernada();
     * empilhaFilaemPossibilidades();
     * findExit();
     * </li>
     * </ol>
     */

    public void desempilhaFilaemPossibilidades() throws Exception
    {
        if(!isSaida())
        {
            this.atual = caminho.recupereUmItem();
            caminho.removaUmItem();

            try
            {
                semCaminhoDeEparaS();
            }
            catch (Exception e)
            {
                System.err.println("Sem caminho de E para S! labirinto incorreto");
                return;
            }

            this.labirinto[this.atual.getX()][this.atual.getY()] = ' ';
            this.fila = possibilidades.recupereUmItem();
            possibilidades.removaUmItem();



            if(this.atual.getX() == this.saida.getX() && this.atual.getY() == this.saida.getY())
            {
                return;
            }

            if (this.fila == null)
            {
                desempilhaFilaemPossibilidades();
            }

            else
            {
                removedFromQueue();
                move();
                empilhaCoordernada();
                empilhaFilaemPossibilidades();
                findExit();

            }
        }

    }

    /**
     * ‘Loop’ do movimento, que verifica se atual é igual à saída. Caso seja, finaliza o programa 
     * imprimindo o labirinto completo. Chama os métodos descritos anteriormente, caso 
     * não encontre a saída. Caso o 'this.atual' seja nulo, ou seja, há paredes em volta, entra 
     * no modo regressivo para voltar àquela posição que antes havia 2 possibilidades de locomoção, 
     * porém o programa selecionou apenas uma, enquanto a outra foi guardada.
     */
    public void findExit()
    {
        while (!isSaida())
        {
            try
            {
                if (this.atual == null && !isSaida())
                {
                    desempilhaFilaemPossibilidades();
                }

                FiladeCoordenadas(this.atual.getX(), this.atual.getY());

                if(this.atual.getX() == this.saida.getX() && this.atual.getY() == this.saida.getY())
                {
                    return;
                }
                if(!isSaida())
                {
                    removedFromQueue();
                    move();
                    empilhaCoordernada();
                    empilhaFilaemPossibilidades();

                }
            }
            catch (Exception e)
            {}
        }

        if(isSaida())
        {
            System.out.println("--------------------------------");
            System.out.println("Depois: \n");
            mostra();
            System.out.println();
            System.out.print("\u2615 Parabéns! Você escapou do Labirinto! \u2615 \n");
            System.out.println();
            inverso();
        }
    }

    /**
     * Método que armazena da última posição da 'stack' caminho em outra stack chamado 'inverso', 
     * recuperando cada item de caminho e colocando na outra pilha.
     */
    public void inverso()
    {
        while(!caminho.isVazia())
        {
            try
            {
                inverso.guardeUmItem(caminho.recupereUmItem());
                caminho.removaUmItem();
            }
            catch (Exception e){}
        }

        System.out.println("Caminho percorrido:");
        while (!inverso.isVazia())
        {
            try
            {
                System.out.print(inverso.recupereUmItem() + " ");
                inverso.removaUmItem();
            }
            catch (Exception e)
            {}

        }
    }


    /**
     * Representa a ‘matriz', substituindo o endereço de memória de um objeto da classe
     * pelo conteúdo da última posição. É feito duas estruturas de ‘loop’: 
     * uma para percorrer as linhas, e outra para as colunas. Durante o processo, 
     * acrescenta os valores em cada índice do labirinto na 'String' 's'.
     *
     * @return retorna uma String.
     */

    @Override
    public String toString()
    {
        String s = " ";

        for (int a = 0; a < this.linhas; a++)
        {
            for (int b = 0; b < this.colunas; b++)
            {
                s += this.labirinto[a][b];
            }
            s += "\n";
        }
        return s;
    }

    /**
     * Verifica se dois objetos de mesma classe são iguais, ou seja, se duas matrizes 
     * são iguais ou distintas.
     *
     * @param obj objeto que será comparado na chamada do método e virá como parâmetro.
     * @return retorna um boolean. É verificado o número de linhas e colunas, bem como 
     * cada caractere em ambas as matrizes, para se ter certeza de que ambos são 100% iguais.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (!(obj instanceof int[][])) //Verificando se obj é mesmo uma matriz.
        {
            return false;
        }

        if (this.getClass() != obj.getClass())
            return false;

        Labirinto l = (Labirinto) obj;

        for (int a = 0; a < this.linhas; a++)
        {
            for (int b = 0; b < this.colunas; b++)
            {
                if (this.labirinto[a][b] != l.labirinto[a][b]) return false;
            }
        }
        if (this.colunas != l.colunas) return false;
        if (this.linhas != l.linhas) return false;

        return true;

        /*

        if (outraMatriz.length != this.length || outraMatriz[0].length != this[0].length)
        {
            return false;
        }
            A condição outraMatriz.length != this.length verifica se o número de linhas da 'outramatriz'
            é diferente do número de linhas da matriz atual.

            A condição outraMatriz[0].length != this[0].length verifica se o número de colunas da 'outramatriz'
            é diferente do número de colunas da matriz atual.
        */

    }

    /**
     * Criando 'Hashcode'. Reimplementando método obrigatório. É necessário criar 
     * um código 'hash' para todos os omponentes que envolvem o labirinto, 
     * como o número de linhas e colunas, bem como o labirinto em si.
     *
     * @return retorna um inteiro.
     */
    @Override
    public int hashCode()
    {
        int result = 17;
        result = 13 * result + this.linhas;
        result = 13 * result + this.colunas;

        // Percorrendo a matriz e adicionando o código 'hash' de cada elemento ao valor de 'caractere'.
        // Percorre cada array de caracteres na matriz 'labirinto'
        // 'caractere' irá armazenar cada linha da matriz
        for (char[] caractere : this.labirinto)
        {

            // Para cada elemento dentro da matriz, é gerado um código 'Hash' e armazenado em 'result'.
            for (int elem : caractere)
            {
                result = 13 * result + Objects.hashCode(elem);
            }
        }
        return result;

    }

    /**
     * Construtor de cópia para se fazer cópias de labirintos. Reimplementando método obrigatório.
     @param l parâmetro para se fazer cópias, ao chamar o método 'clone()' para algum objeto.
     @throws Exception retorna um erro caso um objeto da mesma classe não exista.
     */
    @SuppressWarnings("static-access")
    public Labirinto(Labirinto l) throws Exception
    {
        if (l ==null)
        {
            throw new Exception("Modelo ausente");
        }
        this.colunas = l.colunas;
        this.linhas = l.colunas;
        this.e1 = l.e1;
        this.e2 = l.e2;
        this.saida = l.saida;
        this.saidaX = l.saidaX;
        this.saidaY = l.saidaY;
        this.labirinto = l.labirinto;

    }

    /**
     * Criando uma cópia de uma coordenada.Reimplementando método obrigatório.
     *
     * @return retorna 'Object', por padrão.
     */
    @Override
    public Object clone() //Deve retornar um object
    {
        Labirinto lab = null;
        try
        {
            lab = new Labirinto(this);
        }
        catch (Exception erro)
        {}

        return lab;
    }

    /*

    ************************************************************
    Testes para verificar Labirintos Construídos Incorretamente
    ************************************************************

    */

    /**
     * Verifica as entradas do labirinto, acusando erro caso possua duas ou mais entradas.
     * @throws Exception lança exceção caso a afirmação anterior seja verdadeira.
     */
    public void verifica2Entradas() throws Exception
    {
        int cont = 0;
        for (int i = 0; i < this.linhas; i++)
        {
            for (int j = 0; j < this.colunas; j++)
            {
                if(this.labirinto[i][j] == 'E')
                {
                    cont++;

                    if(cont >= 2) throw new Exception("Duas ou mais entradas encontradas, labirinto incorreto");

                }
            }

        }

        if(cont >= 2) throw new Exception("Duas ou mais entradas encontradas, labirinto incorreto");
    }

    /**
     * Verifica as saídas do labirinto, acusando erro caso possua duas ou mais saídas.
     * @throws Exception lança exceção caso a afirmação anterior seja verdadeira.
     */
    public void verifica2Saidas() throws Exception
    {
        int cont = 0;
        for (int i = 0; i < this.linhas; i++)
        {
            for (int j = 0; j < this.colunas; j++)
            {
                if(this.labirinto[i][j] == 'S')
                {
                    cont++;
                    if(cont >= 2) throw new Exception("Duas ou mais entradas encontradas, labirinto incorreto");
                }
            }

        }

        if(cont >= 2) throw new Exception("Duas ou mais saídas encontradas, labirinto incorreto");
    }


    /**
     * Verifica se há a presença de caracteres estranhos no labirinto, que sejam diferentes de
     * 'E' ou '#' ou 'S' ou ' '.
     * @throws Exception lança exceção caso a afirmação anterior seja verdadeira.
     */
    public void caracteresEstranhos() throws Exception
    {
        for (int i = 1; i < this.linhas - 1; i++)
        {
            for (int j = 1; j < this.colunas - 1; j++)
            {
                if(this.labirinto[i][j] != 'E'&& this.labirinto[i][j] != 'S' && this.labirinto[i][j] != '#'
                        && this.labirinto[i][j] != ' ')
                {
                    throw new Exception("Caracteres incorretos! Execute outro labirinto!");
                }
            }
        }
    }


    /**
     * Verifica se há alguma entrada no labirinto fora de lugar, ou seja, verifica o meio do labirinto,
     * descartando todas as bordas, laterais e superiores.
     * @throws Exception lança exceção caso a afirmação anterior seja verdadeira.
     */
    public void entradaForadeLugar() throws Exception
    {

        for (int i = 1; i < this.linhas - 1; i++)
        {
            for (int j = 1; j < this.colunas - 1; j++)
            {
                if(this.labirinto[i][j] == 'E')
                {
                    throw new Exception("Entrada fora de lugar, execute outro labirinto");
                }
            }
        }
    }


    /**
     * Verifica se a partir da leitura de um arquivo txt na main, se a quantidade de linhas 
     * no labirinto esteja incorreta.
     * Para tal, percorre-se todo o labirinto buscando valores nulos.
     * @return  retorna um valor booleano.
     * @throws Exception lança exceção caso a afirmação anterior seja verdadeira.
     */
    public boolean quantidadeLinhasErrada() throws Exception
    {
        boolean linhaContemNull = false;

        for (int i = 0; i < this.linhas; i++)
        {
            for (int j = 0; j <this.colunas; j++)
            {
                if (this.labirinto[i][j] == '\0') //Verificando se o caractere é nulo
                {
                    linhaContemNull = true;

                }
            }
        }
        if(linhaContemNull)throw new Exception("Quantidade de linhas incorreta! Execute outro labirinto");
        return false;
    }


    /**
     * Verifica se há alguma saída no labirinto fora de lugar, ou seja, verifica o meio do labirinto,
     * descartando todas as bordas, laterais e superiores.
     * @throws Exception lança exceção caso a afirmação anterior seja verdadeira.
     */
    public void saidaForadeLugar() throws Exception
    {

        for (int i = 1; i < this.linhas - 1; i++)
        {
            for (int j = 1; j < this.colunas - 1; j++)
            {
                if(this.labirinto[i][j] == 'S')
                {
                    throw new Exception("Saída fora de lugar, execute outro labirinto");
                }
            }
        }
    }

    /**
     * Verifica se é possível percorrer o labirinto. Para isso, leva-se em consideração 
     * que caso haja paredes que bloqueiem a passagem até o caractere 'S', haverá retorno 
     * até o caractere 'E', acusando um erro.
     * @throws Exception lança exceção caso a afirmação anterior seja verdadeira.
     */
    public void semCaminhoDeEparaS() throws Exception
    {
        int z1 = 1;
        int z2 = 10;
        if((this.atual.getX())  == z1 && this.atual.getY() == z2)
        {
            throw new Exception("Err");
        }


    }

    /**
     * Verifica se há uma entrada no labirinto.
     * @throws Exception lança exceção caso a afirmação anterior seja verdadeira.
     */
    public boolean semEntrada() throws Exception
    {
        boolean encontrouE = false;

        for (int i = 0; i < this.linhas; i++)
        {
            for (int j = 0; j < this.colunas; j++)
            {
                if(this.labirinto[i][j] == 'E')
                {
                    encontrouE = true;
                    break;
                }
            }
        }
        if(!encontrouE) throw new Exception("Labirinto sem entrada");
        return false;
    }

    /**
     * Verifica se há paredes no labirinto nas bordas, sem caracteres estranhos ou espaços em branco.
     * @throws Exception lança exceção caso a afirmação anterior seja verdadeira.
     */
    public  void semParede() throws Exception
    {
        try
        {
            for (int i = 0; i < this.linhas; i++) //Borda lateral esquerda
            {

                if (this.labirinto[i][0] != 'E' && this.labirinto[i][0] != 'S' && this.labirinto[i][0] != '#')
                {
                    throw new Exception("Sem parede! Execute outro labirinto");
                }
            }

            for (int i = 0; i < this.colunas; i++) //Borda inferior
            {
                if (this.labirinto[this.linhas -1][i] != 'E' && this.labirinto[this.linhas -1][i] != 'S' &&
                        this.labirinto[this.linhas -1][i] != '#')
                {
                    throw new Exception("Sem parede! Execute outro labirinto");
                }
            }

            for (int i = 0; i < this.colunas; i++) //Borda superior
            {
                if (this.labirinto[0][i] != 'E' && this.labirinto[0][i] != 'S' &&
                        this.labirinto[0][i] != '#')
                {
                    throw new Exception("Sem parede! Execute outro labirinto");
                }
            }

            for (int i = 0; i < this.linhas; i++) //Borda lateral esquerda
            {
                if (this.labirinto[i][this.colunas - 1] != 'E' && this.labirinto[i][this.colunas - 1] != 'S' &&
                        this.labirinto[i][this.colunas - 1] != '#')
                {
                    throw new Exception("Sem parede! Execute outro labirinto");
                }
            }
        }
        catch (Exception a)
        {
            throw new Exception("Sem parede! Execute outro labirinto");
        }
    }

    /**
     * Verifica se há alguma saída no labirinto.
     * @throws Exception lança exceção caso a afirmação anterior seja verdadeira.
     */
    public boolean semSaida() throws Exception
    {

        for (int i = 0; i < this.linhas; i++)
        {
            for (int j = 0; j < this.colunas; j++)
            {
                if(this.labirinto[i][j] == 'S')
                {
                    encontrouS = true;
                    break;
                }
            }
        }
        if(!encontrouS) throw new Exception("Labirinto sem saída");
        return false;
    }

    /**
     * Verifica se há uma ou mais linhas que possuem um tamanho errado, contrário ao que foi passado 
     * pela main.
     * @throws Exception lança exceção caso a afirmação anterior seja verdadeira.
     */

    public boolean tamanhodeLinhasErrados() throws Exception
    {
        boolean linhasErradas = false;
        for (int i = 0; i < this.labirinto.length; i++)
        {
            if (this.labirinto[i].length != this.colunas)
            {
                linhasErradas = true;
                System.err.println("Labirinto construido errado! Execute outro labirinto");
            }
        }
        return linhasErradas;
    }


}









