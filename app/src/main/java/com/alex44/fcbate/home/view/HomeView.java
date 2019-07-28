package com.alex44.fcbate.home.view;

import com.alex44.fcbate.home.model.dto.TournamentInfoDTO;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface HomeView extends MvpView {

    void initMatchPager();

    void initNewsPager();

    void initTable();

    void fillTable(List<TournamentInfoDTO> infoList);

    void showMessage(String message);
}
