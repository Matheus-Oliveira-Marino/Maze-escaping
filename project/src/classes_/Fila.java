import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Fila <X>
{
    private Object[] elem; // private X[] elem;

    private int index = -1;
    private int contador;
    private int qtd;
    private boolean cheio;


    /**
     Construtor da 'queue', responsável por instanciar a fila, recebendo como parâmetro um tamanho,
     vindo da 'main'.
     @param tamanho tamanho da 'queue', pré-definido como um Array.
     @throws  Exception lança exceção, caso 'tamanho' seja menor ou igual a 0.
     */
    public Fila (int tamanho) throws Exception
    {
        if (tamanho<=0)
            throw new Exception ("Capacidade invalida");

        //this.elem = new X      [capInicial];

        this.elem = new Object[tamanho];
    }


    /**
     Preenche a fila fazendo inicialmente verificações importantes. Caso a fila esteja cheia, 
     chama um método que expande o tamanho do vetor de fila, passando um valor como parâmetro;

     Em seguida. Aumenta — se o 'index', que representa um valor que será usado para percorrer 
     cada índice da 'queue'. Por fim, é verificado se o objeto recebido como parâmetro 
     pode ser clonável, armazenando uma cópia na 'queue'. Em caso negativo, a
     inda armazena 'x' em 'this.elem'.
     @param x parâmetro que vem da 'main', para adicionar valores na 'queue'.
     @throws  Exception lança exceção, caso um valor chamado 'qtd' seja maior que o tamanho
     de 'this.elem'.
     @throws Exception caso o objeto recebido como parâmetro da classe X seja nulo.
     */
    public void Adicionar (X x) throws Exception
    {
        if(qtd >= this.elem.length) throw new Exception("Vetor cheio!");
        if(x == null) throw new Exception("Falta o que guardar");

        if(this.isCheia()) this.redimensioneSe(2.0F);
        index++;

        if(x instanceof Cloneable) this.elem[this.index] = meuClonedeX(x);
        else this.elem[this.index] = x;

        qtd++;
    }


    /**
     Reimplementando método obrigatório, visto que o clone não funciona para classes genéricas,
     pois os valores da 'queue' podem ser alterados quando, também, o valor de um objeto da classe X
     for alterado, este que também tem seu valor armazenado na 'queue'.
     @param x parâmetro para cópia.
     @return retorna um objeto da classe genérica,
     */
    public X meuClonedeX(X x)
    {
        X ret = null;
        try
        {
            Class<?> classe = x.getClass();
            Class<?>[] tpsParmsForms = null;
            Method metodo = null;
            metodo = classe.getMethod("clone", tpsParmsForms);
            Object[] parmsReais = null;
            ret = (X)metodo.invoke(x,parmsReais);
        }
        catch (NoSuchMethodException erro) {}
        catch (InvocationTargetException erro){}
        catch (IllegalAccessException erro){}

        return ret;
    }

    /**
     Remove o primeiro elemento da 'queue'. Em seguida, é feito um redimensionamento onde
     todos os elementos andam uma posição, afim de à posição 1(indíce 0) não fica vazia, ou 'null'.

     @throws  Exception caso o contador seja menor que 0, indicando uma posição negativa,
     inexistente em um Array.
     */

    public void RemoveFirst() throws Exception
    {
        if(this.contador < 0)  throw new Exception("Vetor vazio, não é possível remover");

        this.elem[0] = null;
        for(byte i = 0; i < this.elem.length - 1; i++)
        {
            this.elem[i] = this.elem[i+1];

        }
    }


    /**
     Verifica se a 'queue' está cheia ou não.
     @return  retorna um valor booleano.
     */
    public boolean isCheia()
    {
        return cheio;
    }

    /**
     Verifica se a 'queue' está vazia ou não.
     @return  retorna um valor booleano.
     */
    public boolean isVazia()
    {
        if(this.index==-1)
            return true;

        return false;

    }

    /**
     Obtém o primeiro elemento da fila.
     @return  retorna um objeto da posição 1.
     */
    Object getFirstElement ()
    {
        return this.elem[0];
    }

    /**
     Obtém o segundo elemento da fila.
     @return  retorna um objeto da posição 1.
     */
    Object getSecondElement ()
    {
        return this.elem[1];
    }

    /**
     Obtém o terceiro elemento da fila. No caso do labirinto, este foi criado, pois, sabe-se que a 'queue'
     possui apenas 3 posições.
     @return  retorna um objeto da posição 2.
     */
    Object getThirdElement()
    {
        return this.elem[2];
    }

    /**
     Redimensiona o Array afim de aumentar o tamanho da 'queue'. Ao preencher um objeto novo 
     com os mesmmos elementos e tamanho da 'queue', a fila anterior recebe 
     o mesmo endereço de memória contido em novo; ou seja, passa a conter o mesmo tamanho 
     e mesmo valor de cada item do Array.
     @param porct valor em porcentagem que representará o tamanho do vetor.
     */
    private void redimensioneSe (float porct)
    {
        Object[] novo2 = new Object [(int) Math.ceil(this.elem.length * porct)];

        for (int i=0; i<this.elem.length; i++)
            novo2[i] = this.elem[i];

        this.elem = novo2;
    }

    /**
     Representa o item do primeiro índice, substituindo o endereço de memória de um objeto da classe
     pelo conteúdo da primeira posição.
     @return retorna uma String.
     */
    @Override
    public String toString()
    {
        String first = "";
        first  += this.elem[0];
        return first;
    }


    /**
     Verifica se dois objetos de mesma classe são iguais, ou seja, se em duas 'queues' 
     cada valor dentro do Array é o mesmo em cada posição equivalente.
     @return retorna um boolean.
     @param obj objeto que será comparado na chamada do método e virá como parâmetro.
     */
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj) return true;
        if(this == null) return false;


        if(this.getClass() != obj.getClass()) return false;

        Fila fila = (Fila)obj;

        for(int i = 0; i < this.elem.length; i++)
        {
            if(this.elem[i] != fila.elem[i]) return false;
        }

        return true;
    }


    /**
     Criando 'Hashcode'. Reimplementando método obrigatório.
     @return  retorna um inteiro.
     */
    public int Hashcode()
    {
        int val = 666;
        val = 13 * val + Arrays.hashCode(this.elem);
        if(val <0) val = -val;
        return val;
    }

    /**
     Construtor de cópia para se fazer cópias de 'queues'. Reimplementando método obrigatório.
     @param algo parâmetro para se fazer cópias, ao chamar o método 'clone()' para algum objeto.
     @throws Exception caso um objeto da mesma classe esteja vazio.
     */
    public Fila(Fila<X> algo) throws Exception
    {
        if(algo == null) throw new Exception("Vazio!");
        this.contador = algo.contador;
        this.index =algo.index;
        this.elem = algo.elem;
    }
}


