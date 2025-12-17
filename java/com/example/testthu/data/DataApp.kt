
    package com.example.testthu.data

    import androidx.room.Database
    import androidx.room.Room
    import androidx.room.RoomDatabase
    import android.content.Context

    @Database(entities = [com.example.testthu.data.Comment::class], version = 1)
    abstract class DataApp : RoomDatabase() {
        abstract fun commentDao(): com.example.testthu.data.CommentDao

        companion object {
            @Volatile
            private var INSTANCE: DataApp? = null

            fun getDatabase(context: Context): DataApp {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        DataApp::class.java,
                        "comment_appdata"
                    ).build()
                    INSTANCE = instance
                    instance
                }
            }
        }
    }