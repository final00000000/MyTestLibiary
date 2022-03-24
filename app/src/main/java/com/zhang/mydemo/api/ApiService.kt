package com.zhang.mydemo.api

import retrofit2.http.GET
import java.util.concurrent.Flow

/**
 * @Author : zhang
 * @Create Time : 2022/3/23
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
interface ApiService {

    @GET(Api.BASE_URL)
    suspend fun baaiDu(): Flow

}