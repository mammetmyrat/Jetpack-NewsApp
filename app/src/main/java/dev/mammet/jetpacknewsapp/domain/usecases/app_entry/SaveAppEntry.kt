package dev.mammet.jetpacknewsapp.domain.usecases.app_entry

import dev.mammet.jetpacknewsapp.domain.manger.LocalUserManger

class SaveAppEntry(
   private val localUserManager: LocalUserManger
) {


    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}