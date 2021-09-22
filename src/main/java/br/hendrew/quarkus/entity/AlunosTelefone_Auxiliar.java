package br.hendrew.quarkus.entity;

public class AlunosTelefone_Auxiliar {

    private long sequencia;

    private String tipo;

    private String numero;

    private String contato;


    public AlunosTelefone_Auxiliar() {
    }


    public long getSequencia() {
        return sequencia;
    }


    public void setSequencia(long sequencia) {
        this.sequencia = sequencia;
    }


    public String getTipo() {
        return tipo;
    }


    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public String getNumero() {
        return numero;
    }


    public void setNumero(String numero) {
        this.numero = numero;
    }


    public String getContato() {
        return contato;
    }


    public void setContato(String contato) {
        this.contato = contato;
    }


    @Override
    public String toString() {
        return "AlunosTelefone_Auxiliar [contato=" + contato + ", numero=" + numero + ", sequencia=" + sequencia
                + ", tipo=" + tipo + "]";
    }

    
    
}
