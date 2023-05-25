package com.example.classwork7.data

import java.util.UUID

data class DummyItem(val id: String, val content: String, val details: String) {
    constructor(content: String, details: String): this(UUID.randomUUID().toString(), content, details)

    companion object {
        fun getList(): List<DummyItem> {
            val list: MutableList<DummyItem> = mutableListOf()
            for(i in 1..25) {
                list.add(DummyItem("Item $i", "Details for item $i"))
            }
            return list
        }
    }
}
