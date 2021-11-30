package ovh.devnote.ksiegarnia.entity;

import javax.persistence.*;

@Entity
@Table(name = "zamowione")
public class Zamowione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
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
    @Column(name = "status")
    private String status;

    public Zamowione(){}

    public Zamowione(int id, String username, String nazwa, String kategoria, float cena, String data, String status) {
        this.id = id;
        this.username = username;
        this.nazwa = nazwa;
        this.kategoria = kategoria;
        this.cena = cena;
        this.data = data;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Zamowione{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nazwa='" + nazwa + '\'' +
                ", kategoria='" + kategoria + '\'' +
                ", cena=" + cena +
                ", data='" + data + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
