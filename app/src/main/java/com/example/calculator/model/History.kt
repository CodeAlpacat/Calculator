package com.example.calculator.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//이 클래스자체를 DB table로 사용함.
//androidx에 있는 room 기능을 가져온 것임.
//이렇게 라이브러리를 가져와서 사용해주려면 gradle -> implementation으로 라이브러리를 추가할 수 있다.
@Entity
data class History(
    @PrimaryKey val uid: Int?,
    @ColumnInfo(name = "expression") val expression: String?,
    @ColumnInfo(name = "result") val result: String?
)