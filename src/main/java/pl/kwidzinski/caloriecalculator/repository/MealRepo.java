package pl.kwidzinski.caloriecalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.kwidzinski.caloriecalculator.model.Meal;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealRepo extends JpaRepository<Meal, Long> {

    @Query("from Meal m where m.user.id=:userId and m.date between :start and :to order by m.date")
    List<Meal> findAllByDateBetweenOrderByDateAsc(Long userId, LocalDate start, LocalDate to);

    @Query("from Meal m where m.user.id=:userId order by m.date")
    List<Meal> findAllOrderByDate(Long userId);
}
