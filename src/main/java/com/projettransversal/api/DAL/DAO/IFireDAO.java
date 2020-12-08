package com.projettransversal.api.DAL.DAO;

import com.projettransversal.api.Models.Fire;

import java.util.List;
import java.util.Optional;

public interface IFireDAO {
    public List<Fire> findAll();
    public Optional<Fire> findById(int id);
    public Fire save(Fire fire);
}
