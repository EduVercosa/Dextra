package br.com.dextra.api

interface ResponseRequest<T> {

    fun success(result: T)

    fun fail(message: String? = "")
}