/*
 * Copyright (c) 2021 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.vector.app.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import im.vector.app.databinding.FragmentSelectEnvBinding
import org.matrix.android.sdk.internal.network.RetrofitFactory

class SelectEnvFragment : AbstractLoginFragment<FragmentSelectEnvBinding>() {
    override fun resetViewModel() {}

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?) = FragmentSelectEnvBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        views.btnDev.setOnClickListener {
            RetrofitFactory.BASE_URL = "https://cychat-dev.cioinfotech.com"
            loginViewModel.handle(LoginAction.PostViewEvent(LoginViewEvents.OnHomeserverSelection))

        }

        views.btnTest.setOnClickListener {
            RetrofitFactory.BASE_URL = "https://cyberiaqa-api.cioinfotech.com"
            loginViewModel.handle(LoginAction.PostViewEvent(LoginViewEvents.OnHomeserverSelection))
        }

        views.btnUat.setOnClickListener {
            RetrofitFactory.BASE_URL = "https://cychat-ct.cioinfotech.com"
            loginViewModel.handle(LoginAction.PostViewEvent(LoginViewEvents.OnHomeserverSelection))
        }
    }
}
