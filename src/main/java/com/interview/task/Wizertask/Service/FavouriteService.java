package com.interview.task.Wizertask.Service;

import com.interview.task.Wizertask.Entity.Favourites;

import java.util.List;

public interface FavouriteService {
    void addFavourites(Favourites favourites);

    List<Favourites> getFavourites();

    void deleteBook(Long id);
}
