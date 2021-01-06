package com.projettransversal.api.Services.IServices;

import com.projettransversal.api.Exception.MapItemNotFoundException;
import com.projettransversal.api.Models.Barrack;
import com.projettransversal.api.Models.ViewModels.BarrackViewModel;

public interface IBarrackService extends ICrudService<Barrack> {

    Barrack create(BarrackViewModel barrackVM) throws MapItemNotFoundException;
}
