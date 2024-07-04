package com.example.aad_project

import android.util.Log
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject

class QueryHandler{
    //lateinit var ans:BotResponce

    fun createChatCompletion(message: String): String? {

        Log.d("TAG" , " start createChatCompletion: $message")
/*        val client = OkHttpClient()

        val mediaType = "application/json".toMediaTypeOrNull()
        val body =
            "[{\"content\":\"Hello! I'm an AI assistant bot based on ChatGPT 3. How may I help you?\",\"role\":\"system\"},{\"content\":\"$message\",\"role\":\"user\"}]".toRequestBody(
                mediaType
            )
        val request = Request.Builder()
            .url("https://chatgpt-api8.p.rapidapi.com/")
            .post(body)
            .addHeader("x-rapidapi-key" , "6ec83efecfmsh53997cf1b4ae405p19b400jsn5befa62ed1ac")
            .addHeader("x-rapidapi-host" , "chatgpt-api8.p.rapidapi.com")
            .addHeader("Content-Type" , "application/json")
            .build()

        val response = client.newCall(request).execute()*/
        val client = OkHttpClient()

        val mediaType = "application/json".toMediaTypeOrNull()
        val body =
            "{\"messages\":[{\"role\":\"user\",\"content\":\"$message\"}],\"system_prompt\":\"\",\"temperature\":0.9,\"top_k\":5,\"top_p\":0.9,\"max_tokens\":256,\"web_access\":false}".toRequestBody(
                mediaType
            )
        val request = Request.Builder()
            .url("https://chatgpt-42.p.rapidapi.com/conversationgpt4-2")
            .post(body)
            .addHeader("x-rapidapi-key", "6ec83efecfmsh53997cf1b4ae405p19b400jsn5befa62ed1ac")
            .addHeader("x-rapidapi-host", "chatgpt-42.p.rapidapi.com")
            .addHeader("Content-Type", "application/json")
            .addHeader("X-RapidAPI-Mock-Response", "510")

            .build()

        val response = client.newCall(request).execute()
        val ans1= response.body?.string()
        val jsonObject= ans1?.let { JSONObject(it) }
        val jsonArray= jsonObject?.get("result")



        Log.d("TAG" , "11createChatCompletion: ${ans1} $jsonObject $jsonArray")
        return "$jsonArray"
    }
}