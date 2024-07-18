package com.dicoding.asclepius.repo

import android.app.Application
import androidx.lifecycle.LiveData
import com.dicoding.asclepius.database.History
import com.dicoding.asclepius.database.HistoryDao
import com.dicoding.asclepius.database.HistoryDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class HistoryRepository(application: Application) {
    private val mHistoryDao: HistoryDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = HistoryDatabase.getDatabase(application)
        mHistoryDao = db.historyDao()
    }

    fun getAllNotes(): LiveData<List<History>> = mHistoryDao.getAll()
    fun insert(history: History) {
        executorService.execute { mHistoryDao.insert(history) }
    }

}