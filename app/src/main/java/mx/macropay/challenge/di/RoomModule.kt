package mx.macropay.challenge.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mx.macropay.challenge.data.database.ChallengeDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val MACRO_DATABASE_NAME = "challenge_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ChallengeDatabase::class.java, MACRO_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideMovieDao(db: ChallengeDatabase) = db.getMovieDao()

}
