package com.interview.task.Wizertask.Service;

import com.interview.task.Wizertask.Entity.Favourites;
import com.interview.task.Wizertask.Repository.FavouritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteServiceImpl implements FavouriteService {

    @Autowired
    private FavouritesRepository favouritesRepository;
    @Override
    public void addFavourites(Favourites favourites) {

        favouritesRepository.save(favourites);
    }

    @Override
    public List<Favourites> getFavourites() {
        return favouritesRepository.findAll();
    }

    @Override
    public void deleteBook(Long id) {
        favouritesRepository.deleteById(id);
    }
}
