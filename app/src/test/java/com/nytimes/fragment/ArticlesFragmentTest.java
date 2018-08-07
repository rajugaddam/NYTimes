package com.nytimes.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nytimes.NYTimesApplication;
import com.nytimes.NYTimesArticleList;
import com.nytimes.R;
import com.nytimes.network.AppComponent;
import com.nytimes.network.NetworkService;
import com.nytimes.network.Service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doReturn;

@RunWith(PowerMockRunner.class)
@PrepareForTest({NYTimesApplication.class, ArticlesFragment.class, NYTimesArticleList.class
        , ProgressDialog.class, Service.class, NetworkService.class})
public class ArticlesFragmentTest {

    @Mock
    private LayoutInflater mockLayoutInflater;

    @Mock
    private LayoutInflater mockLayoutInflaterProdRate;

    @Mock
    private ViewGroup mockViewGroup;

    @Mock
    private View mockInflatedView;

    @Mock
    private View mockInflatedViewProdRate;

    @Mock
    private NYTimesArticleList spyMainActivity;

    @Mock
    private NYTimesApplication mockApplication;

    @Mock
    private ArticlesFragment spyArticlesFragment;

    @Mock
    private RecyclerView recyclerView;

    @Mock
    private Bundle mockBundle;

    @Mock
    private FragmentManager mockFragmentManager;

    @Mock
    private FragmentTransaction mockFragmentTransaction;

    @Mock
    private Service service;

    @Mock
    private NetworkService networkService;

    @Mock
    private NYTimesApplication application;

    @Mock
    private AppComponent appComponent;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        spyMainActivity = spy(new NYTimesArticleList());
        spyArticlesFragment = spy(ArticlesFragment.class);
        service = new Service(networkService);

        doReturn(spyMainActivity).when(spyArticlesFragment).getActivity();
        doReturn(mockApplication).when(spyMainActivity).getApplication();
        doReturn(appComponent).when(mockApplication).getAppComponent();

        doReturn(mockLayoutInflaterProdRate).when(spyMainActivity).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        doReturn(mockInflatedView).when(mockLayoutInflater).inflate(anyInt(), any(ViewGroup.class), anyBoolean());
        doReturn(mockInflatedViewProdRate).when(mockLayoutInflaterProdRate).inflate(anyInt(), any(ViewGroup.class));

        Mockito.doReturn(recyclerView).when(mockInflatedView).findViewById(R.id.recyclerView);

        Mockito.doReturn(mockFragmentManager).when(spyMainActivity).getFragmentManager();
        Mockito.doReturn(mockFragmentTransaction).when(mockFragmentManager).beginTransaction();
        Mockito.doReturn(mockFragmentTransaction).when(mockFragmentTransaction).add(anyInt(), any(Fragment.class), anyString());
        Mockito.doReturn(mockFragmentTransaction).when(mockFragmentTransaction).addToBackStack(anyString());
        Mockito.doReturn(0).when(mockFragmentTransaction).commit();
    }

    @Test
    public void testOnCreateView() throws Exception {
        spyArticlesFragment.onCreateView(mockLayoutInflater, mockViewGroup, mockBundle);
        verify(mockLayoutInflater, times(1)).inflate(R.layout.layout_fragment_list, mockViewGroup, false);
    }

    @Test
    public void testOnCreate() throws Exception {
        Whitebox.setInternalState(spyArticlesFragment, "application", mockApplication);
        spyArticlesFragment.onCreate(mockBundle);
    }
}
