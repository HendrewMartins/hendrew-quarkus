package br.hendrew.quarkus.entity;

public class AlunosEndereco_Auxiliar {
   
    private long sequencia;

    private String tipo;

    private String logradouro;

    private String numero;

    private String cep;

    private String bairro;

    private String complemento;


    public AlunosEndereco_Auxiliar() {
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

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Override
    public String toString() {
        return "AlunosEndereco_Auxiliar [bairro=" + bairro + ", cep=" + cep + ", complemento=" + complemento
                + ", logradouro=" + logradouro + ", numero=" + numero + ", sequencia=" + sequencia + ", tipo=" + tipo
                + "]";
    }
    
}
