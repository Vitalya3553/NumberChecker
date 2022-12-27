package com.example.numberchecker.domain.model

data class NumberInfo(
    val carrier: String?,
    val country_code: String?,
    val country_name: String?,
    val country_prefix: String?,
    val international_format: String?,
    val line_type: String?,
    val local_format: String?,
    val location: String?,
    val number: String?,
    val valid: Boolean
)
