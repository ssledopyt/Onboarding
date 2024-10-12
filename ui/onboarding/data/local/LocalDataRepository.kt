package ui.onboarding.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ui.onboarding.data.OnboardingDataState
import javax.inject.Inject
import javax.inject.Singleton


interface OnboardingDataRepository{

    val getData : Flow<OnboardingDataState>

    suspend  fun saveData(state: OnboardingDataState)

    suspend fun clearData()
}

@Singleton
class OnboardingDataRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : OnboardingDataRepository{

    private val gson: Gson = Gson()
    private val DATA_KEY = stringPreferencesKey("data")

    override val getData: Flow<OnboardingDataState>  = dataStore.data.map { pref ->
            val jsonString = pref[DATA_KEY] ?: ""
            gson.fromJson(jsonString, OnboardingDataState::class.java)
        }


    override suspend fun saveData(state: OnboardingDataState) {
        dataStore.edit { pref ->
            val jsonString = gson.toJson(state)
            pref[DATA_KEY] = jsonString
        }
    }

    override suspend fun clearData() {
        dataStore.edit { pref ->
            pref.clear()
        }
    }

}

/*
private const val ONBOARDING_PREFERENCES = "onboarding_preferences"


object OnboardingManager{
    val NAME = stringPreferencesKey("name")
    val EMAIL_OR_PHONE = stringPreferencesKey("email_or_phone")

    val INSTITUTION_NAME = stringPreferencesKey("institution_name")
    val INSTITUTION_COUNTRY_AND_CITY = stringPreferencesKey("institution_country_and_city")
    val INSTITUTION_ADDRESS = stringPreferencesKey("institution_address")
    val IS_NEW_PLACE = booleanPreferencesKey("is_new_place")
    val AUTOMATION_SYSTEM_NAME = stringPreferencesKey("automation_system_name")

    val INSTITUTION_TYPE_BAR = booleanPreferencesKey("institution_type_bar")
    val INSTITUTION_TYPE_CAFE = booleanPreferencesKey("institution_type_cafe")
    val INSTITUTION_TYPE_CANTEEN = booleanPreferencesKey("institution_type_canteen")
    val INSTITUTION_TYPE_COFFEE_SHOP = booleanPreferencesKey("institution_type_coffee_shop")
    val INSTITUTION_TYPE_COOKERY = booleanPreferencesKey("institution_type_cookery")
    val INSTITUTION_TYPE_OTHER = booleanPreferencesKey("institution_type_other")

    val TOTAL_AREA = stringPreferencesKey("total_area")
    val SEATING = stringPreferencesKey("seating")
    val HALLS_AREA = stringPreferencesKey("halls_area")
    val KITCHEN_AREA = stringPreferencesKey("kitchen_area")

    val SERVICE_TYPE_TAKEAWAY = booleanPreferencesKey("service_type_takeaway")
    val SERVICE_TYPE_IN = booleanPreferencesKey("service_type_in")
    val SERVICE_TYPE_DELIVERY = booleanPreferencesKey("service_type_delivery")

}
*/
