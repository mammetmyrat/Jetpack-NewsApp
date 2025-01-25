package dev.mammet.jetpacknewsapp.domain.usecases

import dev.mammet.jetpacknewsapp.domain.manger.LocalUserManger

class SaveAppEntry(
   private val localUserManager: LocalUserManger
) {


    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}