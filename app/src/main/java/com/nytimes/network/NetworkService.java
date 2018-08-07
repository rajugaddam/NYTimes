package com.nytimes.network;


import com.nytimes.model.Response;

import retrofit2.http.GET;
import rx.Observable;



public interface NetworkService {
    @GET("svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=aea8b7c7a56e4bdd994a1325a61869ec")
    Observable<Response> getBaseURL();
}
