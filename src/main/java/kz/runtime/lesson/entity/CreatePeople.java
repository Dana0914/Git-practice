package kz.runtime.lesson.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Scanner;

public class CreatePeople {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();
        Scanner scan = new Scanner(System.in);
        try {
            manager.getTransaction().begin();
            System.out.println("Введите номер города: ");
            long id = scan.nextLong();
            scan.nextLine();
            City city = manager.find(City.class, id);
            if (city == null) {
                System.out.println("Сущность не найдена");
            } else {
                System.out.println("Введите имя человека: ");
                String input = scan.nextLine();
                People people = new People();
                TypedQuery<People> peopleQuery = manager.createQuery(
                        "from People p where p.name = name ", People.class
                );
                List<People> peoples = peopleQuery.getResultList();
                for (People p : peoples) {
                    while (true) {
                        if (p.getName().equals(input)) {
                            System.out.println("Человек с таким именем уже существует, повторите еще раз: ");
                            input = scan.nextLine();
                        } else {
                            people.setName(input);
                            people.setCity(city);
                            manager.persist(people);
                            break;
                        }
                    }
                }
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw new RuntimeException(e);
        }

        factory.close();
        manager.close();
        scan.close();
    }
}