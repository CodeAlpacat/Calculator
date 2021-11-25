package com.example.calculator

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.calculator.dao.HistoryDao
import com.example.calculator.model.History
//버전 정보를 적어줘야 버전이 업데이트되어도 계속 데이터베이스가 유지된다.
//데이터베이스에 히스토리를 선언해줘야 사용할 수 있다.
@Database(entities = [History::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao

}