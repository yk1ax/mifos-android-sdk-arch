package org.mifos.core.repositories

import io.reactivex.Flowable
import org.mifos.core.apimanager.BaseApiManager
import org.mifos.core.models.user.User

/**
 * Created by grandolf49 on 06-06-2020
 *
 * Repository class to interact with AuthService
 */
class AuthRepository {

    // This will later be replaced by Dependency Injection
    private var baseApiManager = BaseApiManager()

    fun login(username: String, password: String): Flowable<User>? {
        return baseApiManager.getAuthApi()?.authenticate(username, password)
    }
}