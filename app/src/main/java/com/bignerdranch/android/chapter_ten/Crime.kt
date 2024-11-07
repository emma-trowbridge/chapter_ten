package com.bignerdranch.android.chapter_ten

import java.util.Date
import java.util.UUID

data class Crime (
    val id:UUID,
    var title:String, //changed from val to var
    val date: Date,
    var isSolved:Boolean, //changed from val to var
    var requiresPolice: Boolean //property that determines if police need to be involved
)