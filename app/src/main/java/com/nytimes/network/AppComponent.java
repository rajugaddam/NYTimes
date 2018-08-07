package com.nytimes.network;





import com.nytimes.NYTimesArticleList;
import com.nytimes.fragment.ArticlesFragment;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {NetWorkModule.class, AppModule.class})
public interface AppComponent {

    void inject(NYTimesArticleList nyTimesArticleList);
    void inject(ArticlesFragment articlesFragment);
}
