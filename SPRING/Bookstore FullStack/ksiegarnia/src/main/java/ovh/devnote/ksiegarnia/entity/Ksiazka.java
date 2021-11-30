package ovh.devnote.ksiegarnia.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "ksiazki")
public class Ksiazka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "nazwa")
    private String nazwa;
    @Column(name = "wydawnictwo")
    private String wydawnictwo;
    @Column(name = "cena")
    private float cena;
    @Column(name = "ilosc")
    private int ilosc;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "autorzy_to_ksiazki",
            joinColumns = @JoinColumn(name = "ksiazka_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private Set<Autor> autorzy;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "kategoria_id")
    private Kategoria kategoria;


    public Ksiazka() { }

    public Ksiazka(String id,String nazwa, String wydawnictwo, float cena, int ilosc) {
        this.id = Integer.parseInt(id);
        this.nazwa = nazwa;
        this.wydawnictwo = wydawnictwo;
        this.cena = cena;
        this.ilosc = ilosc;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNazwa() {
        return nazwa;
    }
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    public String getWydawnictwo() {
        return wydawnictwo;
    }
    public void setWydawnictwo(String wydawnictwo) {
        this.wydawnictwo = wydawnictwo;
    }
    public float getCena() {
        return cena;
    }
    public void setCena(float cena) {
        this.cena = cena;
    }
    public Set<Autor> getAutorzy() {
        return autorzy;
    }
    public void setAutorzy(Set<Autor> autorzy) {
        this.autorzy = autorzy;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = Integer.parseInt(String.valueOf(ilosc));
    }

    public Kategoria getKategoria() {
        return kategoria;
    }
    public void setKategoria(Kategoria kategoria) {
        this.kategoria = kategoria;
    }



    public void addAutor(Autor autor) {
        if (autorzy == null)
            autorzy = new HashSet<>();
        autorzy.add(autor);
    }
    public void removeAutor(Autor autor) {
        if (autorzy == null)
            return;
        autorzy.remove(autor);
    }

    @Override
    public String toString() {
        return "Ksiazka{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", wydawnictwo='" + wydawnictwo + '\'' +
                ", cena=" + cena +
                ", kategoria=" + kategoria +
                ", ilosc=" + ilosc +
                '}';
    }
}