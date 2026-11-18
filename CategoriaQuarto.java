package model;

public class CategoriaQuarto {

    private int id;
    private String nome;
    private int capacidadePessoas;
    private double precoBase;

    public CategoriaQuarto() { }

    public CategoriaQuarto(String nome, int capacidadePessoas, double precoBase) {
        this.nome = nome;
        this.capacidadePessoas = capacidadePessoas;
        this.precoBase = precoBase;
    }

    public CategoriaQuarto(int id, String nome, int capacidadePessoas, double precoBase) {
        this.id = id;
        this.nome = nome;
        this.capacidadePessoas = capacidadePessoas;
        this.precoBase = precoBase;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getCapacidadePessoas() { return capacidadePessoas; }
    public void setCapacidadePessoas(int capacidadePessoas) { this.capacidadePessoas = capacidadePessoas; }

    public double getPrecoBase() { return precoBase; }
    public void setPrecoBase(double precoBase) { this.precoBase = precoBase; }

    @Override
    public String toString() {
        return "CategoriaQuarto{id=" + id +
               ", nome='" + nome + '\'' +
               ", capacidadePessoas=" + capacidadePessoas +
               ", precoBase=" + precoBase + '}';
    }
}
