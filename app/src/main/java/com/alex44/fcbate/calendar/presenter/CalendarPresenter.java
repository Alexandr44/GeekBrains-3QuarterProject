package com.alex44.fcbate.calendar.presenter;

import com.alex44.fcbate.calendar.view.CalendarView;
import com.alex44.fcbate.common.navigation.Screens;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class CalendarPresenter extends MvpPresenter<CalendarView> {

    @Inject
    protected Router router;

    private Scheduler mainThreadScheduler;

    public CalendarPresenter(Scheduler mainThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void backClick() {
        router.newRootScreen(new Screens.HomeScreen());
    }
}
