package com.techiespace.projects.tenmagicalritualstosuccess.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Ritual::class, Habit::class], version = 1)

abstract class RitualDatabase : RoomDatabase() {

    abstract fun ritualDao(): RitualDao
    abstract fun habitDao(): HabitDao

    companion object {
        @Volatile
        private var INSTANCE: RitualDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): RitualDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RitualDatabase::class.java,
                    "ritual_database"
                )
                    .addCallback(RitualDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class RitualDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.ritualDao(), database.habitDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(ritualDao: RitualDao, habitDao: HabitDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.

            // Delete all content here.
            ritualDao.deleteAll()

            // Add sample rituals.
            val rituals = listOf(
                Ritual(
                    1,
                    false,
                    "Believe / Visualize",
                    "Dream big and give give it everything you got.",
                    "A3"
                ),
                Ritual(
                    2,
                    true,
                    "Faith",
                    "Faith is the fire that fuels the dream, that powers the engine that turns the world.",
                    "B3"
                ),
                Ritual(
                    3,
                    true,
                    "Plan",
                    "Make a damn schedule", "C3"
                ),
                Ritual(
                    4,
                    true,
                    "Action",
                    "What gets you out of bed everyday",
                    "C3"
                ),
                Ritual(
                    5,
                    true,
                    "Reflect",
                    "How was it? What could you have done better?",
                    "C3"
                ),
                Ritual(
                    6,
                    true,
                    "Believe / Visualize",
                    "Dream big and give give it everything you got.",
                    "A3"
                ),
                Ritual(
                    7,
                    true,
                    "Faith",
                    "Faith is the fire that fuels the dream, that powers the engine that turns the world.",
                    "B3"
                ),
                Ritual(
                    8,
                    true,
                    "Plan",
                    "Make a damn schedule", "C3"
                ),
                Ritual(
                    9,
                    true,
                    "Action",
                    "What gets you out of bed everyday",
                    "C3"
                ),
                Ritual(
                    10,
                    true,
                    "Reflect",
                    "How was it? What could you have done better?",
                    "C3"
                )
            )
            ritualDao.insertMany(rituals)

            habitDao.deleteAll()
            val habits = listOf(
                Habit(
                    1,
                    1,
                    "Believe / Visualize",
                    "Dream big and give give it everything you got.",
                    "",
                    false
                ),
                Habit(
                    2,
                    2,
                    "Faith",
                    "Faith is the fire that fuels the dream, that powers the engine that turns the world.",
                    "",
                    false
                ),
                Habit(3, 3, "Plan", "Make a damn schedule", "", false),
                Habit(4, 4, "Action", "What gets you out of bed everyday", "", false),
                Habit(5, 5, "Reflect", "How was it? What could you have done better?", "", false),
                Habit(6, 6, "Reflect", "How was it? What could you have done better?", "", false),
                Habit(7, 7, "Reflect", "How was it? What could you have done better?", "", false),
                Habit(8, 8, "Reflect", "How was it? What could you have done better?", "", false),
                Habit(9, 9, "Reflect", "How was it? What could you have done better?", "", false),
                Habit(10, 10, "Reflect", "How was it? What could you have done better?", "", false)
            )
            habitDao.insertMany(habits)
        }
    }
}