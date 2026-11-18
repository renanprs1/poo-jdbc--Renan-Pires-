package model;

public class Quarto {

    private int id;
    private int numero;
    private int andar;
    private String status;
    private CategoriaQuarto categoria;   // <-- associação com o lado "1"

    public Quarto() { }

    public Quarto(int numero, int andar, String status, CategoriaQuarto categoria) {
        this.numero = numero;
        this.andar = andar;
        this.status = status;
        this.categoria = categoria;
    }

    public Quarto(int id, int numero, int andar, String status, CategoriaQuarto categoria) {
        this.id = id;
        this.numero = numero;
        this.andar = andar;
        this.status = status;
        this.categoria = categoria;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public int getAndar() { return andar; }
    public void setAndar(int andar) { this.andar = andar; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public CategoriaQuarto getCategoria() { return categoria; }
    public void setCategoria(CategoriaQuarto categoria) { this.categoria = categoria; }

    @Override
    public String toString() {
        return "Quarto{id=" + id +
               ", numero=" + numero +
               ", andar=" + andar +
               ", status='" + status + '\'' +
               ", categoria=" + (categoria != null ? categoria.getNome() : "null") + '}';
    }
}
