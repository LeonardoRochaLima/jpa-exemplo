import entities.Product;

import javax.naming.Name;
import javax.persistence.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpa-lab3-aula");

        EntityManager em = emf.createEntityManager();

        Product p = new Product();
        p.setName("Tenis");
        p.setDescription("Tenis de corrida");


        Query query = em.createQuery("SELECT name, description FROM Product");
        List<String> results = query.getResultList();
        //TypedQuery<Product> q2 = em.createQuery("SELECT name, description FROM Product", Product.class);

        em.persist(p);

        em.persist(query);

        System.out.println(results);
        //em.persist(q2);

        em.close();
        emf.close();
    }

}
