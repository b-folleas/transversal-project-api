package com.projettransversal.api.Services.Services;

import com.projettransversal.api.Models.Barrack;
import com.projettransversal.api.Repositories.BarrackRepository;
import com.projettransversal.api.Services.IServices.IBarrackService;
import org.springframework.stereotype.Service;

@Service
public class BarrackService extends CrudService<Barrack> implements IBarrackService {
    public BarrackService(BarrackRepository barrackRepository) {
        super(barrackRepository);
    }
}
