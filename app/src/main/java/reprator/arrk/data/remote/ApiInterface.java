package reprator.arrk.data.remote;

import reprator.arrk.controllers.constants.RestConstants;
import reprator.arrk.data.modal.StarWarProfile;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET(RestConstants.STARWAR_CHARACTERS)
    Call<StarWarProfile> starWarCharacters(@Query(RestConstants.REQUEST_PAGE) int nextPage);
}