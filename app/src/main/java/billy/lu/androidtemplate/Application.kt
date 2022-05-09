package billy.lu.androidtemplate

import android.app.Application
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class Application: Application() {

    init {

    }

    companion object {
        val instance = Application()

        private lateinit var mSharePreference : SharedPreferences
    }

    fun getEncryptionSP(): SharedPreferences {
        return mSharePreference
    }

    override fun onCreate() {
        super.onCreate()
        val res: Resources = super.getResources()
        val config = Configuration()
        config.setToDefaults()
        res.updateConfiguration(config, res.displayMetrics)


        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        mSharePreference = EncryptedSharedPreferences.create(
            getString(R.string.app_name),
            masterKeyAlias,
            this,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override fun getResources(): Resources {
        val res = super.getResources()
        if (res != null) {
            val config = res.configuration
            if (config != null && config.fontScale != 1.0f) {
                config.fontScale = 1.0f
                res.updateConfiguration(config, res.displayMetrics)
            }
        }
        return res
    }
}