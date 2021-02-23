package com.github.ahmadriza.mvvmboilerplate.ui.login

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.databinding.FragmentLoginBinding
import com.github.ahmadriza.mvvmboilerplate.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val vm: LoginVM by viewModels()

    override fun getLayoutResource(): Int = R.layout.fragment_login

    override fun initViews() {

        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnSignIn.setOnClickListener {
            vm.login(
                email = binding.etEmail.text?.toString() ?: "",
                password = binding.etEmail.text?.toString() ?: ""
            )
        }

    }

    override fun initObservers() {
        vm.response.observe(viewLifecycleOwner) {

        }
    }

    override fun initData() {

    }


}