package com.alex44.fcbate.home.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.ToxicBakery.viewpager.transforms.ZoomOutSlideTransformer;
import com.alex44.fcbate.R;
import com.alex44.fcbate.home.model.dto.TournamentInfoDTO;
import com.alex44.fcbate.home.presenter.HomePresenter;
import com.alex44.fcbate.home.view.HomeView;
import com.alex44.fcbate.utils.model.ISystemInfo;
import com.alex44.fcbate.utils.ui.SystemInfo;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class HomeFragment extends MvpAppCompatFragment implements HomeView {

    private View view;
    private Unbinder unbinder;

    private ISystemInfo systemInfo = new SystemInfo();

    @InjectPresenter
    HomePresenter presenter;

    @BindView(R.id.home_pager)
    protected ViewPager pager;
    @BindView(R.id.home_news_pager)
    protected ViewPager newsPager;
    
    @BindView(R.id.home_table_pos_1)
    protected TextView tablePos1;
    @BindView(R.id.home_table_team_1)
    protected TextView tableTeam1;
    @BindView(R.id.home_table_games_1)
    protected TextView tableGames1;
    @BindView(R.id.home_table_diffs_1)
    protected TextView tableDiffs1;
    @BindView(R.id.home_table_points_1)
    protected TextView tablePoints1;

    @BindView(R.id.home_table_pos_2)
    protected TextView tablePos2;
    @BindView(R.id.home_table_team_2)
    protected TextView tableTeam2;
    @BindView(R.id.home_table_games_2)
    protected TextView tableGames2;
    @BindView(R.id.home_table_diffs_2)
    protected TextView tableDiffs2;
    @BindView(R.id.home_table_points_2)
    protected TextView tablePoints2;

    @BindView(R.id.home_table_pos_3)
    protected TextView tablePos3;
    @BindView(R.id.home_table_team_3)
    protected TextView tableTeam3;
    @BindView(R.id.home_table_games_3)
    protected TextView tableGames3;
    @BindView(R.id.home_table_diffs_3)
    protected TextView tableDiffs3;
    @BindView(R.id.home_table_points_3)
    protected TextView tablePoints3;

    @BindView(R.id.home_facebook_button)
    protected ImageButton facebookButton;
    @BindView(R.id.home_instagram_button)
    protected ImageButton instagramButton;
    @BindView(R.id.home_twitter_button)
    protected ImageButton twitterButton;
    @BindView(R.id.home_vk_button)
    protected ImageButton vkButton;
    @BindView(R.id.home_youtube_button)
    protected ImageButton youtubeButton;
    @BindView(R.id.home_viber_button)
    protected ImageButton viberButton;

    @BindView(R.id.home_table)
    protected TableLayout homeTable;

    @ProvidePresenter
    protected HomePresenter createPresenter() {
        return new HomePresenter(AndroidSchedulers.mainThread(), systemInfo);
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void initMatchPager() {
        final HomePagerAdapter pagerAdapter = new HomePagerAdapter(getChildFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        pagerAdapter.addFragment(new MatchItemFragment(0, presenter.getMatchItemPresenter()), "Match 1");
        pagerAdapter.addFragment(new MatchItemFragment(1, presenter.getMatchItemPresenter()), "Match 2");
        pagerAdapter.addFragment(new MatchItemFragment(2, presenter.getMatchItemPresenter()), "Match 3");
        pagerAdapter.addFragment(new MatchItemFragment(3, presenter.getMatchItemPresenter()), "Match 4");
        pagerAdapter.addFragment(new MatchItemFragment(4, presenter.getMatchItemPresenter()), "Match 5");

        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(2);
        pager.setPageTransformer(true, new ZoomOutSlideTransformer());
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            boolean lastPage = true;
            boolean lastPageDragEnabled = false;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (lastPage && position == 4 && lastPageDragEnabled) {
                    lastPage = false;
                    presenter.goToCalendarScreen();
                }
            }

            @Override
            public void onPageSelected(int position) {
                lastPage = position == 4;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (lastPage) {
                    lastPageDragEnabled = state == ViewPager.SCROLL_STATE_DRAGGING;
                }
                else {
                    lastPageDragEnabled = false;
                }
            }
        });
    }

    @Override
    public void initNewsPager() {
        final HomePagerAdapter pagerAdapter = new HomePagerAdapter(getChildFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        pagerAdapter.addFragment(new NewsItemFragment(0, presenter.getNewsItemPresenter()), "News 1");
        pagerAdapter.addFragment(new NewsItemFragment(1, presenter.getNewsItemPresenter()), "News 2");
        pagerAdapter.addFragment(new NewsItemFragment(2, presenter.getNewsItemPresenter()), "News 3");
        pagerAdapter.addFragment(new NewsItemFragment(3, presenter.getNewsItemPresenter()), "News 4");
        pagerAdapter.addFragment(new NewsItemFragment(4, presenter.getNewsItemPresenter()), "News 5");

        newsPager.setAdapter(pagerAdapter);
        newsPager.setPageTransformer(true, new ZoomOutSlideTransformer());
        newsPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            boolean lastPage = true;
            boolean lastPageDragEnabled = false;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (lastPage && position == 4 && lastPageDragEnabled) {
                    lastPage = false;
                    presenter.goToNewsScreen();
                }
            }

            @Override
            public void onPageSelected(int position) {
                lastPage = position == 4;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (lastPage) {
                    lastPageDragEnabled = state == ViewPager.SCROLL_STATE_DRAGGING;
                }
                else {
                    lastPageDragEnabled = false;
                }
            }
        });
    }

    @Override
    public void initTable() {
        homeTable.setOnTouchListener(new View.OnTouchListener() {
            private Float x = null;
            private Float y = null;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x = event.getX();
                        y = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if (x != null && y != null) {
                            final Float deltaX = Math.abs(x - event.getX());
                            final Float deltaY = Math.abs(y - event.getY());
                            final WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
                            DisplayMetrics metrics = new DisplayMetrics();
                            wm.getDefaultDisplay().getMetrics(metrics);
                            Timber.d("Check: "+deltaX + " "+deltaY+" "+metrics.widthPixels);
                            if (deltaX > deltaY && deltaX > 0 && deltaX > metrics.widthPixels*0.5) {
                                presenter.goToTournamentScreen();
                            }

                        }
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        Timber.d("Cancel");
                        x = null;
                        y = null;
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void fillTable(List<TournamentInfoDTO> infoList) {
        tablePos1.setText(String.valueOf(infoList.get(0).getPosition()));
        tableTeam1.setText(infoList.get(0).getTeamName());
        tableGames1.setText(String.valueOf(infoList.get(0).getGames()));
        tableDiffs1.setText(infoList.get(0).getDiffs());
        tablePoints1.setText(String.valueOf(infoList.get(0).getPoints()));

        tablePos2.setText(String.valueOf(infoList.get(1).getPosition()));
        tableTeam2.setText(infoList.get(1).getTeamName());
        tableGames2.setText(String.valueOf(infoList.get(1).getGames()));
        tableDiffs2.setText(infoList.get(1).getDiffs());
        tablePoints2.setText(String.valueOf(infoList.get(1).getPoints()));

        tablePos3.setText(String.valueOf(infoList.get(2).getPosition()));
        tableTeam3.setText(infoList.get(2).getTeamName());
        tableGames3.setText(String.valueOf(infoList.get(2).getGames()));
        tableDiffs3.setText(infoList.get(2).getDiffs());
        tablePoints3.setText(String.valueOf(infoList.get(2).getPoints()));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.home_facebook_button)
    public void facebookClick() {
        final Snackbar snackbar = Snackbar.make(view, "Facebook Button", Snackbar.LENGTH_SHORT);
        snackbar.setAction("Ok", v -> snackbar.dismiss());
        snackbar.show();
    }

    @OnClick(R.id.home_instagram_button)
    public void instagramClick() {
        final Snackbar snackbar = Snackbar.make(view, "Instagram Button", Snackbar.LENGTH_SHORT);
        snackbar.setAction("Ok", v -> snackbar.dismiss());
        snackbar.show();
    }

    @OnClick(R.id.home_twitter_button)
    public void twitterClick() {
        final Snackbar snackbar = Snackbar.make(view, "Twitter Button", Snackbar.LENGTH_SHORT);
        snackbar.setAction("Ok", v -> snackbar.dismiss());
        snackbar.show();
    }

    @OnClick(R.id.home_vk_button)
    public void vkClick() {
        final Snackbar snackbar = Snackbar.make(view, "VK Button", Snackbar.LENGTH_SHORT);
        snackbar.setAction("Ok", v -> snackbar.dismiss());
        snackbar.show();
    }

    @OnClick(R.id.home_youtube_button)
    public void youtubeClick() {
        final Snackbar snackbar = Snackbar.make(view, "Youtube Button", Snackbar.LENGTH_SHORT);
        snackbar.setAction("Ok", v -> snackbar.dismiss());
        snackbar.show();
    }

    @OnClick(R.id.home_viber_button)
    public void viberClick() {
        final Snackbar snackbar = Snackbar.make(view, "Viber Button", Snackbar.LENGTH_SHORT);
        snackbar.setAction("Ok", v -> snackbar.dismiss());
        snackbar.show();
    }

}
