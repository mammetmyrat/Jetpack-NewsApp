package dev.mammet.jetpacknewsapp.domain.usecases.app_entry

import dev.mammet.jetpacknewsapp.domain.manger.LocalUserManger
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManger
) {

    operator fun invoke():Flow<Boolean>{
        return localUserManager.readAppEntry()
    }
}