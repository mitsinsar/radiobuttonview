package com.hipo.radiogroupview

fun String?.parseCsv(): List<String> {
    return this?.split(",")?.map { it.trim() } ?: emptyList()
}
