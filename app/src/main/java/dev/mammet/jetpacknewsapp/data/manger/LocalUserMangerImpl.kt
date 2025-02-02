package dev.mammet.jetpacknewsapp.data.manger

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dev.mammet.jetpacknewsapp.domain.manger.LocalUserManger
import dev.mammet.jetpacknewsapp.utils.Constants
import dev.mammet.jetpacknewsapp.utils.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserMangerImpl(
    private val context: Context
):LocalUserManger {
    override suspend fun saveAppEntry() {
        context.datastore.edit {settings->
            settings[PreferencesKey.APP_KEY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.datastore.data.map { preferences->
            preferences[PreferencesKey.APP_KEY]?:false

        }
    }
}

private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

private object PreferencesKey{
    val APP_KEY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}