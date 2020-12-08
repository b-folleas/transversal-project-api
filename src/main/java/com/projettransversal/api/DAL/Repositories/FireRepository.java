package com.projettransversal.api.DAL.Repositories;

import com.projettransversal.api.DAL.Interfaces.IFireRepository;
import com.projettransversal.api.Models.Fire;
import com.projettransversal.api.Models.Ground;
import com.projettransversal.api.Models.MapItem;
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
