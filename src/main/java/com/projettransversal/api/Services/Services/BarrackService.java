package com.projettransversal.api.Services.Services;

import com.projettransversal.api.Exception.MapItemNotFoundException;
import com.projettransversal.api.Models.Barrack;
import com.projettransversal.api.Models.ViewModels.BarrackViewModel;
import com.projettransversal.api.ProjetTransversalExceptionEnum;
import com.projettransversal.api.Repositories.BarrackRepository;
import com.projettransversal.api.Services.IServices.IBarrackService;
import com.projettransversal.api.Services.IServices.IMapItemService;
import org.springframework.stereotype.Service;

@Service
public class BarrackService extends CrudService<Barrack> implements IBarrackService {

    private final IMapItemService iMapItemService;

    public BarrackService(BarrackRepository barrackRepository, IMapItemService iMapItemService) {
        super(barrackRepository);
        this.iMapItemService = iMapItemService;
    }

    @Override
    public Barrack create(BarrackViewModel barrackVM) throws MapItemNotFoundException {
        Barrack barrack = barrackVM.toModel(this.iMapItemService).orElseThrow(() -> new MapItemNotFoundException(ProjetTransversalExceptionEnum.MAPITEM_NOT_FOUND, barrackVM.getPosX(), barrackVM.getPosY()));
        return this.insertOrUpdate(barrack);
    }
}
