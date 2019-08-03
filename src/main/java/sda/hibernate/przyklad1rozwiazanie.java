/*
 * przyklad pokazuje jak rozwiazywac problem n+1 select*/
package sda.hibernate;

import org.hibernate.Session;
import sda.hibernate.entity.Klient;
import sda.hibernate.entity.Umowa;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class przyklad1rozwiazanie {
    public static void main(String[] args) {
        insertData();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

            List<Klient> klienci = session.createQuery("SELECT DISTINCT k FROM Klient k JOIN FETCH k.umowy", Klient.class).getResultList();
        for (Klient klient : klienci) {
            System.out.println("Znaleziono klienta: " + klient.getImie() + " " + klient.getNazwisko());

            for (Umowa umowaDB : klient.getUmowy()) {
                System.out.println("Tytul umowy: " + umowaDB.getTytul());
            }
        }

        session.close();
    }

    public static void insertData() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String imiona[] = {"Adam", "Ewa", "Stanisla", "Szymon", "Ola", "Ala", "Robert", "Marcin", "Karolina", "Sylwia", "Ewelina", "Kamil", "Sandra", "Wojtek", "Katarzyna", "Slawek"};
        String nazwiska[] = {"Nowak", "Kowalski", "Mickiewicz", "Slowacki", "Duda", "Trampek", "Wilk", "Szybki", "Wesoly", "Smutny", "Mily", "Nowy", "Maly", "Smialy", "Bolo", "Madry"};

        for (int i = 0; i < imiona.length; i++) {
            Klient klinet = new Klient(imiona[i], nazwiska[i]);
            session.persist(klinet);
            for (int j = 1; j < 4; j++) {
                Umowa umowa = new Umowa("umowa" + j, Date.valueOf(LocalDate.now()), klinet);
                session.persist(umowa);
            }
        }
        session.flush();
        session.close();

        session = HibernateUtil.getSessionFactory().openSession();
    }
}
