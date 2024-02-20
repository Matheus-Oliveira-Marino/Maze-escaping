import java.nio.channels.ClosedChannelException;
import java.util.Arrays;

public class Coordenada implements Cloneable
{

    /**
     Atributos que representam a coordenada X e Y do labirinto.
     */
    int x_Coordenada, y_Coordenada;


    /**
     Construtor da classe 'Coordenada', afim de manipular coordenadas.
     Armazena a linha e a coluna obtida da classe 'Labirinto' em,
     respetivamente, 'this.x_Coordenada' e 'this.y_coordenada'.
     @param linha coluna que representará a coordenada X.
     @param coluna coluna que representará a coordenada Y.
     */
    public Coordenada(int linha, int coluna)
    {
        this.x_Coordenada = linha;
        this.y_Coordenada = coluna;
    }

    /**
     Retorna a coordenada X.
     @return retorna um inteiro.
     */
    public int getX()
    {
        return x_Coordenada;
    }

    /**
     Armazena um parâmetro obtido em 'this.x_Coordenada'.
     @param x define ou altera o valor de X, caso seja chamado.
     */
    public void setX(byte x)
    {
        this.x_Coordenada =  x;
    }

    /**
     Retorna a coordenada Y.
     @return retorna um inteiro.
     */
    public int getY()
    {
        return y_Coordenada;
    }

    /**
     Armazena um parâmetro obtido em 'this.y_Coordenada'.
     @param y define ou altera o valor de y, caso seja chamado.
     */
    public void setY(byte y)
    {
        this.y_Coordenada = y;
    }

    /**
     Representa as coordenadas, substituindo o endereço de memória de um objeto da classe
     pela coordenada em si.
     @return retorna uma String.
     */
    @Override
    public String toString()
    {
        String coordenada = "Coordenada(";

        coordenada +=this.x_Coordenada + "," + this.y_Coordenada;
        return coordenada + ") ";
    }

    /**
     Verifica se dois objetos de mesma classe são iguais, ou seja, se duas coordenadas 
     são iguais ou distintas.
     @return retorna um boolean.
     @param obj objeto que será comparado na chamada do método e virá como parâmetro.
     */
    @Override
    public boolean equals(Object obj)
    {
        if(this==obj)
            return true;

        if(obj==null)
            return false;

        if(this.getClass()!=obj.getClass())
            return false;

        Coordenada coord = (Coordenada) obj;

        if(this.x_Coordenada != coord.x_Coordenada) return false;
        if(this.y_Coordenada != coord.y_Coordenada) return false;
        return true;
    }

    /**
     Criando 'Hashcode'. Reimplementando método obrigatório.
     @return  retorna um inteiro.
     */
    public int Hashcode()
    {
        int val = 666;
        val = 13 * val + Integer.hashCode(this.x_Coordenada);
        val = 13 * val + Integer.hashCode(this.y_Coordenada);
        if(val <0) val = -val;
        return val;
    }

    /**
     Construtor de cópia para se fazer cópias de coordenadas. Reimplementando método obrigatório.
     @param c parâmetro para se fazer cópias, ao chamar o método 'clone()' para algum objeto.
     @throws Exception caso um objeto da mesma classe não exista.
     */
    public Coordenada(Coordenada c) throws Exception
    {
        if (c ==null){
            throw new Exception("Modelo ausente");
        }
        this.x_Coordenada = c.x_Coordenada;
        this.y_Coordenada = c.y_Coordenada;

    }

    /**
     Criando uma cópia de uma coordenada. Reimplementando método obrigatório.
     @return retorna Object, por padrão.
     */
    @Override
    public Object clone() //Deve retornar um object
    {
        Coordenada ret = null;
        try
        {
            ret = new Coordenada(this);
        }
        catch (Exception erro)
        {}
        return ret;
    }
}

