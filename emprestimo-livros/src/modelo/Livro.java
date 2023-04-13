package modelo;

public class Livro {

    private String titulo;
    private String isbn;
    private String editora;
    private String autor;

    public Livro(String titulo, String isbn, String editora, String autor) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.editora = editora;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getEditora() {
        return editora;
    }

    public String getAutor() {
        return autor;
    }

}
