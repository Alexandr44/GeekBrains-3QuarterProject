package com.alex44.fcbate.home.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.ViewPager;

import com.ToxicBakery.viewpager.transforms.TabletTransformer;
import com.alex44.fcbate.R;
import com.alex44.fcbate.home.presenter.HomePresenter;
import com.alex44.fcbate.home.view.HomeView;
import com.alex44.fcbate.utils.model.ISystemInfo;
import com.alex44.fcbate.utils.ui.SystemInfo;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class HomeFragment extends MvpAppCompatFragment implements HomeView {

    private View view;
    private Unbinder unbinder;

    private MatchPagerAdapter pagerAdapter;
    private ISystemInfo systemInfo = new SystemInfo();

    @InjectPresenter
    HomePresenter presenter;

    @BindView(R.id.home_pager)
    protected ViewPager pager;

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
        pagerAdapter = new MatchPagerAdapter(getChildFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        pagerAdapter.addFragment(new MatchItemFragment(0, presenter.getMatchItemPresenter()), "Match 1");
        pagerAdapter.addFragment(new MatchItemFragment(1, presenter.getMatchItemPresenter()), "Match 2");
        pagerAdapter.addFragment(new MatchItemFragment(2, presenter.getMatchItemPresenter()), "Match 3");
        pagerAdapter.addFragment(new MatchItemFragment(3, presenter.getMatchItemPresenter()), "Match 4");
        pagerAdapter.addFragment(new MatchItemFragment(4, presenter.getMatchItemPresenter()), "Match 5");

        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(2);
        pager.setPageTransformer(true, new TabletTransformer());
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

}