package mx.macropay.challenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.macropay.challenge.data.database.dao.MovieDao
import mx.macropay.challenge.data.database.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class ChallengeDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
}
