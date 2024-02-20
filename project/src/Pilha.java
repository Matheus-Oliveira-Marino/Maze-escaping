import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Pilha <X> implements Cloneable
{
    private Object[] elemento;
    private int ultimo=-1;

    private int contador;
    
    @SuppressWarnings("unused")
    private int tamanho;


    /**
     Construtor da 'stack', responsável por instanciar a pilha, recebendo como parâmetro um tamanho,
     vindo da 'main'.
     @param tamanho tamanho da 'stack', pré-definido como um Array.
     @throws  Exception lança exceção, caso 'tamanho' seja menor ou igual a 0.
     */
    public Pilha (int tamanho) throws Exception
    {
        if (tamanho<=0)
            throw new Exception ("Tamanho invalido");

        this.elemento=new Object [tamanho];
        this.tamanho=tamanho;
    }

    /**
     Redimensiona o Array afim de aumentar o tamanho da 'stack'. Ao preencher um objeto 'novo'
     com os mesmmos elementos e tamanho da 'stack', a fila anterior recebe o mesmo 
     endereço de memória contido no objeto 'novo'; ou seja, passa a conter o mesmo tamanho 
     e mesmo valor de cada ‘item’ do Array.
     @param porct valor em porcentagem que representará o tamanho do vetor.
     */
    private void redimensioneSe(float porct)
    {
        Object[] novo = new Object[(int) Math.ceil(this.elemento.length*porct)];

        for(int i = 0 ; i<this.elemento.length ; i++ )
        {
            novo[i]= this.elemento[i] ;
        }

        this.elemento = novo;
        this.tamanho = this.elemento.length;
    }

    /**
     Preenche a pilha fazendo inicialmente verificações importantes. Caso a pilha esteja cheia, 
     chama um método que expande o tamanho do vetor de pilha, passando um valor como parâmetro;

     Em seguida. Aumenta — se o 'this.ultimo', que representa um valor que será usado 
     para percorrer cada índice da queue' e, posteriormente, pegar o último elemento 
     da última posição. Por fim, é verificado se o objeto recebido como parâmetro 
     pode ser clonável, armazenando uma cópia na 'stack' e aumentando 'contador'.

     Em caso negativo, ainda armazena 'x' em 'this.elem' e aumenta, também, 'contador'.
     @param x parâmetro que vem da 'main', para adicionar valores na 'stack'.
     @throws  Exception lança exceção, caso um valor chamado 'x' seja null.
     @throws Exception caso o objeto recebido como parâmetro da classe X seja nulo.
     */
    public void guardeUmItem (X x) throws Exception
    {
        if (x==null)
            throw new Exception ("Falta o que guardar");

        if (this.isCheia())
            this.redimensioneSe (2.0F);

        this.ultimo++;

        if (x instanceof Cloneable)
        {
            this.elemento[this.ultimo] = meuCloneDeX(x);
            contador++;
        }
        else
        {
            this.elemento[this.ultimo] = x;
            contador++;
        }

    }

    /**
     Inicialmente, verifica se 'this.ultimo' é igual a -1, pois não existe indice negativo, 
     lançando exceção.
     Caso o elemento contido em dada posição seja clonável, é armazenado uma cópia 
     desse objeto armazenado.
     Do contrário, retorna apenas o objeto da tipagem especificada pela classe genérica.
     @return retorna um objeto da tipagem especificada pela classe genérica.
     @throws Exception caso a variável usada para percorrer os índices do vetor seja nula.
     */
    @SuppressWarnings("unchecked")
    public X recupereUmItem () throws Exception
    {
        if (this.ultimo==-1)
            throw new Exception ("Nada a recuperar");

        X ret=null;
        if (this.elemento[this.ultimo] instanceof Cloneable)
            ret = meuCloneDeX((X)this.elemento[this.ultimo]);
        else
            ret = (X)this.elemento[this.ultimo];

        return ret;
    }

    /**
     Remove o último elemento da 'stack'. Em seguida, diminui o contador que pega o último elemento.
     @throws  Exception caso o contador seja menor que 0, indicando uma posição negativa,
     inexistente em um Array.
     */
    public void removaUmItem () throws Exception
    {
        if (this.ultimo==-1)
            throw new Exception ("Nada a remover");

        this.elemento[this.ultimo] = null;
        this.ultimo--;

    }

    /**
     Verifica se a 'stack' está cheia ou não.
     @return  retorna um valor booleano.
     */
    public boolean isCheia ()
    {
        if(this.ultimo+1==this.elemento.length)
            return true;

        return false;
    }

    /**
     Verifica se a 'stack' está vazia ou não.
     @return  retorna um valor booleano.
     */
    public boolean isVazia ()
    {
        if(this.ultimo==-1)
            return true;

        return false;
    }

    /**
     Reimplementando método obrigatório, visto que o clone não funciona para classes genéricas,
     pois os valores da 'queue' podem ser alterados quando, também, o valor de um objeto da classe X
     for alterado, este que também tem seu valor armazenado na 'queue'.
     @param x parâmetro para cópia.
     @return retorna um objeto da classe genérica,
     */
    @SuppressWarnings("unchecked")
    private X meuCloneDeX(X x)
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
     Construtor de cópia para se fazer cópias de 'stacks'. Reimplementando método obrigatório.
     @param modelo parâmetro para se fazer cópias, ao chamar o método 'clone()' para algum objeto.
     @throws Exception caso um objeto da mesma classe seja nulo.
     */
    public Pilha(Pilha<X> modelo) throws Exception
    {
        if(modelo == null)
            throw new Exception("modelo ausente");

        this.ultimo = modelo.ultimo;

        this.elemento = new Object[modelo.elemento.length];

        for(int i=0 ; i<modelo.elemento.length ; i++)
            this.elemento[i] = modelo.elemento[i]  ;
    }

    /**
     Representa o ‘item’ do último índice, substituindo o endereço de memória de um objeto da classe
     pelo conteúdo da última posição. Pega a quantidade de elementos da 'stack' de acordo com
     um contador de elementos. Depois, verifica se 'this.ultimo' difere de -1. Em caso afirmativo,
     pega o último elemento da 'pilha' na última posição.
     @return retorna uma String.
     */
    public String toString()
    {
        String ret = this.contador + " elemento(s), ";

        if (this.ultimo!=-1)
            ret += ", sendo o ultimo: "+this.elemento[this.ultimo];


        return ret;
    }

    /**
     Verifica se dois objetos de mesma classe são iguais, ou seja, se em duas 'stacks' 
     cada valor dentro do 'Array' é o mesmo em cada posição equivalente.
     @return retorna um boolean.
     @param obj objeto que será comparado na chamada do método e virá como parâmetro.
     */
    @SuppressWarnings("unchecked")
    public boolean equals (Object obj)
    {
        if(this==obj)
            return true;

        if(obj==null)
            return false;

        if(this.getClass()!=obj.getClass())
            return false;

        Pilha<X> pil = (Pilha<X>) obj;

        if(this.ultimo!=pil.ultimo)
            return false;

        for(int i=0 ; i<this.ultimo;i++)
            if(!this.elemento[i].equals (pil.elemento[i]))
                return false;

        return true;
    }

    /**
     Criando 'Hashcode'. Reimplementando método obrigatório.
     @return  retorna um inteiro.
     */
    public int hashCode ()
    {
        int ret=666;

        ret = ret*7 + Arrays.hashCode(this.elemento);

        for (int i=0; i<this.ultimo; i++)
            ret = ret*7 + this.elemento[i].hashCode();

        if (ret<0)
            ret=-ret;

        return ret;
    }
}
