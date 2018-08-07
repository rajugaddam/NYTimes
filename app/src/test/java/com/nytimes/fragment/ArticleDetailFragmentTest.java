package com.nytimes.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nytimes.NYTimesApplication;
import com.nytimes.NYTimesArticleList;
import com.nytimes.network.AppComponent;
import com.nytimes.utils.Constant;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.spy;


@RunWith(PowerMockRunner.class)
@PrepareForTest({NYTimesApplication.class, ArticleDetailFragment.class})
public class ArticleDetailFragmentTest {

    @Mock
    private LayoutInflater mockLayoutInflater;
    @Mock
    private ViewGroup mockViewGroup;
    @Mock
    private View mockInflatedViewProdRate;
    @Mock
    private NYTimesArticleList spyMainActivity;
    @Mock
    private ArticleDetailFragment spyArticlesDetailFragment;
    @Mock
    private Bundle mockBundle;
    @Mock
    private AppComponent appComponent;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        spyMainActivity = spy(new NYTimesArticleList());
        mockBundle.putString(Constant.BUNDLE_ARTICLE_URL,"");
    }
    @Test
    public void testOnCreateView() throws Exception {
        spyArticlesDetailFragment.onCreateView(mockLayoutInflater, mockViewGroup, mockBundle);
        spyArticlesDetailFragment.onCreate(mockBundle);
    }
}
