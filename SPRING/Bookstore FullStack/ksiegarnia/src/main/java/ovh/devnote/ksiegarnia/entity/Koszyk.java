package ovh.devnote.ksiegarnia.entity;

import javax.persistence.*;

@Entity
@Table(name = "koszyk")
public class Koszyk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column (name = "ksiazka_id")
    private int ksiazka_id;
    @Column(name = "username")
    private String username;
    @Column(name="nazwa")
    private String nazwa;
    @Column(name="kategoria")
    private String kategoria;
    @Column(name="cena")
    private float cena;
    @Column(name = "data")
    private String data;

//    @Temporal(TemporalType.DATE)
//    private Date data;




    public Koszyk(){}

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public Koszyk(String id, int ksiazka_id, String username) {
        this.id = Integer.parseInt(id);
        this.ksiazka_id = ksiazka_id;
        this.username = username;


    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKsiazka_id() {
        return ksiazka_id;
    }

    public void setKsiazka_id(int ksiazka_id) {
        this.ksiazka_id = ksiazka_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public String toString() {
        return "KOSZYK{" +
                "id=" + id +
                ", ksiazka_id='" + ksiazka_id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
