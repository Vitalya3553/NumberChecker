package com.example.numberchecker.data.remote.dto

import com.example.numberchecker.domain.model.NumberInfo
import kotlinx.coroutines.Deferred

data class NumberInfoDto(
    val valid: Boolean,
    val number: String?,
    val local_format: String?,
    val international_format: String?,
    val country_prefix: String?,
    val country_code: String?,
    val country_name: String?,
    val location: String?,
    val carrier: String?,
    val line_type: String?,
) {
    fun toNumberInfo():NumberInfo{
        return NumberInfo(
            carrier = carrier,
            country_code = country_code,
            country_name = country_name,
            country_prefix = country_prefix,
            international_format = international_format,
            line_type = line_type,
            local_format = local_format,
            location = location,
            number = number,
            valid = valid
        )
    }
}