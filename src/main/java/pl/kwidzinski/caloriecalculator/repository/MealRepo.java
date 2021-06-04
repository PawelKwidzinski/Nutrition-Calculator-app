package pl.kwidzinski.caloriecalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.kwidzinski.caloriecalculator.model.Meal;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealRepo extends JpaRepository<Meal, Long> {

    List<Meal> findAllByDateBetweenOrderByDateAsc(LocalDate from, LocalDate to);

    @Query(nativeQuery = true, value = "select * from meals order by date desc")
    List<Meal> findAllOrderByDate();
}
