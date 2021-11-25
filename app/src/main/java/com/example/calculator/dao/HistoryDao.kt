package com.example.calculator.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.calculator.model.History

@Dao
//history를 지우고 join하는 것을 관리.
interface HistoryDao {
    //모든 history를 검색하는 sql 쿼리문.
    @Query("SELECT * FROM history")
    fun getAll(): List<History>

    //insert하는 메소드
    @Insert
    fun insertHistory(history: History)

    @Query("DELETE FROM history")
    fun deleteAll()

/*
    @Delete
    fun delete(history: History)
    //result 조건을 걸고 찾는다. LIMIT 1을 걸면 하나만 반환이 가능하다.
    @Query("SELECT * FROM history WHERE result LIKE :result")
    fun findByResult(result: String)

 */
}