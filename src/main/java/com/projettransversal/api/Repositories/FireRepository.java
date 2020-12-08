package com.projettransversal.api.Repositories;

import com.projettransversal.api.DAO.FireDAO;
import com.projettransversal.api.DAO.IFireDAO;
import com.projettransversal.api.IRepositories.IFireRepository;
import com.projettransversal.api.models.Fire;
import com.projettransversal.api.models.Ground;
import com.projettransversal.api.models.MapItem;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FireRepository implements IFireRepository {
    public static List<Fire> fires = new ArrayList<Fire>();

    static {
        fires.add(new Fire(1, new MapItem(1,1, Ground.BUILDING), 350));
        fires.add(new Fire(2, new MapItem(1,1, Ground.BUILDING), 500));
        fires.add(new Fire(3, new MapItem(1,1, Ground.BUILDING), 750));
    }

    @Override
    public List<Fire>findAll() {
        return fires;
    }

    @Override
    public Optional<Fire> findById(int id) {
        return fires
                .stream()
                .filter(f -> f.getId() == id)
                .findFirst();
    }

    @Override
    public Fire save(Fire product) {
        fires.add(product);
        return product;
    }
}
