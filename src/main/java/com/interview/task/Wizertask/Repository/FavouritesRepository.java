package com.interview.task.Wizertask.Repository;

import com.interview.task.Wizertask.Entity.Favourites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouritesRepository extends JpaRepository<Favourites, Long> {
}
