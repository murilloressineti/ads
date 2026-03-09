package _Aula2_ED;

public class Veiculo {
    private String placa, marca, modelo; 
    private int ano; 
    private float preco;
    private String tipo;

    public Veiculo () {
        placa="-"; marca="-"; modelo="-";
        ano=0; preco=0.0f; tipo="-";
    }

    public Veiculo (String placa, String marca, String modelo,
                    int ano, float preco, String tipo) {
        setPlaca(placa);
        setMarca(marca); 
        setModelo(modelo);
        setAno(ano); 
        setPreco(preco); 
        setTipo(tipo);
    }
    
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public int getAno() { return ano; }
    public void setAno(int ano) {
        this.ano = 0;
        if(ano > 1900) this.ano = ano;
    }
    public float getPreco() { return preco; }
    public void setPreco(float preco) {
        this.preco = 0.0f;
        if(preco > 0) this.preco = preco;
    }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    @Override
    public String toString() {
        return (getPlaca() 
                + ",  " + getMarca()  
                + ",  " + getModelo()  
                + ",  " + getAno() 
                + ",  R$ " + String.format("%12.2f", getPreco()) 
                + ",  " + getTipo() + "." );
    }
}