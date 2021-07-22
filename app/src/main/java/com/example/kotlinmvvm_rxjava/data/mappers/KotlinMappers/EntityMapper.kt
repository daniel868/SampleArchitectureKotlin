package com.example.kotlinmvvm_rxjava.data.mappers.KotlinMappers

interface EntityMapper<FromType, ToType> {
    fun map(type: FromType): ToType

}