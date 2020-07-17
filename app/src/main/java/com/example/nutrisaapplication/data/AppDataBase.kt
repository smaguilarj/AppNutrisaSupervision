package com.example.nutrisaapplication.data

/**
DELL
17/07/2020
NutrisaApplication
 */
//@Database(entities = [Developers::class], version = 1)
//abstract class AppDatabase : RoomDatabase() {
//    abstract fun developerDAO(): DeveloperDAO?
//
//    companion object {
//        private var INSTANCE: AppDatabase? = null
//        fun getAppDB(context: Context): AppDatabase? {
//            if (INSTANCE == null) {
//                INSTANCE = Room.databaseBuilder(
//                    context.getApplicationContext(),
//                    AppDatabase::class.java, Constantes.NAME_DATABASE
//                )
//                    .build()
//            }
//            return INSTANCE
//        }
//
//        fun destroyBD() {
//            INSTANCE = null
//        }
//    }
//}