package sda.hibernate.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "umowa")
public class Umowa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid= UUID.randomUUID().toString();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Umowa umowa = (Umowa) o;
        return uuid.equals(umowa.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Column
    private String tytul;
    @Column
    private Date dataPodpisania;

    @ManyToOne
    @JoinColumn(name = "klientID")
    private Klient klient;

    public Umowa() {
    }

    public Umowa(String tytul, Date dataPodpisania, Klient klient) {
        this.tytul = tytul;
        this.dataPodpisania = dataPodpisania;
        this.klient = klient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public Date getDataPodpisania() {
        return dataPodpisania;
    }

    public void setDataPodpisania(Date dataPodpisania) {
        this.dataPodpisania = dataPodpisania;
    }

}
