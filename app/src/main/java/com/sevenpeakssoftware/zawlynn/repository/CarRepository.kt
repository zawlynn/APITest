package com.sevenpeakssoftware.zawlynn.repository

import android.content.Context
import android.util.Log
import com.sevenpeakssoftware.zawlynn.api.IApiServices
import com.sevenpeakssoftware.zawlynn.data.network.NetworkBoundResource
import com.sevenpeakssoftware.zawlynn.data.network.Resource
import com.sevenpeakssoftware.zawlynn.data.network.response.APIResponse
import com.sevenpeakssoftware.zawlynn.model.CarModel
import io.reactivex.Flowable
import retrofit2.Response

class CarRepository(val apiRepository: APIRepository,val dbRepository: DatabaseRepository) {
    fun getDataFromApi(context:Context):Flowable<Resource<List<CarModel>>>{
        return object :NetworkBoundResource<APIResponse,List<CarModel>>(context){
            override fun saveCallResult(request: APIResponse) {
                Log.d("CAR REPOSITORY","SAVE CALL REESULT")
               dbRepository.saveCars(request.contents)
            }

            override fun loadFromDb(): Flowable<List<CarModel>> {
                Log.d("CAR REPOSITORY","LOAD FROM DB")
              return dbRepository.fetchLocalData()
            }

            override fun createCall(): Flowable<Response<APIResponse>> {
                Log.d("CAR REPOSITORY","CREATE CALL")
               return apiRepository.getRetrofitClient().create(IApiServices::class.java).getDataFromAPI()
            }

        }.asFlowable()
    }
}