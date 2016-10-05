package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
@Transactional(readOnly=true, propagation = Propagation.SUPPORTS)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        User ref = em.getReference(User.class, userId);
        meal.setUser(ref);

        if(meal.isNew()) {
            em.persist(meal);
        }
        else {
            if(get(meal.getId(), userId) == null)
                return null;
            return em.merge(meal);
        }

        return meal;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Meal.DELETE).setParameter("id", id)
                .setParameter("user_id", userId).executeUpdate() != 0;
    }

    @Transactional
    public boolean deleteAll(int userId) {
        return em.createNamedQuery(Meal.DELETE).setParameter("user_id", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal result = null;
        try {
            result = em.createNamedQuery(Meal.GET, Meal.class).setParameter("id", id)
                    .setParameter("user_id", userId).getSingleResult();
        } catch (Exception e) {
            throw new NotFoundException("Not found entity with id: " + id + " and userId: " + userId);
        }
        return result;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.GET_ALL_SORTED, Meal.class).setParameter("user_id", userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(Meal.GET_BETWEEN, Meal.class)
                .setParameter("user_id", userId)
                .setParameter("start_date", startDate)
                .setParameter("end_date", endDate).getResultList();
    }
}